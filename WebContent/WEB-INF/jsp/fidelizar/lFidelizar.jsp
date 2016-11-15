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
        
				   		<form:form cssClass="form-horizontal" name="frmDocumentos" id="frmDocumentos" action="#" method="post" modelAttribute="factura">
				   		
							<fieldset>
							
							<!-- Form Name -->
							<legend>Fidelizar Clientes</legend>
							
							<!-- Select Basic -->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="cmbPrioridad">Consumo</label>
							  
							    <div class="col-md-4 form-group input-group">
                                     <span class="input-group-addon">S/.</span>
                                     <input type="text" name="monto" id="monto" value="5000" readonly="readonly" class="form-control" />
                                     
                                 </div>
							  
							</div>
							
							<!-- Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="txtRazonSocial">RUC</label>  
							  <div class="col-md-5">
							  	<form:input path="cliente.rucCliente" id="rucCliente" size="20" cssClass="form-control input-md" />
							  </div>
							</div>
							
							<!-- Button -->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="btnBuscar"></label>
							  <div class="col-md-4">
							    <button id="btnBuscar" name="btnBuscar" class="btn btn-success">Buscar</button>
							  </div>
							</div>
							
							</fieldset>
				         </form:form>
				   		
                         	<div id="tablaDinamica">
						 	<div id="resultado">
					   		<div id="displayTagDiv">
					   		  
						    	<display:table  name="requestScope.lClientes" requestURI="lFidelizar.htm" class="displaytag" pagesize="10"
						            defaultsort="1" defaultorder="descending" sort="list" export="true" id="row" excludedParams="ajax _chk"
						            >
						            
						            <display:column title="" >
						            	<a href="lCompensar.htm?idCliente=${row.idCliente}">ver</a>
						            </display:column>
						            <display:column title="Razon Social" property="nomCliente" sortable="true" headerClass="sortable" />
						            <display:column title="RUC" property="rucCliente" sortable="true" headerClass="sortable" />
						            <display:column title="Consumo" property="monto" sortable="true" headerClass="sortable" />
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
		document.forms[0].action="lFidelizar.htm";
		document.forms[0].submit();
		//return false;
	});
	
	$( function(){
   		$("#displayTagDiv").displayTagAjax();
   	});
   
</script>