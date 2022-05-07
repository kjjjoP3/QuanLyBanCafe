/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ACER NITRO 5
 */
public class ThongKeFr extends javax.swing.JFrame {
DefaultTableModel tblModel;
    Vector row, vecTK, vecSPBan;
    SimpleDateFormat ft = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
    NumberFormat formatter = new DecimalFormat("#,###");
    NumberFormat nft = new DecimalFormat("#,###");
    /**
     * Creates new form ThongKeFr
     */
    public ThongKeFr() {
        initComponents();
        
        tblModel = new DefaultTableModel();
        tblModel.addColumn("ID");
        tblModel.addColumn("Tên");
        tblModel.addColumn("Giá");
        tblModel.addColumn("Loại");
        tblModel.addColumn("Số lượng");
//        tblModel.addColumn("Thành tiền");
        tblThongKe.setModel(tblModel);
        loadBangSP();
        loadCBTK();
        btnPrint.setEnabled(false);
    }
    
    
    public void loadBangSP() {
        
        
        
        try {
            
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
        String connectionUrl = "jdbc:sqlserver://localhost;database=TheOne;";
        String dbusername = "sa";
        String password ="01219164372";
        Connection con = DriverManager.getConnection(connectionUrl,dbusername,password);
        
        
        PreparedStatement pstmt;
        ResultSet rs ;
        
        try {
            
            pstmt = con.prepareStatement("select * from OrderDetails join [Order] on OrderDetails.IDOrder=[Order].IDOrder"
                    + " join Product on OrderDetails.IDProduct=Product.IDProduct Order by OrderDetails.IDOrder DESC");
            rs = pstmt.executeQuery();
            int price, quantity, into;
            while (rs.next()) {
                row = new Vector();
                price = Integer.parseInt(rs.getString("Price"));
                quantity = Integer.parseInt(rs.getString("Quantity"));
                into = price * quantity;
                row.add(rs.getString("IDOrder"));
                row.add(rs.getString("ProductName"));
                row.add(rs.getString("Price"));
                row.add(rs.getString("IDType"));
                row.add(rs.getString("Quantity"));
                tblModel.addRow(row);
            }
            tblThongKe.setModel(tblModel);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi 101:: Không thể kết nối đến máy chủ");
        }
        
        con.close();
        
        
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            e.printStackTrace();
        }
        
    }

    public void loadCBTK() {
        
        
        
        try {
            
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
        String connectionUrl = "jdbc:sqlserver://localhost;database=TheOne;";
        String dbusername = "sa";
        String password ="01219164372";
        Connection con = DriverManager.getConnection(connectionUrl,dbusername,password);
        
        
        PreparedStatement pstmt;
        ResultSet rs ;
        
        cbTimKiemTK.removeAllItems();
        try {
            String url = "Select DISTINCT ProductName from Product";
            pstmt = con.prepareStatement(url);
            rs = pstmt.executeQuery();
            vecTK = new Vector();
            while (rs.next()) {//tim kiem
                vecTK.add(rs.getString("ProductName"));//tim kiem
            }
            JTextField text = (JTextField) cbTimKiemTK.getEditor().getEditorComponent();//tim kiem
            text.setText("");
            text.addKeyListener(new ComboListener(cbTimKiemTK, vecTK));//tim kiem
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi 101:: Không thể kết nối đến máy chủ");
        }
        
        
        con.close();
        
        
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtGia = new javax.swing.JTextField();
        txtSoLuong = new javax.swing.JTextField();
        cbTimKiemTK = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnPrint = new javax.swing.JButton();
        btnTimKiemTK = new javax.swing.JButton();
        txtStart = new com.toedter.calendar.JDateChooser();
        txtdate1 = new com.toedter.calendar.JDateChooser();
        btnNgay = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        SPBan = new javax.swing.JButton();
        lbSPBan = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblThongKe = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Sản phẩm đã bán:");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Tổng số lượng:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Tổng giá:");

        txtGia.setDisabledTextColor(new java.awt.Color(255, 0, 0));
        txtGia.setEnabled(false);

        txtSoLuong.setDisabledTextColor(new java.awt.Color(255, 0, 0));
        txtSoLuong.setEnabled(false);

        cbTimKiemTK.setEditable(true);
        cbTimKiemTK.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 0, 0));
        jLabel5.setText("Ly");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 0, 0));
        jLabel6.setText("VNĐ");

        btnPrint.setText("In");
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });

        btnTimKiemTK.setText("Tìm Kiếm");
        btnTimKiemTK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemTKActionPerformed(evt);
            }
        });

        txtStart.setDateFormatString("dd/MM/yyyy");

        txtdate1.setDateFormatString("dd/MM/yyyy");

        btnNgay.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnNgay.setForeground(new java.awt.Color(255, 0, 204));
        btnNgay.setText("Thống kê theo ngày");
        btnNgay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNgayActionPerformed(evt);
            }
        });

        btnReset.setText("Làm mới");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        SPBan.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        SPBan.setForeground(new java.awt.Color(255, 0, 255));
        SPBan.setText("Sản phẩm bán nhiều nhất");
        SPBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SPBanActionPerformed(evt);
            }
        });

        tblThongKe.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblThongKe);

        jLabel4.setText("Ngày Bắt Đầu:");

        jLabel7.setText("Ngày Kết Thúc:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(83, 83, 83)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtSoLuong, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel6)))
                                    .addComponent(jLabel1))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(49, 49, 49)
                                        .addComponent(btnReset))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(89, 89, 89)
                                        .addComponent(btnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(247, 247, 247)
                                        .addComponent(lbSPBan, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(5, 5, 5)
                                .addComponent(cbTimKiemTK, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnTimKiemTK)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtdate1, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtStart, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(btnNgay)))
                            .addComponent(SPBan, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(0, 176, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cbTimKiemTK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnTimKiemTK)))
                        .addGap(11, 11, 11))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(txtStart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel7)
                                .addComponent(txtdate1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel2)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(4, 4, 4)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel6))))
                                        .addGap(22, 22, 22)
                                        .addComponent(SPBan))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(17, 17, 17)
                                        .addComponent(lbSPBan, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(btnNgay))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnReset)
                        .addGap(27, 27, 27)
                        .addComponent(btnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(65, 65, 65)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        // TODO add your handling code here:
        
        
        
        try {
            
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
        String connectionUrl = "jdbc:sqlserver://localhost;database=TheOne;";
        String dbusername = "sa";
        String password ="01219164372";
        Connection con = DriverManager.getConnection(connectionUrl,dbusername,password);
        
        
        PreparedStatement pstmt;
        ResultSet rs ;
        
        
        //Viết vào file txt
        try {
            Date now = new Date();
            Writer b = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("ThongKe.txt"), "UTF8"));
            b.write("\t\t\t\t\t\tTHE GARDEN COFFEE\r\n\r\n");
            b.write("\t\t\t\t\tĐịa chỉ: 590 CMT8, P.11, Q.3, TPHCM\r\n");
            b.write("\t\t\t\t\t\tSĐT: 01212692802\r\n");
            b.write("\t\t\t\t\tThời gian: " + time.format(now) + "\r\n\r\n");
            b.write("\t\t\t\t\tBẢNG THỐNG KÊ SẢN PHẨM " + ft.format(now) + "\r\n");
            //            b.write("Tài khoản: " + cbTimKiemTK.getSelectedItem() + "\r\n");
            try {
                pstmt = con.prepareStatement("Select DISTINCT ProductName from Product");
                //                ps = con.prepareStatement("select * from OrderDetails join [Order] on OrderDetails.IDOrder=[Order].IDOrder"
                    //                    + " join Product on OrderDetails.IDProduct=Product.IDProduct Order by OrderDetails.IDOrder DESC");
                pstmt.setString(1, (String) cbTimKiemTK.getSelectedItem());
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    b.write("Tên sản phẩm: " + rs.getString("ProductName") + "\r\n\r\n");
                }
            } catch (SQLException ex) {
            }
            b.write("--------------------------------------------------------------------------------------------------------------------------------\r\n");
            b.write("\t\t\t\tMã HĐ\tTên SP\t\t\t\tĐơn giá (VNĐ)\tLoại SP\t\tSố lượng (ly)\r\n");
            b.write("--------------------------------------------------------------------------------------------------------------------------------\r\n");

            int line = tblThongKe.getRowCount();
            for (int i = 0; i < line; i++) {
                String s1 = (String) tblThongKe.getValueAt(i, 0);
                String s2 = (String) tblThongKe.getValueAt(i, 1);
                String s3 = (String) tblThongKe.getValueAt(i, 2);
                String s4 = (String) tblThongKe.getValueAt(i, 3);
                String s5 = (String) tblThongKe.getValueAt(i, 4);
                b.write("\t\t\t\t" + s1 + "\t" + s2 + "\t\t\t" + s3 + "\t\t" + s4 + "\t\t" + s5 + "\t" + "\r\n");
            }
            b.write("--------------------------------------------------------------------------------------------------------------------------------\r\n");
            b.write("\t\t\t\t\t\t\t\t\t\t\t\tTổng số lượng: " + txtSoLuong.getText() + "\r\n\r\n");
            b.write("\t\t\t\t\t\t\t\t\t\t\t\tTổng tiền: " + txtGia.getText() + "\tVNĐ");
            b.close();
        } catch (IOException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        //Mở file txt
        Runtime run = Runtime.getRuntime();
        try {
            run.exec("notepad ThongKe.txt");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        btnResetActionPerformed(evt);
        
        con.close();
        
        
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnPrintActionPerformed

    private void btnTimKiemTKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemTKActionPerformed
        // TODO add your handling code here
        
        
        try {
            
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
        String connectionUrl = "jdbc:sqlserver://localhost;database=TheOne;";
        String dbusername = "sa";
        String password ="01219164372";
        Connection con = DriverManager.getConnection(connectionUrl,dbusername,password);
        
        
        PreparedStatement pstmt ;
        ResultSet rs ;
        
        tblModel.getDataVector().removeAllElements();
        String s1 = (String) cbTimKiemTK.getSelectedItem();
        if (cbTimKiemTK.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(null, "Tên sản phẩm không được để trống.");
            btnPrint.setEnabled(false);
        } else {
            try {

                pstmt = con.prepareStatement("select * from OrderDetails join [Order] on OrderDetails.IDOrder=[Order].IDOrder"
                    + " join Product on OrderDetails.IDProduct=Product.IDProduct where ProductName=?");
                pstmt.setString(1, (String) cbTimKiemTK.getSelectedItem());
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    pstmt = con.prepareStatement("select * from OrderDetails join [Order] on OrderDetails.IDOrder=[Order].IDOrder"
                        + " join Product on OrderDetails.IDProduct=Product.IDProduct where ProductName=?");
                    pstmt.setString(1, (String) cbTimKiemTK.getSelectedItem());
                    rs = pstmt.executeQuery();
                    while (rs.next()) {
                        vecTK = new Vector();
                        vecTK.add(rs.getString("IDOrder"));
                        vecTK.add(rs.getString("ProductName"));
                        vecTK.add(rs.getString("Price"));
                        vecTK.add(rs.getString("IDType"));
                        vecTK.add(rs.getString("Quantity"));
                        tblModel.addRow(vecTK);
                    }
                    tblThongKe.setModel(tblModel);
                } else {
                    JOptionPane.showMessageDialog(null, "Không tìm được sản phẩm");
                    loadBangSP();
                }
            } catch (Exception e) {
            }
            int line = tblThongKe.getRowCount();
            int tong = 0, tinhtien = 0;
            for (int i = 0; i < line; i++) {
                //            if (tblThongKe.getValueAt(i, 1).equals(cbTimKiemTK.getSelectedItem())) {
                    int SL = Integer.parseInt((String) tblThongKe.getValueAt(i, 4));
                    int tien = Integer.parseInt((String) tblThongKe.getValueAt(i, 2));
                    tong += SL;
                    tinhtien += tien;
                    //                spQuantity.setValue(quanTotal);
                    //            }
            }
            txtGia.setText(String.valueOf(nft.format(tinhtien)));
            txtSoLuong.setText(String.valueOf(tong));
            //        loadBangSP();
            cbTimKiemTK.setSelectedIndex(-1);
        }

        btnPrint.setEnabled(true);
        
        con.close();
        
        
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnTimKiemTKActionPerformed

    private void btnNgayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNgayActionPerformed
        // TODO add your handling code here:
        
        
        
        try {
            
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
        String connectionUrl = "jdbc:sqlserver://localhost;database=TheOne;";
        String dbusername = "sa";
        String password ="01219164372";
        Connection con = DriverManager.getConnection(connectionUrl,dbusername,password);
        
        PreparedStatement pstmt ;
        ResultSet rs ;
        
        SimpleDateFormat ft = new SimpleDateFormat("yyyy/MM/dd");
        String start = ft.format(txtStart.getDate());
        String end = ft.format(txtdate1.getDate());
        while (true) {
            if (start.compareTo(end) >= 0) {
                JOptionPane.showMessageDialog(null, "Thời gian bắt đầu phải nhỏ hơn thời gian kết thúc.");
                return;
            } else {
                break;
            }
        }
        tblThongKe.removeAll();
        DefaultTableModel tblMD = new DefaultTableModel();
        tblMD.addColumn("ID");
        tblMD.addColumn("Tên");
        tblMD.addColumn("Giá");
        tblMD.addColumn("Loại");
        tblMD.addColumn("Ngày");
        tblThongKe.setModel(tblMD);
        try {
            pstmt = con.prepareStatement("select o.IDProduct, p.Price,p.ProductName,p.IDType,a.DateOrder"
                + " from OrderDetails o join Product p on o.IDProduct=p.IDProduct"
                + "	join [Order] a on a.IDOrder=o.IDOrder"
                + " where convert(varchar, TRY_PARSE(DateOrder as date using 'vi-vn'), 111) >= '" + start + "'"
                + "  and convert(varchar, TRY_PARSE(DateOrder as date using 'vi-vn'), 111) <= '" + end + "' order by convert(varchar, TRY_PARSE(DateOrder as date using 'vi-vn'), 111) DESC");
            rs = pstmt.executeQuery();
            if (rs.next()) {
                pstmt = con.prepareStatement("select o.IDProduct, p.Price,p.ProductName,p.IDType,a.DateOrder"
                    + " from OrderDetails o join Product p on o.IDProduct=p.IDProduct"
                    + "	join [Order] a on a.IDOrder=o.IDOrder"
                    + " where convert(varchar, TRY_PARSE(DateOrder as date using 'vi-vn'), 111) >= '" + start + "'"
                    + "  and convert(varchar, TRY_PARSE(DateOrder as date using 'vi-vn'), 111) <= '" + end + "' order by convert(varchar, TRY_PARSE(DateOrder as date using 'vi-vn'), 111) DESC");
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    vecSPBan = new Vector();
                    vecSPBan.add(rs.getString("IDProduct"));
                    vecSPBan.add(rs.getString("ProductName"));
                    vecSPBan.add(rs.getString("Price"));
                    vecSPBan.add(rs.getString("IDType"));
                    //                    vecSPBan.add(rsSPBan.getString("Tong"));
                    vecSPBan.add(rs.getString("DateOrder"));
                    tblMD.addRow(vecSPBan);
                }
                tblThongKe.setModel(tblMD);
            } else {

            }
        } catch (Exception e) {
        }
        
        txtGia.setText("");
        txtSoLuong.setText("");
        
        con.close();
        
        
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Vui Lòng Nhập Ngày Bắt Đầu Và Ngày Kết Thúc");
            e.printStackTrace();
        }
        //        btnPrint.setEnabled(true);
    }//GEN-LAST:event_btnNgayActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        // TODO add your handling code here:
        txtSoLuong.setText("");
        txtGia.setText("");
        cbTimKiemTK.setSelectedIndex(-1);
        tblModel.getDataVector().removeAllElements();
        loadBangSP();
        btnPrint.setEnabled(false);
        txtStart.setCalendar(null);
        txtdate1.setCalendar(null);
    }//GEN-LAST:event_btnResetActionPerformed

    private void SPBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SPBanActionPerformed
        // TODO add your handling code here:

        
        
        try {
            
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
        String connectionUrl = "jdbc:sqlserver://localhost;database=TheOne;";
        String dbusername = "sa";
        String password ="01219164372";
        Connection con = DriverManager.getConnection(connectionUrl,dbusername,password);
       
        PreparedStatement pstmt ;
        ResultSet rs ;
        
        tblModel.getDataVector().removeAllElements();
        try {

            pstmt = con.prepareStatement("select o.IDProduct, p.Price,p.ProductName,p.IDType, SUM(o.Quantity) as Tong"
                + " from OrderDetails o join Product p on o.IDProduct=p.IDProduct Group by o.IDProduct,p.Price,p.ProductName,p.IDType order by Tong DESC");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                vecSPBan = new Vector();
                vecSPBan.add(rs.getString("IDProduct"));
                vecSPBan.add(rs.getString("ProductName"));
                vecSPBan.add(rs.getString("Price"));
                vecSPBan.add(rs.getString("IDType"));
                vecSPBan.add(rs.getString("Tong"));
                tblModel.addRow(vecSPBan);
            }
            tblThongKe.setModel(tblModel);
        } catch (Exception e) {
        }
//        btnPrint.setEnabled(true);
        
        con.close();
        
        
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_SPBanActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ThongKeFr.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ThongKeFr.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ThongKeFr.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ThongKeFr.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ThongKeFr().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton SPBan;
    private javax.swing.JButton btnNgay;
    private javax.swing.JButton btnPrint;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnTimKiemTK;
    private javax.swing.JComboBox cbTimKiemTK;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbSPBan;
    private javax.swing.JTable tblThongKe;
    private javax.swing.JTextField txtGia;
    private javax.swing.JTextField txtSoLuong;
    private com.toedter.calendar.JDateChooser txtStart;
    private com.toedter.calendar.JDateChooser txtdate1;
    // End of variables declaration//GEN-END:variables
}
