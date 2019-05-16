var config = require("../../apis/config.js");
var icon = require("../../apis/icon.js");
var http = require("../../apis/request.js")
var httpSync = require("../../apis/request_sync.js")
var app = getApp();

Component({
  /**
   * 组件的属性列表
   */
  properties: {
    imageList: {
      type: Array
    },
  },



  /**
   * 组件的初始数据
   */
  data: {
    imageList_local:[],
    text: ["xxxx", "xxxx", "xxxxx"],
    deleteIcon:"/assets/icon/index/deleteIcon.png"
  },

  // 生命周期函数，可以为函数，或一个在methods段中定义的方法名
  attached() {
    this.setData({ L: wx.T })
 
  },
  
  ready(){
    let that = this;
    let imageList = that.properties.imageList;
    var imageList_local = that.data.imageList_local;
    imageList.forEach((item,index) => {
      imageList_local.push(item)
    })
    that.setData({
      imageList_local: imageList_local
    })
  },

  /**
   * 组件的方法列表
   */
  methods: {
    //预览图片
    previewImage(e) {
      //获取当前图片的下标
      var index = e.currentTarget.dataset.index;
      //所有图片
      var imageUrlList = [];
      this.data.imageList_local.forEach((item,index) => {
        imageUrlList.push(item.url)
      })
      wx.previewImage({
        //当前显示图片
        current: imageUrlList[index],
        //所有图片
        urls: imageUrlList
      })
    },

    /**
     * 删除图片
     */
    deleteImage(e) {
      console.log(e);
      var that = this;
      var index = e.currentTarget.dataset.index; //获取当前长按图片下标
      var imageList_local = that.data.imageList_local;
      console.log(imageList_local);
      wx.showModal({
        title: wx.T.get("tishi"),
        // content: '确定要删除此图片吗？',
        content: wx.T.get("quedingyaoshanchucitupianma")+"?",
        cancelText:wx.T.get("cancel"),
        confirmText:wx.T.get("quedin"),
        success: function(res) {
          if (res.confirm) {
            console.log(imageList_local[index]);
            if (imageList_local[index].id){
              var url = config.url.BASE_URL + "/dogInfo/deleteFile";
              var token = app.globalData.token;
              var params = {
                fid: imageList_local[index].id
              }
              http.httpGet(url, token, params, (res) => {
                console.log(res);
              })
            }
            imageList_local.splice(index, 1);
          } else {
            return false;
          }
          that.setData({
            imageList_local: imageList_local
          })
        }
      })
    },

    //选择图片
    chooseImage() {
      // var that =this;
      wx.chooseImage({
        count: config.maxImage,
        sizeType: ['original', 'compressed'],
        sourceType: ['album', 'camera'], 
        success: (res) => {
          console.log(res);
          // 返回选定照片的本地文件路径列表，tempFilePath可以作为img标签的src属性显示图片
          if (res.tempFilePaths.length > 3) {
            wx.showToast({
              // title: '最多上传三张',
              title: wx.T.get("zuiduoshangchuansanzhang"),
              icon: 'none',
              duration: 1500,
              mask: false,
            })
            return;
          }
          var imageList_local = this.data.imageList_local;
          var imageLength = imageList_local.length + res.tempFilePaths.length;
          if (imageLength > 3) {
            wx.showToast({
              // title: '最多上传三张',
              title: wx.T.get("zuiduoshangchuansanzhang"),
              icon: 'none',
              duration: 1500,
              mask: false,
            })
            return;
          }
          var tempFilePaths = res.tempFilePaths;
          for (var index in tempFilePaths) {
            var tempFilePath = {
              url: tempFilePaths[index]
            }
            imageList_local.push(tempFilePath);
          }
          this.setData({
            imageList_local: imageList_local
          })
        },
      })
    },

    /**
     * 上传图片
     */
    uploadImage(id, type) {
      var that = this;
      var imageList_local = that.data.imageList_local;
      for (var index in imageList_local) {
        if (imageList_local[index].id){
          continue;
        }
        debugger
        wx.uploadFile({
          url: config.url.BASE_URL + "/manureDisposal/uploadPic",
          filePath: imageList_local[index].url,
          name: 'files',
          header: {
            "Content-Type": "application/x-www-form-urlencoded",
            "token": app.globalData.token
          },
          formData: {
            id: id,
            type: type,
          },
          success: function(res) {
            console.log(res)
          },
        })
      }
    }
  },



})