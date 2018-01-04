package com.fauzi.store;

import com.fauzi.store.form.Login;

/**
 *
 * @author adwiarifin
 */
public class Main {

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }
}
