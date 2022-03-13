/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import controlador.Conexion;
import java.sql.*;
import javax.swing.JOptionPane;

public class TransfereciaDAO {
    PreparedStatement ps;
    Conexion con = new Conexion();
    Connection mysql = con.conexionsql();
    
    public boolean quitardinero(Transacciones tr){
        boolean trans=false;
            try {
                ps=mysql.prepareStatement("Update creditos Set monto=monto-? Where documentocre=?");
                ps.setInt(1, tr.getValor());
                ps.setString(2, tr.getDocumentome());
                if(ps.executeUpdate()>0){
                    if(meterdinero(tr.getDocumentores(), tr.getValor())){
                        trans = true;
                    }
                }
        } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
        }
        return trans;
    }
    
    public boolean meterdinero(String documento, int valor){
        boolean val=false;
            try {
                ps=mysql.prepareStatement("Update creditos Set monto=monto+? Where documentocre=?");
                ps.setInt(1, valor);
                ps.setString(2, documento);
                if(ps.executeUpdate()>0){
                    val = true;
                }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return val;
    }
        public boolean retirardinero(Transacciones tr){
        boolean trans=false;
            try {
                ps=mysql.prepareStatement("Update creditos Set monto=monto-? Where documentocre=?");
                ps.setInt(1, tr.getValor());
                ps.setString(2, tr.getDocumentome());
                if(ps.executeUpdate()>0){
                    trans=true;
                }
        } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
        }
        return trans;
    }
}
