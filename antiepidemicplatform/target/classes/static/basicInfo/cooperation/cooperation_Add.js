


$(function () {
	
	$("#save").click(function(){
		addCooperation();
		});

}
)

function addCooperation() {
    var data = $("#cooperationAdd").serialize();
    $.ajax({
        type: 'POST',
        url: "/biz/cooperation/addCooperation",
        data: data,
        dataType: "json",
        success: function (data) {
        	window.location="/biz/cooperation/cooperation_List";
        },
        error:function (error) {
        	alert("未知错误,请联系管理员");
        }
    });

}
