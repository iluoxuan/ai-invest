<template>
  <view class="stock - container">
    <view class="stock - header">
      <text>名称</text>
      <text>代码</text>
      <text>价格</text>
      <text>涨跌幅(%)</text>
      <text>涨跌值</text>
    </view>
    <view class="stock - item" v - for="(stock, index) in stockList" :key="index">
      <text class="stock - name">{{stock.name}}</text>
      <text class="stock - code">{{stock.code}}</text>
      <text :class="['stock - price', stock.change > 0? 'stock - up' : 'stock - down']">{{stock.price}}</text>
      <text :class="['stock - change - percent', stock.change > 0? 'stock - up' : 'stock - down']">{{stock.changePercent}}%</text>
      <text :class="['stock - change - value', stock.change > 0? 'stock - up' : 'stock - down']">{{stock.changeValue}}</text>
    </view>
  </view>
</template>

<script>
export default {
  data() {
    return {
      stockList: [
        {
          name: "南方恒生科技",
          code: "HK3033",
          price: "4.378",
          change: - 3.06,
          changePercent: - 3.06,
          changeValue: - 0.138
        },
        {
          name: "标普500ETF",
          code: "513500",
          price: "2.244",
          change: 0.54,
          changePercent: 0.54,
          changeValue: 0.012
        },
        // 在此处添加其他股票数据，按照图片中的格式依次添加
      ]
    }
  }
}
</script>

<style scoped>
.stock - container {
  display: flex;
  flex - direction: column;
}

.stock - header {
  display: flex;
  justify - content: space - between;
  background - color: #f2f2f2;
  padding: 10px;
  margin - bottom: 10px;
}

.stock - item {
  display: flex;
  justify - content: space - between;
  padding: 10px;
  border - bottom: 1px solid #ccc;
}

.stock - name {
  width: 150px;
}

.stock - code {
  width: 100px;
}

.stock - price {
  width: 100px;
}

.stock - change - percent {
  width: 100px;
}

.stock - change - value {
  width: 100px;
}

.stock - up {
  color: red;
}

.stock - down {
  color: green;
}
</style>