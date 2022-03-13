/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.sql.*;
import javax.swing.JOptionPane;
/**
 *
 * @author SENA
 */
public class Conexion {
    Connection mysql;
    
    public Connection conexionsql(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try {
                mysql = DriverManager.getConnection("jdbc:mysql://localhost:3306/banco","root","");
                System.out.println("conexion exitosa");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,"El error es "+ e);
            }
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null,"Error en la conexion"+e);
        }
           return mysql;
    }
    /*public static void main(String[] args) {
        Conexion con = new Conexion();
        con.conexionsql();
    }*/
}

