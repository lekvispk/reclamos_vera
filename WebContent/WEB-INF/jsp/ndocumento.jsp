<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<jsp:include page="include/cabecera.jsp" />

<!-- Main hero unit for a primary marketing message or call to action -->

<script>
	
		function grabar(){
		    document.forms[0].action = "ndocumento.htm";
		    document.forms[0].submit();
		}
		
		function cancel(){ 
		   window.location='pendientes.htm';
		}
		
		function adjuntar( tram ){
			window.open('adjunta.htm?idTramite='+tram, this.target, 'width=500,height=400'); 
		}

    	
    	$(function() {
    		$( "#tagsEntidad" ).autocomplete({
    			width: 300,
    	        max: 10,
    	        delay: 100,
    	        autoFocus: true,
    	        cacheLength: 1,
    	        scroll: true,
    	        highlight: false,
    			source: function(request, response) {
    	            $.ajax({
    	                url: "${pageContext.request.contextPath}/lEntidadAuto.htm",
    	                dataType: "json",
    	                data: request,
    	                success: function( data, textStatus, jqXHR) {
    	                    /*console.log( data);*/
    	                    var items = data;
    	                    response(items);
    	                },
    	                error: function(jqXHR, textStatus, errorThrown){
    	                    /* console.log(textStatus);*/
    	                }
    	            });
    	        },
    			minLength: 2,
    			select: function( event, ui ) {
    				  $( "#idEntidad" ).val( ui.item.id );
    			}
    		});
    		
    	 	$( "#fechaDocumento" ).datepicker({
     	        showOn: "button",
     	        buttonImage: "${pageContext.request.contextPath}/img/cal.gif",
     	        buttonImageOnly: false,
     	        dateFormat: 'dd/mm/yy'
     	      });
    	 	
    	 	$( "#fechaIngreso" ).datepicker({
     	        showOn: "button",
     	        buttonImage: "${pageContext.request.contextPath}/img/cal.gif",
     	        buttonImageOnly: false,
     	        dateFormat: 'dd/mm/yy'
     	      });
    	 	
    	 	
    	});
    		
</script>
<div class="hero-unit">

	<form:form cssClass="form-horizontal" name="frm2" action="ndocumento.htm" method="post" modelAttribute="tramite">
                                    
		<form:hidden path="idTramite"/>
        <input type="hidden"  value="1" name="estado"/>  
         <c:if test="${not empty msgError}"><div class="alert alert-danger"> <strong><c:out value="${msgError}"/></strong> </div></c:if>
		<c:if test="${not empty mensaje}"><div class="alert alert-success">  <strong><c:out value="${mensaje}"/></strong> </div></c:if>
		<fieldset>

			<!-- Form Name -->
			<legend>Nuevo Documento</legend>

			<!-- Search input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="tagsEntidad">Entidad Remitente</label>
				<div class="col-md-6">
                    <c:if test="${tramite.entidad.idEntidad > 0}">
                   		<form:hidden path="entidad.idEntidad" id="idEntidad"/>
                   	</c:if>
                   	<c:if test="${empty tramite.entidad.idEntidad || tramite.entidad.idEntidad <= 0}">
                   		<input type="hidden" name="entidad.idEntidad" id="idEntidad" value="-1"/>
                   	</c:if>
                   	
                   	<div class="demo" style="float: left;">
					<div class="ui-widget">
						<form:input path="entidad.nombreEntidad" id="tagsEntidad" size="40" cssClass="form-control input-md" />
					</div>
					</div>
					<div class="col-md-2">
						<a href="javascript:nuevaEntidad();"><span class="glyphicon glyphicon-user"></span></a>
					</div>
 				</div>
			</div>

			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="fechaDocumento">Fecha Documento</label>
				<div class="col-md-2">
					<fmt:formatDate value="${tramite.fechaDocumento}" pattern="dd/MM/yyyy" var="f_fechaDocumento"/>
                    <input type="text" class="form-control input-md" name="fechaDocumento" id="fechaDocumento" placeholder="dd/mm/yyyy" size="20" value="${f_fechaDocumento}"/>
										
				</div>
			</div>

			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="numeroDocumento">Número de Documento</label>
				<div class="col-md-4">
					<form:input path="numeroDocumento" id="numeroDocumento" cssClass="form-control input-md"/>
				</div>
			</div>

			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="fechaIngreso">Fecha de Recepción</label>
				<div class="col-md-2">
					<fmt:formatDate value="${tramite.fechaIngreso}" pattern="dd/MM/yyyy" var="f_fechaIngreso"/>
                    <input type="text" class="form-control input-md" name="fechaIngreso" id="fechaIngreso" placeholder="dd/mm/yyyy" size="20" value="${f_fechaIngreso}"/>
				</div>
			</div>

			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="numeroIngreso">Número de Recepción</label>
				<div class="col-md-4">
					<form:input path="numeroIngreso" id="numeroIngreso" cssClass="form-control input-md" />
				</div>
			</div>

			<!-- Select Basic -->
			<div class="form-group">
				<label class="col-md-4 control-label" for="tipoTramite">Tipo de Trámite</label>
				<div class="col-md-5">
					<form:select path="tipoTramite" id="tipoTramite" cssClass="form-control">
						<form:options items="${lTipoTram}" itemLabel="valorTexto" itemValue="valorNumerico"/>
					</form:select>
				</div>
			</div>

	<!-- Select Basic -->
			<div class="form-group">
				<label class="col-md-4 control-label" for="idOficina">Oficina de destino</label>
				<div class="col-md-5">
					<form:select path="movimientoTramite.oficina1.idOficina" id="idOficina" cssClass="form-control">
						<form:option value="-1" label="--Seleccionar--"/>
						<form:option value="1" label="Tecnologia"/>
						<form:option value="2" label="Secreatria General"/>
						<form:option value="3" label="Mesa de Partes"/>
						<form:option value="4" label="Tramite Documentario"/>
						<form:option value="5" label="Tribunal de Honor"/>
						<form:option value="6" label="Fiscalia"/>
						<form:option value="7" label="Archivo"/>
						<form:option value="8" label="Recepcion"/>
						<form:option value="9" label="Comunicaciones"/>
						<form:option value="10" label="Logistica"/>
						<form:option value="11" label="Contabilidad"/>
						<form:option value="12" label="Fonsol"/>
					</form:select>
				</div>
			</div>
			
			<!-- Textarea -->
			<div class="form-group">
				<label class="col-md-4 control-label" for="resumen">Resumen</label>
				<div class="col-md-6">
					<form:textarea path="resumen" id="resumen" cssClass="form-control" rows="5"/>
				</div>
			</div>

			<c:if test="${tramite.idTramite != 0 }">
			
					<!-- aun solo es de prueba esta seccion-->
					<c:if test="${ empty tramite.adjuntos }">
						<div class="form-group">
							<label class="col-md-4 control-label" for="btnArchivo">Archivo</label>
							<div class="col-md-4">
								<a href="javascript:adjuntar(${tramite.idTramite});">Adjuntar Archivo</a>
							</div>
						</div>
					</c:if>
					<c:if test="${ not empty tramite.adjuntos }">
						<div class="form-group">
							<label class="col-md-4 control-label" for="numeroIngreso">Archivo</label>
							<div class="col-md-4">
							<c:forEach items="${tramite.adjuntos}" var="doc">
							 	<a href="descargar.htm?id=${doc.idAdjunto}">${doc.nombre}</a>&nbsp;<br>	
			                 </c:forEach>
							</div>
						</div>
					</c:if>
			
			<!-- Button (Double) -->
				<div class="form-group">
					<label class="col-md-4 control-label" for="btnGrabar"></label>
					<div class="col-md-4">
						<button id="btnGrabar" name="btnGrabar" class="btn btn-success" onclick="javascript:grabar();">Grabar</button>
						<a id="btnCancel" class="btn btn-success" onclick="javascript:cancel();">Finalizar</a>
					</div>
				</div>
			</c:if>
			
			<c:if test="${tramite.idTramite == 0 }">
				<!-- Button (Double) -->
				<div class="form-group">
					<label class="col-md-4 control-label" for="btnGrabar"></label>
					<div class="col-md-4">
						<button id="btnGrabar" class="btn btn-success" name="btnGrabar" onclick="javascript:grabar();">Grabar</button>
						<a id="btnCancel" class="btn btn-success" onclick="javascript:cancel();">Cancelar</a>
					</div>
				</div>
			</c:if>
		</fieldset>
		</form:form>
</div>



            <div id='divPJ'  style="display: none">
				<div id="contenedorSmall">            
			         <div id="cuerpoSmall" align="center">
			              <div id="contenidoSmall">  
			                 <table class="bloqueTablaLineal">                                       
		                            <tr>
		                                <th>Nombre/Raz&oacute;n Social:</th>
		                                <td><input type="text" name="nombreEnt" id="nombreNEnt" class="nombre" size="50"/></td>
		                            </tr>
		                        </table>
			                        
			               </div>
			           </div>
			       </div>
		     </div>
		     
<hr>

<jsp:include page="include/pie.jsp" />