// components/dateSelector/dateSelector.js
Component({
  /**
   * 组件的属性列表
   */
  properties: {
    isShow: {
      type: Boolean,
      value: false
    }
  },

  /**
   * 组件的初始数据
   */
  data: {
    startDate: "",
    endDate: "",
  },

  /**
   * 组件的方法列表
   */
  methods: {
    /**开始日期change事件 */
    bindStartDateChange: function(e) {
      var that = this;
      that.setData({
        ["startDate"]: e.detail.value
      });
    },

    /**结束日期change事件 */
    bindEndDateChange: function(e) {
      var that = this;
      that.setData({
        ["endDate"]: e.detail.value
      });
    },



    reset() {
      var that = this;
      that.setData({
        startDate: "",
        endDate: "",
      })
    },

    confirm() {
      var that = this;
      var startDate = that.data.startDate;
      var endDate = that.data.endDate;
      var dateString = "";
      if (startDate != undefined && startDate == "") {
        wx.showModal({
          title: wx.T.get("tishi"),
          // content: '请选择开始时间',
          content: wx.T.get("qingxuanzekaishishijian"),
          showCancel: false,
          confirmText:wx.T.get("quedin")
        })
        return;
      } else {
        dateString += startDate
      }
      if (endDate != undefined && endDate == "") {
        // dateString += "   -   至今"
        dateString += "   -   " + wx.T.get("zhijin")
      } else {
        dateString += "   -   " + endDate
      }
      that.setData({
        isChoose: true,
        dateString: dateString,
        isShow: false,
      })
      var eventDetail = {
        val: {
          isChoose: true,
          dateString: dateString,
          startDate: startDate,
          endDate: endDate
        }
      }
      this.triggerEvent("dataEvent", eventDetail);
    }
  },
  //生命周期函数
  attached: function() {
    this.setData({
      L: wx.T
    })
  }
})