
let picIsSuccess = 0;
let videoIsSuccess = 0;
var testAmount=null;
var qualifiedAmount=null;
var number_regex = /^[0-9.]+$/;
$(function () {
	
//	$("#qualifiedAmount").blur(function(){
//		 qualifiedAmount=$("#qualifiedAmount").val();
//	});
	initCal();
}
)

function initCal(){
	qualifiedAmount=$("#qualifiedAmount").val();
	testAmount=$("#testAmount").val();
}

var compute_qualifiedRate = function() {
    console.log("testAmount: " + testAmount + ", qualifiedAmount: " + qualifiedAmount);
    if (testAmount == null || qualifiedAmount == null) {
        $("#qualifiedRate").val(waitCompute);
    } else {
        if (testAmount > 0 && qualifiedAmount >=0 && qualifiedAmount <= testAmount) {
            var qualifiedRate = qualifiedAmount / testAmount * 100;
            $("#qualifiedRate").val(qualifiedRate.toFixed(2));
        } else {
            $("#qualifiedRate").val(errorCompute);
            layer.msg(qualifyShouldSmallThanTest);
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

    compute_qualifiedRate();
});

$("#qualifiedAmount").on('input propertychange', function(){
    qualifiedAmount = null;

    if (number_regex.test($(this).val())) {
        var tmp = parseFloat($(this).val());
        if (tmp >= 0) {
            qualifiedAmount = tmp;
        } else {
        	layer.msg(qualifiedAmountShouldBigThanZero);
        }
    } else {
    	layer.msg(pleaseInputLegal);
    }

    compute_qualifiedRate();
});

function updateAntibodyDetection(form) {
	if(form.testAmount.value==''){
		layer.msg(pleaseInputTestNumber);
		form.testAmount.focus();
		return false;
	}
	if(form.qualifiedAmount.value==''){
		layer.msg(pleaseInputQualifyNum);
		form.qualifiedAmount.focus();
		return false;
	}
	
	var check= /^\d+(\.{0,1}\d+){0,1}$/;
	if(!check.test(form.testAmount.value))
	{
		layer.msg(pleaseInputLegal);
		form.testAmount.focus();
	return false;
	}
	
	if(!check.test(form.qualifiedAmount.value))
	{
		layer.msg(pleaseInputLegal);
		form.qualifiedAmount.focus();
	return false;
	}
	
	if(!check.test(form.qualifiedRate.value))
	{
		layer.msg(qualifyRateError);
		form.qualifiedRate.focus();
	return false;
	}
	
	 var data = $("#antibodyDetectionUpdate").serialize();
    $.ajax({
        type: 'POST',
        url: "/biz/antibodyDetection/update",  
        data: data,
        dataType: "json",
        async:false,
        success: function (data) {
        	if(data.code==0){
       		 layer.confirm(operationSuccess, {
       	         btn : [ sure5 ]
       	     }, function() {
       	    	 location.href='/biz/antibodyDetection/toAntibodyDetection_List';
       	     })
       		
       	}else{
       		layer.confirm(operationFailure, {
      	         btn : [ sure5 ]
      	     }, function() {
      	    	 location.href='/biz/antibodyDetection/toAntibodyDetection_List';
      	     })
       	}
        },
        error:function (error) {
        	layer.alert(unKnownError);
        }
    });
	
	
	
}

