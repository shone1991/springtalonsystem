
<%@ page language="java" contentType="text/html; UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<div class="am-sideleft">


	<div class="tab-content">
		<div id="mainMenu" class="tab-pane active">
			<ul class="nav am-sideleft-menu">
				<li class="nav-item"><a
					href="<c:url value='/violations/violationlist'/>"
					class="nav-link active"> <i class="fa fa-id-card"></i> <span
						style="margin-left: 3px;">Талоны</span>
				</a></li>
				<!-- nav-item -->
				<sec:authorize access="hasRole('DBA')">
					<li class="nav-item"><a href="<c:url value='/users/list'/>"
						class="nav-link active"> <i class="icon ion-person-stalker"></i>
							<span>Пользователи</span>
					</a></li>
					<!-- nav-item -->
					<li class="nav-item"><a href="<c:url value='/rju/list'/>"
						class="nav-link with-sub active"> <i class="fa fa-bank"></i> <span>Службы</span>
					</a>
						<ul class="nav-sub" style="display: none;">
							<li class="nav-item"><a
								href="<c:url value='/unitdepart/list'/>" class="nav-link">Предприятии</a></li>
							<li class="nav-item"><a
								href="<c:url value='/underunitdep/list'/>" class="nav-link">Подразделении</a></li>
						</ul></li>
					<!-- nav-item -->
					<li class="nav-item"><a href=""
						class="nav-link with-sub active"> <i class="fa fa-road"></i> <span>РЖУ</span>
					</a>
						<ul class="nav-sub" style="display: none;">
							<li class="nav-item"><a href="<c:url value='/rju/list'/>"
								class="nav-link">Узлы</a></li>
							<li class="nav-item"><a
								href="<c:url value='/underrju/list'/>" class="nav-link">Подразделении</a></li>
						</ul></li>
					<!-- nav-item -->
					<li class="nav-item"><a href="<c:url value='/rju/list'/>"
						class="nav-link with-sub active"> <i
							class="icon ion-briefcase"></i> <span>Справочники</span>
					</a>
						<ul class="nav-sub" style="display: none;">
							<li class="nav-item"><a href="<c:url value='/job/list'/>"
								class="nav-link">Справочник должностей</a></li>
							<li class="nav-item"><a
								href="<c:url value='/violguide/list'/>" class="nav-link">Справочник
									нарушений</a></li>
							<li class="nav-item"><a href="<c:url value='/cviol/list'/>"
								class="nav-link">Справочник обобщенных нарушений</a></li>
						</ul></li>
					<!-- nav-item -->
					<li class="nav-item"><a href="#"
						class="nav-link with-sub active"> <i
							class="fa fa-bar-chart-o"></i> <span>Отчеты</span>
					</a>
						<ul class="nav-sub" style="display: none;">
							<li class="nav-item"><a
								href="<c:url value='/report/listunit'/>" class="nav-link">Общий по службам и предприятиям</a></li>
							<li class="nav-item"><a
								href="<c:url value='/report/listrju'/>" class="nav-link">Общий по РЖУ</a></li>
							<li class="nav-item"><a href="<c:url value='/report/listunitdetail'/>"
								class="nav-link">Нарушении по службам и предприятиям</a></li>
							<li class="nav-item"><a href="<c:url value='/report/listrjudetail'/>"
								class="nav-link">Нарушении по РЖУ</a></li>
						</ul></li>
					<!-- nav-item -->
				</sec:authorize>

			</ul>
		</div>
		<!-- #mainMenu -->
	</div>
	<!-- tab-content -->
</div>
<!-- am-sideleft -->

<div class="am-mainpanel">
	<div class="am-pagetitle">
		<h5 class="am-title">${amandatitle}</h5>
		<sec:authorize
			access="hasRole('DBA') or hasRole('ADMIN') or hasRole('USER')">
			<a href="" class="btn btn-info pd-x-20" data-toggle="modal"
				data-target="#modaldemo1"><i class="fa fa-search"></i><span
				style="margin-left: 5px;">Поиск</span></a>
		</sec:authorize>




	</div>
	<!-- am-pagetitle -->