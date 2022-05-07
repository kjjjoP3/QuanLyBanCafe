/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uiNew;

import helpers.MessageDialogHelper;
import helpers.dialogHelper;
import helpers.shareHelper;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import static java.lang.Thread.sleep;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import ui.ComboListener;
import ui.lichsutest;
import ui.thongTinNhanVien;
import ui.frLogin;
/**
 *
 * @author ACER NITRO 5
 */
public class billCafeNew extends javax.swing.JFrame {
Vector vec, rowHis;
    DefaultComboBoxModel cbModel;
    DefaultTableModel tblModel, tblModelHis;
    SimpleDateFormat ft = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
    SimpleDateFormat ftnow = new SimpleDateFormat("yyyy/MM/dd");
    SimpleDateFormat ftNgay = new SimpleDateFormat("dd/MM/yyyy");
    NumberFormat formatter = new DecimalFormat("#,###");
    /**
     * Creates new form billCafeNew
     */
    public billCafeNew(String EmpName) {
        setTitle("Tính Tiền CaFFee");
        ImageIcon img = new ImageIcon("D:\\CAFFEEN4\\src\\main\\resources\\icon\\icons8_room_60px_1.png");
        this.setIconImage(img.getImage());
        initComponents();
        setLocationRelativeTo(null);
        try {
            
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
        String connectionUrl = "jdbc:sqlserver://localhost;database=TheOne;";
        String dbusername = "sa";
        String password ="01219164372";
        Connection con = DriverManager.getConnection(connectionUrl,dbusername,password);
        
        
        PreparedStatement ps;
        ResultSet rs ;
        
        clock();
        
        PanelOnOff(false);
        setText(false);
        txtIDBill.setEnabled(false);
        btnPrint.setEnabled(false);
        txtEmpName.setText(EmpName);
        spQuantity.setValue(1);

        tblModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };
        tblModel.addColumn("Mã");
        tblModel.addColumn("Tên sản phẩm");
        tblModel.addColumn("Nhóm");
        tblModel.addColumn("Kích thước");
        tblModel.addColumn("Đơn giá (VNĐ)");
        tblModel.addColumn("Số lượng (ly)");
        tblModel.addColumn("Thành tiền (VNĐ)");
        tblBill.setModel(tblModel);
        try {
            String url = "Select DISTINCT ProductName from Product Join ProductType on Product.IDType=ProductType.IDType";
            ps = con.prepareStatement(url);
            rs = ps.executeQuery();
            vec = new Vector();
            while (rs.next()) {
                vec.add(rs.getString("ProductName"));
            }
            JTextField text = (JTextField) cbProduct.getEditor().getEditorComponent();
            text.setText("");
            text.addKeyListener(new ComboListener(cbProduct, vec));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi 101:: Không thể kết nối đến máy chủ");
        }
        ReloadCombobox();
       
        con.close();
        
        
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            e.printStackTrace();
        }
    }
    
    private billCafeNew() {
        lblTen.setText(shareHelper.NhanVienDangNhap.getName());
        
    }

    public void clock() {
        Thread clock = new Thread() {
            public void run() {
                try {
                    while (true) {
                        Date t = new Date();
                        lbTime.setText(ft.format(t));
                        sleep(1000);
                    }
                } catch (InterruptedException e) {
                }
            }
        };
        clock.start();
    }

    public void intomoney() {
        int price, totalprice = 0;
        int count = tblBill.getRowCount();
        for (int i = 0; i < count; i++) {
            price = Integer.parseInt((String) tblBill.getValueAt(i, 6));
            totalprice += price;
        }
        txtTotal.setText(formatter.format(totalprice)); //set giá trị cho txtOrder bằng totalprice
    }
    
    
    public void LoadTblFromDB() {
        
        try {
            
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
        String connectionUrl = "jdbc:sqlserver://localhost;database=TheOne;";
        String dbusername = "sa";
        String password ="01219164372";
        Connection con = DriverManager.getConnection(connectionUrl,dbusername,password);
        PreparedStatement ps;
        ResultSet rs;
        
        try {
            String url = "Select * from Product Join ProductType on Product.IDType=ProductType.IDType where Product.ProductName=? and ProductType.Size=?";
            ps = con.prepareStatement(url);
            ps.setString(1, (String) cbProduct.getSelectedItem());
            ps.setString(2, (String) cbSize.getSelectedItem());
            rs = ps.executeQuery();
            int price, quantity, into;
            if (rs.next()) {
                vec = new Vector();
                price = Integer.parseInt(rs.getString("Price"));
                quantity = Integer.parseInt(spQuantity.getValue().toString());
                into = price * quantity;
                vec.add(rs.getString("IDProduct"));
                vec.add(rs.getString("ProductName"));
                vec.add(rs.getString("TypeName"));
                vec.add(rs.getString("Size"));
                vec.add(rs.getString("Price"));
                vec.add(spQuantity.getValue());
                vec.add(String.valueOf(into));
                tblModel.addRow(vec);
            } else {
                JOptionPane.showMessageDialog(null, "Lỗi:: Không tìm thấy sản phẩm");
                cbProduct.grabFocus();
            }
            tblBill.setModel(tblModel);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi 101:: Không thể kết nối đến máy chủ");
        }       
        con.close();
        
        
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void setText(boolean b) {
        txtGuest.setEnabled(b);
    }

    public void UpdatetxtDis1() {
        int Dis;
        NumberFormat formatter = new DecimalFormat("#,###");
        //tính Discount
        String Order = txtTotal.getText().replaceAll(",", "");
        Dis = (Integer.parseInt(txtDis1.getText()) * Integer.parseInt(Order)) / 100;
        txtDis2.setText(formatter.format(Dis));
        //tính total
        int total = Integer.parseInt(Order) - Dis;
        txtPay.setText(formatter.format(total));
    }
    
    public void ReloadCombobox() {
        
        try {
            
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
        String connectionUrl = "jdbc:sqlserver://localhost;database=TheOne;";
        String dbusername = "sa";
        String password ="01219164372";
        Connection con = DriverManager.getConnection(connectionUrl,dbusername,password);
        
        PreparedStatement ps;
        ResultSet rs;
        
        cbCTKM.removeAllItems();
        cbCTKM.addItem("Không có");
        cbCTKM.addItem("Khách hàng VIP");
        try {
            Date now = new Date();
            ps = con.prepareStatement("select * from Promotions where StartPromo <= ? and EndPromo >= ?");
            ps.setString(1, ftnow.format(now));
            ps.setString(2, ftnow.format(now));
            rs = ps.executeQuery();
            while (rs.next()) {
                cbCTKM.addItem(rs.getString(2));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi 101:: Không thể kết nối đến máy chủ");
        }
        
        con.close();
        
        
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            e.printStackTrace();
        }
        
    }

    public void PanelOnOff(boolean b) {
        lbNhap.setVisible(b);
        txtID.setVisible(b);
        lbIDError.setVisible(b);
        pnInformation.setVisible(b);
    }
    
        public void PressPrintandSave(String Name) {
        
        try {
            
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
        String connectionUrl = "jdbc:sqlserver://localhost;database=TheOne;";
        String dbusername = "sa";
        String password ="01219164372";
        Connection con = DriverManager.getConnection(connectionUrl,dbusername,password);
        
        
        PreparedStatement ps;
        ResultSet rs ;
        
        int line = tblBill.getRowCount();
        //Thêm từng value vào trong database
        try {
            ps = con.prepareStatement("Insert into [Order] values(?,convert(varchar(20),getdate(),103),convert(varchar(20),getdate(),108),?)");
            ps.setString(1, txtIDBill.getText());
            ps.setString(2, Name);
            ps.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi 101:: Không thể kết nối đến máy chủ");
        }
        for (int i = 0; i < line; i++) {
            String IDProduct = (String) tblModel.getValueAt(i, 0);
            String quantity = String.valueOf(tblModel.getValueAt(i, 5));
            try {
                String in = "Insert into OrderDetails values(?,?,?,?,?)";
                ps = con.prepareStatement(in);
                ps.setString(1, txtIDBill.getText());
                ps.setString(2, IDProduct);
                if (cbCTKM.getSelectedItem().equals("Khách hàng VIP")) {
                    ps.setString(3, lbIDCus.getText());
                } else {
                    ps.setString(3, "Khách vãng lai");
                }
                ps.setInt(4, Integer.parseInt(quantity));
                ps.setString(5, (String) cbCTKM.getSelectedItem());
                ps.executeUpdate();
//                JOptionPane.showMessageDialog(this, "lưu Thành Công");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Lỗi 101:: Không thể kết nối đến máy chủ");
            }
        }
        //kiểm tra số tiền ngày hôm nay và set lại giá trị
        String pay = txtPay.getText().replaceAll(",", "");
        try {
            ps = con.prepareStatement("SELECT * from Revenue where Date=convert(varchar(20),getdate(),103)");
            rs = ps.executeQuery();
            if (rs.next()) {
                int money1 = Integer.parseInt(rs.getString("Money"));
                int money2 = money1 + Integer.parseInt(pay);
                ps = con.prepareStatement("update Revenue set Money=" + money2 + " where Date=convert(varchar(20),getdate(),103)");
                ps.executeUpdate();
            } else {
                ps = con.prepareStatement("insert into Revenue values(convert(varchar(20),getdate(),103)," + pay + ")");
                ps.executeUpdate();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi 101:: Không thể kết nối đến máy chủ");
        }
        //Viết vào file txt
        int guest = Integer.parseInt(txtGuest.getText());
        try {
            Date now = new Date();
            Writer bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("History//" + txtIDBill.getText().trim() + ".txt"), "UTF8"));
            bw.write("\t\t\tCOFFEE THE ONE\r\n\r\n");
            bw.write("\t\t\tĐịa chỉ: Đồng Hơi\r\n");
            bw.write("\t\t\tSĐT: 0886374063\r\n");
            bw.write("\t\t\tHÓA ĐƠN BÁN HÀNG\r\n\r\n");
            bw.write("Mã hóa đơn: " + txtIDBill.getText() + "\r\n");
            bw.write("Thời gian: " + ft.format(now) + "\r\n");
            bw.write("NHÂN VIÊN: " + txtEmpName.getText() + "\r\n");
            bw.write("------------------------------------------------------------\r\n");
            bw.write("Mã\tKích thước\tSố lượng\tĐơn giá\tThành tiền\r\n");
            bw.write("-----------------------------------------------------------\r\n");
            //Ghi sản phẩm
            int quantotal = 0;
            for (int i = 0; i < line; i++) {
                String id = (String) tblModel.getValueAt(i, 0);
                String name = (String) tblModel.getValueAt(i, 1);
                String size = (String) tblModel.getValueAt(i, 3);
                String price = String.valueOf(tblModel.getValueAt(i, 4));
                String quantity = String.valueOf(tblModel.getValueAt(i, 5));
                String intomoney = String.valueOf(tblModel.getValueAt(i, 6));
                bw.write((i + 1) + ". " + name + "\r\n");
                bw.write(id + "\t" + size + "\t\t" + quantity + "\t\t" + price + "\t" + intomoney + "\r\n\r\n");
                quantotal += Integer.parseInt(quantity);
            }
            bw.write("------------------------------------------------------------\r\n");
            bw.write("Tổng cộng:\t\t" + quantotal + "\t\t\t" + txtTotal.getText() + " VNĐ\r\n");
            bw.write("\t\tChiết khấu:\t" + txtDis1.getText() + "%\t\t-" + txtDis2.getText() + " VNĐ\r\n");
            bw.write("\t\t--------------------------------------------\r\n");
            bw.write("\t\tThành tiền:\t\t\t" + txtPay.getText() + " VNĐ\r\n");
            bw.write("\t\t--------------------------------------------\r\n");
            bw.write("\t\tTiền khách đưa:\t\t\t" + formatter.format(guest) + " VNĐ\r\n");
            bw.write("\t\tTiền trả lại:\t\t\t" + txtRepay.getText() + " VNĐ\r\n");
            bw.write("------------------------------------------------------------\r\n");
            bw.write("Chương trình khuyến mãi: ");
            if (cbCTKM.getSelectedItem().equals("Không có")) {
                bw.write("Không có.\r\n");
            } else if (cbCTKM.getSelectedItem().equals("Khách hàng VIP")) {
                bw.write("Thành viên quán.\r\n");
                bw.write("-----Thông tin thành viên-----\r\n");
                bw.write("Mã thẻ: " + lbIDCus.getText() + "\r\n");
                bw.write("Tên thành viên: " + lbNameCus.getText() + "\r\n");
                bw.write("Ngày đăng ký: " + lbDateCus.getText() + "\r\n");
                bw.write("Số lượng cũ: " + lbQuantityCus.getText() + " ly.\r\n");
                bw.write("Số ly mới mua: " + quantotal + " ly.\r\n");
                bw.write("Chiết khấu (tính theo số lượng cũ): " + lbDisCus.getText() + "\r\n");
            } else {
                bw.write((String) cbCTKM.getSelectedItem() + "\r\n");
            }
            bw.write("------------------------------------------------------------\r\n");
            bw.write("Mật khẩu Wifi: motdentam\r\n");
            bw.write("---------------------CÁM ƠN QUÝ KHÁCH!----------------------");
            bw.close();
            //update số ly và chiết khấu vào bảng customer
            int quannew = Integer.parseInt(lbQuantityCus.getText()) + quantotal;
            if (quannew < 10) {
                try {
                    ps = con.prepareStatement("Update Customer set Quantity=?,Discount=? where IDCus=?");
                    ps.setInt(1, quannew);
                    ps.setString(2, txtDis1.getText());
                    ps.setString(3, lbIDCus.getText());
                    ps.executeUpdate();
                } catch (Exception e) {
                }
            } else if (quannew >= 10 && quannew < 20) {
                try {
                    ps = con.prepareStatement("Update Customer set Quantity=?,Discount=? where IDCus=?");
                    ps.setInt(1, quannew);
                    ps.setString(2, txtDis1.getText());
                    ps.setString(3, lbIDCus.getText());
                    ps.executeUpdate();
                } catch (Exception e) {
                }
            } else if (quannew >= 20 && quannew < 30) {
                try {
                    ps = con.prepareStatement("Update Customer set Quantity=?,Discount=? where IDCus=?");
                    ps.setInt(1, quannew);
                    ps.setString(2, txtDis1.getText());
                    ps.setString(3, lbIDCus.getText());
                    ps.executeUpdate();
                } catch (Exception e) {
                }
            } else if (quannew >= 30 && quannew < 40) {
                try {
                    ps = con.prepareStatement("Update Customer set Quantity=?,Discount=? where IDCus=?");
                    ps.setInt(1, quannew);
                    ps.setInt(2, 15);
                    ps.setString(3, lbIDCus.getText());
                    ps.executeUpdate();
                } catch (Exception e) {
                }
            } else if (quannew >= 40 && quannew < 50) {
                try {
                    ps = con.prepareStatement("Update Customer set Quantity=?,Discount=? where IDCus=?");
                    ps.setInt(1, quannew);
                    ps.setInt(2, 20);
                    ps.setString(3, lbIDCus.getText());
                    ps.executeUpdate();
                } catch (Exception e) {
                }
            } else if (quannew >= 50) {
                try {
                    ps = con.prepareStatement("Update Customer set Quantity=?,Discount=? where IDCus=?");
                    ps.setInt(1, quannew);
                    ps.setInt(2, 25);
                    ps.setString(3, lbIDCus.getText());
                    ps.executeUpdate();
                } catch (Exception e) {
                }
            }
        } catch (Exception e) {
        }
//        Mở file txt
        Runtime run = Runtime.getRuntime();
        try {
            run.exec("notepad History//" + txtIDBill.getText().trim() + ".txt");
        } catch (IOException e) {
        }

        // set lại bảng, combobox và textbox
        tblModel.getDataVector().removeAllElements();
        tblBill.revalidate();
        setText(false);
        txtIDBill.setEnabled(false);
        cbCTKM.setSelectedIndex(0);
        txtPay.setText("0");
        txtTotal.setText("0");
        txtGuest.setText("0");
        txtRepay.setText("0");
        ResetPnInfor();
        txtID.setText("");
        lbIDError.setText("");
        btnPrint.setEnabled(false);
        
        con.close();
        
        
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            e.printStackTrace();
        }
        
    }
        
        private void ResetPnInfor() {
        lbIDCus.setText("...");
        lbNameCus.setText("...");
        lbDateCus.setText("...");
        lbQuantityCus.setText("...");
        lbDisCus.setText("...");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTen = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        cbCTKM = new javax.swing.JComboBox();
        jLabel12 = new javax.swing.JLabel();
        lbNhap = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        pnInformation = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        lbIDCus = new javax.swing.JLabel();
        lbNameCus = new javax.swing.JLabel();
        lbQuantityCus = new javax.swing.JLabel();
        lbDisCus = new javax.swing.JLabel();
        lbDateCus = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        lbIDError = new javax.swing.JLabel();
        lbNgayKM = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lbSolve = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtDis1 = new javax.swing.JTextField();
        txtGuest = new javax.swing.JTextField();
        txtPay = new javax.swing.JTextField();
        txtDis2 = new javax.swing.JTextField();
        txtTotal = new javax.swing.JTextField();
        btnPrint = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        txtIDBill = new javax.swing.JTextField();
        lbLoiGia = new javax.swing.JLabel();
        txtRepay = new javax.swing.JTextField();
        lbTime = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        cbProduct = new javax.swing.JComboBox();
        txtEmpName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        cbSize = new javax.swing.JComboBox();
        spQuantity = new javax.swing.JSpinner();
        btnAdd = new javax.swing.JButton();
        btnDel = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBill = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        miInformation = new javax.swing.JMenuItem();
        miPassChange = new javax.swing.JMenuItem();
        miLogout = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();

        lblTen.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblTen.setText("KeepToo");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(32, 33, 35));

        jPanel3.setBackground(new java.awt.Color(245, 245, 245));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, new java.awt.Color(0, 0, 0), null));

        cbCTKM.setBorder(new javax.swing.border.MatteBorder(null));
        cbCTKM.setOpaque(false);
        cbCTKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbCTKMActionPerformed(evt);
            }
        });
        cbCTKM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbCTKMKeyPressed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 102));
        jLabel12.setText("Chương trình khuyến mãi:");

        lbNhap.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbNhap.setText("Nhập mã thẻ:");

        txtID.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        txtID.setOpaque(false);
        txtID.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtIDCaretUpdate(evt);
            }
        });
        txtID.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtIDFocusGained(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 102));
        jLabel13.setText("Thông tin khách hàng:");

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel14.setText("Mã thẻ:");

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel15.setText("Họ và tên:");

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel16.setText("Số ly đã mua:");

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel17.setText("Được giảm:");

        lbIDCus.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbIDCus.setForeground(new java.awt.Color(204, 0, 51));
        lbIDCus.setText("...");

        lbNameCus.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbNameCus.setForeground(new java.awt.Color(204, 0, 51));
        lbNameCus.setText("...");

        lbQuantityCus.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbQuantityCus.setForeground(new java.awt.Color(204, 0, 51));
        lbQuantityCus.setText("...");

        lbDisCus.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbDisCus.setForeground(new java.awt.Color(204, 0, 51));
        lbDisCus.setText("...");

        lbDateCus.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbDateCus.setForeground(new java.awt.Color(204, 0, 51));
        lbDateCus.setText("...");

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel19.setText("Ngày đăng ký:");

        javax.swing.GroupLayout pnInformationLayout = new javax.swing.GroupLayout(pnInformation);
        pnInformation.setLayout(pnInformationLayout);
        pnInformationLayout.setHorizontalGroup(
            pnInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnInformationLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addGroup(pnInformationLayout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbIDCus))
                    .addGroup(pnInformationLayout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbNameCus))
                    .addGroup(pnInformationLayout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbQuantityCus))
                    .addGroup(pnInformationLayout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbDisCus))
                    .addGroup(pnInformationLayout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbDateCus)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnInformationLayout.setVerticalGroup(
            pnInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnInformationLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(lbIDCus))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(lbNameCus))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbDateCus)
                    .addComponent(jLabel19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbQuantityCus)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(lbDisCus))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lbIDError.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbIDError.setForeground(new java.awt.Color(255, 0, 0));
        lbIDError.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbIDError.setToolTipText("");

        lbNgayKM.setForeground(new java.awt.Color(255, 0, 0));
        lbNgayKM.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtID)
                            .addComponent(lbNhap)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbNgayKM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbCTKM, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lbIDError, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(pnInformation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbCTKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(lbNgayKM, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbNhap)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbIDError, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnInformation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(245, 245, 245));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder(null, new java.awt.Color(153, 153, 153)));

        jLabel9.setFont(new java.awt.Font("Cooper Black", 1, 24)); // NOI18N
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_money_26px_1.png"))); // NOI18N
        jLabel9.setText("Thanh toán");

        jLabel6.setFont(new java.awt.Font("Sitka Text", 1, 18)); // NOI18N
        jLabel6.setText("Tổng cộng:");

        jLabel5.setFont(new java.awt.Font("Sitka Text", 1, 18)); // NOI18N
        jLabel5.setText("Chiết khấu:");

        jLabel8.setFont(new java.awt.Font("Sitka Text", 1, 18)); // NOI18N
        jLabel8.setText("Thành tiền:");

        jLabel4.setFont(new java.awt.Font("Sitka Text", 1, 18)); // NOI18N
        jLabel4.setText("Tiền khách đưa:");

        jLabel7.setFont(new java.awt.Font("Sitka Text", 1, 18)); // NOI18N
        jLabel7.setText("Tiền trả lại:");

        jLabel10.setFont(new java.awt.Font("Sitka Text", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(153, 51, 0));
        jLabel10.setText("%");

        txtDis1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtDis1.setForeground(new java.awt.Color(0, 0, 102));
        txtDis1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtDis1.setText("0");
        txtDis1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        txtDis1.setDisabledTextColor(new java.awt.Color(51, 0, 204));
        txtDis1.setEnabled(false);
        txtDis1.setOpaque(false);

        txtGuest.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtGuest.setForeground(new java.awt.Color(0, 0, 102));
        txtGuest.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtGuest.setText("0");
        txtGuest.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        txtGuest.setOpaque(false);
        txtGuest.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtGuestCaretUpdate(evt);
            }
        });

        txtPay.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtPay.setForeground(new java.awt.Color(255, 0, 0));
        txtPay.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtPay.setText("0");
        txtPay.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        txtPay.setDisabledTextColor(new java.awt.Color(255, 0, 0));
        txtPay.setEnabled(false);
        txtPay.setOpaque(false);

        txtDis2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtDis2.setForeground(new java.awt.Color(0, 0, 102));
        txtDis2.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtDis2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        txtDis2.setDisabledTextColor(new java.awt.Color(0, 0, 204));
        txtDis2.setEnabled(false);
        txtDis2.setOpaque(false);

        txtTotal.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtTotal.setForeground(new java.awt.Color(0, 0, 102));
        txtTotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTotal.setText("0");
        txtTotal.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        txtTotal.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtTotal.setDisabledTextColor(new java.awt.Color(0, 0, 204));
        txtTotal.setEnabled(false);
        txtTotal.setOpaque(false);
        txtTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalActionPerformed(evt);
            }
        });

        btnPrint.setFont(new java.awt.Font("Sitka Subheading", 1, 24)); // NOI18N
        btnPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_send_to_printer_30px.png"))); // NOI18N
        btnPrint.setText("Lưu và In");
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 0, 0));
        jLabel20.setText("Mã hóa đơn:");

        txtIDBill.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtIDBill.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        txtIDBill.setOpaque(false);

        lbLoiGia.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        lbLoiGia.setForeground(new java.awt.Color(255, 0, 0));
        lbLoiGia.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        txtRepay.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtRepay.setForeground(new java.awt.Color(0, 0, 102));
        txtRepay.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtRepay.setText("0");
        txtRepay.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        txtRepay.setDisabledTextColor(new java.awt.Color(0, 0, 204));
        txtRepay.setEnabled(false);
        txtRepay.setOpaque(false);

        lbTime.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lbTime.setForeground(new java.awt.Color(255, 0, 0));
        lbTime.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 102));
        jLabel2.setText("Tên nhân viên:");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 102));
        jLabel1.setText("Tên sản phẩm");

        cbProduct.setEditable(true);
        cbProduct.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        cbProduct.setOpaque(false);
        cbProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbProductActionPerformed(evt);
            }
        });

        txtEmpName.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtEmpName.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        txtEmpName.setDisabledTextColor(new java.awt.Color(255, 0, 0));
        txtEmpName.setEnabled(false);
        txtEmpName.setOpaque(false);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 102));
        jLabel3.setText("Số lượng:");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 102));
        jLabel11.setText("Kích thước:");

        cbSize.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Nhỏ", "Vừa", "Lớn" }));
        cbSize.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        cbSize.setOpaque(false);

        spQuantity.setModel(new javax.swing.SpinnerNumberModel(1, 1, 100, 1));
        spQuantity.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        spQuantity.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        spQuantity.setOpaque(false);

        btnAdd.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_download_16px_3.png"))); // NOI18N
        btnAdd.setText("Thêm");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnDel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnDel.setForeground(new java.awt.Color(255, 0, 0));
        btnDel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_trash_16px_1.png"))); // NOI18N
        btnDel.setText("Xóa");
        btnDel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelActionPerformed(evt);
            }
        });

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(219, 219, 219)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addComponent(txtRepay, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel20)
                                    .addComponent(txtIDBill, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(btnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lbSolve)
                                .addGap(278, 278, 278)
                                .addComponent(jLabel9)
                                .addGap(84, 84, 84)
                                .addComponent(lbTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6))
                                .addGap(19, 19, 19)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(txtDis1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel10)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtDis2))
                                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtPay))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lbLoiGia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txtGuest)))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtEmpName)
                                    .addComponent(cbProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel3))
                                .addGap(12, 12, 12)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(spQuantity)
                                    .addComponent(cbSize, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnDel, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(lbSolve))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lbTime, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel9)))
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtTotal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel8)))
                    .addComponent(txtPay, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtDis2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDis1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(jLabel10)
                        .addComponent(jLabel4)
                        .addComponent(txtGuest, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtRepay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(lbLoiGia, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnPrint)
                        .addComponent(txtIDBill, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addGap(33, 33, 33)))
                .addGap(28, 28, 28)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtEmpName, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cbProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel1)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnDel, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(spQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))))
                .addContainerGap())
        );

        tblBill.setBackground(new java.awt.Color(0, 255, 0));
        tblBill.setSelectionBackground(new java.awt.Color(255, 0, 0));
        tblBill.getTableHeader().setReorderingAllowed(false);
        tblBill.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBillMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblBill);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jMenuBar1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        jMenuBar1.setAutoscrolls(true);

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_administrator_male_16px.png"))); // NOI18N
        jMenu1.setText("Tài khoản");

        miInformation.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_male_user_16px_1.png"))); // NOI18N
        miInformation.setText("Thông tin tài khoản");
        miInformation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miInformationActionPerformed(evt);
            }
        });
        jMenu1.add(miInformation);

        miPassChange.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_edit_16px_1.png"))); // NOI18N
        miPassChange.setText("Đổi mật khẩu");
        miPassChange.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miPassChangeActionPerformed(evt);
            }
        });
        jMenu1.add(miPassChange);

        miLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_exit_16px_1.png"))); // NOI18N
        miLogout.setText("Đăng xuất");
        miLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miLogoutActionPerformed(evt);
            }
        });
        jMenu1.add(miLogout);

        jMenuBar1.add(jMenu1);

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_purchase_order_16px.png"))); // NOI18N
        jMenu2.setText("Chi tiết hóa đơn");
        jMenu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu2MouseClicked(evt);
            }
        });
        jMenu2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu2ActionPerformed(evt);
            }
        });

        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_administrator_male_16px.png"))); // NOI18N
        jMenuItem1.setText("Cá Nhân");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_people_16px_1.png"))); // NOI18N
        jMenuItem2.setText("Tổng Hợp");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuBar1.add(jMenu2);

        jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_info_16px.png"))); // NOI18N
        jMenu3.setText("Giới thiệu");
        jMenu3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu3MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void miInformationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miInformationActionPerformed
        //        new Information(txtEmpName.getText()).setVisible(true);
        thongTinNewDiag jh = new thongTinNewDiag(this, true);
        jh.setVisible(true);
    }//GEN-LAST:event_miInformationActionPerformed

    private void miPassChangeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miPassChangeActionPerformed
        //        new PasswordChange(txtEmpName.getText()).setVisible(true);
        
            new doiMatKhauJdialog(this, true).setVisible(true);
            
       
    }//GEN-LAST:event_miPassChangeActionPerformed

    private void miLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miLogoutActionPerformed
        if (MessageDialogHelper.showConFirmDialog(this, "Bạn có muốn đăng xuất không?", "Hỏi") != JOptionPane.YES_OPTION) {
            return;
        }
        if (JOptionPane.YES_OPTION == 0) {
            this.setVisible(false);
            new LoginFrNew().setVisible(true);
        }
    }//GEN-LAST:event_miLogoutActionPerformed

    private void jMenu3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu3MouseClicked
        //        new AboutUs().setVisible(true);
        new gioiThieuDIalog(this,true).setVisible(true);
    }//GEN-LAST:event_jMenu3MouseClicked

    private void jMenu2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu2MouseClicked
//        new History().setVisible(true);
    }//GEN-LAST:event_jMenu2MouseClicked

    private void cbCTKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbCTKMActionPerformed
        try {

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String connectionUrl = "jdbc:sqlserver://localhost;database=TheOne;";
            String dbusername = "sa";
            String password ="01219164372";
            Connection con = DriverManager.getConnection(connectionUrl,dbusername,password);

            PreparedStatement ps;
            ResultSet rs;

            btnPrint.setEnabled(false);
            txtIDBill.setEnabled(false);
            if (cbCTKM.getSelectedItem().equals("Không có")) {
                txtDis1.setText("0");
                lbNgayKM.setText("");
                PanelOnOff(false);
                UpdatetxtDis1();
                ResetPnInfor();
                txtID.setText("");
                lbIDError.setText("");
            } else if (cbCTKM.getSelectedItem().equals("Khách hàng VIP")) {
                txtDis1.setText("0");
                lbNgayKM.setText("");
                UpdatetxtDis1();
                PanelOnOff(true);
            } else {
                PanelOnOff(false);
                ResetPnInfor();
                txtID.setText("");
                lbIDError.setText("");
                try {
                    ps = con.prepareStatement("Select * from Promotions where NamePromo=?");
                    ps.setString(1, (String) cbCTKM.getSelectedItem());
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        Date start = ftnow.parse(rs.getString(4));
                        Date end = ftnow.parse(rs.getString(5));
                        txtDis1.setText(rs.getString(3));
                        lbNgayKM.setText(ftNgay.format(start) + " - " + ftNgay.format(end));
                        UpdatetxtDis1();
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Lỗi 101:: Không thể kết nối đến máy chủ");
                } catch (ParseException ex) {
                    Logger.getLogger(billCafeNew.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            con.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            e.printStackTrace();
        }

    }//GEN-LAST:event_cbCTKMActionPerformed

    private void cbCTKMKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbCTKMKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_F5){
            ReloadCombobox();
        }
    }//GEN-LAST:event_cbCTKMKeyPressed

    private void txtIDCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtIDCaretUpdate

        try {

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String connectionUrl = "jdbc:sqlserver://localhost;database=TheOne;";
            String dbusername = "sa";
            String password ="01219164372";
            Connection con = DriverManager.getConnection(connectionUrl,dbusername,password);

            PreparedStatement ps;
            ResultSet rs;

            btnPrint.setEnabled(false);
            txtIDBill.setEnabled(false);
            if (txtID.getText().trim().equals("")) {
                txtDis1.setText("0");
                lbIDError.setText("Vui lòng nhập mã thẻ.");
                lbIDError.setForeground(Color.red);
                ResetPnInfor();
            } else {
                while (true) {
                    if (!txtID.getText().trim().matches("\\d+")) {
                        lbIDError.setText("Mã thẻ dạng số.");
                        lbIDError.setForeground(Color.red);
                        txtDis1.setText("0");
                        ResetPnInfor();
                        return;
                    } else {
                        break;
                    }
                }
                try {
                    ps = con.prepareStatement("Select * from Customer where IDCus=?");
                    ps.setString(1, txtID.getText());
                    rs = ps.executeQuery();
                    if (!rs.next()) {
                        lbIDError.setText("Mã thẻ không tồn tại!");
                        lbIDError.setForeground(Color.red);
                        txtDis1.setText("0");
                        ResetPnInfor();
                    } else {
                        lbIDError.setText("Thành công.");
                        lbIDError.setForeground(Color.BLUE);
                        lbIDCus.setText(rs.getString(1));
                        lbNameCus.setText(rs.getString(3));
                        lbDateCus.setText(rs.getString(4));
                        lbQuantityCus.setText(rs.getString(7));
                        lbDisCus.setText(rs.getString(8) + "%");
                        txtDis1.setText(rs.getString(8));
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Lỗi 101:: Không thể kết nối đến máy chủ");
                }
            }
            UpdatetxtDis1();

            con.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            e.printStackTrace();
        }

    }//GEN-LAST:event_txtIDCaretUpdate

    private void txtIDFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtIDFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDFocusGained

    private void txtGuestCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtGuestCaretUpdate
        int Repay;
        //tính Discount
        while (true) {
            if (txtGuest.getText().trim().equals("")) {
                lbLoiGia.setText("Khách hàng chưa đưa tiền.");
                txtRepay.setText("0");
                btnPrint.setEnabled(false);
                txtIDBill.setEnabled(false);
                return;
            } else if (!txtGuest.getText().trim().matches("\\d+")) {
                lbLoiGia.setText("Tiền có dạng số.");
                txtRepay.setText("0");
                btnPrint.setEnabled(false);
                txtIDBill.setEnabled(false);
                return;
            } else {
                lbLoiGia.setText("");
                btnPrint.setEnabled(false);
                txtIDBill.setEnabled(false);
                break;
            }
        }
        String total = txtPay.getText().replaceAll(",", "");
        Repay = Integer.parseInt(txtGuest.getText()) - Integer.parseInt(total);
        txtRepay.setText(formatter.format(Repay));
        if (Repay < 0) {
            lbLoiGia.setText("Khách hàng chưa đưa đủ tiền.");
            btnPrint.setEnabled(false);
            txtIDBill.setEnabled(false);
            txtRepay.setText("0");
        } else if (Integer.parseInt(txtGuest.getText()) == 0) {
            btnPrint.setEnabled(false);
            txtIDBill.setEnabled(false);
            txtRepay.setText("0");
        } else {
            lbLoiGia.setText("");
            btnPrint.setEnabled(true);
            txtIDBill.setEnabled(true);
        }
    }//GEN-LAST:event_txtGuestCaretUpdate

    private void txtTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalActionPerformed

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed

        try {

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String connectionUrl = "jdbc:sqlserver://localhost;database=TheOne;";
            String dbusername = "sa";
            String password ="01219164372";
            Connection con = DriverManager.getConnection(connectionUrl,dbusername,password);

            PreparedStatement ps;
            ResultSet rsIDOrder,rsEmp;

            if (cbCTKM.getSelectedItem().equals("Khách hàng VIP")) {
                while (true) {
                    if (txtID.getText().trim().equals("")) {
                        JOptionPane.showMessageDialog(null, "Mã thẻ VIP không được để trống!");
                        txtID.grabFocus();
                        return;
                    } else if(!txtID.getText().trim().equals("") && !lbIDError.getText().equals("Thành công.")) {
                        JOptionPane.showMessageDialog(null, "Mã thẻ VIP chưa đúng, vui lòng nhập lại!");
                        txtID.grabFocus();
                        return;
                    } else{
                        break;
                    }
                }
            }
            while (true) {
                if (txtIDBill.getText().trim().equals("")) {
                    JOptionPane.showMessageDialog(null, "Mã hóa đơn không được để trống.");
                    txtIDBill.grabFocus();
                    return;
                } else if (!txtIDBill.getText().trim().matches("HD[0-9]{4}")) {
                    JOptionPane.showMessageDialog(null, "Mã hóa đơn có dạng HDxxxx, trong đó xxxx là số nguyên.");
                    txtIDBill.grabFocus();
                    return;
                } else {
                    break;
                }
            }
            try {
                ps = con.prepareStatement("select IDOrder from [Order] where IDOrder=?");
                ps.setString(1, txtIDBill.getText().trim());
                rsIDOrder = ps.executeQuery();
                if (!rsIDOrder.next()) {
                    ps = con.prepareStatement("Select * from Employee where NameEmp=?");
                    ps.setString(1, txtEmpName.getText());
                    rsEmp = ps.executeQuery();
                    if (rsEmp.next()) {
                        PressPrintandSave(rsEmp.getString(1));
                    } else{
                        JOptionPane.showMessageDialog(null, "Tên nhân viên không tồn tại.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Mã hóa đơn đã tồn tại, vui lòng chọn mã mới.");
                    txtIDBill.grabFocus();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Lỗi 101:: Không thể kết nối đến máy chủ");
            }

            con.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            e.printStackTrace();
        }

    }//GEN-LAST:event_btnPrintActionPerformed

    private void tblBillMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBillMouseClicked

    }//GEN-LAST:event_tblBillMouseClicked

    private void btnDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelActionPerformed
        int line = tblBill.getSelectedRow();
        tblModel.removeRow(line);
        intomoney();
        UpdatetxtDis1();
        if (tblBill.getRowCount() > 0) {
            setText(true);
        } else {
            setText(false);
        }
        //        txtPay.setText("0");
        //        txtTotal.setText("0");
        //        txtGuest.setText("0");
        //        txtRepay.setText("0");
        btnPrint.setEnabled(false);
        txtIDBill.setEnabled(false);
    }//GEN-LAST:event_btnDelActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        int line = tblBill.getRowCount();
        for (int i = 0; i < line; i++) {
            if (tblBill.getValueAt(i, 1).equals(cbProduct.getSelectedItem()) && tblBill.getValueAt(i, 3).equals(cbSize.getSelectedItem())) {
                int quanCu = (int) tblBill.getValueAt(i, 5);
                int quanMoi = (int) spQuantity.getValue();
                int quanTotal = quanCu + quanMoi;
                spQuantity.setValue(quanTotal);
                tblModel.removeRow(i);
                break;
            }
        }
        LoadTblFromDB();
        cbProduct.setSelectedIndex(-1);
        spQuantity.setValue(1);
        cbSize.setSelectedIndex(0);
        intomoney();
        UpdatetxtDis1();
        if (tblBill.getRowCount() > 0) {
            setText(true);
        } else {
            setText(false);
        }
        btnPrint.setEnabled(false);
        txtIDBill.setEnabled(false);
    }//GEN-LAST:event_btnAddActionPerformed

    private void jMenu2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu2ActionPerformed
        // TODO add your handling code here:
//        lichsutest ps = new lichsutest();
//        ps.setVisible(true);
    }//GEN-LAST:event_jMenu2ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        hoadonnhanVien ps = new hoadonnhanVien(this, true);
        ps.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        hoadonFull ps = new hoadonFull(this, true);
        ps.setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void cbProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbProductActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbProductActionPerformed

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
            java.util.logging.Logger.getLogger(billCafeNew.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(billCafeNew.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(billCafeNew.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(billCafeNew.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new billCafeNew().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDel;
    private javax.swing.JButton btnPrint;
    private javax.swing.JComboBox cbCTKM;
    private javax.swing.JComboBox cbProduct;
    private javax.swing.JComboBox cbSize;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lbDateCus;
    private javax.swing.JLabel lbDisCus;
    private javax.swing.JLabel lbIDCus;
    private javax.swing.JLabel lbIDError;
    private javax.swing.JLabel lbLoiGia;
    private javax.swing.JLabel lbNameCus;
    private javax.swing.JLabel lbNgayKM;
    private javax.swing.JLabel lbNhap;
    private javax.swing.JLabel lbQuantityCus;
    private javax.swing.JLabel lbSolve;
    private javax.swing.JLabel lbTime;
    private javax.swing.JLabel lblTen;
    private javax.swing.JMenuItem miInformation;
    private javax.swing.JMenuItem miLogout;
    private javax.swing.JMenuItem miPassChange;
    private javax.swing.JPanel pnInformation;
    private javax.swing.JSpinner spQuantity;
    private javax.swing.JTable tblBill;
    private javax.swing.JTextField txtDis1;
    private javax.swing.JTextField txtDis2;
    private javax.swing.JTextField txtEmpName;
    private javax.swing.JTextField txtGuest;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtIDBill;
    private javax.swing.JTextField txtPay;
    private javax.swing.JTextField txtRepay;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
