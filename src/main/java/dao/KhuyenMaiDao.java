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
import model.KhuyenMai;
import model.LoaiSanPham;

/**
 *
 * @author ACER NITRO 5
 */
public class KhuyenMaiDao {
    public List <KhuyenMai> findAll()
            throws  Exception{
        
        
        String sql = "select * from [KhuyenMai]";
        try(
                Connection con = DatabaseHelper.openConnection();
                PreparedStatement pstmt = con.prepareStatement(sql);
                ){
            
            try(ResultSet rs = pstmt.executeQuery();){
                List<KhuyenMai> list = new ArrayList<>();
                while(rs.next()){
                    KhuyenMai sv = new KhuyenMai();
        sv.setIdQuangCao(rs.getInt("IdQuangCao"));
        sv.setTenQuangCao(rs.getString("TenQuangCao"));
        sv.setKhuyenMaiGiamGia(rs.getInt("KhuyenMaiGiamGia"));
        sv.setBatDauQuangCao(rs.getString("BatDauQuangCao"));
        sv.setKetThucQuangCao(rs.getString("KetThucQuangCao"));
        sv.setMota(rs.getString("Mota"));
        
        

        
        list.add(sv);
        
                }
                return list;
            }
           
                
            
        }
       
    }
}
