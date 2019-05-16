window.onload = function(){
new Vue({
    el:"#app",
    data:{
        drug:{
        	drugName:'',
        	period:'',
        	usageMode:'',
        	purposeOfUse:'',
        	drugComposition:'',
        	description:'',
        	type:1,
        	areaId:1,//待定
        	orgId:1//待定
            //deviceId:0
        }
    },
    methods:{
    	submit: function() {
    		axios.post('/biz/drug/addDrug',this.drug)
                .then(function (res) {
                    if(res.status==200){
                    	//alert("添加饲料成功");
                           layer.msg("添加成功");
                    }else {
                    	//alert("添加饲料失败");
                           layer.msg("添加失败");
                    }
                	window.location="/biz/drug/therapeutical_Drug_List";
                })
                .catch(function (error) {
                       console.log(error);
                });
    		/*$.ajax({
    	        type: "post",
    	        url: "/biz/forage/addForage",
    	        data:JSON.stringify(this.forage),
    	        dataType: "json",
    	        success: function (result) {
    	        	if(result.code==0){
    	        		layer.msg("添加饲料成功");
    	        	}else{
    	        		layer.msg("添加饲料失败");
    	        	}
    	        	window.location="/biz/forage/forage_List";
    	        }
    		}
    		)*/
        }
    }
});
}