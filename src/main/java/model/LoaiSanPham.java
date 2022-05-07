/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author ACER NITRO 5
 */
public class LoaiSanPham {
    private String IDProductType;
    private String TenLoaiSanPham;
    private String kichThuocSp;

    public LoaiSanPham() {
    }

    public LoaiSanPham(String IDProductType, String TenLoaiSanPham, String kichThuocSp) {
        this.IDProductType = IDProductType;
        this.TenLoaiSanPham = TenLoaiSanPham;
        this.kichThuocSp = kichThuocSp;
    }

    public String getIDProductType() {
        return IDProductType;
    }

    public void setIDProductType(String IDProductType) {
        this.IDProductType = IDProductType;
    }

    public String getTenLoaiSanPham() {
        return TenLoaiSanPham;
    }

    public void setTenLoaiSanPham(String TenLoaiSanPham) {
        this.TenLoaiSanPham = TenLoaiSanPham;
    }

    public String getKichThuocSp() {
        return kichThuocSp;
    }

    public void setKichThuocSp(String kichThuocSp) {
        this.kichThuocSp = kichThuocSp;
    }
    
    
}
