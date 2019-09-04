$(document).ready(function(){
	$("#Submit_Select").click(function(){
		var ProName1 = $("#project-bd").val();
		if(ProName1!=""){
			ShowSpec();
		}else{
			alert("NG:專案名稱不能爲空！");
			/*ShowProName();*/
		}
	})
	
	ShowProName();
})

	function ShowSpec(){
		var ProName = $("#project-bd").val();
		$.ajax({
			url:"../SelectSpcTOOL/ShowProName",
			type:"post",
			async:false,
			data:{"ProName":ProName},
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
		});
	}

	function ShowSpecList(result){
		var obj = JSON.parse(result)
		
		$(".bottom").html('');
		/*console.log(obj[0].WorkShop);*/
		var ShowTable = '';
		ShowTable += "<table class='table table-hover table-bordered show-table' id='linkManageTable'><thead><tr><th>點檢編號</th><th>點檢項目</th><th>設備編號</th><th>點檢内容</th><th>頻率</th><th>上傳時間</th><th>上傳者</th></tr></thead><tbody>";
		for(var i=0;i<obj.length;i++){
			ShowTable+='<tr><td>'+obj[i].INSPECTION_ITEM+'</td>'
					  +'<td>'+obj[i].INSPECTION_TYPE+'</td>'
					  +'<td>'+obj[i].DEVICE_NUM+'</td>'
					  +'<td>'+obj[i].INSPECTION_CONTENT+'</td>'
					  +'<td>'+obj[i].FREQUENCY+'</td>'
					  +'<td>'+obj[i].DATETIME+'</td>'
					  +'<td>'+obj[i].PERSONNEL_ID+'</td></tr>'
					  
		}
		ShowTable+="</tbody></table>";
		$(".bottom").append(ShowTable);
	}
	
	function ShowProName(){	
		$.ajax({
			url:"../SelectSpcTOOL/ShowProNameList",
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