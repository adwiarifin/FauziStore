package com.fauzi.store;

import com.fauzi.store.form.Discount;
import com.fauzi.store.form.Login;
import com.fauzi.store.form.Penjualan;
import com.fauzi.store.form.Restock;
import com.fauzi.store.form.Stock;
import com.fauzi.store.model.Barang;
import com.fauzi.store.model.Diskon;
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

    private final Login fLogin;
    private final Stock fStock;
    private final Restock fRestock;
    private final Penjualan fPenjualan;
    private final Discount fDiscount;

    private final Connection conn;
    private final Pegawai mPegawai;
    private final Barang mBarang;
    private final Diskon mDiskon;

    private String activeUser;

    public Main() {
        conn = getConnection();

        mPegawai = new Pegawai(conn);
        mBarang = new Barang(conn);
        mDiskon = new Diskon(conn);

        fLogin = new Login(this);
        fStock = new Stock(this);
        fRestock = new Restock(this);
        fDiscount = new Discount(this);
        fPenjualan = new Penjualan();

        activeUser = "";
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            Main main = new Main();
//            main.showLogin();
            main.showDiscount();
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
        fLogin.setVisible(true);
    }

    public void hideLogin() {
        fLogin.setVisible(false);
    }

    public void showStock() {
        fStock.setVisible(true);
    }

    public void hideStock() {
        fStock.setVisible(false);
    }

    public void showRestock() {
        fRestock.setVisible(true);
    }

    public void hideRestock() {
        fRestock.setVisible(false);
    }
    
    public void showDiscount() {
        fDiscount.setVisible(true);
    }

    public void hideDiscount() {
        fDiscount.setVisible(false);
    }

    public void showPenjualan() {
        fPenjualan.setVisible(true);
    }

    public void hidePenjualan() {
        fPenjualan.setVisible(false);
    }
    
    public Barang getModelBarang(){
        return mBarang;
    }
    
    public Pegawai getModelPegawai(){
        return mPegawai;
    }
    
    public Diskon getModelDiskon() {
        return mDiskon;
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
