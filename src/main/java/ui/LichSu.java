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
public class LichSu extends javax.swing.JFrame {
Vector vecKhongCTKM, vecKHV, vecCTKM, vecEmp;
DefaultTableModel tblModel = new DefaultTableModel() {

        @Override
        public boolean isCellEditable(int row, int column) {
            return false; //To change body of generated methods, choose Tools | Templates.
        }

    };
SimpleDateFormat ft = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
    NumberFormat formatter = new DecimalFormat("#,###");

    /**
     * Creates new form LichSU
     */
    public LichSu() {
        initComponents();
        
        
        
        try {
            
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
        String connectionUrl = "jdbc:sqlserver://localhost;database=TheOne;";
        String dbusername = "sa";
        String password ="01219164372";
        Connection con = DriverManager.getConnection(connectionUrl,dbusername,password);
        
        
        PreparedStatement pstmt ;
        ResultSet rs;
        
        tblModel.addColumn("Mã đơn hàng");
        tblModel.addColumn("Mã sản phẩm");
        tblModel.addColumn("Số lượng (Ly)");
        tblModel.addColumn("Đơn giá (VNĐ)");
        tblModel.addColumn("Tên CTKM");
        tblModel.addColumn("Mã khách hàng");
        tblModel.addColumn("Chiết khấu (%)");
        tblModel.addColumn("Thời gian");
        tblModel.addColumn("Ngày");
        tblModel.addColumn("TK nhân viên");
        tblModel.addColumn("Thành tiền (VNĐ)");
        tblHistory.setModel(tblModel);
        try {
            String url = "Select UsernameEmp from Employee";
            pstmt = con.prepareStatement(url);
            rs = pstmt.executeQuery();
            vecEmp = new Vector();
            while (rs.next()) {
                vecEmp.add(rs.getString("UsernameEmp"));
            }
            JTextField text = (JTextField) cbEmp.getEditor().getEditorComponent();
            text.setText("");
            text.addKeyListener(new ComboListener(cbEmp, vecEmp));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi 101:: Không thể kết nối đến máy chủ");
        }
        ReloadTbl();
        LoadlbTotal();
        
        
        con.close();
        
        
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            e.printStackTrace();
        }
        
    }
    
    public void LoadlbTotal() {
        int total = 0;
        int line = tblHistory.getRowCount();
        for (int i = 0; i < line; i++) {
            String ThanhTien = (String) tblHistory.getValueAt(i, 10);
            total += Integer.parseInt(ThanhTien.replaceAll(",", ""));
        }
        lbTotal.setText(formatter.format(total) + " VNĐ");
    }

    public void ReloadTbl() {
        
        
        
        try {
            
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
        String connectionUrl = "jdbc:sqlserver://localhost;database=TheOne;";
        String dbusername = "sa";
        String password ="01219164372";
        Connection con = DriverManager.getConnection(connectionUrl,dbusername,password);
        
        
        PreparedStatement pstmt  ;
        ResultSet rs ;
        
        tblModel.getDataVector().removeAllElements();
        //select theo khách hàng VIP
        try {
            pstmt = con.prepareStatement("select * from OrderDetails  join [Order] on OrderDetails.IDOrder=[Order].IDOrder "
                    + "join Product on OrderDetails.IDProduct=Product.IDProduct "
                    + "join Customer on OrderDetails.CusName=Customer.IDCus  "
                    + "where Orderdetails.CusName != 'Khách vãng lai'");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                vecKHV = new Vector();
                vecKHV.add(rs.getString("IDOrder"));
                vecKHV.add(rs.getString("IDProduct"));
                vecKHV.add(rs.getString("Quantity"));
                vecKHV.add(formatter.format(rs.getInt("Price")));
                vecKHV.add(rs.getString("NamePromo"));
                vecKHV.add(rs.getString("CusName"));
                vecKHV.add(rs.getString("Discount"));
                vecKHV.add(rs.getString("TimeOrder"));
                vecKHV.add(rs.getString("DateOrder"));
                vecKHV.add(rs.getString("UsernameEmp"));
                int quanKHV = rs.getInt("Quantity");
                int priceKHV = rs.getInt("Price");
                int discountKHV = rs.getInt("Discount");
                int dismoneyKHV = (quanKHV * priceKHV * discountKHV) / 100;
                int totalKHV = (priceKHV * quanKHV) - dismoneyKHV;
                vecKHV.add(formatter.format(totalKHV));
                tblModel.addRow(vecKHV);
            }
            tblHistory.setModel(tblModel);
        } catch (Exception e) {
        }
        //select theo CTKM
        try {
            pstmt = con.prepareStatement("select * from OrderDetails join [Order] on OrderDetails.IDOrder=[Order].IDOrder "
                    + "join Product on OrderDetails.IDProduct=Product.IDProduct "
                    + "join Promotions on OrderDetails.NamePromo=Promotions.NamePromo");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                vecCTKM = new Vector();
                vecCTKM.add(rs.getString("IDOrder"));
                vecCTKM.add(rs.getString("IDProduct"));
                vecCTKM.add(rs.getString("Quantity"));
                vecCTKM.add(formatter.format(rs.getInt("Price")));
                vecCTKM.add(rs.getString("NamePromo"));
                vecCTKM.add(rs.getString("CusName"));
                vecCTKM.add(rs.getString("DiscountPromo"));
                vecCTKM.add(rs.getString("TimeOrder"));
                vecCTKM.add(rs.getString("DateOrder"));
                vecCTKM.add(rs.getString("UsernameEmp"));
                int quanCTKM = rs.getInt("Quantity");
                int priceCTKM = rs.getInt("Price");
                int discountCTKM = rs.getInt("DiscountPromo");
                int dismoneyCTKM = (quanCTKM * priceCTKM * discountCTKM) / 100;
                int totalCTKM = (priceCTKM * quanCTKM) - dismoneyCTKM;
                vecCTKM.add(formatter.format(totalCTKM));
                tblModel.addRow(vecCTKM);
            }
            tblHistory.setModel(tblModel);
        } catch (SQLException ex) {
        }
        //select theo không áp dụng CTKM
        try {
            pstmt = con.prepareStatement("select * from OrderDetails join [Order] on OrderDetails.IDOrder=[Order].IDOrder "
                    + "join Product on OrderDetails.IDProduct=Product.IDProduct "
                    + "where NamePromo='Không có'");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                vecKhongCTKM = new Vector();
                vecKhongCTKM.add(rs.getString("IDOrder"));
                vecKhongCTKM.add(rs.getString("IDProduct"));
                vecKhongCTKM.add(rs.getString("Quantity"));
                vecKhongCTKM.add(formatter.format(rs.getInt("Price")));
                vecKhongCTKM.add(rs.getString("NamePromo"));
                vecKhongCTKM.add(rs.getString("CusName"));
                vecKhongCTKM.add("0");
                vecKhongCTKM.add(rs.getString("TimeOrder"));
                vecKhongCTKM.add(rs.getString("DateOrder"));
                vecKhongCTKM.add(rs.getString("UsernameEmp"));
                int quanKhongCTKM = rs.getInt("Quantity");
                int priceKhongCTKM = rs.getInt("Price");
                int totalKhongCTKM = priceKhongCTKM * quanKhongCTKM;
                vecKhongCTKM.add(formatter.format(totalKhongCTKM));
                tblModel.addRow(vecKhongCTKM);
            }
            tblHistory.setModel(tblModel);
        } catch (SQLException ex) {
        }
        
        
        
        
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

        btnPrint = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHistory = new javax.swing.JTable();
        btnReset = new javax.swing.JButton();
        btnSearch = new javax.swing.JButton();
        txtdate = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        lbTotal = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cbEmp = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });

        tblHistory.setModel(new javax.swing.table.DefaultTableModel(
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
        tblHistory.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblHistory);

        btnReset.setText("Làm mới");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        btnSearch.setText("Tìm kiếm");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        txtdate.setDateFormatString("dd/MM/yyyy");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Ngày lập đơn hàng:");

        lbTotal.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbTotal.setForeground(new java.awt.Color(255, 0, 0));
        lbTotal.setText("0 VNĐ");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 0, 0));
        jLabel3.setText("Tổng số tiền:");

        cbEmp.setEditable(true);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("TK nhân viên:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbTotal))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtdate, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSearch)
                        .addGap(18, 18, 18)
                        .addComponent(btnReset)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2)
                                    .addComponent(cbEmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(txtdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnSearch)
                                .addComponent(btnReset)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(lbTotal))
                        .addGap(0, 2, Short.MAX_VALUE))
                    .addComponent(btnPrint, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        //xuat file txt

        //
        try {
            
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
        String connectionUrl = "jdbc:sqlserver://localhost;database=TheOne;";
        String dbusername = "sa";
        String password ="01219164372";
        Connection con = DriverManager.getConnection(connectionUrl,dbusername,password);
        
        
        PreparedStatement pstmt ;
        ResultSet rs ;
        
            File file = new File("History.txt");
        file.delete();
        //Viết vào file txt
        try {
            Date now = new Date();
            Writer b = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("History.txt"), "UTF8"));
            b.write("THE GARDEN COFFEE\r\n\r\n");
            b.write("Địa chỉ: 590 CMT8, P.11, Q.3, TPHCM\r\n");
            b.write("SĐT: 01212692802\r\n");
            b.write("Thời gian: " + time.format(now) + "\r\n\r\n");
            b.write("\t\t\t\t\tBẢNG THỐNG KÊ BÁN HÀNG NGÀY " + ft.format(txtdate.getDate()) + "\r\n");
            b.write("Tài khoản: " + cbEmp.getSelectedItem() + "\r\n");
            try {
                pstmt = con.prepareStatement("Select * from Employee where UsernameEmp=?");
                pstmt.setString(1, (String) cbEmp.getSelectedItem());
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    b.write("Tên nhân viên: " + rs.getString("NameEmp") + "\r\n\r\n");
                }
            } catch (SQLException ex) {
            }
            b.write("--------------------------------------------------------------------------------------------------------------------------------\r\n");
            b.write("Mã ĐH\tMã SP\tSố lượng (ly)\tĐơn giá (VNĐ)\tTên CTKM\tMã khách hàng\tChiết khấu (%)\tThời gian\tThành tiền (VNĐ)\r\n");
            b.write("--------------------------------------------------------------------------------------------------------------------------------\r\n");

            int line = tblHistory.getRowCount();
            for (int i = 0; i < line; i++) {
                String s1 = (String) tblHistory.getValueAt(i, 0);
                String s2 = (String) tblHistory.getValueAt(i, 1);
                String s3 = (String) tblHistory.getValueAt(i, 2);
                String s4 = (String) tblHistory.getValueAt(i, 3);
                String s5 = (String) tblHistory.getValueAt(i, 4);
                String MKH = (String) tblHistory.getValueAt(i, 5);
                String s6;
                if (!MKH.equals("Khách vãng lai")) {
                    s6 = (String) tblHistory.getValueAt(i, 5) + "\t";
                } else {
                    s6 = (String) tblHistory.getValueAt(i, 5);
                }
                String s7 = (String) tblHistory.getValueAt(i, 6);
                String s8 = (String) tblHistory.getValueAt(i, 7);
                String s11 = (String) tblHistory.getValueAt(i, 10);
                b.write(s1 + "\t" + s2 + "\t" + s3 + "\t\t" + s4 + "\t\t" + s5 + "\t" + s6 + "\t" + s7 + "\t\t" + s8 + "\t" + s11 + "\r\n");
            }
            b.write("--------------------------------------------------------------------------------------------------------------------------------\r\n");
            b.write("Tổng tiền: " + lbTotal.getText());
            b.close();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        //Mở file txt
        Runtime run = Runtime.getRuntime();
        try {
            run.exec("notepad History.txt");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
        
        con.close();
        
        
        } catch (Exception e) {
//            JOptionPane.showMessageDialog(this, e.getMessage());
        JOptionPane.showMessageDialog(null, "Tìm Kiếm 1 Nhân Viên Rồi Mới Được Xuất");
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnPrintActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        cbEmp.setSelectedIndex(-1);
        ReloadTbl();
        LoadlbTotal();
        btnPrint.setEnabled(false);
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
                
        try {
            
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
        String connectionUrl = "jdbc:sqlserver://localhost;database=TheOne;";
        String dbusername = "sa";
        String password ="01219164372";
        Connection con = DriverManager.getConnection(connectionUrl,dbusername,password);
        
        
        PreparedStatement pstmt;
        ResultSet rs ;
        
        String date = ft.format(txtdate.getDate());
        String name = (String) cbEmp.getSelectedItem();
        if (cbEmp.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(null, "Tài khoản nhân viên không được để trống.");
            btnPrint.setEnabled(false);
        } else {
            try {
                pstmt = con.prepareStatement("select * from OrderDetails  join [Order] on OrderDetails.IDOrder=[Order].IDOrder "
                    + "where UsernameEmp ='" + name + "' and DateOrder = '" + date + "'");
                rs = pstmt.executeQuery();
                if (!rs.next()) {
                    JOptionPane.showMessageDialog(null, "Nhân viên " + name + " chưa bán được sản phẩm nào trong ngày " + date + ".");
                    btnResetActionPerformed(evt);
                } else {
                    btnPrint.setEnabled(true);
                    tblModel.getDataVector().removeAllElements();
                    //select theo khách hàng VIP
                    try {
                        pstmt = con.prepareStatement("select * from OrderDetails  join [Order] on OrderDetails.IDOrder=[Order].IDOrder "
                            + "join Product on OrderDetails.IDProduct=Product.IDProduct "
                            + "join Customer on OrderDetails.CusName=Customer.IDCus  "
                            + "where Orderdetails.CusName != 'Khách vãng lai' and UsernameEmp ='" + name + "' and DateOrder = '" + date + "'");
                        rs = pstmt.executeQuery();
                        while (rs.next()) {
                            vecKHV = new Vector();
                            vecKHV.add(rs.getString("IDOrder"));
                            vecKHV.add(rs.getString("IDProduct"));
                            vecKHV.add(rs.getString("Quantity"));
                            vecKHV.add(formatter.format(rs.getInt("Price")));
                            vecKHV.add(rs.getString("NamePromo"));
                            vecKHV.add(rs.getString("CusName"));
                            vecKHV.add(rs.getString("Discount"));
                            vecKHV.add(rs.getString("TimeOrder"));
                            vecKHV.add(rs.getString("DateOrder"));
                            vecKHV.add(rs.getString("UsernameEmp"));
                            int quanKHV = rs.getInt("Quantity");
                            int priceKHV = rs.getInt("Price");
                            int discountKHV = rs.getInt("Discount");
                            int dismoneyKHV = (quanKHV * priceKHV * discountKHV) / 100;
                            int totalKHV = (priceKHV * quanKHV) - dismoneyKHV;
                            vecKHV.add(formatter.format(totalKHV));
                            tblModel.addRow(vecKHV);
                        }
                        tblHistory.setModel(tblModel);
                    } catch (SQLException e) {
                    }
                    //select theo CTKM
                    try {
                        pstmt = con.prepareStatement("select * from OrderDetails join [Order] on OrderDetails.IDOrder=[Order].IDOrder "
                            + "join Product on OrderDetails.IDProduct=Product.IDProduct "
                            + "join Promotions on OrderDetails.NamePromo=Promotions.NamePromo "
                            + "where UsernameEmp ='" + name + "' and DateOrder = '" + date + "'");
                        rs = pstmt.executeQuery();
                        while (rs.next()) {
                            vecCTKM = new Vector();
                            vecCTKM.add(rs.getString("IDOrder"));
                            vecCTKM.add(rs.getString("IDProduct"));
                            vecCTKM.add(rs.getString("Quantity"));
                            vecCTKM.add(formatter.format(rs.getInt("Price")));
                            vecCTKM.add(rs.getString("NamePromo"));
                            vecCTKM.add(rs.getString("CusName"));
                            vecCTKM.add(rs.getString("DiscountPromo"));
                            vecCTKM.add(rs.getString("TimeOrder"));
                            vecCTKM.add(rs.getString("DateOrder"));
                            vecCTKM.add(rs.getString("UsernameEmp"));
                            int quanCTKM = rs.getInt("Quantity");
                            int priceCTKM = rs.getInt("Price");
                            int discountCTKM = rs.getInt("DiscountPromo");
                            int dismoneyCTKM = (quanCTKM * priceCTKM * discountCTKM) / 100;
                            int totalCTKM = (priceCTKM * quanCTKM) - dismoneyCTKM;
                            vecCTKM.add(formatter.format(totalCTKM));
                            tblModel.addRow(vecCTKM);
                        }
                        tblHistory.setModel(tblModel);
                    } catch (SQLException ex) {
                    }
                    //select theo không áp dụng CTKM
                    try {
                        pstmt = con.prepareStatement("select * from OrderDetails join [Order] on OrderDetails.IDOrder=[Order].IDOrder "
                            + "join Product on OrderDetails.IDProduct=Product.IDProduct "
                            + "where NamePromo='Không có' and UsernameEmp ='" + name + "' and DateOrder = '" + date + "'");
                        rs = pstmt.executeQuery();
                        while (rs.next()) {
                            vecKhongCTKM = new Vector();
                            vecKhongCTKM.add(rs.getString("IDOrder"));
                            vecKhongCTKM.add(rs.getString("IDProduct"));
                            vecKhongCTKM.add(rs.getString("Quantity"));
                            vecKhongCTKM.add(formatter.format(rs.getInt("Price")));
                            vecKhongCTKM.add(rs.getString("NamePromo"));
                            vecKhongCTKM.add(rs.getString("CusName"));
                            vecKhongCTKM.add("0");
                            vecKhongCTKM.add(rs.getString("TimeOrder"));
                            vecKhongCTKM.add(rs.getString("DateOrder"));
                            vecKhongCTKM.add(rs.getString("UsernameEmp"));
                            int quanKhongCTKM = rs.getInt("Quantity");
                            int priceKhongCTKM = rs.getInt("Price");
                            int totalKhongCTKM = priceKhongCTKM * quanKhongCTKM;
                            vecKhongCTKM.add(formatter.format(totalKhongCTKM));
                            tblModel.addRow(vecKhongCTKM);
                        }
                        tblHistory.setModel(tblModel);
                    } catch (SQLException ex) {
                    }
                }
            } catch (SQLException ex) {
            }
        }
        LoadlbTotal();
        
        con.close();
        
        
        } catch (Exception e) {
//            JOptionPane.showMessageDialog(this, e.getMessage());
    JOptionPane.showMessageDialog(null, "Vui Lòng Nhập Vào Ngày Tìm Kiếm");
            e.printStackTrace();
        }
        
    }//GEN-LAST:event_btnSearchActionPerformed

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
            java.util.logging.Logger.getLogger(LichSu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LichSu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LichSu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LichSu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LichSu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPrint;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSearch;
    private javax.swing.JComboBox cbEmp;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbTotal;
    private javax.swing.JTable tblHistory;
    private com.toedter.calendar.JDateChooser txtdate;
    // End of variables declaration//GEN-END:variables
}
