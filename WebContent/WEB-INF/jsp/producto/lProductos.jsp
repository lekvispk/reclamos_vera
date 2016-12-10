<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"  %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div id="tablaDinamica2">
 	<div id="resultado2">
  		<div id="displayTagDiv">
  			
  			<display:table  name="requestScope.lProductosDevoluciones" requestURI="lPromociones.htm" class="displaytag" 
            defaultsort="1" defaultorder="descending" sort="list" export="false" id="rowProductos2" excludedParams="ajax">
	            
	            <display:column title="Descripcion" property="producto.descripcion" sortable="true" headerClass="sortable" />
	            <display:column title="Precio Unitario" property="producto.precio" sortable="true" headerClass="sortable" />
	            <display:column title="Cantidad" value="1" sortable="true" headerClass="sortable" />
	            <display:column title="Importe" sortable="false" headerClass="sortable">
	            	${ 1 * rowProductos2.producto.precio }
	            </display:column>
	            
	    	</display:table>
		
		</div>
  	</div>
</div>	