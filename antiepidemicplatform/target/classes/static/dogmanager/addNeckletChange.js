var model={
    traceId:null,
    deviceNo:null,
    blag:false
};

$(function () {
    model.traceId=deviceRefDog.traceId;
    $("#oldNeckletNo").val(deviceRefDog.deviceNo);

   // selectDevice();
    getStatis();

    //新增
    $("#ajaxBtn").click(function() {
        addNeckletChange();
    })
});



function addNeckletChange(){
    if(model.blag==false){
        layer.msg(disNecklet);
        return false;
    }
    var params = $("#myForm11").serialize();
    var data = $.param({'traceId': model.traceId}) + '&' + $('#myForm11').serialize();
    $.ajax( {
        type : "POST",
        url :"/biz/dogInfo/nectletChange/save",
        data : data,
        async: false,
        success : function(res) {
                if (res.status == 200) {
                    layer.confirm(operationSuccess, {
                        btn : [ sure5 ]
                    }, function(){
                        window.location = "/biz/dogInfo/dogMasterFile";
                    })
                } else {
                    layer.msg(operationFailure);
                }
        }
    });
}


function getDeviceNo(e) {
    if($.trim(e.value)==''){
        return false
    }
    $.ajax( {
        type : "get",
        url :"/biz/dogInfo/checkDeviceIsUse",
        data : {deviceNo:$.trim(e.value)},
        success : function(res) {
            if(res.code != 0) {
                layer.msg(res.msg);
                model.blag=false;
            }else{
                model.blag=true;
            }
        }
    });
}



/*function selectDevice(){
    $.ajax( {
        type : "get",
        url :"/biz/dogInfo/getDevice",
        success : function(res) {
            console.log(res);
            var data=res.data;
            if(data!=null){
                for (var i = 0; i < data.length; i++) {
                    var option = $("<option ></option>");
                    option.val(data[i].id);
                    option.text(data[i].deviceNo);
                    $("#selectDevice").append(option);
                }
            }
        }
    });
}*/



//获得字典数据
function getStatis(){
    $.ajax( {
        type : "get",
        url :"/biz/dogCancel/findTSysDict",
        success : function(res) {

            var deviceDealList=res.data.deviceDeal;
            if(deviceDealList!=null){
                for (var i = 0; i < deviceDealList.length; i++) {
                    var option = $("<option ></option>");
                    option.val(deviceDealList[i].value);
                    option.text(deviceDealList[i].name);
                    $("#selectDeviceDeal").append(option);
                }
            }
        }
    });
}

