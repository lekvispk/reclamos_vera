<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"  %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix='security' uri='http://www.springframework.org/security/tags' %>

<jsp:include page="../include/cabecera.jsp"/>

        <!-- Page Content -->
        <div id="page-wrapper">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-12">
                                               
                       <jsp:include page="../include/error.jsp"/>
        
        				<form:form cssClass="form-horizontal" name="frmDocumentos" id="frmDocumentos" action="#" method="post" modelAttribute="reclamo">
				   		
							<fieldset>
							
							<!-- Form Name -->
							<legend>Gestionar Reclamos</legend>
							
							<!-- Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="txtCodigo">C&oacute;digo</label>  
							  <div class="col-md-5">
							  	<form:input path="idReclamo" id="idReclamo" size="10" cssClass="form-control input-md" />
							  </div>
							</div>
							
							<!-- Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="txtRazonSocial">Raz&oacute;n Social</label>  
							  <div class="col-md-5">
							  	<form:input path="factura.cliente.nomCliente" id="nomCliente" size="20" cssClass="form-control input-md" />
							  </div>
							</div>
														
							<!-- Select Basic -->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="cmbPrioridad">Prioridad</label>
							  <div class="col-md-4">
							    
							    <form:select path="prioridad" cssClass="form-control" >
							    	<form:option value="">-Todas-</form:option>
							  		<form:option value="1">Alta</form:option>
							  		<form:option value="2">Normal</form:option>
							  		<form:option value="3">Baja</form:option>
							  	</form:select>
							  	
							  </div>
							</div>
							
							<!-- Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="txtVencimiento">Fecha de vencimiento</label>  
							  <div class="col-md-4">
							  
							  	<div class='input-group date' id='datetimepicker1'>
				                    <fmt:formatDate value="${reclamo.vencimiento}" pattern="dd/MM/yyyy" var="f_vencimiento"/>
                    				<input type="text" class="form-control input-md" name="vencimiento" id="vencimiento" placeholder="dd/MM/yyyy"  size="10" value="${f_vencimiento}"/>
				                    <span class="input-group-addon">
				                        <span class="glyphicon glyphicon-calendar"></span>
				                    </span>
				                </div>
				                
							  </div>
							</div>
							
							<!-- Button -->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="btnBuscar"></label>
							  <div class="col-md-4">
							    <button id="btnBuscar" name="btnBuscar" class="btn btn-success">Buscar</button>
							    <security:authorize ifAnyGranted="ROLE_RECLAMO_R">
							    <a href="registro.htm" class="btn btn-success">Nuevo </a>
							    </security:authorize>
							  </div>
							</div>
							
							</fieldset>
				          </form:form>
				   		
                         	<div id="tablaDinamica">
						 	<div id="resultado">
					   		<div id="displayTagDiv">
						    	<display:table  name="requestScope.lReclamos" requestURI="lGestionar.htm" class="displaytag" pagesize="20"
						            defaultsort="1" defaultorder="descending" sort="list" export="true" id="row" excludedParams="ajax" >
						            <display:column title="Codigo" property="idReclamo" sortable="true" headerClass="sortable" />
						           <display:column title="Asunto" property="asunto" sortable="true" headerClass="sortable" />
						           <display:column title="Razon Social" property="factura.cliente.nomCliente" sortable="true" headerClass="sortable" />
						           <display:column title="RUC" property="factura.cliente.rucCliente" sortable="true" headerClass="sortable" />
						             <display:column title="Prioridad" sortable="true" headerClass="sortable">
						            	<c:if test="${row.prioridad == 1}">Alta</c:if>
						            	<c:if test="${row.prioridad == 2}">Normal</c:if>
						            	<c:if test="${row.prioridad == 3}">Baja</c:if>
						            </display:column>
						            <display:column title="Fec. Vencimiento" property="vencimiento" format="{0,date,dd/MM/yyyy}" sortable="true" headerClass="sortable" />						         
						             <display:column title="Estado" sortable="true" headerClass="sortable">
						            	<c:if test="${row.estado == 1}">En Proceso</c:if>
						            	<c:if test="${row.estado == 2}">Atendido</c:if>
						            </display:column>
						           <display:column title="Factura" property="factura.numero" sortable="true" headerClass="sortable" />
						         
						             <display:column title="Acciones" >
						         		<a href="modificar.htm?idReclamo=${row.idReclamo}" title="Editar Reclamo"><span class="glyphicon glyphicon-pencil"></span></a>
						         		<a href="javascript:eliminar(${row.idReclamo});" title="Eliminar Reclamo" class="trash"><span class="glyphicon glyphicon-trash"></span></a>
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
		document.forms[0].action="lGestionar.htm";
		document.forms[0].submit();
		//return false;
	});

	function nuevo(){
		document.forms[0].action='ncliente.htm';
		document.forms[0].action.submit();
	}
		
	function eliminar(id){
		if(confirm('¿Está seguro de eliminar el reclamo?')){
			$("#tablaDinamica").css('opacity', 0.4);
	   		$("#tablaDinamica").load('eliminarReclamo.htm?idReclamo='+ id +'&randval=' + Math.random() + " #resultado", 
	   				function(){ 
	   					$("#tablaDinamica").css('opacity', 1); 
	   					//$("#rolling").toggle(); 
	   				}
	   		);
		}
	}

	$( function(){
		$("#displayTagDiv").displayTagAjax();
   		$('#datetimepicker1').datetimepicker({
   		    format: 'DD/MM/YYYY'
   		});

	});
   
</script>