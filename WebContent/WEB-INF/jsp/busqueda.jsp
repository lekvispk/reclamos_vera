<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"  %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<jsp:include page="include/cabecera.jsp"/>

<style>

	.row{
		    margin-top:40px;
		    padding: 0 10px;
		}
		.clickable{
		    cursor: pointer;   
		}

		.panel-heading div {
			margin-top: -18px;
			font-size: 15px;
		}
		.panel-heading div span{
			margin-left:5px;
		}
		.panel-body{
			display: none;
		}

</style>
<script>
	
	 $( function(){
		 
			$( "#tagsEntidad" ).autocomplete({
    			width: 300,
    	        max: 10,
    	        delay: 100,
    	        autoFocus: true,
    	        cacheLength: 1,
    	        scroll: true,
    	        highlight: false,
    			source: function(request, response) {
    	            $.ajax({
    	                url: "${pageContext.request.contextPath}/lEntidadAuto.htm",
    	                dataType: "json",
    	                data: request,
    	                success: function( data, textStatus, jqXHR) {
    	                    /*console.log( data);*/
    	                    var items = data;
    	                    response(items);
    	                },
    	                error: function(jqXHR, textStatus, errorThrown){
    	                    /* console.log(textStatus);*/
    	                }
    	            });
    	        },
    			minLength: 2,
    			select: function( event, ui ) {
    				  $( "#idEntidad" ).val( ui.item.id );
    			}
    		});
    		
    	 	$( "#fechaIngreso" ).datepicker({
     	        showOn: "button",
     	        buttonImage: "${pageContext.request.contextPath}/img/cal.gif",
     	        buttonImageOnly: false,
     	        dateFormat: 'dd/mm/yy'
     	      });
    	 	
    	 	$( "#fechaIngreso2" ).datepicker({
     	        showOn: "button",
     	        buttonImage: "${pageContext.request.contextPath}/img/cal.gif",
     	        buttonImageOnly: false,
     	        dateFormat: 'dd/mm/yy'
     	      });
    	 	 
    	 	$('#datetimepicker').datetimepicker();
    	 	
   	  		 $("#displayTagDiv").displayTagAjax();
   	 
	     // attach table FILTER PLUGIN to inputs
	 	
	 	$('.container').on('click', '.panel-heading span.filter', function(e){
	 		var $this = $(this), 
	 				$panel = $this.parents('.panel');
	 		
	 		$panel.find('.panel-body').slideToggle();
	 		if($this.css('display') != 'none') {
	 			$panel.find('.panel-body input').focus();
	 		}
	 	});
	 	$('[data-toggle="tooltip"]').tooltip();
	 	
	 	
	 	$('#btnlimpiar').click(function(){
	 		$('#idEntidad').val("-1");
	 		$('#tagsEntidad').val("");
	 		$('#tipoTramite').val("0");
	 		$('#estado').val("0");
	 		$('#numeroIngreso').val("");
	 		$('#numeroDocumento').val(""); 
	 		$('#fechaIngreso').val("");
	 		$('#fechaIngreso2').val("");
	 	 }); 
	 });
	 
	function ver( id ){
   		 $("#somediv").html( '<img src="${pageContext.request.contextPath}/img/indicator.gif"/>' );
   		 $("#somediv").load( 'ver.htm?idTramite=' + id + '&randval=' + Math.random() ).dialog({
   			resizable: false,
   			id: 'miModal',
   			title:'Detalle de Tramite',
  	            height:400,
  	            width:600,
  	            modal: true
   		 });     		
   	}
	 
</script>
      <!-- Main hero unit for a primary marketing message or call to action -->
      <div class="hero-unit">
        
          
         <c:if test="${not empty msgError}"><div class="alert alert-danger"> <strong><c:out value="${msgError}"/></strong> </div></c:if>
		<c:if test="${not empty mensaje}"><div class="alert alert-success">  <strong><c:out value="${mensaje}"/></strong> </div></c:if>
	
		<form:form name="frmDocumentos" id="frmDocumentos" action="buscar.htm" method="post" modelAttribute="tramite" cssClass="form-horizontal">  
		
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title">B&uacute;squeda de documentos</h3>
				<div class="pull-right">
					<span class="clickable filter" data-toggle="tooltip" title="Mostrar Filtros" data-container="body">
						<i class="glyphicon glyphicon-filter"></i>
					</span>
				</div>
			</div>
			<div class="panel-body">					
			<!-- <fieldset>
			<legend>B&uacute;squeda de documentos</legend>
			 -->
			<!-- Text input-->
			<div class="form-group">
			  <label class="col-md-4 control-label" for="tagsEntidad">Remitente</label>  
			  <div class="col-md-5">
			  	 	<input type="hidden" name="entidad.idEntidad" id="idEntidad" value="-1"/>
                    <div class="demo" style="float: left;">
						<div class="ui-widget">
							<input id="tagsEntidad" type="text" size="40"  placeholder="remitente"  class="form-control input-md"/>
						</div>
					</div>
			  </div>
			</div>
			
			
			<!-- Select Basic -->
			<div class="form-group">
			  <label class="col-md-4 control-label" for="tipoTramite">Tipo de Trámite</label>
			  <div class="col-md-2">
			  	<form:select path="tipoTramite" id="tipoTramite" cssClass="form-control">
			   		<form:option value="0" label="-Todas-"/>
			      	<form:options items="${lTipoTram}" itemLabel="valorTexto" itemValue="valorNumerico"/>
			    </form:select>
			 </div>
			  
			  <label class="col-md-2 control-label" for="tipoTramite">Estado</label>
			  <div class="col-md-2">
			   <form:select path="estado" id="estado" cssClass="form-control">
			   		<form:option value="0" label="-Todos-"/>
			      	<form:options items="${lTipoMov}" itemLabel="valorTexto" itemValue="valorNumerico"/>
			    </form:select>
			  </div>
			  
			</div>
				
			<!-- Text input-->
			<div class="form-group">
			  <label class="col-md-4 control-label" for="numeroIngreso">Número de Recepción</label>  
			  <div class="col-md-2">
			  	  <input id="numeroIngreso" name="numeroIngreso" type="text" placeholder="nro. recepción" class="form-control input-md">
				</div>
			</div>
			
			<!-- Text input-->
			<div class="form-group">
			  <label class="col-md-4 control-label" for="numeroDocumento">Número de Documento</label>  
			  <div class="col-md-4">
			  	   <input id="numeroDocumento" name="numeroDocumento" type="text" placeholder="nro. documento" class="form-control input-md">
			  </div>
			</div>
			
		<!-- <div class="form-group">
				<label class="col-md-4 control-label" for="fechaDocumento">Fecha</label>
				<div class="col-md-2">

				
				<div class="input-append date form_datetime">
				    <input size="16"  class="form-control input-md"  type="text" value="" readonly>
				    <span class="add-on"><i class="icon-th"></i></span>
				</div>
				
				<script type="text/javascript">
				//https://github.com/smalot/bootstrap-datetimepicker
			    $(".form_datetime").datetimepicker({
			        format: "dd/mm/yyyy",
			        autoclose: true,
			        todayBtn: true
			    });
			</script>


				</div>
			</div>
			 -->	
			<div class="form-group">
				<label class="col-md-4 control-label" for="fechaIngreso">Fecha de recepci&oacute;n</label>
   				<div class="col-xs-6 col-sm-6 col-md-2">
   					<fmt:formatDate value="${tramite.fechaIngreso}" pattern="dd/MM/yyyy" var="f_fechaIngreso"/>
                   	<input type="text" class="form-control input-md" name="fechaIngreso" id="fechaIngreso" placeholder="Desde" value="${f_fechaIngreso}"/>
				</div>
   				<div class="col-xs-6 col-sm-6 col-md-2">
   					<fmt:formatDate value="${tramite.fechaIngreso2}" pattern="dd/MM/yyyy" var="f_fechaIngreso2"/>
                   	<input type="text" class="form-control input-md" name="fechaIngreso2" id="fechaIngreso2" placeholder="Hasta" value="${f_fechaIngreso2}"/>
				</div>
   			</div>
   			
			
			
			<!-- Button -->
			<div class="form-group">
			  <label class="col-md-4 control-label" for="btnBuscar"></label>
			  <div class="col-md-4">
			    <button id="btnBuscar" name="btnBuscar" class="btn btn-success">Buscar</button>
			     <input  id="btnlimpiar" type="button" class="btn btn-success" value="Limpiar" />
			  </div>
			</div>
		</div>
		</div>
		</form:form>
   		
   	<div id="tablaDinamica">
	   <div id="resultado">
   		<div id="displayTagDiv">
	    	<display:table  name="requestScope.ldocumentos" requestURI="${listado}.htm" class="displaytag" pagesize="10"
	            defaultsort="1" defaultorder="descending" sort="list" export="false" id="row" excludedParams="ajax" >
	            <display:column title="Remitente" property="entidad.nombreEntidad" sortable="true" headerClass="sortable" />
	            <display:column title="Fec. Recepcion" property="fechaIngreso" format="{0,date,dd/MM/yyyy}" sortable="true" headerClass="sortable" />
	            <display:column title="Nro. Documento" property="numeroIngreso" sortable="true" headerClass="sortable" />
	            <display:column title="Tipo de Tr&aacute;mite" sortable="true" headerClass="sortable">
	            	<c:if test="${row.tipoTramite == 1}">Alerta</c:if>
	            	<c:if test="${row.tipoTramite == 2}">Oposicion</c:if>
	            	<c:if test="${row.tipoTramite == 3}">Usuarios</c:if>
	            </display:column>
	            <display:column title="Estado" sortable="true" headerClass="sortable">
	            	<c:if test="${row.estado == 1}">Registrado</c:if>
	            	<c:if test="${row.estado == 2}">Recibido</c:if>
	            	<c:if test="${row.estado == 3}">Derivado</c:if>
	            	<c:if test="${row.estado == 4}">Atendido</c:if>
	            	<c:if test="${row.estado == 5}">Archivado</c:if>
	            </display:column>
	            <display:column title="" style="width:120px;">
	            	<div class="pull-right action-buttons">
	            		<a href="javascript:ver(${row.idTramite});"><span class="glyphicon glyphicon-search"></span></a>
                    </div>
				</display:column>
	   		</display:table>
		</div>
	</div>
	</div>			
	
        
      </div>

	<div id='somediv'></div>

    <jsp:include page="include/pie.jsp"/>