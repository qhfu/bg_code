hljs.initHighlightingOnLoad();
$(document).ready(function(event){
	var urlStr = window.location.href;
	$("#memu li").removeClass('active');
	if (urlStr.indexOf("blog") !== -1) {
		$("#blog").addClass('active');
	} else if (urlStr.indexOf('about') !== -1) {
		$("#about").addClass('active');
	} else {
		$("#main").addClass('active');
	}
});