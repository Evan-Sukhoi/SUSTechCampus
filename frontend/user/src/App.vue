<template>
  <router-view/>
</template>

<script>
export default {
  mounted() {
    setTimeout(() => {
      window.L2Dwidget.init({
        pluginRootPath: 'live2dw/',
        pluginJsPath: 'lib/',
        pluginModelPath: 'live2d-widget-model-z16/assets/',
        tagMode: false,
        debug: false,
        model: { jsonPath: '../live2dw/live2d-widget-model-z16/assets/z16.model.json' },
        display: { position: 'left', width: 100, height: 200 },
        mobile: { show: true },
        log: false
      })
    }, 1000)
  },
}
</script>