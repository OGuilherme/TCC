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
                        Envio de arquivo
                    </div>
                    <!--<form class="form-inline">-->
                    <div class="panel-body">
                        <form class="form-border"  action='<c:url value="/salvar"/>' method="post" enctype="multipart/form-data">
                        <input type="hidden" value="${usuarioLogado.id}" name="usuario.id">
                        	<div id="textdiv"></div>
                        	<c:if test="${not empty sucesso}">
        						<div class="alert alert-success text-center">${sucesso}</div>
							</c:if>
                            <div class="col-xs-6"> 
                                <label class="control-label">Nome do arquivo</label>
                                <input class="form-control" required="required" type="text" id="nome" name="nome" maxlength="30">
                            </div>
                            <div class="col-md-6">
                                <label class="control-label">Arquivo</label>
                                <div class="upload-file">
                                    <input type="file" id="upload-arquivo" required="required" class="upload-demo" name="file">
                                    <label data-title="Selecione" for="upload-arquivo" id="arquivo">
                                        <span data-title="Nenhum arquivo selecionado..."></span>
                                    </label>
                                </div>
                            </div>
                            <div class="pull-right">
                            	<br>
                            	<br>
                            	<button class="btn btn-success btn-sm" type="submit" onClick="return validarCampos();">
									Salvar arquivo
								</button>	
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
	<script src="/resources/js/custom/enviar-arquivo.js"></script>
	</layout:put>
</layout:extends>