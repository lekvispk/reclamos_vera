<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<div class="hero-unit">
	
        <c:if test="${not empty msgError}"><div class="alert alert-danger"> <strong><c:out value="${msgError}"/></strong> </div></c:if>
		<c:if test="${not empty mensaje}"><div class="alert alert-success">  <strong><c:out value="${mensaje}"/></strong> </div></c:if>
		<fieldset>

			<!-- Form Name -->
			<legend>Detalle de  Documento</legend>
			
			<table>
			<tr>
				<th>Entidad Remitente</th>
				<td>${tramite.entidad.nombreEntidad}</td>
			</tr>
			<tr>
				<th>Fecha Documento</th>
				<td><fmt:formatDate value="${tramite.fechaDocumento}" pattern="dd/MM/yyyy"/></td>
			</tr>
			<tr>
				<th>Número de Documento</th>
				<td>${tramite.numeroDocumento}</td>
			</tr>
			<tr>
				<th>Fecha de Recepción</th>
				<td><fmt:formatDate value="${tramite.fechaIngreso}" pattern="dd/MM/yyyy"/></td>
			</tr>
			<tr>
				<th>Número de Recepción</th>
				<td>${tramite.numeroIngreso}</td>
			</tr>
			<tr>
				<th>Tipo de Trámite</th>
				<td>
					<c:if test="${tramite.tipoTramite == 1}">Alerta</c:if>
	            	<c:if test="${tramite.tipoTramite == 2}">Oposicion</c:if>
	            	<c:if test="${tramite.tipoTramite == 3}">Creacion de Usuarios</c:if>
	            </td>
			</tr>
			<tr>
				<th>Resumen</th>
				<td>${tramite.resumen}</td>
			</tr>
			<c:if test="${ not empty tramite.adjuntos }">
			<tr>
				<th>Archivo</th>
				<td>
					<c:forEach items="${tramite.adjuntos}" var="doc">
					<a href="descargar.htm?id=${doc.idAdjunto}">${doc.nombre}</a><br>
					</c:forEach>	
				</td>
			</tr>
			</c:if>
		</table>
		</fieldset>
	
</div>
