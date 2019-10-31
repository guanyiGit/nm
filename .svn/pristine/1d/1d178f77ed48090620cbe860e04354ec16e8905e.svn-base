

let pastoralId='';
$(function () {


   
});


function savePastoral(form) {
	if(form.name.value==''){
		layer.msg(qsrcwhm);
		form.name.focus();
		return false;
	}
	var check= /^\d+(\.{0,1}\d+){0,1}$/;
	if(form.floorArea.value !=null && form.floorArea.value !=""){
		if(!check.test(form.floorArea.value))
		{
			layer.msg(qsrhfsz);
			form.floorArea.focus();
		return false;
		}
	}
	
	if(form.useableArea.value !=null && form.useableArea.value !=""){
		if(!check.test(form.useableArea.value))
		{
			layer.msg(qsrhfsz);
			form.useableArea.focus();
		return false;
		}
	}
		
//	　if (isNaN(form.floorArea.value)) { 
//	　　　　layer.msg("请输入数字!");
//		form.floorArea.focus();
//		return false;
//	　　} 
//	
//	　if (isNaN(form.useableArea.value)) { 
//	　　　　layer.msg("请输入数字!");
//		form.useableArea.focus();
//		return false;
//	　　} 
    var data = $("#pastoralAdd").serialize();
    $.ajax({
        type: 'POST',
        url: "/biz/pastoralCommittee/addPastoralCommittee",
        data: data,
        dataType: "json",
        success: function (data) {
        	if(data.code==0){
       		 layer.confirm(addSuccess, {
       	         btn : [ determine ]
       	     }, function() {
       	    	 location.href='/biz/pastoralCommittee/pastoral_List';
       	     })
       		
       	}else{
       		layer.confirm(addFailure, {
      	         btn : [ determine ]
      	     }, function() {
      	    	 location.href='/biz/pastoralCommittee/pastoral_List';
      	     })
       	}
        },
        error:function (error) {
        	layer.alert(wzcwqlxgly);
        }
    });

}


