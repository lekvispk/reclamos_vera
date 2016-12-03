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
		
		var fields = $("input[name='_chk']:checked").serializeArray(); 
	    if (fields.length >= 1) {
	    	window.location.assign("${pageContext.request.contextPath}/capacitacion/posponer.htm?id="+$("input[name='_chk']:checked").val());
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
        
				   		<form:form cssClass="form-horizontal" name="frmDocumentos" id="frmDocumentos" action="#" method="post" modelAttribute="capacitacion">
				   		
							<fieldset>
							
							<!-- Form Name -->
							<legend>Posponer Capacitaci&oacute;n</legend>
							
							<!-- Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="idCapacitacion">C&oacute;digo de Capacitaci&oacute;n</label>  
							  <div class="col-md-4">
							  	<form:input path="idCapacitacion" cssClass="form-control input-md"/>
							  </div>
							  <div class="col-md-4">
							    <input type="button" id="btnBuscar" name="btnBuscar" onclick="javascript:buscar();" class="btn btn-success" value="Buscar"/>
							  </div>
							</div>
							
							<!-- Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="razonSocial">Raz&oacute;n Social</label>  
							  <div class="col-md-4">
							  	<form:input path="factura.cliente.nomCliente" cssClass="form-control input-md"/>
							  </div>
							</div>
							
							<!-- Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="txtVencimiento">Fecha de Capacitaci&oacute;n</label>  
							  <div class="col-md-4">
							  
							  	<div class='input-group date' id='datetimepicker1'>
				                    <fmt:formatDate value="${capacitacion.fechaCapacitacion}" pattern="dd/MM/yyyy" var="f_fechaCapacitacion"/>
                    				<input type="text" class="form-control input-md" name="fechaCapacitacion" id="fechaCapacitacion" placeholder="dd/MM/yyyy"  size="10" value="${f_fechaCapacitacion}"/>
				                    <span class="input-group-addon">
				                        <span class="glyphicon glyphicon-calendar"></span>
				                    </span>
				                </div>
				                
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
							        decorator.setId("idCapacitacion");
							        decorator.setFieldName("_chk");
							        pageContext.setAttribute("checkboxDecorator", decorator);
							     ]]>
							  </jsp:scriptlet> 
							  
						    	<display:table  name="requestScope.lCapacitacion" requestURI="lPromociones.htm" class="displaytag" pagesize="3"
						            defaultsort="1" defaultorder="descending" sort="list" export="true" id="row" excludedParams="ajax _chk" decorator="checkboxDecorator" >
						            
						            <display:column property="checkbox" />
						            <display:column title="Capacitacion" property="idCapacitacion" sortable="true" headerClass="sortable" />
						            <display:column title="Factura" property="factura.numero" sortable="true" headerClass="sortable" />
						            <display:column title="Reclamo" property="reclamo.idReclamo" sortable="true" headerClass="sortable" />
						            <display:column title="Solcuion" property="reclamo.solucion" sortable="true" headerClass="sortable" />
						            
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
	
	   	$('#datetimepicker1').datetimepicker({
		    format: 'DD/MM/YYYY'
		});
		
   });
   
</script>