<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"  %>

<jsp:include page="../include/cabecera.jsp"/>


        <!-- Page Content -->
        <div id="page-wrapper">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-12">
                                               
                       <jsp:include page="../include/error.jsp"/>
        
				   		<form class="form-horizontal" action="#" name="frmDocumentos" id="frmDocumentos" method="post">
							<fieldset>
							
							<!-- Form Name -->
							<legend>Indemnizar Reclamos</legend>
							
							<!-- Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="txtCodigo">N&uacute;mero de Reclamo</label>  
							  <div class="col-md-5">
							  	<input type="text" id="txtCodigo" name="txtCodigo" placeholder="Codigo" class="form-control input-md">
							  </div>
							</div>
							
							<!-- Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="txtRazonSocial">Raz&oacute;n Social</label>  
							  <div class="col-md-5">
							  	<input id="txtRazonSocial" name="txtRazonSocial" type="text" placeholder="Razon Social" class="form-control input-md">
							  </div>
							</div>
														
							
							
							<!-- Button -->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="btnBuscar"></label>
							  <div class="col-md-4">
							    <button id="btnBuscar" name="btnBuscar" class="btn btn-success">Buscar</button>
							    <a href="indemnizar.htm" class="btn btn-success">Proccesar </a>
							  </div>
							</div>
							
							</fieldset>
				          </form>
				   		
                         	<div id="tablaDinamica">
						 	<div id="resultado">
					   		<div id="displayTagDiv">
						    	<jsp:scriptlet>
							    <![CDATA[
							       
							        org.displaytag.decorator.CheckboxTableDecorator decorator = new org.displaytag.decorator.CheckboxTableDecorator();
							        decorator.setId("idReclamo");
							        decorator.setFieldName("_chk");
							        pageContext.setAttribute("checkboxDecorator", decorator);
							     ]]>
							  </jsp:scriptlet> 
							  
						    	<display:table  name="requestScope.lReclamos" requestURI="lIndemnizar.htm" class="displaytag" pagesize="3"
						            defaultsort="1" defaultorder="descending" sort="list" export="true" id="row" excludedParams="ajax _chk"
						            decorator="checkboxDecorator" >
						             <display:column property="checkbox"/>
						            <display:column title="Codigo" property="idReclamo" sortable="true" headerClass="sortable" />
						           <display:column title="Asunto" property="asunto" sortable="true" headerClass="sortable" />
						           <display:column title="Razon Social" property="factura.cliente.nomCliente" sortable="true" headerClass="sortable" />
						           <display:column title="RUC" property="factura.cliente.rucCliente" sortable="true" headerClass="sortable" />
						             <display:column title="Prioridad" sortable="true" headerClass="sortable">
						            	<c:if test="${row.prioridad == 1}">Alta</c:if>
						            	<c:if test="${row.prioridad == 2}">Normal</c:if>
						            	<c:if test="${row.prioridad == 3}">Baja</c:if>
						            </display:column>
						            <display:column title="Fec. Vencimiento" property="fecReclamo" format="{0,date,dd/MM/yyyy}" sortable="true" headerClass="sortable" />						         
						             <display:column title="Estado" sortable="true" headerClass="sortable">
						            	<c:if test="${row.estado == 1}">Abierto</c:if>
						            	<c:if test="${row.estado == 2}">Aceptado</c:if>
						            	<c:if test="${row.estado == 3}">Rechazado</c:if>
						            	<c:if test="${row.estado == 4}">Solucionado</c:if>
						            </display:column>
						           <display:column title="Factura" property="factura.numero" sortable="true" headerClass="sortable" />
						           <%--
						           
						           
						           <display:column title="RUC" property="ruc" sortable="true" headerClass="sortable" />
						           <display:column title="Representante" property="representante" sortable="true" headerClass="sortable" />
						           <display:column title="Estado" sortable="true" headerClass="sortable">
						            	<c:if test="${row.estado == 1}">Activo</c:if>
						            	<c:if test="${row.estado == 2}">Inactivo</c:if>
						            	<c:if test="${row.estado == 3}">Eliminado</c:if>
						            </display:column>
						            <display:column title="Codigo" property="entidad.nombreEntidad" sortable="true" headerClass="sortable" />
						            <display:column title="Asunto" property="entidad.nombreEntidad" sortable="true" headerClass="sortable" />
						            
						            <display:column title="RUC" property="entidad.nombreEntidad" sortable="true" headerClass="sortable" />
						            <display:column title="Prioridad" property="entidad.nombreEntidad" sortable="true" headerClass="sortable" />

						            
						            <display:column title="Factura" property="entidad.nombreEntidad" sortable="true" headerClass="sortable" />
						           --%>
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
   });
   
</script>