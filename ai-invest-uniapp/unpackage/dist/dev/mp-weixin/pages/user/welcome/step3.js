"use strict";
const common_vendor = require("../../../common/vendor.js");
const _sfc_main = {
  data() {
    return {};
  },
  methods: {
    handleClick(item) {
      console.log(`点击了 ${item}`);
      common_vendor.index.navigateTo({
        url: "/pages/user/welcome/step4"
      });
    }
  }
};
function _sfc_render(_ctx, _cache, $props, $setup, $data, $options) {
  return {
    a: common_vendor.o(($event) => $options.handleClick("step3"))
  };
}
const MiniProgramPage = /* @__PURE__ */ common_vendor._export_sfc(_sfc_main, [["render", _sfc_render], ["__scopeId", "data-v-b1fcb6e8"]]);
wx.createPage(MiniProgramPage);
