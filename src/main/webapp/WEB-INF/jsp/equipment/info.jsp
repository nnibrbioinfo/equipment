<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="path" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>연구장비 공동활용 신청서</title>
</head>
<body>
 입력이 완료 되었습니다.
신청장비 이름: <c:out value='${applicationFormVO.equipmentVO.equipName}'/> <br/>

신청자 이름: <c:out value='${applicationFormVO.applicant}'/> <br/>
신청자 전화번호: <c:out value='${applicationFormVO.phone}'/> <br/>
신청자 이메일: <c:out value='${applicationFormVO.email}'/> <br/>
신청자 서명: <img src="<c:out value='${applicationFormVO.signature}'/>" alt="신청자 서명" />  <br/>
</body>
</html>
