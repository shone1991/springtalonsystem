<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
User: ${user.ssoId}
<br>
Company: ${user.company.callname}
<br>
Class: ${user.company.getClass().name}
<br>
<c:choose>
	<c:when test="${listcompanies}">
		<c:forEach items="${listcompanies}" var="company">
			${company.callname}<br>
		</c:forEach>
	</c:when>
	<c:otherwise>
		without references: ${user.company.callname}
	</c:otherwise>
</c:choose>


