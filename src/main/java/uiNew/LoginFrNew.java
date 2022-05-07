/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uiNew;

import dao.NhaVienDao;
import dao.adminDao;
import helpers.dialogHelper;
import helpers.shareHelper;
import java.awt.Color;
import java.awt.geom.RoundRectangle2D;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import model.NhanVien;
import model.admin;

/**
 *
 * @author ACER NITRO 5
 */
public class LoginFrNew extends javax.swing.JFrame {
int xx,xy;
    /**
     * Creates new form LoginFrNew
     */
    public LoginFrNew() {
        
        initComponents();
        setLocationRelativeTo(null);
        buttonGroup1.add(rdoNhanVien);
        buttonGroup1.add(rdoQuanLy);
        setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 30, 30));
        rdoQuanLy.setForeground(new Color(102,102,102));
        setTitle("Đăng Nhập");
        ImageIcon img = new ImageIcon("D:\\CAFFEEN4\\src\\main\\resources\\icon\\icons8_workstation_60px.png");
        this.setIconImage(img.getImage());
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
        jPanel1 = new javax.swing.JPanel();
        chkMatkhau = new javax.swing.JCheckBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtUser = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        rdoQuanLy = new javax.swing.JRadioButton();
        rdoNhanVien = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();
        kButton1 = new com.k33ptoo.components.KButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(32, 33, 35));
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(32, 33, 35));
        jPanel1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel1MouseDragged(evt);
            }
        });
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel1MousePressed(evt);
            }
        });
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        chkMatkhau.setBackground(new java.awt.Color(32, 33, 35));
        chkMatkhau.setForeground(new java.awt.Color(102, 102, 102));
        chkMatkhau.setText("Hiện Mật Khẩu");
        chkMatkhau.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        chkMatkhau.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                chkMatkhauFocusGained(evt);
            }
        });
        chkMatkhau.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chkMatkhauMouseClicked(evt);
            }
        });
        chkMatkhau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkMatkhauActionPerformed(evt);
            }
        });
        jPanel1.add(chkMatkhau, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 210, -1, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/chu png trắng9.png"))); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(-60, 80, 390, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/123.jpg"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 370, 481));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(204, 204, 204));
        jLabel4.setText("Tên Đăng Nhập:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 90, -1, -1));

        txtUser.setBackground(new Color(0,0,0,0));
        txtUser.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txtUser.setForeground(new java.awt.Color(255, 255, 255));
        txtUser.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        txtUser.setCaretColor(new java.awt.Color(204, 0, 255));
        txtUser.setOpaque(false);
        txtUser.setPreferredSize(new java.awt.Dimension(59, 20));
        txtUser.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtUserFocusGained(evt);
            }
        });
        jPanel1.add(txtUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 100, 290, 32));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setText("Mật Khẩu:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 160, -1, -1));

        txtPassword.setBackground(new Color(0,0,0,0));
        txtPassword.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txtPassword.setForeground(new java.awt.Color(255, 255, 255));
        txtPassword.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        txtPassword.setCaretColor(new java.awt.Color(204, 0, 255));
        txtPassword.setOpaque(false);
        txtPassword.setPreferredSize(new java.awt.Dimension(59, 20));
        txtPassword.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtPasswordFocusGained(evt);
            }
        });
        jPanel1.add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 170, 290, 30));

        rdoQuanLy.setBackground(new Color(0,0,0,0));
        rdoQuanLy.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        rdoQuanLy.setForeground(new java.awt.Color(204, 204, 204));
        rdoQuanLy.setText("Quản Lý");
        rdoQuanLy.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        rdoQuanLy.setOpaque(false);
        rdoQuanLy.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdoQuanLyMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                rdoQuanLyMousePressed(evt);
            }
        });
        jPanel1.add(rdoQuanLy, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 240, 70, 20));

        rdoNhanVien.setBackground(new Color(0,0,0,0));
        rdoNhanVien.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        rdoNhanVien.setForeground(new java.awt.Color(204, 204, 204));
        rdoNhanVien.setSelected(true);
        rdoNhanVien.setText("Nhân Viên");
        rdoNhanVien.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        rdoNhanVien.setOpaque(false);
        rdoNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdoNhanVienMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                rdoNhanVienMousePressed(evt);
            }
        });
        jPanel1.add(rdoNhanVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 240, 80, 20));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_delete_24px_1.png"))); // NOI18N
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 10, -1, -1));

        kButton1.setForeground(new java.awt.Color(0, 0, 0));
        kButton1.setText("Đăng Nhập");
        kButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        kButton1.setkBorderRadius(40);
        kButton1.setkEndColor(new java.awt.Color(255, 255, 255));
        kButton1.setkFillButton(false);
        kButton1.setkHoverEndColor(new java.awt.Color(204, 0, 204));
        kButton1.setkHoverForeGround(new java.awt.Color(255, 204, 255));
        kButton1.setkHoverStartColor(new java.awt.Color(255, 255, 255));
        kButton1.setkStartColor(new java.awt.Color(255, 255, 255));
        kButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(kButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 280, 180, 40));

        jPanel2.setBackground(new java.awt.Color(32, 33, 35));
        jPanel2.setPreferredSize(new java.awt.Dimension(24, 24));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_delete_24px_1.png"))); // NOI18N
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel6MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel6MouseExited(evt);
            }
        });
        jPanel2.add(jLabel6);

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 10, 30, 30));

        jPanel3.setBackground(new java.awt.Color(32, 33, 35));
        jPanel3.setPreferredSize(new java.awt.Dimension(24, 24));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_subtract_24px.png"))); // NOI18N
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel7MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel7MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel7MousePressed(evt);
            }
        });
        jPanel3.add(jLabel7);

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 10, 30, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, 480));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        // TODO add your handling code here:
//        System.exit(0);
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        // TODO add your handling code here:
         System.exit(0);
    }//GEN-LAST:event_jLabel6MouseClicked

    private void jPanel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MousePressed
        // TODO add your handling code here:
        xx = evt.getX();
        xy = evt.getY();
    }//GEN-LAST:event_jPanel1MousePressed

    private void jPanel1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseDragged
        // TODO add your handling code here:
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x-xx,y-xy);
    }//GEN-LAST:event_jPanel1MouseDragged

    private void kButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kButton1ActionPerformed
        // TODO add your handling code here:
        OK();
    }//GEN-LAST:event_kButton1ActionPerformed

    private void txtUserFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtUserFocusGained
        // TODO add your handling code here:
        jLabel4.setForeground(new Color(204,204,204));
        jLabel1.setForeground(new Color(102,102,102));
    }//GEN-LAST:event_txtUserFocusGained

    private void txtPasswordFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPasswordFocusGained
        // TODO add your handling code here:
        jLabel1.setForeground(new Color(204,204,204));
        jLabel4.setForeground(new Color(102,102,102));
    }//GEN-LAST:event_txtPasswordFocusGained

    private void chkMatkhauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkMatkhauActionPerformed
        // hien mat khau
        if (chkMatkhau.isSelected()) {
            txtPassword.setEchoChar((char)0);
        }else{
            txtPassword.setEchoChar('*');
        }
    }//GEN-LAST:event_chkMatkhauActionPerformed

    private void chkMatkhauFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_chkMatkhauFocusGained
        // TODO add your handling code here:
//            if (chkMatkhau.setSelected(false)){
//                chkMatkhau.setForeground(new Color(102,102,102));
//            }else{
//                chkMatkhau.setForeground(new Color(204,204,204));
//            }
//            
    }//GEN-LAST:event_chkMatkhauFocusGained

    private void chkMatkhauMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chkMatkhauMouseClicked
       
        if (chkMatkhau.isSelected()) {
            chkMatkhau.setForeground(new Color(204,204,204));
        }else{
            
            chkMatkhau.setForeground(new Color(102,102,102));
        }
    }//GEN-LAST:event_chkMatkhauMouseClicked

    private void rdoNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdoNhanVienMouseClicked
        // TODO add your handling code here:
        
            rdoNhanVien.setForeground(new Color(204,204,204));
        
            rdoQuanLy.setForeground(new Color(102,102,102));
        
    }//GEN-LAST:event_rdoNhanVienMouseClicked

    private void rdoQuanLyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdoQuanLyMouseClicked
        // TODO add your handling code here:
        
//            rdoQuanLy.setForeground(new Color(204,204,204));
//        
//            
//            rdoNhanVien.setForeground(new Color(102,102,102));
        
    }//GEN-LAST:event_rdoQuanLyMouseClicked

    private void rdoNhanVienMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdoNhanVienMousePressed
        // TODO add your handling code here:
        rdoNhanVien.setForeground(new Color(204,204,204));
        
            rdoQuanLy.setForeground(new Color(102,102,102));
    }//GEN-LAST:event_rdoNhanVienMousePressed

    private void rdoQuanLyMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdoQuanLyMousePressed
        // TODO add your handling code here:
        rdoQuanLy.setForeground(new Color(204,204,204));
        
            
            rdoNhanVien.setForeground(new Color(102,102,102));
    }//GEN-LAST:event_rdoQuanLyMousePressed

    private void jLabel7MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MousePressed
        // TODO add your handling code here:
        this.setExtendedState(LoginFrNew.ICONIFIED);
    }//GEN-LAST:event_jLabel7MousePressed

    private void jLabel6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseEntered
        // TODO add your handling code here:
//        setColor(jPanel2);
    }//GEN-LAST:event_jLabel6MouseEntered

    private void jLabel6MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseExited
        // TODO add your handling code here:
//        resetColor(jPanel2);
    }//GEN-LAST:event_jLabel6MouseExited

    private void jLabel7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseEntered
        // TODO add your handling code here:
//        setColor(jPanel3);
    }//GEN-LAST:event_jLabel7MouseEntered

    private void jLabel7MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseExited
        // TODO add your handling code here:
//        resetColor(jPanel3);
    }//GEN-LAST:event_jLabel7MouseExited

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
            java.util.logging.Logger.getLogger(LoginFrNew.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginFrNew.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginFrNew.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginFrNew.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginFrNew().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox chkMatkhau;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private com.k33ptoo.components.KButton kButton1;
    private javax.swing.JRadioButton rdoNhanVien;
    private javax.swing.JRadioButton rdoQuanLy;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUser;
    // End of variables declaration//GEN-END:variables
}
