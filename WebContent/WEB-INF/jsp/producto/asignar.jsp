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
							<legend>Asignar distribuidor</legend>
							
							<!-- Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="ruc">Día de la semana</label>  
							  <div class="col-md-4">
							  	<select name="dia" id="dia" class="form-control input-md">
							  		<option value="2">Lunes</option>
							  		<option value="3">Martes</option>
							  		<option value="4">Miércoles</option>
							  		<option value="5">Jueves</option>
							  		<option value="6">Viernes</option>
							  		<option value="7">Sábado</option>
							  		<option value="1">Domingo</option>
							  	</select>
							  </div>
							  <div class="col-md-4">
							    <input type="button" id="btnBuscar" name="btnBuscar" class="btn btn-success" value="Buscar"/>
							  </div>
							</div>
							
							<div class="form-group">
							  <label class="col-md-4 control-label" for="ruc">Hora de entrega</label>  
							  
							  <div class="col-md-4">
							  	
								<div class="input-group bootstrap-timepicker timepicker">
						            <input id="hora" name="hora" type="text" class="form-control input-small">
						            <span class="input-group-addon"><i class="glyphicon glyphicon-time"></i></span>
					        	</div>
							  
							  </div>
							  
							</div>
							
							<div class="form-group">
								<div class="col-md-2"></div>
							  	<div class="col-md-6">
								  	<div id="tablaDinamica">
									 	<div id="resultado">
								   		<div id="displayTagDiv">
								   		
									    	<display:table  name="requestScope.lProductos" requestURI="lPromociones.htm" class="displaytag" 
									            defaultsort="1" defaultorder="descending" sort="list" export="false" id="rowProductos" excludedParams="ajax">
									            
									            <display:column>
									            	<input type="radio" name="_chkIt" id="_chkIt_${rowProductos.idDetalleDevolucion}" value="${rowProductos.idDetalleDevolucion}"/>
									            </display:column>
									            <display:column title="Codigo" property="producto.skuProducto" sortable="false" headerClass="sortable" />
									            <display:column title="Descripcion" property="producto.descripcion" sortable="false" headerClass="sortable" />
									            
									    	</display:table>
										
										</div>
									  	</div>
									</div>	
							   	</div>
							   	<div class="col-md-4">
								    
								</div>
							</div>
						
							<div class="form-group">
							  <div class="col-md-2"></div>
							  <div class="col-md-6">
															  	
								<div id="tablaDinamica2">
								 	<div id="resultado2">
								  		<div id="displayTagDiv">
								  			
								  			<display:table  name="requestScope.lDespachadores" requestURI="lPromociones.htm" class="displaytag" 
								            defaultsort="1" defaultorder="descending" sort="list" export="false" id="rowRepartidor" excludedParams="ajax">
									            
									            <display:column>
								            		<input type="radio" name="_chkDes" id="_chkDes_${rowRepartidor.idDespachador}" value="${rowRepartidor.idDespachador}"/>
								            	</display:column>
								            	<display:column title="Codigo" property="codigo" sortable="false" headerClass="sortable" />
									            <display:column title="Nombre" property="nombres" sortable="false" headerClass="sortable" />
									            <display:column title="Nombre" property="apellidos" sortable="false" headerClass="sortable" />
									            
									    	</display:table>
										
										</div>
								  	</div>
								</div>	
							   </div>
							   <div class="col-md-4">
							   		<input type="button" id="btnAsignar" name="btnAsignar"  class="btn btn-success" value="Asignar"/>
							   </div>
							</div>
							
							<!-- Button -->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="btnCancela"></label>
							  <div class="col-md-4">
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
		
		var dia = $( "#dia option:selected" ).val();
		console.log('seleccionado ' + dia);
		
		$("#tablaDinamica").css('opacity', 0.4);
   		$("#tablaDinamica").load('${pageContext.request.contextPath}/producto/buscarProductosParaAsignarDespachador.htm?dia='+ dia + '&randval=' + Math.random() + " #resultado", 
   			function(){ 
   				$("#tablaDinamica").css('opacity', 1); 
   				//$("#rolling").toggle(); 
   			}
   		);
   		
   		cargarListaTrabajadores();
   		
	});
	
	$(document).undelegate('#btnAsignar', 'click').delegate('#btnAsignar', 'click', function(){
		
		console.log('Asignar el producto al despachador');
		
		limpiarErrorAjax();
    	
		var productoSeleccionado = $("input[name='_chkIt']:checked").serializeArray(); 
	    if (productoSeleccionado.length == 0) { 
	    	agregarErrorAjax( 'Selecione un producto' );
	    	return;
	    }
	    
	    var despachadorSeleccionado = $("input[name='_chkDes']:checked").serializeArray(); 
	    if (despachadorSeleccionado.length == 0) { 
	    	agregarErrorAjax( 'Selecione un despachador' );
	    	return;
	    }
	    
	    var horaDeEntrega = $("#hora").val(); 
	    if ( horaDeEntrega.length == 0 ) { 
	    	agregarErrorAjax( 'Indique la hora de despacho' );
	    	return;
	    }
	    
	    console.log('inicia llamada ajax....');
	    
	    $.ajax({
            url: "${pageContext.request.contextPath}/producto/asignar.htm",
            dataType: "json",
            method: "POST",
            data: { 
    	        	'idDetalleDevolucion': $("input[name='_chkIt']:checked").val(),	
            		'idDespachador': $("input[name='_chkDes']:checked").val(),
            		'dia': $("#dia").val(),
            		'hora': $("#hora").val()
            	},
            success: function( data ) {
                console.log( "ok... resultado " +  data );
                if( data.status == 1 ){
                	console.log('registrado correctamente');
                	agregarMensajeAjax( 'Registro correcto.' );
                }
            },
            error: function(jqXHR, textStatus, errorThrown){
                 console.log('Error:  status='+textStatus+'; error='+errorThrown);
            }
        });
	    
	});

	$(document).undelegate('#btnCancela', 'click').delegate('#btnCancela', 'click', function(){
		if(confirm('¿Segudo de cancelar?')){
	   		console.info('redirecciona al home');
	   		window.location.assign("${pageContext.request.contextPath}/inicio.htm");
		}
	});
	
 
	 $( function(){
		$('#datetimepicker1').datetimepicker();
	    $('#hora').datetimepicker({
	    	 format: 'HH:mm:ss'
        });
   	   	$("#displayTagDiv").displayTagAjax();
   });

	   function cargarListaTrabajadores(){
		   console.log('cargarListaTrabajadores()');
		   $("#tablaDinamica2").css('opacity', 0.4);
	   		$("#tablaDinamica2").load('${pageContext.request.contextPath}/producto/listaDespachadores.htm'+ '?randval=' + Math.random() + " #resultado2", 
   				function(){ 
   					$("#tablaDinamica2").css('opacity', 1); 
   					//$("#rolling").toggle(); 
   				}
	   		);
		}
</script>
