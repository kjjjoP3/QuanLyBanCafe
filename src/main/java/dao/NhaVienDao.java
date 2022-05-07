/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import helpers.DatabaseHelper;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.rowset.serial.SerialBlob;
import model.NhanVien;

/**
 *
 * @author ACER NITRO 5
 */
public class NhaVienDao {
    public boolean insert(NhanVien sv)
            throws  Exception{
        
        
        
        String sql = "INSERT INTO [dbo].[Employee]([UsernameEmp],[Password],[NameEmp],[Gender],[Birthday],[Phone],[Email],[Address],[Hinh])"
                + "VALUES(?,?,?,?,?,?,?,?,?)";
        try(
                Connection con = DatabaseHelper.openConnection();
                PreparedStatement pstmt = con.prepareStatement(sql);
                ){
            pstmt.setString(1, sv.getUser());
            pstmt.setString(2, sv.getPass());
            pstmt.setString(3, sv.getName());
            pstmt.setInt(4, sv.getGioiTinh());
            pstmt.setString(5, sv.getNgaySinh());
            pstmt.setString(6, sv.getPhone());
            pstmt.setString(7, sv.getEmail());
            pstmt.setString(8, sv.getDiaChi());
            
            if(sv.getHinh() != null){
                Blob hinh = new SerialBlob(sv.getHinh());
                pstmt.setBlob(9, hinh);
            }else{
                Blob hinh = null;
                pstmt.setBlob(9, hinh);
            }
            return pstmt.executeUpdate()>0;
                
            
        }
       
    }
    
    
    
    public boolean update(NhanVien sv)
            throws  Exception{
        
        
        
        String sql = "UPDATE [dbo].[Employee] " +
"     SET [Password] = ?,[NameEmp] = ?,[Gender] = ?,[Birthday] = ?,[Phone] = ?,[Email] = ?,[Address] = ?,[Hinh] = ?"
                + " where [UsernameEmp] = ?  ";
        try(
                Connection con = DatabaseHelper.openConnection();
                PreparedStatement pstmt = con.prepareStatement(sql);
                ){
            pstmt.setString(9, sv.getUser());
            pstmt.setString(1, sv.getPass());
            pstmt.setString(2, sv.getName());
            pstmt.setInt(3, sv.getGioiTinh());
            pstmt.setString(4, sv.getNgaySinh());
            pstmt.setString(5, sv.getPhone());
            pstmt.setString(6, sv.getEmail());
            pstmt.setString(7, sv.getDiaChi());
            
            if(sv.getHinh() != null){
                Blob hinh = new SerialBlob(sv.getHinh());
                pstmt.setBlob(8, hinh);
            }else{
                Blob hinh = null;
                pstmt.setBlob(8, hinh);
            }
            return pstmt.executeUpdate()>0;
                
            
        }
       
    }
    
    
    public boolean delete(String MaSach)
            throws  Exception{
        
      
       String sql1 = "DELETE FROM [dbo].[Employee] where [UsernameEmp] = ?";
               
       //try thu-> chay 
       //duoc -> finally
       //loi -> catch
       //catch(xu ly loi)
       //finally (bat buoc chay(khong duoc phep loi))
      
        try(
                Connection con = DatabaseHelper.openConnection();
                PreparedStatement pstmt = con.prepareStatement(sql1);
                
                ){
            pstmt.setString(1, MaSach);
          
            return pstmt.executeUpdate()>0;
            
                
            
        } 
       
    }
 
    
      public NhanVien findById(String maSinhVien)
            throws  Exception{
        
        
        String sql = "select * from Employee where UsernameEmp = ? ";
        try(
                Connection con = DatabaseHelper.openConnection();
                PreparedStatement pstmt = con.prepareStatement(sql);
                ){
            pstmt.setString(1, maSinhVien);
            try(ResultSet rs = pstmt.executeQuery();){
                if(rs.next()){
                    NhanVien sv = createSinhVien(rs);
                    return sv;
                }
            }
            return null;
                
            
        }
       
    }
      
      
      public List <NhanVien> SearchTen(String tenSach)
            throws  Exception{
        
        
        String sql="SELECT * FROM Employee WHERE UsernameEmp like N'%" + tenSach +  "%' or NameEmp like N'%"+ tenSach + "%'"  ;
        try(
                Connection con = DatabaseHelper.openConnection();
                PreparedStatement pstmt = con.prepareStatement(sql);
                ){
            
            try(ResultSet rs = pstmt.executeQuery();){
                List<NhanVien> list = new ArrayList<>();
                while(rs.next()){
                    NhanVien sv = new NhanVien();
        sv.setUser(rs.getString("UsernameEmp"));
        sv.setPass(rs.getString("Password"));
        sv.setName(rs.getString("NameEmp"));
        sv.setGioiTinh(rs.getInt("Gender"));
        sv.setNgaySinh(rs.getString("Birthday"));
        sv.setPhone(rs.getString("Phone"));
        sv.setEmail(rs.getString("Email"));
        sv.setDiaChi(rs.getString("Address"));
        Blob blob = rs.getBlob("Hinh");
        if(blob != null)
            sv.setHinh(blob.getBytes(1, (int) blob.length()));

        
        list.add(sv);
        
                }
                return list;
            }
           
                
            
        }
       
    }
      
      
//      
//      
//      
//      
//
    private NhanVien createSinhVien(final ResultSet rs) throws SQLException {
        NhanVien sv = new NhanVien();
        sv.setUser(rs.getString("UsernameEmp"));
        sv.setPass(rs.getString("Password"));
        sv.setName(rs.getString("NameEmp"));
        sv.setGioiTinh(rs.getInt("Gender"));
        sv.setNgaySinh(rs.getString("Birthday"));
        sv.setPhone(rs.getString("Phone"));
        sv.setEmail(rs.getString("Email"));
        sv.setDiaChi(rs.getString("Address"));
        Blob blob = rs.getBlob("Hinh");
        if(blob != null)
            sv.setHinh(blob.getBytes(1, (int) blob.length()));
        return sv;
    }
//    
      public List <NhanVien> findAll()
            throws  Exception{
        
        
        String sql = "select * from  Employee";
        try(
                Connection con = DatabaseHelper.openConnection();
                PreparedStatement pstmt = con.prepareStatement(sql);
                ){
            
            try(ResultSet rs = pstmt.executeQuery();){
                List<NhanVien> list = new ArrayList<>();
                while(rs.next()){
                    NhanVien sv = new NhanVien();
        sv.setUser(rs.getString("UsernameEmp"));
        sv.setPass(rs.getString("Password"));
        sv.setName(rs.getString("NameEmp"));
        sv.setGioiTinh(rs.getInt("Gender"));
        sv.setNgaySinh(rs.getString("Birthday"));
        sv.setPhone(rs.getString("Phone"));
        sv.setEmail(rs.getString("Email"));
        sv.setDiaChi(rs.getString("Address"));
        Blob blob = rs.getBlob("Hinh");
        if(blob != null)
            sv.setHinh(blob.getBytes(1, (int) blob.length()));

        
        list.add(sv);
        
                }
                return list;
            }
           
                
            
        }
       
    }
}
