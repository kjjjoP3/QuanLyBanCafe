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
public class KhuyenMai {
    private int IdQuangCao;
    private String TenQuangCao;
    private int KhuyenMaiGiamGia;
    private String BatDauQuangCao;
    private String KetThucQuangCao;
    private String Mota;

    public KhuyenMai() {
    }

    public KhuyenMai(int IdQuangCao, String TenQuangCao, int KhuyenMaiGiamGia, String BatDauQuangCao, String KetThucQuangCao, String Mota) {
        this.IdQuangCao = IdQuangCao;
        this.TenQuangCao = TenQuangCao;
        this.KhuyenMaiGiamGia = KhuyenMaiGiamGia;
        this.BatDauQuangCao = BatDauQuangCao;
        this.KetThucQuangCao = KetThucQuangCao;
        this.Mota = Mota;
    }

    public int getIdQuangCao() {
        return IdQuangCao;
    }

    public void setIdQuangCao(int IdQuangCao) {
        this.IdQuangCao = IdQuangCao;
    }

    public String getTenQuangCao() {
        return TenQuangCao;
    }

    public void setTenQuangCao(String TenQuangCao) {
        this.TenQuangCao = TenQuangCao;
    }

    public int getKhuyenMaiGiamGia() {
        return KhuyenMaiGiamGia;
    }

    public void setKhuyenMaiGiamGia(int KhuyenMaiGiamGia) {
        this.KhuyenMaiGiamGia = KhuyenMaiGiamGia;
    }

    public String getBatDauQuangCao() {
        return BatDauQuangCao;
    }

    public void setBatDauQuangCao(String BatDauQuangCao) {
        this.BatDauQuangCao = BatDauQuangCao;
    }

    public String getKetThucQuangCao() {
        return KetThucQuangCao;
    }

    public void setKetThucQuangCao(String KetThucQuangCao) {
        this.KetThucQuangCao = KetThucQuangCao;
    }

    public String getMota() {
        return Mota;
    }

    public void setMota(String Mota) {
        this.Mota = Mota;
    }
    
    
    
}
