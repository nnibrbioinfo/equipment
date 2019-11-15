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
  <meta name="description" content="">
  <meta name="author" content="">

  <title>NNIBR 연구장비예약</title>

  <!-- Bootstrap core JavaScript -->
  <script src="${path}/plugins/equip/jquery/jquery.min.js"></script>
  <script src="${path}/plugins/equip/bootstrap/js/bootstrap.bundle.min.js"></script>

  <!-- Layout -->
  <script src="${path}/js/layout.js"></script>

  <!-- Bootstrap core CSS -->
  <link href="${path}/plugins/equip/bootstrap/css/bootstrap.min.css" rel="stylesheet">

  <!-- Custom fonts for this template -->
  <link href="${path}/plugins/equip/fontawesome-free/css/all.min.css" rel="stylesheet">
  <link href="${path}/plugins/equip/simple-line-icons/css/simple-line-icons.css" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Lato:300,400,700,300italic,400italic,700italic" rel="stylesheet" type="text/css">

  <!-- Custom styles for this template -->
  <link href="${path}/css/landing-page.css" rel="stylesheet">

  <script type="text/javascript">
  var path = '${path}';
  $(function(){
	  $("span, .overlay").click(function () {
	      $(".show").fadeOut();
	  });
	  
	  $('#equipImges img').on('click',function(e){
		  tt = this;
		  te = e;
		  console.log(e);
		  var imagePath = e.target.src.split(/THUMB_(.+)/)[1];
	      $(".img-show img").attr("src", path+'/image.do?image='+imagePath);
	      $(".show").fadeIn();
	  })
  });
  

  </script>

  <style>
  
  .equipDesc {
  	font-weight: bold;
  }
  
  .table td, .table th {
  	padding: 1rem;
  }
    .equip-image {
        display: block;
        height: 150px;
        width: 190px;
        overflow: hidden;
        position: relative;
        border: 1px solid #ced4da;
    }
    
    .equip-image img {
        height: 150px;
        width: 190px;
        cursor: zoom-in;
    }
    
    .btn-primary{
        border-radius: 0;
        position: relative;
        /*color: #fff;*/
        /*border:1px solid transparent;*/
        text-transform: uppercase;
    }
    
        .show{
            z-index: 999;
            display: none;
        }
        .show .overlay{
            z-index: 1000;
            width: 100%;
            height: 100%;
            background: rgba(0,0,0,.66);
            position: absolute;
            top: 0;
            left: 0;
        }
        .show .img-show{
            z-index: 1001;
            width: 1000px;
            height: 750px;
            /*width: 300px;*/
            /*height: 100%;*/
            background: #FFF;
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%,-50%);
            overflow: hidden;
        }
        .img-show span{
            position: absolute;
            top: 10px;
            right: 10px;
            z-index: 99;
            cursor: pointer;
        }
        .img-show img{
            position: absolute;
            max-width: 100%;
            max-height: 100%;
            width: auto;
            height: auto;
            margin: auto;
            top: 0;
            bottom: 0;
            left: 0;
            right: 0;
        }
  </style>
</head>

<body>

  <!-- Navigation -->
  <nav class="main-header navbar navbar-light navbar-expand-lg bg-light static-top">
    <div class="container">
      <a class="navbar-brand" href="${path}"><img alt="nnibr" width="65" height="30" src="${path}/images/equip/nnibr_logo.png"><span>국립낙동강생물자원관 연구장비예약</span></a>

      <button class="navbar-toggler navbar-toggler-right text-uppercase font-weight-bold bg-primary text-white rounded" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
        Menu
        <i class="fas fa-bars"></i>
      </button>

      <div class="collapse navbar-collapse" id="navbarResponsive">
        <ul class="navbar-nav ml-auto">
          <li class="nav-item mx-0 mx-lg-1">
            <a class="nav-link py-3 px-0 px-lg-3" href="${path}/equipment/list.do">장비 검색</a>
          </li>
          <li class="nav-item mx-0 mx-lg-1">
            <a class="nav-link py-3 px-0 px-lg-3" href="${path}/reservation.do">예약 확인</a>
          </li>
        </ul>
      </div>

    </div>
  </nav>

  <!-- Masthead -->


  <!-- Icons Grid -->
  <section class="features-icons bg-light">
    <div class="content-wrapper container">
      <div class="row">
        <div class="col-md-12">
          <div class="card">
            <div class="card-body">
              <div class="row">
                <div class="col-lg-12">
                  <p class="lead">장비 정보</p>
                  <div class="table-responsive">
                    <table class="table">
                      <tbody>
                        <tr>
                          <th style="width:25%"> 
                              <div id="equipImges" class="carousel slide equip-image" data-ride="carousel">
                                  <ol class="carousel-indicators">
                                  	  <c:forEach items="${equip.fileInfos}" var="fileInfo" varStatus="status">
                                  	  	<c:if test="${status.index eq 0}">
                                  	  		<c:set var="fileInfoClass" value="active" />
                                  	  	</c:if>
                                  	  	<c:if test="${status.index ne 0}">
                                  	  		<c:set var="fileInfoClass" value="" />
                                  	  	</c:if>
                                      	<li data-target="#equipImges" data-slide-to="${status.index}" class="${fileInfoClass}"></li>
                                  	  </c:forEach>
                                  </ol>
                                  <div class="carousel-inner">
                                  	  <c:forEach items="${equip.fileInfos}" var="fileInfo" varStatus="status">
                                  	  	<c:if test="${status.index eq 0}">
                                  	  		<c:set var="imgClass" value="carousel-item active" />
                                  	  	</c:if>
                                  	  	<c:if test="${status.index ne 0}">
                                  	  		<c:set var="imgClass" value="carousel-item" />
                                  	  	</c:if>
	                                  	<div class="${imgClass}">
	                                  		<img src="${path}/image.do?image=THUMB_<c:out value='${fileInfo.uuid}'/>" class="img-responsive">
	                                  	</div>
                                  	</c:forEach>
                                  </div>
                                  <a class="carousel-control-prev" href="#equipImges" role="button" data-slide="prev">
                                      <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                      <span class="sr-only">Previous</span>
                                  </a>
                                  <a class="carousel-control-next" href="#equipImges" role="button" data-slide="next">
                                      <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                      <span class="sr-only">Next</span>
                                  </a>
                              </div>
                          </th>
                          <td style="width:25%; vertical-align: middle;">
                            <div><strong><c:out value="${equip.equipName}"/></strong></div>
                            <div><strong><c:out value="${equip.equipNameEn}"/></strong></div>
                            <div><small><c:out value='${equip.category.parentCategory.categoryName}'/> / <c:out value='${equip.category.categoryName}'/></small></div>
                            <div class="mt-3">
								<!-- <button type="button" class="btn btn-info"><i class="fas fa-history"></i></button> -->
								<a href="${path}/equipment/apply.do?id=${equip.equipId}" target="_blank" class="btn btn-primary"><i class="far fa-clock"></i><small>장비예약</small></a>
							</div>
                          </td>
                          <th style="width:25%"></th>
                          <td style="width:25%"></td>
                        </tr>
                        <tr>
                          <th>자산명</th>
                          <td><c:out value="${equip.assetName}"/></td>
                          <th>품명</th>
                          <td><c:out value="${equip.itemName}"/></td>
                        </tr>
                        <tr>
                          <th>제조사</th>
                          <td><c:out value="${equip.manufacturer}"/></td>
                          <th>수량</th>
                          <td><c:out value="${equip.quantity}"/></td>
                        </tr>
                        <tr>
                          <th>기기상태</th>
                          <td><c:if test="${equip.equipStatusType eq 'USEABLE'}">사용가능</c:if>
                          <c:if test="${equip.equipStatusType eq 'USED'}">사용중</c:if>
                          <c:if test="${equip.equipStatusType eq 'UNUSEABLE'}">사용불가</c:if>
                          <c:if test="${equip.equipStatusType eq 'REPAIR'}">수리중</c:if></td>
                          <th></th>
                          <td></td>
                        </tr>
                      </tbody>
                    </table>
                  </div>
                  <p class="lead">관리담당자</p>
                  <div class="table-responsive">
                    <table class="table">
                      <tbody>
                        <tr>
                          <th>담당자</th>
                          <td><c:out value="${equip.managerVO.name}"/></td>
                          <th>전화</th>
                          <td><c:out value="${equip.managerVO.phone}"/></td>
                          <th>위치</th>
                          <td><c:out value="${equip.locationVO.locationName}"/></td>
                        </tr>
                      </tbody>
                    </table>
                  </div>

                  <p class="lead">사양 및 활용분야</p>
                  <div class="table-responsive">
                    <table class="table">
                      <tbody>
                        <tr>
                          <td style="width:33%;">
                            <p class="equipDesc">원리 및 특성</p>
                            <% pageContext.setAttribute("newLineChar", "\n"); %>
                            <c:forEach var="desc" items="${fn:split(equip.description,newLineChar)}">
                            <li><c:out value="${desc}"/></li>
                            </c:forEach>
                          </td>
                          <td style="width:33%;">
                            <p class="equipDesc">구성 및 성능</p>
                            <c:forEach var="desc" items="${fn:split(equip.configuration,newLineChar)}">
                            <li><c:out value="${desc}"/></li>
                            </c:forEach>
                          </td>
                          <td style="width:33%;">
                            <p class="equipDesc">활용분야</p>
                            <c:forEach var="desc" items="${fn:split(equip.utilization,newLineChar)}">
                            <li><c:out value="${desc}"/></li>
                            </c:forEach>
                          </td>
                          </tr>
                      </tbody>
                    </table>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      
      <div class="show">
        <div class="overlay"></div>
          <div class="img-show">
            <span>X</span>
            <img src="">
          </div>
      </div>
        </div>
      </div>
  </section>
  <!-- Footer -->
  <footer class="main-footer footer bg-light">
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