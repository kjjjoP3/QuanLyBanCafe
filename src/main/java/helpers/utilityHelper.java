/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import static java.awt.Color.pink;
import static java.awt.Color.white;
import javax.swing.JPasswordField;

/**
 *
 * @author ACER NITRO 5
 */
public class utilityHelper {
    public static boolean checkNullPass(JPasswordField txt) {
        txt.setBackground(white);
        if (txt.getPassword().length > 0) {
            return true;
        } else {
            txt.setBackground(pink);
            dialogHelper.alert(txt.getRootPane(), "Không được để trống " + txt.getName());
            return false;
        }
    }
}
