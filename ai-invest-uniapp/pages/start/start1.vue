<template>
	<view class="container">
		<view class="tip">

			你当前的账号有股票 阿里巴巴 10W港币

		</view>

		<view class="itemContainer">

			<view class="item" @click="handleClick('稳钱账户')">
				<view class="content">
					<text class="title">左侧加仓</text>
				</view>
			</view>
			<view class="item" @click="handleClick('海外长钱')">
				<view class="content">
					<text class="title">右侧加仓</text>
				</view>
			</view>

		</view>

	</view>
</template>

<script>
	export default {
		methods: {
			handleClick(item) {
				console.log(`点击了 ${item}`);
				// 这里可以添加跳转到详细页面的逻辑
			}
		}
	};
</script>

<style lang="scss" scoped>
	.container {
		display: flex;
		flex-direction: column;
		padding: 20rpx;
		width: 100%;
		height: 100vh;
		background-color: #fcfcfc;
	}

	.tip {

		margin-top: 100rpx;
		margin-bottom: 100rpx;

	}

	.itemContainer {
		display: flex;
		justify-content: flex-start;
	}

	.item {
		width: 40%;
		margin-right: 50rpx;
		height: 300rpx;
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