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
            
        </div><!-- /wrapper -->
	</layout:put>
	<layout:put block="scripts" type="REPLACE">
	</layout:put>
</layout:extends>