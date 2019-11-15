<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="path" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>연구장비 공동활용 신청서</title>


<!-- Jquery -->

<script src="${path}/plugins/equip/jquery/jquery.min.js"></script>

<!-- bootstrap4 -->
<script src="${path}/plugins/equip/bootstrap/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="${path}/plugins/equip/bootstrap/css/bootstrap.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>


<link href="${path}/plugins/equip/fontawesome-free/css/all.min.css" rel="stylesheet">

<script src="${path}/js/layout.js"></script>

<!-- datepicker -->
<script src="${path}/js/datepicker/js/bootstrap-datepicker.min.js"></script>
<script src="${path}/js/datepicker/js/locales/bootstrap-datepicker.ko.min.js"></script>
<link rel="stylesheet" href="${path}/js/datepicker/css/bootstrap-datepicker.min.css">

<!-- moments -->
<script src="${path}/plugins/equip/moment/moment.min.js"></script>

<!-- signaturepad -->
<script src="${path}/js/signaturepad/jq-signature.js"></script>

<!-- daum adress -->
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<!-- google recaptcha -->
<script src="https://www.google.com/recaptcha/api.js" async defer></script>

<link href="${path}/plugins/equip/simple-line-icons/css/simple-line-icons.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Lato:300,400,700,300italic,400italic,700italic" rel="stylesheet" type="text/css">

<!-- Custom styles for this template -->
<link href="${path}/css/landing-page.css" rel="stylesheet">


<script type="text/javascript">


	$(function(){
		var dateMap = new Map();
				<c:forEach items="${dateMap}" var="dateList" varStatus="lStatus">
				dateMap.set(${dateList.key},[
				<c:forEach items="${dateList.value}" var="date" varStatus="dStatus">
				'<c:out value="${date}"/>'
				<c:if test="${!dStatus.last}">
				,
				</c:if>
				</c:forEach>
				])
				</c:forEach>
		var assetNoInfoCnt = <c:out value="${assetNoInfoCnt}"/>;

		tdm = dateMap;
		tac = assetNoInfoCnt;
		var dateLimit = dateMap.size < <c:out value="${assetNoInfoCnt}"/>+1 ? false : true;  
		console.log(dateLimit);
		tdm = dateMap;
		 $('#signature-pad').jqSignature();
		 var blankData = $('#signature-pad').jqSignature('getDataURL');
		 var data;
		 
		 
		 $('#clear').on('click',function(e){
		 	$('#signature-pad').jqSignature('clearCanvas');
		 	data='';
		 })

		 $("#applicationForm").submit(function() {
		  	
		   $('#signature').val('');
		   
		   data = $('#signature-pad').jqSignature('getDataURL');
		  
		   if(data == blankData){
		 	  alert("서명해 주세요");
		 	  
		 	  return false;
		   }else{
		 	  $('#signature').val(data);
		   }
		   
		   var response = grecaptcha.getResponse();
		   if(response.length == 0) 
		   { 
		     //reCaptcha not verified
		     alert("로봇이 아닙니다. 체크해 주세요"); 

		     return false;
		   }

		   return true;
		 });

		 var disabledDates = dateLimit ? dateMap.get(0) : null;
		 $('.input-daterange').datepicker({
		     autoclose: true,
		     language: 'ko',
		     format: 'yyyy-mm-dd',
		     autoclose: 1,
		     todayHighlight: 1,
		     daysOfWeekHighlighted: "0",
		     todayBtn: true,
		     clearBtn: true,
		     forceParse: 0,
		     startDate: new Date(),
		     daysOfWeekDisabled: [0,6],
		     datesDisabled: disabledDates
		 });

		$("#startDate").on("change", function (e) {
			var endDate = setMaxDates(e.target.value);
			console.log('endDate : ' + endDate);
		    $('#endDate').datepicker('setStartDate', e.target.value);
		    $('#endDate').datepicker('clearDates');
		    $('#endDate').datepicker('setEndDate', '');
			if(endDate!=undefined)$('#endDate').datepicker('setEndDate', endDate);
		});
		

		function setMaxDates(selectedDate){
			var sdt = new Date(selectedDate).getTime(); // selected date time
			var md; //maxDate
			var cs; //current status
			var rc = false; // return Chk
			if(dateMap.size == 1) return null;
			if(dateMap.size < assetNoInfoCnt +1) return null;

			dateMap.forEach(function(v,k){
				if(k != 0 && !rc){
					var sdc = false; // select date check
					var mdc = false; // max date check
					v.forEach(function(v2,k2){
						console.log('v2 : ' + v2);
						if(!sdc && !mdc){
							var i2t = new Date(v2).getTime();
							if(i2t == sdt) {
								sdc = true;
							} else if(i2t > sdt) {
								mdc = true;
								md = md && new Date(md) > new Date(i2t) ? md : v2;
							}
						}
					})
					if(!sdc && !mdc){
						rc = true;
					}
					//console.log(sdc,mdc)
				}
			})
			return rc ? null : moment(md).clone().add(-1,'days').format("YYYY-MM-DD");
		}
		
		$('#searchPostCode').on('click',function(e){
			searchPostCode();
		})

		 function searchPostCode(){
		    new daum.Postcode({
		        oncomplete: function(data) {
		        	 // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

		            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
		            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
		            var addr = ''; // 주소 변수
		            var extraAddr = ''; // 참고항목 변수
		            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
		            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
		                addr = data.roadAddress;
		            } else { // 사용자가 지번 주소를 선택했을 경우(J)
		                addr = data.jibunAddress;
		            }

		            // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
		            if(data.userSelectedType === 'R'){
		                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
		                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
		                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
		                    extraAddr += data.bname;
		                }
		                // 건물명이 있고, 공동주택일 경우 추가한다.
		                if(data.buildingName !== '' && data.apartment === 'Y'){
		                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
		                }
		                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
		                if(extraAddr !== ''){
		                    extraAddr = ' (' + extraAddr + ')';
		                }

		            } 

		            // 우편번호와 주소 정보를 해당 필드에 넣는다.
		            document.getElementById('zipcode').value = data.zonecode;
		            document.getElementById("address").value = addr;
		            // 커서를 상세주소 필드로 이동한다.
		            document.getElementById("detailAddress").focus();
		        }
		        
		    }).open();
		  }
	})

</script>

<style>
.user_privacy .card-body {
    max-height: 300px;
    min-height: 300px;
    overflow-y: scroll;
}

/* Tooltip container */
.tooltip {
  position: relative;
  display: inline-block;
  border-bottom: 1px dotted black; /* If you want dots under the hoverable text */
}

/* Tooltip text */
.tooltip .tooltiptext {
  visibility: hidden;
  width: 120px;
  background-color: black;
  color: #fff;
  text-align: center;
  padding: 5px 0;
  border-radius: 6px;
 
  /* Position the tooltip text - see examples below! */
  position: absolute;
  z-index: 1;
}

/* Show the tooltip text when you mouse over the tooltip container */
.tooltip:hover .tooltiptext {
  visibility: visible;
}
.tooltip .tooltiptext::after {
  content: " ";
  position: absolute;
  top: 100%; /* At the bottom of the tooltip */
  left: 50%;
  margin-left: -5px;
  border-width: 5px;
  border-style: solid;
  border-color: black transparent transparent transparent;
}

</style>
</head>

<body>


  <!-- Navigation -->
  <nav class="navbar navbar-light navbar-expand-lg bg-light static-top">
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


<section class="bg-light">
<div class="container">
	<div class="card">
		<div class="card-header">
			연구장비 공동 활용 신청서
		</div>
		<div class="card-body">
			<form:form id="applicationForm" class="needs-validation" commandName="applicationForm" role="form" action="${path}/equipment/insert.do">
				<form:hidden path="equipId" id="equipId" />
				<form:hidden path="signature" id="signature" />

				<h5 class="card-title">활용 신청을 위해 아래 항목을 작성해 주세요.</h5><hr/>
				<div class="form-row">
					<div class="col-md-6">
						<dl>
							<dt>장비</dt>
							<dd><c:out value="${equipmentVO.equipName}"/></dd>
						</dl>
					</div>
					<div class="col-md-6">
						<dl>
							<dt>카테고리</dt>
							<dd><c:out value="${equipmentVO.category.parentCategory.categoryName}"/> / <c:out value="${equipmentVO.category.categoryName}"/></dd>
						</dl>
					</div>
				</div>
				<hr/>
				<div class="form-row">
					<div class="form-group col-md-8">
						<label for="applicant">성명</label>
						<form:input type="text" id="applicant" path="applicant" class="form-control" required="true" />
					</div>
					<div class="form-group col-md-4">
						<label for="phone">전화번호</label>
						<form:input type="text" id="phone" path="phone" placeholder="연락가능한 전화번호를 적어주세요." class="form-control" required="true"/>
					</div>
				</div>
				<div class="form-row">
					<div class="form-group col-md-6">
						<label for="affiliation">소속</label>
						<form:input type="text" id="affiliation" path="affiliation" class="form-control" required="true"/>
					</div>
					<div class="form-group col-md-6">
						<label for="email">전자우편</label>
						<form:input type="email" id="email" path="email" class="form-control" required="true"/>
					</div>
				</div>
				<div class="form-row">
					<div class="input-group col-md-3 mb-3">
						<form:input type="text" class="form-control" id="zipcode" path="zipcode" placeholder="우편번호" required="true" />
						<div class="input-group-append">
							<button type="button" class="btn btn-secondary" id="searchPostCode">주소검색</button>
						</div>
					</div>
				</div>
				<div class="form-row">
					<div class="form-group col-md-7">
						<label for="address">도로명 주소</label>
						<form:input type="text" class="form-control" id="address" path="address"   required="true"/>
					</div>
					<div class="form-group col-md-5">
						<label for="detailAddress">상세주소</label>
						<form:input type="text" class="form-control" id="detailAddress" path="detailAddress" placeholder="상세주소를 입력해주세요." />
					</div>
				</div>
				<div class="form-row">
					<div class="form-group col-md-10">
						<label for="startDate">이용 일자
						
						<div class="tooltip">정보
						  <span class="tooltiptext">지난일 또는 예약이 다 찬 날은 비활성화 됩니다</span>
						</div>
						</label>
						<div class="input-daterange input-group" id="datepicker">
							<div class="input-group-prepend">
								<div class="input-group-text"><i class="far fa-calendar-alt"></i></div>
							</div>
							<form:input type="text" id="startDate" path="startDate" class="form-control" placeholder="시작 일자"  required="true"/>
							<span class="input-group-addon">~</span>
							<div class="input-group-prepend">
								<div class="input-group-text"><i class="far fa-calendar-alt"></i></div>
							</div>
							<form:input type="text" id="endDate" path="endDate"  class="form-control" placeholder="종료 일자" required="true"/>
						</div>
					</div>
					<div class="form-group col-md-2">
						<label for="phone">이용 시간</label>
						<form:input type="text" id="hours" path="hours" class="form-control"  required="true"/>
					</div>
				</div>
				<div class="form-row">
					<div class="form-group col-md-12">
						<label for="purpose">이용 목적</label>
						<form:input type="text" id="purpose" path="purpose" class="form-control" required="true" />
					</div>
				</div>
				<h5 class="card-title">이용자 준수의무</h5><hr/>
				<div class="form-row user_privacy">
					<div class="col-sm-6">
						<div class="card">
							<div class="card-header text-center">
								이용자 준수 의무
							</div>
							<div class="card-body">
								<p class="card-text">
									1. 이용자는 국립낙동강생물자원관이 정한 각종규칙을 성실히 준수하여야 한다.
								</p>
								<p class="card-text">
									2. 이용자는 장비의 이용을 종료한 후 장비책임자에게 장비의 이상유무를 확인받아야한다.
								</p>
								<p class="card-text">
									3. 이용자는 장비 이용 중 이상이 발견되었을 경우 그 내용을 즉시 장비담당자에게 알려야 한다.
								</p>
								<p class="card-text">
									4. 이용자는 장비 이용 중 이용자의 귀책사유로 발생한 고장, 손상 및 사고 등에 대하여 변상조치 또는 원상복구 등 모든 책임을 져야 한다.
								</p>
								<p class="card-text">
									5. 이용자는 장비의 이용으로 얻은 자료를 논문, 연구보고서, 산업재산권 등에 인용하는 경우 장비의 이용시설을 명기하여야 하며 그 결과는 국립낙동강생물자원관으로 통보하여야 한다.
								</p>
							</div>
							<div class="card-footer">
								본인은 귀 기관의 연구장비를 이용함에 있어 위의 의무사항을 준수할 것이며 이에 연구장비의 이용을 신청합니다.
							</div>
						</div>
					</div>
					<div class="col-sm-6">
						<div class="card">
							<div class="card-header text-center">
								개인정보 수집 및 이용 동의서
							</div>
							<div class="card-body">
								<table class="table table-bordered">
									<tbody>
										<tr>
											<td>수집활동</td>
											<td>
												<p class="card-text">● 필수 : 성명 소속 주소 전화 전자우편</p>
												<p class="card-text">● 선택 : 필수정보 이외 개인정보</p>
											</td>
										</tr>
										<tr>
											<td>수집 이용 목적</td>
											<td>
												<p class="card-text">● 연구장비 공동 활용 사용자 정보</p>
											</td>
										</tr>
										<tr>
											<td>개인정보의 보유 및 이용기간</td>
											<td>
												<p class="card-text">● 10년 보관 원칙</p>
												<p class="card-text">● 신청자가 삭제를 요청할 경우 해당 정보 삭제</p>
											</td>
										</tr>
										<tr>
											<td>개인정보 제공동의 거부권리 및 거부에 따른 불이익 내용 또는 제한사항</td>
											<td>
												<p class="card-text">● 지원자는 개인정보 제공에 동의하지 않을 권리가 있습니다.</p>
												<p class="card-text">● 다만 연구장비 공동 활용 신청서 등을 통해 제공받는 정보는 국립낙동강생물자원관 연구장비의 공동 활용에 필수적인 항목으로 개인정보 제공에 동의하지 않을 경우 공동 활용이 제한될 수 있습니다.</p>
											</td>
										</tr>
									</tbody>
								</table>
							</div>
							<div class="card-footer">
                                <div class="custom-control custom-checkbox">
                                    <input type="checkbox" class="custom-control-input" id="isAgreePrivacyInfo" name="isAgreePrivacyInfo" value="1" required>
                                    <label class="custom-control-label" for="isAgreePrivacyInfo">본인은 "개인정보 수집 및 이용" 에 관한 설명을 모두 이해하였으며 이에 동의합니다</label>
                                </div>
							</div>
						</div>
					</div>
				</div>
				<div class="form-row">
					<div class="col-sm-3">
						<h5 class="card-title">서명해 주세요.</h5>
						<div class="js-signature" id="signature-pad"></div>
					</div>
				</div>
				<p><button type="button" id="clear" class="btn btn-default">서명 지우기</button></p>
				<div class="g-recaptcha" data-sitekey="6LdkjbcUAAAAANQ69zQMP4sqLJqypxQoxwUYgYVw"></div>
				<button type="submit" id="" class="btn btn-primary">신청하기</button>
			</form:form>
		</div>
	</div>
</div>


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
