
let picIsSuccess = 0;
let videoIsSuccess = 0;
var infectionId=null;
$(function () {
	
//	$("#positiveAmount").blur(function(){
//		 positiveAmount=$("#positiveAmount").val();
//	});
	//计算阳性率
	//cal();
}
)

function updateInfectionInfo(form) {
	
	if(form.detectionAmount.value=='' || form.detectionAmount.value ==null){
		layer.msg(pleaseInputSurveyNum);
		form.detectionAmount.focus();
		return false;
	}
	if(form.infectionAmount.value === "" || form.infectionAmount.value ==null){
		layer.msg(pleaseInputInfectionNum);
		form.infectionAmount.focus();
		return false;
	}
	
	if(form.harmlessTreatAmount.value=='' || form.harmlessTreatAmount.value ==null){
		layer.msg(pleaseInputHarmlessNum);
		form.harmlessTreatAmount.focus();
		return false;
	}
	
	var check= /^\d+(\.{0,1}\d+){0,1}$/;
		if(!check.test(form.detectionAmount.value))
		{
			layer.msg(pleaseInputLegal);
			form.detectionAmount.focus();
		return false;
		}
	
		if(!check.test(form.infectionAmount.value))
		{
			layer.msg(pleaseInputLegal);
			form.infectionAmount.focus();
		return false;
		}

	
		if(!check.test(form.harmlessTreatAmount.value))
		{
			layer.msg(pleaseInputLegal);
			form.harmlessTreatAmount.focus();
		return false;
		}
	
	var flag=false;
	 var data = $("#infectionUpdate").serialize();
    $.ajax({
        type: 'POST',
        url: "/biz/infectionInfo/update",
        data: data,
        dataType: "json",
        async:false,
        success: function (data) {
        	if(data.code==0){
       		 layer.confirm(operationSuccess, {
       	         btn : [ sure5 ]
       	     }, function() {
       	    	 location.href='/biz/infectionInfo/toInfectionInfo_List';
       	     })
       		
       	}else{
       		layer.confirm(operationFailure, {
      	         btn : [ sure5 ]
      	     }, function() {
      	    	 location.href='/biz/infectionInfo/toInfectionInfo_List';
      	     })
       	}
        },
        error:function (error) {
        	layer.msg(unKnownError);
        }
    });
	
}

