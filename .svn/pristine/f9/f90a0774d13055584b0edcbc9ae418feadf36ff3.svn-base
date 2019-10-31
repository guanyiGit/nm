$(function () {
    // 统计犬龄分布
    getDogAge();
    // 统计犬种分布
    getDogBreed();
    // 统计犬性别分布
    getDogSex();
    // 区域分布
    if(roleId == 5 || roleId == 6 || roleId ==9 || roleId ==10) {
        getDogAreaInfo();
    }

})

function getDogBreed() {
   $.ajax({
       type: "get",
       url: "/biz/dogCount/getDogBreed",
       async: false,
       success: function (result) {
           if(result) {
               // console.log(result);
               var breedData = result;
               initEhcart4(breedData);
           }
       }
   })
}
function getDogSex() {
    $.ajax({
        type: "get",
        url: "/biz/dogCount/getDogSex",
        async: false,
        success: function (result) {
            if(result) {
                console.log(result);
                for (var i = 0; i < result.length;i++) {
                    // alert(a.name);
                    // alert(a.value);
                    if(result[i].name == 0) {
                        result[i].name = dogMale;
                    }else {
                        result[i].name = dogFemale;
                    }
                }
                var breedData = result;
                initEhcart5(breedData);
            }
        }
    })
}
function getDogAge() {
    $.ajax({
        type: "get",
        url: "/biz/dogCount/getDogAge",
        async: false,
        success: function (result) {
            if(result) {
                // console.log(result);
                var breedData = result;
                for (var i=0;i<breedData.length;i++) {
                    if (breedData[i].name=="3岁以下") {
                        breedData[i].name=threeAgeFollowing;
                    }else if (breedData[i].name=="3~5岁") {
                        breedData[i].name=threeToFiveAnnum;
                    }else if (breedData[i].name=="5~8岁") {
                        breedData[i].name=fiveToEightAnnum;
                    }else if (breedData[i].name=="8~10岁") {
                        breedData[i].name=eightToTenAnnum;
                    }else if (breedData[i].name=="10~15岁") {
                        breedData[i].name=tenToFifteenAnnum;
                    }else if (breedData[i].name=="15岁以上") {
                        breedData[i].name=fifteenAgeAbove;
                    }
                }
                initEhcart6(breedData);
            }
        }
    })
}
function getDogAreaInfo() {
    $.ajax({
        type: "get",
        url: "/biz/dogCount/getDogAreaInfo",
        async: false,
        success: function (result) {
            if(result) {
                // console.log(result);
                var breedData = result;
                initEchart7(breedData);
            }
        }
    })
}

function initEhcart4(breedData) {
    var myChart4 = echarts.init(document.getElementById('main4'));
    option = {
        tooltip : {
            trigger: 'item',
            formatter: "{b} : {c} ({d}%)"
        },

        series : [
            {
                // name: '',
                type: 'pie',
                radius : '55%',
                center: ['50%', '60%'],
                data:breedData
                    /*[
                    {value:335, name:'中华田园犬（13.08%）'},
                    {value:310, name:'哈士奇（13.08%）'},
                    {value:335, name:'贵宾犬（9.13%）'},
                    {value:310, name:'拉布拉多（5.27%）'},
                    {value:335, name:'雪纳瑞（60.42%）'},
                    {value:335, name:'博美（13.08%）'},
                    {value:310, name:'比熊犬（13.08%）'},
                    {value:335, name:'金毛（9.13%）'},
                    {value:310, name:'边境牧羊犬（5.27%）'},
                    {value:335, name:'萨摩耶（60.42%）'},
                    {value:335, name:'其他（60.42%）'}
                ]*/,
                itemStyle: {
                    emphasis: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
    };

    myChart4.setOption(option);
}

function initEhcart5(breedData) {
    var myChart5 = echarts.init(document.getElementById('main5'));
    option = {
        tooltip: {
            trigger: 'item',
            formatter: "{b}: {c} ({d}%)"
        },

        series: [
            {
                // name:'',
                type:'pie',
                radius: ['40%', '60%'],
                // label: {
                //     normal: {
                //         backgroundColor: '#eee',
                //         borderColor: '#aaa',
                //         borderWidth: 1,
                //         borderRadius: 4,
                //         rich: {
                //             a: {
                //                 color: '#999',
                //                 lineHeight: 22,
                //                 align: 'center'
                //             },
                //             hr: {
                //                 borderColor: '#aaa',
                //                 width: '100%',
                //                 borderWidth: 0.5,
                //                 height: 0
                //             },
                //             b: {
                //                 fontSize: 16,
                //                 lineHeight: 33
                //             },
                //             per: {
                //                 color: '#eee',
                //                 backgroundColor: '#334455',
                //                 padding: [2, 4],
                //                 borderRadius: 2
                //             }
                //         }
                //     }
                // },
                data: breedData
                //     [
                //     {value:135, name:'母'},
                //     {value:310, name:'公'}
                //
                // ]
            }
        ]
    };
    myChart5.setOption(option);
}


function initEhcart6(breedData) {
    var myChart6 = echarts.init(document.getElementById('main6'));
    option = {
        tooltip : {
            trigger: 'item',
            formatter: "{b} : {c} ({d}%)"
        },

        series : [
            {
                name:radiusModel,
                type:'pie',
            },
            {
                // name:'',
                type:'pie',
                radius : [20, 110],
                center : ['50%', '50%'],
                roseType : 'area',
                data: breedData
                //     [
                //     {value:10, name:'5岁以上'},
                //     {value:5, name:'8岁以上'},
                //     {value:15, name:'10岁以上'},
                //     {value:25, name:'15岁以上'},
                //     {value:20, name:'18岁以上'},
                //     {value:35, name:'20岁以上'},
                // ]
            }
        ]
    };

    myChart6.setOption(option);
}

function initEchart7(breedData) {
    var myChart7 = echarts.init(document.getElementById('main7'));
    option = {
        tooltip : {
            trigger: 'item',
            formatter: "{b} : {c} ({d}%)"
        },

        series : [
            {
                // name: '访问来源',
                type: 'pie',
                radius : '55%',
                center: ['50%', '60%'],
                data: breedData
                //     [
                //     {value:335, name:'中华田园犬（13.08%）'},
                //     {value:310, name:'哈士奇（13.08%）'},
                //     {value:335, name:'贵宾犬（9.13%）'},
                //     {value:310, name:'拉布拉多（5.27%）'},
                //     {value:335, name:'雪纳瑞（60.42%）'},
                //     {value:335, name:'博美（13.08%）'},
                //     {value:310, name:'比熊犬（13.08%）'},
                //     {value:335, name:'金毛（9.13%）'},
                //     {value:310, name:'边境牧羊犬（5.27%）'},
                //     {value:335, name:'萨摩耶（60.42%）'},
                //     {value:335, name:'其他（60.42%）'}
                // ]
                ,
                itemStyle: {
                    emphasis: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
    };
    myChart7.setOption(option);

}
