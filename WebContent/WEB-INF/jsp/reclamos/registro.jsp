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
        
        				<form:form cssClass="form-horizontal" name="frmDocumentos" action="" method="post" modelAttribute="reclamo">
        					<form:hidden path="idReclamo"/>
        					<input type="hidden" value="1" name="estado"/>  
        				
							<fieldset>
							<legend>Registrar Reclamo</legend>
							
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
							
							<input type="hidden" value="0" name="idCliente" id="idCliente2"/>
							<!-- Razon Social Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="asunto">Raz&oacute;n Social</label>  
							  <div class="col-md-5">
							  	<input type="text" name="razonSocial" id="razonSocial" class="form-control input-md" readonly="readonly"/>
							  </div>
							</div>
							<!-- Representante Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="asunto">Representante</label>  
							  <div class="col-md-5">
							  	<input type="text" name="representante" id="representante" class="form-control input-md" readonly="readonly"/>
							  </div>
							</div>
							<!-- Email Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="asunto">Email</label>  
							  <div class="col-md-5">
							  	<input type="text" name="email" id="email" class="form-control input-md" readonly="readonly"/>
							  </div>
							</div>
							<!-- Button -->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="btnBuscar"></label>
							  <div class="col-md-4">
							    <input type="button" id="btnBuscarRuc" class="btn btn-success" value="Buscar">
							  </div>
							</div>
							
							</fieldset>
							<fieldset>
							<legend>Informaci&oacute;n General</legend>
							
							<!-- Factura Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="idFactura">Factura</label>  
							  <div class="col-md-5">
							  	<select name="factura.idFactura" id="idFactura" class="form-control">
							  		<option value="0">-seleccionar-</option>
							  	</select>
							  </div>
							</div>
							
							<div class="form-group">
							  <label class="col-md-4 control-label" for="idFactura">Items</label>  
							  <div class="col-md-5">
							  		<div class="box-body table-responsive no-padding">
					                  <table id="lContratolocal" class="table table-bordered table-striped">
					                    <thead>
					                      <tr>
					                        <th>Codigo</th>
					                        <th>Descripcion</th>
					                        <th>Cantida</th>
					                        <th>Importe</th>
					                        <th></th>
					                      </tr>
					                    </thead>
					                    <tbody>
					                      
					                    </tbody>
					                  </table>
					                </div>
							  </div>
							</div>
							
							
							<!-- Prioridad Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="prioridad">Prioridad</label>  
							  <div class="col-md-5">
							  	
							  	<form:select path="prioridad" cssClass="form-control">
							  		<form:option value="1">Alta</form:option>
							  		<form:option value="2">Normal</form:option>
							  		<form:option value="3">Baja</form:option>
							  	</form:select>
							  	
							  </div>
							</div>
							
							</fieldset>
							<fieldset>
							<legend>Detalles del mensaje</legend>
							
							<!-- Asunto Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="asunto">Asunto</label>  
							  <div class="col-md-5">
							  	<form:input path="asunto" id="asunto" cssClass="form-control input-md"/>
							  </div>
							</div>
							
							<!-- mensaje Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="mensaje">Mensaje</label>  
							  <div class="col-md-5">
							  	<form:textarea path="mensaje" id="mensaje" cssClass="form-control input-md" cols="40" rows="5"/>
							  </div>
							</div>
							
							<!-- Button -->
							<div class="form-group">
							  <label class="col-md-4 control-label"></label>
							  <div class="col-md-4">
							    <input id="btnRegistrar" type="button" value="Registrar" class="btn btn-success" >
							  </div>
							  <div class="col-md-4">
							    <input id="btnLimpiar" class="btn btn-success" type="reset" value="Limpiar">
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
	
	$(document).undelegate('#btnRegistrar', 'click').delegate('#btnRegistrar', 'click', function(){
		var fields = $("input[name='rb_item']").serializeArray(); 
	    if (fields.length == 0) {
			alert('Debe seleccionar un producto');
			return;
		}  
		document.forms[0].action="registro.htm";
		document.forms[0].submit();
		
	});
	
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
	}
	
	$( function(){
		 
   	   $("#displayTagDiv").displayTagAjax();

   	   /* voy a dejar el autocompletar, ahora el cargar combo facturas sera desde un boton y tambien pitara los datos del cliente */
   	   
	   	$( "#tagsCliente" ).autocomplete({
			width: 300,
	        max: 10,
	        delay: 100,
	        autoFocus: true,
	        cacheLength: 1,
	        scroll: true,
	        highlight: false,
			source: function(request, response) {
				
				$( "#idCliente" ).val( -1 );
				$( "#idCliente2" ).val( -1 );
				$( "#razonSocial" ).val( '' );
	            $( "#representante" ).val( '' );
	            $( "#email" ).val( '' );
				
	            $.ajax({
	                url: "${pageContext.request.contextPath}/reclamos/lClienteAuto.htm",
	                dataType: "json",
	                data: request,
	                success: function( data, textStatus, jqXHR) {
	                    /*console.log( data);*/
	                    var items = data;
	                    response(items);
	                },
	                error: function(jqXHR, textStatus, errorThrown){
	                    console.log('no encontrado nada en la lista');
	                	$( "#idCliente" ).val( -1 );
	   				  	$( "#idCliente2" ).val( -1 );
	   					$( "#razonSocial" ).val( '' );
	 	            	$( "#representante" ).val( '' );
	 	            	$( "#email" ).val( '' );	 	            
	                }
	            });
	        },
			minLength: 2,
			select: function( event, ui ) {
				  $( "#idCliente" ).val( ui.item.id );
				  $( "#idCliente2" ).val( ui.item.id );
				  
				  //cargar combo de facturas;
				  //cargarFacturas( ui.item.id );
			}
		});
	
   	
	});

	$(document).undelegate('#btnLimpiar', 'click').delegate('#btnLimpiar', 'click', function(){
		console.debug('Limpiar');
		$('#lContratolocal tbody').remove();
	});	
	
	$(document).undelegate('#idFactura', 'change').delegate('#idFactura', 'change', function(){
		console.debug('factura seleccionada ');
		var idFactura =  $(this).val();
		var idCliente = $('#idCliente').attr('value');
		console.debug(' idFactura = ' + idFactura + ' idCliente=' + idCliente );
		//listar items de la factura
		
		cargarItemsFactura( idCliente, idFactura );
			
		
	});	

	$(document).undelegate('#btnBuscarRuc', 'click').delegate('#btnBuscarRuc', 'click', function(){
		var idCliente = $('#idCliente').attr('value');
			
		if( idCliente == -1 ){
			alert('No existe RUC, se debe registrar al cliente');
			return;
		}
		
		cargarFacturas( idCliente );
		cargarCliente( idCliente );
	});

	function cargarCliente( idCliente ){
		
		$.ajax({
            url: "${pageContext.request.contextPath}/rest/clientes/"+idCliente,
            dataType: "json",
            data: {  },
            success: function( data, textStatus, jqXHR) {
                console.log( " cliente " +  data);
                var items = data;
				
                $( "#razonSocial" ).val( data.nomCliente );
                $( "#representante" ).val( data.representante );
                $( "#email" ).val( data.persona.email );
                
            },
            error: function(jqXHR, textStatus, errorThrown){
                 console.log(textStatus);
            }
        });
	}
	
	function cargarFacturas( idCliente ){
		
		$.ajax({
            url: "${pageContext.request.contextPath}/rest/clientes/"+idCliente+"/facturas/",
            dataType: "json",
            data: {  },
            success: function( data, textStatus, jqXHR) {
                console.log( " facturas " +  data);
                var items = data;
                $('#idFactura').empty();

                $('#idFactura').append($("<option></option>")
		                        .attr("value",-1)
		                        .text( '-Seleccionar-' ));
                
                $.each( data, function( key, value ) {
                	//console.log( key +" facturas " +  value.numero);
                	 $('#idFactura').append($("<option></option>")
                                .attr("value",value.idFactura)
                                .text(value.numero)); 
               	});

            },
            error: function(jqXHR, textStatus, errorThrown){
                 console.log(textStatus);
            }
        });
	}

	function cargarItemsFactura( idCliente, idFactura ){
		
		$.ajax({
            url: "${pageContext.request.contextPath}/rest/clientes/"+idCliente+"/facturas/"+ idFactura +"/items",
            dataType: "json",
            data: {  },
            success: function( data, textStatus, jqXHR) {
                console.log( " items " +  data);
                var items = data;
                
                $('#lContratolocal tbody').remove();
                $.each(data, function(index, element) {
                	console.log( " sku " +  element.producto.skuProducto);
                	var row = '<tr id="contratolocal_' + element.idDetalleFactura + '">'+
                             '<td>' + element.producto.skuProducto +'</td>'+
                             '<td>' + element.producto.descripcion +'</td>'+
                             '<td>' + element.cantidad + '</td>'+
                             '<td>' + element.precio + '</td>'+
                             '<td> <input type="radio" name="rb_item" id="rb_item_' + element.idDetalleFactura + '" value="' + element.idDetalleFactura + '"> </td>'; 
                             row += '</tr>';
                  $('#lContratolocal').append(row);
                 });
             
            },
            error: function(jqXHR, textStatus, errorThrown){
                 console.log(textStatus);
            }
        });
     }
</script>