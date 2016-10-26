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
				   		
                         	<div id="tablaDinamica">
						 	<div id="resultado">
					   		<div id="displayTagDiv">
					   		
					   			<display:table  name="requestScope.lPerfiles" requestURI="lista.htm" class="displaytag" pagesize="20"
						            defaultsort="1" defaultorder="descending" sort="list" export="true" id="row" excludedParams="ajax">
						           
						           <display:column title="Id" property="idPerfil" sortable="true" headerClass="sortable" />
						           <display:column title="Perfil" property="perfil" sortable="true" headerClass="sortable" />
						           <display:column title="Estado" property="estado" sortable="true" headerClass="sortable" />
						           <display:column>
						           	<a href="#">M</a>
						           	<a href="#">X</a>
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
		
		
	 <jsp:include page="../../include/pie.jsp"/>
	 
	 
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