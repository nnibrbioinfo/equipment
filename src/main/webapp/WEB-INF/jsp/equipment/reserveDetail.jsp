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
  $(function(){
	  $('#download').on('click',function(e){
		  $('#applicationFormVO').submit();
	  })
  })
  </script>

<style>
	.table th, .table td {
		width: 25%;
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
                              <p class="lead">신청 정보</p>
                              <div class="table-responsive">
                                  <table class="table">
                                      <tbody>
                                          <tr>
                                              <th>이름</th>
                                              <td><c:out value="${reservInfo.applicant}"></c:out></td>
                                              <th>소속</th>
                                              <td><c:out value="${reservInfo.affiliation}"></c:out></td>
                                          </tr>
                                          <tr>
                                              <th>접수번호</th>
                                              <td>NNIBREQ<c:out value="${reservInfo.applicationId}"></c:out></td>
                                              <th>전자우편</th>
                                              <td><c:out value="${reservInfo.email}"></c:out></td>
                                          </tr>
                                          <tr>
                                              <th>이용일자</th>
                                              <td><c:out value="${reservInfo.startDate}"></c:out> ~ <c:out value="${reservInfo.endDate}"></c:out></td>
                                              <th>이용시각</th>
                                              <td><c:out value="${reservInfo.hours}"></c:out>시간</td>
                                          </tr>
                                          <tr>
                                              <th>신청서</th>
                                              <td><a href="#" id="download">다운로드</a></td>
                                          </tr>
                                      </tbody>
                                  </table>
                              </div>
                          </div>
                      </div>
                      <div class="row">
                          <div class="col-lg-12">
                              <p class="lead">신청 장비 정보</p>
                              <div class="table-responsive">
                                  <table class="table">
                                      <tbody>
                      <tr>
                          <th style="width:25%">장비 이름</th>
                          <td><c:out value="${reservInfo.equipmentVO.equipName}"></c:out></td>
                          <th style="width:25%">장비 위치</th>
                          <td><c:out value="${reservInfo.equipmentVO.locationVO.locationName}"></c:out></td>
                      </tr>
                      <tr>
                          <th style="width:25%">허가 여부</th>
                          <td>
                            <c:if test="${empty reservInfo.isPermission}">
                              <h5><span class="badge badge-secondary">대기</span></h5>
                            </c:if>
                            <c:if test="${reservInfo.isPermission eq true}">
                              <h5><span class="badge badge-success">수락</span></h5>
                            </c:if>
                            <c:if test="${reservInfo.isPermission eq false}">
                              <h5><span class="badge badge-danger">거부</span></h5>
                            </c:if>
                          </td>
                          <c:if test="${empty reservInfo.isPermission}">
                            <td><span>관리자가 확인중 이오니 기다려주세요</span></td>
                          </c:if>
                          <c:if test="${reservInfo.isPermission eq true}">
                          <th style="width:25%">장비 번호</th>
                          <td><c:out value="${reservInfo.assetNoInfoVO.assetNo}"></c:out></td>
                          </c:if>
                          <c:if test="${reservInfo.isPermission eq false}">
                          <th style="width:25%">거부 사유</th>
                          <td><c:out value="${reservInfo.denyComment}"></c:out></td>
                          </c:if>
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
	<form:form commandName="applicationFormVO" action="${path}/reservation/document.do" method="post">
		<form:hidden path="email"/>
		<form:hidden path="applicationCodeId"/>
		<form:hidden path="passwd"/>
	</form:form>

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