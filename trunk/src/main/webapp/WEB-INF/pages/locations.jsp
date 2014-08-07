<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head lang="en">
<link href="${pageContext.request.contextPath}/resources/css/default.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/javascript/jquery-1.11.1.js"></script>
</head>
<body>
  <table>
  <caption>
  Device: <c:out value="${device.name}"/></br>
  Count: <c:out value="${count}"/></br>
  <span class="pageNumbers">
    Goto Page:
    <c:forEach begin="1" end="${numberOfPages}" varStatus="page">
        <a href="${pageContext.request.contextPath}/device/${device.deviceId}/${pageSize}/${page.index}"><c:out value="${page.index}"/></a>
        <c:if test="${page.index != numberOfPages}">,</c:if>
    </c:forEach>
  </span>
  </caption>
  <thead>
  <tr>
  <th>ID</th>
  <th>Latitude</th>
  <th>Longitude</th>
  <th>Accuracy</th>
  <th>Date and Time</th>
  <th>Map Link</th>
  </tr>
  </thead>
  <tbody>
	<c:forEach items="${locations}" var="location">
	<tr>
	   <td class="id"><c:out value="${location.id}"/></td>
       <td class="latitude"><c:out value="${location.latitude}"/></td>
       <td class="longitude"><c:out value="${location.longitude}"/></td>
       <td class="accuracy"><c:out value="${location.accuracy}"/></td>
       <td class="datetime"><c:out value="${location.createTime}"/></td>
       <td class="link"><a href="http://maps.google.com/?q=${location.latitude},${location.longitude}">Map</a></td>
   </tr
	</c:forEach>
  </tbody>
  </table>
    <span class="pageNumbers">
  Goto Page:
  <c:forEach begin="1" end="${numberOfPages}" varStatus="page">
      <a href="${pageContext.request.contextPath}/device/${device.deviceId}/${pageSize}/${page.index}"><c:out value="${page.index}"/></a>
      <c:if test="${page.index != numberOfPages}">,</c:if>
  </c:forEach>
  </span>
  </br>
  Current Page: <c:out value="${page}"/></br>
  Page Size: <c:out value="${pageSize}"/></p>
</body>
</html>