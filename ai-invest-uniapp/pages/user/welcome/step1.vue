<template>
	<view class="page">

		<view class="tip">

			<text>
				如果你是中长线投资者,投资持有周期至少在六个月以上
				我们将帮助你更好的执行计划,请开始您家庭资产盘点
			</text>

		</view>

		<view class="tip">
			<!-- 使用 Typewriter 组件并传递文本和速度参数 -->
			<Typewriter :text="msg" :speed="180" :showCursor="false" />
		</view>

		<view class="pandian" @click="handleClick('盘点')">
			<view class="pandian-button">
				盘点
			</view>
		</view>

	</view>
</template>

<script>
	import Typewriter from '@/components/Typewriter.vue'; // 引入打字机组件


	export default {
		components: {
			Typewriter
		},
		data() {
			return {
				msg: "如果你是中长线投资者,投资持有周期至少在六个月以上我们将帮助你更好的执行计划,请开始您家庭资产盘点"
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
		margin: 200rpx 50rpx 100rpx 100rpx;

		::v-deep .typewriter-text {
			font-size: 30rpx;
			color: #dca476;
			/* 设置文字颜色 */
			line-height: 1.5;
		}
	}

	.tip text {
		font-size: 30rpx;
		color: #dca476;
		/* 设置文字颜色 */
		line-height: 1.5;

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