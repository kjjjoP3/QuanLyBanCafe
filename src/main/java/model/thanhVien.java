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
public class thanhVien {
    private int idKhanhHang;
    private String CMND;
    private String Name;
    private String Phone;
    private String Email;
    private String NgayThem;
    private int SoLuong;
    private int ChietKhau;

    public thanhVien() {
    }

    public thanhVien(int idKhanhHang, String CMND, String Name, String Phone, String Email, String NgayThem, int SoLuong, int ChietKhau) {
        this.idKhanhHang = idKhanhHang;
        this.CMND = CMND;
        this.Name = Name;
        this.Phone = Phone;
        this.Email = Email;
        this.NgayThem = NgayThem;
        this.SoLuong = SoLuong;
        this.ChietKhau = ChietKhau;
    }

    public int getIdKhanhHang() {
        return idKhanhHang;
    }

    public void setIdKhanhHang(int idKhanhHang) {
        this.idKhanhHang = idKhanhHang;
    }

    public String getCMND() {
        return CMND;
    }

    public void setCMND(String CMND) {
        this.CMND = CMND;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getNgayThem() {
        return NgayThem;
    }

    public void setNgayThem(String NgayThem) {
        this.NgayThem = NgayThem;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    public int getChietKhau() {
        return ChietKhau;
    }

    public void setChietKhau(int ChietKhau) {
        this.ChietKhau = ChietKhau;
    }

    
    
    
    
}

