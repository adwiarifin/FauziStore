package com.fauzi.store.form;

import com.fauzi.store.Main;
import com.fauzi.store.model.Barang;
import com.fauzi.store.model.Diskon;
import com.fauzi.store.model.Pegawai;
import com.fauzi.store.model.Transaksi;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author fauzi
 */
public class Penjualan extends javax.swing.JFrame {

    private final Main main;
    private final Barang barang;
    private final Diskon diskon;
    private final Pegawai pegawai;
    private final Transaksi transaksi;
    private DefaultTableModel mTransaksi;

    /**
     * Creates new form Penjualan
     *
     * @param objMain
     */
    public Penjualan(Main objMain) {
        this.main = objMain;
        this.barang = objMain.getModelBarang();
        this.diskon = objMain.getModelDiskon();
        this.pegawai = objMain.getModelPegawai();
        this.transaksi = objMain.getModelTransaksi();

        initComponents();
        initDocumentListener();
        initTransaksiModel();
    }

    public void initData() {
        // method bikin sendiri
        loadNamaPegawai();
        loadToday();
        setDefaultJumlahBeli();
        generateNota();
        loadBarang("");
    }

    private void initDocumentListener() {
        tfSearch.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                loadBarang(tfSearch.getText().toLowerCase().trim());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                loadBarang(tfSearch.getText().toLowerCase().trim());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                loadBarang(tfSearch.getText().toLowerCase().trim());
            }
        });

        tfTotalBayar.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                calcRetur();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                calcRetur();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                calcRetur();
            }
        });
    }

    private void initTransaksiModel() {
        String[] columns = new String[]{"ID Barang", "Nama Barang", "Harga", "Total Beli", "Diskon", "Subtotal"};
        mTransaksi = new DefaultTableModel(null, columns);
        tbTransaksi.setModel(mTransaksi);
    }

    private void loadNamaPegawai() {
        String user = main.getActiveUser();
        String nama = pegawai.getNamaPegawai(user);
        lbNamaKasir.setText(nama);
    }

    private void loadToday() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date now = new Date();
        String text = sdf.format(now);
        lbDateToday.setText(text);
    }

    private void setDefaultJumlahBeli() {
        spJumlahBeli.setValue(1);
    }

    private void generateNota() {
        int transaksiCount = transaksi.getTransaksiCount();
        SimpleDateFormat sdf = new SimpleDateFormat("yyMM");
        Date now = new Date();

        // Format Nota : YYMM####
        String nota = sdf.format(now) + String.format("%04d", transaksiCount + 1);

        // set it to label
        lbNota.setText(nota);
    }

    private void loadBarang(String keyword) {
        DefaultTableModel modelBarang = new DefaultTableModel(null, Barang.BARANG_COLUMN_TITLE);
        modelBarang.setRowCount(0);

        String[][] listBarang = keyword.isEmpty() ? barang.getListBarang() : barang.getListBarang(keyword);
        for (String[] data : listBarang) {
            modelBarang.addRow(data);
        }

        tbBarang.setModel(modelBarang);
    }

    private void calcGrandTotal() {
        int totalRow = tbTransaksi.getRowCount();
        int grandTotal = 0;
        int discountTotal = 0;
        for (int i = 0; i < totalRow; i++) {
            int subTotal = Integer.parseInt(tbTransaksi.getValueAt(i, 5).toString());
            int disc = Integer.parseInt(tbTransaksi.getValueAt(i, 4).toString());
            grandTotal += subTotal;
            discountTotal += disc;
        }
        lbGrandTotal.setText("" + grandTotal);
        lbDiscount.setText("" + discountTotal);
    }

    private void calcRetur() {
        try {
            String sBayar = !tfTotalBayar.getText().trim().isEmpty() ? tfTotalBayar.getText().trim() : "0";
            String sTotal = lbGrandTotal.getText().trim();
            int bayar = Integer.parseInt(sBayar);
            int total = Integer.parseInt(sTotal);
            int retur = bayar - total;
            lbKembali.setText("" + retur);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(rootPane, "Masukkan angka yang valid", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void clearInput() {
        tfSearch.setText("");
        loadBarang("");
        setDefaultJumlahBeli();
        mTransaksi.setRowCount(0);
        lbGrandTotal.setText("0");
        lbDiscount.setText("0");
        cbPembayaran.setSelectedIndex(0);
        tfTotalBayar.setText("0");
        lbKembali.setText("0");
        generateNota();
        loadToday();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        tfSearch = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbBarang = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lbNamaKasir = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lbDateToday = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lbNota = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbTransaksi = new javax.swing.JTable();
        btInput = new javax.swing.JButton();
        spJumlahBeli = new javax.swing.JSpinner();
        jPanel6 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        lbGrandTotal = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cbPembayaran = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        tfTotalBayar = new javax.swing.JTextField();
        lbKembali = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        lbDiscount = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        btSelesai = new javax.swing.JToggleButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Nama toko");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jLabel8.setText("cari barang");

        tbBarang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tbBarang);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(41, 41, 41)
                        .addComponent(tfSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(tfSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel2.setText("kasir");

        lbNamaKasir.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        lbNamaKasir.setText("nama kasir");

        jLabel4.setText("tanggal");

        lbDateToday.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        lbDateToday.setText("dd-mm-yyy");

        jLabel6.setText("nomor nota");

        lbNota.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        lbNota.setText("0");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(lbNamaKasir)
                .addGap(129, 129, 129)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(lbDateToday)
                .addGap(130, 130, 130)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(lbNota)
                .addContainerGap(255, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lbNamaKasir))
                .addContainerGap(17, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(lbDateToday)
                    .addComponent(jLabel6)
                    .addComponent(lbNota))
                .addContainerGap())
        );

        jLabel10.setText("jumlah");

        tbTransaksi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(tbTransaksi);

        btInput.setText("input");
        btInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btInputActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 876, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(37, 37, 37)
                        .addComponent(spJumlahBeli, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btInput)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(btInput)
                    .addComponent(spJumlahBeli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel11.setText("Total Tagihan");

        lbGrandTotal.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbGrandTotal.setText("0");

        jLabel9.setText("pembayaran");

        cbPembayaran.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cash", "Transfer", "Debit" }));

        jLabel13.setText("jumlah bayar");

        jLabel14.setText("kembali");

        tfTotalBayar.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tfTotalBayar.setText("0");

        lbKembali.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        lbKembali.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbKembali.setText("0");

        jButton3.setText("Edit Jumlah");

        jButton4.setText("Next Transaction");

        jLabel16.setText("Discount");

        lbDiscount.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        lbDiscount.setText("0");

        jButton2.setText("Hapus Transaksi");

        btSelesai.setText("Selesai");
        btSelesai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSelesaiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel16))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(lbDiscount)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel14))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(lbGrandTotal)
                                .addGap(188, 188, 188)
                                .addComponent(jLabel13)))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tfTotalBayar, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                            .addComponent(lbKembali, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(27, 27, 27)
                        .addComponent(cbPembayaran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 125, Short.MAX_VALUE)
                .addComponent(btSelesai)
                .addGap(70, 70, 70)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(lbGrandTotal)
                            .addComponent(jLabel13)
                            .addComponent(tfTotalBayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel14)
                                .addComponent(lbKembali)
                                .addComponent(jLabel16)
                                .addComponent(lbDiscount))
                            .addComponent(jButton3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton4)
                            .addComponent(cbPembayaran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)))
                    .addComponent(btSelesai))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jMenu1.setText("Form");

        jMenuItem4.setText("Discount");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);
        jMenu1.add(jSeparator1);

        jMenuItem1.setText("Stock");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem3.setText("Restock");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);
        jMenu1.add(jSeparator2);

        jMenuItem2.setText("Pegawai");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Akun");

        jMenuItem5.setText("Log-Out");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem5);

        jMenuItem6.setText("Keluar");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem6);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btInputActionPerformed
        // get selection
        int rowNumber = tbBarang.getSelectedRow();
        if (rowNumber > -1) {
            String idBarang = tbBarang.getValueAt(rowNumber, 0).toString();
            String namaBarang = tbBarang.getValueAt(rowNumber, 1).toString();
            String harga = tbBarang.getValueAt(rowNumber, 4).toString();
            int iHarga = Integer.parseInt(harga);
            int totalBeli = Integer.parseInt(spJumlahBeli.getValue().toString());
            int potongan = diskon.getActiveDiscountItem(idBarang);
            int subTotal = iHarga * totalBeli - potongan;
            String[] row = new String[]{
                idBarang,
                namaBarang,
                String.valueOf(iHarga),
                String.valueOf(totalBeli),
                String.valueOf(potongan),
                String.valueOf(subTotal)
            };
            mTransaksi.addRow(row);

            setDefaultJumlahBeli();
            calcGrandTotal();
            calcRetur();
        } else {
            // show warning
            JOptionPane.showMessageDialog(rootPane, "Pilih data dari tabel barang di atas terlebih dahulu!", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btInputActionPerformed

    private void btSelesaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSelesaiActionPerformed
        // make sure payment is sufficient
        int retur = Integer.parseInt(lbKembali.getText());
        if (retur < 0) {
            JOptionPane.showMessageDialog(rootPane, "Pastikan Jumlah Pembayaran Terpenuhi!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // populate transaksi input
        String nota = lbNota.getText();
        String idpegawai = main.getActiveUser();
        int total = Integer.parseInt(lbGrandTotal.getText());
        int idbayar = cbPembayaran.getSelectedIndex() + 1;

        // populate detiltransaksi input
        int rowCount = tbTransaksi.getRowCount();
        String[][] detilTransaksi = new String[rowCount][5];
        for (int i = 0; i < rowCount; i++) {
            // tbTransaksi : "ID Barang", "Nama Barang", "Harga", "Total Beli", "Diskon", "Subtotal"
            // detilTransaksi : idbarang, harga, totalbeli, diskon, subtotal
            detilTransaksi[i][0] = tbTransaksi.getValueAt(i, 0).toString();
            detilTransaksi[i][1] = tbTransaksi.getValueAt(i, 2).toString();
            detilTransaksi[i][2] = tbTransaksi.getValueAt(i, 3).toString();
            detilTransaksi[i][3] = tbTransaksi.getValueAt(i, 4).toString();
            detilTransaksi[i][4] = tbTransaksi.getValueAt(i, 5).toString();;
        }

        // insert to database
        if (transaksi.insertTransaksi(nota, idpegawai, total, idbayar, detilTransaksi)) {
            // informasikan jika insert berhasil
            JOptionPane.showMessageDialog(rootPane, "Data transaksi baru berhasil dimasukkan", "Info", JOptionPane.INFORMATION_MESSAGE);
            // siapkan untuk input baru
            clearInput();
            // set focus
            tfSearch.requestFocus();
        } else {
            // informasikan jika insert gagal
            JOptionPane.showMessageDialog(rootPane, "Data transaksi gagal dimasukkan", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btSelesaiActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        main.showDiscount();
        main.hidePenjualan();
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        main.showStock();
        main.hidePenjualan();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        main.showRestock();
        main.hidePenjualan();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        main.showPegawai();
        main.hidePenjualan();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
        main.showLogin();
        main.hidePenjualan();
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btInput;
    private javax.swing.JToggleButton btSelesai;
    private javax.swing.JComboBox<String> cbPembayaran;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JLabel lbDateToday;
    private javax.swing.JLabel lbDiscount;
    private javax.swing.JLabel lbGrandTotal;
    private javax.swing.JLabel lbKembali;
    private javax.swing.JLabel lbNamaKasir;
    private javax.swing.JLabel lbNota;
    private javax.swing.JSpinner spJumlahBeli;
    private javax.swing.JTable tbBarang;
    private javax.swing.JTable tbTransaksi;
    private javax.swing.JTextField tfSearch;
    private javax.swing.JTextField tfTotalBayar;
    // End of variables declaration//GEN-END:variables
}
