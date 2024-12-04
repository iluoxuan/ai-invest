"use strict";
const common_vendor = require("../../../common/vendor.js");
const _sfc_main = {
  data() {
    return {
      inputValue: ""
      // 初始化输入值
    };
  },
  methods: {
    handleBlur() {
      if (this.inputValue.trim() != "") {
        this.navigateToNextPage();
      }
    },
    navigateToNextPage() {
      common_vendor.index.navigateTo({
        url: "/pages/user/welcome/step3"
        // 替换为目标页面的路径
      });
    }
  }
};
function _sfc_render(_ctx, _cache, $props, $setup, $data, $options) {
  return {
    a: common_vendor.o((...args) => $options.handleBlur && $options.handleBlur(...args)),
    b: $data.inputValue,
    c: common_vendor.o(($event) => $data.inputValue = $event.detail.value)
  };
}
const MiniProgramPage = /* @__PURE__ */ common_vendor._export_sfc(_sfc_main, [["render", _sfc_render], ["__scopeId", "data-v-43fae573"]]);
wx.createPage(MiniProgramPage);
