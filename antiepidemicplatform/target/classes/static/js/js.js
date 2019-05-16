// JavaScript Document

$(function(){
	$(".aa2").on("click", function(e){
    	$(this).parent().parent().next('.aa1').slideDown('slow');
		$(document).one("click", function(){
			$(".aa1").slideUp();
		});
    	e.stopPropagation();
	});
	$(".aa1").on("click", function(e){
		e.stopPropagation();
	});	
});

$(function(){
	$('.oDiv2>a').click(function(){
		$(this).addClass('active').siblings().removeClass('active');
	});
});


$(function(){
	$('.ul10>li').click(function(){
		$(this).find('.ul11').slideDown();
		$(this).siblings().find('.ul11').slideUp();
	});

	$('.ul11>li').click(function(){
		$(this).find('.ul111').slideDown();
		$(this).siblings().find('.ul111').slideUp();
	});

});
$(function(){
	$(".ul04>li").on("click", function(e){
    	$(".div12").slideDown();
		$(document).one("click", function(){
			$(".div12").slideUp();
		});
    	e.stopPropagation();
	});
	$(".div12").on("click", function(e){
		e.stopPropagation();
	});	
});

$(function(){
	var $tab= $('.sub-a');
	$tab.click(function(){
		$(this).addClass('active').siblings().removeClass('active');
		var i= $tab.index(this);
		$('.sub-b').eq(i).show().siblings('.sub-b').hide();
	});
});





