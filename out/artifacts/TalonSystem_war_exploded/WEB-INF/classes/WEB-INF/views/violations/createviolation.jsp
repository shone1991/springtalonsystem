<%@ page language="java" contentType="text/html; UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
			<form:form method="POST" modelAttribute="violation">
				<form:input type="hidden" path="id" id="id" />
				<div class="row mg-b-25">
					<div class="col-lg-4">
						<div class="form-group">
							<label class="form-control-label">Имя: </label>
							<form:input class="form-control" type="text" path="nameEmpl"
								name="nameEmpl" value="" placeholder="Имя" />
						</div>
					</div>
					<!-- col-4 -->
					<div class="col-lg-4">
						<div class="form-group">
							<label class="form-control-label">Фамилия: <span
								class="tx-danger">*</span></label>
							<form:input class="form-control" type="text" path="surnameEmpl"
								name="surnameEmpl" value="" placeholder="Фамилия" />
							<div class="has-error">
								<form:errors path="surnameEmpl" class="help-inline" />
							</div>
						</div>
					</div>
					<!-- col-4 -->
					<div class="col-lg-4">
						<div class="form-group">
							<label class="form-control-label">Отчество: </label>
							<form:input class="form-control" type="text" path="lastnameEmpl"
								name="lastnameEmpl" value="" placeholder="Отчество" />
						</div>
					</div>
					<!-- col-4 -->
					<div class="col-lg-8">
						<div class="form-group mg-b-10-force">
							<label class="form-control-label">Должность: </label>
							<form:input class="form-control" id="w-input-search" type="text"
								path="post.postname" name="post" value=""
								placeholder="Должность" />
							<div class="has-error">
								<form:errors path="post.postname" class="help-inline" />
							</div>
						</div>
					</div>
					<!-- col-8 -->
					<div class="col-lg-4">
						<div class="form-group mg-b-10-force">
							<c:choose>
								<c:when test="${hascompanies}">
									<label class="form-control-label">Место работы: <span
										class="tx-danger">*</span></label>

									<form:input class="form-control" type="text"
										path="company.callname" name="company" id="workat" value=""
										placeholder="Место работы" autocomplete="off" />
									<div class="has-error">
										<form:errors path="company.callname" class="help-inline" />
									</div>
								</c:when>
								<c:otherwise>
									<form:input type="hidden" path="company.callname"
										name="company" value="${currentuser.company.callname}"
										autocomplete="off" />
									<div class="has-error">
										<form:errors path="company.callname" class="help-inline" />
									</div>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
					<!-- col-4 -->
					<div class="col-lg-2">
						<div class="form-group mg-b-10-force">
							<label class="form-control-label">№ талона: <span
								class="tx-danger">*</span></label>
							<form:input class="form-control" type="text" path="stubnum"
								id="stubnum" name="stubnum" value="" placeholder="№ талона"
								maxlength="1" autocomplete="off" required="required" />
							<div class="has-error">
								<form:errors path="stubnum" class="help-inline" />
							</div>
						</div>
					</div>
					<!-- col-2 -->
					<div class="col-lg-6">
						<div class="form-group mg-b-10-force">
							<datalist id="guidelist">
								<c:forEach var="guide" items="${violguides}">
									<option value="${guide.contentViol}" />
								</c:forEach>
							</datalist>
							<label class="form-control-label">Характер нарушения: <span
								class="tx-danger">*</span></label>
							<form:input class="form-control" type="text"
								path="violguide.contentViol" name="violguide" list="guidelist"
								value="" placeholder="Характер нарушения" />
							<div class="has-error">
								<form:errors path="violguide.contentViol" class="help-inline" />
							</div>
						</div>
					</div>
					<!-- col-6 -->
					<div class="col-lg-4">
						<div class="form-group">
							<label class="form-control-label">Кем изъят: </label>
							<form:input class="form-control" type="text" path="seizedFrom"
								name="email" value="" placeholder="Кем изъят" />
						</div>
					</div>
					<!-- col-4 -->
					<div class="col-lg-4">
						<div class="form-group">
							<label class="form-control-label">Дата изъятия: <span
								class="tx-danger">*</span></label>
							<form:input class="form-control" type="date" path="dateseize"
								name="dateseize" placeholder="Дата изъятия" />
							<div class="has-error">
								<form:errors path="dateseize" class="help-inline" />
							</div>
						</div>
					</div>
					<!-- col-4 -->
					<div class="col-lg-4">
						<div class="form-group">
							<label class="form-control-label">№ приказа: </label>
							<form:input class="form-control" type="text" path="numorder"
								name="numorder" value="" placeholder="№ приказа" />
						</div>
					</div>
					<!-- col-4 -->
					<div class="col-lg-4">
						<div class="form-group">
							<label class="form-control-label">Дата приказа: </label>
							<form:input class="form-control" type="date" path="dateorder"
								name="dateorder" placeholder="Дата приказа" />
						</div>
					</div>
					<!-- col-4 -->
					<div class="col-lg-8">
						<div class="form-group mg-b-10-force">
							<label class="form-control-label">Принятые меры: </label>
							<form:input class="form-control" type="text" path="measure"
								name="measure" value="" placeholder="Принятые меры" />
						</div>
					</div>
					<!-- col-8 -->
					<div class="col-lg-4">
						<div class="form-group">
							<label class="form-control-label">Удержанные суммы:</label>
							<form:input class="form-control" type="text" path="stopmoney"
								name="stopmoney" value="" placeholder="Удержанные суммы" />
						</div>
					</div>
					<!-- col-4 -->
				</div>
				<!-- row -->

				<div class="form-layout-footer">
					<c:choose>
						<c:when test="${edit}">
							<input type="submit" value="Обновить" class="btn btn-info mg-r-5">
							<a href="<c:url value='/' />" class="btn btn-secondary">Отменить</a>
						</c:when>
						<c:otherwise>
							<input type="submit" value="Сохранить"
								class="btn btn-info mg-r-5">
							<a href="<c:url value='/' />" class="btn btn-secondary">Отменить</a>
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

<sec:authorize access="hasRole('DBA') or hasRole('ADMIN')">
	<jsp:include page="searchviolation.jsp"></jsp:include>
</sec:authorize>
<sec:authorize access="hasRole('USER')">
	<jsp:include page="usearchviolation.jsp"></jsp:include>
</sec:authorize>
