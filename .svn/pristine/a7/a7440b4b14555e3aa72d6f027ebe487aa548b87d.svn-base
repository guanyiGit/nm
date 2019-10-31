


$(function () {
});


function updatePastoral(form) {
	if(form.name.value==''){
		layer.msg("请输入村委会名!");
		form.name.focus();
		return false;
	}
	if(form.floorArea.value=='' || form.floorArea.value ==null){
		layer.msg("请输入占地面积!");
		form.floorArea.focus();
		return false;
	}
	
	　if (isNaN(form.floorArea.value)) { 
	　　　　layer.msg("请输入数字!");
		form.floorArea.focus();
		return false;
	　　} 
	
	if(form.useableArea.value=='' || form.useableArea.value ==null){
		layer.msg("请输入可利用面积!");
		form.useableArea.focus();
		return false;
	}
	
	　if (isNaN(form.useableArea.value)) { 
	　　　　layer.msg("请输入数字!");
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
          		 layer.confirm('修改成功!', {
          	         btn : [ '确定' ]
          	     }, function() {
          	    	 location.href='/biz/pastoralCommittee/pastoral_List';
          	     })
          		
          	}else{
          		layer.confirm('修改失败!', {
         	         btn : [ '确定' ]
         	     }, function() {
         	    	 location.href='/biz/pastoralCommittee/pastoral_List';
         	     })
          	}
        },
        error:function (error) {
        	layer.alert("未知错误,请联系管理员");
        }
    });

}

// function uploadPic() {
//     $("#file-1").fileinput("upload");
// }



