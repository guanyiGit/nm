


$(function () {
	
	$("#save").click(function(){
		updatePad();
		});

}
)

function updatePad() {
    var data = $("#padUpdate").serialize();
    $.ajax({
        type: 'POST',
        url: "/biz/pad/updatePad",
        data: data,
        dataType: "json",
        success: function (data) {
        	alert("修改成功!")
        	window.location="/biz/pad/pad_List";
        },
        error:function (error) {
        	alert("未知错误,请联系管理员");
        }
    });

}

