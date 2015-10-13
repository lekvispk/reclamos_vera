<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<jsp:include page="include/cabecera.jsp" />

<!-- Main hero unit for a primary marketing message or call to action -->

<script>
	
	function cancel(){ 
		//devolver a la pagina que lo invoco.... pendientes recibidos o enviados
	   window.location='<%=request.getParameter("a")%>.htm';
	}
    	
   	$(function() {

   	 	$( "#fechaEnvio" ).datepicker({
   	        showOn: "button",
   	        buttonImage: "${pageContext.request.contextPath}/img/cal.gif",
   	        buttonImageOnly: false,
   	        dateFormat: 'dd/mm/yy'
   	      });
   	 	
   	 	
   	});
    		
</script>
<div class="hero-unit">

	<form:form cssClass="form-horizontal" name="frm2" action="derivar.htm" method="post" modelAttribute="movimiento">
                                    
		<form:hidden path="idMovimiento"/>
		<form:hidden path="tramite.idTramite"/>
        <input type="hidden"  value="1" name="estado"/>  
        
         <c:if test="${not empty msgError}"><div class="alert alert-danger"> <strong><c:out value="${msgError}"/></strong> </div></c:if>
		<c:if test="${not empty mensaje}"><div class="alert alert-success">  <strong><c:out value="${mensaje}"/></strong> </div></c:if>
				
		<fieldset>

			<!-- Form Name -->
			<legend>Atender Documento</legend>

			<!-- Search input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="tipoMovimiento">Tipo de Accion</label>
				<div class="col-md-2">
					<form:select path="tipoMovimiento" id="tipoMovimiento" cssClass="form-control">
						<form:options items="${lTipoMov}" itemLabel="valorTexto" itemValue="valorNumerico"/>
					</form:select>
 				</div>
			</div>

			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="fechaEnvio">Fecha de Derivo</label>
				<div class="col-md-2">
					<fmt:formatDate value="${tramite.fechaEnvio}" pattern="dd/MM/yyyy" var="f_fechaEnvio"/>
                    <input type="text" class="form-control input-md" name="fechaEnvio" id="fechaEnvio" placeholder="dd/mm/yyyy" size="20" value="${f_fechaEnvio}"/>
				</div>
			</div>

			<!-- Select Basic -->
			<div class="form-group">
				<label class="col-md-4 control-label" for="idOficina">Oficina de destino</label>
				<div class="col-md-5">
				
					<input type="hidden" name="oficina2.idOficina" value="${usrLogin.oficina.idOficina}"/>
					<select id="idOficina" name="oficina1.idOficina" class="form-control">
						<option value="0">--Seleccionar--</option>
						<option value="1">Tecnologia</option>
						<option value="2">Secreatria General</option>
						<option value="3">Mesa de Partes</option>
						<option value="4">Tramite Documentario</option>
						<option value="5">Tribunal de Honor</option>
						<option value="6">Fiscalia</option>
						<option value="7">Archivo</option>
						<option value="8">Recepcion</option>
						<option value="9">Comunicaciones</option>
						<option value="10">Logistica</option>
						<option value="11">Contabilidad</option>
						<option value="12">Fonsol</option>
					</select>
				</div>
			</div>

			<!-- Textarea -->
			<div class="form-group">
				<label class="col-md-4 control-label" for="observacion">Observaciones</label>
				<div class="col-md-4">
					<textarea class="form-control" id="observacion" name="observacion"></textarea>
				</div>
			</div>
 
			<!-- Button (Double) -->
			<div class="form-group">
				<label class="col-md-4 control-label" for="btnGrabar"></label>
				<div class="col-md-4">
					<button id="btnGrabar" name="btnGrabar" class="btn btn-success" onclick="javascript:grabar();">Grabar</button>
					<a id="btnCancel" class="btn btn-success" onclick="javascript:cancel();">Cancelar</a>
				</div>
			</div>

		</fieldset>
		</form:form>
</div>

<hr>

<jsp:include page="include/pie.jsp" />