"use strict";
const common_vendor = require("../../../common/vendor.js");
const Typewriter = () => "../../../components/Typewriter.js";
const tipMsg = "为您量身定制中长线投资策略，精选优质股票。了解您的家庭财务后，助您明智决策，降低风险，实现长期收益。";
const _sfc_main = {
  components: {
    Typewriter
  },
  data() {
    return {
      msg: tipMsg,
      showPandian: false
    };
  },
  methods: {
    handleClick(item) {
      console.log(`点击了 ${item}`);
      common_vendor.index.navigateTo({
        url: "/pages/user/welcome/step2"
      });
    },
    handleOnComplete() {
      console.log("打字机完成回调");
      setTimeout(() => {
        this.showPandian = true;
      }, 500);
    }
  }
};
if (!Array) {
  const _component_Typewriter = common_vendor.resolveComponent("Typewriter");
  _component_Typewriter();
}
function _sfc_render(_ctx, _cache, $props, $setup, $data, $options) {
  return common_vendor.e({
    a: common_vendor.o($options.handleOnComplete),
    b: common_vendor.p({
      text: $data.msg,
      speed: 180,
      showCursor: false
    }),
    c: $data.showPandian
  }, $data.showPandian ? {
    d: common_vendor.o(($event) => $options.handleClick("盘点"))
  } : {});
}
const MiniProgramPage = /* @__PURE__ */ common_vendor._export_sfc(_sfc_main, [["render", _sfc_render], ["__scopeId", "data-v-5337137c"]]);
wx.createPage(MiniProgramPage);
