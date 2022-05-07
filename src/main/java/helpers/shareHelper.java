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
public class shareHelper {
    public static admin adminDangNhap = null;
    public static boolean authenticated() {
    return shareHelper.adminDangNhap != null;
 }
    public static NhanVien NhanVienDangNhap = null;
    public static boolean authenticatedSinhVien() {
    return shareHelper.NhanVienDangNhap != null;
 }
}
