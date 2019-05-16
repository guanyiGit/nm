// JavaScript Document

//垂直菜单二级菜单
$(function(){
    $('.oDiv3-1 li').click(function(){
		if($(this).children(".div2").is(':hidden')){
		//if($('.nav2').is(':visible')){
			$(this).children(".div2").slideDown();
			$(this).siblings().children(".div2").slideUp();
			$(this).addClass('active')
			$(this).siblings().removeClass('active')
		}else{
			$(this).children(".div2").slideUp();
			$(this).removeClass('active')
			$(this).siblings().removeClass('active')
		}
    });
		
});

//或者
$(function(){
    $('#AAA').click(function(){
		if($('.nav2').is(':visible')){
			$(this).children(".div2").slideUp();
		}else{
			$(this).children(".div2").slideDown();
		}
    });
		
});

$(function () {
	$(window).scroll(function() {		
		if($(window).scrollTop() >= 100){
			$('#侧栏').fadeIn(300); 
		}else{    
			$('侧栏').fadeOut(300);    
		}  
	});
	
	var speed = 800;
	var h=document.body.clientHeight;
	$("回到顶部按钮").click(function () {
		$('html,body').animate({
			scrollTop: '0px'
		},speed);			
	});
	$("回到底部按钮").click(function () {
		//alert(h);
		$('html,body').animate({
			scrollTop: h+'px'
		},speed);
	});  
});

//递归自调用
$(function(){
	$('input').click(function(){
		$('.test').first().show(200,function testShow(){
			$(this).next().show(200,testShow);
		});
	})	
});


//滚动到顶部固定定位
$(function(){
	//获取要定位元素距离浏览器顶部的距离
	var navH = $(".到顶部固定定位的元素").offset().top;
	//滚动条事件
	$(window).scroll(function(){
		//获取滚动条的滑动距离
		var scroH = $(this).scrollTop();
		//滚动条的滑动距离大于等于定位元素距离浏览器顶部的距离，就固定，反之就不固定
		if(scroH>=navH){
			$(".到顶部固定定位的元素").css({"position":"fixed","top":0});
		}else if(scroH＜navH){
			$(".到顶部固定定位的元素").css({"position":"static"});
		}
	})
})
//滚动到顶部固定定位
$(function(){
	var divTop=$(".mLeft").offset().top-213;
	$(window).scroll(function () {
		if ($(window).scrollTop()>divTop) {
			$('.mLeft').addClass('hFixed');
		}else{
			$('.mLeft').removeClass('hFixed');
		}
	});
});

//楼层运动
$(function () {
	$('.按钮1').click(function(){
		$('html,body').animate({scrollTop:($('.要滚动到顶部的元素1').offset().top)}, 800);
	});
	$('.按钮2').click(function(){
		$('html,body').animate({scrollTop:($('.要滚动到顶部的元素2').offset().top)}, 800);
	});
	$('.按钮3').click(function(){
		$('html,body').animate({scrollTop:($('.要滚动到顶部的元素3').offset().top)}, 800);
	});
	$('.按钮4').click(function(){
		$('html,body').animate({scrollTop:($('.要滚动到顶部的元素4').offset().top)}, 800);
	});
	$('.按钮5').click(function(){
		$('html,body').animate({scrollTop:($('.要滚动到顶部的元素5').offset().top)}, 800);
	});
});



//封装jQuery插件版--永久居中
(function($){	
    var methods={		
	autosize: function(ele){			
	    if(ele.height()<= $(window).height()){				
		ele.css("top",($(window).height()-ele.height())/2);			
	    };			
	    if(ele.width()<= $(window).width()) {				
		ele.css("left",($(window).width()-ele.width())/2);		
	    };		
	},	
    };	
    $.fn.extend({		
        cen: function(options) {			
	    $this = $(this);			
	    methods.autosize($this);			
	    $(window).resize(function() {				
		methods.autosize($this);			
	    });		
	},	
    });
})(jQuery);
//调用	
$(".oDiv1").cen();


//点击按钮显示。点击任意地方隐藏
$(function(){
	$(".s1-1").on("click", function(e){
    	$(".sTit-3").slideDown();
		$(document).one("click", function(){
			$(".sTit-3").slideUp();
		});
    	e.stopPropagation();
	});
	$(".sTit-3").on("click", function(e){
		e.stopPropagation();
	});	
});

/*隐藏顶部*/
jQuery(document).ready(function () {
	jQuery('.nav').meanmenu({
		meanRevealPosition: "left",
		meanScreenWidth: "767",
	});
		  
});
















