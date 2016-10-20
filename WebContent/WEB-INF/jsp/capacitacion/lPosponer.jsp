<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"  %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<jsp:include page="../include/cabecera.jsp"/>

<script>
	
	function buscar(){
		document.forms[0].action='lPosponer.htm';
		document.forms[0].submit();
	}

	function aceptar(){
		
		var fields = $("input[name='_chk']").serializeArray(); 
	    if (fields.length >= 1) {
	    	window.location.assign("${pageContext.request.contextPath}/capacitacion/posponer.htm?id=1");
	    }else {
	    	alert('Seleccione una capacitacion');
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
							<legend>Posponer Capacitaci&oacute;n</legend>
							
							<form:hidden path="idFactura"/>
							<form:hidden path="cliente.idCliente"/>
							
							<!-- Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="codCapacitacion">C&oacute;digo de Capacitaci&oacute;n</label>  
							  <div class="col-md-4">
							  	<input type="text" name="codCapacitacion" id="codCapacitacion" placeholder="CAP001" class="form-control input-md">
							  </div>
							  <div class="col-md-4">
							    <input type="button" id="btnBuscar" name="btnBuscar" onclick="javascript:buscar();" class="btn btn-success" value="Buscar"/>
							  </div>
							</div>
							
							<!-- Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="razonSocial">Raz&oacute;n Social</label>  
							  <div class="col-md-4">
							  	<input type="text" name="razonSocial" id="razonSocial" placeholder="Razon Social" class="form-control input-md">
							  </div>
							</div>
							
							<!-- Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="txtVencimiento">Fecha de Capacitaci&oacute;n</label>  
							  <div class="col-md-4">
							  	<input id="txtVencimiento" name="txtVencimiento" type="text" placeholder="dd/mm/yyyy" class="form-control input-md">
							  </div>
							</div>
							
							<!-- Button -->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="btnBuscar"></label>
							  <div class="col-md-4">
							    <input type="button" id="btnAceptar" name="btnAceptar" onclick="javascript:aceptar();" class="btn btn-success" value="Aceptar"/>
							  </div>
							</div>
							
							</fieldset>
				         </form:form>
				   		
                         	<div id="tablaDinamica">
						 	<div id="resultado">
					   		<div id="displayTagDiv">
					   		
					   		<jsp:scriptlet>
							    <![CDATA[
							       
							        org.displaytag.decorator.CheckboxTableDecorator decorator = new org.displaytag.decorator.CheckboxTableDecorator();
							        decorator.setId("idFactura");
							        decorator.setFieldName("_chk");
							        pageContext.setAttribute("checkboxDecorator", decorator);
							     ]]>
							  </jsp:scriptlet> 
							  
						    	<display:table  name="requestScope.lFacturas" requestURI="lPromociones.htm" class="displaytag" pagesize="3"
						            defaultsort="1" defaultorder="descending" sort="list" export="true" id="row" excludedParams="ajax _chk" decorator="checkboxDecorator" >
						            
						            <display:column property="checkbox" />
						            <display:column title="Capacitacion" property="cliente.rucCliente" sortable="true" headerClass="sortable" />
						            <display:column title="Factura" property="cliente.rucCliente" sortable="true" headerClass="sortable" />
						            <display:column title="Reclamo" property="cliente.nomCliente" sortable="true" headerClass="sortable" />
						            <display:column title="Solcuion" property="cliente.nomCliente" sortable="true" headerClass="sortable" />
						            
						    	</display:table>
							
							</div>
						  	</div>
							</div>			
                        
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
   });
   
</script>