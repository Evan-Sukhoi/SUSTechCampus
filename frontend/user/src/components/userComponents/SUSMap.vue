<template>
  <div class="map">
    <div id="container"></div>
    <div class="navigation-btn-container">
      <button class="navigation-btn" @click="enterNavigation">进入导航</button>
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
        plugins: ['AMap.Driving', 'AMap.LngLat', 'AMap.Walking', 'AMap.ControlBar', 'AMap.ToolBar', 'AMap.Marker', 'AMap.Polygon', 'AMap.InfoWindow'], // 需要使用的的插件列表，如比例尺'AMap.Scale'等
      })
          .then((AMap) => {
            this.AMap = AMap;
            this.map = new this.AMap.Map("container", {
              rotateEnable:true,
              pitchEnable:true,
              pitch: 50,
              rotation: -15,
              viewMode: "3D",
              terrain: false, // 开启地形图
              zoom: 18,
              center: [parseFloat(this.longitude), parseFloat(this.latitude)],
            });

            // 添加工具条
            const toolBar = new this.AMap.ToolBar({
              visible: true, // 设置工具条是否可见
            });
            this.map.addControl(toolBar);

            // 添加控制条
            const controlBar = new this.AMap.ControlBar({
              showZoomBar: true, // 是否显示缩放按钮
              showControlButton: true, // 是否显示旋转、倾斜按钮
              position: {
                right: '10px',
                top: '10px',
              },
            });
            this.map.addControl(controlBar);

            // 创建林恩标记
            var lynnPath = [
              [113.998798, 22.594761],
              [113.997907, 22.594891],
              [113.998016, 22.595381],
              [113.998849, 22.595317]
            ];

            var lynnPolygon = new this.AMap.Polygon({
              path: lynnPath, // 设置多边形边界路径
              strokeColor: "#0000FF", // 设置多边形边界线颜色
              fillColor: "#FF99FF", // 设置多边形填充颜色
              fillOpacity: 0, // 设置透明度
              strokeOpacity: 0, // 设置边界线透明度
              strokeWeight: 3 // 设置边界线宽度
            });

            this.map.add(lynnPolygon);


            lynnPolygon.on('click', function(event) {
              // 创建一个Vue实例
              const InfoWindowContent = new Vue({
                render: (h) => h(MapContent, {
                  props: {
                    buildingId: 1,
                  },
                }),
              });

              // 手动挂载Vue实例
              InfoWindowContent.$mount();
              const contentElement = InfoWindowContent.$el;

              // 创建信息窗体实例
              this.infoWindow = new this.AMap.InfoWindow({
                content: contentElement, // 使用Vue组件实例的元素作为内容
                position: [event.lnglat.getLng(), event.lnglat.getLat()],
                offset: new this.AMap.Pixel(0, 0)
              });

              // 打开信息窗体
              this.infoWindow.open(this.map);

              // 监听组件的关闭事件
              InfoWindowContent.$on('close', () => {
                this.infoWindow.close();
              });
            }.bind(this));

            // 创建一科标记
            var firstSciPath = [
              [113.996666, 22.59672],
              [113.996097, 22.59631],
              [113.996694, 22.595949],
              [113.997031, 22.596502]
            ];

            var firstSciPolygon = new this.AMap.Polygon({
              path: firstSciPath, // 设置多边形边界路径
              strokeColor: "#0000FF", // 设置多边形边界线颜色
              fillColor: "#FF99FF", // 设置多边形填充颜色
              fillOpacity: 0, // 设置透明度
              strokeOpacity: 0, // 设置边界线透明度
              strokeWeight: 3 // 设置边界线宽度
            });

            this.map.add(firstSciPolygon);


            firstSciPolygon.on('click', function(event) {
              // 创建一个Vue实例
              const InfoWindowContent = new Vue({
                render: (h) => h(MapContent, {
                  props: {
                    buildingId: 2,
                  },
                }),
              });

              // 手动挂载Vue实例
              InfoWindowContent.$mount();
              const contentElement = InfoWindowContent.$el;

              // 创建信息窗体实例
              this.infoWindow = new this.AMap.InfoWindow({
                content: contentElement, // 使用Vue组件实例的元素作为内容
                position: [event.lnglat.getLng(), event.lnglat.getLat()],
                offset: new this.AMap.Pixel(0, 0)
              });

              // 打开信息窗体
              this.infoWindow.open(this.map);

              // 监听组件的关闭事件
              InfoWindowContent.$on('close', () => {
                this.infoWindow.close();
              });
            }.bind(this));


            this.map.on("click", this.handleMapClick.bind(this));
          })
          .catch((e) => {
            console.log(e);
          });

    },


    handleMapClick(e) {
      // e.lnglat 获取点击位置的经纬度
      var lng = e.lnglat.getLng();
      var lat = e.lnglat.getLat();
      console.log("你点击了地图在经度" + lng + "，纬度" + lat + "的位置");

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
  height: 700px;
}

#panel {
  position: absolute;
  right: 10%;
  bottom: 80%;
  width: 30%;
  height: 100px;
  z-index: 100;
}

.navigation-btn-container {
  position: absolute;
  right: 10px;
  bottom: 20px;
}

.navigation-btn {
  padding: 10px 20px;
  background-color: #007BFF;
  color: #FFFFFF;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

.navigation-btn:hover {
  background-color: #0056b3;
}
</style>
