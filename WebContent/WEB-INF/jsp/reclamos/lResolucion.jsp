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
							<legend>Resolucion de Reclamos</legend>
							
							<!-- Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="txtCodigo">C&oacute;digo</label>  
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
														
							<!-- Select Basic -->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="cmbPrioridad">Prioridad</label>
							  <div class="col-md-4">
							    <select id="cmbPrioridad" name="cmbPrioridad" class="form-control">
							      <option value="1">Alerta</option>
							      <option value="2">Oposici�n</option>
							      <option value="3">Usuarios</option>
							    </select>
							  </div>
							</div>
							
							<!-- Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="txtVencimiento">Vencimiento</label>  
							  <div class="col-md-5">
							  	<input id="txtVencimiento" name="txtVencimiento" type="text" placeholder="Remitente" class="form-control input-md">
							  </div>
							</div>
							
							<!-- Button -->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="btnBuscar"></label>
							  <div class="col-md-4">
							    <button id="btnBuscar" name="btnBuscar" class="btn btn-success">Buscar</button>
							    <a href="resolucion.htm" class="btn btn-success">Nuevo </a>
							  </div>
							</div>
							
							</fieldset>
				          </form>
				   		
                         	<div id="tablaDinamica">
						 	<div id="resultado">
					   		<div id="displayTagDiv">
						    	<display:table  name="requestScope.lReclamos" requestURI="lResolucion.htm" class="displaytag" pagesize="10"
						            defaultsort="1" defaultorder="descending" sort="list" export="true" id="row" excludedParams="ajax" >
						            <display:column title="Codigo" property="codigo" sortable="true" headerClass="sortable" />
						            <display:column title="Fec. Vencimiento" property="fecha" format="{0,date,dd/MM/yyyy}" sortable="true" headerClass="sortable" />						         
						           <%--
						           
						           
						           <display:column title="RUC" property="ruc" sortable="true" headerClass="sortable" />
						           <display:column title="Representante" property="representante" sortable="true" headerClass="sortable" />
						           <display:column title="Estado" sortable="true" headerClass="sortable">
						            	<c:if test="${row.estado == 1}">Abierto</c:if>
						            	<c:if test="${row.estado == 2}">En Proceso</c:if>
						            	<c:if test="${row.estado == 3}">Atendido</c:if>
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
		if(confirm('�Est� seguro de eliminar al cliente?')){
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