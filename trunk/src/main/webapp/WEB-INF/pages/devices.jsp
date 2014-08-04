<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<script type="text/javascript" src="resources/javascript/jquery-1.11.1.js"></script>
<body>

<ul>
	<c:forEach items="${devices}" var="device">
       <li><a href="${device.deviceId}"><c:out value="${device.name}"/></a></li>
	</c:forEach>
</ul>
</body>
</html>