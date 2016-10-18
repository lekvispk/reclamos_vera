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
        
				   		<form class="form-horizontal" name="frmDocumentos" id="frmDocumentos" action="#" method="post">
				   		
							<fieldset>
							
							<!-- Form Name -->
							<legend>Reclamos</legend>
							
							<!-- Factura Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="numero">Factura</label>  
							  <div class="col-md-5">
							  	<input type="text" name="numero" id="numero" size="10" placeholder="Factura" class="form-control input-md"> 
							  </div>
							</div>
							
							<!-- Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="fecFacturaFin">Id Ticket</label>  
							  <div class="col-md-5">
							  	<input type="text" name="idticket" id="idticket" size="10" placeholder="idticket" class="form-control input-md">
							  </div>
							</div>
							
							<!-- Select Basic -->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="cmbPrioridad">Estado</label>
							  <div class="col-md-4">
							  	<select  name="estado" id="estado" class="form-control input-md" >
							  	</select>
							  </div>
							</div>
							
							<!-- Button -->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="btnBuscar"></label>
							  <div class="col-md-4">
							    <input type="button" id="btnBuscar" name="btnBuscar" class="btn btn-success" value="Buscar"/>
							  </div>
							  <div class="col-md-4">
							    <input type="button" id="btnVerDetalle" name="btnVerDetalle" class="btn btn-success" value="Ver Detalle"/>
							  </div>
							</div>
							
							</fieldset>
				         </form>
				   		
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
							  
						    	<display:table  name="requestScope.lReclamos" requestURI="lista.htm" class="displaytag" pagesize="3"
						            defaultsort="1" defaultorder="descending" sort="list" export="true" id="row" excludedParams="ajax _chk"
						            decorator="checkboxDecorator" >
						            
						            <display:column title="" >
						            	<input type="radio" id="rbBeneficio_${row.idReclamo}" name="rbBeneficio" value="${row.idReclamo}">
						            </display:column>
						            <display:column title="ID Ticket" property="idReclamo" sortable="true" headerClass="sortable" />
						            <display:column title="Factura" property="factura.numero" sortable="true" headerClass="sortable" />
						            <display:column title="RUC" property="factura.cliente.rucCliente" sortable="true" headerClass="sortable" />
						            <display:column title="Prioridad" property="prioridad" sortable="true" headerClass="sortable" />
						            <display:column title="Fec. Respuesta" property="fecVencimiento" format="{0,date,dd/MM/yyyy}" sortable="true" headerClass="sortable" />
						            <display:column title="Estado" property="estado" sortable="true" headerClass="sortable" />
						    	
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
		
	});
	
	$(document).undelegate('#btnVerDetalle', 'click').delegate('#btnVerDetalle', 'click', function(){
		 var fields = $("input[name='rbBeneficio']").serializeArray(); 
		 if (fields.length === 0){ 
		    alert('Seleccione una factura.');
		 	return false;
		 }
		var idReclamo = $('input[name=rbBeneficio]:checked').val();
   		console.info('ver detalle de reclamo');
   		window.location.assign("${pageContext.request.contextPath}/seguimiento/ver.htm?idReclamo="+idReclamo);
		
	});
	
	 $( function(){
   	   $("#displayTagDiv").displayTagAjax();
   });
   
</script>