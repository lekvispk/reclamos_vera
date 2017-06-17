// Morris.js - Cuadros estadisticos

$(function() {

    // Line Chart
    Morris.Line({
        // ID of the element in which to draw the chart.
        element: 'morris-line-chart',
        // Chart data records -- each entry in this array corresponds to a point on
        // the chart.
        data: [ { label: '2017-01', value: 80 }, 
        		{ label: '2017-02', value: 70 }, 
        		{ label: '2017-03', value: 20 }, 
        		{ label: '2017-04', value: 60 }, 
        		{ label: '2017-05', value: 22 }, 
        		{ label: '2017-06', value: 15 }
        ],
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

    //reclamos atendidos por mes anio actual
    Morris.Bar({
        element: 'reclamos-atendidos-por-mes',
        data: [
        		{ label: 'ENE', value: 35 }, 
        		{ label: 'FEB', value: 5 }, 
        		{ label: 'MAR', value: 2 }, 
        		{ label: 'ABR', value: 50 }, 
        		{ label: 'MAY', value: 15 }, 
        		{ label: 'JUN', value: 20 }
        	],
        xkey: 'label',
        ykeys: ['value'],
        labels: ['Reclamos'],
        barRatio: 0.4,
        xLabelAngle: 35,
        hideHover: 'auto',
        resize: true
    });
    
    // Bar Chart
    Morris.Bar({
        element: 'reclamos-mas-repetidos',
        data: [
        		{ label: 'TV AOC 28"', value: 35 }, 
        		{ label: 'iPhone 3G', value: 5 }, 
        		{ label: 'iPhone 3GS', value: 2 }, 
        		{ label: 'iPhone 4', value: 50 }, 
        		{ label: 'iPhone 4S', value: 15 } 
        	],
        xkey: 'label',
        ykeys: ['value'],
        labels: ['Cantidad'],
        barRatio: 0.4,
        xLabelAngle: 35,
        hideHover: 'auto',
        resize: true
    });
    
    // Donut Chart
    Morris.Donut({
        element: 'morris-donut-chart',
        data: [
        		{ label: "En estado Registrado", value: 12 }, 
        		{ label: "En estado Evaluado", value: 30 }, 
        		{ label: "En estado Rechazado", value: 20 }
        	],
        resize: true
    });

});
