<template>
  <div id="out_container">
    <div class="map">
      <div id="container"></div>
      <div id="panel" v-if="showPanel"></div>
    </div>

    <div class="map_content">

      <div class="navi">
        <button  @click="startNavigation">{{isNavigating ? "取消导航" : "进入导航"}}</button>
        <button  @click="chooseStart" :disabled="!isNavigating">{{selectedStart ? "确认起点" : "选择起点"}}</button>
        <button  @click="chooseEnd" :disabled="!isNavigating">{{selectedEnd ? "确认终点" : "选择终点"}}</button>
      </div>



      <div class="busline">
        <div>
          <label for="startBuilding">起始建筑：</label>
          <select v-model="startBuildingId" id="startBuilding" @click="getStartStation">
            <!-- 这里添加起始点的选项 -->
            <option value="1">林恩图书馆</option>
            <option value="2">第一教学楼</option>
            <!-- 添加更多选项 -->
          </select>
        </div>

        <div>
          <label for="endBuilding">目标建筑：</label>
          <select v-model="endBuildingId" id="endBuilding" @click="getEndStation">
            <!-- 这里添加目标点的选项 -->
            <option value="1">林恩图书馆</option>
            <option value="2">第一教学楼</option>
            <!-- 添加更多选项 -->
          </select>
        </div>

        <button v-if="startBuildingId && endBuildingId" @click="seeBusline">查看公交线路</button>

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
import axios from "axios";

window._AMapSecurityConfig = {
  securityJsCode: '012338515576425f8fdff9a7f8404d60',
}
export default {
  name: "SUSMap",
  data() {
    return {
      map: '',
      AMap: '',
      isNavigating: false,
      selectedStart: false,
      selectedEnd: false,
      driving: '',
      longitude: 114.000725,
      latitude: 22.595509,
      infoWin: '',
      startBuildingId: "1",
      endBuildingId: "2",
      startStation: "行政楼",
      endStation: "科研楼",
      startPoint: [],
      startMarker: '',
      endPoint: [],
      endMarker: '',
      buildings: [],
      walking: '',
      showPanel: false,

    }
  },
  mounted() {
    this.initAMap();
    this.fetchBuildingData();
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

            var startIcon = new AMap.Icon({
              // 图标尺寸
              size: new AMap.Size(25, 34),
              // 图标的取图地址
              image: '//a.amap.com/jsapi_demos/static/demo-center/icons/dir-marker.png',
              // 图标所用图片大小
              imageSize: new AMap.Size(135, 40),
              // 图标取图偏移量
              imageOffset: new AMap.Pixel(-9, -3)
            });

            this.startPoint = [113.997784, 22.594813];
            this.startMarker = new this.AMap.Marker({
              position: this.startPoint,
              title: '起点',
              icon: startIcon,
            });

            this.map.add(this.startMarker);
            this.startMarker.hide();
            this.startMarker.on('dragend', this.start);

            var endIcon = new AMap.Icon({
              size: new AMap.Size(25, 34),
              image: '//a.amap.com/jsapi_demos/static/demo-center/icons/dir-marker.png',
              imageSize: new AMap.Size(135, 40),
              imageOffset: new AMap.Pixel(-95, -3)
            });

            this.endPoint = [this.longitude, this.latitude,];
            this.endMarker = new this.AMap.Marker({
              position: this.endPoint,
              title: '终点',
              icon: endIcon,
            });
            this.map.add(this.endMarker);
            this.endMarker.hide();
            this.endMarker.on('dragend', this.start);

            var walkOption = {
              map: this.map,
              panel: "panel",
              hideMarkers: true,
              isOutline: true,
              outlineColor: '#ffeeee',
              autoFitView: true,
            }

            this.walking = new this.AMap.Walking(walkOption)

            //根据起终点坐标规划步行路线

            this.map.on("click", this.handleMapClick.bind(this));
          })
          .catch((e) => {
            console.log(e);
          });

    },

    fetchBuildingData() {
      axios.get("http://localhost:8081/public/building/get/simple")
          .then(response => {
            this.buildings = response.data.data;
            console.log(response.data);
          })
          .catch(function (error) {
            console.log(error);
          })
          .finally(function () {
          });
    },

    getStartStation() {
      axios.get(`http://localhost:8081/public/building/get/station?buildingId=${parseInt(this.startBuildingId)}`)
          .then(response => {
            this.startStation = response.data.data.nearestStation;
          })
          .catch(function (error) {
            console.log(error);
          })
          .finally(function () {
          });
    },

    getEndStation() {
      axios.get(`http://localhost:8081/public/building/get/station?buildingId=${parseInt(this.endBuildingId)}`)
          .then(response => {
            this.endStation = response.data.data.nearestStation;
          })
          .catch(function (error) {
            console.log(error);
          })
          .finally(function () {
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
      console.log("查看公交线路：", this.startStation, "到", this.endStation);
    },
    startNavigation() {
      this.isNavigating = !this.isNavigating;
      if (this.isNavigating) {
        this.startMarker.show();
        this.endMarker.show();
        this.showPanel = true;
        this.start();
      } else {
        this.startMarker.hide();
        this.endMarker.hide();
        this.showPanel = false;
        this.walking.clear();
      }
    },

    chooseStart() {
      this.selectedStart = !this.selectedStart;
      if (this.selectedStart) {
        this.startMarker.setDraggable(true);
      } else {
        this.startMarker.setDraggable(false);
      }
    },

    chooseEnd() {
      this.selectedEnd = !this.selectedEnd;
      if (this.selectedEnd) {
        this.endMarker.setDraggable(true);
      } else {
        this.endMarker.setDraggable(false);
      }
    },

    start() {
      this.walking.search([this.startMarker.getPosition().lng, this.startMarker.getPosition().lat], [this.endMarker.getPosition().lng, this.endMarker.getPosition().lat], function(status, result) {
        // result即是对应的不行路线数据信息，相关数据结构文档请参考  https://lbs.amap.com/api/javascript-api/reference/route-search#m_RidingResult
        if (status === 'complete') {
          console.log('步行路线数据查询成功')
        } else {
          console.log('步行路线数据查询失败' + result)
        }
      });
    }


  },

}
</script>
<style scoped>
.map {
  position: relative;
  height: 100%;
  width: 100%;
}
#out_container{
  height: 99%;
  width: 100%;
}
#container {
  width: 100%;
  height: 100%;
}
#panel {
  position: fixed;
  background-color: white;
  max-height: 90%;
  overflow-y: auto;
  top: 10%;
  right: 8%;
  width: 280px;
}
#panel .amap-call {
  background-color: #009cf9;
  border-top-left-radius: 4px;
  border-top-right-radius: 4px;
}
#panel .amap-lib-walking {
  border-bottom-left-radius: 4px;
  border-bottom-right-radius: 4px;
  overflow: hidden;
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
