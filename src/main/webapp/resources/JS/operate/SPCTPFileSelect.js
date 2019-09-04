$(function(){
    // 执行代码
	var vaRetrun;
	//showCTPSpec();
	showDefaultPro_Name(); 
	$('#CTP_Select').click(function(){
		alert("点击搜寻");
		
		var ProName1 = $("#project-bd").val();
		if(ProName1!=""){
			showCTPSpec();
			//默認顯示數據庫中存在的專案名稱
		}else{
			alert("NG:專案名稱不能爲空！");
		}
		
		
	});
	
	function showDefaultPro_Name() {
		$.ajax({
			url:"../SelectSpcCTP/ShowCTPNameSpec",
			type:"POST",
			async : false,
			data:{"str2V":null},
			success:function(result){ 
				var StatusCode = result.StatusCode;
				var message = result.message;
				if(StatusCode == "500"){
					alert(message);
				}else if(StatusCode == "200"){
					ShowCTPNameList(message);
				}
			}, 
			error:function(err){ 
				 alert("NG:"+err+"顯示錯誤"); 
			} 
		})
	}
	function showCTPSpec(){
		var strProNumberAll = $("#project-bd").val();
		//alert(strProNumberAll);
		//var str2V=strProNumberAll.substr(0,strProNumberAll.indexOf('.'));
		
		$.ajax({
			url:"../SelectSpcCTP/ShowCTPSpec",
			type:"POST",
			async : false,
			data:{"str2V":strProNumberAll},
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
				 alert("NG:"+err+"顯示錯誤"); 
			} 
		})
	}
	function ShowSpecList(result){
		/*var obj = $.parseJSON(result)*/
		var obj = JSON.parse(result)
		$(".bottom").html('');
		/*console.log(obj[0].WorkShop);*/
		var ShowTable = '';
		ShowTable += "<table class='table table-hover table-bordered show-table' id='linkManageTable'><thead><tr><th>專案名稱</th><th>工站號碼</th><th>工站名稱</th><th>設備名稱</th><th>檢驗項目</th><th>上限</th><th>下限</th><th>檢測型態</th><th>機台型號</th><th>備註</th><th>上傳者</th><th>上傳時間</th></tr></thead><tbody>";
		for(var i=0;i<obj.length;i++){
			//ID,PROJECT_NAME,WORKSHOP,WORKSHOP_NAME,MACHINE_NAME,INSPECTION_ITEM,UPPER_DIM,LOWER_DIM,INSPECTION_TYPE,MACHINE_TYPE,REMARK,PERSONNEL_ID,DATE_TIME
			ShowTable+='<tr><td>'+obj[i].PROJECT_NAME+'</td>'
					  +'<td>'+obj[i].WORKSHOP+'</td>'
					  +'<td>'+obj[i].WORKSHOP_NAME+'</td>'
					  +'<td>'+obj[i].MACHINE_NAME+'</td>'
					  +'<td>'+obj[i].INSPECTION_ITEM+'</td>'
					  +'<td>'+obj[i].UPPER_DIM+'</td>'
					  +'<td>'+obj[i].LOWER_DIM+'</td>'
					  +'<td>'+obj[i].INSPECTION_TYPE+'</td>'
					  +'<td>'+obj[i].MACHINE_TYPE+'</td>'
					  +'<td>'+obj[i].REMARK+'</td>'
					  +'<td>'+obj[i].PERSONNEL_ID+'</td>'
					  +'<td>'+obj[i].DATE_TIME+'</td></tr>'
					  //+'<td>'+obj[i].REMARK+'</td></tr>'
					  
		}
		ShowTable+="</tbody></table>";
		$(".bottom").append(ShowTable);
		
	}
	
	function ShowCTPNameList(result){
		/*var obj = $.parseJSON(result)*/
		var obj = JSON.parse(result)
		$(".bottom").html('');
		/*console.log(obj[0].WorkShop);*/
		var ShowTable = '';
		ShowTable += "<table class='table table-hover table-bordered show-table' id='linkManageTable' ><thead><tr><th  style='text-align: center; color: black;background-color:#dddddd;' colspan='5'>專案名稱</th></tr></thead><tbody style='text-align: center;'><tr>";
		for(var i=0;i<obj.length;i++){
			//ID,PROJECT_NAME,WORKSHOP,WORKSHOP_NAME,MACHINE_NAME,INSPECTION_ITEM,UPPER_DIM,LOWER_DIM,INSPECTION_TYPE,MACHINE_TYPE,REMARK,PERSONNEL_ID,DATE_TIME
			ShowTable+='<td>'+obj[i].PROJECT_NAME+'</td>'
				if (i%4==0&&i!=0) {
					ShowTable+='<tr></tr>'
				}	 
					  
		}
		ShowTable+="</tr></tbody></table>";
		$(".bottom").append(ShowTable);
		
	}
});