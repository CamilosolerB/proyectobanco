/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;


import controlador.Conexion;
import java.sql.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 *
 * @author PC
 */
public class CreditoDAO {
    
    Conexion con = new Conexion();
    Connection mysql = con.conexionsql();
    PreparedStatement ps;
    ResultSet rs;
    
    public ArrayList <String> Consultadocu(){
        ArrayList<String> listado = new ArrayList<>();
        try {
            ps=mysql.prepareStatement("Select documentoc From cliente");
            rs= ps.executeQuery();
            while(rs.next()){
                listado.add(rs.getString("documentoc"));
            }
        } 
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return listado;
    }
   
    public ArrayList <String> Consultacre(){
        ArrayList<String> listado = new ArrayList<>();
        try {
            ps=mysql.prepareStatement("Select codlinea From lineas");
            rs= ps.executeQuery();
            while(rs.next()){
                listado.add(rs.getString("codlinea"));
            }
        } 
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return listado;
    }
    
    public boolean insertarcreditos(Credito cre){
        boolean valor=false;
            try {
                ps=mysql.prepareStatement("Insert into creditos Values(?,?,?,?,?,?)");
                ps.setString(1, cre.getCodigo());
                ps.setString(2, cre.getDocumento());
                ps.setString(3, cre.getCodlinea());
                ps.setInt(4, cre.getMonto());
                ps.setString(5, cre.getFecha());
                ps.setInt(6, cre.getPlazo());
                if(ps.executeUpdate()>0){
                    valor=true;
                }
            } 
            catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        return valor;
    }
    public ArrayList <Credito> Consultacredito(){
        ArrayList<Credito> listado = new ArrayList<Credito>();
        try {
            ps=mysql.prepareStatement("Select * From creditos");
            rs= ps.executeQuery();
            while(rs.next()){
                Credito cre = new Credito(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getInt(6));
                listado.add(cre);
            }
        } 
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return listado;
    }
    
        public boolean actualizarcredito(Credito cre){
        boolean valor=false;
            try {
                ps=mysql.prepareStatement("Update creditos set documentocre=?, codlineacre=?, monto=?, fecha=?, plazo=? where codigo=?");
                ps.setString(1, cre.getDocumento());
                ps.setString(2, cre.getCodlinea());
                ps.setInt(3, cre.getMonto());
                ps.setString(4, cre.getFecha());
                ps.setInt(5, cre.getPlazo());
                ps.setString(6, cre.getCodigo());
                if (ps.executeUpdate()>0) {
                    valor=true;
                }
            } 
            catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        return valor;
    }
        
    public boolean deletecredito(Credito cre){
        boolean valor=false;
            try {
                ps=mysql.prepareStatement("Delete from creditos where codigo=?");
                ps.setString(1, cre.getCodigo());
                if (ps.executeUpdate()>0) {
                    valor=true;
                }
            } 
            catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        return valor;
    }
        
    public ArrayList <Credito> Consultacreditoind(Credito cred){
        ArrayList<Credito> listado = new ArrayList<Credito>();
        try {
            ps=mysql.prepareStatement("Select * From creditos Where documentocre=?");
            ps.setString(1, cred.getCodigo());
            rs= ps.executeQuery();
            while(rs.next()){
                Credito cre = new Credito(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getInt(6));
                listado.add(cre);
            }
        } 
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return listado;
    }
}
