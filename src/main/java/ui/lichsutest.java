/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import helpers.shareHelper;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ACER NITRO 5
 */
public class lichsutest extends javax.swing.JFrame {
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
     * Creates new form 
     */
    public lichsutest() {
        initComponents();
//        try {
//            
//        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
//        String connectionUrl = "jdbc:sqlserver://localhost;database=TheOne;";
//        String dbusername = "sa";
//        String password ="01219164372";
//        Connection con = DriverManager.getConnection(connectionUrl,dbusername,password);
//        
//        
//        PreparedStatement pstmt ;
//        ResultSet rs;
//        
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
//        
//        ReloadTbl();
//       
//        
//        
//        con.close();
//        
//        
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(this, e.getMessage());
//            e.printStackTrace();
//        }

        
    }
    
//    public void ReloadTbl() {
//        
//        
//        
//        try {
//            
//        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
//        String connectionUrl = "jdbc:sqlserver://localhost;database=TheOne;";
//        String dbusername = "sa";
//        String password ="01219164372";
//        Connection con = DriverManager.getConnection(connectionUrl,dbusername,password);
//        
//        
//        PreparedStatement pstmt  ;
//        ResultSet rs ;
//        
//        tblModel.getDataVector().removeAllElements();
//        //select theo khách hàng VIP
//        try {
//            pstmt = con.prepareStatement("select * from OrderDetails  join [Order] on OrderDetails.IDOrder=[Order].IDOrder "
//                    + "join Product on OrderDetails.IDProduct=Product.IDProduct "
//                    + "join Customer on OrderDetails.CusName=Customer.IDCus  "
//                    + "where Orderdetails.CusName != 'Khách vãng lai'");
//            rs = pstmt.executeQuery();
//            while (rs.next()) {
//                vecKHV = new Vector();
//                vecKHV.add(rs.getString("IDOrder"));
//                vecKHV.add(rs.getString("IDProduct"));
//                vecKHV.add(rs.getString("Quantity"));
//                vecKHV.add(formatter.format(rs.getInt("Price")));
//                vecKHV.add(rs.getString("NamePromo"));
//                vecKHV.add(rs.getString("CusName"));
//                vecKHV.add(rs.getString("Discount"));
//                vecKHV.add(rs.getString("TimeOrder"));
//                vecKHV.add(rs.getString("DateOrder"));
//                vecKHV.add(rs.getString("UsernameEmp"));
//                int quanKHV = rs.getInt("Quantity");
//                int priceKHV = rs.getInt("Price");
//                int discountKHV = rs.getInt("Discount");
//                int dismoneyKHV = (quanKHV * priceKHV * discountKHV) / 100;
//                int totalKHV = (priceKHV * quanKHV) - dismoneyKHV;
//                vecKHV.add(formatter.format(totalKHV));
//                tblModel.addRow(vecKHV);
//            }
//            tblHistory.setModel(tblModel);
//        } catch (Exception e) {
//        }
//        //select theo CTKM
//        try {
//            pstmt = con.prepareStatement("select * from OrderDetails join [Order] on OrderDetails.IDOrder=[Order].IDOrder "
//                    + "join Product on OrderDetails.IDProduct=Product.IDProduct "
//                    + "join Promotions on OrderDetails.NamePromo=Promotions.NamePromo");
//            rs = pstmt.executeQuery();
//            while (rs.next()) {
//                vecCTKM = new Vector();
//                vecCTKM.add(rs.getString("IDOrder"));
//                vecCTKM.add(rs.getString("IDProduct"));
//                vecCTKM.add(rs.getString("Quantity"));
//                vecCTKM.add(formatter.format(rs.getInt("Price")));
//                vecCTKM.add(rs.getString("NamePromo"));
//                vecCTKM.add(rs.getString("CusName"));
//                vecCTKM.add(rs.getString("DiscountPromo"));
//                vecCTKM.add(rs.getString("TimeOrder"));
//                vecCTKM.add(rs.getString("DateOrder"));
//                vecCTKM.add(rs.getString("UsernameEmp"));
//                int quanCTKM = rs.getInt("Quantity");
//                int priceCTKM = rs.getInt("Price");
//                int discountCTKM = rs.getInt("DiscountPromo");
//                int dismoneyCTKM = (quanCTKM * priceCTKM * discountCTKM) / 100;
//                int totalCTKM = (priceCTKM * quanCTKM) - dismoneyCTKM;
//                vecCTKM.add(formatter.format(totalCTKM));
//                tblModel.addRow(vecCTKM);
//            }
//            tblHistory.setModel(tblModel);
//        } catch (SQLException ex) {
//        }
//        //select theo không áp dụng CTKM
//        try {
//            pstmt = con.prepareStatement("select * from OrderDetails join [Order] on OrderDetails.IDOrder=[Order].IDOrder "
//                    + "join Product on OrderDetails.IDProduct=Product.IDProduct "
//                    + "where NamePromo='Không có'");
//            rs = pstmt.executeQuery();
//            while (rs.next()) {
//                vecKhongCTKM = new Vector();
//                vecKhongCTKM.add(rs.getString("IDOrder"));
//                vecKhongCTKM.add(rs.getString("IDProduct"));
//                vecKhongCTKM.add(rs.getString("Quantity"));
//                vecKhongCTKM.add(formatter.format(rs.getInt("Price")));
//                vecKhongCTKM.add(rs.getString("NamePromo"));
//                vecKhongCTKM.add(rs.getString("CusName"));
//                vecKhongCTKM.add("0");
//                vecKhongCTKM.add(rs.getString("TimeOrder"));
//                vecKhongCTKM.add(rs.getString("DateOrder"));
//                vecKhongCTKM.add(rs.getString("UsernameEmp"));
//                int quanKhongCTKM = rs.getInt("Quantity");
//                int priceKhongCTKM = rs.getInt("Price");
//                int totalKhongCTKM = priceKhongCTKM * quanKhongCTKM;
//                vecKhongCTKM.add(formatter.format(totalKhongCTKM));
//                tblModel.addRow(vecKhongCTKM);
//            }
//            tblHistory.setModel(tblModel);
//        } catch (SQLException ex) {
//        }
//        
//        
//        
//        
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(this, e.getMessage());
//            e.printStackTrace();
//        }
//    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblHistory = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
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
        tblHistory.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHistoryMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblHistory);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 812, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 792, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(50, 50, 50)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addGap(50, 50, 50)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblHistoryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHistoryMouseClicked
//        try {
//            // TODO add your handling code here:
//            int line = tblHistory.getSelectedRow();
//            cbEmp.setActionCommand(String.valueOf(tblHistory.getValueAt(line, 9)));
//            Date birthday = ft.parse((String) tblHistory.getValueAt(line, 8));
//            txtdate.setDate(birthday);
//        } catch (ParseException ex) {
//            Logger.getLogger(LichSuJpanl.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }//GEN-LAST:event_tblHistoryMouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        try {
            
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
        String connectionUrl = "jdbc:sqlserver://localhost;database=TheOne;";
        String dbusername = "sa";
        String password ="01219164372";
        Connection con = DriverManager.getConnection(connectionUrl,dbusername,password);
        
        
        PreparedStatement pstmt;
        ResultSet rs ;
        
        
        String name = (String) shareHelper.NhanVienDangNhap.getUser();
        if (shareHelper.NhanVienDangNhap.getUser() == null) {
            JOptionPane.showMessageDialog(null, "Tài khoản nhân viên trống.");
            
        } else {
            try {
                pstmt = con.prepareStatement("select * from OrderDetails  join [Order] on OrderDetails.IDOrder=[Order].IDOrder "
                    + "where UsernameEmp ='" + name+ "'");
                rs = pstmt.executeQuery();
                if (!rs.next()) {
                    JOptionPane.showMessageDialog(null, "Nhân viên " + name + " chưa bán được sản phẩm nào !");
                    return;
                } else {
                   
                    
                    //select theo khách hàng VIP
                    try {
                        pstmt = con.prepareStatement("select * from OrderDetails  join [Order] on OrderDetails.IDOrder=[Order].IDOrder "
                            + "join Product on OrderDetails.IDProduct=Product.IDProduct "
                            + "join Customer on OrderDetails.CusName=Customer.IDCus  "
                            + "where Orderdetails.CusName != 'Khách vãng lai' and UsernameEmp ='" + name+ "'");
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
                            + "where UsernameEmp ='" + name+ "'");
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
                            + "where NamePromo='Không có' and UsernameEmp ='" + name+ "'");
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
//        LoadlbTotal();
        
        con.close();
        
        
        } catch (Exception e) {
//            JOptionPane.showMessageDialog(this, e.getMessage());
   
            e.printStackTrace();
        }
        
//        ReloadTbl();
    }//GEN-LAST:event_formWindowOpened

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
            java.util.logging.Logger.getLogger(lichsutest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(lichsutest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(lichsutest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(lichsutest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new lichsutest().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblHistory;
    // End of variables declaration//GEN-END:variables
}
