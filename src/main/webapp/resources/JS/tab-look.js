$(document).ready(function(){
	// $.get("text.html",function(data){
	// 	$('#iframeContent').html(data);
	// })
	// $('#nav-bar .level2 a').click(function(){
		
	// 	var target = $(this).attr('target');
	// 	$.get(target,function(data){
	// 		$('#iframeContent').html(data);
	// 		alert(target);
	// 	})	
	// })		
	$("#main-nav li .nav-list a").click(function(){
		var url = $(this).attr('rel');
		var title = $(this).text();
		// alert(title);
		// alert(url);
		// $('#tabs').append(url);
		addTab(title,url);
	})
	$("#main-nav li a").click(function(){
		if($(this).attr("rel")){
			var url = $(this).attr('rel');
			var title = $(this).text();
			addTab(title,url);
		
		}
		
	})
})
	// 
function createIframe(url){
	var s = '<iframe scrolling="auto" frameborder="0"  src="' + url
			+ '" style="width:100%;height:100%;overflow:auto;"></iframe>';
	return s;
}
function addTab(subtitle, url) {
	//$().tabs('exists',XXX)判断是否存在选项卡
	if (!$('#tabs').tabs('exists', subtitle)) {

		$('#tabs').tabs('add', {

			title : subtitle,
			content : createIframe(url),
			closable : true,
//			collapsible: false,

		});

	} else {

		$('#tabs').tabs('select', subtitle);

	}
}
