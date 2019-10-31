// components/lang/lang.js
var {
  http
} = require("../../apis/request.js")
var conf = require("../../apis/config.js")
Component({
  /**
   * 组件的属性列表
   */
  properties: {

  },
  attached() {
    this.setData({
      L: wx.T
    })
    this.setData({
      LANG: {
        array: wx.T.langCode,
        index: wx.T.getLocaleIndex()
      }
    })
  },
  /**
   * 组件的初始数据
   */
  data: {
    LANG: {
      array: wx.T.langCode,
      index: wx.T.getLocaleIndex()
    },
  },

  /**
   * 组件的方法列表
   */
  methods: {
    langChanged(e) {
      // this.setData({ L: wx.T})
      this.data.LANG.index = e.detail.value;
      this.setData({
        LANG: this.data.LANG
      })
      const currUrl = "/" + getCurrentPages().filter(x => x.data.L)[0].is;
      wx.T.setLocaleByIndex(this.data.LANG.index);

      wx.reLaunch({
        url: currUrl,
      })

      // getCurrentPages().filter(x => x.data.L).forEach(x=>{
      //   x.data.L = wx.T
      //   x.setData(x.data)
      //   console.log(x)
      // })

      http("/mutliLang/changLangInfo", "GET", null, {
          "LANG": wx.T.locale
        }, true)
        .then(res => {
          console.log(res)
        })
    }
  }
})