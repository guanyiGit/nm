$(function () {
    validateRule();
    //初始化物资类型下拉列表
    new MySelct({
        id:"goodsType",
        url:"/biz/manureDisposal/initSelectData",
        value:"value",
        text:"name",
        sv:sv2||'',
        params: {
            type: "goods_type"
        }
    });
    new MySelct({
        id:"receiveOrg",
        url:"/biz/orgInfo/initOrgSelect4",
        value:"id",
        text:"departName",
        sv:sv||'',
    });
})

$.validator.setDefaults({
    submitHandler : function(form) {
        $(form).find(":submit").attr("disabled", true).attr("value", "Submitting...");
        update();
    }
});



function update() {
    let data = $('#updateGoodsDistribution').serialize();
    $.ajax({
        type: 'POST',
        url: '/biz/goodsInfo/update',
        data: data,
        dataType: "json",
        success: function (r) {
            layer.confirm(operationSuccess, {
                btn : [ determine ]
            }, function() {
                location.href='/biz/goodsInfo';
            })
        },
        error: function () {
            // console.log(r.msg);
        }
    })
}

function validateRule() {
    var icon = "<i class='fa fa-times-circle'></i> ";
    $("#updateGoodsDistribution").validate({
        rules : {
            goodsName : {
                required : true
            },
            goodsAmount : {
                required : true,
                min : 1,
                digits:true
            }
        },
        messages : {
            goodsName : {
                required : icon + pleaseInputGoodsName
            },
            goodsAmount : {
                required : icon + pleaseInputNumber,
                digits : icon + onlyInputPosInt
            }
        }
    })
}