/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;
import dao.NhaVienDao;
import helpers.ImageHelper;
import helpers.shareHelper;
import java.awt.Image;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import model.NhanVien;

/**
 *
 * @author ACER NITRO 5
 */
public class thongTinNhanVien extends javax.swing.JFrame {
    private byte[] personalImage;
    /**
     * Creates new form thongTinNhanVien
     */
    public thongTinNhanVien() {
        initComponents();
        init();
         setLocationRelativeTo(null);
          buttonGroup1.add(rdoNam);
        buttonGroup1.add(rdoNu);
    }
    
    NhaVienDao dao = new NhaVienDao();
           
    public void init(){
        txtTaiKhoan.setText(shareHelper.NhanVienDangNhap.getUser());
        txtTaiKhoan.setEditable(false);
        txtMatKhau.setText(shareHelper.NhanVienDangNhap.getPass());
        txtMatKhau.setEditable(false);
        txtHoTen.setText(shareHelper.NhanVienDangNhap.getName());
        txtHoTen.setEditable(false);
        
//        txtNgaySinh.setText(shareHelper.sinhVienDangNhap.getNgaySinh());

                JDateChooser chooser = new JDateChooser();
            JTextFieldDateEditor editor = (JTextFieldDateEditor) chooser.getDateEditor();
editor.setEditable(false);

        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(shareHelper.NhanVienDangNhap.getNgaySinh());
            jDateChooser1.setDate(date);
            } catch (ParseException ex) {   
            }
        
        rdoNam.setEnabled(false);
        rdoNu.setEnabled(false);
        rdoNam.setSelected(shareHelper.NhanVienDangNhap.getGioiTinh() == 1);
        rdoNu.setSelected(shareHelper.NhanVienDangNhap.getGioiTinh() == 0 );
        txtSoDT.setText(shareHelper.NhanVienDangNhap.getPhone());
        txtSoDT.setEditable(false);
        txtEmail.setText(shareHelper.NhanVienDangNhap.getEmail());
        txtEmail.setEditable(false);
        txtDiaChi.setText(shareHelper.NhanVienDangNhap.getDiaChi());
        txtDiaChi.setEditable(false);
        if (shareHelper.NhanVienDangNhap.getHinh() != null) {
            try {
                Image img = ImageHelper.createImageFromVyteArray(shareHelper.NhanVienDangNhap.getHinh(), "jpg");
                lblImage.setIcon(new ImageIcon(img));
                personalImage = shareHelper.NhanVienDangNhap.getHinh();
            } catch (IOException ex) {
                Logger.getLogger(thongTinNhanVien.class.getName()).log(Level.SEVERE, null, ex);
            }
                    } else {
                        personalImage = shareHelper.NhanVienDangNhap.getHinh();
                        ImageIcon icon = new ImageIcon(
                            getClass().getResource(""));
                        lblImage.setIcon(icon);
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
        jLabel1 = new javax.swing.JLabel();
        txtTaiKhoan = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtMatKhau = new javax.swing.JTextField();
        txtHoTen = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        rdoNam = new javax.swing.JRadioButton();
        rdoNu = new javax.swing.JRadioButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDiaChi = new javax.swing.JTextArea();
        txtEmail = new javax.swing.JTextField();
        txtSoDT = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        lblImage = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Tài khoản:");

        txtTaiKhoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTaiKhoanActionPerformed(evt);
            }
        });

        jLabel2.setText("Mật Khẩu:");

        jLabel3.setText("Họ Và Tên:");

        jLabel5.setText("Ngày Sinh:");

        jLabel6.setText("Giới Tính:");

        rdoNam.setText("Nam");

        rdoNu.setText("Nữ");

        jLabel8.setText("Số Điện Thoại:");

        jLabel9.setText("Email:");

        jLabel7.setText("Địa Chỉ:");

        txtDiaChi.setColumns(20);
        txtDiaChi.setRows(5);
        jScrollPane1.setViewportView(txtDiaChi);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblImage, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblImage, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jDateChooser1.setBackground(new java.awt.Color(204, 204, 204));
        jDateChooser1.setDateFormatString("dd-MM-yyyy");
        jDateChooser1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(19, 19, 19)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel7)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel5))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(rdoNam, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(rdoNu, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(1, 1, 1)))))
                        .addGap(70, 70, 70))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtEmail, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtSoDT, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtSoDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(rdoNam)
                                .addComponent(rdoNu)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(132, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtTaiKhoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTaiKhoanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTaiKhoanActionPerformed

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
            java.util.logging.Logger.getLogger(thongTinNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(thongTinNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(thongTinNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(thongTinNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new thongTinNhanVien().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblImage;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JTextArea txtDiaChi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtMatKhau;
    private javax.swing.JTextField txtSoDT;
    private javax.swing.JTextField txtTaiKhoan;
    // End of variables declaration//GEN-END:variables
}