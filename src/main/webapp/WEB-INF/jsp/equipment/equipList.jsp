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
if (!('remove' in Element.prototype)) {
    Element.prototype.remove = function() {
        if (this.parentNode) {
            this.parentNode.removeChild(this);
        }
    };
}


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


	var categoryTree = new Map();
	var categoryMap = new Map();
	<c:forEach items="${categoryTree.categoryTree}" var="ct">
	categoryTree.set(${ct.key},${ct.value});
	</c:forEach>
	<c:forEach items="${categoryTree.categoryMap}" var="ct">
	categoryMap.set(${ct.key},'${ct.value}');
	</c:forEach>
	
	$(function(){
		var cId = '<c:out value="${equipmentVO.categoryIdSrch}"></c:out>';
		var pcId = '<c:out value="${equipmentVO.parentCategoryIdSrch}"></c:out>';
		
		$('#parentCategoryIdSrch').on('change',function(e){
			console.log("parentCategory Changed!");
			$('#categoryIdSrch').children().each(function(i,e){e.remove()});
            $('#categoryIdSrch').append('<option value="0">전체</option>');
            var pcIdx = Number(e.target.value);
            if (pcIdx != 0){
                categoryTree.get(pcIdx).forEach(function(v,i,a){
                	var ac = '<option value='+v+'>'+categoryMap.get(v)+'</option>';
                	$('#categoryIdSrch').append(ac);
                });	
            }
            if(cId != '' && cId != -1){
            	$('#categoryIdSrch').val(cId);
            	cId = -1;
            }
		});

		if(pcId){
			$('#parentCategoryIdSrch').val(pcId);
			console.log("parentCategory Change trigger");
			$('#parentCategoryIdSrch').trigger('change');
		}
		
		$('#equipmentSrchBtn').on('click',function(e){
			fn_search();
		});
		
		$('.equipmentSearchOpt').keypress(function(e){
			if(e.which == 13){
				fn_search();
			}
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
        display: block;
        min-height: 150px;
        overflow: hidden;
        position: relative;
        border: 1px solid #ced4da;
    }
    
    .equip-image img {
    	min-width: 100%;
    	min-height: 100%;
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
    
    .equip-info-main .equip-detail {
        /*border-bottom: 1px solid #dfe5e9;*/
        position: relative;
        background: #fff
    }
    
    .equip-detail .category {
    	font-size: 13px;
    	padding-top: 5px;
    }
    
    .equip-detail .manufacturer {
    	font-size: 11px;
    	padding-top: 5px;
    }
    
    .equip-detail .reservInfo {
    	position: absolute;
    	top: 120px;
    }
    
    .equip-detail .glyphicon {
        color: #3276b1
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
    .btn-primary{
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

<form:form commandName="equipmentVO" id="equipListForm" role="form" method="get" action="${path}/equipment/list.do">
<div class="row">
  <div class="col-md-12">
        <div class="card">
            <div class="card-body">
                <div class="row">
                  <div class="col-md-12">
                      <div class="form-inline">
                          <div class="form-group mr-3 mt-1">
                              <label class="col-form-label mr-3">대분류</label>
                              <select class="form-control equipmentSearchOpt" id="parentCategoryIdSrch" name="parentCategoryIdSrch">
                              <option value="0">전체</option>
                              <c:forEach var="pc" items="${categoryTree.parentCategoryMap}" varStatus="status">
                              <option value="<c:out value="${pc.key}"></c:out>"><c:out value="${pc.value}"></c:out></option>
                              </c:forEach>
                              </select>
                          </div>
                          <div class="form-group mr-3 mt-1">
                              <label class="col-form-label mr-3">중분류</label>
                              <select class="form-control equipmentSearchOpt" id="categoryIdSrch" name="categoryIdSrch">
                              <option value="0">전체</option>
                              </select>
                          </div>
                          <div class="form-group mt-1 float-right">
                              <button type="button" class="btn btn-light" id="equipmentSrchBtn"><i class="fas fa-search"></i>검색</button>
                          </div>
                      </div>
                  </div>
                </div>
                
                <div class="row">
                  <div class="col-md-12">
                      <div class="form-inline">
                          <div class="form-group mr-3 mt-1">
                              <label class="col-form-label mr-3">장비국명</label>
                              <form:input path="equipName" type="text " class="form-control equipmentSearchOpt"/>
                          </div>
                          <div class="form-group mr-3 mt-1">
                              <label class="col-form-label mr-3">장비영문명</label>
                              <form:input path="equipNameEn" type="text " class="form-control equipmentSearchOpt"/>
                          </div>
                          <div class="form-group mr-3 mt-1">
                              <label class="col-form-label mr-3">제조사</label>
                              <form:input path="manufacturer" type="text " class="form-control equipmentSearchOpt"/>
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
								<a href="${path}/equipment/detail.do?id=${equip.equipId}" target="_blank">
									<img src="${path}/image.do?image=THUMB_<c:out value='${equip.fileInfos[0].uuid}'/>" class="img-responsive">
									</a>
								</c:if>
							</div>
						</div>
						<div class="col-sm-7 col-xs-12">
							<div class="equip-detail">
								<div class="name">
									<a href="${path}/equipment/detail.do?id=${equip.equipId}" target="_blank">
										<c:out value='${equip.equipName}'/> <br> <c:out value='${equip.equipNameEn}'/>
									</a>
								</div>
								<div class="category">
									<c:out value='${equip.category.categoryName}'/>
								</div>
								<div class="manufacturer">
									<span><c:out value='${equip.manufacturer}'/></span>
								</div>
								<div class="reservInfo">
									<!-- <button type="button" class="btn btn-primary"><i class="fas fa-history"></i></button> -->
									<a href="${path}/equipment/apply.do?id=${equip.equipId}" target="_blank" class="btn btn-primary"><i class="far fa-clock"></i><small class="ml-1">장비예약</small></a>
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