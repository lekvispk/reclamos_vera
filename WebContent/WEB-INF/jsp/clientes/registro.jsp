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
        
						<form:form cssClass="form-horizontal" name="frmDocumentos" action="registro.htm" method="post" modelAttribute="cliente">
        
							<fieldset>
							
							<form:hidden path="idCliente"/>
							<input type="hidden" value="1" name="estado"/>  
        					
							<!-- Form Name -->
							<legend>Registrar Cliente</legend>
							
							<!-- RUC Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="rucCliente">RUC</label>  
							  <div class="col-md-5">
							  	<form:input path="rucCliente" id="rucCliente" size="20" maxlength="11" cssClass="form-control input-md" />
							  </div>
							</div>
							
							<!-- Razon Social Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="nomCliente">Raz&oacute;n Social</label>  
							  <div class="col-md-5">
							  	<form:input path="nomCliente" id="nomCliente" size="30" maxlength="100" cssClass="form-control input-md" />
							  </div>
							</div>
							
							<!-- representante  Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="representante">Representante</label>  
							  <div class="col-md-5">
							  	<form:input path="representante" id="representante" size="30" maxlength="150" cssClass="form-control input-md" />
							  </div>
							</div>
							
							</fieldset>
							
							<fieldset>
							<legend>Datos adicionales</legend>
							
								<form:hidden path="persona.idPersona"/>
								<form:hidden path="persona.estado"/>
								
								<!-- Nombre Text input-->
								<div class="form-group">
								  <label class="col-md-4 control-label" for="persona.nombres">Nombres</label>  
								  <div class="col-md-5">
								  	<form:input path="persona.nombres" id="persona.nombres" size="30" maxlength="150" cssClass="form-control input-md" />
								  </div>
								</div>
								
								<!-- Ape PaternoText input-->
								<div class="form-group">
								  <label class="col-md-4 control-label" for="persona.apePaterno">Apellido paterno</label>  
								  <div class="col-md-5">
								  	<form:input path="persona.apePaterno" id="persona.apePaterno" size="30" maxlength="100" cssClass="form-control input-md" />
								  </div>
								</div>
								
								<!-- Ape Materno Text input-->
								<div class="form-group">
								  <label class="col-md-4 control-label" for="persona.apeMaterno">Apellido Materno</label>  
								  <div class="col-md-5">
								  	<form:input path="persona.apeMaterno" id="persona.apeMaterno" size="30" maxlength="100" cssClass="form-control input-md" />
								  </div>
								</div>
								
								<!-- Ape Materno Text input-->
								<div class="form-group">
								  <label class="col-md-4 control-label" for="persona.direccion">Dirección</label>  
								  <div class="col-md-5">
								  	<form:input path="persona.direccion" id="persona.direccion" size="30" maxlength="150" cssClass="form-control input-md" />
								  </div>
								</div>
								
								<!-- Ape Materno Text input-->
								<div class="form-group">
								  <label class="col-md-4 control-label" for="persona.email">Email</label>  
								  <div class="col-md-5">
								  	<form:input path="persona.email" id="persona.email" size="30" maxlength="100" cssClass="form-control input-md" />
								  </div>
								</div>
								
								<!-- Num Doc Text input-->
								<div class="form-group">
								  <label class="col-md-4 control-label" for="persona.numeroDocumento">Num. Documento</label>  
								  <div class="col-md-5">
								  	<form:input path="persona.numeroDocumento" id="persona.numeroDocumento" size="30" maxlength="8" cssClass="form-control input-md" />
								  </div>
								</div>
								
								<!-- Ape Materno Text input-->
								<div class="form-group">
								  <label class="col-md-4 control-label" for="persona.telefono1">Telefono</label>  
								  <div class="col-md-5">
								  	<form:input path="persona.telefono1" id="persona.telefono1" size="30" maxlength="20" cssClass="form-control input-md" />
								  </div>
								</div>
								
								<!-- Ape Materno Text input-->
								<div class="form-group">
								  <label class="col-md-4 control-label" for="persona.telefono2">Celular</label>  
								  <div class="col-md-5">
								  	<form:input path="persona.telefono2" id="persona.telefono2" size="30" maxlength="20" cssClass="form-control input-md" />
								  </div>
								</div>
								
							</fieldset>
							<!-- Button -->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="btnBuscar"></label>
							  <div class="col-md-4">
							    <input class="btn btn-success" type="submit" value="Registrar">
							  </div>
							</div>
							
							
							
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