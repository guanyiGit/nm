  /*
  var vm = new Vue({
        el:'#city',
        data:{
            msg:'Hello World!',
    		items1:[],
    		items2:[],
    		items3:[],
    		selected1:'',
    		selected2:''
        },
        created:function(){
        	this.findCityList();
        },
        methods:{
        	findCityList:function(){
        		//獲取所有州級信息
        		axios.get('/system/areaInfo/findCityList', {
        				params: {
		        			level:1
		        		}
        			}).then(function(res){
        				vm.items1=res.data;
        			},function(){
        				console.log('请求失败处理');
        		});
        	},
        	//點擊下拉框
        	indexSelect1:function(){
        		//根據州級id獲取所有縣級信息
        		axios.get('/system/areaInfo/findChildrenList', {
        				params: {
		        			pId:vm.selected1
		        		}
        			}).then(function(res){
        				vm.items2=res.data;
        			},function(){
        				console.log('请求失败处理');
        		});
        	},
            indexSelect2:function(){
                //根據县級id獲取所有乡级信息
                axios.get('/system/areaInfo/findChildrenList', {
                    params: {
                        pId:vm.selected2
                    }
                }).then(function(res){
                    vm.items3=res.data;
                },function(){
                    console.log('请求失败处理');
                });
            },
            indexSelect3: function(){
            	
            }
        }
    });
	
	
}*/
window.onload = function(){
new Vue({
    el:"#app",
    data:{
        forage:{
        	name:'',
        	period:'',
        	additive:'',
        	description:'',
        	areaId:0,
        	orgId:1
            //deviceId:0
        }
    },
    methods:{
    	submit: function() {
    		var that=this;
    		axios.post('/biz/forage/addForage',that.forage)
                .then(function (res) {
                    if(res.status==200){
                    	//alert("添加饲料成功");
                           layer.msg("添加成功");
                    }else {
                    	//alert("添加饲料失败");
                           layer.msg("添加失败");
                    }
                	window.location="/biz/forage/forage_List";
                })
                .catch(function (error) {
                       console.log(error);
                });
        }
    }
});
}
