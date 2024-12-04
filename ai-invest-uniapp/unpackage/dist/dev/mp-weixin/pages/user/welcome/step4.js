"use strict";
const common_vendor = require("../../../common/vendor.js");
const api_invest = require("../../../api/invest.js");
const _sfc_main = {
  data() {
    return {};
  },
  methods: {
    async handleClick(item) {
      try {
        const response = await api_invest.initAccount({
          planAmount: "100000",
          totalAmount: "100000",
          monthlyExpenses: "10000",
          monthlyIncome: "10000"
        });
      } catch (error) {
        common_vendor.index.showToast({
          title: "投资账户初始化失败，请重试",
          icon: "none"
        });
      }
    }
  }
};
function _sfc_render(_ctx, _cache, $props, $setup, $data, $options) {
  return {
    a: common_vendor.o(($event) => $options.handleClick("step4"))
  };
}
const MiniProgramPage = /* @__PURE__ */ common_vendor._export_sfc(_sfc_main, [["render", _sfc_render], ["__scopeId", "data-v-aa80cf03"]]);
wx.createPage(MiniProgramPage);
