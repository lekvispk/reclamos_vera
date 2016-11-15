<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"  %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<jsp:include page="../include/cabecera.jsp"/>

<script>
	
	function aceptar(){
	    document.forms[0].action='promociones.htm';
		document.forms[0].submit();
	}
	
	function buscar(){
		document.forms[0].action='lPromociones.htm';
		document.forms[0].submit();
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
							<legend>Fidelizar Clientes - Promociones</legend>
							
							<form:hidden path="idFactura"/>
							<form:hidden path="cliente.idCliente"/>
							<input type="hidden" name="lfactura" id="lfactura">
							<!-- Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="ruc">RUC</label>  
							  <div class="col-md-4">
							  	<form:input path="cliente.rucCliente" size="10" cssClass="form-control input-md" />
							  </div>
							</div>
							
							<!-- Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="razonSocial">Raz&oacute;n Social</label>  
							  <div class="col-md-4">
							  	<form:input path="cliente.nomCliente" cssClass="form-control input-md"  />
							  </div>
							</div>
							
							<!-- Select Basic -->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="cmbPrioridad">Promocion</label>
							  <div class="col-md-4">
							    <select name="promociones" id="promociones" class="form-control input-md">
							    	<option>dar dscto 20%</option>
							    </select>
							  </div>
							</div>
							
							<!-- Button -->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="btnBuscar"></label>
							  <div class="col-md-4">
							    <input type="button" id="btnRegresar" name="btnRegresar" class="btn btn-success" value="Regresar"/>
							    <input type="button" id="btnAceptar" name="btnAceptar" onclick="javascript:aceptar();" class="btn btn-success" value="Aceptar"/>
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
