<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"  %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="../include/cabecera.jsp"/>


        <!-- Page Content -->
        <div id="page-wrapper">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-12">
                                               
                       <jsp:include page="../include/error.jsp"/>
        		
        				<form:form cssClass="form-horizontal" name="frmDocumentos" id="frmDocumentos" action="#" method="post" modelAttribute="reclamo">
				   			<input type="hidden" id="indemnizar" name="indemnizar" value="si">
				   			<form:hidden path="idReclamo"/>
				   			
							<fieldset>
							
							<!-- Form Name -->
							<legend>Indemnizar Calcular</legend>
							
							<!-- nro reclamo Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="txtRuc">Nro de Recalmo</label>  
							  <div class="col-md-5">
							  	<label class="form-control input-md">${reclamo.idReclamo}</label>
							  </div>
							</div>
							<!-- Fecha de reclamo-->
							<div class="form-group">
							  <label class="col-md-4 control-label" >Nro. Factura:</label>  
							  <div class="col-md-5">
							  	<label class="form-control input-md">${reclamo.factura.numero}</label>
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
							
							<!-- Fecha de vencimiento-->
							<div class="form-group">
							  <label class="col-md-4 control-label" >Fecha de Vencimiento:</label>  
							  <div class="col-md-5">
							  	<label class="form-control input-md">
							  	<fmt:formatDate value="${reclamo.vencimiento}" pattern="dd/MM/yyyy" />
							  	</label>
							  </div>
							</div>
							
							<!-- monto inicial Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="inicial">Monto inicial</label>
							  <div class="col-md-3">
							  	<input type="text" id="inicial" name="inicial"  placeholder="0.0" class="form-control input-md">
							  </div>
							  <div class="col-md-2">
							  	<input type="text" id="adicional" name="adicional"  placeholder="0%" class="form-control input-md">
							  </div>
							</div>
							
							<!-- monto total Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="txtTotal">Monto Total</label>
							  <div class="col-md-5">
							  	<input type="text" id="totalIndemnizacion" name="totalIndemnizacion" readonly="readonly" placeholder="0.0" class="form-control input-md">
							  </div>
							</div>
							
							<!-- Button -->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="btnBuscar"></label>
							  <div class="col-md-4">
							    <input class="btn btn-success" type="button" id="btnRegistrar" value="Registrar">
							  </div>
							  <div class="col-md-4">
							    <input class="btn btn-success" type="button" id="btnCalcular" value="Calcular">
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

	$( "#frmDocumentos" ).validate({
	  rules: {
		inicial: {
	      required: true,
	      number: true
	    },
	    adicional: {
	      required: true,
	      number: true
	    },
	    totalIndemnizacion: {
	      required: true,
	      number: true
	    }
	  }
	});

	$(document).undelegate('#btnRegistrar', 'click').delegate('#btnRegistrar', 'click', function(){
		var total = $('#totalIndemnizacion').val();
		if( total && total!='' ){ 
			document.forms[0].action='indemnizar.htm';
			document.forms[0].submit();	 
		}else{
			alert('No se ha calculado el total');
			return false;
		}
	});

	$(document).undelegate('#btnCalcular', 'click').delegate('#btnCalcular', 'click', function(){
		console.log('calculando...');
		var inicial = $('#inicial').val();
		var porcentaje = $('#adicional').val();

		if( inicial && porcentaje && ( inicial!='' && porcentaje !='' )){
			var total = inicial*1	 + (inicial * ( porcentaje/100 )) ;
			console.log('total=' + total + ' value = ' + $('#totalIndemnizacion').attr('value' ) );
			$('#totalIndemnizacion').attr('value', total );
		}else{
			alert('Ingrese los montos Inicial y el porcentaje');
		}
		
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