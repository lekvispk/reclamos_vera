<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"  %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<jsp:include page="../include/cabecera.jsp"/>

<script>
	
	function aceptar(){
		
		var fields = $("input[name='rbBeneficio']").serializeArray(); 
	    if (fields.length == 1) { 
	    	window.location='compensar.htm?idFactura='+ $("input[name='rbBeneficio']").val();
	    }else {
	    	alert('Seleccione un elemento');
	    }
	
	}
	
	function buscar(){
		
		document.forms[0].action='lCompensar.htm';
		document.forms[0].submit();
	
	}
	
</script>
        <!-- Page Content -->
        <div id="page-wrapper">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-12">
                                               
                       <jsp:include page="../include/error.jsp"/>
        
				   		<form:form cssClass="form-horizontal" name="frmDocumentos" id="frmDocumentos" action="#" method="post" modelAttribute="reclamo">
							<fieldset>
							
							<!-- Form Name -->
							<legend>Solicitudes de Reclamos</legend>
							
							<!-- Factura Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="numero">Factura</label>  
							  <div class="col-md-5">
							  	<form:input path="factura.numero" id="factura.numero" size="10" cssClass="form-control input-md" />
							  </div>
							</div>
							
							<!-- Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="fecFacturaFin">Id Ticket</label>  
							  <div class="col-md-5">
							  	<form:input path="idReclamo" id="idReclamo" size="10" cssClass="form-control input-md" />
							  </div>
							</div>
							
							<!-- Select Basic -->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="cmbPrioridad">Estado</label>
							  <div class="col-md-4">
							  	<form:select path="estado" id="estado" cssClass="form-control input-md">
							  		<form:option value="1">En Proceso</form:option>
							  		<form:option value="2">Atendido</form:option>
							  	</form:select>
							  </div>
							</div>
							
							<!-- Button -->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="btnBuscar"></label>
							  <div class="col-md-4">
							    <input type="button" id="btnBuscar" name="btnBuscar" class="btn btn-success" value="Buscar"/>
							  </div>
							</div>
							
							</fieldset>
				         </form:form>
				   		
                         	<div id="tablaDinamica">
						 	<div id="resultado">
					   		<div id="displayTagDiv">
					   		  
						    	<display:table  name="requestScope.lReclamos" requestURI="lista.htm" class="displaytag" pagesize="10"
						            defaultsort="1" defaultorder="descending" sort="list" export="true" id="row" excludedParams="ajax _chk">
						            <display:column title="ID Ticket" property="idReclamo" sortable="true" headerClass="sortable" />
						            <display:column title="Factura" property="factura.numero" sortable="true" headerClass="sortable" />
						            <display:column title="RUC" property="factura.cliente.rucCliente" sortable="true" headerClass="sortable" />
						            <display:column title="Prioridad" sortable="true" headerClass="sortable">
						            	<c:if test="${row.prioridad == 1}">Alta</c:if>
						            	<c:if test="${row.prioridad == 2}">Normal</c:if>
						            	<c:if test="${row.prioridad == 3}">Baja</c:if>
						            </display:column>
						            <display:column title="Fec. Respuesta" property="fecRespuesta" format="{0,date,dd/MM/yyyy}" sortable="true" headerClass="sortable" />
						            <display:column title="Estado" sortable="true" headerClass="sortable">
						            	<c:if test="${row.estado == 1}">En Proceso</c:if>
						            	<c:if test="${row.estado == 2}">Atendido</c:if>
						            </display:column>
						            
						            <display:column title="Ver" sortable="true" headerClass="sortable" media="html">
						            	<a id="btnVerDetalle" data-codigo="${row.idReclamo}" href="#">Ver Detalle</a>
						            </display:column>
						    	
						    	</display:table>
							
							</div>
						  	</div>
							</div>			
                        
                        
                        
                    </div>
                    <!-- /.col-lg-12 -->
                </div>
                <!-- /.row -->
            </div>
            <!-- /.container-fluid -->
        </div>
        <!-- /#page-wrapper -->

		<div id='somediv' style="display: none">
			<div id="cuerpoDiv"></div>
		</div>
		
		
	 <jsp:include page="../include/pie.jsp"/>
	 
	 
<script>

	$(document).undelegate('#btnBuscar', 'click').delegate('#btnBuscar', 'click', function(){
		document.forms[0].action="lista.htm";
		document.forms[0].submit();
	});
	
	$(document).undelegate('#btnVerDetalle', 'click').delegate('#btnVerDetalle', 'click', function(){
		
		var idReclamo = $(this).data('codigo');
   		console.info('ver detalle de reclamo ' + idReclamo );
   		window.location.assign("${pageContext.request.contextPath}/seguimiento/ver.htm?idReclamo="+idReclamo);
		
	});
	
	 $( function(){
   	   $("#displayTagDiv").displayTagAjax();
   });
   
</script>