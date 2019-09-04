$(document).ready(function(){
	var vaRetrun;
	$("#Submit_Upload").click(function(){
		if($('#OPTest-FileUp').val().length!=0){
			 var fileName = $('#OPTest-FileUp').val();
			 var fileNameExtension = fileName.substr(fileName.lastIndexOf('.'));
			 if(fileNameExtension==".xls"||fileNameExtension==".xlsx"){
				 checkProNumberExist();
				 if(vaRetrun=='Y'){
					 if(confirm("此專案規格已經存在!!是否要複寫？")){
						 upfileAjax();
						showOPTestSpec();
					 }
				 }else if(vaRetrun=='N'){
					 upfileAjax();
					 showOPTestSpec();
				 }else{
					 alert("NG:上傳檔案異常！");
				 }
			 }else{
				 alert("NG:请选择Excel文件！");
				 return false;
			 }
		}else{
			alert("NG:未選擇任何Excel文件！");
			return false;
		}
	});
	
	function checkProNumberExist(){
		var strProNumberAll = $("#OPTest-FileUp").val();
		/*alert(strProNumberAll);*/
		var str2V=strProNumberAll.substr(0,strProNumberAll.indexOf('.'));
	/*	alert(str2V);*/
		$.ajax({ 
			   url:"../UploadSpcOPTest/checkProName.do", 
			   type:'post',
			   async : false,
			   data:{"str2V":str2V},
			   success:function(res){ 
			     vaRetrun=res;
			   }, 
			   error:function(err){ 
			    alert("NG:"+err); 
			   } 
			  
		})   
	}
	
	function upfileAjax(){
		var form = document.getElementById('form1');
		var formData = new FormData(form);
		/*console.log(formData.get('file'));*/
		  $.ajax({ 
		   url:"../UploadSpcOPTest/ajaxUploadOPTestSpec.do", 
		   type:'POST', 
		   data:formData,
		   async : false,
		   processData:false, 
		   contentType:false, 
		   success:function(res){ 
			  if(res){ 
			    alert(res); 
			  } 
		   }, 
		   error:function(err){ 
		    alert('错误：'+err); 
		   } 
		  
		  }) 
	}
	
	function showOPTestSpec(){
		var strProNumberAll = $("#OPTest-FileUp").val();
		var str2V=strProNumberAll.substr(0,strProNumberAll.indexOf('.'));
		$.ajax({
			url:"../UploadSpcOPTest/showOPTestSpec",
			type:"post",
			data:{"str2V":str2V},
			success:function(result){
				var StatusCode = result.StatusCode;
				var message = result.message;
				if(StatusCode=="500"){
					alert(message);
				}else if(StatusCode=="200"){
					ShowSpecList(message);
				}
			},
			error:function(err){
				alert("NG:"+err); 
			}
		})
	}
	
	function ShowSpecList(result){
var obj = JSON.parse(result)
		
		$(".bottom").html('');
		/*console.log(obj[0].WorkShop);*/
		var ShowTable = '';
		ShowTable += "<table class='table table-hover table-bordered show-table' id='linkManageTable'><thead><tr><th>測試序號</th><th>測試類別</th><th>測試階段</th><th>工站名稱</th><th>測試内容</th></tr></thead><tbody>";
		for(var i=0;i<obj.length;i++){
			ShowTable+='<tr><td>'+obj[i].TEST_ITEM+'</td>'
					  +'<td>'+obj[i].TEST_CLASS+'</td>'
					  +'<td>'+obj[i].TEST_STATUS+'</td>'
					  +'<td>'+obj[i].WORK_STATION+'</td>'
					  +'<td>'+obj[i].TEST_CONTENT+'</td></tr>'
		}
		ShowTable+="</tbody></table>";
		$(".bottom").append(ShowTable);
	}
})