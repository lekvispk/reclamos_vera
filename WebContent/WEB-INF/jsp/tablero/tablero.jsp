<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"  %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<jsp:include page="../include/cabecera.jsp"/>

<link href="${pageContext.request.contextPath}/css/plugins/morris.css" rel="stylesheet">

        <!-- Page Content -->
        <div id="page-wrapper">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-12">
                                               
                       <jsp:include page="../include/error.jsp"/>
        
				   		<form:form cssClass="form-horizontal" name="frmDocumentos" id="frmDocumentos" action="#" method="post" modelAttribute="reclamo">
				   											
								 <!-- Flot Charts -->
				                <div class="row">
				                    <div class="col-lg-12">
				                        <h2 class="page-header">Tablero</h2>
				                    </div>
				                </div>
				               
				                <!-- /.row -->
				
				                <div class="row">
				                	<div class="col-lg-6">
				                        <div class="panel panel-yellow">
				                            <div class="panel-heading">
				                                <h3 class="panel-title"><i class="fa fa-long-arrow-right"></i> Reclamos atendidos por mes este año</h3>
				                            </div>
				                            <div class="panel-body">
				                                <div id="reclamos-atendidos-por-mes"></div>
				                            </div>
				                        </div>
				                    </div>			                    
				                    <div class="col-lg-6">
				                        <div class="panel panel-primary">
				                            <div class="panel-heading">
				                                <h3 class="panel-title"><i class="fa fa-long-arrow-right"></i> Reclamos con mas frecuencia de sucesos este año</h3>
				                            </div>
				                            <div class="panel-body">
				                                <div id="reclamos-mas-repetidos"></div>				                                
				                            </div>
				                        </div>
				                    </div>
				                    
				                </div>
				                <!-- /.row -->
				
				
				                <div class="row">
				                    <div class="col-lg-6">
				                        <div class="panel panel-red">
				                            <div class="panel-heading">
				                                <h3 class="panel-title"><i class="fa fa-long-arrow-right"></i> Nro. de Reclamos desatendidos al mes en este año</h3>
				                            </div>
				                            <div class="panel-body">
				                                <div id="desatendidos-por-mes"></div>				                                
				                            </div>
				                        </div>
				                    </div>
				                    <div class="col-lg-6">
				                    	<!-- numero de reclamos segun el estado en que quedaron en el mes actual -->
				                        <div class="panel panel-yellow">
				                            <div class="panel-heading">
				                                <h3 class="panel-title"><i class="fa fa-long-arrow-right"></i> Nro. de reclamos efectuados (mes actual)</h3>
				                            </div>
				                            <div class="panel-body">
				                                <div id="reclamos-por-estado"></div>
				                            </div>
				                        </div>
				                    </div>
				                </div>
				                <!-- /.row -->
				                
								<!-- Button -->
								
								<div class="form-group">
								  <label class="col-md-4 control-label" for="btnBuscar"></label>
								  <div class="col-md-4">
								    <button id="btnVolver" name="btnVolver" class="btn btn-success">Regresar</button>
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

	 <jsp:include page="../include/pie.jsp"/>
	 
	 <!-- Morris Charts JavaScript -->
    <script src="${pageContext.request.contextPath}/js/plugins/morris/raphael.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/plugins/morris/morris.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/plugins/morris/morris-data.js"></script>

    
<script>
$(document).undelegate('#btnVolver', 'click').delegate('#btnVolver', 'click', function(){
	window.location.assign("${pageContext.request.contextPath}/tablero/tablero.htm");
	return false;
});
</script>