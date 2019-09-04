$(document).ready(function(){
	var vaRetrun;
	$("#Submit_Upload").click(function(){
		if($('#TOOL-FileUp').val().length!=0){
			 var fileName = $('#TOOL-FileUp').val();
			 var fileNameExtension = fileName.substr(fileName.lastIndexOf('.'));
			 if(fileNameExtension==".xls"||fileNameExtension==".xlsx"){
				 checkProNumberExist();
				 if(vaRetrun=='Y'){
					 if(confirm("此專案規格已經存在!!是否要複寫？")){
						 upfileAjax();
						 showToolSpec();
					 }
				 }else if(vaRetrun=='N'){
					 upfileAjax();
					 showToolSpec();
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
		var strProNumberAll = $("#TOOL-FileUp").val();
		/*alert(strPartNumberAll);*/
		var str2V=strProNumberAll.substr(0,strProNumberAll.indexOf('.'));
	/*	alert(str2V);*/
		$.ajax({ 
			   url:"../UploadSpcTOOL/checkProName.do", 
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
		   url:"../UploadSpcTOOL/ajaxUploadToolSpec.do", 
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

	function showToolSpec(){
		var strProNumberAll = $("#TOOL-FileUp").val();
		var str2V=strProNumberAll.substr(0,strProNumberAll.indexOf('.'));
		$.ajax({
			url:"../UploadSpcTOOL/ShowToolSpec",
			type:"POST",
			data:{"str2V":str2V},
			success:function(result){ 
				var StatusCode = result.StatusCode;
				var message = result.message;
				if(StatusCode == "500"){
					alert(message);
				}else if(StatusCode == "200"){
					ShowSpecList(message);
				}
			}, 
			error:function(err){ 
				 alert("NG:"+err); 
			} 
		})
	}
	function ShowSpecList(result){
		/*var obj = $.parseJSON(result)*/
		var obj = JSON.parse(result)
		$(".bottom").html('');
		/*console.log(obj[0].WorkShop);*/
		var ShowTable = '';
		ShowTable += "<table class='table table-hover table-bordered show-table' id='linkManageTable'><thead><tr><th>點檢編號</th><th>點檢項目</th><th>設備編號</th><th>點檢内容</th><th>頻率</th></tr></thead><tbody>";
		for(var i=0;i<obj.length;i++){
			ShowTable+='<tr><td>'+obj[i].INSPECTION_ITEM+'</td>'
					  +'<td>'+obj[i].INSPECTION_TYPE+'</td>'
					  +'<td>'+obj[i].DEVICE_NUM+'</td>'
					  +'<td>'+obj[i].INSPECTION_CONTENT+'</td>'
					  +'<td>'+obj[i].FREQUENCY+'</td></tr>'
		}
		ShowTable+="</tbody></table>";
		$(".bottom").append(ShowTable);
	}

})