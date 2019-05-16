var model={
    Id:null,
    type:null

}

$(function () {
    var type= window.sessionStorage.getItem("type");
    model.type=type;

    // laydate.render({
    //     elem: '#antiepidemicDate', //指定元素
    //     type:"datetime",
    //     format:"yyyy-MM-dd HH:mm:ss",
    //     value:new Date()
    // });
    var defendId=window.sessionStorage.getItem("defendId");
    model.Id=defendId;

    InitDrog();

    //回显
    showDefend();

    $("#ajaxBtn").click(function () {
        editDefend();
    });

    $("#cancel").click(function () {
        if(type==0){
            window.location="/biz/antiepidemic/springDefendInfo";
        }else if(type==1){
            window.location="/biz/antiepidemic/autumnDefendInfo";
        }else {
            window.location="/biz/antiepidemic/monthDefendInfo";
        }
    });

})

function InitDrog() {
    $.ajax( {
        type : "get",
        url :"/biz/antiepidemic/getDrug",
        data : {type:0},
        success : function(res) {
           // console.log(res);
            if(res.status==200){
                var data=res.data;
                for (var i = 0; i < data.length; i++) {
                    var option = $("<option ></option>");
                    option.val(data[i].id);
                    option.text(data[i].name);
                    $("#selectDrug").append(option);
                }
            }
        }
    });
}


function showDefend() {
    $.ajax({
        type: "get",
        url: "/biz/antiepidemic/edit2",
        data: {id:model.Id},
        success: function (res) {
           console.log(res);
            if (res.status == 200) {
                var data=res.data.antiepidemic;
                $("#selectDrug option").each(function (i) {
                    if(this.text==data.drugName){
                        $(this).attr("selected","selected");
                    }
                })
                $("#antiepidemicPeriod").val(data.period);
                $("#description").val(data.description);
            }
        }
    })
}

function editDefend() {
    var params = $("#myFrom5").serialize();
    var data = $('#myFrom5').serialize()
        +'&'+$.param({'id': model.Id})
    //areaId
    //orgId
    //protector
    $.ajax( {
        type : "POST",
        url :"/biz/antiepidemic/update",
        data : data,
        success : function(res) {
            if(res.code==0){
                layer.confirm(operationSuccess, {
                    btn : [ sure5 ]
                }, function(){
                    if(type==0){
                        window.location="/biz/antiepidemic/springDefendInfo";
                    }else if(type==1){
                        window.location="/biz/antiepidemic/autumnDefendInfo";
                    }else {
                        window.location="/biz/antiepidemic/monthDefendInfo";
                    }
                })
            }else{
                layer.msg(operationFailure);
            }
        }
    });
}


