$(document).ready(function(){
	

	 if (window != top) {    
	        top.location.href = location.href;    
	    }	 
	 
	var COOKIE_NAME = 'username';
	if($.cookie(COOKIE_NAME)){
		$(".username").val($.cookie(COOKIE_NAME));
	}
	$("#check").click(function(){
		if(this.checked){
			$.cookie(COOKIE_NAME,$('.username').val(),{path:'/',expires:7});
		}else{
			$.cookie(COOKIE_NAME,null,{path:'/'});
		}
	})
	
})