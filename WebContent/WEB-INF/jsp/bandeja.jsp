<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"  %>

<jsp:include page="include/cabecera.jsp"/>

<script>
	function nuevo(){
		document.forms[0].action='ndocumento.htm';
		document.forms[0].action.submit();
	}
	

	function eliminar(id){
		if(confirm('¿Está seguro de eliminar el documento?')){
			$("#tablaDinamica").css('opacity', 0.4);
	   		$("#tablaDinamica").load('eliminarDocumento.htm?idTramite='+ id +'&randval=' + Math.random() + " #resultado", 
	   				function(){ 
	   					$("#tablaDinamica").css('opacity', 1); 
	   					//$("#rolling").toggle(); 
	   				}
	   		);
		}
	}
	
	 $( function(){
   	   $("#displayTagDiv").displayTagAjax();
   });
   
</script>
      <!-- Main hero unit for a primary marketing message or call to action -->
      <div class="hero-unit">
        
          
         <c:if test="${not empty msgError}"><div class="alert alert-danger"> <strong><c:out value="${msgError}"/></strong> </div></c:if>
		<c:if test="${not empty mensaje}"><div class="alert alert-success">  <strong><c:out value="${mensaje}"/></strong> </div></c:if>

	
	
   		<form class="form-horizontal" action="#" name="frmDocumentos" id="frmDocumentos" method="post">
			<fieldset>
			
			<!-- Form Name -->
			<legend>Bandeja de documentos ${bandeja}</legend>
			
			<!-- Text input-->
			<div class="form-group">
			  <label class="col-md-4 control-label" for="txtRemitente">Remitente</label>  
			  <div class="col-md-5">
			  <input id="txtRemitente" name="txtRemitente" type="text" placeholder="Remitente" class="form-control input-md">
			    
			  </div>
			</div>
			
			<!-- Select Basic -->
			<div class="form-group">
			  <label class="col-md-4 control-label" for="cmbTipo">Tipo de Trámite</label>
			  <div class="col-md-4">
			    <select id="cmbTipo" name="cmbTipo" class="form-control">
			      <option value="1">Alerta</option>
			      <option value="2">Oposición</option>
			      <option value="3">Usuarios</option>
			    </select>
			  </div>
			</div>
			
			<!-- Button -->
			<div class="form-group">
			  <label class="col-md-4 control-label" for="btnBuscar"></label>
			  <div class="col-md-4">
			    <button id="btnBuscar" name="btnBuscar" class="btn btn-success">Buscar</button>
			    <a href="ndocumento.htm" class="btn btn-success">Nuevo </a>
			  </div>
			</div>
			
			</fieldset>
          </form>
   		
   	<div id="tablaDinamica">
	   <div id="resultado">
   		<div id="displayTagDiv">
	    	<display:table  name="requestScope.ldocumentos" requestURI="${listado}.htm" class="displaytag" pagesize="10"
	            defaultsort="1" defaultorder="descending" sort="list" export="false" id="row" excludedParams="ajax" >
	            <display:column title="Remitente" property="entidad.nombreEntidad" sortable="true" headerClass="sortable" />
	            <display:column title="Fec. Recepcion" property="fechaIngreso" format="{0,date,dd/MM/yyyy}" sortable="true" headerClass="sortable" />
	            <display:column title="Nro. Documento" property="numeroIngreso" sortable="true" headerClass="sortable" />
	            <display:column title="Clasificación" sortable="true" headerClass="sortable">
	            	<c:if test="${row.tipoTramite == 1}">Información Sensible</c:if>
	            	<c:if test="${row.tipoTramite == 2}">Oposición</c:if>
	            	<c:if test="${row.tipoTramite == 3}">Creación de Usuario</c:if>
	            </display:column>
	            <display:column title="Estado" sortable="true" headerClass="sortable">
	            	<c:if test="${row.estado == 1}">Registrado</c:if>
	            	<c:if test="${row.estado == 2}">Recibido</c:if>
	            	<c:if test="${row.estado == 3}">Derivado</c:if>
	            	<c:if test="${row.estado == 4}">Atendido</c:if>
	            	<c:if test="${row.estado == 5}">Archivado</c:if>
	            </display:column>
	            <display:column title="" style="width:120px;">
	            	<div class="pull-right action-buttons">
	            	<a href="javascript:ver('${row.idTramite}');" title="Detalle Documento"><span class="glyphicon glyphicon-search"></span></a>
	            	<c:if test="${row.estado == 1}">
	            		<a href="editDocumento.htm?idTramite=${row.idTramite}" title="Editar Documento"><span class="glyphicon glyphicon-pencil"></span></a>
	            		<a href="javascript:eliminar(${row.idTramite});" title="Eliminar Documento" class="trash"><span class="glyphicon glyphicon-trash"></span></a>
	            	</c:if>
	            	<!-- si esta pendiente o si ME LO HAN derivado -->
	            	<c:if test="${ (row.estado != 3 && listado ne 'enviados' ) || listado eq 'pendientes'}">
                          <a href="derivar.htm?idTramite=${row.idTramite}&a=${listado}" title="Derivar/Atender Documento" class="flag"><span class="glyphicon glyphicon-share-alt"></span></a>
                    </c:if>
                    </div>
				</display:column>
	   		</display:table>
		</div>
	</div>
	</div>			
	
        
      </div>

<div id='somediv' style="display: none">
	<div id="cuerpoDiv"></div>
</div>

    <jsp:include page="include/pie.jsp"/>