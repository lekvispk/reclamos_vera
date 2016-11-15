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
				   			<input type="hidden" name="id_reclamos">
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
							  </div>
							</div>
							
							</fieldset>
				          </form:form>
				   		
                         	<div id="tablaDinamica">
						 	<div id="resultado">
					   		<div id="displayTagDiv">
						    	
						    	<display:table  name="requestScope.lReclamos" requestURI="lIndemnizar.htm" class="displaytag" pagesize="10"
						            defaultsort="1" defaultorder="descending" sort="list" export="true" id="row" excludedParams="ajax _chk"
						            >
						            	<display:column>
						            		<c:if test="${row.estado == 1}">
						            			<input type="checkbox" name="_chk" id="_chk${row.idReclamo}" value="${row.idReclamo}">
						            		</c:if>
						            	</display:column>
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
							            	<c:if test="${row.estado == 1}">En Proceso</c:if>
							            	<c:if test="${row.estado == 2}">Atendido</c:if>
							            </display:column>
							            <display:column title="Respuesta" property="respuesta" sortable="false"/>
							            <display:column title="Indemnizar" property="indemnizar" sortable="false"/>
						   		</display:table>
							</div>
						  	</div>
							</div>			
                        
                        	<!-- radio button-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="rbIndemnizar">Indemnizar</label>
							  <div class="col-md-8">
							    <label><input type="radio" name="rbIndemnizar" id="rbIndemnizar_si" value="si">SI</label>
							    <label><input type="radio" name="rbIndemnizar" id="rbIndemnizar_no" value="no">No</label>
							  </div>
							</div>
							
                        	<!-- Button -->
							<div class="form-group">
							  <div class="col-md-4"></div>
							  <div class="col-md-4">
							    <button id="btnProcesar" name="btnProcesar" class="btn btn-success">Proccesar</button>
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
		    alert('Seleccione un reclamo.');
		 	return false;
		 }

		 var rb_indemnizar = $("input[name='rbIndemnizar']").serializeArray(); 
		 if (rb_indemnizar.length === 0){ 
			 alert('Seleccione una opcion.');
			 return false;
		 }

		 var rb_nm = $('input[name=rbIndemnizar]:checked').val();
		 
		if( rb_nm == 'si'){
	   		console.info('redirecciona a indemnizar');
	   		window.location.assign("${pageContext.request.contextPath}/indemnizar/indemnizar.htm?idReclamo="+$("input[name='_chk']").val());
		}else{
			//ajax update a NO
			fields_values = "";
			for (i = 0; i < fields.length; i++) { 
				fields_values += fields[i].value + "_";
			}
			
			console.log( 'reclamos a negar indemnizacion : ' + fields_values );
			document.forms[0].action="noIndemnizar.htm";
			document.forms[0].id_reclamos.value=fields_values;
			document.forms[0].submit();
		}
		return false;
	});

	$( function(){
   	   $("#displayTagDiv").displayTagAjax();
   	});
   
</script>