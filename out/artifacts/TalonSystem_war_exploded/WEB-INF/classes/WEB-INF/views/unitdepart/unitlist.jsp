<%@ page language="java" contentType="text/html; UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="tag" uri="/WEB-INF/tld/customTaglib.tld"%>
<!-- include header -->
<jsp:include page="../templates/header.jsp"></jsp:include>
<!-- include aside -->

<jsp:include page="../templates/aside.jsp"></jsp:include>

<div class="am-pagebody">
	<div class="card pd-20 pd-sm-40">
		<h6 class="card-body-title">Список записей таблицы:</h6>
		<a href="<c:url value='/unitdepart/createunitdepart'/>"
			class="btn btn-warning btn-block mg-b-10"><i
			class="fa fa-plus mg-r-10"></i> Довавить запись</a>
		<div class="table-responsive">
			<table id="datatable1" class="table table-hover table-bordered table-primary mg-b-0">
				<thead>
					<tr>
						<th>Названия служб и предприятий</th>
						<sec:authorize access="hasRole('DBA')">
							<th></th>
							<th></th>
						</sec:authorize>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${unitdeparts}" var="unitdepart">


						<tr>
							<td>${unitdepart.callname}</td>
							<sec:authorize access="hasRole('DBA')">
								<td><a
									href="<c:url value='/unitdepart/edit-unitdepart-${unitdepart.id}' />"
									class="btn btn-primary btn-icon"><div>
											<i class="fa fa-edit"></i>
										</div></a></td>
								<td><a
									href="<c:url value='/unitdepart/delete-unitdepart-${unitdepart.id}' />"
									class="btn btn-danger btn-icon"><div>
											<i class="fa fa-remove"></i>
										</div></a></td>
							</sec:authorize>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<c:if test="${noOfPages gt 1}">
				<nav aria-label="Page navigation ">
					<ul class="pagination pagination-basic mg-b-0">

						<%--For displaying Previous link except for the 1st page --%>

						<c:if test="${currentPage != 1}">
							<li class="page-item"><a class="page-link"
								href="<c:url value='/unitdepart/list?page=${currentPage - 1}' />"
								aria-label="Previous"><i class="fa fa-angle-left"></i></a></li>
						</c:if>

						<%--For displaying Page numbers. 
    The when condition does not display a link for the current page--%>

						<c:forEach begin="1" end="${noOfPages}" var="i">
							<c:choose>
								<c:when test="${currentPage eq i}">
									<li class="page-item active"><a class="page-link" href="#">${i}</a></li>
								</c:when>
								<c:otherwise>
									<li class="page-item"><a class="page-link"
										href="<c:url value='/unitdepart/list?page=${i}' />">${i}</a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>

						<%--For displaying Next link --%>
						<c:if test="${currentPage lt noOfPages}">
							<li class="page-item"><a class="page-link"
								href="<c:url value='/unitdepart/list?page=${currentPage + 1}' />"
								aria-label="Next"> <i class="fa fa-angle-right"></i>
							</a></li>
						</c:if>
					</ul>
				</nav>
			</c:if>
		</div>
		<!-- table-wrapper -->

	</div>
	<!-- card -->
</div>
<!-- am-pagebody -->
<!-- include footer -->
<jsp:include page="../templates/footer.jsp"></jsp:include>
<sec:authorize access="hasRole('DBA')">
<jsp:include page="searchunitdep.jsp"></jsp:include>
</sec:authorize>
