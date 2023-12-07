<template>
  <router-view/>
</template>

<script>
export default {
  methods:{
    handleResize() {
      const isSmallScreen = window.matchMedia('(max-width: 768px)').matches;
      if (!isSmallScreen){
        this.createLive2d()
      }
    },
    createLive2d(){
      setTimeout(() => {
        window.L2Dwidget.init({
          pluginRootPath: 'live2dw/',
          pluginJsPath: 'lib/',
          pluginModelPath: 'live2d-widget-model-haru_2/assets/',
          tagMode: false,
          debug: false,
          model: { jsonPath: '../live2dw/live2d-widget-model-haru_2/assets/haru02.model.json' },
          display: { position: 'right', width: 200, height: 450 },
          mobile: { show: true },
          log: false,
        })
      }, 1000)
    }
  },
  beforeMount() {
    this.handleResize()
  }

}
</script>