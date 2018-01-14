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
            
            try(ResultSet rs = stmt.executeQuery()) {
                rs.last();
                result = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Transaksi.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
}
