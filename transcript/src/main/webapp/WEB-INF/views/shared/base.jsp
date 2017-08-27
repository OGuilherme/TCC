<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance" prefix="layout"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	<meta name="author" content="">
	<!-- <link rel="shortcut icon" type="image/ico" href="/resources/img/prosperity.ico"/> -->
	
	<!-- Bootstrap core CSS -->
	<link href="/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	
	<!-- Font Awesome -->
	<link href="/resources/css/font-awesome.min.css" rel="stylesheet">
	
	<!-- Pace -->
	<link href="/resources/css/pace.css" rel="stylesheet">
	
	<!-- Morris -->
	<link href="/resources/css/morris.css" rel="stylesheet"/>	
	
	<!-- Perfect -->
	<link href="/resources/css/app.css" rel="stylesheet">
	<link href="/resources/css/app-skin.css" rel="stylesheet">
	
	<link href="/resources/css/custom.css" rel="stylesheet">
	
	<layout:block name="title">
		<title>Transcript</title>
	</layout:block>
</head>
<body class="overflow-hidden">
<body>
    <!-- Overlay Div -->
    <div id="overlay" class="transparent"></div>
	<div id="wrapper" class="preload">
        <div id="top-nav" class="skin-6 fixed">
          	<div class="brand">
                <a href="dashboard.html"><img src="img/logo.png" class="logo"></a>
            </div><!-- /brand -->
            <button type="button" class="navbar-toggle pull-left" id="sidebarToggle">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
        </div><!-- /top-nav-->
        <aside class="fixed skin-6">
			<div class="sidebar-inner scrollable-sidebar">
				<div class="user-block clearfix">
					<div class="detail">
						<strong>${usuarioLogado.nome}</strong>
					</div>
				</div><!-- /user-block -->
				<div class="main-menu">
					<ul>
						<li>
							<a href="/pagina-inicial">
								<span class="menu-icon">
									<i class="fa  fa-home fa-lg"></i>
								</span>
								<span class="text">
									Página inicial
								</span>
								<span class="menu-hover"></span>
							</a>
                        </li>
						<li class="openable">
							<a href="#">
								<span class="menu-icon">
									<i class="fa fa-folder fa-lg"></i>
								</span>
								<span class="text">
									Meus arquivos
								</span>
								<span class="menu-hover"></span>
							</a>
                            <ul class="submenu">
								<li>
									<a href="/arquivo/meus-arquivos/${usuarioLogado.id}"><span class="submenu-label">Consultar meus arquivos</span></a></li>
                                <li><a href="/arquivo/enviar"><span class="submenu-label">Enviar novo arquivo</span></a></li>
							</ul>
						</li>
						<li>
							<a href="/busca">
								<span class="menu-icon">
									<i class="fa fa-search fa-lg"></i>
								</span>
								<span class="text">
									Buscar usuarios
								</span>
								<span class="menu-hover"></span>
							</a>
						</li>
						<li>
							<a href="/configuracao">
								<span class="menu-icon">
									<i class="fa fa-cog fa-lg"></i>
								</span>
								<span class="text">
									Configurações
								</span>
								<span class="menu-hover"></span>
							</a>
						</li>
                        <li>
							<a href="/login">
								<span class="menu-icon">
									<i class="fa fa-power-off fa-lg"></i>
								</span>
								<span class="text">
									Sair
								</span>
								<span class="menu-hover"></span>
							</a>
						</li>
					</ul>
				</div><!-- /main-menu -->
			</div><!-- /sidebar-inner -->
		</aside>
		<!-- conteúdo da página será carregado aqui -->
	    <layout:block name="contents">
	    
	    </layout:block>
	</div>
	<!-- /wrapper -->
	
	<!-- Logout confirmation -->
	<div class="custom-popup width-100" id="logoutConfirm">
		<div class="padding-md">
			<h4 class="m-top-none">Deseja realmente sair?</h4>
		</div>
		<div class="text-center">
			<a class="btn btn-success m-right-sm" href="/login/logout">Sair</a> 
			<a class="btn btn-danger logoutConfirm_close">Cancelar</a>
		</div>
	</div>
	
	
	<!-- Le javascript ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	
	<!-- Jquery -->
	<script src="/resources/js/jquery-1.10.2.min.js"></script>
	<script src="/resources/js/jquery.dataTables.min.js"></script>
	
	<!-- Bootstrap -->
	<script src="/resources/bootstrap/js/bootstrap.min.js"></script>
	<script src="/resources/js/dataTables.bootstrap.min.js"></script>
	
	<!-- Chosen -->
	<script src='/resources/js/chosen.jquery.min.js'></script>
	
	<!-- Mask-input -->	
	<script src='/resources/js/jquery.mask.min.js'></script>
	
	<!-- Datepicker -->
	<script src='/resources/js/bootstrap-datepicker.min.js'></script>
	
	<!-- Timepicker -->
	<script src='/resources/js/bootstrap-timepicker.min.js'></script>
	
	<!-- Slider -->
	<script src='/resources/js/bootstrap-slider.min.js'></script>
	
	<!-- Flot -->
	<!-- <script src='/resources/js/jquery.flot.min.js'></script> -->
	
	<!-- Morris -->
	<!-- <script src='/resources/js/rapheal.min.js'></script>	
	<script src='/resources/js/morris.min.js'></script>	 -->
	
	<!-- Tag input -->
	<script src='/resources/js/jquery.tagsinput.min.js'></script>
	
	<!-- WYSIHTML5 -->
	<script src='/resources/js/wysihtml5-0.3.0.min.js'></script>
	<script src='/resources/js/uncompressed/bootstrap-wysihtml5.js'></script>
	
	<!-- Dropzone -->
	<script src='/resources/js/dropzone.min.js'></script>
	
	<!-- Modernizr -->
	<script src='/resources/js/modernizr.min.js'></script>
	
	<!-- Pace -->
	<script src='/resources/js/pace.min.js'></script>
	
	<!-- Popup Overlay -->
	<script src='/resources/js/jquery.popupoverlay.min.js'></script>
	
	<!-- Slimscroll -->
	<script src='/resources/js/jquery.slimscroll.min.js'></script>
	
	<!-- Cookie -->
	<script src='/resources/js/jquery.cookie.min.js'></script>
	
	<script src="/resources/js/app/app_form.js"></script>
	<script src="/resources/js/app/app.js"></script>
	
	<!-- menu lateral -->
	<script src="/resources/js/custom/menu.js"></script>

	<!-- Colorbox -->
	<script src='/resources/js/jquery.colorbox.min.js'></script>	

	<!-- Sparkline -->
	<script src='/resources/js/jquery.sparkline.min.js'></script>
	
	<!-- Pace -->
	<script src='/resources/js/uncompressed/pace.js'></script>
	
	<!-- Modernizr -->
	<script src='/resources/js/modernizr.min.js'></script>
	
	<!-- Perfect -->
	<!-- <script src="/resources/js/app/app_dashboard.js"></script> -->
	
	<!-- scripts serão carregados aqui -->	
	<layout:block name="scripts"></layout:block>
	
</body>
</html>