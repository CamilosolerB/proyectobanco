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
public class LineaDAO {
    
    Conexion con = new Conexion();
    Connection mysql = con.conexionsql();
    PreparedStatement ps;
    ResultSet rs;
    
    public boolean insertarlinea(Linea lin){
        boolean valor=false;
            try {
                ps=mysql.prepareStatement("Insert into lineas VALUES (?,?,?,?)");
                ps.setString(1, lin.getCodigo());
                ps.setString(2, lin.getNumero());
                ps.setString(3, lin.getMonto());
                ps.setInt(4, lin.getPlazo());
                
                if (ps.executeUpdate()>0) {
                    valor=true;
                }
            } 
            catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        return valor;
    }
    
    public ArrayList<Linea> consultausuario(){
        ArrayList<Linea> lista = new ArrayList<Linea>();
            try {
            ps=mysql.prepareStatement("Select * from lineas");
            rs=ps.executeQuery();
            while(rs.next()){
                Linea cli = new Linea(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4));
                lista.add(cli);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return lista;
    }
    
    public boolean actualizarlinea(Linea lin){
        boolean valor=false;
            try {
                ps=mysql.prepareStatement("Update lineas set nomlinea=?, montomaximo=?, plazomax=? where codlinea=?");
                ps.setString(1, lin.getNumero());
                ps.setString(2, lin.getMonto());
                ps.setInt(3, lin.getPlazo());
                ps.setString(4, lin.getCodigo());
                if (ps.executeUpdate()>0) {
                    valor=true;
                }
            } 
            catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        return valor;
    }
    
    public boolean deletelinea(Linea li){
        boolean valor=false;
            try {
                ps=mysql.prepareStatement("Delete from lineas where codlinea=?");
                ps.setString(1, li.getCodigo());
                if (ps.executeUpdate()>0) {
                    valor=true;
                }
            } 
            catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        return valor;
    }
    public ArrayList<Linea> consultaind(Linea li){
        ArrayList<Linea> lista = new ArrayList<Linea>();
            try {
            ps=mysql.prepareStatement("Select * from lineas Where codlinea=?");
            ps.setString(1, li.getCodigo());
            rs=ps.executeQuery();
            while(rs.next()){
                Linea cli = new Linea(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4));
                lista.add(cli);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return lista;
    }
}
