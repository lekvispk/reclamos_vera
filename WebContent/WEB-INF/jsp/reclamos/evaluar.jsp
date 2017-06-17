<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="../include/cabecera.jsp"/>

<script>
	function evaluar(){
		
		var fields = $("input[name='respuesta']:checked").serializeArray(); 
	    if (fields.length == 0) { 
	        alert('Seleccione una opción');
	    }else {
	    	document.forms[0].submit();
	    }
		
	}
	
	function mostrarObservaciones(mostrar){
		if(mostrar==1){
			$("#observaciones").css("display", "block");
		}else{
			$("#observaciones").css("display", "none");
		}	
	}
</script>

        <!-- Page Content -->
        <div id="page-wrapper">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-12">
                                               
                       <jsp:include page="../include/error.jsp"/>
        
				   		<form:form cssClass="form-horizontal" name="frmDocumentos" action="evaluar.htm" method="post" modelAttribute="reclamo">
							<fieldset>
							<legend>Evaluar Reclamo</legend>
							
							<form:hidden path="idReclamo"/>
							
							Informaci&oacute;n General:<hr>
							
							<!-- Fecha de reclamo-->
							<div class="form-group">
							  <label class="col-md-4 control-label" >Nro. Factura:</label>  
							  <div class="col-md-5">
							  	<label class="form-control input-md">${reclamo.factura.numero}</label>
							  </div>
							</div>
							
							<!-- Fecha de reclamo-->
							<div class="form-group">
							  <label class="col-md-4 control-label" >Codigo de Producto:</label>  
							  <div class="col-md-5">
							  	<label class="form-control input-md">${reclamo.itemReclamo.detallefactura.producto.skuProducto}</label>
							  </div>
							</div>
							
							<!-- Fecha de reclamo-->
							<div class="form-group">
							  <label class="col-md-4 control-label" >Cantidad:</label>  
							  <div class="col-md-5">
							  	<label class="form-control input-md">${reclamo.itemReclamo.detallefactura.cantidad}</label>
							  </div>
							</div>
							
							<!-- Prioridad-->
							<div class="form-group">
							  <label class="col-md-4 control-label" >Prioridad</label>  
							  <div class="col-md-5">
							  	<label class="form-control input-md">
								  	<c:if test="${reclamo.prioridad == 1}">Alta</c:if>
					            	<c:if test="${reclamo.prioridad == 2}">Normal</c:if>
					            	<c:if test="${reclamo.prioridad == 3}">Baja</c:if>
				            	</label>
							  </div>
							</div>
							
							<!-- Fecha de reclamo-->
							<div class="form-group">
							  <label class="col-md-4 control-label" >Fecha de Reclamo:</label>  
							  <div class="col-md-5">
							  	<label class="form-control input-md">
							  	<fmt:formatDate value="${reclamo.fecReclamo}" pattern="dd/MM/yyyy" />
							  	</label>
							  </div>
							</div>
							
							Detalles del mensaje:<hr>
							
							<!-- asunto -->
							<div class="form-group">
							  <label class="col-md-4 control-label" >Asunto:</label>  
							  <div class="col-md-5">
							  	<label class="form-control input-md">
							  		${reclamo.asunto}
							  	</label>
							  </div>
							</div>
							
							<!-- mensaje -->
							<div class="form-group">
							 <label class="col-md-4 control-label" for="mensaje">Mensaje:</label>  
							  <div class="col-md-5">
							  	<textarea rows="5" class="form-control input-md" readonly="readonly" > ${reclamo.mensaje} </textarea>
							  </div>
							</div>
							
							<!-- mensaje -->
							<div class="form-group">
							 <label class="col-md-4 control-label" for="mensaje"></label>
							  <div class="col-md-5">
							  	<label class="col-md-4">&nbsp;<input type="radio" name="respuesta" value="Aceptado" onclick="javascript:mostrarObservaciones(0);">Aceptar</label>
							  	<label>&nbsp;<input type="radio" name="respuesta" value="Rechazado" onclick="javascript:mostrarObservaciones(1);">Rechazar</label>
							  </div>
							</div>
							
							<div class="form-group" id="observaciones" style="display: none;">
							 <label class="col-md-4 control-label" for="descripcion">Observaciones:</label>  
							  <div class="col-md-5">
							  	<textarea rows="5" id="descripcion" name="descripcion" class="form-control input-md"  ></textarea>
							  </div>
							</div>
							
							<!-- Button -->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="btnBuscar"></label>
							  <div class="col-md-4">
							    <input class="btn btn-success" type="button" onclick="javascript:evaluar();" value="Aceptar">
							    <input class="btn btn-success" type="button" onclick="javascript:history.back();" value="Regresar">
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
	 
	