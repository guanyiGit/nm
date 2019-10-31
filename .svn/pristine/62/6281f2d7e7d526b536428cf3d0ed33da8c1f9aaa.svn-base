/**
 * name: api.js
 * description: request处理基础类
 * author: 彭雨
 * date: 2018-5-19
 */
var config = require("config.js"); //引入配置文件文件

var LANG = {
  "LANG": wx.getStorageSync("lang_code"),
  "UID": wx.getStorageSync("userInfo").userId
}

function Get(url, token, params, cb) {
  if (!token) {
    wx.navigateTo({
      url: '/pages/login/login',
    })
    return;
  }
  wx.showLoading({
    // title: '加载中',
    title: wx.T.get("jiazaizhong"),
    mask: true
  })

  wx.request({
    url: url,
    data: params,
    header: {
      "Content-Type": "application/x-www-form-urlencoded", //跨域请求
      "token": token,
      "LANG": LANG.LANG,
      "UID": LANG.UID
    },
    method: 'GET',
    dataType: 'json',
    success: (res) => {
      wx.hideLoading();
      if (res.header.statusCode == 401) {
        wx.clearStorageSync();
        wx.showModal({
          // title: '登录超时',
          title: wx.T.get("denglvchaoshi"),
          // content: '登录超时,请重新登录',
          content: wx.T.get("denglvchaoshi") + "," + wx.T.get("chongxindenglv"),
          showCancel: false,
          success: function(res) {
            if (res.confirm) {
              wx.navigateTo({
                url: '/pages/login/login',
              })
            }
          }
        })
        return;
      }
      return typeof cb == "function" && cb(res.data);
    },
    fail: (err) => {
      return typeof cb == "function" && cb(false);
    },
    complete: (res) => {
      wx.hideLoading();
    }
  })
};

function Post(url, token, params, cb) {
  if (!token) {
    wx.navigateTo({
      url: '/pages/login/login',
    })
    return;
  }
  wx.showLoading({
    title: '加载中',
    mask: true
  })
  wx.request({
    url: url,
    data: params,
    header: {
      "Content-Type": "application/x-www-form-urlencoded", //表单请求
      "token": token,
      "LANG": LANG.LANG,
      "UID": LANG.UID
    },
    method: 'POST',
    success: (res) => {
      if (res.header.statusCode == 401) {
        wx.clearStorageSync();
        wx.showModal({
          // title: '登录超时',
          title: wx.T.get("denglvchaoshi"),
          // content: '登录超时,请重新登录',
          content: wx.T.get("denglvchaoshi") + "," + wx.T.get("chongxindenglv"),
          showCancel: false,
          success: function(res) {
            if (res.confirm) {
              wx.navigateTo({
                url: '/pages/login/login',
              })
            }
          }
        })
        return;
      }
      return typeof cb == "function" && cb(res.data, "");
    },
    fail: (err) => {
      return typeof cb == "function" && cb(null, err.errMsg);
      console.log("post 请求:" + config.BASE_URL);
      console.log(err);
    },
    complete: (res) => {
      wx.hideLoading();
    }
  });
};

function JSONPost(url, token, params, cb) {
  wx.showLoading({
    // title: '加载中',
    title: wx.T.get("jiazaizhong"),
    mask: true
  })
  wx.request({
    url: url,
    data: params,
    header: {
      "Content-Type": "application/json;charset=UTF-8", //表单请求
      "token": token,
      "LANG": LANG.zh,
      "UID": LANG.UID
    },
    method: 'POST',
    dataType: 'json',
    success: (res) => {
      if (res.header.statusCode == 401) {
        wx.clearStorageSync();
        wx.showModal({
          // title: '登录超时',
          title: wx.T.get("denglvchaoshi"),
          // content: '登录超时,请重新登录',
          content: wx.T.get("denglvchaoshi") + "," + wx.T.get("chongxindenglv"),
          showCancel: false,
          success: function(res) {
            if (res.confirm) {
              wx.navigateTo({
                url: '/pages/login/login',
              })
            }
          }
        })
        return;
      }
      return typeof cb == "function" && cb(res.data, "");
    },
    fail: (err) => {
      return typeof cb == "function" && cb(null, err.errMsg);
    },
    complete: (res) => {
      wx.hideLoading();
    }
  });
};


function http(url, method = "GET", data, header = {}, isAuth = true) {
  url = config.url.BASE_URL + url;
  header = Object.assign({
    "Content-Type": "application/x-www-form-urlencoded",
    "LANG": LANG.LANG,
    "UID": LANG.UID
  }, header)

  if (isAuth) {
    try {
      const token = wx.getStorageSync("token")
      if (!token) {
        throw "token err";
      }
      header.token = token;
    } catch (res) {
      wx.navigateTo({
        url: '/pages/login/login',
      })
      return new Promise((resolve, reject) => { reject(res)})
    }
  }

  wx.showLoading({
    title: wx.T.get("jiazaizhong"),
    mask: true
  })
  return new Promise((resolve, reject) => {
    wx.request({
      url,
      method,
      data,
      header,
      success: (res) => {
        try {
          if (res.header.statusCode == 401) {
            wx.clearStorageSync();
            wx.showModal({
              title: wx.T.get("denglvchaoshi"),
              content: wx.T.get("denglvchaoshi") + "," + wx.T.get("chongxindenglv") + " !",
              showCancel: false,
              success: function(res) {
                if (res.confirm) {
                  wx.navigateTo({
                    url: '/pages/login/login',
                  })
                }
              }
            })
          }
        } catch (err) {
          console.log(err)
        }
        resolve(res)
      },
      fail: (err) => {
        reject(err)
      },
      complete: (res) => {
        wx.hideLoading();
      }
    })

  });
};

//暴露接口
module.exports = {
  httpGet: Get,
  httpPost: Post,
  JSONPost: JSONPost,
  http: http
}