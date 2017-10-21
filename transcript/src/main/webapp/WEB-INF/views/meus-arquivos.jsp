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
                        Arquivos
                    </div>
                    <!--<form class="form-inline">-->
                    <div class="panel-body">
                    	<c:if test="${not empty log}">
							<div class="alert alert-success">${log}</div>
						</c:if>
                        <div class="row">
                        	<form class="form-filtro" action='<c:url value="/arquivo/meus-arquivos/filtrar"/>' method="post">
                        		<input type="hidden" name="idUsuario" value="${usuarioLogado.id}">
	                            <div class="col-md-5">
	                                <div class="form-group">
	                                    <label>Arquivo</label>
	                                    <input type="text" class="form-control" id="arquivo" name="nome" data-required="true" maxlength="20">
	                                </div>
	                            </div>
	                            <div class="col-md-5">
	                                <label for="">Data</label>
	                                <div class="input-group">
	                                    <input type="date" name="de" class="form-control">
	                                    <span class="input-group-addon">até</span>
	                                    <input type="date" name="para" class="form-control">
	                                 </div>
	                            </div>
	                            <div class="text-right">
		                            <button class="btn btn-primary" type="submit" 
		                            style="margin-top: 22px; margin-right: 22px;">Filtrar</button>
	                            </div>
                            </form>
                        </div>
	                    <table class="table table-bordered table-condensed table-hover table-striped" style="font-size: 12px; vertical-align: middle;">
	                        <thead>
	                            <tr>
	                                <th class="text-center">Nome</th>
	                                <th class="text-center">Tamanho</th>
	                                <th class="text-center">Data</th>
	                                <th class="text-center">Ações</th>
	                            </tr>
	                        </thead>
	                        <tbody class="text-center">
	                            <c:forEach var="arquivo" items="${arquivos}">
		                            <tr>
		                                <td>${arquivo.nome }</td>
		                                <td>${arquivo.tamanho } Kbs</td>
		                                <td>${arquivo.data }</td>
		                                <td>
		                                    <div class="btn-group">
		                                        <button class="btn btn-sm btn-info dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><i class="fa fa-cogs fa-lg">&nbsp;</i> <span class="caret"></span></button>
		                                        <ul class="dropdown-menu dropdown-menu-right slidedown">
		                                        	<c:if test="${arquivo.usuario.id == usuarioLogado.id }">
			                                            <li><a href="/arquivo/autorizar/${arquivo.id}"><i class="fa fa-eye"></i> Autorizar</a></li>
			                                            <li class="divider"></li>
			                                            <li><a href="/arquivo/remover-autorizacao/${arquivo.id}"><i class="fa fa-eye"></i> Remover autorização</a></li>
			                                            <li class="divider"></li>
		                                            </c:if>
		                                            <li>
		                                            	<c:url value="/arquivo/download" var="url">
															<c:param name="caminho">${arquivo.caminho}</c:param>
														</c:url>
		                                            	<a href="${url }" target="_blank"><i class="fa fa-arrow-circle-o-down"></i> Download</a>
		                                            </li>
		                                            <li class="divider"></li>
		                                            <li>
		                                            	<c:url value="/arquivo/downloadCriptografado" var="url">
															<c:param name="caminho">${arquivo.caminho}</c:param>
														</c:url>
		                                            	<a href="${url }" target="_blank"><i class="fa fa-arrow-circle-o-down"></i> Download criptografado</a>
		                                            </li>
		                                            <c:if test="${arquivo.usuario.id == usuarioLogado.id }">
			                                            <li class="divider"></li>
			                                            <li><a href="/arquivo/excluir-arquivo/${arquivo.id}"><i class="fa fa-times"></i> Excluir</a></li>
		                                            </c:if>
		                                        </ul>
		                                    </div><!-- /btn-group -->
		                                </td>
		                            </tr>
	                            </c:forEach>
	                        </tbody>
	                    </table>
                    </div><!--</panel body>-->
                </div><!--</panel default>-->
            </div><!--</container>-->
        </div><!--</main container>-->
        <!--corpo -->
	</layout:put>
	<layout:put block="scripts" type="REPLACE">
	</layout:put>
</layout:extends>