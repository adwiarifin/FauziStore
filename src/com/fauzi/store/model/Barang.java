package com.fauzi.store.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author adwiarifin
 */
public class Barang {

    public static final String[] BARANG_COLUMN_TITLE = {"ID Barang", "Nama", "Kategori", "Jenis", "Harga", "Stock"};
    public static final int BARANG_COLUMN_COUNT = BARANG_COLUMN_TITLE.length;
    private final Connection conn;
    
    public Barang(Connection conn) {
        this.conn = conn;
    }

    public String[][] getListBarang(){
        String[][] result = null;
        try{
            String sql = "SELECT b.idbarang, b.namabarang, k.kategori, k.jenis, b.hargabarang, b.jumlah FROM barang b, kategori k WHERE b.kategori = k.idkategori";
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            try (ResultSet rs = stmt.executeQuery()) {
                rs.last();
                int row = rs.getRow();
                result = new String[row][BARANG_COLUMN_COUNT];
                
                rs.beforeFirst();
                int i = 0;
                while(rs.next()){
                    result[i][0] = rs.getString("idbarang");
                    result[i][1] = rs.getString("namabarang");
                    result[i][2] = rs.getString("kategori");
                    result[i][3] = rs.getString("jenis");
                    result[i][4] = rs.getString("hargabarang");
                    result[i][5] = rs.getString("jumlah");
                    i++;
                }
            }
        } catch(SQLException ex){
            Logger.getLogger(Barang.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public String[][] getListBarang(String keyword){
        String[][] result = null;
        try{
            String sql = "SELECT b.idbarang, b.namabarang, k.kategori, k.jenis, b.hargabarang, b.jumlah FROM barang b, kategori k WHERE b.kategori = k.idkategori AND (b.idbarang LIKE ? OR b.namabarang LIKE ? OR k.kategori LIKE ? OR k.jenis LIKE ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + keyword + "%");
            stmt.setString(2, "%" + keyword + "%");
            stmt.setString(3, "%" + keyword + "%");
            stmt.setString(4, "%" + keyword + "%");
            
            try (ResultSet rs = stmt.executeQuery()) {
                rs.last();
                int row = rs.getRow();
                result = new String[row][BARANG_COLUMN_COUNT];
                
                rs.beforeFirst();
                int i = 0;
                while(rs.next()){
                    result[i][0] = rs.getString("idbarang");
                    result[i][1] = rs.getString("namabarang");
                    result[i][2] = rs.getString("kategori");
                    result[i][3] = rs.getString("jenis");
                    result[i][4] = rs.getString("hargabarang");
                    result[i][5] = rs.getString("jumlah");
                    i++;
                }
            }
        } catch(SQLException ex){
            Logger.getLogger(Barang.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
