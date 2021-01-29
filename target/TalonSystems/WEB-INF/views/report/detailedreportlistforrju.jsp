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
		<h6 class="card-body-title">ОТЧЕТ</h6>
		<h6 class="card-body-title">о причинах изъятых
			талонов-предупреждения № 1, 2, 3 по РЖУ
			АО "УТЙ"</h6>
		<h6>${datecriteria}</h6>

		<c:forEach var="entry" items="${report}">
			<!-- entry {key:numstub, value:map<viol,map<abstrcomp,int>>} -->
			<div class="table-responsive">
				<table id="datatable1"
					class="table table-hover table-bordered table-primary mg-b-0">
					<thead>
						<tr>
							<th>№</th>
							<th>Виды нарушений, при которых изъяты талон предупреждения
								№ <c:out value="${entry.key}"></c:out>
							</th>
							<c:forEach var="company" items="${companies}">
								<th><span
									style="writing-mode: tb-rl; transform: rotate(180deg);">${company.callname}</span></th>
							</c:forEach>
							<th><span
								style="writing-mode: tb-rl; transform: rotate(180deg);">Общее</span></th>
						</tr>
					</thead>
					<c:set var="rowCount" value="0" scope="page" />

					<tbody>

						<c:forEach var="violentry" items="${entry.value}">
							<!-- violentry {key:viol, value:map<abstrcomp,int>}  -->
							<c:set var="rowCount" value="${rowCount + 1}" scope="page" />
							<tr>
								<td>${rowCount}</td>
								<td>${violentry.key.contentViol}</td>
								<c:forEach var="percomp" items="${violentry.value}">
									<!-- percomp {key:abstrcomp, value:int} -->
									<c:choose>
										<c:when test="${percomp.value!=0}">
											<td><c:out value="${percomp.value}" /></td>
										</c:when>
										<c:otherwise>
											<td></td>
										</c:otherwise>
									</c:choose>
									<c:set var="horTotal" value="${horTotal+percomp.value}"
										scope="page" />
								</c:forEach>
								<c:choose>
									<c:when test="${horTotal!=0}">
										<td
											style="background-color: gold; font-family: serif; font-weight: bold;"><c:out
												value="${horTotal}" /></td>
									</c:when>
									<c:otherwise>
										<td
											style="background-color: gold; font-family: serif; font-weight: bold;"></td>
									</c:otherwise>
								</c:choose>
								<c:set var="horTotal" value="0" scope="page" />
							</tr>

						</c:forEach>

						<tr
							style="background-color: gold; font-family: serif; font-weight: bold;">
							<td>${rowCount+1}</td>
							<td>Общее</td>

							<c:set var="overAll" value="0" scope="page" />
							<c:forEach var="vgde" items="${grandtotal[entry.key]}">
								<c:choose>
									<c:when test="${vgde.value!=0}">
										<td>${vgde.value}</td>
									</c:when>
									<c:otherwise>
										<td></td>
									</c:otherwise>
								</c:choose>
								<c:set var="overAll" value="${overAll+vgde.value}" scope="page" />
							</c:forEach>
							<td style="background-color: #fb0082; color: antiquewhite;">${overAll}</td>
						</tr>

					</tbody>
				</table>
			</div>
			<!-- table-wrapper -->
			<div style="margin-top: 50px;"></div>
		</c:forEach>

		<!-- ---------------------------------------------------------------------------- -->
	</div>
	<!-- card -->
</div>
<!-- am-pagebody -->
<!-- include footer -->
<jsp:include page="../templates/footer.jsp"></jsp:include>
<sec:authorize access="hasRole('DBA') or hasRole('ADMIN')">
	<jsp:include page="repcriteriafordetrju.jsp"></jsp:include>
</sec:authorize>
