package com.fauzi.store;

import com.fauzi.store.form.Login;
import com.fauzi.store.form.Penjualan;
import com.fauzi.store.form.Restock;
import com.fauzi.store.form.Stock;
import com.fauzi.store.model.Barang;
import com.fauzi.store.model.Pegawai;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author adwiarifin
 */
public class Main {

    private final Login mLogin;
    private final Stock mStock;
    private final Restock mRestock;
    private final Penjualan mPenjualan;

    private final Connection conn;
    private final Pegawai mPegawai;
    private final Barang mBarang;

    private String activeUser;

    public Main() {
        conn = getConnection();

        mPegawai = new Pegawai(conn);
        mBarang = new Barang(conn);

        mLogin = new Login(this, mPegawai);
        mStock = new Stock(this, mBarang);
        mRestock = new Restock(this, mBarang);
        mPenjualan = new Penjualan();

        activeUser = "";
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            Main main = new Main();
//            main.showLogin();
            main.showRestock();
        });
    }

    private Connection getConnection() {
        String server = "localhost";
        String database = "tokoanu";
        String username = "root";
        String password = "";
        String host = "jdbc:mysql://" + server + "/" + database;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection koneksi = DriverManager.getConnection(host, username, password);
            return koneksi;
        } catch (ClassNotFoundException | SQLException ex) {
            // show error
            JOptionPane.showMessageDialog(null, "Tidak dapat menyambung ke database", "Koneksi gagal", JOptionPane.ERROR_MESSAGE);
        }

        return null;
    }

    public void showLogin() {
        mLogin.setVisible(true);
    }

    public void hideLogin() {
        mLogin.setVisible(false);
    }

    public void showStock() {
        mStock.setVisible(true);
    }

    public void hideStock() {
        mStock.setVisible(false);
    }

    public void showRestock() {
        mRestock.setVisible(true);
    }

    public void hideRestock() {
        mRestock.setVisible(false);
    }

    public void showPenjualan() {
        mPenjualan.setVisible(true);
    }

    public void hidePenjualan() {
        mPenjualan.setVisible(false);
    }

    public void setActiveUser(String userId) {
        activeUser = userId;
    }

    public String getActiveUser() {
        return activeUser;
    }

    public void exit() {
        System.exit(0);
    }
}
