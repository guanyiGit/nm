var dateFormat = require("../../apis/dateFormat.js")
var config = require("../../apis/config.js")
var http = require("../../apis/request.js")
var httpSync = require("../../apis/request_sync.js")

var app = getApp();

Component({
  /**
   * 组件的属性列表
   */
  properties: {

  },

  /**
   * 组件的初始数据
   */
  data: {
    isTownAdmin: false,
    isChoose: false,
    regionIndex: [],
    regionArray: [
      [{
        name: "海北州"
      }],
      [{
        name: "海晏县"
      }, {
        name: "其他县"
      }],
      [{
        name: "甘子河乡"
      }, {
        name: "青海湖乡"
      }]
    ],
    protector: {
      isChoose: false,
      protectorIndex: 0,
      protectorArray: []
    }
  },

  /**
   * 组件的方法列表
   */
  methods: {
    changeEvent(e) {
      var that = this;
      if (that.data.isTownAdmin) {
        that.changeprotector(e);
      } else {
        that.changeRegion(e);
      }
    },


    //防疫员下拉
    changeprotector(e) {
      var that = this;
      var index = e.detail.value
      this.setData({
        ['protector.protectorIndex']: index,
        ['protector.isChoose']: true,
      });
      var eventDetail = {};
      if (index == 0) {
        eventDetail = {
          val: ""
        }
      } else {
        eventDetail = {
          val: that.data.protector.protectorArray[index]
        }
      }
      this.triggerEvent("dataEvent", eventDetail);
    },

    //value值改变时执行
    changeRegion(e) {
      var that = this;
      console.log("xxxx", e.detail.value);
      this.setData({
        regionIndex: e.detail.value,
        isChoose: true
      });
      var regionArray = that.data.regionArray;
      var regionIndex = that.data.regionIndex;
      var selectOrg = "";
      for (var i in regionIndex) {
        var index = regionIndex[i];
        if (i > 0) {
          if (index == 0) {
            break;
          } else {
            selectOrg = regionArray[i][regionIndex[i]]
          }
        } else {
          selectOrg = regionArray[i][regionIndex[i]]
        }
      }
      that.setData({
        regionString: selectOrg.areaName
      })
      var eventDetail = {
        val: selectOrg
      }
      this.triggerEvent("dataEvent", eventDetail);
    },

    //某一列的值改变时触发
    changeRegionColumn(e) {
      var that = this;
      var regionArray = that.data.regionArray;
      var regionIndex = that.data.regionIndex;
      regionIndex[e.detail.column] = e.detail.value;
      if (regionArray.length == 3) {
        //当改变列为第二列时，动态加载第三列(因为第二列加上了全部选项(下标+1)，所以获取值时对应的region下标-1))
        if (e.detail.column == 1) {
          var thirdColumn = [{
            // areaName: "全部",
            areaName: wx.T.get("quanbu"),
          }];
          if (e.detail.value == 0) {
            thirdColumn = []
          } else {
            var countyIndex = e.detail.value - 1;
            console.log(that.data.region[0].children[countyIndex]);
            for (var index in that.data.region[0].children[countyIndex].children) {
              thirdColumn.push(that.data.region[0].children[countyIndex].children[index]);
            }
          }
          regionArray[2] = thirdColumn;
        }
        that.setData({
          regionArray: regionArray
        })
      }
    },

    //更多
    more(e) {
      wx.showModal({
        // title: '提示',
        title: wx.T.get("tishi"),
        // content: '敬请期待',
        content: wx.T.get("jingqingqidai"),
        showCancel: false
      })
    },

    //获取默认展示
    regionPicker(data) {
      console.log(data);
      var that = this;
      var regionArray = [];
      var regionIndex = [];
      //添加第一列
      var firstColumn = data;
      if (firstColumn && firstColumn.length > 0) {
        regionArray.push(firstColumn);
        regionIndex.push(0);
        //添加第二列
        if (data[0].children && data[0].children.length > 0) {
          var secondArray = [{
            // areaName: "全部",
            areaName: wx.T.get("quanbu"),
          }];
          for (var index in data[0].children) {
            secondArray.push(data[0].children[index]);
          }
          regionArray.push(secondArray);
          regionIndex.push(0);
          if (secondArray[1].children && secondArray[1].children.length > 0) {
            var thirdArray = [];
            //添加第三列(默认添加第一列第一个选项)
            regionArray.push(thirdArray);
            regionIndex.push(0);
          }
        }
      }
      that.setData({
        regionArray: regionArray,
        regionIndex: regionIndex
      })
    },
  },



  //生命周期函数
  attached: function () {
    this.setData({ L: wx.T })
    var that = this;
    var isTownAdmin = app.isTownAdmin();
    that.setData({
      isTownAdmin: isTownAdmin
    })
    if (that.data.isTownAdmin) {
      var url = config.url.BASE_URL + "/dogInfo/findProtector3";
      var token = app.globalData.token;
      var params = {};
      http.httpGet(url, token, params, function (res) {
        console.log("xxxxx", res);
        var protectorArray = [];
        for (var index in res) {
          protectorArray.push(res[index]);
        }
        that.setData({
          ['protector.protectorArray']: protectorArray
        })
      })
    } else {
      var url = config.url.BASE_URL + "/orgInfo/initOrgSelect2";
      var token = app.globalData.token;
      var params = {};
      http.httpGet(url, token, params, function (res) {
        that.setData({
          region: res
        })
        that.regionPicker(res);
      })
    }

  }
})