


$(function () {


    Vue.prototype.bus = new Vue();
    new Vue({
        el:"#mainTree",
        data:{
            mainTrees:[]
        },
        mounted(){
            $.ajax({
                type:"get",
                url:"/biz/menu/getMainMenu",
                success: (trees)=>{
                    this.mainTrees =  trees;
                }
            });
        },
        methods:{
            loadChildTrees(baseUrl,id,name,event){
                debugger
                //选中当前元素
                var el = event.currentTarget;
                var bros = siblings(el);
                for (let i = 0; i < bros.length; i++) {
                    bros[i].classList.remove("active");
                }
                el.classList.add("active");
                ///~

                let flag = false;
                this.bus.$emit('tabName',name);
                let url = baseUrl+"/"+id;
                // $("#leftLan").show();
                if(id == 105){
                    // $("#leftLan").hide();
                    this.bus.$emit('index',[]);
                    url = baseUrl;
                    var homePageA =  $('<a id="homePagee" target="menuFrame" href="'+url+'"></a>');
                    homePageA[0].click();
                    return ;
                }
                $.ajax({
                    type:"get",
                    url:url,
                    success: (data)=> {
                        // this.leftTrees = data;
                         this.bus.$emit('change',data);
                    }
                });
            },
            // selectIt: function (event) {
            //     var el = event.currentTarget;
            //     var bros = siblings(el);
            //     for (let i = 0; i < bros.length; i++) {
            //         bros[i].classList.remove("active");
            //     }
            //     el.classList.add("active");
            // }
        }
    });


    var tab = new Vue({
        el:"#tabName",
        data:{
            tabName:""
        },
        mounted(){
            this.bus.$on('tabName',(msg)=> {    //订阅change事件
                this.tabName = msg;
            })
        }

    });


    var ltree = new Vue({
            el:"#leftTree",
            data:{
                leftTrees:[]
            },
            mounted(){
                this.bus.$on('change',(msg)=> {    //订阅change事件
                    this.leftTrees = msg;
                    this.$nextTick(function(){

                        if(Array.isArray(msg)){
                            $("#"+msg[0].id)[0].click();
                        // alert('v-for渲染已经完成')
                        }

                    })
                });
                this.bus.$on('index',(msg)=> {    //订阅index事件
                    this.leftTrees = msg;
                })
            },
            methods:{
                selectIt: function (event) {
                    var el = event.currentTarget;
                    var bros = siblings(el);
                    for (let i = 0; i < bros.length; i++) {
                        bros[i].classList.remove("active");
                    }
                    el.classList.add("active");
                }
            }

        }
    );

});

function siblings(elm) {
    var a = [];
    var p = elm.parentNode.children;
    for ( var i =0;i<p.length;i++) {
        a.push(p[i]);
    }
    return a;
}


//加载父菜单资源



