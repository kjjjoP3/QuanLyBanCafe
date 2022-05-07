/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import model.NhanVien;
import model.admin;

/**
 *
 * @author ACER NITRO 5
 */
public class shareHelper1 {
    public static admin adminDangNhap = null;
    public static boolean authenticated() {
    return shareHelper1.adminDangNhap != null;
 }
    public static NhanVien sinhVienDangNhap = null;
    public static boolean authenticatedNhanVien() {
    return shareHelper1.sinhVienDangNhap != null;
 }
}
