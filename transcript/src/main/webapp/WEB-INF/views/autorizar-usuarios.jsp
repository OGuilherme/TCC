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
                        Autorizar usuário
                    </div>
                    <!--<form class="form-inline">-->
                    <div class="panel-body">
                    	<c:if test="${not empty log}">
        					<div class="alert alert-warning text-center">${log}</div>
						</c:if>
                        <form class="form-border" action='<c:url value="/arquivo/confirmarAutorizacao"/>' method="post">
                                <div class="col-md-6">
                                	<input type="hidden" name="id" value="${arquivo.id }">
                                	<input type="hidden" name="nome" value="${arquivo.nome }">
                                	<input type="hidden" name="idUsuario" value="${usuarioLogado.id }">
                                    <label>Arquivo</label>
                                    <input type="text" class="form-control" value="${arquivo.nome}" disabled>
                                </div>
                                <div class="col-md-6">
                                    <label>Usuário</label>
                                    <input type="text" class="form-control" required="required"
                                    name="usuario" data-required="true" maxlength="20">
                                </div><!-- /form-group -->
                              <div class="pull-right col-md-1 form-group">
                              	<br><br>
                                <button class="btn btn-md btn-success" type="submit"> Salvar</button>
                              </div>
                        </form>
                    </div><!--</panel body>-->
                </div>
            </div><!--</container>-->
        </div><!--</main container>-->
        <!--corpo -->
	</layout:put>
	<layout:put block="scripts" type="REPLACE">
	</layout:put>
</layout:extends>