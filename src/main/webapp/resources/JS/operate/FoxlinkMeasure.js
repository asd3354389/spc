var start,counts;
$(document).ready(function(){
	var reg=/^[0-9]+.?[0-9]*$/;
	init();
	getPart('');
	
	function init(){
		$.ajax({
			   url:"../Measure/ShowAllFactoryAndProjectName", 
			   type:'post',
			   success:function(result){
				var Factory = result.Factory;
				var ProjectName = result.ProjectName;
				
				if(Factory == null || Factory == ''){
					alert("查無廠區資料");
				}else{
					var obj = eval(Factory);
					var selectContext = '<option></option>';
					for(var i = 0;i<obj.length;i++){
						selectContext+="<option value = \""+obj[i].FACTORY+"\">"+obj[i].FACTORY+"</option>";
					}
					$('#fl-Factory').html(selectContext);
				}
				
				if(ProjectName == null || ProjectName == ''){
					alert("查無廠區資料");
				}else{
					var obj = eval(ProjectName);
					var selectContext = '<option></option>';
					for(var i = 0;i<obj.length;i++){
						selectContext+="<option value = \""+obj[i].PROJECT_NAME+"\">"+obj[i].PROJECT_NAME+"</option>";
					}
					$('#pro-Name').html(selectContext);
				}
			   }, 
			   error:function(err){ 
			    alert("查詢所有廠區失敗，失敗原因："+err); 
			   } 
		}) 
	}
	
	$('#fl-Factory').on('change',function(){
		var Factory = $(this).val();
		if(Factory == ''){
			$('#fl-Link').html();
		}else{
			$.ajax({
				   url:"../Measure/ShowLink", 
				   type:'post',
				   data:{Factory:Factory},
				   success:function(result){
					if(result == null || result == ''){
						alert("查無廠區資料");
					}else{
						var obj = eval(result);
						var selectContext = '<option></option>';
						for(var i = 0;i<obj.length;i++){
							selectContext+="<option value = \""+obj[i].LINE_NUMBER+"\">"+obj[i].LINE_NUMBER+"</option>";
						}
						$('#fl-Link').html(selectContext);
					}
				   }, 
				   error:function(err){ 
				    alert("查詢線別失敗，失敗原因："+err); 
				   } 
			}) 
		}
	})
	
	$('#pro-Name').on('change',function(){
		var pro_Name = $(this).val();
		var part_version = $('#part-Verion').val();
		var Stutas = $('#meas-Stage').val();
		var Frequency  =$('#meas-Hz').val();
		getPart(pro_Name);
		$('#part-Verion').find("option[value = '"+part_version+"']").attr("selected","selected");
		getStutas($('#part-Verion').val());
		$('#meas-Stage').find("option[value = '"+Stutas+"']").attr("selected","selected");
		getFrequency($('#pro-Name').val(),$('#part-Verion').val(),$('#meas-Stage').val());
		$('#meas-Hz').find("option[value = '"+Frequency+"']").attr("selected","selected");
	})
	
	$('#part-Verion').on('change',function(){
		var part_version = $(this).val();
		var Stutas = $('#meas-Stage').val();
		var Frequency  =$('#meas-Hz').val();
		getStutas(part_version);
		$('#meas-Stage').find("option[value = '"+Stutas+"']").attr("selected","selected");
		getFrequency($('#pro-Name').val(),$('#part-Verion').val(),$('#meas-Stage').val());
		$('#meas-Hz').find("option[value = '"+Frequency+"']").attr("selected","selected");
	})
	
	$('#meas-Stage').on('change',function(){
		var Stutas = $(this).val();
		var part_version = $('#part-Verion').val();
		var Frequency  =$('#meas-Hz').val();
		getFrequency($('#pro-Name').val(),$('#part-Verion').val(),Stutas);
		$('#meas-Hz').find("option[value = '"+Frequency+"']").attr("selected","selected");
	})
	
	function getPart(ProjectName){
		$.ajax({
			   url:"../Measure/ShowPartVerion", 
			   type:'post',
			   data:{ProjectName:ProjectName},
			   async:false, 
			   success:function(result){
				if(result == null || result == ''){
					$('#part-Verion').html("");
				}else{
					var obj = eval(result);
					var selectContext = '<option></option>';
					for(var i = 0;i<obj.length;i++){
						selectContext+="<option value = \""+obj[i].PART_NUMBER_V+"\">"+obj[i].PART_NUMBER_V+"</option>";
					}
					$('#part-Verion').html(selectContext);
				}
			   }, 
			   error:function(err){ 
			    alert("查詢料號版本失敗，失敗原因："+err); 
			   } 
		}) 
	}
	
	function getStutas(part_version){
		if(part_version == ''){
			$('#meas-Stage').html('');
		}else{
			$.ajax({
				   url:"../Measure/ShowStutas", 
				   type:'post',
				   data:{PartVersion:part_version},
				   async:false, 
				   success:function(result){
					if(result == null || result == ''){
						$('#meas-Stage').html("");
					}else{
						var obj = eval(result);
						var selectContext = '<option></option>';
						for(var i = 0;i<obj.length;i++){
							selectContext+="<option value = \""+obj[i].STATUS+"\">"+obj[i].STATUS+"</option>";
						}
						$('#meas-Stage').html(selectContext);
					}
				   }, 
				   error:function(err){ 
				    alert("查詢量測階段失敗，失敗原因："+err); 
				   } 
			})
		}
	}
	function getFrequency(pro_Name,part_version,Stutas){
		if(part_version == ''||Stutas == ''){
			$('#meas-Hz').html('');
		}else{
			$.ajax({
				   url:"../Measure/ShowFrequency", 
				   type:'post',
				   data:{ProjectName:pro_Name,PartVersion:part_version,Stutas:Stutas},
				   async:false, 
				   success:function(result){
					if(result == null || result == ''){
						$('#meas-Hz').html("");
					}else{
						var obj = eval(result);
						var selectContext = '<option></option>';
						for(var i = 0;i<obj.length;i++){
							selectContext+="<option value = \""+obj[i].FREQUENCY+"\">"+obj[i].FREQUENCY+"</option>";
						}
						$('#meas-Hz').html(selectContext);
					}
				   }, 
				   error:function(err){ 
				    alert("查詢量測頻率失敗，失敗原因："+err); 
				   } 
			})
		}
	}
	
	
	//提交之後
	$('#sumbitForm').on('click',function(){
		var Frequency = $('#meas-Hz').val();
		var Factory = $('#fl-Factory').val();
		var Link = $('#fl-Link').val();
		var proName = $('#pro-Name').val();
		var PartVersion = $('#part-Verion').val();
		var Status = $('#meas-Stage').val();
		var Period = $('#p-Number').val();
		var Ticket_Number = $('#input-No').val();
		var Measurement_Number = $('#meas-Mac-No').val();
		var Machine_Number = $('#mac-No').val();
		var Personnel_ID = $('#emp-No').val();
		if(PartVersion==''|| Status=='' || Frequency=='' || Period == '' || Factory =='' || Link == '' || Personnel_ID == ''){
			alert('(*)為必填欄位,請確實填寫');
		}else if(Status=='初件檢驗'&&Ticket_Number!=''){
			alert('初件檢驗量測,請不要填寫"入庫單號"');
		}else if(Status=='巡迴檢驗'&&Ticket_Number!=''){
			alert('巡迴檢驗量測,請不要填寫"入庫單號"');
		}else{
			$.ajax({
				   url:"../Measure/ShowMeasureTable", 
				   type:'post',
				   data:{Factory:Factory,Link:Link,ProjectName:proName,PartVersion:PartVersion,Status:Status,Frequency:Frequency,
					   Period:Period,TicketNumber:Ticket_Number,MeasurementNumber:Measurement_Number,MachineNumber:Machine_Number,Personnel_ID:Personnel_ID},
				   async:false, 
				   success:function(result){
					if(result.SPECStatus=="500"){
						alert("查無資料");
					}else{
						showMeasureTable(result,Frequency);
					}
				}, 
				   error:function(err){ 
				    alert("查詢量測內容失敗，失敗原因："+err); 
				   } 
			})
		}
	})
	
	function showMeasureTable(result,Frequency){
		var tableContext ='';
		var obj = eval(result.Message);
		var HIobj;
		if(result.HISPECStatus==200){
			HIobj = eval(result.HIMessage)
		}
		var MeasureSize;
		if(Frequency=='前工站'||Frequency=='32PCS_批'){
			tableContext += "<table class='table table-hover table-bordered' id='MeasureTable' style='width:2800px;margin:25px 0px 0px 1%;'><thead>" +
					"<tr><th colspan='41'>量測數據輸入</th></tr><tr>";
			MeasureSize = 32;
		}else{
			tableContext += "<table class='table table-hover table-bordered' id='MeasureTable' style='width:980px;margin:25px 0px 0px 1%;'><thead>" +
					"<tr><th colspan='13'>量測數據輸入</th></tr><tr>";
			MeasureSize = 5;
		}
		tableContext += "<th width='90px'>尺寸/工站號</th>";
		tableContext += "<th width='170px'>檢驗項目</th>";
		tableContext += "<th width='10px'>尺寸</th>";
		tableContext += "<th width='10px'>上限</th>";
		tableContext += "<th width='10px'>下限</th>";
		tableContext += "<th width='55px'>頻率</th>";
		tableContext += "<th width='55px'>備註</th>";
		tableContext += "<th width='45px'>量測-1</th>";
		tableContext += "<th width='45px'>量測-2</th>";
		tableContext += "<th width='45px'>量測-3</th>";
		tableContext += "<th width='45px'>量測-4</th>";
		tableContext += "<th width='45px'>量測-5</th>";
		if(Frequency=='前工站'||Frequency=='32PCS_批'){
			tableContext += "<th width='45px'>量測-6</th>";
			tableContext += "<th width='45px'>量測-7</th>";
			tableContext += "<th width='45px'>量測-8</th>";
			tableContext += "<th width='45px'>量測-9</th>";
			tableContext += "<th width='45px'>量測-10</th>";
			tableContext += "<th width='45px'>量測-11</th>";
			tableContext += "<th width='45px'>量測-12</th>";
			tableContext += "<th width='45px'>量測-13</th>";
			tableContext += "<th width='45px'>量測-14</th>";
			tableContext += "<th width='45px'>量測-15</th>";
			tableContext += "<th width='45px'>量測-16</th>";
			tableContext += "<th width='45px'>量測-17</th>";
			tableContext += "<th width='45px'>量測-18</th>";
			tableContext += "<th width='45px'>量測-19</th>";
			tableContext += "<th width='45px'>量測-20</th>";
			tableContext += "<th width='45px'>量測-21</th>";
			tableContext += "<th width='45px'>量測-22</th>";
			tableContext += "<th width='45px'>量測-23</th>";
			tableContext += "<th width='45px'>量測-24</th>";
			tableContext += "<th width='45px'>量測-25</th>";
			tableContext += "<th width='45px'>量測-26</th>";
			tableContext += "<th width='45px'>量測-27</th>";
			tableContext += "<th width='45px'>量測-28</th>";
			tableContext += "<th width='45px'>量測-29</th>";
			tableContext += "<th width='45px'>量測-30</th>";
			tableContext += "<th width='45px'>量測-31</th>";
			tableContext += "<th width='45px'>量測-32</th>";
		}
		tableContext += "</tr></thead><tbody>";//</table>
		for(var i = 0;i<obj.length;i++){
			var color = new Array();
			var Upper_Dim = obj[i].Upper_Dim;
	    	var Lower_Dim = obj[i].Lower_Dim;
	    	
			if(result.HISPECStatus==200){
				if(Frequency=='前工站'||Frequency=='32PCS_批'){
					for(var A1 =0;A1<32;A1++){
		 				var colorData = 1;
		 				if(JSON.stringify(HIobj[i]['MEASURE_VALUE'+(A1+1)])!='null'){
		 					colorData = HIobj[i]['MEASURE_VALUE'+(A1+1)];
		 					if(colorData*1>=Lower_Dim*1&&colorData*1<=Upper_Dim*1){
		 						//jqobj.css('color','black');
		 						color[A1] = 'black';
		 					}else{
		 						//jqobj.css('color','red');
		 						color[A1] = 'red';
		 					}
		 				}else{
		 					color[A1] = 'black';
		 				}
		    		}
				}else{
		 			for(var A1 =0;A1<5;A1++){
		 				var colorData = 1;
		 				if(JSON.stringify(HIobj[i]['MEASURE_VALUE'+(A1+1)])!='null'){
		 					colorData = HIobj[i]['MEASURE_VALUE'+(A1+1)];
		 					if(colorData*1>=Lower_Dim*1&&colorData*1<=Upper_Dim*1){
		 						//jqobj.css('color','black');
		 						color[A1] = 'black';
		 					}else{
		 						//jqobj.css('color','red');
		 						color[A1] = 'red';
		 					}
		 				}else{
		 					color[A1] = 'black';
		 				}
		    		}
		 		}
			}
			
			var Remark1 = JSON.stringify(obj[i].Remark1);
			tableContext += "<tr><td align=center>"+obj[i].WorkShop+"</td>" +
							"<td align=center>"+obj[i].Inspection_Item+"</td>" +
							"<td align=center>"+obj[i].Nominal_Dim+"</td>" +
							"<td align=center>"+obj[i].Upper_Dim+"</td>" +
							"<td align=center>"+obj[i].Lower_Dim+"</td>" +
							"<td align=center>"+obj[i].Frequency+"</td>" +
							"<td align=center>"+(Remark1=="null"?"":obj[i].Remark1)+"</td>" ;
							/*"<td><input type='text' onmousedown='getdata(this.id)' value='' style='width:45px'></td>" +
							"<td><input type='text' onmousedown='getdata(this.id)' value='' style='width:45px'></td>" +
							"<td><input type='text' onmousedown='getdata(this.id)' value='' style='width:45px'></td>" +
							"<td><input type='text' onmousedown='getdata(this.id)' value='' style='width:45px'></td>" +
							"<td><input type='text' onmousedown='getdata(this.id)' value='' style='width:45px'></td>";*/
			if(result.HISPECStatus==500){
				for(A4=0;A4<MeasureSize;A4++){
					tableContext += "<td align=center><input type='text' onmousedown='getdata(this)' value='' style='width:45px;font-size:14px;'></td>";
				}
			}else{
				for(A4=0;A4<MeasureSize;A4++){
					tableContext += "<td align=center><input type='text' onmousedown='getdata(this)' value='"+(JSON.stringify(HIobj[i]['MEASURE_VALUE'+(A4+1)])=="null"?"":HIobj[i]['MEASURE_VALUE'+(A4+1)])+"' style='width:45px;font-size:14px;color:"+color[A4]+"'></td>";
				}
		
			}
				tableContext += "</tr>";
		}
		tableContext+="</tbody><tfoot><tr align='left'><td width='140px' height='30px' align='left' colspan='41'>" +
						"<input type='button' name='submitA' id='submitA' value='資料上傳' class='BT'></td></tr></tfoot></table>";
		$('.bottom').html(tableContext);
		
		
		//設置定時提交表單
		start=new Date();
		start=Date.parse(start)/1000;
		counts=600;

		//設定一個時間自動執行"CountDown()"
		window.setTimeout('CountDown()',100);
		
		$('#submitA').on('click',function(){
			var faultList=[];
			var istrue =0;
			$("#MeasureTable").find('tbody').each(function (index, item) {
			     $(this).find('tr').each(function () {
			      var tdArr = $(this).children();
			      //td里的内容
			      var MeasureSize = 0;
			      var A1 = 12;
			      var Measure = new Object();
			      Measure.WorkShop = tdArr.eq(0).text();
			      Measure.Inspection_Item = tdArr.eq(1).text();
			      Measure.Nominal_Dim = tdArr.eq(2).text();
			      Measure.Upper_Dim = tdArr.eq(3).text();
			      Measure.Lower_Dim = tdArr.eq(4).text();
			      Measure.Frequency = tdArr.eq(5).text();
			      Measure.Remark1 = tdArr.eq(6).text();
			      Measure.Dim1 = new Array();
			      if(Frequency=='前工站'||Frequency=='32PCS_批'){
			    	  A1=39;
			      }else{
			    	  A1=12;
			      }
			      for(var i = 7;i<A1;i++){
		    		  var meaValue = tdArr.eq(i).find('input').val();
		    		  if(meaValue=='NA'||meaValue==''){
		    			  Measure.Dim1.push('');
		    		  }else if(meaValue*1>=tdArr.eq(4).text()*1&&meaValue*1<=tdArr.eq(3).text()*1){
		    			  Measure.Dim1.push(tdArr.eq(i).find('input').val());
		    		  }else if(meaValue*1<=tdArr.eq(4).text()*1||meaValue*1>=tdArr.eq(3).text()*1){
		    			  MeasureSize = MeasureSize + 1;
		    			  Measure.Dim1.push(tdArr.eq(i).find('input').val());
		    		  }
		    		  if(!(reg.test(meaValue)||meaValue=='NA'||meaValue=='')){
		    			  istrue = istrue + 1;
		    		  }
		    	  }

			      if(MeasureSize==0){
			    	  Measure.Result="OK";
			      }else{
			    	  Measure.Result="NG";
			      }
			      faultList.push(Measure);
			      /*Measure.Dim1_1 = tdArr.eq(7).find('input').val();
			      Measure.Dim1_2 = tdArr.eq(8).find('input').val();
			      Measure.Dim1_3 = tdArr.eq(9).find('input').val();
			      Measure.Dim1_4 = tdArr.eq(10).find('input').val();
			      Measure.Dim1_5 = tdArr.eq(11).find('input').val();*/
			      /*var WorkShop = tdArr.eq(0).text();
			      var Inspection_Item = tdArr.eq(1).text();
			      var Nominal_Dim = tdArr.eq(2).text();
			      var Upper_Dim = tdArr.eq(3).text();
			      var Lower_Dim = tdArr.eq(4).text();
			      var Frequency = tdArr.eq(5).text();
			      var Remark1 = tdArr.eq(6).text();
			      var Dim1_1 = tdArr.eq(7).find('input').val();*/
			      /*//input的value
			      var expertid = tdArr.eq(0).find('input').val();
			      //选中：true 未选中：false
			      var checkbox = tdArr.eq(1).find("input[type='checkbox']").is(':checked');
			      //富文本框里面的内容
			      var info = tdArr.eq(2).find('textarea').val();*/
			    });
			});
			if(istrue==0){
				$.ajax({
					   url:"../Measure/sumbitMeasureTable", 
					   type:'post',
					   contentType:"application/json",
					   data:JSON.stringify(faultList),
					   async:false, 
					   success:function(result){
						if(result.SPECStatus=='200'){
							$('.bottom').html('<h1>'+result.Message+'</h1>');
						}else{
							alert(result.Message);
						}
					}, 
					   error:function(err){ 
					    alert("量測數據上傳失敗，請檢查所填寫信息是否正確"); 
					   } 
				})
			}else{
				alert('請確認測量資料是否填寫正確，應為數字或NA或者為空');
			}
			
			
		})
		
	}
})

function getdata(obj){
	var jqobj = $(obj);
	var Upper_Dim = jqobj.parent().parent().find("td:eq(3)").text();
	var Lower_Dim = jqobj.parent().parent().find("td:eq(4)").text();
	var data;
	$.ajax({
		   url:"../Measure/getMeasureData", 
		   type:'post',
		   async:false, 
		   success:function(result){
			data = result.Message;
			}, 
		   error:function(err){ 
		    alert("獲取量測值失敗，失敗原因："+err.statu); 
		   } 
	})
	if(data==''||data=='NA'){
		jqobj.css('color','red');
		jqobj.val(data);
	}else{
		if(data*1>=Lower_Dim*1&&data*1<=Upper_Dim*1){
			jqobj.css('color','black');
			jqobj.val(data);
		}else{
			jqobj.css('color','red');
			jqobj.val(data);
		}
	}
}

function CountDown(){
	var now=new Date();
	now=Date.parse(now)/1000;
	var x=parseInt(counts-(now-start),10);
	if(x>0){
		timerID=setTimeout("CountDown()", 100)
	}else{
		if($("#submitA").length >0){
			$('#submitA').trigger('click');
		}
		return false;
	}
}