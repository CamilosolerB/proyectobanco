<%-- 
    Document   : index
    Created on : 18/02/2022, 1:24:10 p. m.
    Author     : SENA
--%>
<%
    HttpSession sesion = request.getSession();
    String usu = (String) sesion.getAttribute("usuario");
    String rol = (String) sesion.getAttribute("rol");
    String nom = (String) sesion.getAttribute("nombre");
    String ape = (String) sesion.getAttribute("apellido");
    String foto = (String) sesion.getAttribute("foto");
%>
<%@page import="controlador.Conexion"%>
<%@page import="controlador.Servletusuario" %>
<%@page import="java.sql.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Formulario usuarios</title>
        <link rel="stylesheet" href="css/style.css"/>
        <link rel="icon" href="https://e7.pngegg.com/pngimages/181/292/png-clipart-santander-group-santander-bank-logo-quiz-ultimate-bank-text-hand.png"/>
        <script src="https://cdn.tailwindcss.com"></script>
        <script src="https://cdn.tailwindcss.com?plugins=forms,typography,aspect-ratio,line-clamp"></script>
    </head>
    <body>
        <nav class="bg-gray-800">
          <div class="max-w-7xl mx-auto px-2 sm:px-6 lg:px-8">
            <div class="relative flex items-center justify-between h-16">
              <div class="absolute inset-y-0 left-0 flex items-center sm:hidden">
                <button type="button" class="inline-flex items-center justify-center p-2 rounded-md text-gray-400 hover:text-white hover:bg-gray-700 focus:outline-none focus:ring-2 focus:ring-inset focus:ring-white" aria-controls="mobile-menu" aria-expanded="false">
                  <span class="sr-only">Open main menu</span>
                  <svg class="block h-6 w-6" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke="currentColor" aria-hidden="true">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M4 6h16M4 12h16M4 18h16" />
                  <svg class="hidden h-6 w-6" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke="currentColor" aria-hidden="true">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12" />
                  </svg>
                </button>
              </div>
              <div class="flex-1 flex items-center justify-center sm:items-stretch sm:justify-start">
                <div class="flex-shrink-0 flex items-center">
                  <img class="block lg:hidden h-8 w-auto" src="https://tailwindui.com/img/logos/workflow-mark-indigo-500.svg" alt="Workflow">
                  <img class="hidden lg:block h-8 w-auto" src="https://tailwindui.com/img/logos/workflow-logo-indigo-500-mark-white-text.svg" alt="Workflow">
                </div>
                <div class="hidden sm:block sm:ml-6">
                  <div class="flex space-x-4">
                    <%
                        if (rol.equals("admin")) {
                    %>
                    <a href="indexa.jsp" class="bg-gray-900 text-white px-3 py-2 rounded-md text-sm font-medium" aria-current="page">Usuarios</a>
                     <%}%>
                    <a href="cliente.jsp" class="text-gray-300 hover:bg-gray-700 hover:text-white px-3 py-2 rounded-md text-sm font-medium">Clientes</a>

                    <a href="lineas.jsp" class="text-gray-300 hover:bg-gray-700 hover:text-white px-3 py-2 rounded-md text-sm font-medium">Lineas</a>

                    <a href="credito.jsp" class="text-gray-300 hover:bg-gray-700 hover:text-white px-3 py-2 rounded-md text-sm font-medium">Creditos</a>
                  </div>
                </div>
              </div>
              <div class="absolute inset-y-0 right-0 flex items-center pr-2 sm:static sm:inset-auto sm:ml-6 sm:pr-0">
                <button type="button" class="bg-gray-800 p-1 rounded-full text-gray-400 hover:text-white focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-offset-gray-800 focus:ring-white">
                  <span class="sr-only">View notifications</span>
                    <p class="text-gray-300 hover:bg-gray-700 hover:text-white px-3 py-2 rounded-md text-sm font-medium"><%=nom%> <%=ape%></p>
                </button>
                <div class="ml-3 relative">
                  <div>
                      <a href="/bancoo">
                      <button onclick="window.location.href="/bancoo"" type="submit" class="bg-gray-800 flex text-sm rounded-full focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-offset-gray-800 focus:ring-white" id="user-menu-button" aria-expanded="false" aria-haspopup="true">
                      <span class="sr-only">Open user menu</span>
                      <img class="h-8 w-8 rounded-full" src="<%=foto%>" alt="">
                      </button>
                      </a>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </nav>
        <div class="containers">
            <h1>Formulario de Lineas</h1>
            <a href="#">Insertar</a>
            <a href="consultausuarios.jsp">Consultar</a>
        </div>
        <form class="formulario" action="Servletusuario" method="POST" enctype="multipart/form-data">
            <label for="documento">Numero de documento</label>
            <input type="number" id="documento" placeholder="Documento" name="documento">
            <label for="nombre">Nombre de usuario</label>
            <input type="text" id="nombre" placeholder="Digite el usuario" name="nombre">
            <label for="clave">Clave</label>
            <input type="password" id="clave" placeholder="Digite su clave" name="clave">
            <label for="Rol">Rol del usuario</label>
            <select id="Rol" name="rol">
                <option value="value" disabled>Indique el rol</option>
                <option value="admin">Administrador</option>
                <option value="cliente">Cliente</option>
                <option value="empleado">Empleado</option>
            </select>
            <label for="estado">Estado</label>
            <select id="estado" name="estado">
                <option value="estado" disabled>Estado</option>
                <option value="activo">Activo</option>
                <option value="inactivo">Inactivo</option>
            </select>
            <label id="imagen">Foto</label>
            <input type="file" id="id" name="imagen">
            <button type="submit" name="subir">Enviar</button>
        </form>
        
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
    </body>
</html>
