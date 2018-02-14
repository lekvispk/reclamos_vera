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
							<legend>Autorizar Cambio de Producto</legend>
							
							<input type="hidden" name="idReclamo" id="idReclamo" value="">
							
							<!-- Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="nroReclamo">Nro reclamo</label>  
							  <div class="col-md-4">
							  	<input type="text" name="nroReclamo" id="nroReclamo" placeholder="REC - 000" class="form-control input-md">
							  </div>
							  <div class="col-md-4">
							    <input type="button" id="btnBuscar" name="btnBuscar" class="btn btn-success" value="Buscar"/>
							  </div>
							</div>
							
							<!-- Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="razonSocial">Nro. Factura</label>  
							  <div class="col-md-4">
							  	<input type="text" name="nroFactura" id="nroFactura" placeholder="FACT-0000" class="form-control input-md" disabled>
							  </div>
							</div>
							
							<!-- Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="descripcion">Descripcion</label>  
							  <div class="col-md-4">
							  	<input type="text" name="descripcion" id="descripcion" class="form-control input-md" disabled>
							  </div>
							</div>
											
							<!-- Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="hFinal">Fecha de autorizacion</label>  
							  <div class="col-md-4">
							  	<div class='input-group date' id='datetimepicker1'>
				                    <input type='text' name="fechaAutorizacion" id="fechaAutorizacion" class="form-control" placeholder="dd/mm/yyyy"/>
				                    <span class="input-group-addon">
				                        <span class="glyphicon glyphicon-calendar"></span>
				                    </span>
				                </div>
							  </div>
							</div>
							
							<!-- Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="nroActa">Nro. Acta</label>  
							  <div class="col-md-2">
							  	<form:input path="numeroActa" id="numeroActa" cssClass="form-control input-md"/>
							  </div>
							</div>
							
							<!-- Button -->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="btnBuscar"></label>
							  <div class="col-md-4">
							    <!-- <input type="button" id="btnImprimir" name="btnImprimir"  class="btn btn-success" value="Imprimir"/> -->
							    <input type="button" id="btnAutoriza" name="btnAutoriza" class="btn btn-success" value="Autorizar"/>
							    <input type="button" id="btnCancela" name="btnCancela"  class="btn btn-success" value="Cancelar"/>
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
	
	$(document).undelegate('#btnBuscar', 'click').delegate('#btnBuscar', 'click', function(){
		
		limpiarErrorAjax();
		
		if( $('#nroReclamo').val() == '' ){
			agregarErrorAjax( 'No ha indicado un reclamo' );
			return;
		}
		
		var url = "${pageContext.request.contextPath}/rest/clientes/reclamos/" + $('#nroReclamo').val();
		$.ajax({
            url: url,
            dataType: "json",
            method:"GET",
            data: {  },
            success: function( data, textStatus, jqXHR) {
                console.log( " idReclamo " +  data.idReclamo);

				if( data.status ){
					agregarErrorAjax( 'Error: ' + data.mensaje  );
					$('#nroFactura').val( '' );
					$('#descripcion').val( '' );
					$('#fechaAutorizacion').val( '' );
					return;
				}
                
                $('#idReclamo').val( data.idReclamo );
                if( data.factura ){
                	$('#nroFactura').val( data.factura.numero );
                	$('#descripcion').val( data.mensaje );
                }
            },
            error: function(jqXHR, textStatus, errorThrown){
                 console.log(textStatus);
                 $('#idReclamo').val( '' );
                 agregarErrorAjax( 'error al acceder al servidor' );
            }
        });
        
	});

	$(document).undelegate('#btnCancela', 'click').delegate('#btnCancela', 'click', function(){
		window.location.assign("${pageContext.request.contextPath}/producto/autorizar.htm");
	});
	
	$(document).undelegate('#btnAutoriza', 'click').delegate('#btnAutoriza', 'click', function(){

		limpiarErrorAjax();
		
		var idReclamo = $("#idReclamo").val();
		var fecha = $("#fechaAutorizacion").val();
		var acta = $("#numeroActa").val();
	    
		if ( !idReclamo ) {
	    	agregarErrorAjax( 'No se ha obtenido un reclamo' );
	    	return false;
	    }
		
		if ( !fecha ) {
	    	agregarErrorAjax( 'Ingrese la fecha' );
	    	return false;
	    }
		
		if ( !acta ) {
	    	agregarErrorAjax( 'Ingrese el numero de acta' );
	    	return false;
	    }
	    
		$('#btnAutoriza').attr( 'disabled','disabled' );
		
		var url = "${pageContext.request.contextPath}/producto/autorizar.htm";
		$.ajax({
            url: url,
            dataType: "json",
            method:"POST",
            data: { "idReclamo" : $('#idReclamo').val(),
					"fechaAutorizacion" : $('#fechaAutorizacion').val(),
					"numeroActa" : $('#numeroActa').val() 
			},
            success: function( data, textStatus, jqXHR) {
                console.log( " status " +  data.status);
                if( data.status ){
                	$('#btnAutoriza').removeAttr('disabled');
                	agregarMensajeAjax( 'Datos grabados satsfactoriamente' );
                	document.getElementById("frmDocumentos").reset();
                	$('#numeroActa').val( data.nroActa );
                }
            },
            error: function(jqXHR, textStatus, errorThrown){
                 console.log(textStatus);
                 agregarErrorAjax( 'error al acceder al servidor' );
            }
        });
	});

	 $( function(){

		$('#datetimepicker1').datetimepicker({
		    format: 'DD/MM/YYYY'
		});
		 
   	   	$("#displayTagDiv").displayTagAjax();
   });
   
</script>