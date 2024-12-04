"use strict";
const common_vendor = require("../common/vendor.js");
const _sfc_main = {
  __name: "Typewriter",
  props: {
    text: {
      type: String,
      required: true
    },
    speed: {
      type: Number,
      default: 100
      // 默认打字速度为 100ms
    },
    showCursor: {
      type: Boolean,
      default: true
      // 是否显示光标
    },
    onComplete: {
      type: Function,
      // 回调函数，当打字完成时调用
      default: null
      // 如果没有提供回调函数，则默认为 null
    }
  },
  emits: ["complete"],
  setup(__props, { emit: __emit }) {
    const props = __props;
    const emit = __emit;
    const originalText = common_vendor.ref(props.text);
    const currentText = common_vendor.ref("");
    let currentIndex = 0;
    let timer = null;
    const typewriterEffect = () => {
      if (currentIndex < originalText.value.length) {
        currentText.value += originalText.value[currentIndex];
        currentIndex++;
        timer = setTimeout(typewriterEffect, props.speed);
      } else {
        clearTimeout(timer);
        emit("complete");
        console.log("clearTimeout after");
        if (props.onComplete) {
          console.log("onComplete start");
          props.onComplete();
          console.log("onComplete end");
        }
      }
    };
    common_vendor.watch(() => props.text, (newText) => {
      clearTimeout(timer);
      originalText.value = newText;
      currentText.value = "";
      currentIndex = 0;
      typewriterEffect();
    });
    common_vendor.onMounted(() => {
      typewriterEffect();
    });
    common_vendor.onUnmounted(() => {
      clearTimeout(timer);
    });
    return (_ctx, _cache) => {
      return {
        a: common_vendor.t(currentText.value)
      };
    };
  }
};
const Component = /* @__PURE__ */ common_vendor._export_sfc(_sfc_main, [["__scopeId", "data-v-1e44b6e9"]]);
wx.createComponent(Component);
