


$(function () {
	
	$("#save").click(function(){
		addVeterinarian();
		});
	
	 //初始化民族下拉框
    new MySelct({
        id:"selectedNation",
        url:"/biz/protector/findTotalNation",
        value:"name",
        text:"name",
    });

    laydate.render({
        elem: '#birth', //指定元素
        type:"datetime",
        format:"yyyy-MM-dd",
        value:new Date()
    });

}
)

function addVeterinarian() {
    var data = $("#veterinarianAdd").serialize();
    $.ajax({
        type: 'POST',
        url: "/biz/veterinarian/addVeterinarian",
        data: data,
        dataType: "json",
        success: function (data) {
        	if(data.id==-2){
        		alert("该用户已经存在,请重新输入!");
        	}
        	window.location="/biz/veterinarian/veterinarian_List";
        },
        error:function (error) {
        	alert("未知错误,请联系管理员");
        }
    });

}
