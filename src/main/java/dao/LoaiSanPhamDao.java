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
import java.util.ArrayList;
import java.util.List;
import model.LoaiSanPham;
import model.NhanVien;

/**
 *
 * @author ACER NITRO 5
 */
public class LoaiSanPhamDao {
    
    public List <LoaiSanPham> findAll()
            throws  Exception{
        
        
        String sql = "select * from ProductType";
        try(
                Connection con = DatabaseHelper.openConnection();
                PreparedStatement pstmt = con.prepareStatement(sql);
                ){
            
            try(ResultSet rs = pstmt.executeQuery();){
                List<LoaiSanPham> list = new ArrayList<>();
                while(rs.next()){
                    LoaiSanPham sv = new LoaiSanPham();
        sv.setIDProductType(rs.getString("IDType"));
        sv.setTenLoaiSanPham(rs.getString("TypeName"));
        sv.setKichThuocSp(rs.getString("Size"));
        

        
        list.add(sv);
        
                }
                return list;
            }
           
                
            
        }
       
    }
}
