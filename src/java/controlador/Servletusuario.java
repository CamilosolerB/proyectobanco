/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.sql.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.swing.JOptionPane;
import modelo.Usuario;
import modelo.UsuarioDAO;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author SENA
 */
@WebServlet(name = "Servletusuario", urlPatterns = {"/Servletusuario"})
@MultipartConfig 
public class Servletusuario extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //PrintWriter out = response.getWriter();
        RequestDispatcher rd;
        HttpSession sesion = request.getSession();
        String rol = (String) sesion.getAttribute("rol");
        String  d,u,c,r,e,nomarc,nombre,url,context,delete,stimg;
        if(request.getParameter("subir")!=null){
            //Connection cnn;
            //Conexion con = new Conexion();
            //con.conexionsql();
            d=request.getParameter("documento");
            u=request.getParameter("nombre");
            c=request.getParameter("clave");
            String encriptado=DigestUtils.md5Hex(c);
            r=request.getParameter("rol");
            e=request.getParameter("estado");
            Part i = request.getPart("imagen");
            nomarc = i.getSubmittedFileName();
            context = request.getServletContext().getRealPath("webimages");
            url=Paths.get(i.getSubmittedFileName()).getFileName().toString();
            i.write(context+ File.separator + url);
            url = "webimages/"+nomarc;
            Usuario us = new Usuario(d, u, encriptado, r, e, url);
            UsuarioDAO udao = new UsuarioDAO();
            boolean y=udao.insertarusuario(us);
            if(y){
                JOptionPane.showMessageDialog(null, "usuario insertado");
                
            }
            else{
                JOptionPane.showMessageDialog(null, "error en la insercion");
            }
        }
        if(request.getParameter("actfot")!=null){
            
            d=request.getParameter("adoc");
            if(d==null){
                d=(String) sesion.getAttribute("documento");
                JOptionPane.showMessageDialog(null, d);
            }
            Part i = request.getPart("aimg");
            nomarc = i.getSubmittedFileName();
            context = request.getServletContext().getRealPath("webimages");
            url=Paths.get(i.getSubmittedFileName()).getFileName().toString();
            i.write(context+ File.separator + url);
            url = "webimages/"+nomarc;
            UsuarioDAO udao = new UsuarioDAO();
            Usuario us = new Usuario(d,url);
            if(udao.actualizarimg(us)){
                JOptionPane.showMessageDialog(null, "Imagen actualizada con exito");
                if(rol.equals("cliente")){
                    rd = request.getRequestDispatcher("/vistacliente.jsp");
                    rd.forward(request, response);
                }
                else{
                    rd = request.getRequestDispatcher("/indexa.jsp");
                    rd.forward(request, response);
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "Error al actualizar");
                                if(rol.equals("cliente")){
                    rd = request.getRequestDispatcher("/vistacliente.jsp");
                    rd.forward(request, response);
                }
                else{
                    rd = request.getRequestDispatcher("/indexa.jsp");
                    rd.forward(request, response);
                }
            }
        }
        if(request.getParameter("update")!=null){
            d=request.getParameter("adoc");
            u=request.getParameter("ausu");
            c=request.getParameter("acla");
            String encriptado=DigestUtils.md5Hex(c);
            r=request.getParameter("arol");
            e=request.getParameter("aest");
            url=request.getParameter("imgant");
            Usuario us = new Usuario(d, u,encriptado, r, e);
            UsuarioDAO udao = new UsuarioDAO();
            boolean res = udao.actualizarusuuario(us);
            if (res) {
                JOptionPane.showMessageDialog(null, "Usuario actualizado correctamente");
            }
            else{
                JOptionPane.showMessageDialog(null, "error en la actualizacion");
            }
        }
        if(request.getParameter("delete")!=null){
            d=request.getParameter("adoc");
            Usuario us = new Usuario(d);
            UsuarioDAO udao = new UsuarioDAO();
            boolean call = udao.deleteusuario(us);
            if (call) {
                JOptionPane.showMessageDialog(null, "Dato eliminado con exito");
            }
            else{
                JOptionPane.showMessageDialog(null, "Error en la eliminacion");
            }
        }
        if (request.getParameter("actid")!=null) {
            d=(String) sesion.getAttribute("documento");
            u=request.getParameter("ausu");
            c=request.getParameter("acla");
            String encriptado=DigestUtils.md5Hex(c);
            Usuario us = new Usuario(d, u, encriptado);
            UsuarioDAO udao = new UsuarioDAO();
            if (udao.actualizarcli(us)) {
                JOptionPane.showMessageDialog(null, "Sus datos se han actualizado correctamente");
                if(rol.equals("cliente")){
                    rd = request.getRequestDispatcher("/vistacliente.jsp");
                    rd.forward(request, response);
                }
                else{
                    rd = request.getRequestDispatcher("/indexa.jsp");
                    rd.forward(request, response);
                }
            }
        }
        rd = request.getRequestDispatcher("/indexa.jsp");
        rd.forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
