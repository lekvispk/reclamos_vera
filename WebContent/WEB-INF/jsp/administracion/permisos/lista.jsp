<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"  %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<jsp:include page="../../include/cabecera.jsp"/>

        <!-- Page Content -->
        <div id="page-wrapper">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-12">
                                               
                       <jsp:include page="../../include/error.jsp"/>
        
				   		<form:form cssClass="form-horizontal" name="frmDocumentos" id="frmDocumentos" action="#" method="post" modelAttribute="reclamo">
				   			<fieldset>
							
							<!-- Form Name -->
							<legend>Lista de Permisos</legend>
						
							</fieldset>
				          </form:form>
				   		
                         	<div id="tablaDinamica">
						 	<div id="resultado">
					   		<div id="displayTagDiv">
					   		
					   		
						    	<display:table  name="requestScope.lPermisos" requestURI="lista.htm" class="displaytag" pagesize="20"
						            defaultsort="1" defaultorder="descending" sort="list" export="true" id="row" excludedParams="ajax">
						           
						           <display:column title="Id" property="idPermiso" sortable="true" headerClass="sortable" />
						           <display:column title="Permiso" property="permiso" sortable="true" headerClass="sortable" />
						          
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
		
		
	 <jsp:include page="../../include/pie.jsp"/>
	 
	 
<script>
	
	 $( function(){
   	   $("#displayTagDiv").displayTagAjax();
   });
   
</script>