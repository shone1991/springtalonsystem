<%@ page language="java" contentType="text/html; UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; UTF-8">
<meta http-equiv="refresh" content="<%=session.getMaxInactiveInterval()%>;url=logout"/>
<title>${title}</title>
<!-- vendor css -->
    <link href="<c:url value='/static/lib/font-awesome/css/font-awesome.css' />" rel="stylesheet">
    <link href="<c:url value='/static/lib/Ionicons/css/ionicons.css' />" rel="stylesheet">
    <link href="<c:url value='/static/lib/perfect-scrollbar/css/perfect-scrollbar.css'/>" rel="stylesheet">
    <link href="<c:url value='/static/lib/jquery-toggles/toggles-full.css'/>" rel="stylesheet">
    <link href="<c:url value='/static/lib/rickshaw/rickshaw.min.css'/>" rel="stylesheet">
    

    <!-- Amanda CSS -->
    <link rel="stylesheet" href="<c:url value='/static/css/amanda.css'/>">
    <link rel="stylesheet" href="<c:url value='/static/css/app.css'/>">
    <link href="<c:url value="/static/css/main.css" />" rel="stylesheet">
</head>
<body>
	<div class="am-header">
      <div class="am-header-left">
        <a id="naviconLeft" href="" class="am-navicon d-none d-lg-flex"><i class="icon ion-navicon-round"></i></a>
        <a id="naviconLeftMobile" href="" class="am-navicon d-lg-none"><i class="icon ion-navicon-round"></i></a>
        <a href="index.html" class="am-logo">Электронная система учета изъятых талонов по <c:out value="${currentuser.company.callname}"/></a>
      </div><!-- am-header-left -->

      <div class="am-header-right">
        
        <div class="dropdown dropdown-profile">
          <a href="" class="nav-link nav-link-profile" data-toggle="dropdown">
<%--            <img src="<c:url value='/static/img/img3.jpg'/>" class="wd-32 rounded-circle" alt="">--%>
    <i class="fa fa-user-circle fa-2x"></i>
    <span class="logged-name"><span class="hidden-xs-down">${currentuser.ssoId}</span><i class="fa fa-angle-down mg-l-3"></i></span>
          </a>
          <div class="dropdown-menu wd-200">
            <ul class="list-unstyled user-profile-nav">
              <li><a href="<c:url value='/users/edit-user-${currentuser.ssoId}' />"><i class="icon ion-ios-person-outline"></i>Изменить профиль</a></li>
              <li><a href="<c:url value="/logout" />"><i class="icon ion-power"></i> Выход</a></li>
            </ul>
          </div><!-- dropdown-menu -->
        </div><!-- dropdown -->
      </div><!-- am-header-right -->
    </div><!-- am-header -->