<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"  %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<jsp:include page="../include/cabecera.jsp"/>

<script>
	function evaluar(){
		
		var fields = $("input[name='_chk']").serializeArray(); 
	    if (fields.length == 1) { 
	    	window.location='evaluar.htm?idReclamo='+ $("input[name='_chk']").val();
	    }else {
	    	alert('Seleccione un Reclamo');
	    }
		
	}
</script>

        <!-- Page Content -->
        <div id="page-wrapper">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-12">
                                               
                       <jsp:include page="../include/error.jsp"/>
        
				   		<form:form cssClass="form-horizontal" name="frmDocumentos" id="frmDocumentos" action="#" method="post" modelAttribute="reclamo">
				   			<fieldset>
							
							<!-- Form Name -->
							<legend>Lista de Usuarios</legend>
							<!-- Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="txtCodigo">C&oacute;digo</label>  
							  <div class="col-md-5">
							  	<form:input path="idReclamo" id="idReclamo" size="10" cssClass="form-control input-md" />
							  </div>
							</div>
								
							<!-- Select Basic -->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="cmbPrioridad">Prioridad</label>
							  <div class="col-md-4">
							    
							    <form:select path="prioridad" cssClass="form-control" >
							    	<form:option value="">-Seleccionar-</form:option>
							  		<form:option value="1">Alta</form:option>
							  		<form:option value="2">Normal</form:option>
							  		<form:option value="3">Baja</form:option>
							  	</form:select>
							  	
							  </div>
							</div>
							
							<!-- Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="txtVencimiento">Vencimiento</label>  
							  <div class="col-md-4">
							  	<fmt:formatDate value="${reclamo.fecReclamo}" pattern="dd/MM/yyyy" var="f_fecReclamo"/>
                    			<input type="text" class="form-control input-md" name="fecReclamo" id="fecReclamo" placeholder="dd/MM/yyyy" size="10" value="${f_fecReclamo}"/>
							  </div>
							</div>
							
							<!-- Button -->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="btnBuscar"></label>
							  <div class="col-md-4">
							    <button id="btnBuscar" name="btnBuscar" class="btn btn-success">Buscar</button>
							    <a href="javascript:evaluar();" class="btn btn-success">Registrar</a>
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
						           <display:column title="Email" property="email" sortable="true" headerClass="sortable" />
						           <display:column>
						           	<a href="#">M</a>
						           	<a href="#">M</a>
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