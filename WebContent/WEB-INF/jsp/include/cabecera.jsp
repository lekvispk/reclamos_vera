<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri='http://www.springframework.org/security/tags' prefix='security'%>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Reclamos</title>

    <!-- Bootstrap Core CSS -->
    <link href="${pageContext.request.contextPath}/bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/css/bootstrap-datetimepicker.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="${pageContext.request.contextPath}/bower_components/metisMenu/dist/metisMenu.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="${pageContext.request.contextPath}/dist/css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="${pageContext.request.contextPath}/bower_components/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

	<link href="${pageContext.request.contextPath}/css/displaytag.css" rel="stylesheet" type="text/css"/>

	<link href="${pageContext.request.contextPath}/css/jquery-ui-1.10.4.custom.min.css" rel="stylesheet">
	
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

	<!-- estilos para jquery validator -->
	<link rel="stylesheet" href="https://jqueryvalidation.org/files/demo/site-demos.css">
</head>

<body>

    <div id="wrapper">

        <!-- Navigation -->
        <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="${pageContext.request.contextPath}/inicio.htm">Reclamos</a>
            </div>
            <!-- /.navbar-header -->

            <ul class="nav navbar-top-links navbar-right">
                
                <!-- /.dropdown -->
                
                <!-- /.dropdown -->
                <li>
                	Bienvenido: <b>${pageContext.request.userPrincipal.name} </b>
                </li>
                <!-- /.dropdown -->
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-user fa-fw"></i>  <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-user">
                        <!-- <li><a href="#"><i class="fa fa-user fa-fw"></i> User Profile</a>
                        </li>
                        <li><a href="#"><i class="fa fa-gear fa-fw"></i> Settings</a>
                        </li> -->
                        <li><a href="${pageContext.request.contextPath}/cambiarClave.htm"><i class="fa fa-gear fa-fw"></i> Cambiar clave</a></li>
                        <li class="divider"></li>
                        <li><a href="${pageContext.request.contextPath}/j_spring_security_logout"><i class="fa fa-sign-out fa-fw"></i> Logout</a>
                        </li>
                    </ul>
                    <!-- /.dropdown-user -->
                </li>
                <!-- /.dropdown -->
            </ul>
            <!-- /.navbar-top-links -->

            <div class="navbar-default sidebar" role="navigation">
                <div class="sidebar-nav navbar-collapse">
                    <ul class="nav" id="side-menu">
                        <li class="sidebar-search">
                            <!-- <div class="input-group custom-search-form">
                                <input type="text" class="form-control" placeholder="Search...">
                                <span class="input-group-btn">
                                    <button class="btn btn-default" type="button">
                                        <i class="fa fa-search"></i>
                                    </button>
                                </span>
                            </div> -->
                            <!-- /input-group -->
                        </li>
                        <security:authorize ifAnyGranted="ROLE_RECLAMO">
                        <li>
                            <a href="#"><i class="fa fa-edit fa-fw"></i> Reclamos<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                            	<security:authorize ifAnyGranted="ROLE_RECLAMO_R">
                                <li><a href="${pageContext.request.contextPath}/reclamos/registro.htm">Registrar</a></li>
                                </security:authorize>
                                <li><a href="${pageContext.request.contextPath}/reclamos/lGestionar.htm">Gestionar</a></li>
                                <li><a href="${pageContext.request.contextPath}/reclamos/lEvaluar.htm">Evaluar</a></li>
                                <li><a href="${pageContext.request.contextPath}/reclamos/lSolucionar.htm">Solucionar</a></li>
                                <li><a href="${pageContext.request.contextPath}/indemnizar/lIndemnizar.htm">Indemnizar</a></li>
                            </ul>
                            <!-- /.nav-second-level -->
                        </li>
                        </security:authorize>
                        <security:authorize ifAnyGranted="ROLE_FIDELIZA">
                        <li>
                        	<a href="#"><i class="fa fa-star fa-fw"></i> Fidelizar<span class="fa arrow"></span></a>
                        	<ul class="nav nav-third-level">
                        		<li><a href="${pageContext.request.contextPath}/fidelizar/lFidelizar.htm">Fidelizar</a></li>
                        		<li><a href="${pageContext.request.contextPath}/fidelizar/lPromociones.htm">Promoci&oacute;n</a></li>
                        		<li><a href="${pageContext.request.contextPath}/tablero/tablero.htm">Mostrar Tablero</a></li>
                        	</ul>	
                        </li>
                        </security:authorize>
                        <security:authorize ifAnyGranted="ROLE_CAPACITA">
                        <li>
                            <a href="#"><i class="fa fa-clipboard fa-fw"></i> Capacitaci&oacute;n<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                             	<li><a href="${pageContext.request.contextPath}/capacitacion/lCapacitaciones.htm">Registrar capacitaci&oacute;n</a></li>
                                <li><a href="${pageContext.request.contextPath}/capacitacion/lPosponer.htm">Posponer capacitaci&oacute;n</a></li>
                                <li><a href="${pageContext.request.contextPath}/capacitacion/lCapacitador.htm">Asignar capacitador</a></li>
                              </ul>
                            <!-- /.nav-second-level -->
                        </li>
                        </security:authorize>
                        <security:authorize ifAnyGranted="ROLE_DESPACHO">
                         <li>
                            <a href="#"><i class="fa fa-shopping-cart fa-fw"></i> Producto<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                             	<li><a href="${pageContext.request.contextPath}/producto/autorizar.htm">Autorizaci&oacute;n</a></li>
                                <li><a href="${pageContext.request.contextPath}/producto/seleccionar.htm">Selecci&oacute;n</a></li>
                                <li><a href="${pageContext.request.contextPath}/producto/asignar.htm">Asignar</a></li>
                              </ul>
                            <!-- /.nav-second-level -->
                        </li>
                        </security:authorize>
                        <li>
                            <a href="#"><i class="fa fa-link  fa-fw"></i> Seguimiento<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                               <li><a href="${pageContext.request.contextPath}/seguimiento/lista.htm">Ver Solicitudes</a></li>
                             </ul>
                            <!-- /.nav-second-level -->
                        </li>
                         <li>
                            <a href="#"><i class="fa fa-users fa-fw"></i> Clientes<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li><a href="${pageContext.request.contextPath}/clientes/lista.htm">Gestionar</a></li>
                            </ul>
                            <!-- /.nav-second-level -->
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-link  fa-fw"></i> Administraci&oacute;n<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                               <li><a href="${pageContext.request.contextPath}/usuario/lista.htm">Usuarios</a></li>
                               <li><a href="${pageContext.request.contextPath}/permiso/lista.htm">Permisos</a></li>
                               <li><a href="${pageContext.request.contextPath}/perfil/lista.htm">Perfiles</a></li>
                             </ul>
                            <!-- /.nav-second-level -->
                        </li>
                        
                    </ul>
                </div>
                <!-- /.sidebar-collapse -->
            </div>
            <!-- /.navbar-static-side -->
        </nav>
