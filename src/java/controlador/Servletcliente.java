/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.HeadlessException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;
import modelo.Clientes;
import modelo.ClientesDAO;
import modelo.Credito;
import modelo.CreditoDAO;
import com.itextpdf.*;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import java.io.StringReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author PC
 */
@WebServlet(name = "Servletcliente", urlPatterns = {"/Servletcliente"})
public class Servletcliente extends HttpServlet {

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
            HttpSession sesion = request.getSession();
            String rol = (String) sesion.getAttribute("rol");
            RequestDispatcher rd;
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/aaaa");
            String documento, nombre, apellido, correo, numero, sexo, fecnac;
            if(request.getParameter("subir")!=null){
                documento=request.getParameter("documento");
                nombre=request.getParameter("nombre");
                apellido=request.getParameter("apellido");
                correo=request.getParameter("correo");
                numero=request.getParameter("numero");
                sexo=request.getParameter("sexo");
                fecnac=request.getParameter("fecnac");
                Clientes clien = new Clientes(documento, nombre, apellido, correo, numero, sexo, fecnac);
                ClientesDAO cdao = new ClientesDAO();
                boolean comparador = cdao.insertartcliente(clien);
                if (comparador) {
                    JOptionPane.showMessageDialog(null, "Cliente insertado correctamente");
                    rd = request.getRequestDispatcher("/cliente.jsp");
                    rd.forward(request, response);
                }
                else{
                    JOptionPane.showMessageDialog(null, "Error en la insercion");
                    rd = request.getRequestDispatcher("/cliente.jsp");
                    rd.forward(request, response);
                }
            }
            if(request.getParameter("update")!=null){
                documento=request.getParameter("adoc");
                nombre=request.getParameter("anom");
                apellido=request.getParameter("aape");
                correo=request.getParameter("aema");
                numero=request.getParameter("anum");
                sexo=request.getParameter("sexo");
                fecnac=request.getParameter("fecnac");
                Clientes cl = new Clientes(documento, nombre, apellido, correo, numero, sexo, fecnac);
                ClientesDAO cla = new ClientesDAO();
                boolean result = cla.actualizarcliente(cl);
                if (result) {
                    JOptionPane.showMessageDialog(null, "Actualizacion completa");
                    if(rol.equals("cliente")){
                        rd = request.getRequestDispatcher("/vistacliente.jsp");
                        rd.forward(request, response);
                    }
                    else{
                        rd = request.getRequestDispatcher("/cliente.jsp");
                        rd.forward(request, response);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "No se ha podido actualizar");
                    if(rol.equals("cliente")){
                        rd = request.getRequestDispatcher("/vistacliente.jsp");
                        rd.forward(request, response);
                    }
                    else{
                        rd = request.getRequestDispatcher("/cliente.jsp");
                        rd.forward(request, response);
                    }
                }
            }
            if(request.getParameter("delete")!=null){
                documento=request.getParameter("adoc");
                Clientes cl = new Clientes(documento);
                ClientesDAO cla = new ClientesDAO();
                boolean result = cla.deletecliente(cl);
                if (result) {
                    JOptionPane.showMessageDialog(null, "Eliminado satisfactoriamente");
                    rd = request.getRequestDispatcher("/cliente.jsp");
                    rd.forward(request, response);
                }
                else{
                    JOptionPane.showMessageDialog(null, "Error en la eliminacion");
                    rd = request.getRequestDispatcher("/cliente.jsp");
                    rd.forward(request, response);
                }
            }
            if(request.getParameter("pdfind")!=null){
                response.setContentType("application/pdf");
                String numdoc = request.getParameter("adoc");
                String usu = (String) sesion.getAttribute("usuario");
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                Clientes cli = new Clientes(numdoc);
                ClientesDAO cdao = new ClientesDAO();
                ArrayList<Clientes> val = cdao.consultaclienteind(cli);
                OutputStream ficheroPDF = response.getOutputStream();
                    Document documen = new Document();
                    try {
                        String[] array = new String[7];;
                        for(Clientes lista:val){
                            array[0]=lista.getDocumento();
                            array[1]=lista.getNombre();
                            array[2]=lista.getApellido();
                            array[3]=lista.getCorreo();
                            array[4]=lista.getNumero();
                            array[5]=lista.getSexo();
                            array[6]=lista.getFecnac();
                        }
                        
                    PdfWriter.getInstance(documen, ficheroPDF);
                    documen.open();
                        HTMLWorker htmlworker = new HTMLWorker(documen);
                        String text = 
                        "<html>"+
                            "<head></head>"+
                                "<body>"+
                                "<nav>"
                                  +"<img src=\"https://centrocomercialportoalegre.com/wp-content/uploads/2018/03/marca-banco-caja-social-centro-comercial-portoalegre.jpg\">" +
                                    "<h1 class=\"title\">Banco caja social</h1><br>"+
                                "</nav>"+
                                    "<p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Atque animi ea harum saepe similique ab dicta in eligendi cupiditate. Nam mollitia amet delectus corporis provident quidem corrupti porro minus dolore!</p>" +
                                    "<p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Autem inventore sit, animi excepturi esse labore, rem cumque voluptate perspiciatis consequuntur ea facere quod fuga. Iste tempora repellendus quae cupiditate eveniet.</p><br>"
                                + "<form action=\"\">"
                                    + "<p>Documento: "+array[0]+"</p>" +
                                      "<p>Nombre: "+array[1]+"</p>" +
                                      "<p>Apellido: "+array[2]+"</p>" +
                                      "<p>Correo: "+array[3]+"</p>" +
                                      "<p>Celular: "+array[4]+"</p>" +
                                      "<p>Sexo: "+array[5]+"</p>" +
                                      "<p>Fecha de nacimiento:"+array[6]+"</p>"
                                    + "<br><p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Fugit similique consequatur voluptatibus ipsum fuga? Minus dolorum, consectetur deleniti, consequatur qui eligendi, fugiat adipisci alias dolorem illum explicabo aut dolores quisquam.</p>\n" +
                                      "<p>Lorem ipsum dolor sit, amet consectetur adipisicing elit. Repellendus deleniti a tempore! Deleniti non amet sint nobis quisquam, veniam facere harum asperiores totam provident earum, accusantium magnam, quo voluptate doloremque.</p>\n" +
                                    "<footer>" +
                                        "<br><p>Comunicate con nosotros: 31000000000</p>" +
                                        "<p>Hora de generacion: "+dtf.format(LocalDateTime.now())+"</p>" +
                                        "<p>Generado por: "+usu+"</p>" +
                                    "</footer>"+
                                "</body>"+
                        "</html>";
                    htmlworker.parse(new StringReader(text));
                    documen.close();
                    } 
                catch (DocumentException ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }
            }
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
