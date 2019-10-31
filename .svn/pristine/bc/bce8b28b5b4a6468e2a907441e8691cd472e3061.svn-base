window.onload = function(){
new Vue({
    el:"#app",
    data:{
        pad:{
        	padNo:'',
        	passwd:'',
        	recipient:'',
        	description:''
            //deviceId:0
        }
    },
    methods:{
    	submit: function() {
    		axios.post('/biz/pad/addPad',this.pad)
                .then(function (res) {
                    if(res.status==200){
                    	//alert("添加饲料成功");
                           layer.msg("添加成功");
                    }else {
                    	//alert("添加饲料失败");
                           layer.msg("添加失败");
                    }
                	window.location="/biz/pad/pad_List";
                })
                .catch(function (error) {
                       console.log(error);
                });
        }
    }
});
}