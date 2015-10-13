<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"  %>

<jsp:include page="../include/cabecera.jsp"/>

<script>

	function evaluar(){
		document.forms[0].submit();		
	}
	
</script>

        <!-- Page Content -->
        <div id="page-wrapper">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-12">
                                               
                       <jsp:include page="../include/error.jsp"/>
        
				   		<form:form cssClass="form-horizontal" name="frmDocumentos" action="solucionar.htm" method="post" modelAttribute="reclamo">
							<fieldset>
							<legend>Evaluar Reclamo</legend>
							
							<form:hidden path="idReclamo"/>
							
							Informaci&oacute;n General:<hr>
							<!-- Prioridad-->
							<div class="form-group">
							  <label class="col-md-4 control-label" >Prioridad</label>  
							  <div class="col-md-5">
							  	<c:if test="${reclamo.prioridad == 1}">Alta</c:if>
				            	<c:if test="${reclamo.prioridad == 2}">Normal</c:if>
				            	<c:if test="${reclamo.prioridad == 3}">Baja</c:if>
							  </div>
							</div>
							
							<!-- Fecha de reclamo-->
							<div class="form-group">
							  <label class="col-md-4 control-label" >Fecha de Reclamo:</label>  
							  <div class="col-md-5">
							  	${reclamo.fecReclamo}
							  </div>
							</div>
							
							Detalles del mensaje:<hr>
							
							<!-- asunto -->
							<div class="form-group">
							  <label class="col-md-4 control-label" >Asunto:</label>  
							  <div class="col-md-5">
							  	${reclamo.asunto}
							  </div>
							</div>
							
							<!-- mensaje -->
							<div class="form-group">
							 <label class="col-md-4 control-label" for="mensaje">Mensaje:</label>  
							  <div class="col-md-5">
							  	${reclamo.mensaje}
							  </div>
							</div>
							
							<div class="form-group">
							 <label class="col-md-4 control-label" for="solucion">Soluci&oacute;n:</label>  
							  <div class="col-md-5">
							  	<input type="text" id="solucion" name="solucion"  placeholder="Solucion" class="form-control input-md">
							  </div>
							</div>
							
							<!-- Button -->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="btnBuscar"></label>
							  <div class="col-md-4">
							    <input class="btn btn-success" type="button" onclick="javascript:history.back();" value="Regresar">
							    <input class="btn btn-success" type="button" onclick="javascript:evaluar();" value="Aceptar">
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
	 
	 