<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link rel="stylesheet" type="text/css" href="../resources/bootstrap-3.3.7-dist/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="../resources/css/SubPage.css">
	<script type="text/javascript" src="../resources/jq/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="../resources/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="../resources/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="../resources/JS/AjaxCheckSession.js"></script>
</head>
<body>
	<div class="top">
		<form class="form-horizontal" id="form1" enctype="multipart/form-data" method="post">
			<div class="form-box">
				<label for="fl-Factory"  class="col-sm-1 control-label">廠區:
				</label>
				<div class="col-sm-2">
					<select id="fl-Factory" name="fl-Factory" class="form-control">
						<option>禁止选择</option>
					</select>
				</div>
				<label for="fl-Link"  class="col-sm-1 control-label">綫別:
				</label>
				<div class="col-sm-2">
					<select id="fl-Link" name="fl-Link" class="form-control">
						<option>禁止选择</option>
					</select>
				</div>
				<label for="pro-Name"  class="col-sm-1 control-label">專案名稱:
				</label>
				<div class="col-sm-2">
					<select id="pro-Name" name="pro-Name" class="form-control">
						<option>禁止选择</option>
					</select>
				</div>
				<label for="part-Verion"  class="col-sm-1 control-label">料號_版本:
				</label>
				<div class="col-sm-2">
					<select id="part-Verion" name="part-Verion" class="form-control">
						<option>禁止选择</option>
					</select>
				</div>
			</div>
			<div class="form-box">
				<label for="dpick1"  class="col-sm-1 control-label">搜尋日期:
				</label>
				<div class="col-sm-2">
					<input id="dpick1" name="search-time" style="height:34px;" class="Wdate form-control" type="text" onClick="WdatePicker({maxDate:'%y-%M-{%d}'})" autocomplete="off">
				</div>
				<button type="button" class="btn btn-primary btn-main">查詢</button>
				
			</div>
			<!-- <button type="submit" class="btn btn-primary btn-left">提交</button>
			<button type="submit" class="btn btn-primary btn-left">輸出</button> -->
			<!-- <button type="button" class="btn btn-primary">点击</button> -->
		</form>
	</div>
	<div class="bottom">
	</div>
</body>
</html>