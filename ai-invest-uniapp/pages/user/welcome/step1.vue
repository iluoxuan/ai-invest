<template>
	<view class="page">
		<view class="tip-container">
			<Typewriter :text="msg" :speed="180" :showCursor="false" @complete='handleOnComplete' />
		</view>

		<view v-if="showPandian" class="pandian-container" @click="handleClick('盘点')">
			<view class="pandian-button">
				盘点
			</view>
		</view>
	</view>
</template>

<script>
	import Typewriter from '@/components/Typewriter.vue';

	const tipMsg = "为您量身定制中长线投资策略，精选优质股票。了解您的家庭财务后，助您明智决策，降低风险，实现长期收益。";

	export default {



		components: {
			Typewriter
		},
		data() {
			return {
				msg: tipMsg,
				showPandian: false
			}
		},
		methods: {
			handleClick(item) {

				console.log(`点击了 ${item}`);
				uni.navigateTo({
					url: '/pages/user/welcome/step2'
				});
			},
			handleOnComplete() {
				console.log('打字机完成回调');
				setTimeout(() => {
					this.showPandian = true;
				}, 500); // 你可以根据需要调整等待时间
			}
		}
	}
</script>

<style lang="scss" scoped>
	/* 设置全局页面背景颜色 */
	.page {
		background-color: #F9F9F9;
		/* 微信小程序常用的浅灰色背景 */
		min-height: 100vh;
		/* 确保背景色覆盖整个视口高度 */
		padding: 40rpx;
		/* 给页面一些内边距 */
		box-sizing: border-box;
	}

	/* 提示信息容器 */
	.tip-container {
		text-align: left;
		/* 居中对齐提示信息 */
		margin-top: 100rpx;
		/* 调整与顶部的距离 */
		margin-left: 50rpx;
		margin-right: 50rpx;

		::v-deep .typewriter-text {
			font-size: 33rpx;
			/* 适中的字体大小 */
			color: #dca476;
			/* 深灰色文本颜色，确保可读性 */
			line-height: 1.5;
			/* 增加行高，使文本更易读 */
			text-align: left;
			/* 确保打字机文本也居中对齐 */
		}
	}

	/* 盘点按钮容器 */
	.pandian-container {
		margin-top: 100rpx;
		/* 与提示信息保持适当距离 */
		width: 60%;
		/* 适中的宽度，适合小屏设备 */
		max-width: 600rpx;
		/* 设置最大宽度，避免在大屏幕上过宽 */
		margin-left: auto;
		margin-right: auto;
		/* 居中对齐 */
		padding: 20rpx;
		/* 内边距让内容不至于贴边 */
		background-color: #fcebe3;
		/* 白色背景，突出按钮 */
		border-radius: 8rpx;
		/* 圆角稍微加大 */
		display: flex;
		align-items: center;
		justify-content: center;
	}

	/* 盘点按钮 */
	.pandian-button {
		text-align: center;
		color: #1a2847;
		/* 白色文字，与按钮背景形成对比 */
		line-height: 1.5;
		font-weight: bold;
		font-size: 40rpx;
		/* 适中的按钮文字大小 */
		padding: 20rpx 40rpx;
		/* 增加内边距，使按钮看起来更大 */
		border: none;
		border-radius: 8rpx;
	}
</style>