<%-- 
    Document   : transferecia
    Created on : 6 mar 2022, 18:39:35
    Author     : PC
--%>

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
    <div class="columns">
       <form action="Servlettrasferencias" method="POST" class="container p-5 m-6 box">
        <h1 class="label" style="text-align: center">Transferir dinero</h1>
        <div class="field">
            <label class="label">Documento de la persona a transferir</label>
            <div class="control">
                <input class="input" type="number" placeholder="Documento" name="doc">
            </div>
          </div>
          
          <div class="field">
            <label class="label">Valor a transferir</label>
            <div class="control">
                <input class="input" type="number" placeholder="Nombre" name="val">
            </div>
          </div>
        <input type="submit" class="button is-info is-light is-medium is-rounded" value="Tranferir dinero" name="money" />
    </form> 
    </div>
    <div class="columns">
        <form action="Servlettrasferencias" method="POST" class="container p-5 m-6 box">
        <h1 class="label" style="text-align: center">Retirar dinero</h1>
        <div class="field">
            <label class="label">¿Cuanto desea retirar?</label>
            <div class="control">
                <input class="input" type="number" placeholder="Documento" name="val">
            </div>
          </div>
        <input type="submit" class="button is-info is-light is-medium is-rounded" value="Tranferir dinero" name="retiro" />
        </form>
    </div>
    
    </body>
</html>
