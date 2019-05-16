var icon = require("../../apis/icon.js")

Component({
  /**
   * 组件的属性列表
   */
  properties: {
    placeholderText:{
      type: String, // 类型（必填），目前接受的类型包括：String, Number, Boolean, Object, Array, null（表示任意类型）
      value: '', // 属性初始值（可选），如果未指定则会根据类型选择一个
    },
    isShow:{
      type: Boolean,
      value:true,
    },
    query:{
      type: String,
    }
  },

  /**
   * 组件的初始数据
   */
  data: {
      searchIcon:icon.search,
      scan: icon.scan
  },

  /**
   * 组件的方法列表
   */
  methods: {
    scanSearchEvent:function(){
      var that = this;
       wx.scanCode({
         onlyFromCamera: true,
         success: function(res) {
           var val = res.result;
           var searchVal = {
             val: val
           }
           that.triggerEvent("scanSearchEvent", searchVal)
         },
       }) 
    },

    searchEvent: function (e) {
      var that = this;
      var val = e.detail.value; //通过这个传递数据
      var searchVal = {
        val : val
      }// detail对象，提供给事件监听函数
      that.triggerEvent("searchEvent", searchVal)
    },

  }
})
