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
public class NhanVien {
     private String User;
     private String Pass;
    private String Name;
    private int gioiTinh;
    private String NgaySinh;
    private String Phone;
    private String Email;
    private String DiaChi;
    private byte[] Hinh;

    public NhanVien() {
    }

    public NhanVien(String User, String Pass, String Name, int gioiTinh, String NgaySinh, String Phone, String Email, String DiaChi, byte[] Hinh) {
        this.User = User;
        this.Pass = Pass;
        this.Name = Name;
        this.gioiTinh = gioiTinh;
        this.NgaySinh = NgaySinh;
        this.Phone = Phone;
        this.Email = Email;
        this.DiaChi = DiaChi;
        this.Hinh = Hinh;
    }

    public String getUser() {
        return User;
    }

    public void setUser(String User) {
        this.User = User;
    }

    public String getPass() {
        return Pass;
    }

    public void setPass(String Pass) {
        this.Pass = Pass;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public int getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(int gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(String NgaySinh) {
        this.NgaySinh = NgaySinh;
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

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public byte[] getHinh() {
        return Hinh;
    }

    public void setHinh(byte[] Hinh) {
        this.Hinh = Hinh;
    }
    
    
    
}
