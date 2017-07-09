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
                        <form class="form-border" action="configuracao/atualizarConta" method="post">
                        <div id="textDiv"></div>
							<div class="col-xs-6">
							    <label class="control-label">Nome do usuário</label>
							    <input class="form-control" type="text" id="nome" name="nome">
							</div>
							<div class="col-xs-6">
							    <label class="control-label">Usuário</label>
							    <input class="form-control" type="text" id="usuario" name="usuario">
							</div>
							<div class="col-xs-6">
							    <label class="control-label">E-mail</label>
							    <input class="form-control" disabled type="text" value="">
							</div>
							<div class="col-xs-6">
							    <label class="control-label">Senha</label>
							    <input class="form-control" type="text" id="senha" name="senha">
							</div>
							<div class="pull-right">
                                <br>
                                <input type="hidden" id="idUsuario" value="1" name="id">
                                <button class="btn btn-md btn-success" type="submit" onClick="return salvarConta();"> Salvar</button>
                                <button class="btn btn-md btn-danger" type="button" onClick="excluirConta();"> Excluir conta</button>
                              </div>
                        </form>
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