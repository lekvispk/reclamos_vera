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
        
				   		<form:form cssClass="form-horizontal" name="frmDocumentos" id="frmDocumentos" 
				   				action="#" method="post" modelAttribute="usuario">
				   			<fieldset>
							
							<!-- Form Name -->
							<legend>Lista de Usuarios</legend>
							
							
							<!-- Button -->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="btnBuscar"></label>
							  <div class="col-md-4">
							    <a id="btnRegistrar" name="btnRegistrar" class="btn btn-success">Nuevo</a>
							  </div>
							</div>
							
							</fieldset>
				          </form:form>
				   		
                         	<div id="tablaDinamica">
						 	<div id="resultado">
					   		<div id="displayTagDiv">
					   		
					   		
						    	<display:table  name="requestScope.lUsuarios" requestURI="lista.htm" class="displaytag" pagesize="20"
						            defaultsort="1" defaultorder="descending" sort="list" export="true" id="row" excludedParams="ajax">
						           
						           <display:column title="Usuario" property="username" sortable="true" headerClass="sortable" />
						           <display:column title="Nombres" property="persona.nombres" sortable="true" headerClass="sortable" />
						           <display:column title="Email" property="email" sortable="true" headerClass="sortable" />
						           <display:column title="Perfil" property="perfil.perfil" sortable="true" headerClass="sortable" />
						           <display:column>
						           		<a href="modificar.htm?idUsuario=${row.idUsuario}" title="Editar Usuario"><span class="glyphicon glyphicon-pencil"></span></a>
						         		<a href="javascript:eliminar(${row.idUsuario});" title="Eliminar Usuario" class="trash"><span class="glyphicon glyphicon-trash"></span></a>
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
	});

	$(document).undelegate('#btnRegistrar', 'click').delegate('#btnRegistrar', 'click', function(){
		window.location='${pageContext.request.contextPath}/usuario/registro.htm';
	});
	
	function evaluar(){
		
		var fields = $("input[name='_chk']").serializeArray(); 
	    if (fields.length == 1) { 
	    	window.location='evaluar.htm?idReclamo='+ $("input[name='_chk']").val();
	    }else {
	    	alert('Seleccione un Reclamo');
	    }
		
	}
	
	function nuevo(){
		document.forms[0].action='ncliente.htm';
		document.forms[0].action.submit();
	}
	
	function eliminar(id){
		if(confirm('¿Está seguro de eliminar al usuario?')){
			$("#tablaDinamica").css('opacity', 0.4);
	   		$("#tablaDinamica").load('eliminar.htm?idUsuario='+ id +'&randval=' + Math.random() + " #resultado", 
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