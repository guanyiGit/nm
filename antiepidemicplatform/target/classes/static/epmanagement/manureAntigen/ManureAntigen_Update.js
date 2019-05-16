
var testAmount = null;
var positiveAmount = null;
var number_regex = /^[0-9.]+$/;
var manureId=null;
$(function () {
	
//	$("#positiveAmount").blur(function(){
//		 positiveAmount=$("#positiveAmount").val();
//	});
	//计算阳性率
	initCal();
}
)

function initCal(){
	positiveAmount=$("#positiveAmount").val();
	testAmount=$("#testAmount").val();
}

var compute_positiveRate = function() {
    console.log("testAmount: " + testAmount + ", positiveAmount: " + positiveAmount);
    if (testAmount == null || positiveAmount == null) {
        $("#positiveRate").val(waitCompute);
    } else {
        if (testAmount > 0 && positiveAmount >=0 && positiveAmount <= testAmount) {
            var positiveRate = positiveAmount / testAmount * 100;
            $("#positiveRate").val(positiveRate.toFixed(2));
        } else {
            $("#positiveRate").val(errorCompute);
            layer.msg(positiveShouldSmallThanTest);
        }
    }
}


$("#testAmount").on('input propertychange', function(){
    testAmount = null;

    if (number_regex.test($(this).val())) {
        var tmp = parseFloat($(this).val());
        if (tmp > 0) {
            testAmount = tmp;
        } else {
        	layer.msg(TestShouldBigThanZero);
        }
    } else {
    	layer.msg(pleaseInputLegal);
    }

    compute_positiveRate();
});

$("#positiveAmount").on('input propertychange', function(){
    positiveAmount = null;

    if (number_regex.test($(this).val())) {
        var tmp = parseFloat($(this).val());
        if (tmp >= 0) {
            positiveAmount = tmp;
        } else {
        	layer.msg(positiveShouldBigThanZero);
        }
    } else {
    	layer.msg(pleaseInputLegal);
    }

    compute_positiveRate();
});

function updateManureAntigen(form) {
	
	if(form.testAmount.value==''){
		layer.msg(pleaseInputTestNumber);
		form.testAmount.focus();
		return false;
	}
	if(form.positiveAmount.value==''){
		layer.msg(pleaseInputPositiveNum);
		form.positiveAmount.focus();
		return false;
	}
	
	var check= /^\d+(\.{0,1}\d+){0,1}$/;
	if(!check.test(form.testAmount.value))
	{
		layer.msg(pleaseInputLegal);
		form.testAmount.focus();
	return false;
	}
	if(!check.test(form.positiveAmount.value))
	{
		layer.msg(pleaseInputLegal);
		form.positiveAmount.focus();
	return false;
	}
	
	if(!check.test(form.positiveRate.value))
	{
		layer.msg(positiveRateError);
		form.positiveRate.focus();
	return false;
	}
	
	 var data = $("#manureAntigenUpdate").serialize();
    $.ajax({
        type: 'POST',
        url: "/biz/manureAntigen/update",
        data: data,
        dataType: "json",
        async:false,
        success: function (data) {
        	if(data.code==0){
        		 layer.confirm(operationSuccess, {
        	         btn : [ sure5 ]
        	     }, function() {
        	    	 location.href='/biz/manureAntigen/toManureAntigen_List';
        	     })
        		
        	}else{
        		layer.confirm(operationFailure, {
       	         btn : [ sure5 ]
       	     }, function() {
       	    	 location.href='/biz/manureAntigen/toManureAntigen_List';
       	     })
        	}
        	
        	
        },
        error:function (error) {
        	layer.msg(unKnownError);
        }
    });
	
	
}

