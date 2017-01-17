<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"  %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<jsp:include page="../../include/cabecera.jsp"/>

<script>

	function registrar(){
		window.location='registro.htm';
	}
	
</script>

        <!-- Page Content -->
        <div id="page-wrapper">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-12">
                                               
                       <jsp:include page="../../include/error.jsp"/>
        
				   		<form:form cssClass="form-horizontal" name="frmDocumentos" id="frmDocumentos" action="#" method="post" modelAttribute="reclamo">
				   			<fieldset>
							
							<!-- Form Name -->
							<legend>Lista de Perfiles</legend>
							
							<!-- Button -->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="btnBuscar"></label>
							  <div class="col-md-4">
							    <button id="btnBuscar" name="btnBuscar" class="btn btn-success">Buscar</button>
							    <a href="javascript:registrar();" class="btn btn-success">Registrar</a>
							  </div>
							</div>
							
							</fieldset>
				          </form:form>
				   		
				   		<div class="form-group">
						  <label class="col-md-2"></label>
						  <div class="col-md-8">
								
		                 	<div id="tablaDinamica">
						 	<div id="resultado">
					   		<div id="displayTagDiv">
					   		
					   			<display:table  name="requestScope.lPerfiles" requestURI="lista.htm" class="displaytag" pagesize="20"
						            defaultsort="1" defaultorder="descending" sort="list" export="true" id="row" excludedParams="ajax">
						           
						           <display:column title="Id" property="idPerfil" sortable="true" headerClass="sortable" />
						           <display:column title="Perfil" property="perfil" sortable="true" headerClass="sortable" />
						           <display:column title="Estado" sortable="true" headerClass="sortable">
						           		<c:if test="${row.estado == 0}">Inactivo</c:if>
						           		<c:if test="${row.estado == 1}">Activo</c:if>
						           </display:column>
						           <display:column title="Acciones" media="html" style="text-align:center">
						           		<a href="#" id="btnEdit" data-id="${row.idPerfil}" style="border: 0px;" title="Modificar"><img src="${pageContext.request.contextPath}/img/edit.png" width="18" height="18" border="0"></a>
						           		<a href="#" id="btnDelete" data-id="${row.idPerfil}" style="border: 0px;" title="Eliminar"><img src="${pageContext.request.contextPath}/img/error.png" width="18" height="18" border="0"></a>
						           </display:column>
						           
						    	</display:table>
						    	
							</div>
						  	</div>
							</div>			
                                
		                            
						  </div>
						  <label class="col-md-2"></label>
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
		
		
	 <jsp:include page="../../include/pie.jsp"/>
	 
	 
<script>

	$( function(){
	   $("#displayTagDiv").displayTagAjax();
	});

	$(document).undelegate('#btnEdit', 'click').delegate('#btnEdit', 'click', function(){
		var id = $(this).data('id');
		window.location.assign("${pageContext.request.contextPath}/perfil/modificar.htm?id="+id);
	});

	$(document).undelegate('#btnDelete', 'click').delegate('#btnDelete', 'click', function(){
		if(confirm('¿Seguro de eliminar el perfil?')){
			var id = $(this).data('id');
			$("#tablaDinamica").css('opacity', 0.4);
	   		$("#tablaDinamica").load('${pageContext.request.contextPath}/perfil/eliminar.htm?id='+ id +'&randval=' + Math.random() + " #resultado", 
   				function(){ 
   					$("#tablaDinamica").css('opacity', 1); 
   					$("#rolling").toggle(); 
   				}
	   		);
		}
	});
	
	function nuevo(){
		document.forms[0].action='ncliente.htm';
		document.forms[0].action.submit();
	}
	
</script>