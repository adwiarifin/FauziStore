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
}
