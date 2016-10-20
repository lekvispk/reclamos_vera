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
							
							<form:hidden path="idFactura"/>
							<form:hidden path="cliente.idCliente"/>
							
							<!-- Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="nroReclamo">Nro reclamo</label>  
							  <div class="col-md-4">
							  	<input type="text" name="nroReclamo" id="nroReclamo" placeholder="REc0000" class="form-control input-md">
							  </div>
							  <div class="col-md-4">
							    <input type="button" id="btnBuscar" name="btnBuscar" class="btn btn-success" value="Buscar"/>
							  </div>
							</div>
							
							<!-- Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="razonSocial">Nro. Factura</label>  
							  <div class="col-md-4">
							  	<input type="text" name="razonSocial" id="razonSocial" placeholder="0000" class="form-control input-md">
							  </div>
							</div>
							
							<!-- Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="descripcion">Descripcion</label>  
							  <div class="col-md-4">
							  	<input type="text" name="descripcion" id="descripcion" placeholder="0000" class="form-control input-md">
							  </div>
							</div>
											
							<!-- Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="hFinal">Fecha de capacitacion</label>  
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
							  <label class="col-md-4 control-label" for="nroActa">Nro. Acta</label>  
							  <div class="col-md-2">
							  	<input type="text" name="nroActa" id="nroActa" placeholder="0000" class="form-control input-md">
							  </div>
							</div>
							
							<!-- Button -->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="btnBuscar"></label>
							  <div class="col-md-4">
							    <input type="button" id="btnImprimir" name="btnImprimir"  class="btn btn-success" value="Imprimir"/>
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
		document.forms[0].action='autorizar.htm';
		document.forms[0].submit();
	});

	$(document).undelegate('#btnCancela', 'click').delegate('#btnCancela', 'click', function(){
		if(confirm('¿Segudo de cancelar?')){
	   		console.info('redirecciona a lista de posponer capacitaciones');
	   		window.location.assign("${pageContext.request.contextPath}/producto/autorizar.htm");
		}
	});
	
	$(document).undelegate('#btnAutoriza', 'click').delegate('#btnAutoriza', 'click', function(){
		document.forms[0].action='autorizar.htm';
		document.forms[0].submit();
	});

	 $( function(){
		$('#datetimepicker1').datetimepicker();
		 
   	   	$("#displayTagDiv").displayTagAjax();
   });
   
</script>