<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<html>
<head>
<meta charset="UTF-8">
<title>FoxLink品質資訊與數位分析 系統</title>
<%-- <c:url value="/resources/bootstrap-3.3.7-dist/css/bootstrap.min.css" var="BootStrapCSS" />
	<c:url value="/resources/css/SubPage.css" var="SubPageCSS" />
	<link href="${BootStrapCSS}" rel="stylesheet">
	<link href="${SubPageCSS}" rel="stylesheet"> --%>
<link rel="stylesheet" type="text/css"
	href="../resources/bootstrap-3.3.7-dist/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="../resources/css/SubPage.css">
<%--<c:url value="/resources/jq/jquery-1.11.3.min.js" var="JqueryJS" />
	<c:url value="/resources/bootstrap-3.3.7-dist/js/bootstrap.min.js" var="BootStrapJS" />
	<script src="${JqueryJS}" type="text/javascript"></script>
	<script src="${BootStrapJS}" type="text/javascript"></script> --%>
<script type="text/javascript"
	src="../resources/jq/jquery-1.11.3.min.js"></script>
<script type="text/javascript"
	src="../resources/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<script type="text/javascript" src="../resources/JS/AjaxCheckSession.js"></script>
<script type="text/javascript" src="../resources/JS/operate/LinkManage.js"></script>
</head>
<body>
	<div class="top">
		<form class="form-horizontal">
			<div class="form-box">
				<label for="fl-Factory" class="col-sm-1 control-label">廠區: </label>
				<div class="col-sm-2">
					<select id="fl-Factory" name="Factory" class="form-control">
						<option>禁止选择</option>
					</select>
				</div>
				<label for="fl-Floor" class="col-sm-1 control-label">樓層: </label>
				<div class="col-sm-2">
					<select id="fl-Floor" name="Floor" class="form-control">
						<option>禁止选择</option>
					</select>
				</div>
				<label for="fl-PrdU" class="col-sm-1 control-label">生產單位: </label>
				<div class="col-sm-2">
					<select id="fl-PrdU" name="PrdU" class="form-control">
						<option>禁止选择</option>
					</select>
				</div>
				<label for="fl-Link" class="col-sm-1 control-label">綫別: </label>
				<div class="col-sm-2">
					<input id="fl-Link" name="Link" type='text' class="form-control" />
				</div>
			</div>
			<button type="button" class="btn btn-primary btn-left" id="addLink">新增or修改</button>
			<!-- <button type="button" class="btn btn-primary">点击</button> -->
		</form>
	</div>
	<div class="bottom" style = "overflow:auto">
		<table class="table table-hover table-bordered show-table" id="linkManageTable">
		<thead>
			<tr>
				<th>廠區</th>
				<th>樓層</th>
				<th>線別</th>
				<th>部門代碼</th>
				<th>生產單位</th>
				<th>推播群組</th>
				<th>刪除</th>
			</tr>
		</thead>
		<tbody></tbody>
		</table>
	</div>
</body>
</html>