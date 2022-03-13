/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
import modelo.Credito;
import modelo.CreditoDAO;

/**
 *
 * @author PC
 */
@WebServlet(name = "Servletcreditos", urlPatterns = {"/Servletcreditos"})
public class Servletcreditos extends HttpServlet {

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
        RequestDispatcher rd;
        String codigo,documento, codlinea, fecha;
        int monto, plazo;
        if(request.getParameter("subir")!=null){
            codigo=request.getParameter("codigo");
            documento=request.getParameter("documento");
            codlinea=request.getParameter("codlinea");
            monto=Integer.parseInt(request.getParameter("monto"));
            fecha=request.getParameter("fecha");
            plazo=Integer.parseInt(request.getParameter("plazo"));
            //envio a los constructores
            Credito cre = new Credito(codigo, documento, codlinea, monto, fecha, plazo);
            //envio para la base de datos
            CreditoDAO cdao = new CreditoDAO();
            boolean valorfinal = cdao.insertarcreditos(cre);
            if (valorfinal) {
                JOptionPane.showMessageDialog(null, "Credito generado con exito");
                rd = request.getRequestDispatcher("/credito.jsp");
                rd.forward(request, response);
            }
            else{
                JOptionPane.showMessageDialog(null, "Credito no generado");
                rd = request.getRequestDispatcher("/credito.jsp");
                rd.forward(request, response);
            }
        }
        if(request.getParameter("update")!=null){
            codigo=request.getParameter("acod");
            documento=request.getParameter("adoc");
            codlinea=request.getParameter("acod");
            monto=Integer.parseInt(request.getParameter("amon"));
            fecha=request.getParameter("afec");
            plazo=Integer.parseInt(request.getParameter("apla"));
            Credito cre = new Credito(codigo, documento, codlinea, monto, fecha, plazo);
            CreditoDAO cdao = new CreditoDAO();
            if(cdao.actualizarcredito(cre)){
                JOptionPane.showMessageDialog(null, "Actualizacion realizada");
                rd = request.getRequestDispatcher("/credito.jsp");
                rd.forward(request, response);
            }
            else{
                JOptionPane.showMessageDialog(null, "Error en la actualizacion");
                rd = request.getRequestDispatcher("/credito.jsp");
                rd.forward(request, response);
            }
        }
        if(request.getParameter("delete")!=null){
            codigo=request.getParameter("acod");
            Credito cre = new Credito(codigo);
            CreditoDAO cdao = new CreditoDAO();
            if(cdao.deletecredito(cre)){
                JOptionPane.showMessageDialog(null, "Eliminacion completada");
                rd = request.getRequestDispatcher("/credito.jsp");
                rd.forward(request, response);
            }
            else{
                JOptionPane.showMessageDialog(null, "Fallo al eliminar");
                rd = request.getRequestDispatcher("/credito.jsp");
                rd.forward(request, response);
            }
        }
        if(request.getParameter("pdfind")!=null){
            HttpSession sesion = request.getSession();
            response.setContentType("application/pdf");
            String doc = request.getParameter("adoc");
            Credito cre = new Credito(doc);
            CreditoDAO cdao = new CreditoDAO();
            ArrayList <Credito> tcredido = new ArrayList<>();
            tcredido = cdao.Consultacreditoind(cre);
            OutputStream out = response.getOutputStream();
            String usu = (String) sesion.getAttribute("usuario");
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            
            Document documen = new Document();
            try {
                PdfWriter.getInstance(documen, out);
                documen.open();
                
                HTMLWorker htmlworker = new HTMLWorker(documen);
                //tabla
                PdfPTable table = new PdfPTable(6);
                table.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.setWidthPercentage(80);
                
                PdfPCell cod =new PdfPCell(new Paragraph("Codigo",
                FontFactory.getFont("arial",12,Font.BOLD,BaseColor.BLACK)));
                
                PdfPCell docu =new PdfPCell(new Paragraph("Documento",
                FontFactory.getFont("arial",10,Font.BOLD,BaseColor.BLACK)));
            
                PdfPCell codlin =new PdfPCell(new Paragraph("Codigo de linea",
                FontFactory.getFont("arial",10,Font.BOLD,BaseColor.BLACK)));
                
                PdfPCell mont =new PdfPCell(new Paragraph("Monto",
                FontFactory.getFont("arial",10,Font.BOLD,BaseColor.BLACK)));
                
                PdfPCell fecg =new PdfPCell(new Paragraph("Fecha de generacion",
                FontFactory.getFont("arial",10,Font.BOLD,BaseColor.BLACK)));
                
                PdfPCell pla =new PdfPCell(new Paragraph("Plazo",
                FontFactory.getFont("arial",10,Font.BOLD,BaseColor.BLACK)));
                
                table.addCell(cod);
                table.addCell(docu);
                table.addCell(codlin);
                table.addCell(mont);
                table.addCell(fecg);
                table.addCell(pla);
                
                for(Credito lista: tcredido){
                    table.addCell(lista.getCodigo());
                    table.addCell(lista.getDocumento());
                    table.addCell(lista.getCodlinea());
                    String mnt = String.valueOf(lista.getMonto());
                    table.addCell(mnt);
                    table.addCell(lista.getFecha());
                    String plaz = String.valueOf(lista.getPlazo());
                    table.addCell(plaz);
                }
                
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
                                + "<form action=\"\">";
                                    
                String text2= 
                                "</form>"
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
                documen.add(table);
                htmlworker.parse(new StringReader(text2));
                documen.close();
            } catch (DocumentException ex) {
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
