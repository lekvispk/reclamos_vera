<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
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
        
				   		<form:form cssClass="form-horizontal" name="frmDocumentos" id="frmDocumentos" action="#" method="post" modelAttribute="factura">
				   		
							<fieldset>
							
							<!-- Form Name -->
							<legend>Fidelizar Clientes - Compensar</legend>
							
							<form:hidden path="idFactura"/>
							<form:hidden path="cliente.idCliente"/>
							
							<!-- Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="ruc">RUC</label>  
							  <div class="col-md-4">
							  	<label class="form-control" >${factura.cliente.rucCliente}</label>
							  </div>
							</div>
							
							<!-- Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="razonSocial">Raz&oacute;n Social</label>  
							  <div class="col-md-4">
							  	<label class="form-control" >${factura.cliente.nomCliente}</label>
							  </div>
							</div>
							
								<!-- Select Basic -->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="monto">Consumo</label>
							  <div class="col-md-4">
							    <label class="form-control" >${factura.monto}</label>
							  </div>
							</div>
							
							<!-- Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="numero">Factura</label>  
							  <div class="col-md-5">
							  	<form:input path="numero" id="numero" size="10" cssClass="form-control input-md" />
							  </div>
							</div>
							
							<!-- Button -->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="btnBuscar"></label>
							  <div class="col-md-4">
							    <input type="button" id="btnBuscar" name="btnBuscar" onclick="javascript:buscar();" class="btn btn-success" value="Buscar"/>
							  </div>
							</div>
							
							</fieldset>
				         </form:form>
				   		
                         	<div id="tablaDinamica">
						 	<div id="resultado">
					   		<div id="displayTagDiv">
					   	
					   			<display:table  name="requestScope.lFacturas" requestURI="lFidelizar.htm" class="displaytag" pagesize="10"
						            defaultsort="1" defaultorder="descending" sort="list" export="true" id="row" excludedParams="ajax _chk"
						            >
						            <display:column title="Beneficio" >
						            	<c:if test="${row.estado==1}">
						            	<input type="radio" id="rbBeneficio_${row.idFactura}" name="rbBeneficio" value="${row.idFactura}">
						            	</c:if>
						            	<c:if test="${row.estado==2}">
						            		Resuelto
						            	</c:if>
						            </display:column>
						            <display:column title="Razon Social" property="cliente.nomCliente" sortable="true" headerClass="sortable" />
						            <display:column title="Factura" property="numero" sortable="true" headerClass="sortable" />
						            <display:column title="Fec. Factura" property="emision" format="{0,date,dd/MM/yyyy}" sortable="true" headerClass="sortable" />
						            <display:column title="Consumo" property="monto" sortable="true" headerClass="sortable" />
						    	</display:table>
							
							</div>
						  	</div>
							</div>			
                        
                        	<!-- Button -->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="btnBuscar"></label>
							  <div class="col-md-4">
							    <input type="button" id="btnAceptar" name="btnAceptar" onclick="javascript:aceptar();" class="btn btn-success" value="Aceptar"/>
							    <input type="button" id="btnRegresar" name="btnRegresar" onclick="javascript:history.back();" class="btn btn-success" value="Regresar"/>
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
	
	$( function(){
   	   $("#displayTagDiv").displayTagAjax();
	});

	$(document).undelegate('#btnAceptar', 'click').delegate('#btnAceptar', 'click', function(){

		var fields = $("input[name='rbBeneficio']").serializeArray(); 
	    if (fields.length == 1) { 
	    	document.forms[0].action="compensar.htm";
	    	document.forms[0].idFactura.value=$("input[name='rbBeneficio']").val();
			document.forms[0].submit();
	    }else {
	    	alert('Seleccione un elemento');
	    }
	    
	});

	$(document).undelegate('#btnRegresar', 'click').delegate('#btnRegresar', 'click', function(){
		window.location="${pageContext.request.contextPath}/fidelizar/lFidelizar.htm";
	});
	
	   /*
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
	*/
</script>