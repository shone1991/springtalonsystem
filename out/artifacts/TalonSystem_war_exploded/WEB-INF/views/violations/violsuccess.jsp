<%@ page language="java" contentType="text/html; UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!-- include header -->
<jsp:include page="../templates/header.jsp"></jsp:include>
<!-- include aside -->

<jsp:include page="../templates/aside.jsp"></jsp:include>

<div class="am-pagebody">
	<div class="card pd-20 pd-sm-40">
	<div class="alert alert-success lead">
	    	Запись успешно добавлен
	    	<a href="<c:url value='/violations/createviolation'/>"
			class="btn btn-warning mg-r-5"><i
			class="fa fa-plus mg-r-10"></i> Довавить запись</a>
			<a href="<c:url value='/violations/violationlist'/>"
			class="btn btn-info mg-r-5"><i
			class="fa fa-list mg-r-10"></i> Список изъятых талонов</a>
		</div>
		
		
		

	</div>
	<!-- card -->
</div>
<!-- am-pagebody -->
<!-- include footer -->
<jsp:include page="../templates/footer.jsp"></jsp:include>
<sec:authorize access="hasRole('DBA') or hasRole('ADMIN')">
	<jsp:include page="searchviolation.jsp"></jsp:include>
</sec:authorize>
<sec:authorize access="hasRole('USER')">
	<jsp:include page="usearchviolation.jsp"></jsp:include>
</sec:authorize>
