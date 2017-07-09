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
                        Resultado de busca
                    </div>
                    <table class="table table-bordered table-condensed table-hover table-striped" style="font-size: 12px; vertical-align: middle;">
                        <thead>
                            <tr>
                                <th class="text-center">Nome</th>
                                <th class="text-center">Usuário</th>
                                <th class="text-center">Ações</th>
                            </tr>
                        </thead>
                        <tbody class="text-center">
                        	<c:forEach items="${usuarios}" var="usuarios">
                            <tr>
                                <td>${usuarios.nome}</td>
                                <td>${usuarios.usuario}</td>
                                <td>
                                    <div class="btn-group">
                                        <button class="btn btn-md btn-info" ><span href="/meus-arquivos/${usuarios.id}"> Ver arquivos</span></button>
                                    </div><!-- /btn-group -->
                                </td>
                            </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div><!--</panel default>-->
                <div class="panel-footer clearfix">
                    <div class="row">
                        <ul class="pagination pagination-xs m-top-none pull-right">
                            <li class="disabled"><a href="#">Anterior</a></li>
                            <li class="active"><a href="#">1</a></li>
                            <li><a href="#">2</a></li>
                            <li><a href="#">3</a></li>
                            <li><a href="#">4</a></li>
                            <li><a href="#">5</a></li>
                            <li><a href="#">Próximo</a></li>
                        </ul>
                    </div><!--</div row>-->
                </div><!--</panel footer>-->
            </div><!--</container>-->
        </div><!--</main container>-->
        <!--corpo -->
	</layout:put>
	<layout:put block="scripts" type="REPLACE">
	</layout:put>
</layout:extends>