<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance" prefix="layout"%>

<layout:extends name="base">
	<layout:put block="contents">
<!-- Modal delete -->
        <div class="modal fade" id="delete-modal" data-target="#delete-modal" tabindex="-1" role="dialog" aria-labelledby="modalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Fechar">
                            <span aria-hidden="true">&times;</span>
                        </button>
                        <h4 class="modal-title" id="modalLabel">Excluir arquivos</h4>
                    </div>
                    <div class="modal-body">Deseja realmente excluir este arquivo?</div>
                    <div class="modal-footer">
                        <a href="#">
                            <button id="excluirArquivo" type="button" class="btn btn-primary" data-dismiss="modal">Sim</button>
                        </a>
                        <button type="button" class="btn btn-default" data-dismiss="modal">Não</button>
                    </div>
                </div>
            </div>
        </div>
        <!-- /.modal delete-->

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
                        <div class="row ">
                            <div class="col-md-4">
                                <div class="form-group">
                                    <label>Arquivo</label>
                                    <input type="text" class="form-control" id="arquivo" data-required="true">
                                </div>
                            </div>
                            <div class="col-md-5">
                                <label for="">Data</label>
                                <div class="input-group">
                                    <input type="date" class="form-control">
                                    <span class="input-group-addon">até</span>
                                    <input type="date" class="form-control">
                                 </div>
                            </div>
                            <div class="text-right">
                                <a href="#" style="text-decoration: none; color: #ffffff">
                                    <button class="btn btn-primary" style="margin-top: 22px; margin-right: 22px;">Filtrar</button>
                                </a>
                            </div>
                        </div>
                    </div><!--</panel body>-->
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
                            <tr>
                                <td>Shablau</td>
                                <td>150mb</td>
                                <td>15/02/2017</td>
                                <td>
                                    <div class="btn-group">
                                        <button class="btn btn-sm btn-info dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><i class="fa fa-cogs fa-lg">&nbsp;</i> <span class="caret"></span></button>
                                        <ul class="dropdown-menu dropdown-menu-right slidedown">
                                            <li><a href="#autorizar-modal" data-toggle="modal"><i class="fa fa-eye"></i> Autorizar</a></li>
                                            <li class="divider"></li>
                                            <li><a href="#"><i class="fa fa-arrow-circle-o-down"></i> Download</a></li>
                                            <li class="divider"></li>
                                            <li><a href="#delete-modal" data-toggle="modal" ><i class="fa fa-times"></i> Excluir</a></li>
                                        </ul>
                                    </div><!-- /btn-group -->
                                </td>
                            </tr>
                            <tr>
                                <td>Alacatum</td>
                                <td>1gb</td>
                                <td>15/02/2017</td>
                                <td>
                                    <div class="btn-group">
                                        <button class="btn btn-sm btn-info dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><i class="fa fa-cogs fa-lg">&nbsp;</i> <span class="caret"></span></button>
                                        <ul class="dropdown-menu dropdown-menu-right slidedown">
                                            <li><a href="#autorizar-modal" data-toggle="modal"><i class="fa fa-eye"></i> Autorizar</a></li>
                                            <li class="divider"></li>
                                            <li><a href="#"><i class="fa fa-arrow-circle-o-down"></i> Download</a></li>
                                            <li class="divider"></li>
                                            <li><a href="#delete-modal" data-toggle="modal" ><i class="fa fa-times"></i> Excluir</a></li>
                                        </ul>
                                    </div><!-- /btn-group -->
                                </td>
                            </tr>
                            <tr>
                                <td>primary</td>
                                <td>400kb</td>
                                <td>15/02/2017</td>
                                <td>
                                    <div class="btn-group">
                                        <button class="btn btn-info btn-sm dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><i class="fa fa-cogs fa-lg">&nbsp;</i> <span class="caret"></span></button>
                                        <ul class="dropdown-menu dropdown-menu-right slidedown">
                                            <li><a href="#autorizar-modal" data-toggle="modal"><i class="fa fa-eye"></i> Autorizar</a></li>
                                            <li class="divider"></li>
                                            <li><a href="#"><i class="fa fa-arrow-circle-o-down"></i> Download</a></li>
                                            <li class="divider"></li>
                                            <li><a href="#delete-modal" data-toggle="modal" ><i class="fa fa-times"></i> Excluir</a></li>
                                        </ul>
                                    </div><!-- /btn-group -->
                                </td>
                            </tr>
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