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
    public static final String[] DETAIL_BARANG_COLUMN_TITLE = {"ID Barang", "Nama", "Kategori", "Jenis", "Harga", "Stock", "Bahan", "Satuan", "Ukuran", "Keterangan"};
    public static final int DETAIL_BARANG_COLUMN_COUNT = DETAIL_BARANG_COLUMN_TITLE.length;
    private final Connection conn;

    public Barang(Connection conn) {
        this.conn = conn;
    }

    public String[][] getListBarang() {
        String[][] result = null;
        try {
            String sql = "SELECT b.idbarang, b.namabarang, k.kategori, k.jenis, b.hargabarang, b.jumlah FROM barang b, kategori k WHERE b.kategori = k.idkategori";
            PreparedStatement stmt = conn.prepareStatement(sql);

            try (ResultSet rs = stmt.executeQuery()) {
                rs.last();
                int row = rs.getRow();
                result = new String[row][BARANG_COLUMN_COUNT];

                rs.beforeFirst();
                int i = 0;
                while (rs.next()) {
                    result[i][0] = rs.getString("idbarang");
                    result[i][1] = rs.getString("namabarang");
                    result[i][2] = rs.getString("kategori");
                    result[i][3] = rs.getString("jenis");
                    result[i][4] = rs.getString("hargabarang");
                    result[i][5] = rs.getString("jumlah");
                    i++;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Barang.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public String[][] getListBarang(String keyword) {
        String[][] result = null;
        try {
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
                while (rs.next()) {
                    result[i][0] = rs.getString("idbarang");
                    result[i][1] = rs.getString("namabarang");
                    result[i][2] = rs.getString("kategori");
                    result[i][3] = rs.getString("jenis");
                    result[i][4] = rs.getString("hargabarang");
                    result[i][5] = rs.getString("jumlah");
                    i++;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Barang.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public String[] getListDetailBarang(String idBarang) {
        String[] result = null;
        try {
            String sql = "SELECT b.idbarang, b.namabarang, k.kategori, k.jenis, b.hargabarang, b.jumlah, d.bahan, d.satuan, d.ukuran, d.keterangan FROM barang b, kategori k, detailbarang d WHERE b.kategori = k.idkategori AND b.idbarang = d.idbarang AND b.idbarang = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, idBarang);

            try (ResultSet rs = stmt.executeQuery()) {
                result = new String[DETAIL_BARANG_COLUMN_COUNT];

                rs.next();
                result[0] = rs.getString("idbarang");
                result[1] = rs.getString("namabarang");
                result[2] = rs.getString("kategori");
                result[3] = rs.getString("jenis");
                result[4] = rs.getString("hargabarang");
                result[5] = rs.getString("jumlah");
                result[6] = rs.getString("bahan");
                result[7] = rs.getString("satuan");
                result[8] = rs.getString("ukuran");
                result[9] = rs.getString("keterangan");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Barang.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public String[] getListKategori() {
        String[] result = null;
        try {
            String sql = "SELECT DISTINCT kategori FROM kategori ORDER by kategori";
            PreparedStatement stmt = conn.prepareStatement(sql);

            try (ResultSet rs = stmt.executeQuery()) {
                rs.last();
                int row = rs.getRow();
                result = new String[row];

                rs.beforeFirst();
                int i = 0;
                while (rs.next()) {
                    result[i] = rs.getString("kategori");
                    i++;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Barang.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public int getIdKategori(String kategori, String jenis) {
        int result = 0;
        try {
            String sql = "SELECT idkategori FROM kategori WHERE kategori = ? AND jenis = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, kategori);
            stmt.setString(2, jenis);

            try (ResultSet rs = stmt.executeQuery()) {
                rs.last();
                int row = rs.getRow();
                if (row > 0) {
                    result = rs.getInt("idkategori");
                } else {
                    // insert
                    result = insertKategori(kategori, jenis);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Barang.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public int insertKategori(String kategori, String jenis) {
        int result = 0;
        try {
            String sql = "INSERT INTO kategori(kategori, jenis) VALUES(?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, kategori);
            stmt.setString(2, jenis);

            stmt.executeUpdate();
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                rs.next();
                result = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Barang.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public boolean insertBarang(String idBarang, String namaBarang, String kategori, String jenis, int harga, int stock, String bahan, String satuan, String ukuran, String keterangan) {
        boolean result = false;
        try {
            // start transaction
            conn.setAutoCommit(false);

            // load kategori id
            int idkategori = getIdKategori(kategori, jenis);

            // insert to barang
            String sqlBarang = "INSERT INTO barang(idbarang, namabarang, kategori, hargabarang, jumlah) VALUES(?, ?, ?, ?, ?)";
            PreparedStatement stmtBarang = conn.prepareStatement(sqlBarang);

            stmtBarang.setString(1, idBarang);
            stmtBarang.setString(2, namaBarang);
            stmtBarang.setInt(3, idkategori);
            stmtBarang.setInt(4, harga);
            stmtBarang.setInt(5, stock);

            stmtBarang.executeUpdate();

            // insert to detilbarang
            String sqlDetil = "INSERT INTO detailbarang(idbarang, bahan, satuan, ukuran, keterangan) VALUES(?, ?, ?, ?, ?)";
            PreparedStatement stmtDetil = conn.prepareStatement(sqlDetil);

            stmtDetil.setString(1, idBarang);
            stmtDetil.setString(2, bahan);
            stmtDetil.setString(3, satuan);
            stmtDetil.setString(4, ukuran);
            stmtDetil.setString(5, keterangan);

            stmtDetil.executeUpdate();

            // end transaction
            conn.commit();
            conn.setAutoCommit(true);
            
            result = true;
        } catch (SQLException ex) {
            Logger.getLogger(Barang.class.getName()).log(Level.SEVERE, null, ex);
            try {
                conn.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(Barang.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        return result;
    }

    public boolean updateBarang(String idBarang, int harga, int stock, String bahan, String satuan, String ukuran, String keterangan) {
        boolean result = false;
        try {
            // start transaction
            conn.setAutoCommit(false);

            // update barang
            String sqlBarang = "UPDATE barang SET hargabarang = ?, jumlah = ? WHERE idbarang = ?";
            PreparedStatement stmtBarang = conn.prepareStatement(sqlBarang);
            
            stmtBarang.setInt(1, harga);
            stmtBarang.setInt(2, stock);
            stmtBarang.setString(3, idBarang);

            stmtBarang.executeUpdate();

            // insert to detilbarang
            String sqlDetil = "UPDATE detailbarang SET bahan = ?, satuan = ?, ukuran = ?, keterangan = ? WHERE idbarang = ?";
            PreparedStatement stmtDetil = conn.prepareStatement(sqlDetil);

            stmtDetil.setString(1, bahan);
            stmtDetil.setString(2, satuan);
            stmtDetil.setString(3, ukuran);
            stmtDetil.setString(4, keterangan);
            stmtDetil.setString(5, idBarang);

            stmtDetil.executeUpdate();

            // end transaction
            conn.commit();
            conn.setAutoCommit(true);
            
            result = true;
        } catch (SQLException ex) {
            Logger.getLogger(Barang.class.getName()).log(Level.SEVERE, null, ex);
            try {
                conn.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(Barang.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        return result;
    }
}
