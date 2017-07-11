<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"  %>

<jsp:include page="../include/cabecera.jsp"/>

        <!-- Page Content -->
        <div id="page-wrapper">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-12">
                                               
                       <jsp:include page="../include/error.jsp"/>
        
						<form:form cssClass="form-horizontal" name="frmDocumentos" 
							action="registro.htm" method="post" modelAttribute="usuario">
        
							<fieldset>
							
							<form:hidden path="idUsuario"/>
							<form:hidden path="estado"/>
        					
							<!-- Form Name -->
							<legend>Registrar Usuario</legend>
							
							<form:hidden path="persona.idPersona"/>
							
							<!-- Persona Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="persona.nombres">Nombres</label>  
							  <div class="col-md-5"> <form:input path="persona.nombres" maxlength="150" cssClass="form-control input-md"/> </div>
							</div>
							<div class="form-group">
							  <label class="col-md-4 control-label" for="persona.apePaterno">Apellido Paterno</label>  
							  <div class="col-md-5"> <form:input path="persona.apePaterno" maxlength="150" cssClass="form-control input-md"/> </div>
							</div>
							<div class="form-group">
							  <label class="col-md-4 control-label" for="persona.apeMaterno">Apellido Materno</label>  
							  <div class="col-md-5"> <form:input path="persona.apeMaterno" maxlength="150" cssClass="form-control input-md"/> </div>
							</div>
							<div class="form-group">
							  <label class="col-md-4 control-label" for="persona.numeroDocumento">Nro. de documento</label>  
							  <div class="col-md-5"> <form:input path="persona.numeroDocumento" maxlength="8" cssClass="form-control input-md"/> </div>
							</div>
							
							<c:if test="${usuario.idUsuario > 0}">
							<!-- Username Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="rucCliente">Nombre de usuario</label>  
							  <div class="col-md-5">
							  	<form:input path="username" id="username" readonly="true" size="20" cssClass="form-control input-md" />
							  </div>
							</div>
							
							<form:hidden path="password"/>
							<input type="hidden" name="confirmar" id="confirmar" value="${usuario.password}"/>
							</c:if>
							
							<c:if test="${ empty usuario.idUsuario }">
							<!-- Username Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="rucCliente">Nombre de usuario</label>  
							  <div class="col-md-5">
							  	<form:input path="username" id="username" size="20" maxlength="150" cssClass="form-control input-md" />
							  </div>
							</div>
							<!-- clave Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="nomCliente">Contrase&ntilde;a</label>  
							  <div class="col-md-5">
							  	<form:password path="password" id="password" size="30" maxlength="20" cssClass="form-control input-md" />
							  </div>
							</div>
							<!-- confirmar claveText input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="nomCliente">Confirmar Contrase&ntilde;a</label>  
							  <div class="col-md-5">
							  	<input type="password" name="confirmar" id="confirmar" placeholder="" maxlength="20" class="form-control input-md" >
							  </div>
							</div>
							</c:if>
							
							<!--email Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="email">Email</label>  
							  <div class="col-md-5">
							  	<form:input path="email" id="email" size="30" maxlength="100" cssClass="form-control input-md" />
							  </div>
							</div>
							
							<!-- confirmar claveText input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="perfil">Perfil</label>  
							  <div class="col-md-5">
							  	<form:select path="perfil.idPerfil" cssClass="form-control">
							  		<form:options items="${ lPerfiles }" itemValue="idPerfil" itemLabel="perfil"/>
							  	</form:select>
							  </div>
							</div>
							
							<!-- Button -->
							<div class="form-group">
							  <label class="col-md-4 control-label"></label>
							  <div class="col-md-4">
							    <input id="btnCancelar" class="btn btn-success" type="button" value="Cancelar">
							    <input id="btnRegistrar" class="btn btn-success" type="button" value="Registrar">
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

	$(document).undelegate('#btnRegistrar', 'click').delegate('#btnRegistrar', 'click', function(){

		//validar claves iguales
		if( $("#password").val() != $("#confirmar").val() ){
			alert('la contrase&ntilde;a no coincide');
			return;
		}
		
		document.forms[0].action="registro.htm";
		document.forms[0].submit();
		//return false;
	});

	$(document).undelegate('#btnCancelar', 'click').delegate('#btnCancelar', 'click', function(){
		window.location.assign("${pageContext.request.contextPath}/usuario/lista.htm");
	});
	
	 $( function(){
   	   $("#displayTagDiv").displayTagAjax();
   });
   
</script>