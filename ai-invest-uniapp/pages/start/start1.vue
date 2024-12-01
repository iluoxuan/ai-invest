<!-- pages/YourPage.vue -->
<template>
	<view>
		<!-- 使用 Typewriter 组件 -->
		<Typewriter :text="message" :speed="75" :showCursor="false" />
	</view>
</template>

<script setup>
	import Typewriter from '@/components/Typewriter.vue'; // 引入打字机组件

	// 定义页面使用的数据
	const message = "这是一个来自其他页面的打字机效果示例！这是一个来自其他页面的打字机效果示例这是一个来自其他页面的打字机效果示例这是一个来自其他页面的打字机效果示例";
</script>

<style scoped>
	/* 页面特定样式 */
	::v-deep .typewriter-text {
		font-size: 30rpx;
		color: #dca476;
		/* 设置文字颜色 */
		line-height: 1.5;
	}
</style>