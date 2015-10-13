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
							  	<form:input path="nomCliente" id="nomCliente" size="30" maxlength="500" cssClass="form-control input-md" />
							  </div>
							</div>
							
							<!-- direccionl Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="direCliente">Direccion</label>  
							  <div class="col-md-5">
							  	<form:input path="direCliente" id="direCliente" size="40" maxlength="500" cssClass="form-control input-md" />
							  </div>
							</div>
							
							<!--email Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="email">Email</label>  
							  <div class="col-md-5">
							  	<form:input path="email" id="email" size="30" maxlength="100" cssClass="form-control input-md" />
							  </div>
							</div>
							
							<!-- Button -->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="btnBuscar"></label>
							  <div class="col-md-4">
							    <input class="btn btn-success" type="submit" value="Registrar">
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