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
							<legend>Fidelizar Clientes</legend>
							
							<!-- Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="fecFactura">Desde</label>  
							  <div class="col-md-4">
							  	<div class='input-group date' id='datetimepicker1'>
				                    <fmt:formatDate value="${factura.emision}" pattern="dd/MM/yyyy" var="f_fecFactura"/>
                    				<input type="text" class="form-control input-md" name="emision" id="emision" placeholder="dd/MM/yyyy" size="10" value="${f_fecFactura}"/>
				                    <span class="input-group-addon">
				                        <span class="glyphicon glyphicon-calendar"></span>
				                    </span>
				                </div>
							  </div>
							</div>
							
							<!-- Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="fecFacturaFin">Hasta</label>  
							  <div class="col-md-4">
							  	<div class='input-group date' id='datetimepicker2'>
				                    <fmt:formatDate value="${factura.emisionFin}" pattern="dd/MM/yyyy" var="f_fecFacturaFin"/>
                    				<input type="text" class="form-control input-md" name="emisionFin" id="emisionFin" placeholder="dd/MM/yyyy"  size="10" value="${f_fecFacturaFin}"/>
				                    <span class="input-group-addon">
				                        <span class="glyphicon glyphicon-calendar"></span>
				                    </span>
				                </div>
							  </div>
							</div>
							
								<!-- Select Basic -->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="cmbPrioridad">Consumo</label>
							  
							    <div class="col-md-4 form-group input-group">
                                     <span class="input-group-addon">S/.</span>
                                     <input type="text" name="monto" id="monto" value="5000" readonly="readonly" class="form-control" />
                                     
                                 </div>
							  
							</div>
							
							<!-- Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="txtRazonSocial">RUC</label>  
							  <div class="col-md-5">
							  	<form:input path="cliente.rucCliente" id="rucCliente" size="20" cssClass="form-control input-md" />
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
					   		  
						    	<display:table  name="requestScope.lFacturas" requestURI="lFidelizar.htm" class="displaytag" pagesize="10"
						            defaultsort="1" defaultorder="descending" sort="list" export="true" id="row" excludedParams="ajax _chk"
						            >
						            
						            <display:column title="" >
						            	<a href="lCompensar.htm?idCliente=${row.cliente.idCliente}">ver</a>
						            </display:column>
						            <display:column title="Razon Social" property="cliente.nomCliente" sortable="true" headerClass="sortable" />
						            <display:column title="RUC" property="cliente.rucCliente" sortable="true" headerClass="sortable" />
						            <display:column title="Factura" property="numero" sortable="true" headerClass="sortable" />
						            <display:column title="Fec. Emision" property="emision" format="{0,date,dd/MM/yyyy}" sortable="true" headerClass="sortable" />
						            <display:column title="Consumo" property="monto" sortable="true" headerClass="sortable" />
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
		document.forms[0].action="lFidelizar.htm";
		document.forms[0].submit();
		//return false;
	});
	
	$( function(){
   		$("#displayTagDiv").displayTagAjax();

   		$('#datetimepicker1').datetimepicker({
		    format: 'DD/MM/YYYY'
		});

   		$('#datetimepicker2').datetimepicker({
   		    format: 'DD/MM/YYYY'
   		});
   		
   	});
   
</script>