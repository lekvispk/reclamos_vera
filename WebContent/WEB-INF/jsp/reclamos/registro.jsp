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
							
							<!-- Factura Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="idFactura">Factura</label>  
							  <div class="col-md-5">
							  	<select name="factura.idFactura" id="idFactura" class="form-control">
							  		<option value="1">0000222</option>
							  	</select>
							  </div>
							</div>
							
							<!-- Prioridad Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="prioridad">Prioridad</label>  
							  <div class="col-md-5">
							  	
							  	<form:select path="prioridad" class="form-control">
							  		<form:option value="1">Alta</form:option>
							  		<form:option value="2">Normal</form:option>
							  		<form:option value="3">Baja</form:option>
							  	</form:select>
							  	
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
							  <label class="col-md-4 control-label" for="mensaje">Mensaje</label>  
							  <div class="col-md-5">
							  	<form:textarea path="mensaje" id="mensaje" cssClass="form-control input-md" cols="40" rows="5"/>
							  </div>
							</div>
							
							<!-- Button -->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="btnBuscar"></label>
							  <div class="col-md-4">
							    <input class="btn btn-success" type="submit" value="Registrar">
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
	function nuevo(){
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
	}
	
	$( function(){
		 
   	   $("#displayTagDiv").displayTagAjax();
   	   
   	   
	   	$( "#tagsCliente" ).autocomplete({
			width: 300,
	        max: 10,
	        delay: 100,
	        autoFocus: true,
	        cacheLength: 1,
	        scroll: true,
	        highlight: false,
			source: function(request, response) {
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
	                    /* console.log(textStatus);*/
	                }
	            });
	        },
			minLength: 2,
			select: function( event, ui ) {
				  $( "#idCliente" ).val( ui.item.id );
				  //cargar combo de facturas;
				  cargarFacturas( ui.item.id );
			}
		});
	
   	
	});

	function cargarFacturas( idCliente ){
		
		$.ajax({
            url: "${pageContext.request.contextPath}/rest/"+idCliente+"/facturas/",
            dataType: "json",
            data: {  },
            success: function( data, textStatus, jqXHR) {
                console.log( " facturas " +  data);
                var items = data;
                $('#idFactura').empty();
                $.each( data, function( key, value ) {
                	console.log( key +" facturas " +  value.numero);
                	 $('#idFactura').append($("<option></option>")
                                .attr("value",key)
                                .text(value.numero)); 
               	});

            },
            error: function(jqXHR, textStatus, errorThrown){
                 console.log(textStatus);
            }
        });
	}
</script>