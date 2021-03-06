/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uiNew;

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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import ui.ComboListener;
/**
 *
 * @author ACER NITRO 5
 */
public class LichSuJpanl extends javax.swing.JPanel {
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
     * Creates new form LichSuJpanl
     */
    public LichSuJpanl() {
        initComponents();
        tblHistory.setRowHeight(20);
        btnPrint.setEnabled(false);
        try {
            
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
        String connectionUrl = "jdbc:sqlserver://localhost;database=TheOne;";
        String dbusername = "sa";
        String password ="01219164372";
        Connection con = DriverManager.getConnection(connectionUrl,dbusername,password);
        
        
        PreparedStatement pstmt ;
        ResultSet rs;
        
        tblModel.addColumn("M?? ????n h??ng");
        tblModel.addColumn("M?? s???n ph???m");
        tblModel.addColumn("S??? l?????ng (Ly)");
        tblModel.addColumn("????n gi?? (VN??)");
        tblModel.addColumn("T??n CTKM");
        tblModel.addColumn("M?? kh??ch h??ng");
        tblModel.addColumn("Chi???t kh???u (%)");
        tblModel.addColumn("Th???i gian");
        tblModel.addColumn("Ng??y");
        tblModel.addColumn("TK nh??n vi??n");
        tblModel.addColumn("Th??nh ti???n (VN??)");
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
            JOptionPane.showMessageDialog(null, "L???i 101:: Kh??ng th??? k???t n???i ?????n m??y ch???");
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
        lbTotal.setText(formatter.format(total) + " VN??");
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
        //select theo kh??ch h??ng VIP
        try {
            pstmt = con.prepareStatement("select * from OrderDetails  join [Order] on OrderDetails.IDOrder=[Order].IDOrder "
                    + "join Product on OrderDetails.IDProduct=Product.IDProduct "
                    + "join Customer on OrderDetails.CusName=Customer.IDCus  "
                    + "where Orderdetails.CusName != 'Kh??ch v??ng lai'");
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
        //select theo kh??ng ??p d???ng CTKM
        try {
            pstmt = con.prepareStatement("select * from OrderDetails join [Order] on OrderDetails.IDOrder=[Order].IDOrder "
                    + "join Product on OrderDetails.IDProduct=Product.IDProduct "
                    + "where NamePromo='Kh??ng c??'");
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cbEmp = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        txtdate = new com.toedter.calendar.JDateChooser();
        btnSearch = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        btnPrint = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHistory = new javax.swing.JTable();
        lbTotal = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("TK nh??n vi??n:");

        cbEmp.setEditable(true);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Ng??y l???p ????n h??ng:");

        txtdate.setDateFormatString("dd/MM/yyyy");

        btnSearch.setText("T??m ki???m");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        btnReset.setText("L??m m???i");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        btnPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_send_to_printer_30px.png"))); // NOI18N
        btnPrint.setText("In");
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 0, 0));
        jLabel3.setText("T???ng s??? ti???n:");

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
        tblHistory.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHistoryMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblHistory);

        lbTotal.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbTotal.setForeground(new java.awt.Color(255, 0, 0));
        lbTotal.setText("0 VN??");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        jLabel4.setText("H??a ????n B??n Ra Nh??n Vi??n");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbTotal))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtdate, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSearch)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnReset)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12))
                    .addComponent(jSeparator1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(7, 7, 7)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnReset)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2)
                                    .addComponent(cbEmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(txtdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnSearch))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(lbTotal)))
                    .addComponent(btnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getAccessibleContext().setAccessibleName("H??a ????n Nh??n Vi??n");
    }// </editor-fold>//GEN-END:initComponents

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
                JOptionPane.showMessageDialog(null, "T??i kho???n nh??n vi??n kh??ng ???????c ????? tr???ng.");
                btnPrint.setEnabled(false);
            } else {
                try {
                    pstmt = con.prepareStatement("select * from OrderDetails  join [Order] on OrderDetails.IDOrder=[Order].IDOrder "
                        + "where UsernameEmp ='" + name + "' and DateOrder = '" + date + "'");
                    rs = pstmt.executeQuery();
                    if (!rs.next()) {
                        JOptionPane.showMessageDialog(null, "Nh??n vi??n " + name + " ch??a b??n ???????c s???n ph???m n??o trong ng??y " + date + ".");
                        btnResetActionPerformed(evt);
                    } else {
                        btnPrint.setEnabled(true);
                        tblModel.getDataVector().removeAllElements();
                        //select theo kh??ch h??ng VIP
                        try {
                            pstmt = con.prepareStatement("select * from OrderDetails  join [Order] on OrderDetails.IDOrder=[Order].IDOrder "
                                + "join Product on OrderDetails.IDProduct=Product.IDProduct "
                                + "join Customer on OrderDetails.CusName=Customer.IDCus  "
                                + "where Orderdetails.CusName != 'Kh??ch v??ng lai' and UsernameEmp ='" + name + "' and DateOrder = '" + date + "'");
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
                        //select theo kh??ng ??p d???ng CTKM
                        try {
                            pstmt = con.prepareStatement("select * from OrderDetails join [Order] on OrderDetails.IDOrder=[Order].IDOrder "
                                + "join Product on OrderDetails.IDProduct=Product.IDProduct "
                                + "where NamePromo='Kh??ng c??' and UsernameEmp ='" + name + "' and DateOrder = '" + date + "'");
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
            JOptionPane.showMessageDialog(null, "Vui L??ng Nh???p V??o Ng??y T??m Ki???m");
            e.printStackTrace();
        }

    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        cbEmp.setSelectedIndex(-1);
        ReloadTbl();
        LoadlbTotal();
        btnPrint.setEnabled(false);
        txtdate.setCalendar(null);
    }//GEN-LAST:event_btnResetActionPerformed

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
            //Vi???t v??o file txt
            try {
                Date now = new Date();
                Writer b = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("History.txt"), "UTF8"));
                b.write("COFFEE THE ONE\r\n\r\n");
                b.write("?????a ch???: ?????ng H??i\r\n");
                b.write("S??T: 0886374063\r\n");
                b.write("Th???i gian: " + time.format(now) + "\r\n\r\n");
                b.write("\t\t\t\t\tB???NG TH???NG K?? B??N H??NG NG??Y " + ft.format(txtdate.getDate()) + "\r\n");
                b.write("T??i kho???n: " + cbEmp.getSelectedItem() + "\r\n");
                try {
                    pstmt = con.prepareStatement("Select * from Employee where UsernameEmp=?");
                    pstmt.setString(1, (String) cbEmp.getSelectedItem());
                    rs = pstmt.executeQuery();
                    if (rs.next()) {
                        b.write("T??n nh??n vi??n: " + rs.getString("NameEmp") + "\r\n\r\n");
                    }
                } catch (SQLException ex) {
                }
                b.write("--------------------------------------------------------------------------------------------------------------------------------\r\n");
                b.write("M?? ??H\tM?? SP\tS??? l?????ng (ly)\t????n gi?? (VN??)\tT??n CTKM\tM?? kh??ch h??ng\tChi???t kh???u (%)\tTh???i gian\tTh??nh ti???n (VN??)\r\n");
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
                    if (!MKH.equals("Kh??ch v??ng lai")) {
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
                b.write("\t\t\t\t\t\t\t\t\t\t\t\tT???ng ti???n: " + lbTotal.getText());
                b.close();
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, e);
            }
            //M??? file txt
            Runtime run = Runtime.getRuntime();
            try {
                run.exec("notepad History.txt");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, e);
            }

            con.close();

        } catch (Exception e) {
            //            JOptionPane.showMessageDialog(this, e.getMessage());
            JOptionPane.showMessageDialog(null, "T??m Ki???m 1 Nh??n Vi??n R???i M???i ???????c Xu???t");
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnPrintActionPerformed

    private void tblHistoryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHistoryMouseClicked
        try {
            // TODO add your handling code here:
            int line = tblHistory.getSelectedRow();
            cbEmp.setActionCommand(String.valueOf(tblHistory.getValueAt(line, 9)));
            Date birthday = ft.parse((String) tblHistory.getValueAt(line, 8));
            txtdate.setDate(birthday);
        } catch (ParseException ex) {
            Logger.getLogger(LichSuJpanl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tblHistoryMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPrint;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSearch;
    private javax.swing.JComboBox cbEmp;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lbTotal;
    private javax.swing.JTable tblHistory;
    private com.toedter.calendar.JDateChooser txtdate;
    // End of variables declaration//GEN-END:variables
}
