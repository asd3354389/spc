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
	<script type="text/javascript" src="../resources/JS/AjaxCheckSession.js"></script>
	<script type="text/javascript" src="../resources/JS/operate/SPCTPFileSelect.js"></script>
</head>
<body>
	<div class="top">
		<form class="form-horizontal" id="form1" enctype="multipart/form-data" method="post">
			<div class="form-box">
					<h3><span class="span-left">CTP規格書搜尋</span></h3>
			</div>
			<div class="form-box">
				<label for="project-bd"  class="col-sm-1 control-label">專案名稱_版次:
				</label>
				<div class="file-main">
					<input id="project-bd" name="project-bd" class="form-control" type="text">
				</div>	
				<button type="button" class="btn btn-primary file-only" id="CTP_Select">搜尋</button>	
			</div>
		</form>
	</div>
	<div class="bottom" style="overflow: auto;">
	
	<!--   <table class='table table-hover table-bordered show-table' id='CTPSelect_Table'>
	<thead>
	<tr>
	<th style="text-align: center; color: red; background: ">專案名稱</th>
	</tr>
	</thead>
	<table>  -->
	</div>
</body>
</html>