<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/javascript/jquery-1.11.1.js"></script>
<body>

<ul>
	<c:forEach items="${devices}" var="device">
       <li><a href="${device.deviceId}/100/1"><c:out value="${device.name}"/></a></li>
	</c:forEach>
</ul>
</body>
</html>