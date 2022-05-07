/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author thanh
 */
public class NewClass1 {
    public static void main(String[] args) {
        int x=10,y=10;
 do {
	 for(int j = 1; j<5;j++) {
		 if(j%3==1) {
			 y+=x/j;
		 }else {
			y-=x*j;
		}
		 x++;
	 }
 }while(x++ <5);
        System.out.println(y);
    }
    
}
