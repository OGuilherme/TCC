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
                        Buscar usuário
                    </div>
                    <!--<form class="form-inline">-->
                    <div class="panel-body">
                        <form class="form-busca" action="busca/realizarBusca" method="post">
                        	<div id="textDiv"></div>
                            <div class="col-md-6">
                                <label class="control-label">Usuário</label>
                                <input class="form-control" type="text" id="user" name="usuario">
                            </div>
                            <div class="col-md-6">
                                <label class="control-label">E-mail do usuário</label>
                                <input class="form-control" type="text" id="email" name="email">
                            </div>
                            <div class="col-md-12">
                            	<br>
                              	<button class="btn btn-md btn-success pull-right" type="submit" onClick="return validarCampos();">
                              	 Buscar</button>
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
	<script src="/resources/js/custom/buscar-usuarios.js"></script>
	</layout:put>
</layout:extends>