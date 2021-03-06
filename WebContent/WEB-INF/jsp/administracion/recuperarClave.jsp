<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<head>
	
	<link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicon.ico" />
	
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Reclamos</title>

    <!-- Bootstrap Core CSS -->
    <link href="${pageContext.request.contextPath}/bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="${pageContext.request.contextPath}/bower_components/metisMenu/dist/metisMenu.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="${pageContext.request.contextPath}/dist/css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="${pageContext.request.contextPath}/bower_components/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

    <div class="container">
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <div class="login-panel panel panel-default">
                        
			        <c:if test="${not empty msgError}"><div class="alert alert-danger"> <button type="button" class="close" data-dismiss="alert" aria-hidden="true">�</button> <strong><c:out value="${msgError}"/></strong> </div></c:if>
					<c:if test="${not empty mensaje}" ><div class="alert alert-success"><button type="button" class="close" data-dismiss="alert" aria-hidden="true">�</button> <strong><c:out value="${mensaje}"/></strong> </div></c:if>
				
                    <div class="panel-heading">
                        <h3 class="panel-title">Recuperar Contrase&ntilde;a</h3>
                    </div>
                    <div class="panel-body">
                       <form accept-charset="UTF-8" role="form" action="${pageContext.request.contextPath}/solicitarClave.htm" method="post">
                            
                            <fieldset>
                                <div class="form-group">
                                    Se enviar&aacute; una clave tenporal a su correo electronico
                                </div>
                                <div class="form-group">
                                    <input class="form-control" placeholder="E-mail" name="email"  value="" type="email" autofocus>
                                </div>
                                <input class="btn btn-lg btn-success btn-block" type="submit" value="Enviar clave temporal">
                            </fieldset>
                            
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- jQuery -->
    <script src="${pageContext.request.contextPath}/bower_components/jquery/dist/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="${pageContext.request.contextPath}/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="${pageContext.request.contextPath}/bower_components/metisMenu/dist/metisMenu.min.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="${pageContext.request.contextPath}/dist/js/sb-admin-2.js"></script>

</body>

</html>
