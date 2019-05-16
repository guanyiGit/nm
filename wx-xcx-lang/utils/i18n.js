// /utils/i18n.js
let T = {
  locale: null,
  locales: {},
  langCode: ['ZH_CN', 'ZH_BO', 'ZH_MN'],
  STORAGE_KEY: 'lang_code'
}

T.registerLocale = function(locales) {
  T.locales = locales;
  T.setLocale(wx.getStorageSync(T.STORAGE_KEY) || T.langCode[0])
}

T.get = function (key = 'undefined') {
  return T.getLanguage()[key]
}

T.setLocale = function(code) {
  T.locale = code;
  wx.setStorageSync(T.STORAGE_KEY, T.locale)
}

T.setLocaleByIndex = function(index) {
  T.setLocale(T.langCode[index]);
}

T.getLocaleIndex = function() {
  return T.langCode.findIndex(x => x == T.locale) || 0;
}


T.getLanguage = function() {
  return T.locales[T.locale];
}

export default T