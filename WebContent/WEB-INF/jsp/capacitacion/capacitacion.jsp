<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"  %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<jsp:include page="../include/cabecera.jsp"/>

<script>
	
	function buscar(){
		document.forms[0].action='lCapacitaciones.htm';
		document.forms[0].submit();
	}

	function aceptar(){
		
		var fields = $("input[name='_chk']").serializeArray(); 
	    if (fields.length >= 1) {
		    document.forms[0].action='preCapacitacion.htm';
			document.forms[0].submit();
	    }else {
	    	alert('Seleccione un cliente');
	    }
	
	}
	
</script>
        <!-- Page Content -->
        <div id="page-wrapper">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-12">
                                               
                       <jsp:include page="../include/error.jsp"/>
        
				   		<form:form cssClass="form-horizontal" name="frmDocumentos" id="frmDocumentos" action="#" method="post" modelAttribute="factura">
				   		
							<fieldset>
							
							<!-- Form Name -->
							<legend>Registrar Capacitaci&oacute;n</legend>
							
							<form:hidden path="idFactura"/>
							<form:hidden path="cliente.idCliente"/>
							
							<!-- Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="ruc">RUC</label>  
							  <div class="col-md-4">
							  	<form:input path="cliente.rucCliente" cssClass="form-control input-md"/>
							  </div>
							</div>
							
							<!-- Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="razonSocial">Raz&oacute;n Social</label>  
							  <div class="col-md-4">
							  	<form:input path="cliente.nomCliente" cssClass="form-control input-md"/>
							  </div>
							</div>
							
							<!-- Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="direccion">Direccion</label>  
							  <div class="col-md-4">
							  	<form:input path="cliente.persona.direccion" cssClass="form-control input-md"/>
							  </div>
							</div>
							
							<!-- Text input-->
							<div class="form-group">
								<label class="col-md-4 control-label"></label>
							  	<div class="col-md-4">
							  		<div id="tablaDinamica">
								 	<div id="resultado">
							   		<div id="displayTagDiv">
							   		  
								    	<display:table  name="requestScope.lItems" requestURI="lPromociones.htm" class="displaytag" pagesize="3"
								            defaultsort="1" defaultorder="descending" sort="list" export="false" id="row" excludedParams="ajax rb_item">
								            <display:column title="Producto" property="producto.skuProducto" sortable="true" headerClass="sortable" />
								            <display:column title="Descripcion" property="producto.descripcion" sortable="true" headerClass="sortable" />
								            <display:column title="Editar">
								            	<input type="radio" name="rb_item" id="rb_item_${row.idDetalleFactura}" value="${row.idDetalleFactura}">
								            </display:column>
								    	</display:table>
									
									</div>
								  	</div>
									</div>	
							  </div>
							</div>
							
							<input type="hidden" name="idCapacitacion" id="idCapacitacion" value="${capacitacion.idCapacitacion}">
							<input type="hidden" name="idCapacitacionItem" id="idCapacitacionItem" value="">
							 
							<!-- Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="direccion">Detalle</label>  
							  <div class="col-md-4">
							  	<textarea name="detalle" id="detalle" placeholder="Razon Social" class="form-control input-md">
							  	</textarea>
							  </div>
							  <div class="col-md-4">
							  	<input type="button" id="btnGuardar" name="btnGuardar" class="btn btn-success" value="Guardar"/>
							  </div>
							</div>
							
							<!-- Button -->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="btnBuscar"></label>
							  <div class="col-md-4">
							    <input type="button" id="btnRegresar" name="btnRegresar" class="btn btn-success" value="Regresar"/>
							    <input type="button" id="btnGrabar" name="btnGrabar" class="btn btn-success" value="Grabar"/>
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

   	   //
	
   	});

	$(document).undelegate('#btnRegresar', 'click').delegate('#btnRegresar', 'click', function(){
   		window.location = '${pageContext.request.contextPath}/capacitacion/lCapacitaciones.htm';
	});
	
   	$(document).undelegate('#btnGrabar', 'click').delegate('#btnGrabar', 'click', function(){
   		window.location = '${pageContext.request.contextPath}/capacitacion/lCapacitaciones.htm';
	});
	
	$(document).undelegate('[id^=rb_item_]', 'click').delegate('[id^=rb_item_]', 'click', function(){
		console.log('obtener detalle de capacitacion ' + $("#idCapacitacion").val() + ' Item ' + $(this).val());

		traerDetalleCapacitacionItem( $("#idCapacitacion").val() , $(this).val() );
		//alert('cargar datos de cada item ' + $(this).val() );
	});

	$(document).undelegate('#btnGuardar', 'click').delegate('#btnGuardar', 'click', function(){

		console.log('id item ' + + $("input[name='rb_item']:checked").val() + ' id capacitacion ' + $("#idCapacitacion").val());

		$.ajax({
            url: "${pageContext.request.contextPath}/capacitacion/grabarDetalle.htm",
            dataType: "json",
            method: "POST",
            data: { 'idCapacitacion': 		''+$("#idCapacitacion").val() ,
            		'idCapacitacionItem': 	''+$("#idCapacitacionItem").val() ,
            		'idDetalleFactura': 	''+$("input[name='rb_item']:checked").val() ,
                	'detalle' : 			''+$("#detalle").val()  
                	},
            success: function( data ) {
                console.log( " resultado " +  data );
                if( data.status == 1 ){
                	$("input[name='rb_item']").attr("checked", false);
                	$( "#idCapacitacionItem" ).val( '' );
            		$( "#detalle" ).val( '' );
                }else{
					alert('No se pudo grabar el detalle');
                }
            },
            error: function(jqXHR, textStatus, errorThrown){
                 console.log(textStatus);
            }
        });
        
	});

	function traerDetalleCapacitacionItem( idCapacitacion, idItem ){
		$.ajax({
            url: "${pageContext.request.contextPath}/rest/capacitacion/"+idCapacitacion +"/detalleItem/"+idItem,
            dataType: "json",
            data: {  },
            success: function( data, textStatus, jqXHR) {
                console.log( " capacitacionItem " +  data);
                var items = data;
				if( data.estado != -1){
					$( "#detalle" ).val( data.detalle );
					$( "#idCapacitacionItem" ).val( data.idCapacitacionItem );
				}else{
					$( "#detalle" ).val( '' );
				}
            },
            error: function(jqXHR, textStatus, errorThrown){
                 console.log(textStatus);
            }
        });
	}
	
</script>