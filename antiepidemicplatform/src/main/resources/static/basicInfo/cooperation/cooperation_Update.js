


$(function () {
	
	$("#save").click(function(){
		addCooperation();
		});

}
)

function addCooperation() {
    var data = $("#cooperationUpdate").serialize();
    $.ajax({
        type: 'POST',
        url: "/biz/cooperation/updateCooperation",
        data: data,
        dataType: "json",
        success: function (data) {
        	alert("修改成功!")
        	window.location="/biz/cooperation/cooperation_List";
        },
        error:function (error) {
        	alert("未知错误,请联系管理员");
        }
    });

}
