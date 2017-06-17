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
				                        <div class="panel panel-primary">
				                            <div class="panel-heading">
				                                <h3 class="panel-title"><i class="fa fa-long-arrow-right"></i> Reclamos con mas frecuencia de sucesos</h3>
				                            </div>
				                            <div class="panel-body">
				                                <div id="morris-bar-chart"></div>				                                
				                            </div>
				                        </div>
				                    </div>
				                    <div class="col-lg-6">
				                        <div class="panel panel-yellow">
				                            <div class="panel-heading">
				                                <h3 class="panel-title"><i class="fa fa-long-arrow-right"></i> Multiple Axes Line Graph Example with Tooltips and Raw Data</h3>
				                            </div>
				                            <div class="panel-body">
				                                <div class="flot-chart">
				                                    <div class="flot-chart-content" id="flot-multiple-axes-chart"></div>
				                                </div>
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
				                                <div id="morris-line-chart"></div>				                                
				                            </div>
				                        </div>
				                    </div>
				                    <div class="col-lg-6">
				                        <div class="panel panel-green">
				                            <div class="panel-heading">
				                                <h3 class="panel-title"><i class="fa fa-long-arrow-right"></i> Nro. de reclamos efectuados</h3>
				                            </div>
				                            <div class="panel-body">
				                                <div class="flot-chart">
				                                    <div class="flot-chart-content" id="flot-pie-chart"></div>
				                                </div>
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

    <!-- Flot Charts JavaScript -->
    <!--[if lte IE 8]><script src="js/excanvas.min.js"></script><![endif]-->
    <script src="${pageContext.request.contextPath}/js/plugins/flot/jquery.flot.js"></script>
    <script src="${pageContext.request.contextPath}/js/plugins/flot/jquery.flot.tooltip.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/plugins/flot/jquery.flot.resize.js"></script>
    <script src="${pageContext.request.contextPath}/js/plugins/flot/jquery.flot.pie.js"></script>
    <script src="${pageContext.request.contextPath}/js/plugins/flot/jquery.flot.time.js"></script>
    <script src="${pageContext.request.contextPath}/js/plugins/flot/flot-data.js"></script>
    
<script>
$(document).undelegate('#btnVolver', 'click').delegate('#btnVolver', 'click', function(){
	window.location.assign("${pageContext.request.contextPath}/tablero/tablero.htm");
	return false;
});
</script>