<template>
	<view class="container">
		<!-- 总资产、总盈亏、当日参考盈亏 -->
		<view class="summary-row">
			<view class="item">
				<text class="title">总资产</text>
				<text class="amount">{{ totalAssets }}</text>
			</view>
			<view class="item">
				<text class="title">总市值</text>
				<text class="amount">{{ totalProfitLoss }}</text>
			</view>
			<view class="item">
				<text class="title">总计划</text>
				<text class="amount">{{ dailyReferencePL }}</text>
			</view>
		</view>

		<view class="summary-row">
			<view class="item">
				<text class="title">总盈亏</text>
				<text class="amount">{{ totalAssets }}</text>
			</view>
			<view class="item">
				<text class="title">可用</text>
				<text class="amount">{{ totalProfitLoss }}</text>
			</view>
			<view class="item">
				<text class="title">天数</text>
				<text class="amount">{{ dailyReferencePL }}</text>
			</view>
		</view>

		<!-- 持仓分时 -->
		<view class="holdings">
			<view class="header">
				<text class="column-title">市值</text>
				<text class="column-title">盈亏</text>
				<text class="column-title">持仓</text>
				<text class="column-title">成本/现价</text>
			</view>
			<view class="row" v-for="(holding, index) in holdings" :key="index">
				<text class="column-value">阿里巴巴{{ holding.marketValue }}</text>
				<text class="column-value">{{ holding.profitLoss }}</text>
				<text class="column-value">{{ holding.holdings }}</text>
				<text class="column-value">{{ holding.costPrice }} / {{ holding.currentPrice }}</text>
			</view>
		</view>

		<view class="action">

			<view class="action-add" @click="handleClick('加仓')">
				<view class="action-add-text">
					加仓
				</view>
			</view>

			<view class="action-add" @click="handleClick('市场')">
				<view class="action-add-text">
					市场
				</view>
			</view>

			<view class="action-add" @click="handleClick('市场')">
				<view class="action-add-text">
					减仓
				</view>
			</view>

		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				totalAssets: "1,075,244.75",
				totalProfitLoss: "-553.51",
				dailyReferencePL: "653.00",
				marketValue: "1,032,932.65",
				availableBalance: "42,262.10",
				withdrawableBalance: "42,262.10",
				holdings: [{
						marketValue: "1,000,000.00",
						profitLoss: "0.00%",
						holdings: "10000",
						available: "0",
						costPrice: "100.000",
						currentPrice: "100.000"
					},
					{
						marketValue: "23,814.45",
						profitLoss: "2.140%",
						holdings: "300",
						available: "300",
						costPrice: "HK$83.216",
						currentPrice: "HK$85.000"
					},
					{
						marketValue: "6,379.20",
						profitLoss: "0.250%",
						holdings: "1600",
						available: "1600",
						costPrice: "3.977",
						currentPrice: "3.987"
					},
					{
						marketValue: "2,739.00",
						profitLoss: "1.410%",
						holdings: "100",
						available: "100",
						costPrice: "27.010",
						currentPrice: "27.390"
					}
				]
			};
		}
	};
</script>

<style lang="scss" scoped>
	.container {
		display: flex;
		flex-direction: column;
		background-color: #fcfcfc;
		height: 100vh;

		.summary-row {
			display: flex;
			justify-content: space-around;
			margin-top: 50rpx;
			margin-bottom: 20rpx;


			.item {
				display: flex;
				flex-direction: column;
				align-items: center;
				text-align: center;

				.title {
					color: rgba(0, 0, 0, 0.6);
					font-size: 28rpx;
				}

				.amount {
					font-weight: bold;
					font-size: 30rpx;
					color: #dca476;
				}
			}
		}

		.holdings {
			margin-top: 20rpx;
			margin-bottom: 25rpx;
			background-color: #ffffff;

			.header {
				display: flex;
				justify-content: space-between;
				background-color: #fcebe3;
				padding: 10rpx 0;

				.column-title {
					flex: 1;
					text-align: center;
					font-size: 28rpx;
					color: rgba(0, 0, 0, 0.6);
				}
			}

			.row {
				display: flex;
				justify-content: space-between;
				align-items: center;
				padding: 20rpx 0;
				box-shadow: 0 1px 0 #fcebe3;
				/* 使用 box-shadow 模拟细线 */
				/* 添加底部边框 */

				.column-value {
					flex: 1;
					text-align: center;
					font-size: 28rpx;
					color: #ea3538;
				}

				&:last-child {
					box-shadow: none;
					/* 隐藏最后一个列表项的细线 */
				}
			}
		}

		.action {

			display: flex;
			flex-direction: row;
			margin: 25rpx;
			margin-top: 50rpx;

			.action-add {
			    margin-left: 50rpx;
				width: 24%;
				height: 150rpx;
				background-color: #fcebe3;
				/* 参考图片中的背景色 */
				border-radius: 15rpx;
				overflow: hidden;
				display: flex;
				align-items: center;
				/* 垂直居中 */
				justify-content: center;
				
			}
			
			.action-add-text{
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

		}
	}
</style>