<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="mensajesAjax">

</div>

 	<c:if test="${not empty msgError}"><div class="alert alert-danger"> <strong><c:out value="${msgError}"/></strong> </div></c:if>
	<c:if test="${not empty mensaje}"><div class="alert alert-success">  <strong><c:out value="${mensaje}"/></strong> </div></c:if>
		