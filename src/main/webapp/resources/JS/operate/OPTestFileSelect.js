$(document).ready(function(){
	ShowProName();
	$("#Submit_Select").bind("click",function(){
		var ProName1 = $("#project-bd").val();
		if(ProName1!=""){
			ShowSpec();
		}else{
			alert("NG:專案名稱不能爲空！");
		}
	}) 
})

	function ShowSpec(){
		var ProName1 = $("#project-bd").val();
		$.ajax({
			url:"../SelectSpcOPTest/ShowProName",
			type:"post",
			async:false,
			data:{"ProName":ProName1},
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
		ShowTable += "<table class='table table-hover table-bordered show-table' id='linkManageTable'><thead><tr><th>測試類別</th><th>測試階段</th><th>測試序號</th><th>工站名稱</th><th>測試内容</th><th>上傳時間</th><th>上傳者</th></tr></thead><tbody>";
		for(var i=0;i<obj.length;i++){
			ShowTable+='<tr><td>'+obj[i].TEST_CLASS+'</td>'
					  +'<td>'+obj[i].TEST_STATUS+'</td>'
					  +'<td>'+obj[i].TEST_ITEM+'</td>'
					  +'<td>'+obj[i].WORK_STATION+'</td>'
					  +'<td>'+obj[i].TEST_CONTENT+'</td>'
					  +'<td>'+obj[i].DATETIME+'</td>'
					  +'<td>'+obj[i].PERSONNEL_ID+'</td></tr>'
		}
		ShowTable+="</tbody></table>";
		$(".bottom").append(ShowTable);
	}
	
	function ShowProName(){	
		$.ajax({
			url:"../SelectSpcOPTest/ShowProNameList",
			type:"post",
			async:false,
			success:function(result){
				var StatusCode = result.StatusCode;
				var message = result.message;
				if(StatusCode=="500"){
					alert(message);
				}else if(StatusCode=="200"){
					ShowProNameList(message);
				}
			},
			error:function(err){
				alert("NG:"+err);
			}
		})
		
		
	}
	
	function ShowProNameList(result){
		var obj = JSON.parse(result)
		$(".bottom").html('');
		/*console.log(obj[0].WorkShop);*/
		var ShowTable = '';
		ShowTable += "<table class='table table-hover table-bordered show-table' id='linkManageTable'><thead><tr><th colspan='10' style='text-align:center;background-color:#dddddd;'>專案名稱</th></tr></thead><tbody><tr>";
		for(var i=0;i<obj.length;i++){
			ShowTable+="<td style='width:10%;'>"+obj[i].PROJECT_NAME+"</td>";
			if(i%9==0&&i!=0){
				ShowTable+='</tr><tr>';
			}
		}
		ShowTable+="</tr></tbody></table>";
		
		$(".bottom").append(ShowTable);
	}