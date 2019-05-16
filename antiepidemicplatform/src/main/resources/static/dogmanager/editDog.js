var sv = null
var model={
    traceId:null,
    isWeight:true,
    isAge:true
};
$(function () {
    model.traceId=traceId;
    //初始化犬主
    InintOwner(1);
    //初始化犬种
    // selectBreed();
    //初始化电子围栏
    selectFence(1);
    //回显
    showDog();

    $("#ajaxBtn").click(function () {
        edit();
    });

})


function InintOwner(id){
    $.ajax( {
        type : "get",
        url :"/biz/dogInfo/getDogOwner",
        data : {Id:id},
        success : function(res) {
            var data=res.data;
            if(data!=null){
                for (var i = 0; i < data.length; i++) {
                    var option = $("<option ></option>");
                    option.val(data[i].id);
                    option.text(data[i].name);
                    $("#selectOwner").append(option);
                }
            }
        }
    });
}

function selectBreed(){
    $.ajax( {
        type : "get",
        url :"/biz/dogInfo/getDogBreed",
        success : function(res) {
            var data=res.data;
            if(data!=null){
                for (var i = 0; i < data.length; i++) {
                    var option = $("<option ></option>");
                    option.val(data[i].name);
                    option.text(data[i].name);
                    $("#selectBreed").append(option);
                }
            }
        }
    });
}

function initBreed() {
    //初始化处理方法下拉列表
    new MySelct({
        id:"selectBreed",
        url:"/biz/manureDisposal/initSelectData",
        value:"value",
        text:"name",
        sv:sv||'',
        params: {
            type: "dog_breed"
        }
    });
}

function selectFence(id){
    $.ajax( {
        type : "get",
        url :"/biz/dogInfo/getFence",
        data:{Id:id},
        async:false,
        success : function(res) {
            var data=res.data;
            if(data!=null){
                for (var i = 0; i < data.length; i++) {
                    var option = $("<option ></option>");
                    option.val(data[i].id);
                    option.text(data[i].name);
                    $("#selectFence").append(option);
                }
            }
        }
    });
}


//数据回显
function showDog() {
    $.ajax({
        type: "get",
        url: "/biz/dogInfo/findOne2",
        data: {traceId:model.traceId},
        success: function (res) {
            if (res.status == 200) {
                debugger
                var data=res.data;
                console.log(data);
                $("#selectOwner").val(data.owner);
                // $("#selectBreed").val(data.breed);
                sv = data.breedId;
                $("#dogName").val(data.dogName);
                $("#age").val(data.age);
                $("#sex").val(data.sex);
                $("#hairColor").val(data.hairColor);
                $("#weight").val(data.weight);
                $("#selectFence").val(data.fenceId);
                $("#description").val(data.description);

                //显示下拉框，并选中
                initBreed();
            }
        }
    })
}

function checkAge(e) {
    if(!isAge(e.value) && e.value!=''){
        layer.msg(falseAge);
        model.isAge=false;
        return false;
    }else {
        model.isAge=true;
    }
}

function checkWeight(e) {
    if(!isWeight(e.value) && e.value!=''){
        layer.msg(falseWeight);
        model.isWeight=false;
        return false;
    }else {
        model.isWeight=true;
    }
}

//修改犬只
function edit(){
    // if($("#dogName").val()==''){
    //     layer.msg("犬名不能为空");
    //     return false;
    // }
    if(model.isAge==false){
        layer.msg(falseAge);
        return false;
    }
    if(model.isWeight==false){
        layer.msg(falseWeight);
        return false;
    }
    var params = $("#myFrom2").serialize();
    var data = $.param({'traceId': model.traceId}) + '&' + $('#myFrom2').serialize();
    $.ajax({
        type: "POST",
        url: "/biz/dogInfo/update",
        data: data,
        success: function (res) {
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
    })
}
