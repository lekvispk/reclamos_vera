<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<style>
.error {
	color: #ff0000;
}
 
.errorblock {
	color: #000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	padding: 8px;
	margin: 16px;
}
</style>

</head>
<body>


  
         <c:if test="${not empty msgError}"><div class="alert alert-danger"> <strong><c:out value="${msgError}"/></strong> </div></c:if>
		<c:if test="${not empty mensaje}"><div class="alert alert-success">  <strong><c:out value="${mensaje}"/></strong> </div></c:if>
	
 
 	<c:if test="${ empty uploadForm.idAdjunto  }">
 	<h2>Cargar archivo</h2>
	<form:form  action="adjunta.htm" method="POST" modelAttribute="uploadForm" enctype="multipart/form-data">
 
		<form:errors path="*" cssClass="errorblock" element="div" />
 
 		<form:hidden path="idTramite"/>
 		
		Seleccione un archivo : <input type="file" name="file" />
		<input type="submit" value="upload" />
		<span><form:errors path="file" cssClass="error" />
		</span>
 
	</form:form>
	</c:if>
	
	<c:if test="${not empty uploadForm.idAdjunto  }">
	<h2>Archivo cargado correctamente </h2>
		Nombre del archivo : ${uploadForm.file.originalFilename  }<br>
		Tamaño: ${uploadForm.file.size} <br>
	</c:if>
	
</body>
</html>