<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"  %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

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
							<legend>Indemnizar Reclamos</legend>
							
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
							
							<!-- Button -->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="btnBuscar"></label>
							  <div class="col-md-4">
							    <button id="btnBuscar" name="btnBuscar" class="btn btn-success">Buscar</button>
							    <button id="btnProcesar" name="btnProcesar" class="btn btn-success">Proccesar</button>
							  </div>
							</div>
							
							</fieldset>
				          </form:form>
				   		
                         	<div id="tablaDinamica">
						 	<div id="resultado">
					   		<div id="displayTagDiv">
						    	<jsp:scriptlet>
							    <![CDATA[
							        org.displaytag.decorator.CheckboxTableDecorator decorator = new org.displaytag.decorator.CheckboxTableDecorator();
							        decorator.setId("idReclamo");
							        decorator.setFieldName("_chk");
							        pageContext.setAttribute("checkboxDecorator", decorator);
							     ]]>
							  </jsp:scriptlet> 
							  
						    	<display:table  name="requestScope.lReclamos" requestURI="lIndemnizar.htm" class="displaytag" pagesize="10"
						            defaultsort="1" defaultorder="descending" sort="list" export="true" id="row" excludedParams="ajax _chk"
						            decorator="checkboxDecorator" >
						            	<display:column property="checkbox"/>
						            	<display:column title="Codigo" property="idReclamo" sortable="true" headerClass="sortable" />
						           		<display:column title="Asunto" property="asunto" sortable="true" headerClass="sortable" />
						           		<display:column title="Razon Social" property="factura.cliente.nomCliente" sortable="true" headerClass="sortable" media="csv" />
						          		<display:column title="RUC" property="factura.cliente.rucCliente" sortable="true" headerClass="sortable" />
						          		<display:column title="Factura" property="factura.numero" sortable="true" headerClass="sortable" />
							            <display:column title="Prioridad" sortable="true" headerClass="sortable">
							            	<c:if test="${row.prioridad == 1}">Alta</c:if>
							            	<c:if test="${row.prioridad == 2}">Normal</c:if>
							            	<c:if test="${row.prioridad == 3}">Baja</c:if>
							            </display:column>
							            <display:column title="Fec. Vencimiento" property="fecReclamo" format="{0,date,dd/MM/yyyy}" sortable="true" headerClass="sortable" />						         
							            <display:column title="Estado" sortable="true" headerClass="sortable">
							            	<c:if test="${row.estado == 1}">Abierto</c:if>
							            	<c:if test="${row.estado == 2}">Aceptado</c:if>
							            	<c:if test="${row.estado == 3}">Rechazado</c:if>
							            	<c:if test="${row.estado == 4}">Solucionado</c:if>
							            </display:column>
							            <display:column title="Respuesta" property="respuesta" sortable="false"/>
							            <display:column title="Indemnizar" sortable="false">
							            -
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
		document.forms[0].action="lIndemnizar.htm";
		document.forms[0].submit();
	});

	$(document).undelegate('#btnProcesar', 'click').delegate('#btnProcesar', 'click', function(){

		 var fields = $("input[name='_chk']").serializeArray(); 
		 if (fields.length === 0){ 
		    alert('Seleccione una factura.');
		 	return false;
		 }
		
		if(confirm('¿Indemnizar al cliente?')){
	   		console.info('redirecciona a indemnizar');
	   		window.location.assign("${pageContext.request.contextPath}/indemnizar/indemnizar.htm");
		}else{
			console.debug(' registrar en BD que no se va a indemnizar');
		}
		return false;
	});

	/*function nuevo(){
		document.forms[0].action='ncliente.htm';
		document.forms[0].action.submit();
	}
	function eliminar(id){
		if(confirm('¿Está seguro de eliminar al cliente?')){
			$("#tablaDinamica").css('opacity', 0.4);
	   		$("#tablaDinamica").load('eliminarCliente.htm?id='+ id +'&randval=' + Math.random() + " #resultado", 
	   				function(){ 
	   					$("#tablaDinamica").css('opacity', 1); 
	   					//$("#rolling").toggle(); 
	   				}
	   		);
		}
	}*/
	
	$( function(){
   	   $("#displayTagDiv").displayTagAjax();
   	});
   
</script>