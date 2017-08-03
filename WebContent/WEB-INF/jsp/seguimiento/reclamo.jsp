<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="../include/cabecera.jsp"/>


        <!-- Page Content -->
        <div id="page-wrapper">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-12">
                                               
                       <jsp:include page="../include/error.jsp"/>
        
        				<form:form cssClass="form-horizontal" name="frmDocumentos" action="registro.htm" method="post" modelAttribute="reclamo">
        					
        					<form:hidden path="idReclamo"/>
        					<input type="hidden" value="1" name="estado"/>  
        				
							<fieldset>
							
							<!-- Form Name -->
							<legend>Registrar Reclamo</legend>
							
							<!-- Id Ticket Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="idTicket">Id Ticket</label>  
							  <div class="col-md-5">
							  	<label class="form-control input-md" > ${reclamo.idReclamo} </label>
							  </div>
							</div>
							
							<!-- Factura Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="idFactura">Factura</label>  
							  <div class="col-md-5">
							  	<label class="form-control input-md" > ${reclamo.factura.numero} </label> 
							  </div>
							</div>
							
							<!-- RUC Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="rucCliente">RUC</label>  
							  <div class="col-md-5">
							  
							   	<c:if test="${reclamo.factura.cliente.idCliente > 0}">
			                   		<form:hidden path="factura.cliente.idCliente" id="idCliente"/>
			                   	</c:if>
			                   	<c:if test="${empty reclamo.factura.cliente.idCliente || reclamo.factura.cliente.idCliente <= 0}">
			                   		<input type="hidden" name="factura.cliente.idCliente" id="idCliente" value="-1"/>
			                   	</c:if>
			                   	
			                    <div class="demo" style="float: left;">
									<div class="ui-widget">
										<form:input path="factura.cliente.rucCliente" id="tagsCliente" size="40" cssClass="form-control input-md" />
									</div>
								</div>
								
							  </div>
							</div>
							
							<!-- Prioridad Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="prioridad">Prioridad</label>  
							  <div class="col-md-5">
							  	<label class="form-control input-md" > 
								  	<c:if test="${reclamo.prioridad == 1}">Alta</c:if>
					            	<c:if test="${reclamo.prioridad == 2}">Normal</c:if>
					            	<c:if test="${reclamo.prioridad == 3}">Baja</c:if>
							  	</label> 
							  </div>
							</div>
							
							<!-- Razon Social Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="rucCliente">Razon Social</label>  
							  <div class="col-md-5">
							  	<label class="form-control input-md" > 
							   	<c:if test="${reclamo.factura.cliente.idCliente > 0}">
			                   		${reclamo.factura.cliente.nomCliente }
			                   	</c:if>
			                   	</label>
							  </div>
							</div>
							
							<!-- Fecha de reclamo Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="fecha">Fecha de Reclamo</label>  
							  <div class="col-md-5">
							  	<label class="form-control input-md" >
							  		 <fmt:formatDate value="${reclamo.fecReclamo}" pattern="dd/MM/yyyy" />
							  	</label>
							  </div>
							</div>
							
							<!-- Fecha de respuesta Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="fecha">Fecha de Respuesta</label>  
							  <div class="col-md-5">
							  	<label class="form-control input-md" >
							  		 <fmt:formatDate value="${reclamo.fecRespuesta}" pattern="dd/MM/yyyy" />
							  	</label>
							  </div>
							</div>
							
							<!-- Asunto Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="asunto">Asunto</label>  
							  <div class="col-md-5">
							  	<form:input path="asunto" id="asunto" cssClass="form-control input-md"/>
							  </div>
							</div>
							
							<!-- mensaje Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="mensaje">Detalle</label>  
							  <div class="col-md-5">
							  	<form:textarea path="mensaje" id="mensaje" cssClass="form-control input-md" cols="40" rows="5"/>
							  </div>
							</div>
							
							<!-- respuesta Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="mensaje">Respuesta</label>  
							  <div class="col-md-5">
							  	<form:textarea path="respuesta" id="respuesta" cssClass="form-control input-md" cols="40" rows="5"/>
							  </div>
							</div>
							
							<!-- solucion Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="solucion">Solucion</label>  
							  <div class="col-md-5">
							  	<form:textarea path="solucion" id="solucion" cssClass="form-control input-md" cols="40" rows="5"/>
							  </div>
							</div>
														
							<!-- Button -->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="btnBuscar"></label>
							  <div class="col-md-4">
							    <input class="btn btn-success" type="button" id="btnRegresar" value="Regresar">
							  </div>
							</div>
							
							</fieldset>
				          </form:form>
				   	
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
	
	$(document).undelegate('#btnRegresar', 'click').delegate('#btnRegresar', 'click', function(){
		
		window.location.assign("${pageContext.request.contextPath}/seguimiento/lista.htm");
		
	});

	$( function(){
		 
   	   $("#displayTagDiv").displayTagAjax();
   	   
   });
   
</script>