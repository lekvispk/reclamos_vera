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
        
				   		<form:form cssClass="form-horizontal" name="frmDocumentos" id="frmDocumentos" action="#" method="post" modelAttribute="cliente">
							<fieldset>
							
							<!-- Form Name -->
							<legend>Gestionar Clientes</legend>
							
							<!-- Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="nomCliente">Razón Social</label>  
							  <div class="col-md-5">
							  	<form:input path="nomCliente" id="nomCliente" size="20" cssClass="form-control input-md" />
							  </div>
							</div>
							
							<!-- rucCliente Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="rucCliente">R.U.C.</label>  
							  <div class="col-md-5">
							  	<form:input path="rucCliente" id="rucCliente" size="20" cssClass="form-control input-md" />
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
				          </form:form>
				   		
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
						           <display:column title="Acciones" sortable="false">
						           		<a href="modificar.htm?idCliente=${row.idCliente}" title="Editar Cliente"><span class="glyphicon glyphicon-pencil"></span></a>
						         		<a href="javascript:eliminar(${row.idCliente});" title="Eliminar Cliente" class="trash"><span class="glyphicon glyphicon-trash"></span></a>
						            </display:column>
						           
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

	$(document).undelegate('#btnBuscar', 'click').delegate('#btnBuscar', 'click', function(){
		document.forms[0].action="lista.htm";
		document.forms[0].submit();
		//return false;
	});
	
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