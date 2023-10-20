<template>
  <div class="map">
    <div id="container"></div>
    <div id="panel"></div>
    <div>
      <p>经度: {{ longitude }}</p>
      <p>纬度: {{ latitude }}</p>
      <button @click="getLocation">获取位置</button>
    </div>
  </div>
</template>
<script>
import AMapLoader from "@amap/amap-jsapi-loader";
import MapContent from "@/components/userComponents/MapContent.vue";
import Vue from "vue";

window._AMapSecurityConfig = {
  securityJsCode: '0631f670d1dec9cd73d8d4dca2075792',
}
export default {
  name: "SUSMap",
  data() {
    return {
      driving: '',
      longitude: 114.000725,
      latitude: 22.595509,
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
              center: [parseFloat(this.longitude), parseFloat(this.latitude)],
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
      var buidlingID = '';

      if (this.infoWindow) {
        this.infoWindow.close();
      }
      if (lng >= 113.99837 && lng < 113.9985 && lat >= 22.59501 && lat < 22.59513) {
        buidlingID = '1';

        // 创建一个Vue实例
        const InfoWindowContent = new Vue({
          render: (h) => h(MapContent, {
            props: {
              buildingId: buidlingID,
            },
          }),
        });

        // 手动挂载Vue实例
        InfoWindowContent.$mount();
        const contentElement = InfoWindowContent.$el;

        // 创建信息窗体实例
        this.infoWindow = new this.AMap.InfoWindow({
          content: contentElement, // 使用Vue组件实例的元素作为内容
          position: [lng, lat],
          offset: new this.AMap.Pixel(0, 0)
        });

        // 打开信息窗体
        this.infoWindow.open(this.map);

        // 监听组件的关闭事件
        InfoWindowContent.$on('close', () => {
          this.infoWindow.close();
        });
      }
    },

    getLocation() {
      if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(
            position => {
              this.longitude = position.coords.longitude;
              this.latitude = position.coords.latitude;
              this.map.setCenter([parseFloat(this.longitude), parseFloat(this.latitude)])
            },
            error => {
              console.error('无法获取位置信息', error);
            }
        );
      } else {
        console.error('浏览器不支持 Geolocation API');
      }
    },
  },

}
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