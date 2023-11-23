<template>
  <el-menu
      default-active="2"
      class="el-menu-vertical-demo"
      @open="handleOpen"
      @close="handleClose"
      style="height: 100%;">
    <el-menu-item index="2">
      <i class="el-icon-menu"></i>
      <span slot="title">{{$t('lang.reservationRecord')}}</span>
    </el-menu-item>
  </el-menu>
</template>
<script>
export default {
  methods: {
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    }
  }
}
</script>