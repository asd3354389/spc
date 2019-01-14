/*$(document).ready(function(){
			// $('.level1').click(function(){
			// 	$(this).children("a").next().show().parent().siblings().children("a").next().hide();
			
				
			// })
			$('.get').click(function(){
				var $ul = $(this).next();
				if($ul.css('display')=='block'){
					$ul.hide();

				}else{
					// 选中的显示二级目录，同辈标签的二级目录隐藏
					$ul.show().parent().siblings().children("a").next().hide();
				}
			})
				
})*/
$(function() {

	InsertMenu();	
})

function InsertMenu(){
	$(".nav-stacked").empty();
	var menulist="";
	$.each(_menus.menus,function(i,n){
		/*if(n.span_class){
				
				menulist+='<li><a href="#'+n.controll+'" class="nav-header" data-toggle="collapse"><i class="glyphicon m-right '+
							n.icon+'"></i><span class="'+n.span_class+'"></span>';	
				menulist+='<span>'+n.menuname+'</span></a>';
				menulist+='<ul id="'+n.controll+'" class="nav nav-list collapse secondmenu" style="height: 0px;">';
				$.each(n.menus_s,function(j,o){	
					menulist+='<li><a href="#" rel="'+o.rel+'" class="p-left">';
					menulist+='<i class="glyphicon '+o.icon+'"></i>'+o.menusname+'</a></li>';
//					console.log(menulist);
				})
				menulist+='</ul></li>';
		}else{*/
			menulist+='<li><a href="#" rel="'+n.rel+'"><i class="glyphicon m-right '+
			n.icon+'"></i>';
			menulist+='<span>'+n.menuname+'</span></a>';
			menulist+='</li>';
		/*}	*/	
		/*$.each(n.menus_i,function(j,o){
			menulist+='<i class="glyphicon m-right '+o.icon+'"></i>';
		})*/	
	})
	console.log(menulist);
	$(".nav-stacked").append(menulist);
/*	console.log(menulist);*/
}