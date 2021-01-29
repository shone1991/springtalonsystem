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
			<form:form method="POST" modelAttribute="user">
				<form:input type="hidden" path="id" id="id" />
				<div class="row mg-b-25">
					<sec:authorize access="hasRole('DBA')">
						<div class="col-lg-4">
							<div class="form-group">
								<label class="form-control-label">Имя: <span
									class="tx-danger">*</span></label>
								<form:input class="form-control" type="text" path="firstName"
									name="firstName" value="" placeholder="Имя" />
							</div>
							<div class="has-error">
								<form:errors path="firstName" class="help-inline" />
							</div>
						</div>
						<!-- col-4 -->
						<div class="col-lg-4">
							<div class="form-group">
								<label class="form-control-label">Фамилия: <span
									class="tx-danger">*</span></label>
								<form:input class="form-control" type="text" path="lastName"
									name="lastName" value="" placeholder="Фамилия" />
								<div class="has-error">
									<form:errors path="lastName" class="help-inline" />
								</div>
							</div>
						</div>
					</sec:authorize>
					<!-- col-4 -->
					<div class="col-lg-4">
						<div class="form-group">
							<label class="form-control-label">Имя пользователя: <span
								class="tx-danger">*</span></label>
							<c:choose>
								<c:when test="${edit}">
									<form:input class="form-control" type="text" path="ssoId"
										name="ssoId" value="" placeholder="Имя пользователя" />
									<div class="has-error">
										<form:errors path="ssoId" class="help-inline" />
									</div>
								</c:when>
								<c:otherwise>
									<form:input class="form-control" type="text" path="ssoId"
										name="ssoId" value="" placeholder="Имя пользователя" />
									<div class="has-error">
										<form:errors path="ssoId" class="help-inline" />
									</div>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
					<!-- col-4 -->
					<div class="col-lg-4">
						<div class="form-group">
							<label class="form-control-label">Пароль: <span
								class="tx-danger">*</span></label>
							<form:input class="form-control" type="password" path="password"
								name="password" value="" placeholder="Пароль" />
							<div class="has-error">
								<form:errors path="password" class="help-inline" />
							</div>
						</div>
					</div>
					<!-- col-4 -->
					<sec:authorize access="hasRole('DBA')">
						<div class="col-lg-4">
							<div class="form-group mg-b-10-force">
								<%-- <datalist id="companylist">
									<c:forEach var="company" items="${companies}">
										<option value="${company.callname}" />
									</c:forEach>
								</datalist> --%>
								<label class="form-control-label">Место работы: <span
									class="tx-danger">*</span></label>
								<%-- <c:choose>
									<c:when test="${edit}">
										<form:input class="form-control" type="text"
											path="company.callname" name="company.callname"
											list="companylist" value="${user.company.callname}"
											placeholder="Место работы" autocomplete="off"/>
										<div class="has-error">
											<form:errors path="company.callname" class="help-inline" />
										</div>
									</c:when>
									<c:otherwise>
										<form:input class="form-control" type="text"
											path="company.callname" name="company.callname"
											list="companylist" value="" placeholder="Место работы" autocomplete="off"/>
										<div class="has-error">
											<form:errors path="company.callname" class="help-inline" />
										</div>
									</c:otherwise>
								</c:choose> --%>
								
								<%-- <form:input class="form-control" type="text"
									path="company.callname" name="company.callname"
									list="companylist" value="" placeholder="Место работы" />
								<div class="has-error">
									<form:errors path="company.callname" class="help-inline" />
								</div> --%>
								
								<form:input class="form-control" type="text"
											path="company.callname" name="company.callname"
											id="postsearch" placeholder="Место работы" autocomplete="off"/>
										<div class="has-error">
											<form:errors path="company.callname" class="help-inline" />
										</div>
							</div>

						</div>
						<!-- col-8 -->

						<div class="col-lg-4">
							<div class="form-group">
								<label class="form-control-label">Привилегии: <span
									class="tx-danger">*</span></label>
								<form:select path="userProfiles" items="${roles}"
									multiple="true" itemValue="id" itemLabel="type"
									class="form-control" />
								<div class="has-error">
									<form:errors path="userProfiles" class="help-inline" />
								</div>
							</div>
						</div>
						<!-- col-4 -->
					</sec:authorize>
				</div>
				<!-- row -->

				<div class="form-layout-footer">
					<c:choose>
						<c:when test="${edit}">
							<input type="submit" value="Обновить" class="btn btn-info mg-r-5">
							<a href="<c:url value='/users/list' />" class="btn btn-secondary">Отменить</a>
						</c:when>
						<c:otherwise>
							<input type="submit" value="Сохранить"
								class="btn btn-info mg-r-5">
							<a href="<c:url value='/users/list' />" class="btn btn-secondary">Отменить</a>
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
<jsp:include page="searchuser.jsp"></jsp:include>
</sec:authorize>
