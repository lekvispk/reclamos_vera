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
        
				   		<form name="frmDocumentos" id="frmDocumentos" action="#" method="post" class="form-horizontal">
				   			<fieldset>
							
							<!-- Form Name -->
							<legend>Mostrar Tablero</legend>
							
							<!-- Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="txtCodigo">RUC</label>  
							  <div class="col-md-5">
							  	<input type="text" name="idReclamo" id="idReclamo" size="10" class="form-control input-md" />
							  </div>
							</div>
							
							<!-- Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="txtRazonSocial">Raz&oacute;n Social</label>  
							  <div class="col-md-5">
							  	<input type="text" name="factura.cliente.nomCliente" id="nomCliente" size="20" class="form-control input-md" />
							  </div>
							</div>
							
							<!-- Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="txtVencimiento">Fecha de Inicio</label>  
							  <div class="col-md-4">
							  	<input type="text" class="form-control input-md" name="fecReclamo" id="fecReclamo" placeholder="dd/MM/yyyy" size="10" value=""/>
							  </div>
							</div>
							
							<!-- Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="txtVencimiento">Fecha de Fin</label>  
							  <div class="col-md-4">
							  	<input type="text" class="form-control input-md" name="fecReclamo" id="fecReclamo" placeholder="dd/MM/yyyy" size="10" value=""/>
							  </div>
							</div>
							
							<!-- Button -->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="btnBuscar"></label>
							  <div class="col-md-4">
							    <button id="btnProcesar" name="btnProcesar" class="btn btn-success">Procesar</button>
							  </div>
							</div>
							
							</fieldset>
				          </form>
				   		
                    </div>
                    <!-- /.col-lg-12 -->
                </div>
                <!-- /.row -->
            </div>
            <!-- /.container-fluid -->
        </div>
        <!-- /#page-wrapper -->

	 <jsp:include page="../include/pie.jsp"/>
	 
<script>

	$(document).undelegate('#btnProcesar', 'click').delegate('#btnProcesar', 'click', function(){
		document.forms[0].action='tablero.htm';
		document.forms[0].submit();
	});

</script>