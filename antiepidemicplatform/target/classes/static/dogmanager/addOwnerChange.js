/*var model={
    oldOwner:null,
    dogSourceCard:null
}
$(function () {
   model.oldOwner=window.sessionStorage.getItem("oldOwner");
   model.dogSourceCard=window.sessionStorage.getItem("dogSourceCard");

})

//初始化犬主
function InintOwner() {
    $.ajax( {
        type : "get",
        url :"/biz/dogInfo/getDogOwner",
        success : function(res) {

        }
    });
}*/













new Vue({
    el: "#app1",
    data: {
        ownerChange: {
            oldOwner: 0,
            newOwner: '',
            changeReasons: '',
            description: '',
            traceId: '',
        },
        oldOwnerName:'',
        dogOwnerList: []
    },
    created: function () {
        this.InintOwner();
    },
    methods: {
        submit: function () {
            if (this.ownerChange.changeReasons == '') {
                layer.msg("更换原因不能为空");
                return false;
            }
            console.log(this.ownerChange);
            axios.post('/biz/dogInfo/ownerChange/save', this.ownerChange)
                .then(function (res) {
                    if (res.status == 200) {
                        layer.confirm("操作成功", {
                            btn : [ '确定' ]
                        }, function(){
                            window.location = "/biz/dogInfo/ownerChangeFile";
                        })
                    } else {
                        layer.msg("添加失败");
                    }
                })
                .catch(function (error) {
                    console.log(error);
                });
        },
        InintOwner: function () {
            // get
            this.Inint();
            var that = this;
            axios.get('/biz/dogInfo/getDogOwner', {
                params: {
                    Id: that.operator,
                }
            })
                .then(function (res) {
                    if (res.data.status == 200) {
                        that.dogOwnerList = res.data.data;
                        that.ownerChange.newOwner = that.dogOwnerList[0].id;
                    }
                })
        },
        Inint: function () {
            this.ownerChange.oldOwner = window.sessionStorage.getItem("oldOwner");
            this.oldOwnerName = window.sessionStorage.getItem("oldOwnerName");
            this.ownerChange.traceId = window.sessionStorage.getItem("traceId");
        }
    }
});

