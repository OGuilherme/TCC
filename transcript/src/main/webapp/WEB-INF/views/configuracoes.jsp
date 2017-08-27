<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance" prefix="layout"%>

<layout:extends name="base">
	<layout:put block="contents">
<div id="main-container">
            <div id="breadcrumb">
                    <ul class="breadcrumb">
                    </ul>
            </div><!--breadcrumb-->
            <!--corpo -->
            <div class="container">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        Configurações
                    </div>
                    <!--<form class="form-inline">-->
                    <div class="panel-body">
                       <form id="atualizar" class="form-border" action="configuracao/atualizarConta" method="post">
	                        <input type="hidden" value="${usuarioLogado.id }" name="id">
	                        <div id="textDiv"></div>
	                        <c:if test="${not empty log1}">
	        					<div class="alert alert-success text-center">${log1}</div>
							</c:if>
							<div class="col-xs-6">
							    <label class="control-label">Nome do usuário</label>
							    <input class="form-control" type="text" id="nome" name="nome" value="${usuarioLogado.nome }">
							    <input type="hidden" id="idUsuario" value="${usuarioLogado.id }" required="required" name="id" maxlength="20">
							</div>
							<div class="col-xs-6">
							    <label class="control-label">Usuário</label>
							    <input class="form-control" type="text" id="usuario" name="usuario" maxlength="18"
							    required="required" value="${usuarioLogado.usuario }">
							</div>
							<div class="col-xs-6">
							<br>
							    <label class="control-label">E-mail</label>
							    <input class="form-control" disabled type="email" value="${usuarioLogado.email }" maxlength="50">
							</div>
							<div class="col-xs-6">
							<br>
							    <label class="control-label">Senha</label>
							    <input class="form-control" type="password" id="senha" name="senha" maxlength="12">
							</div>
                        </form>
                        <form id="excluir" class="form-border" action="configuracao/excluirConta" method="post">
                       			<input type="hidden" id="idUsuario" value="${usuarioLogado.id }" name="id">
                        </form>
                        <div class="pull-right">
	                        <br>
	                        <button class="btn btn-md btn-success" type="button" onClick="salvarConta();"> Salvar</button>
	                        <button class="btn btn-md btn-danger" type="button" onClick="excluirConta();"> Excluir conta</button>
                    	</div>
                    </div><!--</panel body>-->
                </div>
            </div><!--</container>-->
        </div><!--</main container>-->
        <!--corpo -->
	</layout:put>
	<layout:put block="scripts" type="REPLACE">
	<!-- javaScript aqui -->
	<script src="/resources/js/custom/configuracoes.js"></script>
	</layout:put>
</layout:extends>