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
							<legend>Seleccion cambio de producto</legend>
							
							<form:hidden path="idFactura"/>
							<form:hidden path="cliente.idCliente"/>
							
							<!-- Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="ruc">Nro. Factura</label>  
							  <div class="col-md-4">
							  	<input type="text" name="numero" id="numero" placeholder="F-001" class="form-control input-md">
							  </div>
							  <div class="col-md-4">
							    <input type="button" id="btnBuscar" name="btnBuscar" class="btn btn-success" value="Buscar"/>
							  </div>
							</div>
							
							<div class="form-group">
								<div class="col-md-2"></div>
							  	<div class="col-md-8">
							  	<div id="tablaDinamica">
								 	<div id="resultado">
							   		<div id="displayTagDiv">
							   		
								    	<display:table  name="requestScope.lProductos" requestURI="lPromociones.htm" class="displaytag" 
								            defaultsort="1" defaultorder="descending" sort="list" export="false" id="rowProductos" excludedParams="ajax">
								            <display:column>
								            	<input type="radio" name="_chkIt" id="_chkIt_${rowProductos.idDetalleFactura}" value="${rowProductos.idDetalleFactura}"/>
								            </display:column>
								            <display:column title="Codigo" property="factura.numero" sortable="true" headerClass="sortable" />
								            <display:column title="Descripcion" property="producto.descripcion" sortable="true" headerClass="sortable" />
								            <display:column title="Precio Unitario" property="precio" sortable="true" headerClass="sortable" />
								            <display:column title="Cantidad" property="cantidad" sortable="true" headerClass="sortable" />
								            <display:column title="Importe" sortable="false" headerClass="sortable">
								            	${ rowProductos.cantidad * rowProductos.precio }
								            </display:column>
								            
								    	</display:table>
									
									</div>
								  	</div>
								</div>	
							   	</div>
							   	<div class="col-md-2"></div>
							</div>
						
							<!-- Button -->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="btnBuscar"></label>
							  <div class="col-md-4">
							    <input type="button" id="btnAgregar" name="btnAgregar"  class="btn btn-success" value="Agregar"/>
							  </div>
							</div>
							
							<div class="form-group">
							  <div class="col-md-2"></div>
							  <div class="col-md-8">
							  	<div id="tablaDinamica">
								 	<div id="resultado">
							   		<div id="displayTagDiv">
							   			
							   			<display:table  name="requestScope.lProductos2" requestURI="lPromociones.htm" class="displaytag" 
								            defaultsort="1" defaultorder="descending" sort="list" export="false" id="rowProductos2" excludedParams="ajax">
								            
								            <display:column title="Codigo" property="factura.numero" sortable="true" headerClass="sortable" />
								            <display:column title="Descripcion" property="producto.descripcion" sortable="true" headerClass="sortable" />
								            <display:column title="Precio Unitario" property="precio" sortable="true" headerClass="sortable" />
								            <display:column title="Cantidad" property="cantidad" sortable="true" headerClass="sortable" />
								            <display:column title="Importe" sortable="false" headerClass="sortable">
								            	${ rowProductos2.cantidad * rowProductos2.precio }
								            </display:column>
								            
								    	</display:table>
									
									</div>
								  	</div>
								</div>	
							   </div>
							   <div class="col-md-2"></div>
							</div>
							
							<!-- Button -->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="btnBuscar"></label>
							  <div class="col-md-4">
							    <input type="button" id="btnAutoriza" name="btnAutoriza"  class="btn btn-success" value="Autorizar"/>
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
		document.forms[0].action='seleccionar.htm';
		document.forms[0].submit();
	});

	
	$(document).undelegate('#btnAgregar', 'click').delegate('#btnAgregar', 'click', function(){

		var fields = $("input[name='_chkIt']:checked").serializeArray(); 
	    if (fields.length == 0) { 
	    	alert('Selecione un producto');
	    	return;
	    }

	    $.ajax({
            url: "${pageContext.request.contextPath}/producto/agregar.htm",
            dataType: "json",
            method: "POST",
            data: { 'idDetalleFactura':''+$("input[name='_chkIt']:checked").val() 
                	},
            success: function( data ) {
                console.log( " resultado " +  data );
                if( data.idDetalleDevolucion ){
                	console.log( " id devolucion =  "  +  data.idDetalleDevolucion );
                }
            },
            error: function(jqXHR, textStatus, errorThrown){
                 console.log(textStatus);
            }
        });
	    
	});

	$(document).undelegate('#btnCancela', 'click').delegate('#btnCancela', 'click', function(){
		if(confirm('�Segudo de cancelar?')){
	   		console.info('redirecciona a lista de posponer capacitaciones');
	   		window.location.assign("${pageContext.request.contextPath}/producto/seleccionar.htm");
		}
	});
	
	$(document).undelegate('#btnAutoriza', 'click').delegate('#btnAutoriza', 'click', function(){
		document.forms[0].action='seleccionar.htm';
		document.forms[0].submit();
	});


	 $( function(){
		$('#datetimepicker1').datetimepicker();
		 
   	   	$("#displayTagDiv").displayTagAjax();
   });
   
</script>