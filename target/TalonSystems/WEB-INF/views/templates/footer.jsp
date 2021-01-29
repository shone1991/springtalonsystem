<%@ page language="java" contentType="text/html; UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="now" class="java.util.Date" />
<fmt:formatDate var="year" value="${now}" pattern="yyyy" />
<div class="am-footer">
	<span>Copyright &copy; ${year}. Все права защищены. Шаблон: Amanda Responsive
		Bootstrap 4 Admin Dashboard. </span> <span> Разработан: ThemePixels,
		Inc., ИВЦ АО "Узбекистон темир йуллари"</span>
</div>
<!-- am-footer -->
</div>
<!-- am-mainpanel -->

<script src="<c:url value="/static/js/jquery.1.10.2.min.js" />"></script>
<script src="<c:url value="/static/js/jquery.autocomplete.min.js" />"></script>

<%-- <script src="<c:url value='/static/lib/jquery/jquery.js'/>"></script> --%>
<script src="<c:url value="/static/js/customautocomplete.js" />"></script>


<script src="<c:url value='/static/lib/popper.js/popper.js'/>"></script>
<script src="<c:url value='/static/lib/bootstrap/bootstrap.js'/>"></script>
<script
	src="<c:url value='/static/lib/perfect-scrollbar/js/perfect-scrollbar.jquery.js'/>"></script>
<script src="<c:url value='/static/js/amanda.js'/>"></script>
<script src="<c:url value='/static/js/ResizeSensor.js'/>"></script>



</body>
</html>