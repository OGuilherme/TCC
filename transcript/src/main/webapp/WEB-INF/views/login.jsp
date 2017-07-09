<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>Login</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Bootstrap core CSS -->
	<link href="/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	
	<!-- Font Awesome -->
	<link href="/resources/css/font-awesome.min.css" rel="stylesheet">
	
	<!-- Pace -->
	<link href="/resources/css/pace.css" rel="stylesheet">
	
	<!-- Perfect -->
	<link href="/resources/css/app.css" rel="stylesheet">
	<link href="/resources/css/app-skin.css" rel="stylesheet">

  </head>
  <body>
	<div class="login-wrapper">
		<div class="text-center">
			<h2 class="fadeInUp animation-delay8" style="font-weight:bold">
                <span style="color:#000">TransCrip</span>
			</h2>
		</div>
		<div class="login-widget animation-delay1">	
			<div class="panel panel-default">
				<c:if test="${not empty log1}">
        			<div class="alert alert-success text-center">${log1}</div>
				</c:if>
				<c:if test="${not empty log2}">
        			<div class="alert alert-danger text-center">${log2}</div>
				</c:if>
				<div class="panel-heading clearfix">
					<div class="pull-left">
						<i class="fa fa-lock fa-lg"></i> Login
					</div>
					<div class="pull-right">
						<span style="font-size:11px;">Não tem uma conta?</span>
						<a class="btn btn-default btn-xs" href="#novo-usuario-modal" data-toggle="modal" style="margin-top:-2px;"><i class="fa fa-plus-circle"></i> Inscreva-se</a>
					</div>
				</div>
				<div class="panel-body">
					<form class="form-login" action="login/autenticar" method="post">
						<c:if test="${not empty log}">
							<div class="alert alert-danger">${log}</div>
						</c:if>
						<div class="form-group bounceIn animation-delay2">
							<label>E-mail</label>
							<div class="input-group">
                                <span class="input-group-addon">@</span>
                                <input name="email" type="text" placeholder="exemplo@transcrip.com.br" class="form-control input-sm">
                            </div>
						</div>
						<div class="form-group bounceIn animation-delay4">
                            <label>Senha</label>
							<div class="input-group">
                                <span class="input-group-addon"><i class="fa fa-lock"></i></span>
                                <input name="senha" type="password" placeholder="Senha" class="form-control input-sm">
                            </div>
						</div>
						<div class="seperator"></div>
						<div class="form-group bounceIn animation-delay5">
                            <a href="#recuperar-senha-modal" data-toggle="modal">Esqueceu sua senha?</a>
						</div>

						<hr/>
						<button class="btn btn-success btn-sm pull-right bounceIn animation-delay6" type="submit">
							<i class="fa fa-sign-in"></i> Entrar
						</button>	
					</form>
				</div>
			</div><!-- /panel -->
		</div><!-- /login-widget -->
	</div><!-- /login-wrapper -->
	
	<!-- Modal inscrever-se -->
    <div class="modal fade" id="novo-usuario-modal" data-target="#novo-usuario-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="login-wrapper">
            <div class="text-center">
                <h2 class="fadeInUp animation-delay8" style="font-weight:bold">
                    <span style="color:#000">TransCrip</span>
                </h2>
            </div>
            <div class="login-widget animation-delay1">	
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <i class="fa fa-plus-circle fa-lg"></i> Inscrever-se
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    </div>
                    <div class="panel-body">
                        <form class="form-login" action="login/registrar" method="post" onsubmit="return validarSenha()">
                        	<div id="textDiv"></div>
                            <div class="form-group bounceIn animation-delay">
                                <label>Usuário</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                    <input name="usuario" id="user" type="text" placeholder="Usuário" class="form-control input-sm" >
                                </div>
                            </div><!-- /form-group -->
                            <div class="form-group bounceIn animation-delay3">
                                <label>Nome</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                    <input name="nome" id="nome" type="text" placeholder="Nome" class="form-control input-sm" >
                                </div>
                            </div><!-- /form-group -->
                            <div class="form-group bounceIn animation-delay4">
                                <label>E-mail</label>
                                <div class="input-group">
                                    <span class="input-group-addon">@</span>
                                    <input name="email" id="email" type="text" placeholder="exemplo@transcrip.com.br" class="form-control input-sm">
                                </div>
                            </div><!-- /form-group -->
                            <div class="form-group bounceIn animation-delay5">
                                <label>Senha</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-lock"></i></span>
                                    <input name="senha" id="senha" type="password" placeholder="Senha" class="form-control input-sm">
                                </div>
                            </div><!-- /form-group -->
                            <div class="form-group bounceIn animation-delay6">
                                <label>Confirmação de senha</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-lock"></i></span>
                                    <input id="confirmarSenha" type="password" placeholder="Confirmação de senha" class="form-control input-sm">
                                </div>
                            </div><!-- /form-group -->
                            <div class="form-group bounceIn animation-delay7">
                                <label class="label-checkbox">
                                     <input type="checkbox"/>
                                     <span class="custom-checkbox info"></span>
                                     Eu aceito os termos de uso.
                                </label>
                            </div><!-- /form-group -->

                            <div class="seperator"></div>
                            <hr/>
                            <div class="form-group bounceIn animation-delay8">
                                <div class="controls text-right">
	                                <button class="btn btn-success btn-sm pull-right bounceIn animation-delay6" onClick="return validarCampos();" type="submit">
										<i class="fa fa-plus-circle"></i> Inscreva-se
									</button>	
                                </div>
                            </div><!-- /form-group -->
                        </form>
                    </div>
                </div><!-- /panel -->
            </div><!-- /login-widget -->
        </div><!-- /login-wrapper -->
    </div>
    <!-- Modal -->
      
    <!-- Modal esqueceu a senha -->
    <div class="modal fade" id="recuperar-senha-modal" data-target="#recuperar-senha-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="login-wrapper">
            <div class="text-center">
                <h2 class="fadeInUp animation-delay5" style="font-weight:bold">
                    <span style="color:#000">TransCrip</span>
                </h2>
            </div>
            <div class="login-widget animation-delay1">	
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <i class="fa fa-key fa-lg"></i> Recuperar senha
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    </div>
                    <div class="panel-body">
                        <form class="form-login bounceIn animation-delay2">
                            <div class="form-group">
                                <label>Usuário</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                    <input type="text" placeholder="Usuário" class="form-control input-sm" >
                                </div>
                            </div><!-- /form-group -->
                            <div class="form-group bounceIn animation-delay3">
                                <label>E-mail</label>
                                <div class="input-group">
                                    <span class="input-group-addon">@</span>
                                    <input type="text" placeholder="exemplo@transcrip.com.br" class="form-control input-sm">
                                </div>
                            </div><!-- /form-group -->
                            <div class="seperator"></div>
                            <hr/>
                            <div class="form-group bounceIn animation-delay4">
                                <div class="controls text-right">
                                    <a class="btn btn-warning btn-sm" href="#recuperar" data-toggle="modal"><i class="fa fa-key"></i> Recuperar</a>
                                </div>
                            </div><!-- /form-group -->
                        </form>
                    </div>
                </div><!-- /panel -->
            </div><!-- /login-widget -->
        </div><!-- /login-wrapper -->
    </div>
    <!-- Modal esqueceu a senha -->
    <!-- Modal inscrito com sucesso -->
    <!-- Modal -->
    <!-- Modal inscrito com sucesso -->
    <div class="modal fade" id="recuperar" data-target="#recuperar" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="alert alert-success text-center">
           <strong>Senha enviada para o seu e-mail com sucesso!</strong>
        </div>
    </div>
    <!-- Modal -->

    <!-- Le javascript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    
    <!-- Jquery -->
	<script src="/resources/js/jquery-1.10.2.min.js"></script>
    
    <!-- Bootstrap -->
    <script src="/resources/bootstrap/js/bootstrap.min.js"></script>
   
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

	<!-- Perfect -->
	<script src="/resources/js/app/app.js"></script>
	
	<!-- javaScript aqui -->
	<script src="/resources/js/custom/login.js"></script>
  </body>
</html>