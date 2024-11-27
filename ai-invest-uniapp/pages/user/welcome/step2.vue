<template>
	<view class="page">

		<view class="tip">

			<view class="tip-text">
				大概预估下您的家庭总资产
			</view>
			<view class="tip-text">
				1. 存款，理财，基金，股票，债券
			</view>
			<view class="tip-text">
				2. 房产，租金，工资，被动收入
			</view>
			<view class="tip-text">
				3. 请您认真思考一分钟，这将对您投资很重要
			</view>
		</view>

		<view class="uni-form-item">
			<input class="uni-input" v-model="inputValue" type="number" placeholder="" @blur="handleBlur" />
		</view>

	</view>
</template>

<script>
	export default {
		data() {
			return {
				inputValue: '' // 初始化输入值
			}
		},
		methods: {

			handleBlur() {
				// 检查输入值是否为空
				if (this.inputValue.trim() != "") {

					this.navigateToNextPage();
				}
			},
			navigateToNextPage() {
				// 使用uni-app的页面跳转方法
				uni.navigateTo({
					url: '/pages/user/welcome/step3' // 替换为目标页面的路径
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