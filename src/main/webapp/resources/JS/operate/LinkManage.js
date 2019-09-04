$(document).ready(function(){
	var Factory_array = new Array('FQ1', 'FQ2', 'LK', 'MS');
	var Floor_array1  = new Array('3F');
	var Floor_array2  = new Array('5F');
	var Floor_array3  = new Array('A2F', 'A3F', 'A4F', 'A5F', 'B2F', 'B3F', 'B4F', 'B5F');
	var Floor_array4  = new Array('B4F', 'C3F');
	var Group_array1  = new Array('FQ一期電子產品課');
	var Group_array2  = new Array('FQ二期生產一課', 'FQ二期生產二課');
	var Group_array3  = new Array('龍坑生產一課', '龍坑生產二課', '龍坑生產三課', '龍坑生產四課', '龍坑生產五課', '龍坑生產六課');
	var Group_array4  = new Array('馬鞍山生產一課', '馬鞍山生產二課');
	initselect();
	initAllLink();
	
	
	function initselect(){
		$("#fl-Factory").find("option:selected").text("");
		$("#fl-Factory").empty();
		for(var i = 0;i<Factory_array.length;i++){
			$("#fl-Factory").append("<option>"+Factory_array[i]+"</option>");
		}
		
		$("#fl-Floor").find("option:selected").text("");
		$("#fl-Floor").empty();
		$("#fl-Floor").append("<option>"+Floor_array1[0]+"</option>");
		
		$("#fl-PrdU").find("option:selected").text("");
		$("#fl-PrdU").empty();
		$("#fl-PrdU").append("<option>"+Group_array1[0]+"</option>");
		
		$("#fl-Link").val("FA-");
	}
	
	function initAllLink(){
		$.ajax({
			   url:"../LinkManage/ShowAllLink", 
			   type:'post',
			   success:function(result){
				var StatusCode = result.StatusCode;
				var m = result.message;
				if(StatusCode=="500"){
					alert(m);
				}else if(StatusCode=="200"){
					showAllLinkManage(m);
				}
			   }, 
			   error:function(err){ 
			    alert("查詢所有線別失敗，失敗原因："+err); 
			   } 
		}) 
	}
	
	function showAllLinkManage(result){
		var obj = eval(result); 
		$('#linkManageTable tbody').html('');
		for(var i = 0;i<obj.length;i++){
			var	tableContents="";
			tableContents += '<tr><td>'+obj[i].FACTORY+'</td>'
				+'<td>'+obj[i].FLOOR+'</td>'
				+'<td>'+obj[i].LINE_NUMBER+'</td>'
				+'<td>'+obj[i].DEPT_CODE+'</td>'
				+'<td>'+obj[i].WECHAT_GROUP+'</td>'
				+'<td>'+obj[i].PRODUCTION_DEPT+'</td>'
				+'<td><input type="button" value="刪除" class="deleteBtn btn btn-xs btn-link"></td></tr>';
			
			$('#linkManageTable tbody').append(tableContents);
		}
		$(".deleteBtn").click(function(){
			var Factory = $(this).parents('tr').find('td').eq(0).text();
			var Line_Number = $(this).parents('tr').find('td').eq(2).text();
			$.ajax({
				   url:"../LinkManage/DeleteLink", 
				   type:'post',
				   async:false, 
				   data:{Factory:Factory,Line_Number:Line_Number},
				   success:function(result){
					var StatusCode = result.StatusCode;
					var m = result.message;
					if(StatusCode=="500"){
						alert(m);
					}else if(StatusCode=="200"){
						alert(m);
					}
				   }, 
				   error:function(err){ 
				    alert("刪除線別失敗，失敗原因："+err.status);
				   } 
			})
			initAllLink();
		});
	}
	
	$("#fl-Factory").change(function(){
		var Factory = $(this).children('option:selected').val(); 
		if(Factory == 'FQ1'){
			$("#fl-Floor").find("option:selected").text("");
			$("#fl-Floor").empty();
			$("#fl-Floor").append("<option>"+Floor_array1[0]+"</option>");
			
			$("#fl-PrdU").find("option:selected").text("");
			$("#fl-PrdU").empty();
			$("#fl-PrdU").append("<option>"+Group_array1[0]+"</option>");
			
			$("#fl-Link").val("FA-");
		}else if(Factory == 'FQ2'){
			
			$("#fl-Floor").find("option:selected").text("");
			$("#fl-Floor").empty();
			$("#fl-Floor").append("<option>"+Floor_array2[0]+"</option>");
			
			$("#fl-PrdU").find("option:selected").text("");
			$("#fl-PrdU").empty();
			for(var i = 0;i<Group_array2.length;i++){
				$("#fl-PrdU").append("<option>"+Group_array2[i]+"</option>");
			}
			
			$("#fl-Link").val("FA-");
		}else if(Factory == 'LK'){
			
			$("#fl-Floor").find("option:selected").text("");
			$("#fl-Floor").empty();
			for(var i = 0;i<Floor_array3.length;i++){
				$("#fl-Floor").append("<option>"+Floor_array3[i]+"</option>");
			}
			
			$("#fl-PrdU").find("option:selected").text("");
			$("#fl-PrdU").empty();
			$("#fl-PrdU").append("<option>"+Group_array3[1]+"</option>");
			
			$("#fl-Link").val("LA-");
		}else if(Factory == 'MS'){
			$("#fl-Floor").find("option:selected").text("");
			$("#fl-Floor").empty();
			for(var i = 0;i<Floor_array4.length;i++){
				$("#fl-Floor").append("<option>"+Floor_array4[i]+"</option>");
			}
			
			$("#fl-PrdU").find("option:selected").text("");
			$("#fl-PrdU").empty();
			$("#fl-PrdU").append("<option>"+Group_array4[0]+"</option>");
			
			$("#fl-Link").val("MSA-");
		}
		
	})
	
	$("#fl-Floor").change(function(){
		var Factory = $("#fl-Factory").children('option:selected').val();
		var Floor = $(this).children('option:selected').val();
		if(Factory == 'LK' && Floor == 'A2F'){
			$("#fl-PrdU").find("option:selected").text("");
			$("#fl-PrdU").empty();
			$("#fl-PrdU").append("<option>"+Group_array3[1]+"</option>");
			$("#fl-Link").val("LA-");
		}else if(Factory == 'LK' && (Floor == 'A3F' || Floor == 'A4F')){
			$("#fl-PrdU").find("option:selected").text("");
			$("#fl-PrdU").empty();
			$("#fl-PrdU").append("<option>"+Group_array3[4]+"</option>");
			$("#fl-Link").val("LA-");
		}else if(Factory == 'LK' && Floor == 'A5F'){
			$("#fl-PrdU").find("option:selected").text("");
			$("#fl-PrdU").empty();
			$("#fl-PrdU").append("<option>"+Group_array3[0]+"</option>");
			$("#fl-Link").val("LA-");
		}else if(Factory == 'LK' && (Floor == 'B2F' || Floor == 'B5F')){
			$("#fl-PrdU").find("option:selected").text("");
			$("#fl-PrdU").empty();
			$("#fl-PrdU").append("<option>"+Group_array3[2]+"</option>");
			$("#fl-Link").val("LB-");
		}else if(Factory == 'LK' && Floor == 'B3F'){
			$("#fl-PrdU").find("option:selected").text("");
			$("#fl-PrdU").empty();
			$("#fl-PrdU").append("<option>"+Group_array3[5]+"</option>");
			$("#fl-Link").val("LB-");
		}else if(Factory == 'LK' && Floor == 'B4F'){
			$("#fl-PrdU").find("option:selected").text("");
			$("#fl-PrdU").empty();
			$("#fl-PrdU").append("<option>"+Group_array3[3]+"</option>");
			$("#fl-Link").val("LB-");
		}else if(Factory == 'MS' && Floor == 'B4F'){
			$("#fl-PrdU").find("option:selected").text("");
			$("#fl-PrdU").empty();
			$("#fl-PrdU").append("<option>"+Group_array4[0]+"</option>");
			$("#fl-Link").val("MSA-");
		}else if(Factory == 'MS' && Floor == 'C3F'){
			$("#fl-PrdU").find("option:selected").text("");
			$("#fl-PrdU").empty();
			$("#fl-PrdU").append("<option>"+Group_array4[1]+"</option>");
			$("#fl-Link").val("MSB-");
		}
	})
	
	$("#addLink").click(function(){
		var Factory,Floor,Line_Number,Dept_Code,Production_Dept,Wechat_Group;
		var info = '';
		Factory = $("#fl-Factory").find("option:selected").val();
		Floor = $("#fl-Floor").find("option:selected").val();
		Production_Dept = $("#fl-PrdU").find("option:selected").val();
		Line_Number = $("#fl-Link").val();
		if(Factory=='' || Floor == '' || Production_Dept == '' || Line_Number == ''){
			info += '請填入相應信息\t\n';
		}
		if (Production_Dept == '龍坑生產一課') {
            Dept_Code      = '3703';
            Wechat_Group    = 'SCBG_ICBU_CABLE_IPQC_LK_1';
        } else if (Production_Dept == '龍坑生產二課') {
            Dept_Code       = '3703';
            Wechat_Group    = 'SCBG_ICBU_CABLE_IPQC_LK_2';
        } else if (Production_Dept == '龍坑生產三課') {
            Dept_Code       = '3686';
            Wechat_Group    = 'SCBG_ICBU_CABLE_IPQC_LK_3';
        } else if (Production_Dept == '龍坑生產四課') {
            Dept_Code       = '3686';
            Wechat_Group    = 'SCBG_ICBU_CABLE_IPQC_LK_4';
        } else if (Production_Dept == '龍坑生產五課') {
            Dept_Code       = '3703';
            Wechat_Group    = 'SCBG_ICBU_CABLE_IPQC_LK_5';
        } else if (Production_Dept == '龍坑生產六課') {
            Dept_Code       = '3686';
            Wechat_Group    = 'SCBG_ICBU_CABLE_IPQC_LK_6';
        } else if (Production_Dept == 'FQ一期電子產品課') {
            Dept_Code       = '8802';
            Wechat_Group    = 'SCBG_ICBU_CABLE_IPQC_FQ1_1';
        } else if (Production_Dept == 'FQ二期生產一課') {
            Dept_Code       = '8802';
            Wechat_Group    = 'SCBG_ICBU_CABLE_IPQC_FQ2_1';
        } else if (Production_Dept == 'FQ二期生產二課') {
            Dept_Code       = '8802';
            Wechat_Group    = 'SCBG_ICBU_CABLE_IPQC_FQ2_2';
        } else if (Production_Dept == '馬鞍山生產一課') {
            Dept_Code       = '5876';
            Wechat_Group    = 'SCBG_ICBU_CABLE_IPQC_MS_1';
        } else if (Production_Dept == '馬鞍山生產二課') {
            Dept_Code       = '5877';
            Wechat_Group    = 'SCBG_ICBU_CABLE_IPQC_MS_2';
        } else {
        	info += '找不到對應生產單位，請重新確認\t\n';
        }
		
		if(info != ''){
			alert(info);
		}else{
			//Factory,Floor,Line_Number,Dept_Code,Production_Dept,Wechat_Group
			$.ajax({
				   url:"../LinkManage/AddLink", 
				   type:'post',
				   async:false, 
				   data:{Factory:Factory,Floor:Floor,Line_Number:Line_Number,
					   Dept_Code:Dept_Code,Production_Dept:Production_Dept,Wechat_Group:Wechat_Group},
				   success:function(result){
					var StatusCode = result.StatusCode;
					var m = result.message;
					if(StatusCode=="500"){
						alert(m);
					}else if(StatusCode=="200"){
						alert(m);
					}
				   }, 
				   error:function(err){ 
				    alert("添加線別失敗，失敗原因："+err.status);
				   } 
			})
			initAllLink();
		}
		
	})
	
})