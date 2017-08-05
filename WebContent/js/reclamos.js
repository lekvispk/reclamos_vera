	
	function limpiarErrorAjax(){
		$('#mensajesAjax').removeClass('alert alert-danger');
		$('#mensajesAjax').removeClass('alert alert-success');
		$('#mensajesAjax').text('');
	}
	
	function agregarErrorAjax( mensaje ){
		$('#mensajesAjax').addClass('alert alert-danger');
		$('#mensajesAjax').text( mensaje );
	}
	
	function agregarMensajeAjax( mensaje ){
		$('#mensajesAjax').addClass('alert alert-success');
		$('#mensajesAjax').text( mensaje );
	}
	