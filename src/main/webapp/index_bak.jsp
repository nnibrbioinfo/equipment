<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="path" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>

  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

  <title>NNIBR 연구장비예약</title>


  <!-- Bootstrap core JavaScript -->
  <script src="${path}/plugins/equip/jquery/jquery.min.js"></script>
  <script src="${path}/plugins/equip/bootstrap/js/bootstrap.bundle.min.js"></script>
  
  <!-- Bootstrap core CSS -->
  <link href="${path}/plugins/equip/bootstrap/css/bootstrap.min.css" rel="stylesheet">

  <!-- Custom fonts for this template -->
  <link href="${path}/plugins/equip/fontawesome-free/css/all.min.css" rel="stylesheet">
  <link href="${path}/plugins/equip/simple-line-icons/css/simple-line-icons.css" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Lato:300,400,700,300italic,400italic,700italic" rel="stylesheet" type="text/css">

  <!-- Custom styles for this template -->
  <link href="${path}/css/landing-page.css" rel="stylesheet">

  <script type="text/javascript">
  </script>
  <style>
  </style>

</head>
<body>

  <!-- Navigation -->
  <nav class="navbar navbar-light navbar-expand-lg bg-light static-top">
    <div class="container">
      <a class="navbar-brand" href="#">연구장비예약</a>

      <button class="navbar-toggler navbar-toggler-right text-uppercase font-weight-bold bg-primary text-white rounded" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
        Menu
        <i class="fas fa-bars"></i>
      </button>

      <div class="collapse navbar-collapse" id="navbarResponsive">
        <ul class="navbar-nav ml-auto">
          <li class="nav-item mx-0 mx-lg-1">
            <a class="nav-link py-3 px-0 px-lg-3" href="${path}/equipment/list.do">장비 검색</a>
          </li>
          <%--
          <li class="nav-item mx-0 mx-lg-1">
            <a class="nav-link py-3 px-0 px-lg-3" href="#about">장비 예약</a>
          </li>
           --%>
          <li class="nav-item mx-0 mx-lg-1">
            <a class="nav-link py-3 px-0 px-lg-3" href="#contact">예약 확인</a>
          </li>
        </ul>
      </div>

    </div>
  </nav>

  <!-- Masthead -->
  

  <!-- Icons Grid -->
  <section class="features-icons bg-light text-center">
    <div class="container">
      <div class="row">
        <div class="col-lg-6">
          <div class="features-icons-item mx-auto mb-5 mb-lg-0 mb-lg-3">
            <div class="features-icons-icon d-flex">
              <i class="icon-screen-desktop m-auto text-primary"></i>
            </div>
            <h3>장비 검색</h3>
            <p class="lead mb-0"></p>
          </div>
        </div>
        <%--
        <div class="col-lg-4">
          <div class="features-icons-item mx-auto mb-5 mb-lg-0 mb-lg-3">
            <div class="features-icons-icon d-flex">
              <i class="icon-layers m-auto text-primary"></i>
            </div>
            <h3>장비 예약</h3>
            <p class="lead mb-0"></p>
          </div>
        </div>
         --%>
        <div class="col-lg-6">
          <div class="features-icons-item mx-auto mb-0 mb-lg-3">
            <div class="features-icons-icon d-flex">
              <i class="icon-check m-auto text-primary"></i>
            </div>
            <h3>예약 확인</h3>
            <p class="lead mb-0"></p>
          </div>
        </div>
      </div>
    </div>
  </section>
  

  <!-- Footer -->
  <footer class="footer bg-light">
    <div class="container">
      <div class="row">
        <div class="col-lg-12 h-100 text-center my-auto">
          <p class="text-muted mb-4 mb-lg-0">
            (우) 37242 경상북도 상주시 도남2길 137 (도남동) 국립낙동강생물자원관  054-530-0700
          </p>
          <p class="text-muted mb-4 mb-lg-0">
            Copyright © <a href="http://www.nnibr.re.kr">Nakdonggang National Institute of Biological Resources</a>.
          </p>
        </div>
      </div>
    </div>
  </footer>
</body>
</html>