/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import dao.NhaVienDao;
import dao.adminDao;
import helpers.dialogHelper;
import helpers.shareHelper;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.geom.RoundRectangle2D;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.border.Border;
import model.NhanVien;
import model.admin;
import ui.BillCafeFr;
import uiNew.AdminFr;
import uiNew.billCafeNew;
/**
 *
 * @author ACER NITRO 5
 */
public class frLogin extends javax.swing.JFrame {
public int pixels;
 public boolean kTransparentControls = true;
    /**
     * Creates new form frLogin
     */
    public frLogin() {
        initComponents();
        setLocationRelativeTo(null);
        buttonGroup1.add(rdoNhanVien);
        buttonGroup1.add(rdoQuanLy);
        setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 30, 30));
        
    }
    
    public frLogin(int pix) {

        this.setPreferredSize(new Dimension(380, 280));
        this.pixels = pix;
        Border border = BorderFactory.createEmptyBorder(pixels, pixels, pixels, pixels);
        
        this.setLayout(new BorderLayout());
        
        /**
         * Above code is use to make drop shadow border
         */

        /////////////////////////////////////
        if (kTransparentControls) {
            setBg(true);
        } else {
            setBg(false);
        }

    }
    
    
    public boolean iskTransparentControls() {
        return kTransparentControls;
    }

    public void setkTransparentControls(boolean kTransparentControls) {
        this.kTransparentControls = kTransparentControls;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    private void setBg(boolean isOpaque) {
        Component[] components = this.getComponents();
        for (Component component : components) {

            ((JLabel) component).setOpaque(isOpaque);
            ((JCheckBox) component).setOpaque(isOpaque);
            ((JTextField) component).setOpaque(isOpaque);
            ((JPasswordField) component).setOpaque(isOpaque);
            ((JFormattedTextField) component).setOpaque(isOpaque);
            ((JToolBar) component).setOpaque(isOpaque);
            ((JRadioButton) component).setOpaque(isOpaque);

        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    NhaVienDao daosv = new NhaVienDao();
    adminDao dao = new adminDao();
    public void OK(){        
        try {
            
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
        String connectionUrl = "jdbc:sqlserver://localhost;database=TheOne;";
        String dbusername = "sa";
        String password ="01219164372";
        Connection con = DriverManager.getConnection(connectionUrl,dbusername,password);
        
        
        PreparedStatement pstmt ;
        ResultSet rs ;
        
        while(true){
            if(txtUser.getText().trim().equals("")){
                JOptionPane.showMessageDialog(null, "Tên tài khoản không được để trống.");
                txtUser.grabFocus();
                return;
            } else{
                break;
            }
        }
        while(true){
            if(txtPassword.getText().trim().equals("")){
                JOptionPane.showMessageDialog(null, "Mật khẩu không được để trống.");
                txtPassword.grabFocus();
                return;
            } else{
                break;
            }
        }
        String manv = txtUser.getText();
        String matKhau = new String(txtPassword.getPassword());
        if (rdoQuanLy.isSelected()==true) {
            try {
                String admin = "select * from Administrator where Username=? COLLATE SQL_Latin1_General_CP1_CS_AS and Password=? COLLATE SQL_Latin1_General_CP1_CS_AS";
                pstmt = con.prepareStatement(admin);
                pstmt.setString(1, txtUser.getText());
                pstmt.setString(2, txtPassword.getText());
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    new AdminFr(rs.getString("Username")).setVisible(true);
                    this.setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null, "Lỗi:: Sai tên tài khoản hoặc mật khẩu Quản lý.");
                    txtUser.grabFocus();
                }
                
                admin ad = dao.findById(manv); 
            if(ad != null){    //nếu manv đúng
                String matKhau2 = ad.getPassword();
                if(matKhau.equals(matKhau2)){  //nếu mật khẩu đúng
                    shareHelper.adminDangNhap = ad;
//                    dialogHelper.alert(this, "Đăng nhập thành công!");
                    
                    
                }
                else{
                    dialogHelper.alert(this, "Sai mật khẩu!");
                }
            }
            else{
                dialogHelper.alert(this, "Sai tên đăng nhập!");
            }
                
                
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Lỗi:: Không thể kết nối đến SQL");
            }
        }
        if (rdoNhanVien.isSelected()==true) {
            try {
                String Emp = "select * from Employee where UsernameEmp=? COLLATE SQL_Latin1_General_CP1_CS_AS and Password=? COLLATE SQL_Latin1_General_CP1_CS_AS";
                pstmt = con.prepareStatement(Emp);
                pstmt.setString(1, txtUser.getText());
                pstmt.setString(2, txtPassword.getText());
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    new billCafeNew(rs.getString("NameEmp")).setVisible(true);
                    this.setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null, "Lỗi:: Sai tên tài khoản hoặc mật khẩu Nhân viên.");
                    txtUser.grabFocus();
                }
                
                NhanVien sv = daosv.findById(manv); 
            if(sv != null){    //nếu manv đúng
                String matKhau2 = sv.getPass();
                if(matKhau.equals(matKhau2)){  //nếu mật khẩu đúng
                    shareHelper.NhanVienDangNhap = sv;
//                    dialogHelper.alert(this, "Đăng nhập thành công!");      
                }
                
            }
            
            
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Lỗi:: Không thể kết nối đến máy chủ");
            }
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        kGradientPanel1 = new com.k33ptoo.components.KGradientPanel();
        txtUser = new javax.swing.JTextField();
        txtPassword = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        kButton1 = new com.k33ptoo.components.KButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        rdoQuanLy = new javax.swing.JRadioButton();
        rdoNhanVien = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        kGradientPanel1.setkEndColor(new java.awt.Color(204, 0, 204));
        kGradientPanel1.setkStartColor(new java.awt.Color(0, 204, 204));
        kGradientPanel1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                kGradientPanel1MouseDragged(evt);
            }
        });
        kGradientPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                kGradientPanel1MousePressed(evt);
            }
        });
        kGradientPanel1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kGradientPanel1KeyPressed(evt);
            }
        });
        kGradientPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtUser.setBackground(new Color(0,0,0,0));
        txtUser.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtUser.setForeground(new java.awt.Color(255, 255, 255));
        txtUser.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        txtUser.setCaretColor(new java.awt.Color(204, 0, 255));
        txtUser.setOpaque(false);
        txtUser.setPreferredSize(new java.awt.Dimension(59, 20));
        kGradientPanel1.add(txtUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(216, 162, 430, 32));

        txtPassword.setBackground(new Color(0,0,0,0));
        txtPassword.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txtPassword.setForeground(new java.awt.Color(255, 255, 255));
        txtPassword.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        txtPassword.setCaretColor(new java.awt.Color(204, 0, 255));
        txtPassword.setOpaque(false);
        txtPassword.setPreferredSize(new java.awt.Dimension(59, 20));
        kGradientPanel1.add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 230, 430, 30));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setText("Mật Khẩu:");
        kGradientPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 220, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 204, 204));
        jLabel2.setText("Tên Đăng Nhâp:");
        kGradientPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 150, -1, -1));

        kButton1.setForeground(new java.awt.Color(0, 0, 0));
        kButton1.setText("Đăng Nhập");
        kButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        kButton1.setkBorderRadius(40);
        kButton1.setkEndColor(new java.awt.Color(0, 204, 204));
        kButton1.setkFillButton(false);
        kButton1.setkHoverEndColor(new java.awt.Color(204, 0, 204));
        kButton1.setkHoverForeGround(new java.awt.Color(255, 204, 255));
        kButton1.setkHoverStartColor(new java.awt.Color(0, 204, 204));
        kButton1.setkStartColor(new java.awt.Color(255, 255, 255));
        kButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kButton1ActionPerformed(evt);
            }
        });
        kGradientPanel1.add(kButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 340, 180, 40));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_delete_24px_1.png"))); // NOI18N
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        kGradientPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 10, -1, -1));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_Male_User_50px_1.png"))); // NOI18N
        kGradientPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 60, -1, -1));

        rdoQuanLy.setBackground(new Color(0,0,0,0));
        rdoQuanLy.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        rdoQuanLy.setForeground(new java.awt.Color(255, 255, 255));
        rdoQuanLy.setText("Quản Lý");
        rdoQuanLy.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        rdoQuanLy.setOpaque(false);
        kGradientPanel1.add(rdoQuanLy, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 300, 70, 20));

        rdoNhanVien.setBackground(new Color(0,0,0,0));
        rdoNhanVien.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        rdoNhanVien.setForeground(new java.awt.Color(255, 255, 255));
        rdoNhanVien.setSelected(true);
        rdoNhanVien.setText("Nhân Viên");
        rdoNhanVien.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        rdoNhanVien.setOpaque(false);
        kGradientPanel1.add(rdoNhanVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 300, 80, 20));

        getContentPane().add(kGradientPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 849, 510));

        pack();
    }// </editor-fold>//GEN-END:initComponents
int xx,xy;
    private void kGradientPanel1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_kGradientPanel1MouseDragged
        // TODO add your handling code here:
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x-xx,y-xy);
    }//GEN-LAST:event_kGradientPanel1MouseDragged

    private void kGradientPanel1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kGradientPanel1KeyPressed
        // TODO add your handling code here:
     
    }//GEN-LAST:event_kGradientPanel1KeyPressed

    private void kGradientPanel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_kGradientPanel1MousePressed
        // TODO add your handling code here:
        xx = evt.getX();
        xy = evt.getY();
    }//GEN-LAST:event_kGradientPanel1MousePressed

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jLabel3MouseClicked

    private void kButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kButton1ActionPerformed
        // TODO add your handling code here:
        OK();
    }//GEN-LAST:event_kButton1ActionPerformed

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private com.k33ptoo.components.KButton kButton1;
    private com.k33ptoo.components.KGradientPanel kGradientPanel1;
    private javax.swing.JRadioButton rdoNhanVien;
    private javax.swing.JRadioButton rdoQuanLy;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUser;
    // End of variables declaration//GEN-END:variables
}
