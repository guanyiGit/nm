


$(function () {
});


function updatePastoral(form) {
	if(form.name.value==''){
		layer.msg(qsrcwhm);
		form.name.focus();
		return false;
	}
	if(form.floorArea.value=='' || form.floorArea.value ==null){
		layer.msg(qsrzdmj);
		form.floorArea.focus();
		return false;
	}
	
	　if (isNaN(form.floorArea.value)) { 
	　　　　layer.msg(qsrhfsz);
		form.floorArea.focus();
		return false;
	　　} 
	
	if(form.useableArea.value=='' || form.useableArea.value ==null){
		layer.msg(qsrklymj);
		form.useableArea.focus();
		return false;
	}
	
	　if (isNaN(form.useableArea.value)) { 
	　　　　layer.msg(qsrhfsz);
		form.useableArea.focus();
		return false;
	　　} 
    var data = $("#pastoralUpdate").serialize();
    $.ajax({
        type: 'POST',
        url: "/biz/pastoralCommittee/updatePastoralCommittee",
        data: data,
        dataType: "json",
        success: function (data) {
        	if(data.code==0){
          		 layer.confirm(updateSuccess, {
          	         btn : [ determine ]
          	     }, function() {
          	    	 location.href='/biz/pastoralCommittee/pastoral_List';
          	     })
          		
          	}else{
          		layer.confirm(updateFailure, {
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

// function uploadPic() {
//     $("#file-1").fileinput("upload");
// }



