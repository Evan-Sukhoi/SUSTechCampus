<template>
  <el-card class="cdkey-card">
    <div class="greeting">
      {{ $t('lang.thanking') }}
    </div>
    <div class="cdkey-label">{{ $t('lang.cdk') }}</div>
    <div class="cdkey-box">
      <!-- 使用vue-qrcode组件显示二维码 -->
      <qrcode-vue :value="qrData" class="qrcode"></qrcode-vue>
      <div class="cdkey-value">ABC123-DEF456-GHI789</div>
    </div>
    <div class="instructions">
      {{ $t('lang.cdkHint') }}
    </div>
    <el-button type="primary" @click="redirectToService">服务页面</el-button>
  </el-card>
</template>

<script>
import QrcodeVue from 'vue-qrcode';

export default {
  components: {
    QrcodeVue,
  },
  data() {
    return {
      qrData: 'ABC123-DEF456-GHI789', // 设置二维码的值
    };
  },
  methods: {
    redirectToService() {
      // 处理跳转到服务页面的逻辑
    },
  },
};
</script>

<style scoped>
.cdkey-card {
  margin: 20px;
  background-color: #f0f8ff; /* 浅蓝色背景 */
  text-align: center;
  padding: 20px;
}

.greeting {
  font-size: 28px; /* 调大字号 */
  color: #000; /* 黑色文字 */
  margin-bottom: 15px;
}

.cdkey-label {
  font-size: 24px; /* 调大字号 */
  color: #007bff; /* 蓝色文字 */
  margin-bottom: 5px;
}

.cdkey-box {
  border: 2px solid #007bff; /* 蓝色边框 */
  padding: 10px;
  display: inline-block;
  background: linear-gradient(135deg, #f0f8ff 25%, #CFF8FF 25%, #CFF8FF 50%, #f0f8ff 50%, #f0f8ff 75%, #CFF8FF 75%, #CFF8FF 100%); /* 蓝白条纹背景 */
  background-size: 20px 20px; /* 条纹大小 */
  border-radius: 10px; /* 圆角 */
}

.cdkey-value {
  font-size: 40px;
  color: #000000;
}

.instructions {
  font-size: 20px; /* 调大字号 */
  color: #333;
  margin-top: 15px;
  margin-bottom: 20px;
}

.el-button {
  margin-top: 10px;
}
</style>
