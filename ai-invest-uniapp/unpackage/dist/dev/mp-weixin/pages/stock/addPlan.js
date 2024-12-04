"use strict";
const common_vendor = require("../../common/vendor.js");
const _sfc_main = {
  data() {
    return {
      totalAssets: "1,075,244.75",
      totalProfitLoss: "-553.51",
      dailyReferencePL: "653.00",
      marketValue: "1,032,932.65",
      availableBalance: "42,262.10",
      withdrawableBalance: "42,262.10",
      holdings: [
        {
          marketValue: "1,000,000.00",
          profitLoss: "0.00%",
          holdings: "10000",
          available: "0",
          costPrice: "100.000",
          currentPrice: "100.000"
        },
        {
          marketValue: "23,814.45",
          profitLoss: "2.140%",
          holdings: "300",
          available: "300",
          costPrice: "HK$83.216",
          currentPrice: "HK$85.000"
        },
        {
          marketValue: "6,379.20",
          profitLoss: "0.250%",
          holdings: "1600",
          available: "1600",
          costPrice: "3.977",
          currentPrice: "3.987"
        },
        {
          marketValue: "2,739.00",
          profitLoss: "1.410%",
          holdings: "100",
          available: "100",
          costPrice: "27.010",
          currentPrice: "27.390"
        }
      ]
    };
  },
  methods: {
    addStock() {
      this.navigateToNextPage();
    },
    navigateToNextPage() {
      common_vendor.index.navigateTo({
        url: "/pages/stock/add"
        // 替换为目标页面的路径
      });
    }
  }
};
function _sfc_render(_ctx, _cache, $props, $setup, $data, $options) {
  return {
    a: common_vendor.f($data.holdings, (holding, index, i0) => {
      return {
        a: common_vendor.t(holding.profitLoss),
        b: common_vendor.t(holding.profitLoss),
        c: common_vendor.t(holding.holdings),
        d: common_vendor.t(holding.costPrice),
        e: common_vendor.t(holding.holdings),
        f: index
      };
    })
  };
}
const MiniProgramPage = /* @__PURE__ */ common_vendor._export_sfc(_sfc_main, [["render", _sfc_render], ["__scopeId", "data-v-f787fe35"]]);
wx.createPage(MiniProgramPage);
