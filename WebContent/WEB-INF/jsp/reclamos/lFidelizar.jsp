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
							
							<!-- Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="fecFactura">Desde</label>  
							  <div class="col-md-4">
							  	<fmt:formatDate value="${factura.emision}" pattern="dd/MM/yyyy" var="f_fecFactura"/>
                    			<input type="text" class="form-control input-md" name="fecFactura" id="fecFactura" placeholder="dd/MM/yyyy" size="10" value="${f_fecFactura}"/>
							  </div>
							</div>
							
							<!-- Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="fecFacturaFin">Hasta</label>  
							  <div class="col-md-4">
							  	<fmt:formatDate value="${factura.emisionFin}" pattern="dd/MM/yyyy" var="f_fecFacturaFin"/>
                    			<input type="text" class="form-control input-md" name="fecFacturaFin" id="fecFacturaFin" placeholder="dd/MM/yyyy" size="10" value="${f_fecFacturaFin}"/>
							  </div>
							</div>
							
								<!-- Select Basic -->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="cmbPrioridad">Consumo</label>
							  <div class="col-md-4">
							    
							    <form:select path="monto" cssClass="form-control" >
							    	<form:option value="">-Seleccionar-</form:option>
							  		<form:option value="7000.0">Mayor a 7000</form:option>
							  		<form:option value="5000.0">Mayor a 5000</form:option>
							  	</form:select>
							  	
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
					   		
					   		<jsp:scriptlet>
							    <![CDATA[
							       
							        org.displaytag.decorator.CheckboxTableDecorator decorator = new org.displaytag.decorator.CheckboxTableDecorator();
							        decorator.setId("idReclamo");
							        decorator.setFieldName("_chk");
							        pageContext.setAttribute("checkboxDecorator", decorator);
							     ]]>
							  </jsp:scriptlet> 
							  
						    	<display:table  name="requestScope.lFacturas" requestURI="lFidelizar.htm" class="displaytag" pagesize="20"
						            defaultsort="1" defaultorder="descending" sort="list" export="true" id="row" excludedParams="ajax _chk"
						            decorator="checkboxDecorator" >
						            
						            <display:column title="" >
						            	<a href="lCompensar.htm?idFactura=${row.idFactura}">ver</a>
						            </display:column>
						            <display:column title="RUC" property="cliente.rucCliente" sortable="true" headerClass="sortable" />
						            <display:column title="Razon Social" property="cliente.nomCliente" sortable="true" headerClass="sortable" />
						            <display:column title="Fec. Inicio" property="emision" format="{0,date,dd/MM/yyyy}" sortable="true" headerClass="sortable" />
						            <display:column title="Fec. Fin" property="emision" format="{0,date,dd/MM/yyyy}" sortable="true" headerClass="sortable" />
						            						         
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
	
	 $( function(){
   	   $("#displayTagDiv").displayTagAjax();
   });
   
</script>