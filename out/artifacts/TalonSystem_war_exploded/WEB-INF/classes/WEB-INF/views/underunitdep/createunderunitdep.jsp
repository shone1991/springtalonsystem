<%@ page language="java" contentType="text/html; UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!-- include header -->
<jsp:include page="../templates/header.jsp"></jsp:include>
<!-- include aside -->
<jsp:include page="../templates/aside.jsp"></jsp:include>



<div class="am-pagebody">
	<div class="card pd-20 pd-sm-40">
		<h6 class="card-body-title">${bodytitle}</h6>
		<p class="mg-b-20 mg-sm-b-30">
			Поля обозначенные со знаком (<span class="tx-danger">*</span> )
			подлежат к обязательному заполнению
		</p>

		<div class="form-layout">
			<form:form method="POST" modelAttribute="underunitdep">
				<form:input type="hidden" path="id" id="id" />
				<div class="row mg-b-25">
					<div class="col-lg-12">
						<div class="form-group">
							<label class="form-control-label">Названия предприятии: <span
								class="tx-danger">*</span></label>
							<c:choose>
								<c:when test="${edit}">
									<form:input class="form-control" type="text" path="callname"
										name="callname" value="${underunitdep.callname}" placeholder="Названия предприятии" />
									<div class="has-error">
										<form:errors path="callname" class="help-inline" />
									</div>
								</c:when>
								<c:otherwise>
									<form:input class="form-control" type="text" path="callname"
										name="callname" value="${underunitdep.callname}" placeholder="Названия предприятии" />
									<div class="has-error">
										<form:errors path="callname" class="help-inline" />
									</div>
								</c:otherwise>
							</c:choose>
						</div>

					</div>
					<!-- col-12 -->
					<div class="col-lg-8">
						<div class="form-group mg-b-10-force">
							<datalist id="listunit">
								<c:forEach var="underunit" items="${underunitlist}">
									<option value="${underunit.callname}" />
								</c:forEach>
							</datalist>
							<label class="form-control-label">Служба: <span
								class="tx-danger">*</span></label>
							<form:input class="form-control" type="text" path="unitdep.callname"
								name="unitdep.callname" list="listunit" value="" placeholder="Служба" />
							<div class="has-error">
								<form:errors path="unitdep.callname" class="help-inline" />
							</div>
						</div>
					</div>
					<!-- col-6 -->
				</div>
				<!-- row -->

				<div class="form-layout-footer">
					<c:choose>
						<c:when test="${edit}">
							<input type="submit" value="Обновить" class="btn btn-info mg-r-5">
							<a href="<c:url value='/underunitdep/list' />"
								class="btn btn-secondary">Отменить</a>
						</c:when>
						<c:otherwise>
							<input type="submit" value="Сохранить"
								class="btn btn-info mg-r-5">
							<a href="<c:url value='/underunitdep/list' />"
								class="btn btn-secondary">Отменить</a>
						</c:otherwise>

					</c:choose>
				</div>
				<!-- form-layout-footer -->
			</form:form>
		</div>
		<!-- form-layout -->
	</div>
	<!-- card -->
</div>
<!-- am-pagebody -->
<!-- include footer -->
<jsp:include page="../templates/footer.jsp"></jsp:include>
<sec:authorize access="hasRole('DBA')">
<jsp:include page="searchunderunitdep.jsp"></jsp:include>
</sec:authorize>
