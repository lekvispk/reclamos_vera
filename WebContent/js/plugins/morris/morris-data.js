// Morris.js - Cuadros estadisticos

$(function() {

	var atendidosPorMes;	
	$.getJSON('http://localhost:8080/reclamos/rest/reportes/reclamos/mes/atentido', function(results) {
		console.log( results );
		//reclamos atendidos por mes anio actual
		atendidosPorMes =  Morris.Bar({
	        element: 'reclamos-atendidos-por-mes',
	        data: results,
	        xkey: 'label',
	        ykeys: ['value'],
	        labels: ['Reclamos'],
	        barRatio: 0.4,
	        xLabelAngle: 35,
	        hideHover: 'auto',
	        resize: true
	    });
	    
    });
	
	var masepetidos;	
	$.getJSON('http://localhost:8080/reclamos/rest/reportes/reclamos/masrepetidos', function(results) {
		console.log( results );
		// Bar Chart
		masepetidos = Morris.Bar({
	        element: 'reclamos-mas-repetidos',
	        data: results,
	        xkey: 'label',
	        ykeys: ['value'],
	        labels: ['Cantidad'],
	        barRatio: 0.4,
	        xLabelAngle: 35,
	        hideHover: 'auto',
	        resize: true
	    });
	    
    });
	
	var reportesNoAtendidosPorMes;	
	$.getJSON('http://localhost:8080/reclamos/rest/reportes/reclamos/mes/noatendidos', function(results) {
		console.log( results );		
		// Line Chart
		reportesNoAtendidosPorMes = Morris.Line({
	        // ID of the element in which to draw the chart.
	        element: 'desatendidos-por-mes',
	        // Chart data records -- each entry in this array corresponds to a point on
	        // the chart.
	        data: results,
	        // The name of the data record attribute that contains x-visitss.
	        xkey: 'label',
	        // A list of names of data record attributes that contain y-visitss.
	        ykeys: ['value'],
	        // Labels for the ykeys -- will be displayed when you hover over the
	        // chart.
	        labels: ['Reclamos'],
	        // Disables line smoothing
	        xLabels:'month',
	        smooth: false,
	        resize: true
	    });
	    
    });
   
	var reclamosPorEstadoEnMes;	
	$.getJSON('http://localhost:8080/reclamos/rest/reportes/reclamos/mes/porestado', function(results) {
		console.log( results );		
		// Donut Chart
		reclamosPorEstadoEnMes = Morris.Donut({
	        element: 'reclamos-por-estado',
	        data: results,
	        resize: true
	    });
	    
    });
    

});
