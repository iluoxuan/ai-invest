"use strict";
const common_vendor = require("../../common/vendor.js");
const _sfc_main = {
  data() {
    return {};
  },
  methods: {
    handleClick(item) {
      console.log(`点击了 ${item}`);
      common_vendor.index.navigateTo({
        url: "/pages/start2/start2"
      });
    }
  }
};
function _sfc_render(_ctx, _cache, $props, $setup, $data, $options) {
  return {
    a: common_vendor.o(($event) => $options.handleClick("盘点"))
  };
}
const MiniProgramPage = /* @__PURE__ */ common_vendor._export_sfc(_sfc_main, [["render", _sfc_render], ["__scopeId", "data-v-aef3fc1e"]]);
wx.createPage(MiniProgramPage);
