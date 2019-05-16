var sv = null;
var model={
    strayId:null,
    isWeight:true,
    isAge:true
}

$(function () {
    model.strayId=strayId;

    //初始化犬种
    // selectBreed();

    //回显
    showDog();

    $("#ajaxBtn").click(function () {
        edit();
    });

})

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

//数据回显
function showDog() {
    $.ajax({
        type: "get",
        url: "/biz/strayDog/wxfindOne",
        data: {Id:model.strayId},
        success: function (res) {
            console.log(res);
            if (res.status == 200) {
                debugger
                var data=res.data.strayDog;
                // $("#selectBreed").val(data.breed);
                sv = data.breedId;
                $("#age").val(data.age);
                $("#sex").val(data.sex);
                $("#hairColor").val(data.hairColor);
                $("#weight").val(data.weight);
                $("#processMode").val(data.processMode);
                $("#description").val(data.description);

                //回显选中犬只品种
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
    if(model.isAge==false){
        layer.msg(falseAge);
        return false;
    }
    if(model.isWeight==false){
        layer.msg(falseWeight);
        return false;
    }
    if($("#processMode").val()==''){
        layer.msg(falseHandleWay);
        return false;
    }
    var params = $("#myFrom8").serialize();
    var data = $.param({'id': model.strayId}) + '&' + $('#myFrom8').serialize();
    $.ajax({
        type: "POST",
        url: "/biz/strayDog/update",
        data: data,
        success: function (res) {
            if (res.status == 200) {
                layer.confirm(operationSuccess, {
                    btn : [ sure5 ]
                }, function(){
                    window.location = "/biz/strayDog/StrayDogFile";
                })
            } else {
                layer.msg(operationFailure);
            }
        }
    })
}
