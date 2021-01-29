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
			<form:form method="POST" modelAttribute="violguide">
				<form:input type="hidden" path="id" id="id" />
				<div class="row mg-b-25">
					<div class="col-lg-12">
						<div class="form-group">
							<label class="form-control-label">Названия нарушении: <span
								class="tx-danger">*</span></label>
							<form:input class="form-control" type="text" path="contentViol"
								name="contentViol" value="" placeholder="Названия нарушении" />
							<div class="has-error">
								<form:errors path="contentViol" class="help-inline" />
							</div>
						</div>

					</div>
					<!-- col-12 -->
					<div class="col-lg-4">
						<div class="form-group">
							<label class="form-control-label">Элементы: <span
								class="tx-danger">*</span></label>
							<form:input class="form-control" type="text" path="elements"
								name="elements" value="" placeholder="Элементы" />
							<div class="has-error">
								<form:errors path="elements" class="help-inline" />
							</div>
						</div>
					</div>
					<!-- col-4 -->
					<div class="col-lg-8">
						<div class="form-group mg-b-10-force">
							<datalist id="guidelist">
								<c:forEach var="conclguide" items="${conclist}">
									<option value="${conclguide.content}" />
								</c:forEach>
							</datalist>
							<label class="form-control-label">Обобщения: <span
								class="tx-danger">*</span></label>
							<form:input class="form-control" type="text"
								path="conclviolguide.content" name="violguide" list="guidelist"
								value="" placeholder="Обобщения" />
							<div class="has-error">
								<form:errors path="conclviolguide.content" class="help-inline" />
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
							<a href="<c:url value='/violguide/list' />"
								class="btn btn-secondary">Отменить</a>
						</c:when>
						<c:otherwise>
							<input type="submit" value="Сохранить"
								class="btn btn-info mg-r-5">
							<a href="<c:url value='/violguide/list' />"
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
