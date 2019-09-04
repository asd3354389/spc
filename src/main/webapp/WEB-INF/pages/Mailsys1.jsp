<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"/>
<title>密碼找尋系統</title>
<link rel="stylesheet" type="text/css" href="../resources/css/RetrievePassword.css"> 
<script type="text/javascript" src="../resources/jq/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="../resources/JS/operate/RetrievePassword.js"></script>
</head>
<body>
<div>
<h1 class="RetrievePassword" >
	<t>密  碼  找 尋 系 統</t>
	</h1>
	<div>
	<form action="POST" name="rPasword">
	<div class="Account">
	<span class="AccountSpan" >工 號 :</span>
	<input type="text" name="工號"  id="Account">
	<br>
	<br>
	<button type="button" id="launch">送出</button>
	<input type="button" value="回上一頁" class="return" onclick="javascript:window.location.href='/spc/Login'">
	</div>
	</form>
	</div>
</div>
	
</body>
</html>