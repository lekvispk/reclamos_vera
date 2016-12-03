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
        
				   		<form:form cssClass="form-horizontal" name="frmDocumentos" id="frmDocumentos" action="#" method="post" modelAttribute="capacitacion">
				   		
							<fieldset>
							
							<!-- Form Name -->
							<legend>Posponer Capacitaci&oacute;n</legend>
							<form:hidden path="idCapacitacion"/>
							
							<!-- Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="ruc">RUC</label>  
							  <div class="col-md-4">
							  	<label class="form-control input-md"> ${capacitacion.factura.cliente.rucCliente} </label>
							  </div>
							</div>
							
							<!-- Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="razonSocial">Raz&oacute;n Social</label>  
							  <div class="col-md-4">
							  	<label class="form-control input-md"> ${capacitacion.factura.cliente.nomCliente} </label>
							  </div>
							</div>
							
							<!-- Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="hInicio">Hora Inicio</label>  
							  <div class="col-md-4">
							  	<div class='input-group date' id='dateti'>
							  		<fmt:formatDate value="${capacitacion.fechaCapacitacion}" pattern="dd/MM/yyyy" var="f_fecha"/>
				                    <fmt:formatDate value="${capacitacion.horaCapacitacion}" pattern="HH:mm" var="f_horaCapacitacion"/>
                    				<label class="form-control input-md"> ${f_fecha} ${f_horaCapacitacion} </label>
                    				<span class="input-group-addon">
				                        <span class="glyphicon glyphicon-time"></span>
				                    </span>
				                </div>
							  </div>
							</div>
							
							<!-- Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="fecha">Fecha</label>  
							  <div class="col-md-4">
							  	<div class='input-group date' id='datetimepicker1'>
				                    <fmt:formatDate value="${capacitacion.fechaCapacitacion}" pattern="dd/MM/yyyy" var="f_fechaCapacitacion"/>
                    				<input type="text" class="form-control input-md" name="fechaCapacitacion" id="fechaCapacitacion" placeholder="dd/MM/yyyy"  size="10" value="${f_fechaCapacitacion}"/>
				                    <span class="input-group-addon">
				                        <span class="glyphicon glyphicon-calendar"></span>
				                    </span>
				                </div>
							  </div>
							</div>
							
							<!-- Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="hora">Hora</label>  
							  <div class="col-md-4">
							  	<div class='input-group date' id='datetimepicker2'>
				                    <fmt:formatDate value="${capacitacion.horaCapacitacion}" pattern="HH:mm" var="f_horaCapacitacion"/>
                    				<input type="text" class="form-control input-md" name="horaCapacitacion" id="horaCapacitacion" placeholder="HH:mm"  size="10" value="${f_horaCapacitacion}"/>
				                    <span class="input-group-addon">
				                        <span class="glyphicon glyphicon-time"></span>
				                    </span>
				                </div>
							  </div>
							</div>							
							<!-- Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="motivo">Motivo</label>  
							  <div class="col-md-4">
							  	<textarea name="motivoPospuesto" id="motivoPospuesto" placeholder="Motivo" class="form-control input-md"></textarea>
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
		 
		$('#datetimepicker1').datetimepicker({
		    format: 'DD/MM/YYYY'
		});
	
	   	$('#datetimepicker2').datetimepicker({
	   		format: 'LT'
		});
		  
	});
   
</script>