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
							<legend>Gestionar Clientes</legend>
							
							<!-- Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="txtRemitente">Remitente</label>  
							  <div class="col-md-5">
							  <input id="txtRemitente" name="txtRemitente" type="text" placeholder="Remitente" class="form-control input-md">
							    
							  </div>
							</div>
							
							<!-- Select Basic -->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="cmbTipo">Tipo de Trámite</label>
							  <div class="col-md-4">
							    <select id="cmbTipo" name="cmbTipo" class="form-control">
							      <option value="1">Alerta</option>
							      <option value="2">Oposición</option>
							      <option value="3">Usuarios</option>
							    </select>
							  </div>
							</div>
							
							<!-- Button -->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="btnBuscar"></label>
							  <div class="col-md-4">
							    <button id="btnBuscar" name="btnBuscar" class="btn btn-success">Buscar</button>
							    <a href="registro.htm" class="btn btn-success">Nuevo </a>
							  </div>
							</div>
							
							</fieldset>
				          </form>
				   		
                         	<div id="tablaDinamica">
						 	<div id="resultado">
					   		<div id="displayTagDiv">
						    	<display:table  name="requestScope.lClientes" requestURI="lista.htm" class="displaytag" pagesize="10"
						            defaultsort="1" defaultorder="descending" sort="list" export="true" id="row" excludedParams="ajax" >
						           
						           <display:column title="Razon Social" property="nomCliente" sortable="true" headerClass="sortable" />
						           <display:column title="RUC" property="rucCliente" sortable="true" headerClass="sortable" />
						           <display:column title="Email" property="persona.email" sortable="true" headerClass="sortable" />
						           <display:column title="Estado" sortable="true" headerClass="sortable">
						            	<c:if test="${row.estado == 1}">Activo</c:if>
						            	<c:if test="${row.estado == 2}">Inactivo</c:if>
						            	<c:if test="${row.estado == 3}">Eliminado</c:if>
						            </display:column>
						           <%--
						            <display:column title="Codigo" property="entidad.nombreEntidad" sortable="true" headerClass="sortable" />
						            <display:column title="Asunto" property="entidad.nombreEntidad" sortable="true" headerClass="sortable" />
						            
						            <display:column title="RUC" property="entidad.nombreEntidad" sortable="true" headerClass="sortable" />
						            <display:column title="Prioridad" property="entidad.nombreEntidad" sortable="true" headerClass="sortable" />
						            <display:column title="Fec. Vencimiento" property="fechaIngreso" format="{0,date,dd/MM/yyyy}" sortable="true" headerClass="sortable" />
						            
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