<template>
	<view class="page">
		<view class="prompt-container">
			<view class="prompt-text">
				如果你是中长线投资，三个月以上
			</view>
			<view class="prompt-text">

			</view>

		</view>
		<view class="prompt-text">
			请点击开始盘点大概家庭资产
		</view>
		<view class="item" @click="handleClick('盘点')">
			<view class="content">
				<text class="title">盘点</text>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {};
		},
		methods: {
			handleClick(item) {
				console.log(`点击了 ${item}`);
				uni.navigateTo({
					url: '/pages/start/stepTwo'
				});
			}
		}
	};
</script>

<style lang="scss" scoped>
	.page {
		display: flex;
		flex-direction: column;
		align-items: center;
		justify-content: center;
		min-height: 95vh;
		background-color: #fcfcfc;
		/* 设置背景颜色 */
		position: relative;
		/* 使 prompt-container 能够相对于 page 定位 */
	}

	.prompt-container {
		width: 80%;
		/* 控制提示框的宽度 */
		margin-bottom: 120rpx;
		text-align: left;
		/* 文本左对齐 */
	}

	.prompt-text {
		font-size: 36rpx;
		color: #dca476;
		/* 设置文字颜色 */
		margin-bottom: 10rpx;
		/* 段落之间间距 */
	}

	.item {
		width: 45%;
		height: 150rpx;
		background-color: #fcebe3;
		/* 参考图片中的背景色 */
		border-radius: 10rpx;
		overflow: hidden;
		display: flex;
		align-items: center;
		/* 垂直居中 */
		justify-content: center;
		/* 水平居中 */

		.content {
			text-align: center;
			/* 文字水平居中 */
			padding: 20rpx;
			box-sizing: border-box;

			.title {
				font-size: 45rpx;
				color: #dca476;
				/* 参考图片中的文字颜色 */
				line-height: 1.2;
				/* 控制行高 */
				font-weight: bold;
				/* 字体加粗 */
			}
		}
	}
</style>