<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"  %>

<jsp:include page="../../include/cabecera.jsp"/>


        <!-- Page Content -->
        <div id="page-wrapper">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-12">
                                               
                       <jsp:include page="../../include/error.jsp"/>
        
        				<form:form cssClass="form-horizontal" name="frmDocumentos" action="registro.htm" method="post" modelAttribute="perfil">
        				
        					<input type="hidden" value="1" name="estado"/>  
        				
							<fieldset>
							
							<!-- Form Name -->
							<legend>Registrar Perfil</legend>
							
							<!-- Nombre de perfil Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="rucCliente">Perfil</label>  
							  <div class="col-md-5">
							  
							   	<c:if test="${perfil.idPerfil > 0}">
			                   		<form:hidden path="idPerfil" id="idPerfil"/>
			                   	</c:if>
			                   	<c:if test="${empty perfil.idPerfil || perfil.idPerfil <= 0}">
			                   		<input type="hidden" name="perfil.idPerfil" id="idPerfil" value="-1"/>
			                   	</c:if>
			                   	<form:input path="perfil" id="tagsCliente" size="40" cssClass="form-control input-md" />
								
							  </div>
							</div>
							
							<div class="form-group">
							  <label class="col-md-4 control-label" for="rucCliente">Lista de Permisos</label>  
							  <div class="col-md-5">
							   	<form:checkboxes items="${lPermisos}" path="listaPermisos" itemLabel="permiso" itemValue="idPermiso" delimiter="<br>"/>
							  </div>
							</div>
							
							<!-- Button -->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="btnBuscar"></label>
							  <div class="col-md-4">
							    <input class="btn btn-success" type="submit" value="Registrar">
							    <input id="btnCancelar" class="btn btn-success" type="button" value="Cancelar">
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
		
		
	 <jsp:include page="../../include/pie.jsp"/>
	 
	 
<script>

	function nuevo(){
		document.forms[0].action='ncliente.htm';
		document.forms[0].action.submit();
	}
	
	$(document).undelegate('#btnCancelar', 'click').delegate('#btnCancelar', 'click', function(){
	
   		window.location.assign("${pageContext.request.contextPath}/perfil/lista.htm");
	
	});
</script>