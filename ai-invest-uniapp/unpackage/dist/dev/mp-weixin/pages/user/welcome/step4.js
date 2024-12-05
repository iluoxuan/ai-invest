"use strict";
const common_vendor = require("../../../common/vendor.js");
const config_env = require("../../../config/env.js");
const url = `${config_env.env.INVEST_URL}/api/account/init`;
console.log("url", url);
const reqData = {
  planAmount: "100000",
  totalAmount: "100000",
  monthlyExpenses: "10000",
  monthlyIncome: "10000"
};
const _sfc_main = {
  data() {
    return {};
  },
  methods: {
    async handleClick(item) {
      try {
        const response = await common_vendor.index.request({
          url,
          method: "POST",
          header: {
            "content-type": "application/json"
          },
          data: reqData,
          complete: (res) => {
            console.log("请求完成:", res);
            if (res.data.success) {
              common_vendor.index.navigateTo({
                url: "/pages/user/init/home"
              });
              return;
            }
            throw new Error(res.data.msg);
          }
        });
      } catch (error) {
        console.error("投资账户初始化发生错误:", error.message);
        throw error;
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
