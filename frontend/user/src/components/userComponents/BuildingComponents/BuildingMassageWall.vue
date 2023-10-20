<template>
  <div id="word-cloud"></div>
</template>

<script>

export default {
  props: {
    words: Array, // 输入的词云数据
  },

}
</script>

<style>
/* 添加一些样式 */
#word-cloud svg {
  background-color: #f0f0f0;
}
</style>
