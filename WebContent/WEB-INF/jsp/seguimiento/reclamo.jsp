<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"  %>

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
							  	<input type="text" name="idTicket" id="idTicket" class="form-control input-md" value="000" placeholder="id ticket">
							  </div>
							</div>
							
							<!-- Factura Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="idFactura">Factura</label>  
							  <div class="col-md-5">
							  	<select name="factura.idFactura" id="idFactura">
							  		<option value="1">0000222</option>
							  	</select>
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
							  	
							  	<form:select path="prioridad">
							  		<form:option value="1">Alta</form:option>
							  		<form:option value="2">Normal</form:option>
							  		<form:option value="3">Baja</form:option>
							  	</form:select>
							  	
							  </div>
							</div>
							
							<!-- Razon Social Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="rucCliente">Razon Social</label>  
							  <div class="col-md-5">
							   	<c:if test="${reclamo.factura.cliente.idCliente > 0}">
			                   		${reclamo.factura.cliente.nomCliente }
			                   	</c:if>
							  </div>
							</div>
							
							<!-- Fecha Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="fecha">Fecha</label>  
							  <div class="col-md-5">
							  	<input type="text" name="fecha" id="fecha" value=""/>
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
							  	<form:textarea path="mensaje" id="mensaje" cssClass="form-control input-md" cols="40" rows="5"/>
							  </div>
							</div>
							
							<!-- observacion Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="mensaje">Observacion</label>  
							  <div class="col-md-5">
							  	<form:textarea path="mensaje" id="mensaje" cssClass="form-control input-md" cols="40" rows="5"/>
							  </div>
							</div>
							
							<!-- Button -->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="btnBuscar"></label>
							  <div class="col-md-4">
							    <input class="btn btn-success" type="button" value="Regresar">
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
	
	 $( function(){
		 
   	   $("#displayTagDiv").displayTagAjax();
   	   
   });
   
</script>