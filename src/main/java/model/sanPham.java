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
public class sanPham {
    private String IDProduct;
    private String TenSanPham;
    private String LoaiSpID;
    private int GiaBan;
    
    private String IDProductType;
    private String TenLoaiSanPham;
    private String kichThuocSp;

    public sanPham() {
    }

    public sanPham(String IDProduct, String TenSanPham, String LoaiSpID, int GiaBan) {
        this.IDProduct = IDProduct;
        this.TenSanPham = TenSanPham;
        this.LoaiSpID = LoaiSpID;
        this.GiaBan = GiaBan;
    }

    public sanPham(String IDProduct, String TenSanPham, String LoaiSpID, int GiaBan, String IDProductType, String TenLoaiSanPham, String kichThuocSp) {
        this.IDProduct = IDProduct;
        this.TenSanPham = TenSanPham;
        this.LoaiSpID = LoaiSpID;
        this.GiaBan = GiaBan;
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
    
    

    public String getIDProduct() {
        return IDProduct;
    }

    public void setIDProduct(String IDProduct) {
        this.IDProduct = IDProduct;
    }

    public String getTenSanPham() {
        return TenSanPham;
    }

    public void setTenSanPham(String TenSanPham) {
        this.TenSanPham = TenSanPham;
    }

    public String getLoaiSpID() {
        return LoaiSpID;
    }

    public void setLoaiSpID(String LoaiSpID) {
        this.LoaiSpID = LoaiSpID;
    }

    public int getGiaBan() {
        return GiaBan;
    }

    public void setGiaBan(int GiaBan) {
        this.GiaBan = GiaBan;
    }
    
    
    
}
