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
							  	<input type="text" name="ruc" id="ruc" placeholder="CAP001" class="form-control input-md">
							  </div>
							  <div class="col-md-4">
							    <input type="button" id="btnBuscar" name="btnBuscar" class="btn btn-success" value="Buscar"/>
							  </div>
							</div>
							
							<div class="form-group">
							  <div class="col-md-12">
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
								            defaultsort="1" defaultorder="descending" sort="list" export="false" id="row" excludedParams="ajax _chk" decorator="checkboxDecorator" >
								            
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
							</div>
						
							<!-- Button -->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="btnBuscar"></label>
							  <div class="col-md-4">
							    <input type="button" id="btnAceptar" name="btnAceptar"  class="btn btn-success" value="Agregar"/>
							  </div>
							</div>
							
							<div class="form-group">
							  <div class="col-md-12">
							  	<div id="tablaDinamica">
								 	<div id="resultado">
							   		<div id="displayTagDiv">
							   		
							   		<jsp:scriptlet>
									    <![CDATA[
									       
									        org.displaytag.decorator.CheckboxTableDecorator decorator2 = new org.displaytag.decorator.CheckboxTableDecorator();
									        decorator2.setId("idFactura");
									        decorator2.setFieldName("_chk");
									        pageContext.setAttribute("checkboxDecorator", decorator2);
									     ]]>
									  </jsp:scriptlet> 
									  
								    	<display:table  name="requestScope.lFacturas" requestURI="lPromociones.htm" class="displaytag" pagesize="3"
								            defaultsort="1" defaultorder="descending" sort="list" export="false" id="row" excludedParams="ajax _chk" decorator="checkboxDecorator" >
								            
								            <display:column property="checkbox" />
								             <display:column title="Codigo" property="cliente.nomCliente" sortable="true" headerClass="sortable" />
								            <display:column title="Nombre" property="cliente.nomCliente" sortable="true" headerClass="sortable" />
								            
								    	</display:table>
									
									</div>
								  	</div>
								</div>	
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
		document.forms[0].action='seleccionar.htm';
		document.forms[0].submit();
	});
	
	$(document).undelegate('#btnCancela', 'click').delegate('#btnCancela', 'click', function(){
		if(confirm('¿Segudo de cancelar?')){
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