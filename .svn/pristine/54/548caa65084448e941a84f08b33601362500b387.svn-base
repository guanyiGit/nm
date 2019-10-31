// pages/home/antiepidemic/antiepidemic.js
var image = require("../../../../apis/image.js")
var config = require("../../../../apis/config.js")
var dateFormat = require("../../../../apis/dateFormat.js")
var http = require("../../../../apis/request.js")
var httpSync = require("../../../../apis/request_sync.js")

var app = getApp();

Page({

  /**
   * 页面的初始数据
   */
  data: {
    component: {
      placeholderText: "溯源ID/项圈编号/犬主姓名/手机号/身份证号",
      isShow: false
    },
    antiepidemicArray: [],
    ishasData: true,
    maxLength: 6, //默认最大显示8个
    params: {
      offset: 0,
      limit: 6,
    }
  },

  /**
   * 跳转到犬只详情
   */
  toantiepidemicInfoPage(e) {
    var that = this;
    var index = e.currentTarget.dataset.index;
    var antiepidemic_index = JSON.stringify(that.data.antiepidemicArray[index]);
    wx.navigateTo({
      url: '/pages/admin/home/antiepidemic/info/info?antiepidemic_index=' + antiepidemic_index,
    })
  },


  // 根据组织或者防疫员搜索犬主
  searchAntiepidemicByBox(e) {
    var that = this;
    if (app.globalData.isTownAdmin) {
      var epidemicer = e.detail.val; //防疫员 
      if (!epidemicer) {
        that.setData({
          antiepidemicArray: [],
          maxLength: 6,
          params: {
            offset: 0,
            limit: 6,
          },
          ["query.protector"]: ""
        });
      } else {
        that.setData({
          antiepidemicArray: [],
          maxLength: 6,
          params: {
            offset: 0,
            limit: 6,
          },
          ["query.protector"]: epidemicer.id
        });
      }
      var query = that.data.query;
      that.getList(query);
    } else {
      var org = e.detail.val;
      that.setData({
        antiepidemicArray: [],
        maxLength: 6,
        params: {
          offset: 0,
          limit: 6,
        },
        ["query.orgId"]: org.id
      });
      var query = that.data.query;
      that.getList(query);
    }
  },

  /**
   * 防疫统计详情
   */
  toStatisticalDetailsPage() {
    wx.navigateTo({
      url: '/pages/admin/home/statisticalDetails/statisticalDetails',
    })
  },

  /**
   * 搜索查询
   */
  searchAntiepidemic(e) {
    var that = this;
    that.setData({
      antiepidemicArray: [],
      params: {
        offset: 0,
        limit: 6,
      },
      ["query.string"]: e.detail.val
    });

    this.data.params.offset = 0;

    var query = that.data.query;
    this.getScanList(query);
  },

  scanSearchAntiepidemic(e) {
    var that = this;
    that.setData({
      antiepidemicArray: [],
      params: {
        offset: 0,
        limit: 6,
      },
      ["query.string"]: e.detail.val
    });
    var query = e.detail.val;
    this.getScanList(query);
  },


  //获取列表
  getList(query, fun,isAppend = true) {
    var that = this;
    var url = config.url.BASE_URL + "/antiepidemic/pagelist";
    var token = app.globalData.token;
    var params = that.data.params;
    if (query != undefined) {
      if (query.string != undefined) {
        params.string = query.string
      }
      if (query.protector != undefined) {
        params.protector = query.protector
      }
      if (query.orgId != undefined) {
        params.orgId = query.orgId
      }
      if (query.startTime != undefined) {
        params.startTime = query.startTime
      }
      if (query.endTime != undefined) {
        params.endTime = query.endTime
      }
    }
    http.httpGet(url, token, params, function(res) {
      var antiepidemicArray = res.rows;
      for (var index in antiepidemicArray) {
        if (antiepidemicArray[index].date != '' && antiepidemicArray[index].date != null) {
          antiepidemicArray[index].date = dateFormat(antiepidemicArray[index].date, "yyyy-MM-dd HH:mm");
        }
        if (antiepidemicArray[index].type == 0) {
          antiepidemicArray[index].typeClass = "spring-epidemic"
        } else if (antiepidemicArray[index].type == 1) {
          antiepidemicArray[index].typeClass = "fall-epidemic"
        } else if (antiepidemicArray[index].type == 2) {
          antiepidemicArray[index].typeClass = "month-epidemic"
        }
      }
      if (!isAppend){
        that.data.antiepidemicArray = [];
        that.data.params = {
          offset: 0,
          limit: 6,
        }
      }
      that.setData({
        antiepidemicArray: that.data.antiepidemicArray.concat(antiepidemicArray)
      })
      typeof fun == "function" && fun(antiepidemicArray);
    })
  },


  //获取列表
  getScanList(query) {
    var that = this;
    var url = config.url.BASE_URL + "/antiepidemic/pagelist";
    var token = app.globalData.token;
    var params = that.data.params;
    if (query != undefined) {
      if (query.string != undefined) {
        params.string = query.string
      }
      if (query.protector != undefined) {
        params.protector = query.protector
      }
      if (query.orgId != undefined) {
        params.orgId = query.orgId
      }
    }
    http.httpGet(url, token, params, function(res) {
      if (!res.rows || res.rows.length <= 0) {
        that.setData({
          antiepidemicArray: []
        })
        return;
      }
      var antiepidemicArray = res.rows;
      for (var index in antiepidemicArray) {
        if (antiepidemicArray[index].date != '' && antiepidemicArray[index].date != null) {
          antiepidemicArray[index].date = dateFormat(antiepidemicArray[index].date, "yyyy-MM-dd HH:mm");
        }
        if (antiepidemicArray[index].type == 0) {
          antiepidemicArray[index].typeClass = "spring-epidemic"
        } else if (antiepidemicArray[index].type == 1) {
          antiepidemicArray[index].typeClass = "fall-epidemic"
        } else if (antiepidemicArray[index].type == 2) {
          antiepidemicArray[index].typeClass = "month-epidemic"
        }
      }
      that.setData({
        antiepidemicArray: antiepidemicArray
      })
    })
  },


  setNavigationBarColor() {
    wx.setNavigationBarColor({
      frontColor: '#FFFFFF',
      backgroundColor: '#3343A5',
    })
  },

  setNavigationBarTitle() {
    wx.setNavigationBarTitle({
      title: '犬只防疫',
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    var that = this;
    // that.setNavigationBarTitle();
    // that.setNavigationBarColor();
    that.setData({
      antiepidemicArray: [],
      maxLength: 6,
      params: {
        offset: 0,
        limit: 6
      },
      isLoad: true
    });
    var query = that.data.query;
    that.getList(query, function(antiepidemicArray) {
      if (!antiepidemicArray || antiepidemicArray.length == 0) {
        that.setData({
          ishasData: false
        })
        return;
      }
    });
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function() {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function() {
    var that = this;
    if (that.data.isLoad) {
      that.setData({
        isLoad: false
      })
      return;
    }
    that.setData({
      antiepidemicArray: [],
      maxLength: 6,
      params: {
        offset: 0,
        limit: 6
      }
    });
    var query = that.data.query;
    that.getList(query, function(data) {
      if (data && data.length > 0) {
        that.setData({
          ishasData: true
        })
        return;
      }
    });
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function() {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function() {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function() {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function() {
    var that = this;
    var query = that.data.query;
    that.setData({
      maxLength: that.data.maxLength * 2,
      params: {
        offset: that.data.params.offset + 6,
        limit: 6
      }
    });
    that.getList(query);
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function() {

  },
  conditionChanged(e) {
    const {
      selectDate: {
        start,
        end
      },
      typeRange: {
        array,
        index
      }
    } = e.detail;
    let month = end.split("-")[1] - 1
    if ((month + "").length == 1){
      month = "0" + month
    }
    this.setData({
      ['query.startTime']: start,
      ['query.endTime']: end.split("-")[0] + "-" + month ,
    })
    this.data.params.offset = 0;

    // //开始时间
    // this.data.params.startTime = start;
    // this.data.query.startTime = start;
    // //结束时间
    // this.data.params.endTime = end;
    // this.data.query.endTime = end;

    //防疫类型 0春防 1秋芳 2 月月投药
    if (index > 0) {
      this.data.params.type = index - 1;
      this.data.query.type = index - 1;
    }

    this.getList(this.data.query,null,false);
    
  }
})