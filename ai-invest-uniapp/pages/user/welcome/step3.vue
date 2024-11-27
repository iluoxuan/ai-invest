<template>
	<view class="page">

		<view class="tip">

			<view class="tip-text">
				你家庭总资产约 10万元

			</view>
			<view class="tip-text">
				现在请您从以下方面评估自己的负债
			</view>
			<view class="tip-text">
				1. 房贷，水电煤，物业费，停车费
			
			</view>
			<view class="tip-text">
				2. 吃喝玩乐，交通，房租，油费
			</view>
			<view class="tip-text">
				3. 孩子教育，老人赡养
			</view>
			
		</view>
		<view class="uni-form-item">
			<input class="uni-input" type="number" placeholder="" @blur="handleClick('step3')"/>
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
					url: '/pages/user/welcome/step4'
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

	.uni-form-item {
		margin-top: 100rpx;
		width: 50%;
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

	.uni-input {
		height: 48px;
		font-size: 15px;
		padding: 0px;
		flex: 1;
		color: #dca476;
	}

	.uni-icon {
		width: 24px;
		height: 24px;
	}
</style>