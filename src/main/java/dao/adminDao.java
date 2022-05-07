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
import javax.sql.rowset.serial.SerialBlob;
import model.NhanVien;
import model.admin;

/**
 *
 * @author ACER NITRO 5
 */
public class adminDao {
    public admin findById(String maSinhVien)
            throws  Exception{
        
        
        String sql = "select * from Administrator where Username=? ";
        try(
                Connection con = DatabaseHelper.openConnection();
                PreparedStatement pstmt = con.prepareStatement(sql);
                ){
            pstmt.setString(1, maSinhVien);
            try(ResultSet rs = pstmt.executeQuery();){
                if(rs.next()){
                    admin sv = createSinhVien(rs);
                    return sv;
                }
            }
            return null;
                
            
        }
       
    }
    
     private admin createSinhVien(final ResultSet rs) throws SQLException {
        admin sv = new admin();
        sv.setUsername(rs.getString("Username"));
        sv.setPassword(rs.getString("Password"));
        
        return sv;
    }
     
     public boolean update(admin sv)
            throws  Exception{
        
        
        
        String sql="Update Administrator set Password = ? WHERE Username=?";
        try(
                Connection con = DatabaseHelper.openConnection();
                PreparedStatement pstmt = con.prepareStatement(sql);
                ){
            pstmt.setString(1, sv.getPassword());
            pstmt.setString(2, sv.getUsername());
           
            return pstmt.executeUpdate()>0;
                
            
        }
       
    }
    
    
}
