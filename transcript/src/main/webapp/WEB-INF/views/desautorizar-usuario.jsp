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
                        Desautorizar usuário
                    </div>
                    <!--<form class="form-inline">-->
                    <div class="panel-body">
                    	<c:if test="${not empty log}">
							<div class="alert alert-success">${log}</div>
						</c:if>
                        <form class="form-border" action="/arquivo/confirmarRemover" method="post">
                                <div class="col-md-6">
	                                <input type="hidden" name="id" value="${arquivo.id }">
                                	<input type="hidden" name="nome" value="${arquivo.nome }">
                                	<input type="hidden" name="idUsuario" value="${usuarioLogado.id }">
                                    <label for="arquivo">Arquivo</label>
                                    <input type="text" class="form-control" value="${arquivo.nome }">
                                </div>
                                <div class="col-md-6">
                                    <label>Usuário</label>
                                    <select id="usuario" name="idPermitido" required="required" class="form-control">
                                        <option value="0">Selecionar</option>
                                        <c:forEach var="usuario" items="${usuarios }">
                                        	<option value="${usuario.id }">${usuario.nome }</option>
                                        </c:forEach>
                                    </select>
                                </div><!-- /form-group -->
                              <div class="form-group col-md-1 pull-right">
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
	<!-- javaScript aqui -->
	<script src="/resources/js/custom/enviar-arquivo.js"></script>
	</layout:put>
</layout:extends>