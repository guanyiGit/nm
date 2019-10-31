
$(function () {
    //初始化列表
    getDogMasterFile();

    initOrgSelect();
    initPro();
})


//乡级查全部防疫员的数据
function getAll() {
    //刷新列表 获得乡级组织id
    reLoad();
}

//初始化组织选择框
function initOrgSelect(){
    $('#orgSelect').combotree({
        url: '/biz/orgInfo/initOrgSelect2',
        method:'get',
        required: false,
        valueField:'id',
        textField:'text',
        onSelect:function (data) {
            //刷新列表
            // data.id 组织id
            reLoad2(data.id,null);
        }
    });
}


function initPro(){
    $('#proSelect').combotree({
        url: '/biz/dogInfo/findProtector',
        method:'get',
        required: false,
        valueField:'id',
        textField:'text',
        onSelect:function (data) {
            //刷新列表
            //返回参数说明：
            // data.id 防疫员id
            //data.userId 用户id
            //data.text 防疫员姓名
            reLoad2(null,data.id);
        }
    });
}



function getDogMasterFile(){
    new Vue({
        el :'#app',
        data:{
            string:null,
            pageNum:1,
            pageSize:12,
            totalPage:1,
            totalRecord:1,
            isShow:false,
            items:[]
        },

        created:function(){
            this.getlist();
        },
        beforeDestroy:function () {
            this.items=[];
        },
        methods:{
            edit:function () {
                window.location="/biz/dogInfo/findOne?DogId="+DogId+"";
            },
           /* showSelect:function () {
                console.log(1);
                this.isShow=true;
            },*/
            //模糊查询
            showClick:function () {
                this.getlist();
            },
            //回车事件
            show:function(ev){
                if(ev.keyCode == 13){
                    this.getlist();
                }
            },
            //上一页
            upPagenum:function () {
                var num = this.pageNum;
                num -= 1;
                if (num < 1) {
                    return;
                }
                this.pageNum = num;
                this.getlist();
            },
            //下一页
            nextPagenum:function () {
                var num = this.pageNum;
                num += 1;
                if (num > this.totalPage) {
                    return;
                }
                this.pageNum= num;
                this.getlist();
            },
            //首页
            indexPage:function () {
                var num = this.pageNum;
                if (num == 1) {
                    return;
                }
                this.pageNum = 1;
                this.getlist();
            },
            //末页
            endPagenum:function () {
                var num = this.pageNum;
                if (num == this.totalPage) {
                    return;
                }
                this.pageNum = this.totalPage;
                this.getlist();
            },
            getlist:function () {
                // get
                var that=this;
                axios.get('/biz/dogInfo/findAll', {
                    params: {
                        pageSize:that.pageSize,   //页面大小
                        pageNum: that.pageNum,  //页码
                        string:that.string,
                    }
                })
                    .then(function (response) {
                        that.items = response.data.data.list;
                        that.totalPage=response.data.data.pages;
                        that.totalRecord=response.data.data.total;
                        that.pageNum=response.data.data.pageNum;
                    })
                    .catch(function (error) {
                        console.log(error);
                    });
            }
        }
    });
}
/*$.ajax({
    url :   "/biz/dogInfo/findAll",
    type : "post",
    data : {
        'id' : id
    },
    success : function(r) {
        if (r.code == 0) {
            layer.msg(r.msg);
            reLoad();
        } else {
            layer.msg(r.msg);
        }
    }
});*/









/* // 删除数据
 deldata:function(id){
 this.$http.get('http://vueapi.ittun.com/api/delproduct/'+id)
 .then(function(response){
 if(response.body.status !=0){
 alert(response.body.message);
 return;
 }

 // 刷新列表
 this.getlist();
 });
 },*/
/*            adddata:function(){
 // 实现将数据post到数据添加接口：vueapi.ittun.com/api/addproduct
 // 1.0 获取用户填写的文本框的值只需要通过this.pname即可
 // 2.0 调用ajax的post方法将数据post给服务器
 var url ='http://vueapi.ittun.com/api/addproduct';
 var postData ={name:this.pname};
 var options = {emulateJSON:true};
 this.$http.post(url,postData,options).then(function(response){
 if(response.body.status !=0) {
 alert(response.body.message);
 return;
 }



 // 3.0 直接将列表数据重新加载一次
 this.getlist();

 });
 },*/
