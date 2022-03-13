/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import controlador.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


/**
 *
 * @author SENA
 */
public class UsuarioDAO {
    Conexion con = new Conexion();
    Connection cnn = con.conexionsql();
    PreparedStatement ps;
    ResultSet rs;
    
    public boolean insertarusuario(Usuario us){
        int x=0;
        boolean res=false;
        try {
            ps=cnn.prepareStatement("Insert into usuario VALUES (?,?,?,?,?,?)");
            ps.setString(1, us.getDocumento());
            ps.setString(2, us.getUsuario());
            ps.setString(3, us.getClave());
            ps.setString(4, us.getRol());
            ps.setString(5, us.getEstado());
            ps.setString(6, us.getImagen());
            if(ps.executeUpdate()>x){
                res= true;
            }

            } 
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "sucedio el error "+ex);
        }
        return res;
    }
    
    public ArrayList<Usuario> consultausuario(){
        ArrayList<Usuario> lista = new ArrayList<>();
            try {
            ps=cnn.prepareStatement("Select * from usuario");
            rs=ps.executeQuery();
            while(rs.next()){
                Usuario usu = new Usuario(rs.getString(1), rs.getString(2), rs.getString(3),
                    rs.getString(4), rs.getString(5), rs.getString(6));
                lista.add(usu);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return lista;
    }
    public boolean actualizarusuuario(Usuario usu){
        boolean respuesta=false;
            try {
            ps=cnn.prepareStatement("Update usuario set nombre=?, clave=?, rol=?, estado=? where documento=?");
            ps.setString(1, usu.getUsuario());
            ps.setString(2, usu.getClave());
            ps.setString(3, usu.getRol());
            ps.setString(4, usu.getEstado());
            ps.setString(5, usu.getDocumento());
            if(ps.executeUpdate()>0){
                respuesta=true;
            }
        } catch (Exception e) {
        }
        return respuesta;
    }
    
    public boolean deleteusuario(Usuario us){
        boolean respuesta=false;
            try {
            ps=cnn.prepareStatement("delete from usuario where documento=?");
            ps.setString(1, us.getDocumento());
                if(ps.executeUpdate()>0){
                    respuesta=true;
                }
            } 
            catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        return respuesta;
    }
    
    public boolean actualizarimg(Usuario us){
        boolean respuesta=false;
        try {
            ps=cnn.prepareStatement("Update usuario set imagen=? Where documento=?");
            ps.setString(1, us.getImagen());
            ps.setString(2, us.getDocumento());
            if(ps.executeUpdate()>0){
                respuesta=true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return respuesta;
    } 
    
    public boolean actualizarcli(Usuario us){
        boolean res=false;
            try {
            ps=cnn.prepareStatement("Update usuario set nombre=?, clave=? Where documento=?");
            ps.setString(1, us.getUsuario());
            ps.setString(2, us.getClave());
            ps.setString(3, us.getDocumento());
                if (ps.executeUpdate()>0) {
                    res=true;
                }
        } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
        }
        return res;
    }
}
