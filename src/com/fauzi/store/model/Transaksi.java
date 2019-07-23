package com.fauzi.store.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sasuke
 */
public class Transaksi {

    private final Connection conn;

    public Transaksi(Connection conn) {
        this.conn = conn;
    }

    public int getTransaksiCount() {
        int result = 0;
        try {
            String sql = "SELECT COUNT(nojual) FROM transaksi";
            PreparedStatement stmt = conn.prepareStatement(sql);

            try (ResultSet rs = stmt.executeQuery()) {
                rs.last();
                result = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Transaksi.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    /**
     * @param nota
     * @param total
     * @param idpegawai
     * @param metodeBayar
     * @param detilTransaksi
     * @return true if data is successfully inserted to database, false
     * otherwise
     */
    public boolean insertTransaksi(String nota, String idpegawai, int total, int metodeBayar, String[][] detilTransaksi) {
        boolean result = false;
        try {
            // start transaction
            conn.setAutoCommit(false);

            // get today
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String today = sdf.format(new Date());

            // insert to transaksi
            String sqlTransaksi = "INSERT INTO transaksi(nota, tanggaljual, idpegawai, totaljual, idbayar) VALUES(?, ?, ?, ?, ?)";
            PreparedStatement stmtTransaksi = conn.prepareStatement(sqlTransaksi, PreparedStatement.RETURN_GENERATED_KEYS);

            stmtTransaksi.setString(1, nota);
            stmtTransaksi.setString(2, today);
            stmtTransaksi.setString(3, idpegawai);
            stmtTransaksi.setInt(4, total);
            stmtTransaksi.setInt(5, metodeBayar);

            stmtTransaksi.executeUpdate();

            // get transaksi insert id
            int transaksiId = 0;
            ResultSet rs = stmtTransaksi.getGeneratedKeys();
            if (rs.next()) {
                transaksiId = rs.getInt(1);
            }

            // insert to detiltransaksi
            for (String[] column : detilTransaksi) {
                String idBarang = column[0];
                int harga = Integer.parseInt(column[1]);
                int totalBeli = Integer.parseInt(column[2]);
                int diskon = Integer.parseInt(column[3]);
                int subtotal = Integer.parseInt(column[4]);
                
                String sqlDetilTransaksi = "INSERT INTO detailtransaksi(nojual, idbarang, jmlbarang, hrgsatuan, discount, totalharga) VALUES(?, ?, ?, ?, ?, ?)";
                PreparedStatement stmtDetilTransaksi = conn.prepareCall(sqlDetilTransaksi);
                
                stmtDetilTransaksi.setInt(1, transaksiId);
                stmtDetilTransaksi.setString(2, idBarang);
                stmtDetilTransaksi.setInt(3, totalBeli);
                stmtDetilTransaksi.setInt(4, harga);
                stmtDetilTransaksi.setInt(5, diskon);
                stmtDetilTransaksi.setInt(6, subtotal);
                
                stmtDetilTransaksi.executeUpdate();
                
                // update barang stock
                String sqlUpdateStock = "UPDATE detailbarang SET jumlah = jumlah - ? WHERE idbarang = ?";
                PreparedStatement stmtUpdateStock = conn.prepareStatement(sqlUpdateStock);
                
                stmtUpdateStock.setInt(1, totalBeli);
                stmtUpdateStock.setString(2, idBarang);
                
                stmtUpdateStock.executeUpdate();
            }

            // end transaction
            conn.commit();
            conn.setAutoCommit(true);

            result = true;
        } catch (SQLException ex) {
            Logger.getLogger(Transaksi.class.getName()).log(Level.SEVERE, null, ex);
            try {
                conn.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(Transaksi.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        return result;
    }
}
