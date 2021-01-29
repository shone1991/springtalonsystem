<%@ page language="java" contentType="text/html; UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="tag" uri="/WEB-INF/tld/customTaglib.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- include header -->
<jsp:include page="../templates/header.jsp"></jsp:include>
<!-- include aside -->

<jsp:include page="../templates/aside.jsp"></jsp:include>

<div class="am-pagebody">
	<div class="card pd-20 pd-sm-40">
		<h6 class="card-body-title">Список записей таблицы:</h6>

		<div class="row" style="margin-bottom: 5px; padding-bottom: 5px;">

			<div class="col-lg-6">
				<a href="<c:url value='/violations/createviolation'/>"
					class="btn btn-warning mg-b-10"><i class="fa fa-plus mg-r-10"></i>
					Довавить запись </a>



			</div>
			<div class="col-lg-6">

				<a href="<c:url value='/violations/violationlistpdf'/>"
					class="btn btn-info mg-r-5 pull-right" target="_blank"
					data-toggle="modal" data-target="#modaldemo2"><i
					class="fa fa-file-pdf-o mg-r-10 "></i> Создать PDF отчет </a>

			</div>
		</div>
		<div class="table-responsive">
			<table id="datatable1"
				class="table table-hover table-bordered table-primary mg-b-0">
				<thead>
					<tr>
						<th>Ф.И.О.</th>
						<th>Должность</th>
						<th>Место работы</th>
						<th>№ талона</th>
						<th>Характер нарушения</th>
						<th>Кем изъят</th>
						<th>Дата изъятия</th>
						<th>№ приказа</th>
						<th>Дата приказа</th>
						<th>Принятые меры</th>
						<sec:authorize
							access="hasRole('USER') or hasRole('ADMIN') or hasRole('DBA')">
							<th></th>
						</sec:authorize>
						<sec:authorize access="hasRole('DBA')">
							<th></th>
						</sec:authorize>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${violationlist}" var="violation">


						<tr>
							<td>${violation.surnameEmpl}${violation.nameEmpl}</td>
							<c:choose>
								<c:when test="${empty violation.post}">
									<td></td>
								</c:when>
								<c:otherwise>
									<td>${violation.post.postname}</td>
								</c:otherwise>
							</c:choose>
							<c:choose>
								<c:when test="${not empty violation.company}">
									<td>${violation.company.callname}</td>
								</c:when>
								<c:otherwise>
									<td></td>
								</c:otherwise>
							</c:choose>
							<td>${violation.stubnum}</td>
							<c:choose>
								<c:when test="${not empty violation.violguide}">
									<td>${violation.violguide.contentViol}</td>
								</c:when>
								<c:otherwise>
									<td></td>
								</c:otherwise>
							</c:choose>
							<td>${violation.seizedFrom}</td>
							<c:choose>
								<c:when test="${violation.dateseize != null}">
									<td><fmt:formatDate pattern="dd.MM.yyyy"
											value="${violation.dateseize}" /></td>
								</c:when>
								<c:otherwise>
									<td></td>
								</c:otherwise>
							</c:choose>




							<td>${violation.numorder}</td>
							<c:choose>
								<c:when test="${violation.dateorder != null}">
									<td><fmt:formatDate pattern="dd.MM.yyyy"
											value="${violation.dateorder}" /></td>
								</c:when>
								<c:otherwise>
									<td></td>
								</c:otherwise>
							</c:choose>
							<td>${violation.measure}</td>

							<sec:authorize
								access="hasRole('USER') or hasRole('ADMIN') or hasRole('DBA')">
								<td><a
									href="<c:url value='/violations/edit-violation-${violation.id}' />"
									class="btn btn-primary btn-icon"><div>
											<i class="fa fa-edit"></i>
										</div></a></td>
							</sec:authorize>
							<sec:authorize access="hasRole('DBA')">
								<td><a
									href="<c:url value='/violations/delete-violation-${violation.id}' />"
									class="btn btn-danger btn-icon"><div>
											<i class="fa fa-remove"></i>
										</div></a></td>
							</sec:authorize>
						</tr>
					</c:forEach>
				</tbody>
			</table>

		</div>
		<div class="row">
			<div class="col-lg-12" style="margin-top: 20px;">
				<!-- table-wrapper -->
				<div class="dataTables_paginate paging_simple_numbers">
					<c:if test="${noOfPages gt 1}">
						<nav aria-label="Page navigation ">
							<ul class="pagination pagination-basic mg-b-0">

								<%--For displaying Previous link except for the 1st page --%>

								<c:if test="${currentPage != 1}">
									<li class="page-item"><a class="page-link"
										href="<c:url value='/violations/violationlist?page=${currentPage - 1}' />"
										aria-label="Previous"><i class="fa fa-angle-left"></i></a></li>
								</c:if>

								<%--For displaying Page numbers. 
    The when condition does not display a link for the current page--%>

								<c:forEach begin="1" end="${noOfPages}" var="i">
									<c:choose>
										<c:when test="${currentPage eq i}">
											<li class="page-item active"><a class="page-link"
												href="#">${i}</a></li>
										</c:when>
										<c:otherwise>
											<li class="page-item"><a class="page-link"
												href="<c:url value='/violations/violationlist?page=${i}' />">${i}</a></li>
										</c:otherwise>
									</c:choose>
								</c:forEach>

								<%--For displaying Next link --%>
								<c:if test="${currentPage lt noOfPages}">
									<li class="page-item"><a class="page-link"
										href="<c:url value='/violations/violationlist?page=${currentPage + 1}' />"
										aria-label="Next"> <i class="fa fa-angle-right"></i>
									</a></li>
								</c:if>
							</ul>
						</nav>
					</c:if>
				</div>
			</div>
		</div>
	</div>
	<!-- card -->
</div>
<!-- am-pagebody -->
<!-- include footer -->
<jsp:include page="../templates/footer.jsp"></jsp:include>
<sec:authorize access="hasRole('DBA') or hasRole('ADMIN')">
	<jsp:include page="searchviolation.jsp"></jsp:include>
	<jsp:include page="reportviolation.jsp"></jsp:include>
</sec:authorize>
<sec:authorize access="hasRole('USER')">
	<jsp:include page="usearchviolation.jsp"></jsp:include>
	<jsp:include page="ureportviolation.jsp"></jsp:include>
</sec:authorize>
