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
public class ClientesDAO {
    Conexion con = new Conexion();
    Connection mysql = con.conexionsql();
    PreparedStatement ps;
    ResultSet rs;
    
    public boolean insertartcliente(Clientes cli){
        boolean valor=false;
            try {
                ps=mysql.prepareStatement("Insert into cliente VALUES (?,?,?,?,?,?,?)");
                ps.setString(1, cli.getDocumento());
                ps.setString(2, cli.getNombre());
                ps.setString(3, cli.getApellido());
                ps.setString(4, cli.getCorreo());
                ps.setString(5, cli.getNumero());
                ps.setString(6, cli.getSexo());
                ps.setString(7, cli.getFecnac());
                if(ps.executeUpdate()>0){
                    valor=true;
                }
            } 
            catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        return  valor;
    }
    
    public ArrayList<Clientes> consultausuario(){
        ArrayList<Clientes> lista = new ArrayList<>();
            try {
            ps=mysql.prepareStatement("Select * from cliente");
            rs=ps.executeQuery();
            while(rs.next()){
                Clientes cli = new Clientes(rs.getString(1), rs.getString(2), rs.getString(3),
                    rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
                lista.add(cli);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return lista;
    }
        
    public boolean actualizarcliente(Clientes cli){
        boolean respuesta=false;
            try {
            ps=mysql.prepareStatement("Update cliente set nombrec=?, apellido=?, correo=?, celular=?, sexo=?, fecha_nacimiento=? where documentoc=?");
            ps.setString(1, cli.getNombre());
            ps.setString(2, cli.getApellido());
            ps.setString(3, cli.getCorreo());
            ps.setString(4, cli.getNumero());
            ps.setString(5, cli.getSexo());
            ps.setString(6, cli.getFecnac());
            ps.setString(7, cli.getDocumento());
            if(ps.executeUpdate()>0){
                respuesta=true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return respuesta;
    }
    
        public boolean deletecliente(Clientes cli){
        boolean respuesta=false;
            try {
            ps=mysql.prepareStatement("delete from cliente where documentoc=?");
            ps.setString(1, cli.getDocumento());
                if(ps.executeUpdate()>0){
                    respuesta=true;
                }
            } 
            catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        return respuesta;
    }
      
        public ArrayList<Clientes> consultaclienteind(Clientes cl){
        ArrayList<Clientes> lista = new ArrayList<>();
            try {
            ps=mysql.prepareStatement("Select * from cliente where documentoc=?");
            ps.setString(1, cl.getDocumento());
            rs=ps.executeQuery();
            while(rs.next()){
                Clientes cli = new Clientes(rs.getString(1), rs.getString(2), rs.getString(3),
                    rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
                lista.add(cli);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return lista;
    }
}
