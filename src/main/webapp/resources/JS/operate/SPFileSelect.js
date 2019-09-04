$(document).ready(function(){
	//ShowProName();
	
	showDefaultPro_Name();
	$("#SPC_Select").bind("click",function(){
		//alert("點擊搜尋");
		var ProName1 = $("#partNo-bd").val();
		if(ProName1!=""){
			showSpcSpec();
		}else{
			alert("NG:專案名稱不能爲空！");
		}
	}) ;
	
	
	//顯示數據庫已經有的專案
	function showDefaultPro_Name() {
		$.ajax({
			url:"../SelectSpec/ShowSpcNameSpec",
			type:"POST",
			async : false,
			data:{"str2V":null},
			success:function(result){ 
				var StatusCode = result.StatusCode;
				var message = result.message;
				if(StatusCode == "500"){
					alert(message);
				}else if(StatusCode == "200"){
					ShowSpcNameList(message);
				}
			}, 
			error:function(err){ 
				 alert("NG:"+err+"顯示錯誤"); 
			} 
		})
	}
	
	//顯示列表
	function showSpcSpec(){
		var strProNumberAll = $("#partNo-bd").val();
		//alert(strProNumberAll);
		//var str2V=strProNumberAll.substr(0,strProNumberAll.indexOf('.'));
		
		$.ajax({
			url:"../SelectSpec/ShowSpcSpec",
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
		ShowTable += "<table class='table table-hover table-bordered show-table' id='linkManageTable'><thead><tr><th>專案名稱</th><th>FAI/工站號</th><th>檢驗項目</th><th>檢驗內容</th><th>尺寸</th><th>上限</th><th>下限</th><th>頻率</th><th>檢驗階段</th><th>檢驗方式</th><th>備注</th><th>SPC尺寸號</th><th>SPC尺寸位子</th><th>上傳者</th><th>上傳時間</th></tr></thead><tbody>";
		for(var i=0;i<obj.length;i++){
			ShowTable+='<tr><td>'+obj[i].PROJECT_NAME+'</td>'
				      +'<td>'+obj[i].WorkShop+'</td>'
					  +'<td>'+obj[i].Inspection_Item+'</td>'
					  +'<td>'+obj[i].INSPECTION_CONTENT+'</td>'
					  +'<td>'+obj[i].Nominal_Dim+'</td>'
					  +'<td>'+obj[i].Upper_Dim+'</td>'
					  +'<td>'+obj[i].Lower_Dim+'</td>'
					  +'<td>'+obj[i].Frequency+'</td>'
					  +'<td>'+obj[i].Status+'</td>'
					  +'<td>'+obj[i].INSPECTION_METHOD+'</td>'
			
					  
			var Remarks = obj[i].Remark1!=null?obj[i].Remark1:' ';
			var Spc_num = obj[i].SPC_NUM!=null?obj[i].SPC_NUM:'';
			var Dim_location = obj[i].DIM_LOCATION!=null?obj[i].DIM_LOCATION:'';
			
			ShowTable+='<td>'+Remarks+'</td>'
			 +'<td>'+Spc_num+'</td>'
			 +'<td>'+Dim_location+'</td>'
			 +'<td>'+obj[i].PERSONNEL_ID+'</td>'
			 +'<td>'+obj[i].DATE_TIME+'</td></tr>'
			 
		}
		ShowTable+="</tbody></table>";
		
		$(".bottom").append(ShowTable);
	}
	//顯示專案名稱
	function ShowSpcNameList(result){
		/*var obj = $.parseJSON(result)*/
		var obj = JSON.parse(result)
		$(".bottom").html('');
		/*console.log(obj[0].WorkShop);*/
		var ShowTable = '';
		ShowTable += "<table class='table table-hover table-bordered show-table' id='linkManageTable' ><thead><tr><th  style='text-align: center; color: black;background-color:#dddddd;' colspan='5'>料號名稱</th></tr></thead><tbody style='text-align: center;'><tr>";
		for(var i=0;i<obj.length;i++){
			//ID,PROJECT_NAME,WORKSHOP,WORKSHOP_NAME,MACHINE_NAME,INSPECTION_ITEM,UPPER_DIM,LOWER_DIM,INSPECTION_TYPE,MACHINE_TYPE,REMARK,PERSONNEL_ID,DATE_TIME
			ShowTable+='<td>'+obj[i].PART_NUMBER_V+'</td>'
				if (i%4==0&&i!=0) {
					ShowTable+='<tr></tr>'
				}	 
					  
		}
		ShowTable+="</tr></tbody></table>";
		$(".bottom").append(ShowTable);
		
	}
});