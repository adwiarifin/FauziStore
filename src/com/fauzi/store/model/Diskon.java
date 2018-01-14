package com.fauzi.store.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sasuke
 */
public class Diskon {

    public static final String[] DISKON_COLUMN_TITLE = {"Nama Promo", "Tgl Mulai", "Tgl Selesai", "Jenis Diskon", "Diskon"};
    public static final int DISKON_COLUMN_COUNT = DISKON_COLUMN_TITLE.length;
    private final Connection conn;

    public Diskon(Connection conn) {
        this.conn = conn;
    }

    public String[][] getListDiskon() {
        String[][] result = null;
        try {
            String sql = "SELECT namapromo, tglmulai, tglselesai, jnsdiskon, discount FROM diskon ORDER BY iddiskon DESC";
            PreparedStatement stmt = conn.prepareStatement(sql);

            try (ResultSet rs = stmt.executeQuery()) {
                rs.last();
                int row = rs.getRow();
                result = new String[row][DISKON_COLUMN_COUNT];

                rs.beforeFirst();
                int i = 0;
                while (rs.next()) {
                    result[i][0] = rs.getString("namapromo");
                    result[i][1] = rs.getString("tglmulai");
                    result[i][2] = rs.getString("tglselesai");
                    result[i][3] = rs.getString("jnsdiskon");
                    result[i][4] = rs.getString("discount");
                    i++;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Barang.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public boolean insertDiskon(String nama, String jenis, int diskon, String mulai, String akhir) {
        boolean result = false;
        try {
            String sql = "INSERT INTO diskon(namapromo, tglmulai, tglselesai, jnsdiskon, discount) VALUES(?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareCall(sql);
            
            stmt.setString(1, nama);
            stmt.setString(2, mulai);
            stmt.setString(3, akhir);
            stmt.setString(4, jenis);
            stmt.setInt(5, diskon);
            
            int affected = stmt.executeUpdate();
            
            if (affected > 0) {
                result = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Diskon.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
