<template>
  <el-card class="cdkey-card">
    <div class="greeting">
      {{ $t('lang.thanking') }}
    </div>
    <div class="cdkey-label">{{ $t('lang.cdk') }}</div>
    <div class="cdkey-box">
      <div class="cdkey-value">{{ cdk }}</div>
    </div>
    <div class="instructions">
      {{ $t('lang.cdkHint') }}
    </div>
    <el-button type="primary" @click="redirectToService">服务页面</el-button>
  </el-card>
</template>

<script>
export default {
  data() {
    return {
      cdk: '',
    };
  },
  methods: {
    redirectToService() {
      this.$router.push('/user/service');
    },
    getCdkey() {
      const outTradeNo = this.$route.query.out_trade_no;
      this.$http.get(`http://localhost:8081/public/get-cdkey?orderId=${outTradeNo}`).then((res) => {
        console.log(res);
        this.cdk = res.data.cdkey;
      }).catch((err) => {
        console.error('Failed to fetch cdkey:', err);
      });
    },
  },
  mounted() {
    this.getCdkey();
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
