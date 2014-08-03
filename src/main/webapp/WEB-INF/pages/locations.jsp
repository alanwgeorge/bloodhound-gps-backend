<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<script type="text/javascript" src="resources/javascript/jquery-1.11.1.js"></script>
<body>
  <table>
  <caption>
  Device: <c:out value="${device.name}"/></br>
  Device ID: <c:out value="${device.deviceId}"/>
  </caption>
  <thead>
  <tr>
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
       <td><c:out value="${location.latitude}"/></td>
       <td><c:out value="${location.longitude}"/></td>
       <td><c:out value="${location.accuracy}"/></td>
       <td><c:out value="${location.createTime}"/></td>
       <td><a href="http://www.google.com/maps/place/@${location.latitude},${location.longitude},z15/data=!3m1!4b1!4m2!3m1!1s0x0:0x0">Map</a></td>
   </tr
	</c:forEach>
  </tbody>
  </table>
</body>
</html>