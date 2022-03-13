<%-- 
    Document   : vistacliente
    Created on : 28/02/2022, 4:05:20 p. m.
    Author     : SENA
--%>
<%
    HttpSession sesion = request.getSession();
    String usu = (String) sesion.getAttribute("usuario");
    String rol = (String) sesion.getAttribute("rol");
    String foto = (String) sesion.getAttribute("foto");
    String nom = (String) sesion.getAttribute("nombre");
    String ape = (String) sesion.getAttribute("apellido");
    String cor = (String) sesion.getAttribute("correo");
    String doc = (String) sesion.getAttribute("documento");
    String cla = (String) sesion.getAttribute("password");
    String cel = (String) sesion.getAttribute("celular");
    String sex = (String) sesion.getAttribute("sexo");
    String fec = (String) sesion.getAttribute("fecha");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Vista cliente</title>
    <link rel="stylesheet" href="css/styles.css">
    <link rel="stylesheet" href="bulma/css/bulma-rtl.css">
    <link rel="stylesheet" href="bulma/css/bulma-rtl.min.css">
    <link rel="stylesheet" href="bulma/css/bulma.css">
</head>
<body>
    <nav class="navbar-" style="background-color: #3D63EB;" role="navigation" aria-label="main navigation">
        <div class="navbar-brand">
          <a class="navbar-item" href="https://bulma.io">
            <img src="https://bulma.io/images/bulma-logo.png" width="112" height="28">
          </a>
      
          <a role="button" class="navbar-burger" aria-label="menu" aria-expanded="false" data-target="navbarBasicExample">
            <span aria-hidden="true"></span>
            <span aria-hidden="true"></span>
            <span aria-hidden="true"></span>
          </a>
        </div>
      
        <div id="navbarBasicExample" class="navbar-menu">
          <div class="navbar-start">
              <a class="navbar-item has-text-white" href="vistacliente.jsp">
              Mis datos personales
            </a>
      
              <a class="navbar-item has-text-white" href="lineasind.jsp">
              Mis lineas
            </a>
      
            <div class="navbar-item has-dropdown is-hoverable">
              <a class="navbar-link has-text-white">
                Mas
              </a>
      
              <div class="navbar-dropdown">
                  <a class="navbar-item " href="creditoind.jsp">
                  Mis creditos
                </a>
                  <a class="navbar-item" href="transferencia.jsp">
                  Tranferencias
                </a>
              </div>
            </div>
          </div>
      
          <div class="navbar-end">
            <div class="navbar-item">
              <div class="buttons">
                <a class="button is-light" href="/bancoo">
                  Salir
                </a>
              </div>
            </div>
          </div>
        </div>
      </nav>
    <form action="Servletcliente" method="GET" class="container m-6 p-4 box">
        <h1 class="label">Mis datos</h1>
        <div class="field">
            <label class="label">Documento</label>
            <div class="control">
                <input class="input" value="<%=doc%>" type="number" placeholder="Documento" name="adoc">
            </div>
          </div>
          
          <div class="field">
            <label class="label">Nombre</label>
            <div class="control">
                <input class="input" value="<%=nom%>" type="text" placeholder="Nombre" name="anom">
            </div>
          </div>
          <div class="field">
            <label class="label">Apellido</label>
            <div class="control">
                <input class="input" value="<%=ape%>" type="text" placeholder="Apellido" name="aape">
            </div>
          </div>
          
          <div class="field">
            <label class="label">Correo</label>
            <div class="control">
                <input class="input" value="<%=cor%>" type="email" placeholder="e.g. alexsmith@gmail.com" name="aema">
            </div>
          </div>
          <div class="field">
            <label class="label">Celular</label>
            <div class="control">
                <input class="input" value="<%=cel%>" type="number" placeholder="Numero de telefono" name="anum">
            </div>
          </div>
          <div class="field">
            <label class="label">Sexo</label>
            <div class="control">
                <input class="input" value="<%=sex%>" type="text" placeholder="Masculino, Femenino, No binario" name="sexo">
            </div>
          </div>
          <div class="field">
            <label class="label">Fecha de nacimiento</label>
            <div class="control">
                <input class="input" value="<%=fec%>" type="date" placeholder="Fecha de nacimiento" name="fecnac">
            </div>
          </div>
            <button class="button is-success m-2" type="submit" name="update">Actualizar mi informacion</button>
        </form>
            <form action="Servletusuario" method="post" class="container m-6 p-4 box">
            <h1 class="label">Actualizar Usuario </h1>
            <div class="field">
                <label class="label">Usuario</label>
                <div class="control">
                    <input class="input" type="text" placeholder="e.g Alex Smith" value="<%=usu%>" name="ausu">
                </div>
              </div>
              
              <div class="field">
                <label class="label">Contraseña</label>
                <div class="control">
                    <input class="input" value="<%=cla%>" type="password" placeholder="password" name="acla">
                </div>
              </div>

            <button class="button is-success m-2" name="actid">Actualizar mi informacion</button>
            </form>
</body>
</html>
