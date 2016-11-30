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
					
					<form:form cssClass="form-horizontal" name="frmDocumentos" id="frmDocumentos" action="#" method="post" modelAttribute="fideliza">
							
					<fieldset>
					
					<!-- Form Name -->
					<legend>Fidelizar Clientes - Promociones</legend>
					
					<form:hidden path="idFideliza"/>
					
					<!-- Text input-->
					<div class="form-group">
					  <label class="col-md-4 control-label">RUC</label>  
					  <div class="col-md-4">
					  	<label class="form-control input-md">${factura.cliente.rucCliente}</label>
					  </div>
					</div>
					
					<!-- Text input-->
					<div class="form-group">
					  <label class="col-md-4 control-label">Raz&oacute;n Social</label>  
					  <div class="col-md-4">
					  	<label class="form-control input-md">${factura.cliente.nomCliente}</label>
					  </div>
					</div>
					
					<!-- Text input-->
					<div class="form-group">
					  <label class="col-md-4 control-label">Factura</label>  
					  <div class="col-md-4">
					  	<label class="form-control input-md">${factura.numero}</label>
					  </div>
					</div>
					
					<!-- Text input-->
					<div class="form-group">
					  <label class="col-md-4 control-label">Producto</label>  
					  <div class="col-md-4">
					  	<label class="form-control input-md">${producto.detallefactura.producto.skuProducto}</label>
					  </div>
					</div>
					
					<!-- Text input-->
					<div class="form-group">
					  <label class="col-md-4 control-label">Descripcion</label>  
					  <div class="col-md-4">
					  	<label class="form-control input-md">${producto.detallefactura.producto.descripcion}</label>
					  </div>
					</div>
					
					<!-- Text input-->
					<div class="form-group">
					  <label class="col-md-4 control-label">Cantidad</label>  
					  <div class="col-md-4">
					  	<label class="form-control input-md">${producto.detallefactura.cantidad}</label>
					  </div>
					</div>
					
					<!-- Text input-->
					<div class="form-group">
					  <label class="col-md-4 control-label">Consumo</label>  
					  <div class="col-md-4">
					  	<label class="form-control input-md">${factura.monto}</label>
					  </div>
					</div>
					
					<!-- Select Basic -->
					<div class="form-group">
					  <label class="col-md-4 control-label" for="cmbPrioridad">Promocion</label>
					  <div class="col-md-4">
					  	<form:select path="promocion.idPromocion" cssClass="form-control input-md" 
					  				 items="${lPromociones}" 
					  				 itemLabel="descripcion"
					  				 itemValue="idPromocion">
					  	</form:select>
					  </div>
					</div>
					
					<!-- Button -->
					<div class="form-group">
					  <label class="col-md-4 control-label" for="btnBuscar"></label>
					  <div class="col-md-4">
					    <input type="button" id="btnRegresar" name="btnRegresar" class="btn btn-success" value="Regresar"/>
					    <input type="button" id="btnAceptar" name="btnAceptar" class="btn btn-success" value="Aceptar"/>
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
	
	$(document).undelegate('#btnAceptar', 'click').delegate('#btnAceptar', 'click', function(){
		document.forms[0].action='promociones.htm';
		document.forms[0].submit();
	});

	$(document).undelegate('#btnRegresar', 'click').delegate('#btnRegresar', 'click', function(){
		window.location="${pageContext.request.contextPath}/fidelizar/lPromociones.htm";
	});
	
</script>

