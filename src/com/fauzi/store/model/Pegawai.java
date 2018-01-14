package com.fauzi.store.model;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author Sasuke
 */
public class Pegawai {

    public static final String[] PEGAWAI_COLUMN_TITLE = {"ID Pegawai", "Nama Pegawai"};
    public static final int PEGAWAI_COLUMN_COUNT = PEGAWAI_COLUMN_TITLE.length;
    private final Connection conn;

    public Pegawai(Connection conn) {
        this.conn = conn;
    }

    public boolean login(String idPegawai, String password) {
        boolean result = false;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes("UTF-8"));
            byte[] digest = md.digest();
            String hash = DatatypeConverter.printHexBinary(digest).toLowerCase();

            String sql = "SELECT idpegawai FROM pegawai WHERE idpegawai = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, idPegawai);
            stmt.setString(2, hash);

            try (ResultSet rs = stmt.executeQuery()) {
                rs.last();
                if (rs.getRow() > 0) {
                    result = true;
                }
            }
        } catch (NoSuchAlgorithmException | SQLException | UnsupportedEncodingException ex) {
            Logger.getLogger(Pegawai.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public String getNamaPegawai(String idPegawai) {
        String result = null;
        try {
            String sql = "SELECT namapegawai FROM pegawai WHERE idpegawai = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, idPegawai);

            try (ResultSet rs = stmt.executeQuery()) {
                rs.last();
                if (rs.getRow() > 0) {
                    result = rs.getString("namapegawai");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Pegawai.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public boolean isPegawaiAdmin(String idPegawai) {
        boolean result = false;
        if (idPegawai.charAt(0) == 'O') {
            result = true;
        }
        return result;
    }

    public String[][] getListPegawai() {
        String[][] result = null;
        try {
            String sql = "SELECT idpegawai, namapegawai FROM pegawai ORDER BY namapegawai";
            PreparedStatement stmt = conn.prepareStatement(sql);

            try (ResultSet rs = stmt.executeQuery()) {
                rs.last();
                int row = rs.getRow();
                result = new String[row][PEGAWAI_COLUMN_COUNT];

                rs.beforeFirst();
                int i = 0;
                while (rs.next()) {
                    result[i][0] = rs.getString("idpegawai");
                    result[i][1] = rs.getString("namapegawai");
                    i++;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Pegawai.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public String[][] getListPegawai(String idPegawai) {
        String[][] result = null;
        try {
            String sql = "SELECT idpegawai, namapegawai FROM pegawai WHERE idpegawai = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, idPegawai);

            try (ResultSet rs = stmt.executeQuery()) {
                rs.last();
                int row = rs.getRow();
                result = new String[row][PEGAWAI_COLUMN_COUNT];

                rs.beforeFirst();
                int i = 0;
                while (rs.next()) {
                    result[i][0] = rs.getString("idpegawai");
                    result[i][1] = rs.getString("namapegawai");
                    i++;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Pegawai.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public boolean insertPegawai(String idPegawai, String namaPegawai, String password) {
        boolean result = false;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes("UTF-8"));
            byte[] digest = md.digest();
            String hash = DatatypeConverter.printHexBinary(digest).toLowerCase();

            String sql = "INSERT INTO pegawai(idpegawai, namapegawai, password) VALUES(?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, idPegawai);
            stmt.setString(2, namaPegawai);
            stmt.setString(3, hash);

            int affected = stmt.executeUpdate();
            if (affected > 0) {
                result = true;
            }
        } catch (SQLException | NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            Logger.getLogger(Pegawai.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public boolean updateNamaPegawai(String idPegawai, String namaPegawai) {
        boolean result = false;
        try {
            String sql = "UPDATE pegawai SET namapegawai = ? WHERE idpegawai = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, namaPegawai);
            stmt.setString(2, idPegawai);

            int affected = stmt.executeUpdate();
            if (affected > 0) {
                result = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Pegawai.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public boolean updatePasswordPegawai(String idPegawai, String password) {
        boolean result = false;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes("UTF-8"));
            byte[] digest = md.digest();
            String hash = DatatypeConverter.printHexBinary(digest).toLowerCase();

            String sql = "UPDATE pegawai SET password = ? WHERE idpegawai = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, hash);
            stmt.setString(2, idPegawai);

            int affected = stmt.executeUpdate();
            if (affected > 0) {
                result = true;
            }
        } catch (SQLException | NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            Logger.getLogger(Pegawai.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
