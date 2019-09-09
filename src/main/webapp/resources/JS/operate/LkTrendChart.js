$(document).ready(function(){
/*	 var sd=new Date();
	 var sy=sd.getFullYear();
	 var sm = sd.getMonth()+1;
     if (sm >= 1 && sm <= 9) {
         sm = "0" + sm;
     }
	$('#id-TimeValue').val(sy+"-"+sm);*/
	var tempListMap;
	//打開網頁就去後台查詢所有料號。
	$.ajax({
		   url:"../LkTrendChart/getPartData", 
		   type:'post',
		   success:function(data){
			if(data == null || data == ''){
				alert("查無料號資料！");
				return;
			}else{
				var selectContext="<option></option>" ;
				$.each(data,function(i,result){
					selectContext+="<option value = \""+result['PART_NO']+"\">"+result['PART_NO']+"</option>";
				});
				$('#id-PartNO').html(selectContext);
				/*$("#id-PartNO").change();//手動觸發，哈哈
*/			}
			
		   }, 
		   error:function(err){ 
		    alert("查詢所有料號失敗，失敗原因："+err); 
		   } 
	}) 
	//料號改變了觸發，模號也變
	$('#id-PartNO').on('change',function(){
		var varPartNO = $(this).val();
		if(varPartNO==''){
			$('#id-MoldNO').empty();
			$('#id-CavNO').empty();
			$('#id-CavQty').empty();
			return;
		}
		$.ajax({
			   url:"../LkTrendChart/getModelData", 
			   type:'post',
			   data:{varPartNO:varPartNO},
			   success:function(data){
				if(data == null || data == ''){
					alert("查無模號資料");
					return;
				}else{
					 tempListMap=data;
					var selectContext ;
					var temp='';
					$.each(data,function(i,result){
						if(temp==null||temp==''){
						   selectContext+="<option value = \""+result['MOLD_NO']+"\">"+result['MOLD_NO']+"</option>";
						   temp=result['MOLD_NO'];
						}else if(temp!=result['MOLD_NO']){
							selectContext+="<option value = \""+result['MOLD_NO']+"\">"+result['MOLD_NO']+"</option>";
							temp=result['MOLD_NO'];
						}						
						
					});
					$('#id-MoldNO').html(selectContext);
/*					$('#id-CavNO').html(selectContext2);
					$('#id-CavQty').html(selectContext3);*/	
					$("#id-MoldNO").change();//手動觸發，哈哈
				}
			   }, 
			   error:function(err){ 
			    alert("查詢模號失敗，失敗原因："+err); 
			   } 
		}) 
		
	});
	
/*	$("id-MoldNO").click(function() {
        $("select option").each(function() {
            text = $(this).text();
            if($("select option:contains("+text+")").length > 1)
                $("select option:contains("+text+"):gt(0)").remove();
        });
    });*/
	
	//模號變了觸發，模穴號、模穴數量也變
		$('#id-MoldNO').on('change',function(){
		var varMoldNO = $(this).val();
		var selectContext2 ;
		var selectContext3 ;
		var temp2='';
		var temp3='';
		$.each(tempListMap,function(i,result){
			if(result['MOLD_NO']==varMoldNO){
				if(temp2==null||temp2==''){
					selectContext2+="<option value = \""+result['MOLD_CAVITY_NO']+"\">"+result['MOLD_CAVITY_NO']+"</option>";
					temp2=result['MOLD_CAVITY_NO'];
					}else if(temp2!=result['MOLD_CAVITY_NO']){
						selectContext2+="<option value = \""+result['MOLD_CAVITY_NO']+"\">"+result['MOLD_CAVITY_NO']+"</option>";
						temp2=result['MOLD_CAVITY_NO'];
					}
				if(temp3==null||temp3==''){
					selectContext3+="<option value = \""+result['MOLD_CAVITY_QTY']+"\">"+result['MOLD_CAVITY_QTY']+"</option>";
					temp3=result['MOLD_CAVITY_QTY'];
					}else if(temp3!=result['MOLD_CAVITY_QTY']){
						selectContext3+="<option value = \""+result['MOLD_CAVITY_QTY']+"\">"+result['MOLD_CAVITY_QTY']+"</option>";
						temp3=result['MOLD_CAVITY_QTY'];
					}
			}
		});
		$('#id-CavNO').html(selectContext2);
		$('#id-CavQty').html(selectContext3);
		/*$.ajax({
			   url:"../LkTrendChart/getModelQtyData", 
			   type:'post',
			   data:{varMoldNO:varMoldNO},
			   success:function(data){
				if(data == null || data == ''){
					alert("查無模號Qty資料");
					return;
				}else{
					var selectContext ;
					$.each(data,function(i,result){
						selectContext+="<option value = \""+result['MOLD_CAVITY_QTY']+"\">"+result['MOLD_CAVITY_QTY']+"</option>";
					});
					$('#id-CavQty').html(selectContext);					
				}
			   }, 
			   error:function(err){ 
			    alert("查詢模號Qty失敗，失敗原因："+err); 
			   } 
		}) */
		
	});
		$("#id-query").click(function(){
			var varPartNO=$('#id-PartNO').val();
			var varData=$('#id-Data').val();
			var varMoldNO=$('#id-MoldNO').val();
			var varMOLD_CAVITY_QTY=$('#id-CavQty').val();
			var varMOLD_CAVITY_NO=$('#id-CavNO').val();
			console.log($('#id-TimeValue').val());
			var varYearMonth=$('#id-TimeValue').val().replace(/-/g,'');
			console.log(varYearMonth);
			var varYearMonth2=$('#id-TimeValue2').val().replace(/-/g,'');
			if(varYearMonth2==''||varMOLD_CAVITY_NO==null||varPartNO==null||varMoldNO==null||varMOLD_CAVITY_QTY==null||varYearMonth==''||varMoldNO=='undefined'||varMOLD_CAVITY_QTY=='undefined'){
				 alert("不能為空"); 
				return false;
			}
			addTab1(varMoldNO+'_'+varMOLD_CAVITY_NO+'_'+varData,"LkTrendChart/LKWebChart?varPartNO="+varPartNO+"&varData="+varData+"&varMoldNO="+varMoldNO+"&varMOLD_CAVITY_NO="+varMOLD_CAVITY_NO+"&varYearMonth="+varYearMonth+"&varYearMonth2="+varYearMonth2+"&varMOLD_CAVITY_QTY="+varMOLD_CAVITY_QTY)
			//window.location.href="LKWebChart?varPartNO="+varPartNO+"&varMoldNO="+varMoldNO+"&varMOLD_CAVITY_QTY="+varMOLD_CAVITY_QTY+"&varMOLD_CAVITY_NO"+varMOLD_CAVITY_NO+"&varYearMonth="+varYearMonth+"&varYearMonth2="+varYearMonth2;
		});
		
	
	$("#id-query1").click(function(){
		var varPartNO=$('#id-PartNO').val();
		var varMoldNO=$('#id-MoldNO').val();
		var varMOLD_CAVITY_QTY=$('#id-CavQty').val();
		var varYearMonth=$('#id-TimeValue').val().replace('-','');	
		$("#td-09").text('ok');
		$.ajax({
			   url:"../LkTrendChart/getAllMonth", 
			   type:'post',
			   data:{varPartNO:varPartNO,varMoldNO:varMoldNO,varMOLD_CAVITY_QTY:varMOLD_CAVITY_QTY,varYearMonth:varYearMonth},
			   success:function(data){
				var tableContext ='';
				tableContext+="<table class='table table-condensed'>";
				$.each(data,function(i,result){
					var varResult=result['MEASURE_DATE'].substring(6);
					switch(varResult){
					case '01':
						$("#td-01").text(result['MEASURE_DATE']);
						break;
					case '02':
						$("#td-02").text(result['MEASURE_DATE']);
						$("#td-02").css({'background-color':'#7fb80e'});
						break;						
					case '03':
						$("#td-03").text(result['MEASURE_DATE']);
						break;
					case '04':
						$("#td-04").text(result['MEASURE_DATE']);
						break;
					case '05':
						$("#td-05").text(result['MEASURE_DATE']);
						break;
					case '06':
						$("#td-06").text(result['MEASURE_DATE']);
						break;
					case '07':
						$("#td-07").text(result['MEASURE_DATE']);
						break;
					case '08':
						$("#td-08").text(result['MEASURE_DATE']);
						break;
					case '09':
						$("#td-09").text(result['MEASURE_DATE']);
						break;
					case '10':
						$("#td-10").text(result['MEASURE_DATE']);
						break;
					case '11':
						$("#td-11").text(result['MOLD_CAVITY_QTY']);
						break;
					case '12':
						$("#td-12").text(result['MEASURE_DATE']);
						break;						
					}
				});
			   }, 
			   error:function(err){ 
			    alert("查詢模號Qty失敗，失敗原因："+err); 
			   } 
		}) 
	});
	
	/*$("#id-query2").click(function(){*/
		function check1(){
		var varPartNO=$('#id-PartNO').val();
		var varMoldNO=$('#id-MoldNO').val();
		var varMOLD_CAVITY_QTY=$('#id-CavQty').val();
		var varYearMonth=$('#id-TimeValue').val().replace('-','');
		if(varPartNO==null||varMoldNO==null||varMOLD_CAVITY_QTY==null||varYearMonth==null||varMoldNO=='undefined'){
			 alert("不能為空"); 
			return;
		}
		/*window.location.href="test?varPartNO="+varPartNO+"&varMoldNO="+varMoldNO+"&varMOLD_CAVITY_QTY="+varMOLD_CAVITY_QTY+"&varYearMonth="+varYearMonth;*/
/*		$.ajax({
			   url:"../LkTrendChart/test", 
			   type:'post',
			   data:{varPartNO:varPartNO,varMoldNO:varMoldNO,varMOLD_CAVITY_QTY:varMOLD_CAVITY_QTY,varYearMonth:varYearMonth},
			   success:function(data){
				
				alert(data); 
			   }, 
			   error:function(err){ 
			    alert("查詢模號Qty失敗，失敗原因："+err); 
			   } 
		}) */

	//下载excel表单提交
/*    $('#downExcel').form('submit', {    	
        success : function(data) {
            if(data!=null){
            	alert(data); 
            }
        }
    });	*/
     $('#form1').load(function(){
    	 var test=$(this).contents().find("body").text();   	 
    	 alert(test);
     });
    
}

	
})
        function listenEnd() {//定时监听             
            var loop = setInterval(function() {
            	var vflag=$("#txtendflag").val();
                if ($("#txtendflag").val() != "") {
                    clearInterval(loop);//停止定时任务
                    $('#ajaxLoader').hide();//关闭等待
                    $("#id-query2").attr('disabled',false);//啟用按鈕
                    if(vflag!='OK'){
                      alert(vflag);
                    }
                } else {
                    getendflag();                 
                }
            }, 2000);//单位毫秒  注意：如果导出页面很慢时，建议循环时间段稍长一点
        }
function getendflag() {//请求session标记位             
    $.ajax({
              type : 'post',
              url : '../LkTrendChart/getendflag',
              success : function(data) { 
            	  console.log(data);
                  $("#txtendflag").val(data);                  
              },
              error : function(error) {
            	  alert("查询状态标记错误。");
                  console.log('接口不通' + error);
              }
          })  
}


	function check(){
	$('#form').contents().find("body").empty();//先清空iframe中的內容。
		var varPartNO=$('#id-PartNO').val();
		var varMoldNO=$('#id-MoldNO').val();
		var varMOLD_CAVITY_QTY=$('#id-CavQty').val();
		var varMOLD_CAVITY_NO=$('#id-CavNO').val();
		var varYearMonth=$('#id-TimeValue').val().replace('-','');
		var varYearMonth2=$('#id-TimeValue2').val().replace('-','');
		if(varYearMonth2==''||varMOLD_CAVITY_NO==null||varPartNO==null||varMoldNO==null||varMOLD_CAVITY_QTY==null||varYearMonth==''||varMoldNO=='undefined'||varMOLD_CAVITY_QTY=='undefined'){
			 alert("不能為空"); 
			return false;
		}
		 $('#ajaxLoader').show();//顯示等待進度條
		 $('#id-query2').attr("disabled",true);//禁用按鈕  
		 listenEnd();//和後台聯動。
		/*window.location.href="test?varPartNO="+varPartNO+"&varMoldNO="+varMoldNO+"&varMOLD_CAVITY_QTY="+varMOLD_CAVITY_QTY+"&varYearMonth="+varYearMonth;*/
/*		$.ajax({
			   url:"../LkTrendChart/test", 
			   type:'post',
			   data:{varPartNO:varPartNO,varMoldNO:varMoldNO,varMOLD_CAVITY_QTY:varMOLD_CAVITY_QTY,varYearMonth:varYearMonth},
			   success:function(data){
				
				alert(data); 
			   }, 
			   error:function(err){ 
			    alert("查詢模號Qty失敗，失敗原因："+err); 
			   } 
		}) */

	//下载excel表单提交
/*    $('#downExcel').form('submit', {    	
        success : function(data) {
            if(data!=null){
            	alert(data); 
            }
        }
    });	*/
/*     $('#form').load(function(){
    	 $('#ajaxLoader').hide();
    	 var test=$(this).contents().find("body").text();   	 
    	 alert(test);
     });*/
		 
    
}
	function createIframe(subtitle,url){
		var s = '<iframe id="'+subtitle+'" scrolling="auto" frameborder="0"  src="' + url
				+ '" style="width:100%;height:100%;overflow:auto;"></iframe>';
		return s;
	}
	function addTab1(subtitle, url) {
		//$().tabs('exists',XXX)判断是否存在选项卡
		var jq = top.jQuery;
		if (!jq('#tabs').tabs('exists', subtitle)) {

			jq('#tabs').tabs('add', {

				title : subtitle,
				content : createIframe(subtitle,url),
				closable : true,
//				collapsible: false,

			});

		} else {

			jq('#tabs').tabs('select', subtitle);

		}
	}
