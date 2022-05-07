/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uiNew;

import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import ui.ComboListener;
/**
 *
 * @author ACER NITRO 5
 */
public class DonXuatJpanl extends javax.swing.JPanel {
DefaultTableModel tblModel = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    Vector row, rowSearch, vecIDOrder, vecIDProduct, vecEmp, vecPromotions, vecIDCus;
    /**
     * Creates new form DonXuatJpanl
     */
    public DonXuatJpanl() {
        initComponents();
        tblOrder.setRowHeight(20);
        tblModel.addColumn("Mã đơn hàng");
        tblModel.addColumn("Mã sản phẩm");
        tblModel.addColumn("Mã khách hàng");
        tblModel.addColumn("Số lượng");
        tblModel.addColumn("CTKM");
        tblModel.addColumn("Thời gian");
        tblModel.addColumn("Ngày");
        tblModel.addColumn("Nhân viên");
        tblOrder.setModel(tblModel);
        ReloadTable();
        ReloadCombobox();
    }
public void ReloadTable() {
                
        try {
            
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
        String connectionUrl = "jdbc:sqlserver://localhost;database=TheOne;";
        String dbusername = "sa";
        String password ="01219164372";
        Connection con = DriverManager.getConnection(connectionUrl,dbusername,password);
        
        
        PreparedStatement ps;
        ResultSet rs;
        
        tblModel.getDataVector().removeAllElements();
        try {
            ps = con.prepareStatement("select * from OrderDetails join [Order] on OrderDetails.IDOrder=[Order].IDOrder Order by OrderDetails.IDOrder DESC");
            rs = ps.executeQuery();
            while (rs.next()) {
                row = new Vector();
                row.add(rs.getString("IDOrder"));
                row.add(rs.getString("IDProduct"));
                row.add(rs.getString("CusName"));
                row.add(rs.getString("Quantity"));
                row.add(rs.getString("NamePromo"));
                row.add(rs.getString("TimeOrder"));
                row.add(rs.getString("DateOrder"));
                row.add(rs.getString("UsernameEmp"));
                tblModel.addRow(row);
            }
            tblOrder.setModel(tblModel);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi:: Không thể kết nối đến máy chủ");
        }
        
        con.close();
        
        
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            e.printStackTrace();
        }
    }

    public void ReloadCombobox() {
        
        try {
            
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
        String connectionUrl = "jdbc:sqlserver://localhost;database=TheOne;";
        String dbusername = "sa";
        String password ="01219164372";
        Connection con = DriverManager.getConnection(connectionUrl,dbusername,password);
        
        PreparedStatement ps;
        ResultSet rs, rsIDOrder, rsIDProduct, rsEmp, rsPromotions, rsIDCus, rsSearch1, rsSearch2;
        
        cbIDOrder.removeAllItems();
        cbIDProduct.removeAllItems();
        cbNameEmp.removeAllItems();
        cbPromotions.removeAllItems();
        cbIDCus.removeAllItems();
        try {
            String url = "Select IDOrder from [Order] Order by IDOrder DESC";
            ps = con.prepareStatement(url);
            rsIDOrder = ps.executeQuery();
            vecIDOrder = new Vector();
            while (rsIDOrder.next()) {
                vecIDOrder.add(rsIDOrder.getString("IDOrder"));
            }
            JTextField text = (JTextField) cbIDOrder.getEditor().getEditorComponent();
            text.setText("");
            text.addKeyListener(new ComboListener(cbIDOrder, vecIDOrder));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi 101:: Không thể kết nối đến máy chủ");
        }
        try {
            String url = "Select IDProduct from Product Order by IDProduct DESC";
            ps = con.prepareStatement(url);
            rsIDProduct = ps.executeQuery();
            vecIDProduct = new Vector();
            while (rsIDProduct.next()) {
                vecIDProduct.add(rsIDProduct.getString("IDProduct"));
            }
            JTextField text = (JTextField) cbIDProduct.getEditor().getEditorComponent();
            text.setText("");
            text.addKeyListener(new ComboListener(cbIDProduct, vecIDProduct));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi 101:: Không thể kết nối đến máy chủ");
        }
        try {
            String url = "Select UsernameEmp from Employee where UsernameEmp != 'null'";
            ps = con.prepareStatement(url);
            rsEmp = ps.executeQuery();
            vecEmp = new Vector();
            while (rsEmp.next()) {
                vecEmp.add(rsEmp.getString("UsernameEmp"));
            }
            JTextField text = (JTextField) cbNameEmp.getEditor().getEditorComponent();
            text.setText("");
            text.addKeyListener(new ComboListener(cbNameEmp, vecEmp));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi 101:: Không thể kết nối đến máy chủ");
        }
        try {
            String url = "Select NamePromo from Promotions Order by IDPromo DESC";
            ps = con.prepareStatement(url);
            rsPromotions = ps.executeQuery();
            vecPromotions = new Vector();
            while (rsPromotions.next()) {
                vecPromotions.add(rsPromotions.getString("NamePromo"));
            }
            JTextField text = (JTextField) cbPromotions.getEditor().getEditorComponent();
            text.setText("");
            text.addKeyListener(new ComboListener(cbPromotions, vecPromotions));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi 101:: Không thể kết nối đến máy chủ");
        }
        try {
            String url = "Select IDCus from Customer Order by IDCus DESC";
            ps = con.prepareStatement(url);
            rsIDCus = ps.executeQuery();
            vecIDCus = new Vector();
            vecIDCus.add("Khách vãng lai");
            while (rsIDCus.next()) {
                vecIDCus.add(rsIDCus.getString("IDCus"));
            }
            JTextField text = (JTextField) cbIDCus.getEditor().getEditorComponent();
            text.setText("");
            text.addKeyListener(new ComboListener(cbIDCus, vecIDCus));
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

        jPanel1 = new javax.swing.JPanel();
        txtDate = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblOrder = new javax.swing.JTable();
        cbIDProduct = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cbNameEmp = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        cbPromotions = new javax.swing.JComboBox();
        cbIDOrder = new javax.swing.JComboBox();
        btnSearch = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        btnReset = new javax.swing.JButton();
        txtTime = new javax.swing.JTextField();
        cbIDCus = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lbTimeError = new javax.swing.JLabel();
        lbDateError = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        tblOrder.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblOrder.setAutoscrolls(false);
        tblOrder.setFocusable(false);
        tblOrder.setOpaque(false);
        tblOrder.setRequestFocusEnabled(false);
        tblOrder.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblOrder);

        cbIDProduct.setEditable(true);

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 255));
        jLabel10.setText("Thông tin tìm kiếm:");

        jLabel4.setText("Thời gian:");

        cbNameEmp.setEditable(true);

        jLabel5.setText("Nhân viên:");

        cbPromotions.setEditable(true);

        cbIDOrder.setEditable(true);

        btnSearch.setText("Tìm kiếm");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        jLabel3.setText("Mã khách hàng:");

        btnReset.setText("Làm Mới");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        cbIDCus.setEditable(true);

        jLabel6.setText("Ngày:");

        jLabel1.setText("Mã đơn hàng:");

        jLabel2.setText("Mã sản phẩm:");

        jLabel7.setText("Chương trình khuyến mãi:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        jLabel8.setText("Quản Lý Hóa Đơn");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(37, 37, 37)
                                .addComponent(cbNameEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(90, 90, 90)
                                        .addComponent(cbIDOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel1)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(21, 21, 21)
                                        .addComponent(cbIDProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(26, 26, 26)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(cbPromotions, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(15, 15, 15))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(cbIDCus, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)))))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lbTimeError, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtTime, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(1, 1, 1)
                                        .addComponent(btnReset))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addGap(38, 38, 38)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(lbDateError, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txtDate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE))))
                                .addContainerGap())))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(755, 758, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jSeparator1)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel8)
                .addGap(3, 3, 3)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(cbIDOrder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(jLabel1))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(jLabel7))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jLabel4))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(txtTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(3, 3, 3)
                        .addComponent(lbTimeError, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbDateError, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnSearch)
                                    .addComponent(btnReset)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(cbIDProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(19, 19, 19)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(cbNameEmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(cbPromotions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbIDCus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
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
    }// </editor-fold>//GEN-END:initComponents

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed

        try {

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String connectionUrl = "jdbc:sqlserver://localhost;database=TheOne;";
            String dbusername = "sa";
            String password ="01219164372";
            Connection con = DriverManager.getConnection(connectionUrl,dbusername,password);

            PreparedStatement ps;
            ResultSet rs, rsIDOrder, rsIDProduct, rsEmp, rsPromotions, rsIDCus, rsSearch1, rsSearch2;;

            tblModel.getDataVector().removeAllElements();
            String s1 = (String) cbIDOrder.getSelectedItem();
            String s2 = (String) cbIDProduct.getSelectedItem();
            String s3 = (String) cbIDCus.getSelectedItem();
            String s4 = (String) cbPromotions.getSelectedItem();
            String s5 = txtDate.getText().trim();
            String s6 = txtTime.getText().trim();
            String s7 = (String) cbNameEmp.getSelectedItem();
            if (!txtDate.getText().trim().equals("")) {
                while (true) {
                    if (!txtDate.getText().trim().matches("([0-9]{0,2}/)?([0-9]{0,2}/)?[0-9]{4}")) {
                        lbDateError.setText("Ngày có dạng: dd/MM/yyyy, MM/yyyy hoặc yyyy.");
                        lbDateError.setForeground(Color.red);
                        tblOrder.removeAll();
                        return;
                    } else {
                        lbDateError.setText("");
                        break;
                    }
                }
            } else {
                lbDateError.setText("");
            }
            if (!txtTime.getText().trim().equals("")) {
                while (true) {
                    if (!txtTime.getText().trim().matches("[0-9]{0,2}:?[0-9]{0,2}?")) {
                        lbTimeError.setText("Thời gian có dạng: hh hoặc hh:mm.");
                        lbTimeError.setForeground(Color.red);
                        tblOrder.removeAll();
                        return;
                    } else {
                        lbTimeError.setText("");
                        break;
                    }
                }
            } else {
                lbTimeError.setText("");
            }
            String ss = "select * from OrderDetails join [Order] on OrderDetails.IDOrder=[Order].IDOrder where OrderDetails.IDOrder LIKE ? and OrderDetails.IDProduct LIKE ? and OrderDetails.CusName LIKE ? and OrderDetails.NamePromo LIKE ? and [Order].DateOrder LIKE ? and [Order].TimeOrder LIKE ? and [Order].UsernameEmp LIKE ? Order by OrderDetails.IDOrder DESC";
            try {
                ps = con.prepareStatement(ss);
                if (cbIDOrder.getSelectedIndex() == -1) {
                    ps.setString(1, "%");
                } else {
                    ps.setString(1, s1);
                }
                if (cbIDProduct.getSelectedIndex() == -1) {
                    ps.setString(2, "%");
                } else {
                    ps.setString(2, s2);
                }
                if (cbIDCus.getSelectedIndex() == -1) {
                    ps.setString(3, "%");
                } else {
                    ps.setString(3, s3);
                }
                if (cbPromotions.getSelectedIndex() == -1) {
                    ps.setString(4, "%");
                } else {
                    ps.setString(4, s4);
                }
                if (txtDate.getText().trim().equals("")) {
                    ps.setString(5, "%");
                } else {
                    ps.setString(5, "%" + s5);
                }
                if (txtTime.getText().trim().equals("")) {
                    ps.setString(6, "%");
                } else {
                    ps.setString(6, s6 + "%");
                }
                if (cbNameEmp.getSelectedIndex() == -1) {
                    ps.setString(7, "%");
                } else {
                    ps.setString(7, s7);
                }
                rsSearch1 = ps.executeQuery();
                if (rsSearch1.next()) {
                    ps = con.prepareStatement(ss);
                    if (cbIDOrder.getSelectedIndex() == -1) {
                        ps.setString(1, "%");
                    } else {
                        ps.setString(1, s1);
                    }
                    if (cbIDProduct.getSelectedIndex() == -1) {
                        ps.setString(2, "%");
                    } else {
                        ps.setString(2, s2);
                    }
                    if (cbIDCus.getSelectedIndex() == -1) {
                        ps.setString(3, "%");
                    } else {
                        ps.setString(3, s3);
                    }
                    if (cbPromotions.getSelectedIndex() == -1) {
                        ps.setString(4, "%");
                    } else {
                        ps.setString(4, s4);
                    }
                    if (txtDate.getText().trim().equals("")) {
                        ps.setString(5, "%");
                    } else {
                        ps.setString(5, "%" + s5);
                    }
                    if (txtTime.getText().trim().equals("")) {
                        ps.setString(6, "%");
                    } else {
                        ps.setString(6, s6 + "%");
                    }
                    if (cbNameEmp.getSelectedIndex() == -1) {
                        ps.setString(7, "%");
                    } else {
                        ps.setString(7, s7);
                    }
                    rsSearch2 = ps.executeQuery();
                    while (rsSearch2.next()) {
                        rowSearch = new Vector();
                        rowSearch.add(rsSearch2.getString("IDOrder"));
                        rowSearch.add(rsSearch2.getString("IDProduct"));
                        rowSearch.add(rsSearch2.getString("CusName"));
                        rowSearch.add(rsSearch2.getString("Quantity"));
                        rowSearch.add(rsSearch2.getString("NamePromo"));
                        rowSearch.add(rsSearch2.getString("TimeOrder"));
                        rowSearch.add(rsSearch2.getString("DateOrder"));
                        rowSearch.add(rsSearch2.getString("UsernameEmp"));
                        tblModel.addRow(rowSearch);
                    }
                    tblOrder.setModel(tblModel);
                } else {
                    JOptionPane.showMessageDialog(null, "Không tìm thấy dữ liệu.");

                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Lỗi:: Không thể kết nối đến máy chủ");
            }
            con.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            e.printStackTrace();
        }

    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        ReloadTable();
        cbIDCus.setSelectedIndex(-1);
        cbIDOrder.setSelectedIndex(-1);
        cbIDProduct.setSelectedIndex(-1);
        cbNameEmp.setSelectedIndex(-1);
        cbPromotions.setSelectedIndex(-1);
        txtTime.setText("");
        txtDate.setText("");
        lbTimeError.setText("");
        lbDateError.setText("");
    }//GEN-LAST:event_btnResetActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSearch;
    private javax.swing.JComboBox cbIDCus;
    private javax.swing.JComboBox cbIDOrder;
    private javax.swing.JComboBox cbIDProduct;
    private javax.swing.JComboBox cbNameEmp;
    private javax.swing.JComboBox cbPromotions;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lbDateError;
    private javax.swing.JLabel lbTimeError;
    private javax.swing.JTable tblOrder;
    private javax.swing.JTextField txtDate;
    private javax.swing.JTextField txtTime;
    // End of variables declaration//GEN-END:variables
}
