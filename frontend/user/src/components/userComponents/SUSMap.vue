<template>
  <div id="out_container">
    <div class="map">
      <div id="container"></div>
      <div id="walkingPanel" v-if="showWalkingPanel"></div>
      <div id="drivingPanel" v-if="showDrivingPanel"></div>
    </div>

    <div class="map_content">

      <div class="navi">
        <button @click="startNavigation">{{ isNavigating ? "取消导航" : "进入导航" }}</button>
        <button @click="chooseStart" :disabled="!isNavigating">{{ selectedStart ? "确认起点" : "选择起点" }}</button>
        <button @click="chooseEnd" :disabled="!isNavigating">{{ selectedEnd ? "确认终点" : "选择终点" }}</button>
        <button @click="seeBusline1">{{ seebusline1 ? "关闭公交线路1" : "查看公交线路1" }}</button>
        <button @click="seeBusline2">{{ seebusline2 ? "关闭公交线路2" : "查看公交线路2" }}</button>
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

        <button v-if="startBuildingId && endBuildingId" @click="seeBusline">{{ this.seebusline ? "关闭公交线路" : "查看公交线路" }}</button>


      </div>

      <div class="walk">

      </div>

    </div>
  </div>
</template>
<script>
import AMapLoader from "@amap/amap-jsapi-loader";
import locations from "../../assets/location/location.json"
import buslines from "../../assets/location/busline.json"

import MapContent from "@/components/userComponents/MapContent.vue";
import Vue from "vue";
// import {flatten} from "eslint-plugin-vue/lib/utils";

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
      driving: '',
      showWalkingPanel: false,
      showDrivingPanel: false,
      busline1: '',
      seebusline1: false,
      busline2: '',
      seebusline2: false,
      markers1: [],
      markers2: [],
      path1: [],
      path2: [],
      seebusline: false,
      subLine1: '',
      subLine2: '',
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
        plugins: ['AMap.Driving', 'AMap.LngLat', 'AMap.Walking', 'AMap.ControlBar', 'AMap.ToolBar', 'AMap.Marker', 'AMap.Polygon', 'AMap.InfoWindow', 'AMap.Driving'], // 需要使用的的插件列表，如比例尺'AMap.Scale'等
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

            this.endPoint = [113.99726,22.596677];
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
              panel: "walkingPanel",
              hideMarkers: true,
              isOutline: true,
              outlineColor: '#ffeeee',
              autoFitView: true,
            }

            this.walking = new this.AMap.Walking(walkOption)

            var drivingOption = {
              map: this.map,
              panel: "drivingPanel",
              hideMarkers: true,
              isOutline: true,
              outlineColor: '#00FF00',
              autoFitView: true,
            }

            this.driving = new this.AMap.Driving(drivingOption)

            for (const busline of buslines) {

              for (let i = 0; i < busline.list.length - 1; i++) {
                for (const item of busline.list[i].point) {
                  if (busline.name === 'busline1') {
                    this.path1.push(item)
                  } else {
                    this.path2.push(item)
                  }
                }

                const text = busline.list[i].two[0];
                const position = busline.list[i].point[0];
                const marker = new this.AMap.Marker({
                  position: position,
                  icon: "//a.amap.com/jsapi_demos/static/demo-center/icons/poi-marker-default.png",
                  iconSize: new this.AMap.Size(20, 20),
                  extData: { flag: `${text}` }
                })
                marker.setLabel({
                  offset: new AMap.Pixel(10, 10),  //设置文本标注偏移量
                  content: `<div class='info'>${text}</div>`, //设置文本标注内容
                  direction: 'right', //设置文本标注方位
                });
                if (busline.name === "busline1") {
                  this.markers1.push(marker);
                } else {
                  this.markers2.push(marker);
                }

              }

              for (const item of busline.list[busline.list.length - 1].point) {
                if (busline.name === 'busline1') {
                  this.path1.push(item)
                } else {
                  this.path2.push(item)
                }
              }

              if (busline.name === "busline1") {

                this.busline1 = new this.AMap.Polyline({
                  path: this.path1,
                  isOutline: true,
                  outlineColor: '#ffeeff',
                  borderWeight: 3,
                  strokeColor: "#3366FF",
                  strokeOpacity: 0.8,
                  strokeWeight: 6,
                  // 折线样式还支持 'dashed'
                  strokeStyle: "solid",
                  // strokeStyle是dashed时有效
                  strokeDasharray: [10, 5],
                  lineJoin: 'round',
                  lineCap: 'round',
                  zIndex: 50,
                })
              } else {
                this.busline2 = new this.AMap.Polyline({
                  path: this.path2,
                  isOutline: true,
                  outlineColor: '#ffeeff',
                  borderWeight: 3,
                  strokeColor: "#4eff33",
                  strokeOpacity: 1,
                  strokeWeight: 6,
                  // 折线样式还支持 'dashed'
                  strokeStyle: "solid",
                  // strokeStyle是dashed时有效
                  strokeDasharray: [10, 5],
                  lineJoin: 'round',
                  lineCap: 'round',
                  zIndex: 50,
                })
              }

              let text = busline.list[busline.list.length - 1].two[0];
              let position = busline.list[busline.list.length - 1].point[0];
              let marker = new this.AMap.Marker({
                position: position,
                icon: "//a.amap.com/jsapi_demos/static/demo-center/icons/poi-marker-default.png",
                iconSize: new this.AMap.Size(20, 20),
                extData: { flag: `${text}` }
              })
              marker.setLabel({
                offset: new this.AMap.Pixel(10, 10),  //设置文本标注偏移量
                content: `<div class='info'>${text}</div>`, //设置文本标注内容
                direction: 'right' //设置文本标注方位
              });
              if (busline.name === "busline1") {
                this.markers1.push(marker);
              } else {
                this.markers2.push(marker);
              }
              text = busline.list[busline.list.length - 1].two[1];
              position = busline.list[busline.list.length - 1].point[busline.list[busline.list.length - 1].point.length-1];
              marker = new this.AMap.Marker({
                position: position,
                icon: "//a.amap.com/jsapi_demos/static/demo-center/icons/poi-marker-default.png",
                iconSize: new this.AMap.Size(20, 20),
                extData: { flag: `${text}` }
              })
              marker.setLabel({
                offset: new AMap.Pixel(10, 10),  //设置文本标注偏移量
                content: `<div class='info'>${text}</div>`, //设置文本标注内容
                direction: 'right', //设置文本标注方位
              });
              if (busline.name === "busline1") {
                this.markers1.push(marker);
              } else {
                this.markers2.push(marker);
              }

            }

            this.map.on("click", this.handleMapClick.bind(this));
          })
          .catch((e) => {
          });

    },

    fetchBuildingData() {
      this.$http.get("/public/building/get/simple")
          .then(response => {
            this.buildings = response.data.data;
          })
          .catch(function (error) {
          })
          .finally(function () {
          });
    },

    getStartStation() {
      this.$http.get(`/public/building/get/station?buildingId=${parseInt(this.startBuildingId)}`)
          .then(response => {
            this.startStation = response.data.data.nearestStation;
          })
          .catch(function (error) {
          })
          .finally(function () {
          });
    },

    getEndStation() {
      this.$http.get(`/public/building/get/station?buildingId=${parseInt(this.endBuildingId)}`)
          .then(response => {
            this.endStation = response.data.data.nearestStation;
          })
          .catch(function (error) {
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
      console.log("[" + lng + "," + lat + "]");
      if (this.infoWindow) {
        this.infoWindow.close();
      }
    },

    seeBusline() {
      this.seebusline = !this.seebusline;
      if (this.seebusline) {
        let startPosition = ''
        let endPosition = ''
        // let startPosition2 = ''
        // let endPosition2 = ''
        for (const marker of this.markers1) {
          if (marker.getExtData().flag === this.startStation) {
            startPosition = marker.getPosition();
          }
          if (marker.getExtData().flag === this.endStation) {
            endPosition = marker.getPosition();
          }
        }
        for (const marker of this.markers2) {
          if (marker.getExtData().flag === this.startStation) {
            startPosition = marker.getPosition();
          }
          if (marker.getExtData().flag === this.endStation) {
            endPosition = marker.getPosition();
          }
        }

        let startIndex1 = ''
        let endIndex1 = ''
        for (let i = 0; i < this.path1.length; i++) {
          if (this.path1[i].lng === startPosition.lng && this.path1[i].lat === startPosition.lat) {
            startIndex1 = i
          }
          if (this.path1[i].lng === endPosition.lng && this.path1[i].lat === endPosition.lat) {
            endIndex1 = i
          }
        }
        if (startIndex1 !== '' && endIndex1 !== '') {
          const subPath1 = this.path1.slice(Math.min(startIndex1, endIndex1), Math.max(startIndex1, endIndex1) + 1);
          this.subLine1 = new this.AMap.Polyline({
            path: subPath1,
            isOutline: true,
            outlineColor: '#ffeeff',
            borderWeight: 3,
            strokeColor: "#bb33ff",
            strokeOpacity: 1,
            strokeWeight: 6,
            // 折线样式还支持 'dashed'
            strokeStyle: "solid",
            // strokeStyle是dashed时有效
            strokeDasharray: [10, 5],
            lineJoin: 'round',
            lineCap: 'round',
            zIndex: 50,
          })
          this.map.add(this.subLine1)
          if (!this.seebusline1) {
            this.seeBusline1()
          }
        }

        let startIndex2 = ''
        let endIndex2 = ''
        for (let i = 0; i < this.path2.length; i++) {
          if (this.path2[i].lng === startPosition.lng && this.path2[i].lat === startPosition.lat) {
            startIndex2 = i
          }
          if (this.path2[i].lng === endPosition.lng && this.path2[i].lat === endPosition.lat) {
            endIndex2 = i
          }
        }
        if (startIndex2 !== '' && endIndex2 !== '') {
          const subPath2 = this.path2.slice(Math.min(startIndex1, endIndex1), Math.max(startIndex1, endIndex1) + 1);
          this.subLine2 = new this.AMap.Polyline({
            path: subPath2,
            isOutline: true,
            outlineColor: '#ffeeff',
            borderWeight: 3,
            strokeColor: "#ffc533",
            strokeOpacity: 1,
            strokeWeight: 6,
            // 折线样式还支持 'dashed'
            strokeStyle: "solid",
            // strokeStyle是dashed时有效
            strokeDasharray: [10, 5],
            lineJoin: 'round',
            lineCap: 'round',
            zIndex: 50,
          })
          this.map.add(this.subLine2)
          if (!this.seebusline2) {
            this.seeBusline2()
          }
        }
      } else {
        if (this.seebusline1) {
          this.seeBusline1()
        }
        if (this.seebusline2) {
          this.seeBusline2()
        }

        this.map.remove(this.subLine1)
        this.map.remove(this.subLine2)
      }
    },

    seeBusline1() {
      this.seebusline1 = !this.seebusline1;
      if (this.seebusline1) {
        this.map.add(this.busline1)
        for (const marker of this.markers1) {
          this.map.add(marker)
        }
      } else {
        this.map.remove(this.busline1)
        for (const marker of this.markers1) {
          this.map.remove(marker)
        }
      }
    },

    seeBusline2() {
      this.seebusline2 = !this.seebusline2;
      if (this.seebusline2) {
        this.map.add(this.busline2)
        for (const marker of this.markers2) {
          this.map.add(marker)
        }
      } else {
        this.map.remove(this.busline2)
        for (const marker of this.markers2) {
          this.map.remove(marker)
        }
      }
    },
    startNavigation() {
      this.isNavigating = !this.isNavigating;
      if (this.isNavigating) {
        this.startMarker.show();
        this.endMarker.show();
        this.showWalkingPanel = true;
        this.showDrivingPanel = true;
        this.start();
      } else {
        this.startMarker.hide();
        this.endMarker.hide();
        this.showWalkingPanel = false;
        this.showDrivingPanel = false;
        this.walking.clear();
        this.driving.clear();
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
      this.walking.search([this.startMarker.getPosition().lng, this.startMarker.getPosition().lat], [this.endMarker.getPosition().lng, this.endMarker.getPosition().lat], function (status, result) {
        // result即是对应的不行路线数据信息，相关数据结构文档请参考  https://lbs.amap.com/api/javascript-api/reference/route-search#m_RidingResult
        if (status === 'complete') {
        } else {
        }
      });

      this.driving.search([this.startMarker.getPosition().lng, this.startMarker.getPosition().lat], [this.endMarker.getPosition().lng, this.endMarker.getPosition().lat], function (status, result) {
        // result即是对应的不行路线数据信息，相关数据结构文档请参考  https://lbs.amap.com/api/javascript-api/reference/route-search#m_RidingResult
        if (status === 'complete') {
        } else {
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

#out_container {
  height: 99%;
  width: 100%;
}

#container {
  width: 100%;
  height: 100%;
}

#walkingPanel {
  position: fixed;
  background-color: white;
  max-height: 90%;
  overflow-y: auto;
  top: 10%;
  right: 8%;
  width: 280px;
}

#drivingPanel {
  position: fixed;
  background-color: white;
  max-height: 90%;
  overflow-y: auto;
  top: 10%;
  right: 28%;
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
