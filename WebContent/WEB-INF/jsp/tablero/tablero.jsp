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
        
				   		<form:form cssClass="form-horizontal" name="frmDocumentos" id="frmDocumentos" action="#" method="post" modelAttribute="reclamo">
				   			<fieldset>
							
							<!-- Form Name -->
							<legend>Tablero</legend>
							
							
							
							<!-- Button -->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="btnBuscar"></label>
							  <div class="col-md-4">
							    <button id="btnVolver" name="btnVolver" class="btn btn-success">Regresar</button>
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

	 <jsp:include page="../include/pie.jsp"/>
	 
<script>
$(document).undelegate('#btnVolver', 'click').delegate('#btnVolver', 'click', function(){
	window.location.assign("${pageContext.request.contextPath}/tablero/tablero.htm");
	return false;
});
</script>