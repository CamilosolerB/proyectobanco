/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;
import modelo.Login;
import modelo.Logueo;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author SENA
 */
@WebServlet(name = "Servletlogin", urlPatterns = {"/Servletlogin"})
public class Servletlogin extends HttpServlet {

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
            PrintWriter out = response.getWriter();


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
                HttpSession sesion = request.getSession();
                RequestDispatcher rd;
                Login lgg;
                if(request.getParameter("sing")!=null){
                    
                    String user,password, uu, cl, rr, dd,ff,nn,aa,cc,cel,sex,fecnac;
                    user=request.getParameter("user");
                    password=request.getParameter("password");
                    String encriptado= DigestUtils.md5Hex(password);
                    Logueo log = new Logueo();
                    Login validate = new Login(user, encriptado);
                    lgg=log.validarusuario(validate);
                    if(lgg==null){
                            JOptionPane.showMessageDialog(null, "Usuario y/o contraseña incorrecto");
                            rd=request.getRequestDispatcher("/index.jsp");
                            rd.forward(request, response);                    }
                    else{
                        uu= lgg.getUsuario();
                        cl= lgg.getPassword();
                        rr = lgg.getRol();
                        ff = lgg.getFoto();
                        nn= lgg.getNombre();
                        aa= lgg.getApellido();
                        dd= lgg.getDocumento();
                        cc = lgg.getCorreo();
                        cel = lgg.getCelular();
                        sex = lgg.getSexo();
                        fecnac = lgg.getFechanac();
                            switch (rr) {
                                case "admin":
                                    JOptionPane.showMessageDialog(null, "Bienvenido al sistema");
                                    sesion.setAttribute("usuario", uu);
                                    sesion.setAttribute("rol", rr);
                                    sesion.setAttribute("foto", ff);
                                    sesion.setAttribute("nombre", nn);
                                    sesion.setAttribute("apellido", aa);
                                    rd=request.getRequestDispatcher("/indexa.jsp");
                                    rd.forward(request, response);
                                    break;
                                case "empleado":
                                    JOptionPane.showMessageDialog(null, "Bienvenido al sistema - empleado");
                                    sesion.setAttribute("usuario", uu);
                                    JOptionPane.showMessageDialog(null, uu);
                                    sesion.setAttribute("rol", rr);
                                    sesion.setAttribute("foto", ff);
                                    sesion.setAttribute("nombre", nn);
                                    sesion.setAttribute("apellido", aa);
                                    request.getRequestDispatcher("cliente.jsp").forward(request, response);
                                    //rd.forward(request, response);
                                    break;
                                case "cliente":
                                JOptionPane.showMessageDialog(null, "Bienvenido al sistema - clientes");
                                    sesion.setAttribute("usuario", uu);
                                    sesion.setAttribute("rol", rr);
                                    sesion.setAttribute("foto", ff);
                                    sesion.setAttribute("nombre", nn);
                                    sesion.setAttribute("apellido", aa);
                                    sesion.setAttribute("documento", dd);
                                    sesion.setAttribute("password", cl);
                                    sesion.setAttribute("correo", cc);
                                    sesion.setAttribute("celular", cel);
                                    sesion.setAttribute("sexo", sex);
                                    sesion.setAttribute("fecha", fecnac);
                                rd=request.getRequestDispatcher("/vistacliente.jsp");
                                rd.forward(request, response);
                                    break;
                                default:
                                    JOptionPane.showMessageDialog(null,"rol no encontrado");
                            }
                }
            }
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
