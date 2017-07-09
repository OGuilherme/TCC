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
                        <form class="form-border">
                                <div class="col-md-6">
                                    <label for="cargo">Arquivo</label>
                                    <select id="cargo" class="form-control">
                                        <option value="0">Selecionar</option>
                                        <option value="1">Shablau</option>
                                    </select>
                                </div>
                                <div class="col-md-6">
                                    <label>Usuário</label>
                                    <input type="text" class="form-control" data-required="true">
                                </div><!-- /form-group -->
                              <div class="col-md-12 row">
                                <button class="btn btn-md btn-success" type="button"> Salvar</button>
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