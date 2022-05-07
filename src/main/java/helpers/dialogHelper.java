/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import java.awt.Component;
import javax.swing.JOptionPane;

/**
 *
 * @author ACER NITRO 5
 */
public class dialogHelper {
    public static void alert(Component parent, String message) {
    JOptionPane.showMessageDialog(parent, message,
    "Hệ thống quản lý", JOptionPane.INFORMATION_MESSAGE);
 }
}
