<template>
  <div>
    <div class="map">
      <div id="container"></div>

      <!--    <div class="navigation-btn-container">-->
      <!--      <button class="navigation-btn" @click="enterNavigation">进入导航</button>-->
      <!--    </div>-->
    </div>

    <div class="map_content">

      <div class="navi">
        <div class="coordinate-box">
          <button  @click="chooseStart">起始点坐标</button>
          <div class="coordinate-text">{{ startPoint }}</div>
        </div>

        <div class="coordinate-box">
          <button  @click="chooseEnd">终点坐标</button>
          <div class="coordinate-text">{{ endPoint }}</div>
        </div>

        <button  @click="startNavigation">出发</button>


      </div>

      <div class="busline">
        <div>
          <label for="startBuilding">起始建筑：</label>
          <select v-model="startBuilding" id="endBuilding">
            <!-- 这里添加起始点的选项 -->
            <option value="a">A</option>
            <option value="b">B</option>
            <!-- 添加更多选项 -->
          </select>
        </div>

        <div>
          <label for="endBuilding">目标建筑：</label>
          <select v-model="endBuilding" id="endBuilding">
            <!-- 这里添加目标点的选项 -->
            <option value="c">C</option>
            <option value="d">D</option>
            <!-- 添加更多选项 -->
          </select>
        </div>

        <button v-if="startBuilding && endBuilding" @click="seeBusline">查看公交线路</button>

      </div>

      <div class="walk">

      </div>

    </div>
  </div>
</template>
<script>
import AMapLoader from "@amap/amap-jsapi-loader";
import locations from "../../assets/location/location.json"

import MapContent from "@/components/userComponents/MapContent.vue";
import Vue from "vue";

window._AMapSecurityConfig = {
  securityJsCode: '012338515576425f8fdff9a7f8404d60',
}
export default {
  name: "SUSMap",
  data() {
    return {
      driving: '',
      longitude: 114.000725,
      latitude: 22.595509,
      infoWin: '',
      startBuilding: "",
      endBuilding: "",
      startPoint: "",
      endPoint: "",
    }
  },
  mounted() {
    this.initAMap();
  },
  unmounted() {
    this.map?.destroy();
  },
  watch: {
    "$i18n.locale": function (newVal) {
      if (newVal === 'zh-CN') {
        this.map.setLang('zh_cn');
        console.log('zh-cn')
      } else {
        this.map.setLang('en');
        console.log('en')
      }
    }
  },
  methods: {
    initAMap() {
      AMapLoader.load({
        key: "2a44874f8490449d6ecc495f738c674b", // 申请好的Web端开发者Key，首次调用 load 时必填
        version: "1.4.15", // 指定要加载的 JSAPI 的版本，缺省时默认为 1.4.15
        plugins: ['AMap.Driving', 'AMap.LngLat', 'AMap.Walking', 'AMap.ControlBar', 'AMap.ToolBar', 'AMap.Marker', 'AMap.Polygon', 'AMap.InfoWindow'], // 需要使用的的插件列表，如比例尺'AMap.Scale'等
      })
          .then((AMap) => {
            this.AMap = AMap;
            this.map = new this.AMap.Map("container", {
              rotateEnable: true,
              pitchEnable: true,
              pitch: 50,
              rotation: -15,
              viewMode: "3D",
              terrain: false, // 开启地形图
              zoom: 18,
              center: [parseFloat(this.longitude), parseFloat(this.latitude)],
              lang: this.$i18n.locale === 'zh-CN' ? 'zh_cn' : 'en',
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

            for (const location of locations) {
              const id = location.id;
              const institutePath = location.location;
              var institutePolygon = new this.AMap.Polygon({
                path: institutePath, // 设置多边形边界路径
                strokeColor: "#0000FF", // 设置多边形边界线颜色
                fillColor: "#FF99FF", // 设置多边形填充颜色
                fillOpacity: 0, // 设置透明度
                strokeOpacity: 0, // 设置边界线透明度
                strokeWeight: 3 // 设置边界线宽度
              });

              this.map.add(institutePolygon);

              institutePolygon.on('click', function (event) {
                // 创建一个Vue实例

                const InfoWindowContent = new Vue({
                  render: (h) => h(MapContent, {
                    props: {
                      buildingId: id,
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
                  InfoWindowContent.$destroy();
                });
              }.bind(this));


            }

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
      this.latitude = lat;
      this.longitude = lng;
      console.log("你点击了地图在经度" + lng + "，纬度" + lat + "的位置");
      if (this.infoWindow) {
        this.infoWindow.close();
      }
    },

    seeBusline() {
      console.log("查看公交线路：", this.startBuilding, "到", this.endBuilding);
    },
    startNavigation() {
      // 在这里执行开始导航的操作，可以使用 this.startPoint 和 this.endPoint 的值

      // 你可以根据需要进行导航或其他操作
      // 下面是一个示例将用户导航到 /user/building/{startPoint}/to/{endPoint} 的路由
      // router.push(`/user/building/${this.startPoint}/to/${this.endPoint}`).catch(err => err);
    },

    chooseStart() {
      this.startPoint = [this.latitude, this.longitude];
    },

    chooseEnd() {
      this.endPoint = [this.latitude, this.longitude];
    }


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
  z-index: 1;
}

.map_content {
  position: absolute;
  top: 0;
  left: 100px;
  z-index: 2; /* 设置一个大于1的z-index值 */
}

.map_content {
  display: block;
}

.busline {
  display: flex;
}

.navi {
  display: flex;
}
</style>
