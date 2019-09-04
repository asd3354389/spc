$(function(){
	//工號返回值
	var AccountReturn;
	//姓名返回值
	var NameReturn;
	//部門返回值
	var DeptidReturn;
	//舊密碼返回值
	var OldPassWordReturn;
	//新密码返回值
	var NewPassWordReturn;
	//alert("点击了重置密码");
	
	
	$('#reset_Password').click(function(){
		
		//賬號
		var Account = $("#exampleInputAccountS").val();
		//姓名
		var Name = $("#exampleInputName").val();
		//部門
		var Deptid = $("#exampleInputDepId").val();
		//舊密碼
		var OldPassWord= $("#exampleInputOldPassWord").val();
		//新密碼
		var NewPassWord = $("#exampleInputNewPassWord").val();
		//確認新密碼  exampleInputNewPassWord1
		var NewPassWord1 = $("#exampleInputNewPassWord1").val();
		//alert("輸入正確!!");
		if (Account == ''||Name == ''||Deptid == ''||OldPassWord == ''||NewPassWord == ''||NewPassWord1 == '') {
			alert("你輸入的信息不完整,請重新輸入!!");
		} else{
			//查詢用戶信息
			selectAccount(Account);
			
		}
		
		
		
	

	});
	
	//查詢工號
	function  selectAccount(Account) {
	
		$.ajax({ 
			   url:"ResetPassword/CheckAccount", 
			   type:"POST",
			   async: false,
			  // dataType:"text",
			   data:{"str2V":Account},
			   success:function(result){ 
			    var StatusCode = result.StatusCode;
			    var message = result.message;
			   // alert(result);
			if(StatusCode == "500"){
				alert(message);
				return;
			}else if(StatusCode == "200"){
				
				//alert(message);
				ShowMessage(message);
			}  
			   }, 
			   error:function(err){ 
			    console.log("NG:"+err); 
			   } 
		}) 
	}
	
	function ShowMessage(result) {
		//賬號
		var Account = $("#exampleInputAccountS").val();
		//姓名
		var Name = $("#exampleInputName").val();
		//部門
		var Deptid = $("#exampleInputDepId").val();
		//舊密碼
		var OldPassWord= $("#exampleInputOldPassWord").val();
		//新密碼
		var NewPassWord = $("#exampleInputNewPassWord").val();
		//確認新密碼  exampleInputNewPassWord1
		var NewPassWord1 = $("#exampleInputNewPassWord1").val();
		var obj = JSON.parse(result);
		for(var i=0;i<obj.length;i++){
			var username = obj[i].USERNAME;
			var password = obj[i].PASSWORD;
			var chinesename = obj[i].CHINESENAME;
			var departmentcode = obj[i].DEPARTMENTCODE;
			//名字輸入錯誤
			if (Name != chinesename) {
				alert("姓名輸入錯誤.請重新輸入!!");
				return;
			}else if (Deptid != departmentcode) {
				alert("部門輸入錯誤.請重新輸入!!");
				return;
			}else if (OldPassWord != password) {
				alert("密碼輸入錯誤.請重新輸入!!");
				return;
			}else{
				
				updatePassword(NewPassWord,NewPassWord1);
			}
			
//			//部門輸入錯誤
//			if (Deptid != departmentcode) {
//				alert("部門輸入錯誤.請重新輸入!!");
//				return;
//			}
//			//舊密碼輸入錯誤
//			if (OldPassWord != password) {
//				alert("密碼輸入錯誤.請重新輸入!!");
//				return;
//			}
			
			
		}
		//$(".bottom").html('');
		
	}



	//修改密碼
	function  updatePassword(NewPassWord,NewPassWord1) {
		
		$.ajax({ 
			   url:"ResetPassword/CheckNewPassword", 
			   type:"POST",
			   async: false,
			   //dataType:"text",
			   data:{"str2V":NewPassWord,"newPassword":NewPassWord1},
			   success:function(result){ 
			     var StatusCode = result.StatusCode;
		         var message = result.message;  
		         if(StatusCode == "500"){
						alert(message);
						return;
					}else if(StatusCode == "200"){
						
						ShowUpdateMessage(message);
					}  
			   }, 
			   error:function(err){ 
			    console.log("NG:"+err); 
			   } 
		}) 
	}
	//密碼更新
	function  ShowUpdateMessage(result) {
		//新密碼
		var NewPassWord = $("#exampleInputNewPassWord").val();
		//確認新密碼  exampleInputNewPassWord1
		var NewPassWord1 = $("#exampleInputNewPassWord1").val();
		//舊密碼
		var OldPassWord= $("#exampleInputOldPassWord").val();
		//alert(NewPassWord);
		if (NewPassWord == OldPassWord ) {
			alert("舊密碼同新密碼一樣,請重新輸入!!!");
			return;
		}
		if (NewPassWord != NewPassWord1) {
			alert("兩次輸入的密碼不一致,請重新輸入!!!");
			$("#reset_Password").attr({"disabled":"disabled"})
		}else if(NewPassWord == NewPassWord1){
			alert("密碼更新成功,請重新登錄");
		}
			
		
	}
});