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
							<legend>Posponer Capacitaci&oacute;n</legend>
							
							<form:hidden path="idFactura"/>
							<form:hidden path="cliente.idCliente"/>
							
							<!-- Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="ruc">RUC</label>  
							  <div class="col-md-4">
							  	<input type="text" name="ruc" id="ruc" value="1044194490" class="form-control input-md">
							  </div>
							</div>
							
							<!-- Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="razonSocial">Raz&oacute;n Social</label>  
							  <div class="col-md-4">
							  	<input type="text" name="razonSocial" id="razonSocial" value="Empresa UNO" class="form-control input-md">
							  </div>
							</div>
							
							<!-- Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="hInicio">Hora Inicio</label>  
							  <div class="col-md-4">
							  	<input type="text" name="hInicio" id="hInicio" placeholder="inicio" class="form-control input-md">
							  </div>
							</div>
							
							<!-- Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="hFinal">Hora Final</label>  
							  <div class="col-md-4">
							  	<input type="text" name="hFinal" id="hFinal" placeholder="final" class="form-control input-md">
							  </div>
							</div>
							
							<!-- Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="hFinal">Fecha</label>  
							  <div class="col-md-4">
							  	<div class='input-group date' id='datetimepicker1'>
				                    <input type='text' class="form-control" placeholder="dd/mm/yyyy"/>
				                    <span class="input-group-addon">
				                        <span class="glyphicon glyphicon-calendar"></span>
				                    </span>
				                </div>
							  </div>
							</div>
							
							<!-- Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="motivo">Motivo</label>  
							  <div class="col-md-4">
							  	<textarea name="motivo" id="motivo" placeholder="Motivo" class="form-control input-md">
							  	</textarea>
							  </div>
							</div>
							
							<!-- Button -->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="btnBuscar"></label>
							  <div class="col-md-4">
							    <input type="button" id="btnRegresar" name="btnRegresar" class="btn btn-success" value="Regresar"/>
							    <input type="button" id="btnAceptar" name="btnAceptar" class="btn btn-success" value="Grabar"/>
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
		if(confirm('¿Segudo de cancelar?')){
	   		console.info('redirecciona a lista de posponer capacitaciones');
	   		window.location.assign("${pageContext.request.contextPath}/capacitacion/lPosponer.htm");
		}
	});

	$(document).undelegate('#btnAceptar', 'click').delegate('#btnAceptar', 'click', function(){
		document.forms[0].action='posponer.htm';
		document.forms[0].submit();
	});
	
	$( function(){
		 
		 $('#datetimepicker1').datetimepicker();
		  
	});
   
</script>