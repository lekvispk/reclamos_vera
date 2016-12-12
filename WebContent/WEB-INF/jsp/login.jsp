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
                        
			        <c:if test="${not empty msgError}"><div class="alert alert-danger"> <strong><c:out value="${msgError}"/></strong> </div></c:if>
					<c:if test="${not empty mensaje}"><div class="alert alert-success">  <strong><c:out value="${mensaje}"/></strong> </div></c:if>
				
                    <div class="panel-heading">
                        <h3 class="panel-title">Iniciar sesi&oacute;n</h3>
                    </div>
                    <div class="panel-body">
                       <form accept-charset="UTF-8" role="form" action="${pageContext.request.contextPath}/j_spring_security_check" method="post">
                            <fieldset>
                            <c:if test="${not empty param.login_error}">
				   				<div class="errorLogin">				
									No pudo ingresar al sistema, Intentelo nuevamente.<br />
									Razon: ${sessionScope.SPRING_SECURITY_LAST_EXCEPTION.message}
									
									<script>
										document.forms[0].j_password.focus();
									</script>
								</div>				
							</c:if>
                                <div class="form-group">
                                	<c:if test="${not empty param.login_error}">
										<c:set var="username" value="${sessionScope.SPRING_SECURITY_LAST_USERNAME}"/>
									</c:if>
                                    <input class="form-control" placeholder="E-mail" name="j_username"  value="${username}" type="text" autofocus>
                                </div>
                                <div class="form-group">
                                    <input class="form-control" placeholder="Password" name="j_password" type="password" value="">
                                </div>
                                <div class="checkbox">
                                    <label>
                                        <input name="remember" type="checkbox" value="Remember Me">Recordarme</label>
                                </div>
                                <!-- Change this to a button or input when using this as a form -->
                                <input class="btn btn-lg btn-success btn-block" type="submit" value="Login">
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
