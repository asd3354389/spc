$(function(){
	
	$("#launch").click(function(){
		
		//alert(account);
		selectId();
	});
	//查詢工號
	function selectId() {
		var account = $("#Account").val();
		//alert(account);
		$.ajax({ 
			   url:"../SendMail/SelectAccount", 
			   type:"POST",
			   async: false,
			   data:{"str2V":account},
			   success:function(result){ 
			    var StatusCode = result.StatusCode;
			    var message = result.message;
			if(StatusCode == "500"){
				alert(message);
			}else if(StatusCode == "200"){
				//alert(account);
				//ShowMessage(message);
				SendMail();
			}  
			   }, 
			   error:function(err){ 
			    console.log("NG:"+err); 
			   } 
		
		}) 
	
	}
	
	function SendMail() {
		$.ajax({ 
			   url:"../SendMail/SendMailPassword", 
			   type:"POST",
			   async: false,
			   success:function(result){ 
			    var StatusCode = result.StatusCode;
			    var message = result.message;
			if(StatusCode == "500"){
				alert(message);
			}else if(StatusCode == "200"){
				alert(message);
			
			}  
			   }, 
			   error:function(err){ 
			    console.log("NG:"+err); 
			   } 
		
		}) 
	
		
			
	}
});