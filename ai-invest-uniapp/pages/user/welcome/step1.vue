<template>
	<view class="page">

		<view class="tip">

			<view class="tip-text">
				如果你是中长线投资者
			</view>
			<view class="tip-text">
				投资持有周期至少在六个月以上
			</view>
			<view class="tip-text">
				我们将帮助你更好的执行计划
			</view>
			<view class="tip-text">
				请开始您家庭资产盘点
			</view>
		</view>

		<view class="pandian" @click="handleClick('盘点')">
			<view class="pandian-button">
				盘点
			</view>
		</view>

	</view>
</template>

<script>
	export default {
		data() {
			return {

			}
		},
		methods: {
			
			handleClick(item) {
				console.log(`点击了 ${item}`);
				uni.navigateTo({
					url: '/pages/user/welcome/step2'
				});
			}

		}
	}
</script>

<style lang="scss" scoped>
	.page {
		// 布局 flex
		display: flex;
		// 按列排
		flex-direction: column;
		align-items: center;
		height: 100vh;
		background-color: #fcfcfc;
		/* 设置背景颜色 */
	}

	.tip {
		/* 段落之间间距 */
		text-align: left;
		margin-top: 200rpx;
		margin-bottom: 50rpx;
	}

	.tip-text {
		font-size: 30rpx;
		color: #dca476;
		/* 设置文字颜色 */
		padding: 10rpx;

	}

	.pandian {
		margin-top: 100rpx;
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

	}

	.pandian-button {
		/* 水平居中 */
		text-align: center;
		box-sizing: border-box;
		color: #dca476;
		/* 参考图片中的文字颜色 */
		line-height: 1.2;
		/* 控制行高 */
		font-weight: bold;
		/* 字体加粗 */
		font-size: 45rpx;
	}
</style>