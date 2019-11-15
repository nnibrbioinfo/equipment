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

  function searchEquip(pageIdx){
    var data = new FormData($('#equipmentVO')[0]);
    data.append('pageIndex',pageIdx);
    
    $.ajax({
      url: '${path}/equipment/list.do',
      type: 'get',
      data: data,
      processData: false,
      contentType: false,
      cache: false,
      async: false
    });
  }

  
  function fn_go_page(pageNo) {
    $("#pageIndex").val(pageNo);
    $("#equipListForm").submit();
    return false;
  }
  
  function fn_search(){
    $("#pageIndex").val("1");
    $("#equipListForm").submit();
    return false;
  }
  
  $(function(){
    $('#equipmentSrchBtn').on('click',function(e){
      fn_search();
    })
    $('#equipName').keypress(function(e){
            if(e.which == 13){
          fn_search();
            }
        });
    
    $('.page-link').on('click',function(e){
      te = e;
    });
  });
  </script>


  <style>

.equip-info-main {
    border: 1px solid #ced4da;
    margin-bottom: 20px;
    margin-top: 10px;
    background: #fff;
    padding: 6px;
/*    -webkit-box-shadow: 0 1px 4px 0 rgba(21,180,255,0.5);
    box-shadow: 0 1px 1px 0 rgba(21,180,255,0.5);*/
}

.equip-info-main .equip-image {
    background-color: #EBF8FE;
    display: block;
    min-height: 150px;
    overflow: hidden;
    position: relative;
    border: 1px solid #ced4da;
}

.equip-info-main .product-deatil {
    /*border-bottom: 1px solid #dfe5e9;*/
    padding-bottom: 17px;
    /*padding-left: 16px;*/
    padding-top: 16px;
    position: relative;
    background: #fff
}

.product-content .product-deatil h5 a {
    color: #2f383d;
    font-size: 15px;
    line-height: 19px;
    text-decoration: none;
    padding-left: 0;
    margin-left: 0
}

.equip-info-main .product-deatil h5 a span {
    color: #9aa7af;
    display: block;
    font-size: 13px
}

.product-deatil .glyphicon {
    color: #3276b1
}

.product-deatil .equip-image {
    border-right: 0px solid #fff !important
}

.product-deatil .name {
    margin-top: 0;
    margin-bottom: 0
}

.product-deatil .name small {
    display: block
}

.product-deatil .name a {
    margin-left: 0
}

.product-deatil .price-container {
    font-size: 24px;
    margin: 0;
    font-weight: 300;
    
}

.product-deatil .price-container small {
    font-size: 12px;
    
}

.product-deatil .message-text {
    width: calc(100% - 70px)
}
/*--Button effect classes for add to cart*/ 
.btn-reserve{
    border-radius: 0;
    position: relative;
    /*color: #fff;*/
    /*border:1px solid transparent;*/
    text-transform: uppercase;
}

/*--Button effect classes for More info*/ 
.btn-info{
    border-radius: 0;
    position: relative;
    /*color: #fff;*/
    /*border:1px solid transparent;*/
    text-transform: uppercase;
}

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
            <a class="nav-link py-3 px-0 px-lg-3" href="${path}/reservation.do">예약 확인</a>
          </li>
        </ul>
      </div>

    </div>
  </nav>

  <!-- Masthead -->
  

  <!-- Icons Grid -->
  <section class="bg-light">
    <div class="container">

<form:form commandName="equipmentVO" id="equipListForm" role="form" method="get" action="${path}/equipment/list.do">
<div class="row">
  <div class="col-md-12">
        <div class="card">
            <div class="card-body">
                <div class="row">
                	<div class="col-md-12">
	                    <div class="form-inline">
	                        <div class="form-group mr-3 mt-1">
	                            <label class="col-form-label mr-3">카테고리</label>
	                            <form:select path="categoryId" class="form-control equipmentSearchOpt" id="parentCategoryIdSrch">
	                                <form:option selected="selected" value="" label="전체"></form:option>
	                                <form:options items="${categoryList}" itemValue="categoryId" itemLabel="categoryName" />
	                            </form:select>
	                        </div>
	                        <div class="form-group mr-3 mt-1">
	                            <label class="col-form-label mr-3">장비국명</label>
	                            <form:input path="equipName" type="text " class="form-control equipmentSearchOpt"/>
	                        </div>
	                        <div class="form-group mt-1 float-right">
	                            <button type="button" class="btn btn-default" id="equipmentSrchBtn">검색</button>
	                        </div>
	                    </div>
	                </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="row mt-3">
  <div class="col-sm-12">
    <div class="card">
      <div class="card-body">
        <div class="row">
          <c:forEach var="equip" items="${equipmentVO.equipList}" varStatus="status">
          
              <div class="col-sm-6">
                  <!-- First product box start here-->
                  <div class="equip-info-main prod-wrap clearfix">
                      <div class="row">
                              <div class="col-sm-5 col-xs-12">
                                  <div class="equip-image"> 
                                    <c:if test="${fn:length(equip.fileInfos) > 0}">
                                        <img src="/file/TEMP_<c:out value='${equip.fileInfos[0].uuid}'/>" class="img-responsive">
                                      </c:if>
                                  </div>
                              </div>
                              <div class="col-sm-7 col-xs-12">
                              <div class="product-deatil">
                                <div class="name">
                                  <a href="${path}/equipment/detail.do?id=${equip.equipId}" target="_blank">
                                  <c:out value='${equip.equipName}'/> / <c:out value='${equip.equipNameEn}'/>
                                  </a>
                                </div>
                                <div>
                                        <span><c:out value='${equip.category.parentCategory.categoryName}'/> / <c:out value='${equip.category.categoryName}'/></span>
                                </div>
                                  <div>
                                      <span><c:out value='${equip.manufacturer}'/></span>
                                  </div>
                              </div>
                              <div class="product-info smart-form">
                                  <div class="row">
                                      <div class="col-sm-12"> 
                                          <a href="${path}/equipment/detail.do?id=${equip.equipId}" target="_blank" class="btn btn-danger btn-reserve"><span>info</span></a>
                                          <a href="${path}/equipment/apply.do?id=${equip.equipId}" target="_blank" class="btn btn-info"><span>book</span></a>
                                      </div>
                                  </div>
                              </div>
                          </div>
                      </div>
                  </div>
                  <!-- end product -->
              </div>
          </c:forEach>
          <div class="col-lg-12">
          </div>
        </div>
        <div class="row">
          <div class="col-12">
            <form:hidden path="pageIndex" />
            <ul class="pagination">
              <li class="page-item"><a class="page-link" href="#" onclick="fn_go_page(<c:out value='${pageInfo.firstPageNo}'/>); return false;">처음</a></li>
              <c:if test="${pageInfo.firstPageNo ne pageInfo.firstPageNoOnPageList}">
              <li class="page-item"><a class="page-link" href="#" onclick="fn_go_page(<c:out value="${pageInfo.firstPageNoOnPageList - 1}"/>); return false;">이전</a></li>
              </c:if>
            <c:forEach begin="${pageInfo.firstPageNoOnPageList}" end="${pageInfo.lastPageNoOnPageList}" var="page" varStatus="status">
              <c:if test="${status.index eq pageInfo.currentPageNo}">
              <li class="page-item active"><a class="page-link" href="#" onclick="fn_go_page(<c:out value='${status.index}'/>); return false;"><c:out value="${status.index}"/></a></li>
              </c:if>
              <c:if test="${status.index ne pageInfo.currentPageNo}">
              <li class="page-item"><a class="page-link" href="#" onclick="fn_go_page(<c:out value='${status.index}'/>); return false;"><c:out value="${status.index}"/></a></li>
              </c:if>
            </c:forEach>
              <c:if test="${pageInfo.lastPageNo ne  pageInfo.lastPageNoOnPageList}">
              <li class="page-item"><a class="page-link" href="#" onclick="fn_go_page(<c:out value="${pageInfo.lastPageNoOnPageList + 1}"/>); return false;">다음</a></li>
              </c:if>
              <li class="page-item"><a class="page-link" href="#" onclick="fn_go_page(<c:out value="${pageInfo.lastPageNo}"/>); return false;">마지막</a></li>
            </ul>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>

</form:form>
      
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