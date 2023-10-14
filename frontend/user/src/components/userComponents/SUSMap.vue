<template>
  <div class="map">
    <div id="container"></div>
    <div id="panel"></div>
  </div>
</template>
<script>
import AMapLoader from "@amap/amap-jsapi-loader";

window._AMapSecurityConfig = {
  securityJsCode: '0631f670d1dec9cd73d8d4dca2075792',
}
export default {
  name: "SUSMap",
  data() {
    return {
      driving: '',
    }
  },
  mounted() {
    this.initAMap();
  },
  unmounted() {
    this.map?.destroy();
  },
  methods: {
    initAMap() {
      AMapLoader.load({
        key: "d647a0c3b5f5de68bc23204f7365fc97", // 申请好的Web端开发者Key，首次调用 load 时必填
        version: "1.4.15", // 指定要加载的 JSAPI 的版本，缺省时默认为 1.4.15
        plugins: ['AMap.Driving', 'AMap.LngLat', 'AMap.Walking'], // 需要使用的的插件列表，如比例尺'AMap.Scale'等
      })
          .then((AMap) => {
            this.AMap = AMap;
            this.map = new this.AMap.Map("container", {
              viewMode: "2D",
              zoom: 18,
              center: [114.000725, 22.595509],
            });
            this.driving = new this.AMap.Driving({
              map: this.map,
              panel: "panel"
            });
            this.map.on("click", this.handleMapClick.bind(this));
          })
          .catch((e) => {
            console.log(e);
          });

    },

    findDrivingWay([a, b], [c, d]) {
      this.driving.search(new this.AMap.LngLat(a, b), new this.AMap.LngLat(c, d), function (status, result) {
        console.log("start")
        if (status === 'complete') {
          console.log('绘制驾车路线完成')
        } else {
          console.log('获取驾车数据失败：' + result)
        }
      });
    },

    handleMapClick(e) {
      // e.lnglat 获取点击位置的经纬度
      var lng = e.lnglat.getLng();
      var lat = e.lnglat.getLat();
      console.log("你点击了地图在经度" + lng + "，纬度" + lat + "的位置");
    }



  },


};

</script>
<style scoped>
.map {
  position: relative;
}

#container {
  width: 100%;
  height: 800px;
}

#panel {
  position: absolute;
  right: 10%;
  bottom: 80%;
  width: 30%;
  height: 100px;
  z-index: 100;
}
</style>