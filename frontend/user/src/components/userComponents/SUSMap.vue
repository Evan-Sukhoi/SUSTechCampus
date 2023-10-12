<template>
  <div>
    <div id="container"></div>
    <div id="panel"></div>
  </div>
</template>

<script>
import { Loader } from '@amap/amap-jsapi-loader';

export default {
  mounted() {
    Loader.load().then((AMap) => {
      const map = new AMap.Map('map-container', {
        zoom: 13,
        center: [114.000725, 22.595509],
      });

      // 添加标记
      const marker = new AMap.Marker({
        position: [114.000725, 22.595509],
        title: '标记位置',
      });
      marker.setMap(map);

      // 绘制路线、其他地图操作等
    });
  },
};
</script>
