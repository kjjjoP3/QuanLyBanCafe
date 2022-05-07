/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import dao.LoaiSanPhamDao;
import dao.NhaVienDao;
import dao.SanPhamDao;
import helpers.MessageDialogHelper;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.LoaiSanPham;
import model.NhanVien;
import model.sanPham;

/**
 *
 * @author ACER NITRO 5
 */
public class sanPhamFr extends javax.swing.JFrame {
private DefaultTableModel tableModel;
private DefaultTableModel tableModel1;
    private byte[] personalImage;
    Vector rowSP, rowLoaiSP, vecTK, vecTen, rowTen;
    /**
     * Creates new form sanPhamFr
     */
    public sanPhamFr() {
        initComponents();
        setLocationRelativeTo(null);
        
        initTableLoaiSanPham();
        loadDataToTableLoaiSanPham();
        
        initTableSanPham();
        loadDataToTableSanPham();
        
        cbSizeType.setSelectedIndex(-1);
        cbNameType.setSelectedIndex(-1);//re set combobox
        cbKichCo.setSelectedIndex(-1);
        
        btnTimKiem.setVisible(false);
        cbTen.setVisible(false);
        PanelTimKiem.setVisible(false);
       //cbbox sanpham
       try {
            
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
        String connectionUrl = "jdbc:sqlserver://localhost;database=TheOne;";
        String dbusername = "sa";
        String password ="01219164372";
        Connection con = DriverManager.getConnection(connectionUrl,dbusername,password);
        
        String sql = "select DISTINCT TypeName from  ProductType";
        PreparedStatement pstmt = con.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        
        cbNameType.removeAllItems();
        
        //re set combobox
        
        while (rs.next()){    
            cbNameType.setSelectedIndex(-1);
            cbNameType.addItem(rs.getString(1));
        }
        rs.close();
        pstmt.close();
        con.close();
        
        
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            e.printStackTrace();
        }
    }
    
    //loai san pham
    private void initTableLoaiSanPham() {
        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(new String[]{"ID", "Loại", "Kích Cỡ"});
        tblLoaiSanPham.setModel(tableModel);
    }
    private void loadDataToTableLoaiSanPham() {
        try {
           
            LoaiSanPhamDao dao = new LoaiSanPhamDao();
            List<LoaiSanPham> list = dao.findAll();
            tableModel.setRowCount(0);
            for (LoaiSanPham it : list) {
                tableModel.addRow(new Object[]{
                    it.getIDProductType(), it.getTenLoaiSanPham(), it.getKichThuocSp(), 
                });
            }
            tableModel.fireTableDataChanged();
        } catch (Exception e) {
            e.printStackTrace();
            MessageDialogHelper.showErrorDialog(this, e.getMessage(), "Lỗi");
        }
    }
    
    
    //san Pham
    
    private void initTableSanPham() {
        tableModel1 = new DefaultTableModel();
        tableModel1.setColumnIdentifiers(new String[]{"ID", "Tên", "Loại","Giá","Kích Cỡ"});
        tblSanPham.setModel(tableModel1);
    }
    private void loadDataToTableSanPham() {
        try {
           
            SanPhamDao dao = new SanPhamDao();
            List<sanPham> list = dao.findAll();
            tableModel1.setRowCount(0);
            for (sanPham it : list) {
                tableModel1.addRow(new Object[]{
                    it.getIDProduct(),it.getTenSanPham(),it.getLoaiSpID(),it.getGiaBan(), it.getKichThuocSp(), 
                });
            }
            tableModel1.fireTableDataChanged();
        } catch (Exception e) {
            e.printStackTrace();
            MessageDialogHelper.showErrorDialog(this, e.getMessage(), "Lỗi");
        }
        
        
    }
    
    private void clearForm(){
        txtIDProductType.setText("");
        txtNameType.setText("");
        cbSizeType.setSelectedIndex(-1);//re set combobox
        
    }
    
    private void clearFormSanPham(){
        txtIDProduct.setText("");
        txtNameproduct.setText("");
        txtPrice.setText("");
        cbNameType.setSelectedIndex(-1);//re set combobox
        cbKichCo.setSelectedIndex(-1);//re set combobox
        loadDataToTableSanPham();
        cbTen.setSelectedIndex(-1);
        cbChonTimKiem.setSelectedIndex(0);
        try {
            
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
        String connectionUrl = "jdbc:sqlserver://localhost;database=TheOne;";
        String dbusername = "sa";
        String password ="01219164372";
        Connection con = DriverManager.getConnection(connectionUrl,dbusername,password);
        
        String sql = "select DISTINCT TypeName from  ProductType";
        PreparedStatement pstmt = con.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        
        cbNameType.removeAllItems();
        
        //re set combobox
        
        while (rs.next()){    
            cbNameType.setSelectedIndex(-1);
            cbNameType.addItem(rs.getString(1));
        }
        rs.close();
        pstmt.close();
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
        jPanel2 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txtIDProductType = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtNameType = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        cbSizeType = new javax.swing.JComboBox();
        btnTaoMoi = new javax.swing.JButton();
        btnThemLoai = new javax.swing.JButton();
        btnSuaLoai = new javax.swing.JButton();
        btnXoaLoai = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblLoaiSanPham = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtIDProduct = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtNameproduct = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cbNameType = new javax.swing.JComboBox();
        txtPrice = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cbKichCo = new javax.swing.JComboBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        jButton5 = new javax.swing.JButton();
        btnThemSanPham = new javax.swing.JButton();
        btnSuaSanPham = new javax.swing.JButton();
        btnXoaSanPham = new javax.swing.JButton();
        cbChonTimKiem = new javax.swing.JComboBox();
        PanelTimKiem = new javax.swing.JPanel();
        cbTen = new javax.swing.JComboBox();
        lbGiaTu = new javax.swing.JLabel();
        lbDen = new javax.swing.JLabel();
        txtGiaTu = new javax.swing.JTextField();
        txtDen = new javax.swing.JTextField();
        lbNhom = new javax.swing.JLabel();
        lbLoai = new javax.swing.JLabel();
        cbLoaiTK = new javax.swing.JComboBox();
        cbKichThuoc = new javax.swing.JComboBox();
        lbVND = new javax.swing.JLabel();
        lbVND1 = new javax.swing.JLabel();
        btnTimKiem = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jTabbedPane1.setToolTipText("");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("ID Loại Sản Phẩm :");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Tên Loại Sản Phẩm :");

        txtNameType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNameTypeActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Kích Cỡ :");

        cbSizeType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Nhỏ", "Vừa", "Lớn" }));

        btnTaoMoi.setText("Tạo Mới");
        btnTaoMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoMoiActionPerformed(evt);
            }
        });

        btnThemLoai.setText("Thêm");
        btnThemLoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemLoaiActionPerformed(evt);
            }
        });

        btnSuaLoai.setText("Sửa ");
        btnSuaLoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaLoaiActionPerformed(evt);
            }
        });

        btnXoaLoai.setText("Xóa");
        btnXoaLoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaLoaiActionPerformed(evt);
            }
        });

        tblLoaiSanPham.setModel(new javax.swing.table.DefaultTableModel(
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
        tblLoaiSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblLoaiSanPhamMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblLoaiSanPham);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(167, 167, 167)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnTaoMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnThemLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSuaLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnXoaLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel8))
                        .addGap(61, 61, 61)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtIDProductType)
                            .addComponent(txtNameType)
                            .addComponent(cbSizeType, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(290, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtIDProductType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNameType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(28, 28, 28)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(cbSizeType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTaoMoi)
                    .addComponent(btnThemLoai)
                    .addComponent(btnSuaLoai)
                    .addComponent(btnXoaLoai))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 303, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Loại Sản Phẩm", jPanel3);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("ID Sản Phẩm :");

        txtIDProduct.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Tên Sản Phẩm :");

        txtNameproduct.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Loại Sản Phẩm :");

        cbNameType.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbNameType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        txtPrice.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Giá Sản Phẩm :");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Kích cỡ :");

        cbKichCo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbKichCo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Lớn", "Vừa", "Nhỏ" }));

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
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
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblSanPham);

        jButton5.setText("Tạo Mới");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        btnThemSanPham.setText("Thêm");
        btnThemSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemSanPhamActionPerformed(evt);
            }
        });

        btnSuaSanPham.setText("Sửa");
        btnSuaSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaSanPhamActionPerformed(evt);
            }
        });

        btnXoaSanPham.setText("Xóa");
        btnXoaSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaSanPhamActionPerformed(evt);
            }
        });

        cbChonTimKiem.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "---Tìm kiếm theo---", "Tên", "Giá", "Nhóm" }));
        cbChonTimKiem.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        cbChonTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbChonTimKiemActionPerformed(evt);
            }
        });

        cbTen.setEditable(true);
        cbTen.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbTen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTenActionPerformed(evt);
            }
        });

        lbGiaTu.setText("Giá từ:");

        lbDen.setText("Đến :");

        txtGiaTu.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        txtDen.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        lbNhom.setText("Nhóm:");

        lbLoai.setText("Kích Thước:");

        cbLoaiTK.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbKichThuoc.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Nhỏ", "Vừa", "Lớn" }));

        lbVND.setText("VNĐ");

        lbVND1.setText("VNĐ");

        javax.swing.GroupLayout PanelTimKiemLayout = new javax.swing.GroupLayout(PanelTimKiem);
        PanelTimKiem.setLayout(PanelTimKiemLayout);
        PanelTimKiemLayout.setHorizontalGroup(
            PanelTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelTimKiemLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbTen, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(PanelTimKiemLayout.createSequentialGroup()
                        .addGroup(PanelTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbGiaTu)
                            .addComponent(lbDen))
                        .addGap(18, 18, 18)
                        .addGroup(PanelTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtGiaTu, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                            .addComponent(txtDen))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PanelTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbVND1)
                            .addComponent(lbVND))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(PanelTimKiemLayout.createSequentialGroup()
                        .addComponent(lbLoai)
                        .addGap(28, 28, 28)
                        .addComponent(cbKichThuoc, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(PanelTimKiemLayout.createSequentialGroup()
                        .addComponent(lbNhom)
                        .addGap(18, 18, 18)
                        .addComponent(cbLoaiTK, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        PanelTimKiemLayout.setVerticalGroup(
            PanelTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelTimKiemLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtGiaTu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbVND)
                    .addComponent(lbGiaTu))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbDen)
                    .addComponent(txtDen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbVND1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PanelTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbNhom)
                    .addComponent(cbLoaiTK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbKichThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbLoai))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        btnTimKiem.setText("Tìm kiếm");
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 850, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnThemSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSuaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnXoaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel3)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2)))
                            .addComponent(jLabel7)
                            .addComponent(jLabel9))
                        .addGap(53, 53, 53)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbKichCo, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtIDProduct)
                                .addComponent(txtNameproduct)
                                .addComponent(cbNameType, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(PanelTimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbChonTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimKiem))
                .addGap(100, 100, 100))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtIDProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtNameproduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(cbNameType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(cbKichCo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton5)
                            .addComponent(btnThemSanPham)
                            .addComponent(btnSuaSanPham)
                            .addComponent(btnXoaSanPham)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(cbChonTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(PanelTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnTimKiem)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Sản Phẩm", jPanel4);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemLoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemLoaiActionPerformed
        // them Loai Sản Phẩm
        int line = tblLoaiSanPham.getRowCount();
        if (MessageDialogHelper.showConFirmDialog(this, "Bạn Có Muốn Thêm Loại Sản Phẩm Không", "Hỏi") != JOptionPane.YES_OPTION) {
                return;
            }
        try {
            
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
        String connectionUrl = "jdbc:sqlserver://localhost;database=TheOne;";
        String dbusername = "sa";
        String password ="01219164372";
        Connection con = DriverManager.getConnection(connectionUrl,dbusername,password);
        
//        String sql = " select * from";
        String sql2 = "select * from ProductType";
        PreparedStatement pstmt = con.prepareStatement(sql2);
        ResultSet rs = pstmt.executeQuery();
        while (true) {
            
                    
                    
                    if (rs.next()) {
                        String idprotype = rs.getString(1);

                    }
                    for (int i = 0; i < line; i++) {
                        if (txtNameType.getText().equals(tblLoaiSanPham.getValueAt(i, 1))
                            && (cbSizeType.getSelectedItem().equals(tblLoaiSanPham.getValueAt(i, 2)))) {
                            JOptionPane.showMessageDialog(this, "Loại sản phẩm đã tồn tại, Hoặc kích thước đã tồn tại");
                            txtNameType.grabFocus();
                            return;
                        }
                    }

                    if (txtNameType.getText().trim().equals("")) {
                        JOptionPane.showMessageDialog(this, "Tên không được để trống");
                        txtNameType.grabFocus();
                        return;
                    } else {
                        break;
                    }
                }
        
        String sql = "insert into ProductType values(?,?,?)";
                pstmt = con.prepareStatement(sql);
                pstmt.setString(1, txtIDProductType.getText());
                pstmt.setString(2, txtNameType.getText());
                pstmt.setString(3, (String) cbSizeType.getSelectedItem());//cbBox
                pstmt.executeUpdate();
                tableModel.getDataVector().removeAllElements();
                loadDataToTableLoaiSanPham();
                loadDataToTableSanPham();
                
                JOptionPane.showMessageDialog(null, "Thêm loại sản phẩm thành công");
        
        rs.close();
        pstmt.close();
        con.close();
        
        
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnThemLoaiActionPerformed

    private void tblLoaiSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblLoaiSanPhamMouseClicked
        // hien thi len bang Loai Sản Phẩm
        int line1 = tblLoaiSanPham.getSelectedRow();
        String ID1 = (String) tblLoaiSanPham.getValueAt(line1, 0);
        String Name1 = (String) tblLoaiSanPham.getValueAt(line1, 1);
        String Size = (String) tblLoaiSanPham.getValueAt(line1, 2);
        //gan du lieu vao textfile
        txtIDProductType.setText(ID1);
        txtNameType.setText(Name1);
        cbSizeType.setSelectedItem(Size);
    }//GEN-LAST:event_tblLoaiSanPhamMouseClicked

    private void btnSuaLoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaLoaiActionPerformed
        // Sua Loai Sản Phẩm
        int line = tblLoaiSanPham.getRowCount();
       if (MessageDialogHelper.showConFirmDialog(this, "Bạn Có Muốn Sửa Loại Sản Phẩm Không", "Hỏi") != JOptionPane.YES_OPTION) {
                return;
            }
        
        try {
            
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
        String connectionUrl = "jdbc:sqlserver://localhost;database=TheOne;";
        String dbusername = "sa";
        String password ="01219164372";
        Connection con = DriverManager.getConnection(connectionUrl,dbusername,password);
        
        String sql = "update ProductType set TypeName=?, Size=? where IDType=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
//        ResultSet rs = pstmt.executeQuery();
        
//        pstmt = con.prepareStatement(sql);
//                pstmt.setString(1, txtNameType.getText());
//                pstmt.setString(2, (String) cbSizeType.getSelectedItem());
//                pstmt.setString(3, txtIDProductType.getText());
//                pstmt.executeUpdate();
//                loadDataToTableLoaiSanPham();
//                
//                JOptionPane.showMessageDialog(null, "Sửa loại sản phẩm thành công");
//                loadDataToTableLoaiSanPham();
        
        if (JOptionPane.YES_OPTION == 0) {
            try {
                for (int i = 0; i < line; i++) {
                        if (txtNameType.getText().equals(tblLoaiSanPham.getValueAt(i, 1))
                            && (cbSizeType.getSelectedItem().equals(tblLoaiSanPham.getValueAt(i, 2)))) {
                            JOptionPane.showMessageDialog(this, "Loại sản phẩm đã tồn tại, Hoặc kích thước đã tồn tại");
                            txtNameType.grabFocus();
                            return;
                        }
                    }
                
                        pstmt = con.prepareStatement(sql);
                pstmt.setString(1, txtNameType.getText());
                pstmt.setString(2, (String) cbSizeType.getSelectedItem());
                pstmt.setString(3, txtIDProductType.getText());
                pstmt.executeUpdate();
                loadDataToTableLoaiSanPham();
                JOptionPane.showMessageDialog(null, "Sửa loại sản phẩm thành công");
                tableModel.getDataVector().removeAllElements();
                loadDataToTableLoaiSanPham();
                loadDataToTableSanPham();
            } catch (Exception e) {
            }
        } else {
            JOptionPane.showMessageDialog(null, "Sửa loại sản phẩm không thành công");
        }

//        rs.close();
        pstmt.close();
        con.close();
        
        
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnSuaLoaiActionPerformed

    private void txtNameTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNameTypeActionPerformed
        // Rac
    }//GEN-LAST:event_txtNameTypeActionPerformed

    private void btnXoaLoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaLoaiActionPerformed
        // Xoa Loai Sản Phẩm
        try {
            
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
        String connectionUrl = "jdbc:sqlserver://localhost;database=TheOne;";
        String dbusername = "sa";
        String password ="01219164372";
        Connection con = DriverManager.getConnection(connectionUrl,dbusername,password);
        
        String xoa = "select * from Product where IDType=?";
        PreparedStatement pstmt = con.prepareStatement(xoa);
        ResultSet rs;
        
        try {
            pstmt = con.prepareStatement(xoa);
            pstmt.setString(1, txtIDProductType.getText());
            rs = pstmt.executeQuery();
            if (!rs.next()) {
                if (MessageDialogHelper.showConFirmDialog(this, "Bạn Có Muốn Xóa Loại Sản Phẩm Không", "Hỏi") != JOptionPane.YES_OPTION) {
                return;
            }
                if (JOptionPane.YES_OPTION == 0) {
                    try {
                        String sql = "Delete ProductType where IDType=?";
                        pstmt = con.prepareStatement(sql);
                        pstmt.setString(1, txtIDProductType.getText());
                        pstmt.executeUpdate();
                        tableModel.getDataVector().removeAllElements();
                        loadDataToTableLoaiSanPham();
                        loadDataToTableSanPham();
                        JOptionPane.showMessageDialog(null, "Xóa loại sản phẩm thành công");
                    } catch (Exception e) {
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Xóa loại sản phẩm không thành công");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Sản phẩm còn tồn tại, không thể xóa");
            }
        } catch (SQLException ex) {
        }
        loadDataToTableLoaiSanPham();
        
        pstmt.close();
        con.close();
        
        
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnXoaLoaiActionPerformed

    private void btnTaoMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoMoiActionPerformed
        // Tao Moi Loai San Pham
        clearForm();
    }//GEN-LAST:event_btnTaoMoiActionPerformed

    private void btnThemSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemSanPhamActionPerformed
        // Them Sản Phẩm
        try {     
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
        String connectionUrl = "jdbc:sqlserver://localhost;database=TheOne;";
        String dbusername = "sa";
        String password ="01219164372";
        Connection con = DriverManager.getConnection(connectionUrl,dbusername,password);
        
        String sql = "select * from ProductType where TypeName=? and Size=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        ResultSet rs;
        
        int line = tableModel1.getRowCount();//diem quet so dong kiem tra trung nhau
        if (MessageDialogHelper.showConFirmDialog(this, "Bạn Có Muốn Thêm Sản Phẩm Không", "Hỏi") != JOptionPane.YES_OPTION) {
                return;
            }
        if (JOptionPane.YES_OPTION == 0) {
            try {
                
                pstmt.setString(1, (String) cbNameType.getSelectedItem());
                pstmt.setString(2, (String) cbKichCo.getSelectedItem());
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    while (true) {
                        for (int i = 0; i < line; i++) {
                            if (txtIDProduct.getText().trim().equals(tableModel1.getValueAt(i, 0))) {
                                JOptionPane.showMessageDialog(this, "ID sản phẩm trùng");
                                txtIDProduct.grabFocus();
                                return;
                            }
                        }

                        if (txtIDProduct.getText().trim().equals("")) {
                            JOptionPane.showMessageDialog(this, "ID không được để trống");
                            txtIDProduct.grabFocus();
                            return;
                        } else {
                            break;
                        }
                    }

                    while (true) {
                        if (txtNameproduct.getText().trim().equals("")) {
                            JOptionPane.showMessageDialog(this, "Tên không được để trống");
                            txtNameproduct.grabFocus();
                            return;
                        } else {
                            break;
                        }
                    }
                    while (true) {
                        if (txtPrice.getText().trim().equals("")) {
                            JOptionPane.showMessageDialog(this, "Giá không được để trống");
                            txtPrice.grabFocus();
                            return;
                        }  else {
                            break;
                        }
                    }
                    int line1 = tblSanPham.getRowCount();
                    while (true) {
                        String sql3 = "select * from ProductType where TypeName=? and Size=?";
                        String Loaisp, Size, IDType;
                        pstmt = con.prepareStatement(sql3);
                        pstmt.setString(1, (String) cbNameType.getSelectedItem());
                        pstmt.setString(2, (String) cbKichCo.getSelectedItem());
                        rs = pstmt.executeQuery();
                        if (rs.next()) {
                            IDType = rs.getString(1);
                            for (int i = 0; i < line1; i++) {
                                if (txtNameproduct.getText().equals(tblSanPham.getValueAt(i, 1)) && tblSanPham.getValueAt(i, 2).equals(IDType) && cbKichCo.getSelectedItem().equals(tblSanPham.getValueAt(i, 4))) {
                                    JOptionPane.showMessageDialog(null, "Sản phẩm Đã tồn tại");
                                    return;
                                }
                            }
                            break;
                        }
                    }

                    String sqlrt = "insert into Product values(?,?,?,?)";
                    pstmt = con.prepareStatement(sqlrt);
                    pstmt.setString(1, txtIDProduct.getText());
                    pstmt.setString(2, txtNameproduct.getText());
                    pstmt.setString(3, rs.getString("IDType"));//cbBox
                    pstmt.setInt(4, Integer.parseInt(txtPrice.getText()));//kieu int
                    pstmt.executeUpdate();
                    tableModel1.getDataVector().removeAllElements();
                    loadDataToTableSanPham();
                    loadDataToTableLoaiSanPham();
                    cbChonTimKiemActionPerformed(evt);
                    
                    JOptionPane.showMessageDialog(null, "Thêm sản phẩm thành công");
                } else {
                    JOptionPane.showMessageDialog(null, "Không tìm thấy nhóm sản phẩm.");
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
        
        
        
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnThemSanPhamActionPerformed

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        // hien thi thi san pham
        try {
            
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
        String connectionUrl = "jdbc:sqlserver://localhost;database=TheOne;";
        String dbusername = "sa";
        String password ="01219164372";
        Connection con = DriverManager.getConnection(connectionUrl,dbusername,password);
        
        String sql = "select * from ProductType where IDType=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        ResultSet rs ;
        
        String DepName = null;
        
        int line = tblSanPham.getSelectedRow();
        String ID = (String.valueOf(tableModel1.getValueAt(line, 0))) ;
        String Name = (String) tableModel1.getValueAt(line, 1);
        String Type = (String) tableModel1.getValueAt(line, 2);
        String Price = String.valueOf(tableModel1.getValueAt(line, 3)) ;
        String Size = (String) tableModel1.getValueAt(line, 4);
        //gan du lieu vao textfile
        txtIDProduct.setText(ID);
        txtNameproduct.setText(Name);
        try {
           
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, Type);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                DepName = rs.getString(2);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        cbNameType.setSelectedItem(DepName);
        //        cbNameType.setSelectedItem(Type);
        txtPrice.setText(Price);
        cbKichCo.setSelectedItem(Size);
        
        
        
        pstmt.close();
        con.close();
        
        
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void btnSuaSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaSanPhamActionPerformed
        // Sua San Pham
        try {
            
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
        String connectionUrl = "jdbc:sqlserver://localhost;database=TheOne;";
        String dbusername = "sa";
        String password ="01219164372";
        Connection con = DriverManager.getConnection(connectionUrl,dbusername,password);
        
        String sql2 = "select * from ProductType where TypeName=? and Size=?";
        PreparedStatement pstmt;
        ResultSet rs;
        
        if (MessageDialogHelper.showConFirmDialog(this, "Bạn Có Muốn Sửa Sản Phẩm Không", "Hỏi") != JOptionPane.YES_OPTION) {
                return;
            }
        if (JOptionPane.YES_OPTION == 0) {

            try {
                pstmt = con.prepareStatement(sql2);
                pstmt.setString(1, (String) cbNameType.getSelectedItem());
                pstmt.setString(2, (String) cbKichCo.getSelectedItem());
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    String sql = "update Product set ProductName=?, IDType=?, Price=? where IDProduct=?";
                    pstmt = con.prepareStatement(sql);
                    pstmt.setString(1, txtNameproduct.getText());
                    pstmt.setString(2, rs.getString(1));//cbBox
                    pstmt.setInt(3, Integer.parseInt(txtPrice.getText()));//kieu int
                    pstmt.setString(4, txtIDProduct.getText());
                    pstmt.executeUpdate();
                    tableModel1.getDataVector().removeAllElements();
                    loadDataToTableSanPham();
                    cbChonTimKiemActionPerformed(evt);
                    
                    JOptionPane.showMessageDialog(null, "Sửa sản phẩm thành công");
                } else {
                    JOptionPane.showMessageDialog(null, "Không tìm thấy nhóm sản phẩm.");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
        
        
        
       
        con.close();
        
        
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnSuaSanPhamActionPerformed

    private void btnXoaSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaSanPhamActionPerformed
        // xoa San Pham
        try {
            
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
        String connectionUrl = "jdbc:sqlserver://localhost;database=TheOne;";
        String dbusername = "sa";
        String password ="01219164372";
        Connection con = DriverManager.getConnection(connectionUrl,dbusername,password);
        
        String sql1 = "select * from OrderDetails where IDProduct=?";
        PreparedStatement pstmt;
        ResultSet rs;
        
        if (MessageDialogHelper.showConFirmDialog(this, "Bạn Có Muốn Xóa Sản Phẩm Không", "Hỏi") != JOptionPane.YES_OPTION) {
                return;
            }
        if (JOptionPane.YES_OPTION == 0) {
            try {
                
                pstmt = con.prepareStatement(sql1);
                pstmt.setString(1, txtIDProduct.getText());
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "Sản phẩm đã có đơn hàng, không thể xóa sản phẩm.");
                } else {
                    try {
                        String sql = "Delete Product where IDProduct=?";
                        pstmt = con.prepareStatement(sql);
                        pstmt.setString(1, txtIDProduct.getText());
                        pstmt.executeUpdate();
                        tableModel1.getDataVector().removeAllElements();
                        loadDataToTableSanPham();
                        cbChonTimKiemActionPerformed(evt);
                        JOptionPane.showMessageDialog(null, "Xóa sản phẩm thành công.");
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, e);
                    }
                }
            } catch (SQLException e) {
            }

        }
        
        
        con.close();
        
        
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnXoaSanPhamActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        //Lam Mới
        clearFormSanPham();
        
    }//GEN-LAST:event_jButton5ActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // cua so luon load:
    }//GEN-LAST:event_formWindowOpened

    private void cbChonTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbChonTimKiemActionPerformed
//tim kiem



try {
            
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
        String connectionUrl = "jdbc:sqlserver://localhost;database=TheOne";
        String dbusername = "sa";
        String password ="01219164372";
        Connection con = DriverManager.getConnection(connectionUrl,dbusername,password);
        
        String url = "Select DISTINCT ProductName from Product";
        PreparedStatement pstmt;
        ResultSet rs;
        
                PanelTimKiem.setVisible(true);
        if (cbChonTimKiem.getSelectedIndex() == 0) {
            cbTen.setVisible(false);
            txtGiaTu.setVisible(false);
            txtDen.setVisible(false);
            cbLoaiTK.setVisible(false);
            cbKichThuoc.setVisible(false);
            btnTimKiem.setVisible(false);
            lbDen.setVisible(false);
            lbGiaTu.setVisible(false);
            lbLoai.setVisible(false);
            lbNhom.setVisible(false);
            lbVND1.setVisible(false);
            lbVND.setVisible(false);

        } else if (cbChonTimKiem.getSelectedItem().equals("Tên")) {
            cbTen.setVisible(true);
            txtGiaTu.setVisible(false);
            txtDen.setVisible(false);
            cbLoaiTK.setVisible(false);
            cbKichThuoc.setVisible(false);
            btnTimKiem.setVisible(true);
            lbDen.setVisible(false);
            lbGiaTu.setVisible(false);
            lbLoai.setVisible(false);
            lbNhom.setVisible(false);
            lbVND1.setVisible(false);
            lbVND.setVisible(false);

            cbTen.removeAllItems();
            try {
                pstmt = con.prepareStatement(url);
                rs = pstmt.executeQuery();
                vecTen = new Vector();
                while (rs.next()) {
                    vecTen.add(rs.getString("ProductName"));
                }
                JTextField text = (JTextField) cbTen.getEditor().getEditorComponent();
                text.setText("");
                text.addKeyListener(new ComboListener(cbTen, vecTen));
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Lỗi 101:: Không thể kết nối đến máy chủ");
            }
        } else if (cbChonTimKiem.getSelectedItem().equals("Giá")) {
            txtGiaTu.setVisible(true);
            txtDen.setVisible(true);
            cbTen.setVisible(false);
            cbLoaiTK.setVisible(false);
            cbKichThuoc.setVisible(false);
            cbTen.setVisible(false);
            btnTimKiem.setVisible(true);

            lbDen.setVisible(true);
            lbGiaTu.setVisible(true);
            lbLoai.setVisible(false);
            lbNhom.setVisible(false);
            lbVND1.setVisible(true);
            lbVND.setVisible(true);
        } else if (cbChonTimKiem.getSelectedItem().equals("Nhóm")) {
            txtGiaTu.setVisible(false);
            txtDen.setVisible(false);
            cbTen.setVisible(false);
            cbLoaiTK.setVisible(true);
            cbKichThuoc.setVisible(true);
            btnTimKiem.setVisible(true);
            lbDen.setVisible(false);
            lbGiaTu.setVisible(false);
            lbLoai.setVisible(true);
            lbNhom.setVisible(true);
            lbVND1.setVisible(false);
            lbVND.setVisible(false);

            cbLoaiTK.removeAllItems();
            try {
                String sql = "select Distinct TypeName from ProductType";
                pstmt = con.prepareStatement(sql);
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    cbLoaiTK.addItem(rs.getString(1));
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
        
        
        
        con.close();
        
        
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            e.printStackTrace();
        }




    }//GEN-LAST:event_cbChonTimKiemActionPerformed

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed


    try {
            
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
        String connectionUrl = "jdbc:sqlserver://localhost;database=TheOne;";
        String dbusername = "sa";
        String password ="01219164372";
        Connection con = DriverManager.getConnection(connectionUrl,dbusername,password);
        
        
        PreparedStatement pstmt;
        ResultSet rs ;
        
                if (cbChonTimKiem.getSelectedItem().equals("Tên")) {
            tableModel1.getDataVector().removeAllElements();
            try {
                pstmt = con.prepareStatement("select * from Product inner join ProductType on Product.IDType=ProductType.IDType where ProductName=?");
                pstmt.setString(1, (String) cbTen.getSelectedItem());
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    pstmt = con.prepareStatement("select * from Product inner join ProductType on Product.IDType=ProductType.IDType where ProductName=?");
                    pstmt.setString(1, (String) cbTen.getSelectedItem());
                    rs = pstmt.executeQuery();
                    while (rs.next()) {
                        rowTen = new Vector();
                        rowTen.add(rs.getString("IDProduct"));
                        rowTen.add(rs.getString("ProductName"));
                        rowTen.add(rs.getString("IDType"));
                        rowTen.add(rs.getString("Price"));
                        rowTen.add(rs.getString("Size"));
                        tableModel1.addRow(rowTen);
                    }
                    tblSanPham.setModel(tableModel1);
                } else {
                    JOptionPane.showMessageDialog(null, "Không tìm được sản phẩm");
                    loadDataToTableSanPham();
                }
            } catch (Exception e) {
            }
        } else if (cbChonTimKiem.getSelectedItem().equals("Giá")) {
            while (true) {
                if (txtGiaTu.getText().trim().equals("")) {
                    JOptionPane.showMessageDialog(this, "Giá không được để trống");
                    txtGiaTu.grabFocus();
                    return;
                }  else {
                    break;
                }
            }
            while (true) {
                if (txtDen.getText().trim().equals("")) {
                    JOptionPane.showMessageDialog(this, "Giá không được để trống");
                    txtDen.grabFocus();
                    return;
                }  else {
                    break;
                }
            }
            tableModel1.getDataVector().removeAllElements();
            try {
                pstmt = con.prepareStatement("select * from Product inner join ProductType on Product.IDType=ProductType.IDType where Price >= ? and Price <= ?");
                pstmt.setInt(1, Integer.parseInt(txtGiaTu.getText().trim()));
                pstmt.setInt(2, Integer.parseInt(txtDen.getText().trim()));
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    pstmt = con.prepareStatement("select * from Product inner join ProductType on Product.IDType=ProductType.IDType where Price >= ? and Price <= ?");
                    pstmt.setInt(1, Integer.parseInt(txtGiaTu.getText().trim()));
                    pstmt.setInt(2, Integer.parseInt(txtDen.getText().trim()));
                    rs = pstmt.executeQuery();
                    while (rs.next()) {
                        rowTen = new Vector();
                        rowTen.add(rs.getString("IDProduct"));
                        rowTen.add(rs.getString("ProductName"));
                        rowTen.add(rs.getString("IDType"));
                        rowTen.add(rs.getString("Price"));
                        rowTen.add(rs.getString("Size"));
                        tableModel1.addRow(rowTen);
                    }
                    tblSanPham.setModel(tableModel1);
                } else {
                    JOptionPane.showMessageDialog(null, "Không tìm được sản phẩm");
                    loadDataToTableSanPham();
                }
            } catch (Exception e) {
            }
        } else if (cbChonTimKiem.getSelectedItem().equals("Nhóm")) {
            tableModel1.getDataVector().removeAllElements();
            try {
                pstmt = con.prepareStatement("select * from Product inner join ProductType on Product.IDType=ProductType.IDType where TypeName= ? and Size= ?");
                pstmt.setString(1, (String) cbLoaiTK.getSelectedItem());
                pstmt.setString(2, (String) cbKichThuoc.getSelectedItem());
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    pstmt = con.prepareStatement("select * from Product inner join ProductType on Product.IDType=ProductType.IDType where TypeName= ? and Size= ?");
                    pstmt.setString(1, (String) cbLoaiTK.getSelectedItem());
                    pstmt.setString(2, (String) cbKichThuoc.getSelectedItem());
                    rs = pstmt.executeQuery();
                    while (rs.next()) {
                        rowTen = new Vector();
                        rowTen.add(rs.getString("IDProduct"));
                        rowTen.add(rs.getString("ProductName"));
                        rowTen.add(rs.getString("IDType"));
                        rowTen.add(rs.getString("Price"));
                        rowTen.add(rs.getString("Size"));
                        tableModel1.addRow(rowTen);
                    }
                    tblSanPham.setModel(tableModel1);
                } else {
                    JOptionPane.showMessageDialog(null, "Không tìm được sản phẩm");
                    loadDataToTableSanPham();
                }
            } catch (Exception e) {
            }
        }
        
        con.close();
        
        
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            e.printStackTrace();
        }

    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void cbTenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTenActionPerformed
        //cbtenSanPh
    }//GEN-LAST:event_cbTenActionPerformed

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
            java.util.logging.Logger.getLogger(sanPhamFr.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(sanPhamFr.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(sanPhamFr.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(sanPhamFr.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new sanPhamFr().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelTimKiem;
    private javax.swing.JButton btnSuaLoai;
    private javax.swing.JButton btnSuaSanPham;
    private javax.swing.JButton btnTaoMoi;
    private javax.swing.JButton btnThemLoai;
    private javax.swing.JButton btnThemSanPham;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnXoaLoai;
    private javax.swing.JButton btnXoaSanPham;
    private javax.swing.JComboBox cbChonTimKiem;
    private javax.swing.JComboBox cbKichCo;
    private javax.swing.JComboBox cbKichThuoc;
    private javax.swing.JComboBox cbLoaiTK;
    private javax.swing.JComboBox cbNameType;
    private javax.swing.JComboBox cbSizeType;
    private javax.swing.JComboBox cbTen;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lbDen;
    private javax.swing.JLabel lbGiaTu;
    private javax.swing.JLabel lbLoai;
    private javax.swing.JLabel lbNhom;
    private javax.swing.JLabel lbVND;
    private javax.swing.JLabel lbVND1;
    private javax.swing.JTable tblLoaiSanPham;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTextField txtDen;
    private javax.swing.JTextField txtGiaTu;
    private javax.swing.JTextField txtIDProduct;
    private javax.swing.JTextField txtIDProductType;
    private javax.swing.JTextField txtNameType;
    private javax.swing.JTextField txtNameproduct;
    private javax.swing.JTextField txtPrice;
    // End of variables declaration//GEN-END:variables
}
