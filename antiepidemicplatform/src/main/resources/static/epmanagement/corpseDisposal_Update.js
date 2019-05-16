


$(function () {
    //初始化处理方法
        new MySelct({
            id:"processingMethod",
            url:"/biz/manureDisposal/initSelectData",
            value:"value",
            text:"name",
            sv:svMethod||'',
            params: {
                type: "corpse_handle"
            }
        });

        //初始化死亡原因
        new MySelct({
            id:"causeOfDeath",
            url:"/biz/manureDisposal/initSelectData",
            value:"value",
            text:"name",
            sv:svDeath||'',
            params: {
                type: "casuse_of_death"
            }
        });
	
	laydate.render({
        elem: '#deal', //指定元素
        format:"yyyy-MM-dd"
    });
	
	$("#save").click(function(){
		updateCorpse();
		});

}
)


function updateCorpse() {
    var data = $("#corpseUpdate").serialize();
    $.ajax({
        type: 'POST',
        url: "/biz/corpseDisposal/update",
        data: data,
        dataType: "json",
        success: function (data) {
        	 layer.confirm(updateSuccess, {
                 btn : [ sure5 ]
             }, function() {
            	 window.location="/biz/corpseDisposal/toCorpseDisposal";
             }
             )
        },
        error:function (error) {
        	layer.alert(unKnownError);
        }
    });

}
