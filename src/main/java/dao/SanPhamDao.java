/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import helpers.DatabaseHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.LoaiSanPham;
import model.sanPham;

/**
 *
 * @author ACER NITRO 5
 */
public class SanPhamDao {
    public List <sanPham> findAll()
            throws  Exception{
        
        
        String sql = "select * from Product inner join ProductType on Product.IDType=ProductType.IDType";
        try(
                Connection con = DatabaseHelper.openConnection();
                PreparedStatement pstmt = con.prepareStatement(sql);
                ){
            
            try(ResultSet rs = pstmt.executeQuery();){
                List<sanPham> list = new ArrayList<>();
                while(rs.next()){
                    sanPham sv = new sanPham();
        sv.setIDProduct(rs.getString("IDProduct"));
        sv.setTenSanPham(rs.getString("ProductName"));
        sv.setLoaiSpID(rs.getString("IDType"));
        sv.setGiaBan(rs.getInt("Price"));
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
