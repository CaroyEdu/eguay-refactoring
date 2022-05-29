<%--
  Created by IntelliJ IDEA.
  User: parsa
  Date: 5/28/2022
  Time: 6:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!doctype html>
<html lang="en">
<head>
  <title>eguay - Inicio</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

  <link href="https://fonts.googleapis.com/css?family=Lato:300,400,700&display=swap" rel="stylesheet">

  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

  <link rel="stylesheet" href="css/stylesignup.css">

</head>
<body>
<section class="ftco-section">
  <div class="container">
    <div class="row justify-content-center">

    </div>
    <div class="row justify-content-center">

      <div class="wrap d-md-flex">

        <div class="container-fluid">
          <div class="d-flex">
            <div class="w-100">
              <h3 class="mb-4">Iniciar Sesión</h3>
            </div>
          </div>
          <form method="POST" action="/register" class="signin-form" >
            <div class="form-group mb-3">
              <label class="label" for="username">Usuario</label>
              <input name="username" id="username" type="text" class="form-control" placeholder="usuario" required>

            </div>
            <div class="form-group mb-3">
              <label class="label" for="email">email</label>
              <input name="email"  id="email" type="text" class="form-control" placeholder="email" required>

            </div>
            <div class="form-group mb-3">
              <label class="label" for="name">Nombre</label>
              <input name="name" id="name" type="text" class="form-control" placeholder="nombre" required>

            </div>
            <div class="form-group mb-3">
              <label class="label" for="surname">Apellido</label>
              <input name="surname" id="surname" type="text" class="form-control" placeholder="apellido" required>
            </div>

            <div class="form-group mb-3">
              <label class="label" for="address">Domicilio</label>
              <input name="address" id="address" type="text" class="form-control" placeholder="domicilio" >
            </div>

            <div class="form-group mb-3">
              <label class="label" for="country">Pais</label>
              <input name="country" id="country" type="text" class="form-control" placeholder="pais" >
            </div>

            <div class="form-group mb-3">
              <label class="label" for="name">Ciudad</label>
              <input name="city" type="text" class="form-control" placeholder="ciudad" >
            </div>
            <div class="form-group mb-3">
              <label class="label" for="birthday">Fecha de nacimiento</label>
              <input name="birthday" id="birthday" type="date" class="form-control" required>
            </div>
            <div class="input-group">
              <label class="label">sexo</label>
              <div class="rs-select2 js-select-simple select--no-search">
                <select name="sex">
                  <option>Hombre</option>
                  <option>Mujer</option>
                  <option>No especificar</option>
                </select>
                <div class="select-dropdown"></div>
              </div>
            </div>
            <div class="form-group mb-3">
              <label class="label" for="password">Contraseña</label>
              <input name="password" id="password" type="password" class="form-control" placeholder="password" required>
            </div>
            <input type="submit" value="Añadir"/>
          </form>
        </div>
      </div>
    </div>
  </div>
  </div>
</section>

<script src="js/jquery.min.js"></script>
<script src="js/popper.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/main.js"></script>

</body>
</html>



