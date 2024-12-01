<!-- components/Typewriter.vue -->
<template>
	<view class="typewriter-container">
		<text class="typewriter-text">{{ currentText }}</text>
		<!-- 可选：添加光标效果 -->
		<span v-if="showCursor" class="cursor"></span>
	</view>
</template>

<script setup>
	import {
		ref,
		watch,
		onMounted,
		onUnmounted
	} from 'vue';

	// 定义 props
	const props = defineProps({
		text: {
			type: String,
			required: true,
		},
		speed: {
			type: Number,
			default: 100, // 默认打字速度为 100ms
		},
		showCursor: {
			type: Boolean,
			default: true, // 是否显示光标
		}
	});

	// 定义需要逐个字符打印的文本
	const originalText = ref(props.text);
	const currentText = ref('');
	let currentIndex = 0;

	// 定义定时器变量
	let timer = null;

	// 打字机效果函数
	const typewriterEffect = () => {
		// 如果还有字符未打印
		if (currentIndex < originalText.value.length) {
			// 更新当前显示的文本
			currentText.value += originalText.value[currentIndex];
			// 移动到下一个字符
			currentIndex++;
			// 设置下一次打印的时间间隔（毫秒）
			timer = setTimeout(typewriterEffect, props.speed);
		} else {
			// 打印完毕后清除定时器
			clearTimeout(timer);
		}
	};

	// 监听 props 的变化，当 text 发生变化时重新开始打字机效果
	watch(() => props.text, (newText) => {
		// 清除旧的定时器
		clearTimeout(timer);
		// 重置状态
		originalText.value = newText;
		currentText.value = '';
		currentIndex = 0;
		// 重新开始打字机效果
		typewriterEffect();
	});

	// 当组件挂载时启动打字机效果
	onMounted(() => {
		typewriterEffect();
	});

	// 确保在组件卸载时清除定时器
	onUnmounted(() => {
		clearTimeout(timer);
	});
</script>

<style scoped>
	/* 保留基本布局和动画样式 */
	.typewriter-container {
		display: flex;
		justify-content: center;
		align-items: center;
	}

	.cursor {
		margin-left: 2px;
		background-color: black;
		width: 2px;
		height: 1em;
		animation: blink 1s step-end infinite;
	}

	@keyframes blink {
		50% {
			opacity: 0;
		}
	}
</style>