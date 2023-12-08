<template>
 <div>
   <div ref="buildingchart" style="width: 50%; height: 400px;"></div>
   <div ref="roomchart" style="width: 50%; height: 400px;"></div>
 </div>
</template>

<script>
import * as echarts from 'echarts';
export default {
  name: 'AppointmentManage',
  data() {
    return {
      buildingData: [
        // 其他数据项...
      ],

      roomData: [],
    }
  },
  created() {
    this.fetchBuildingSta()
    this.fetchRoomSta()
  },
  beforeMount() {

  },
  mounted() {

  },
  methods: {
    fetchBuildingSta() {
      this.$http.get('/admin/building/statistics').then(res => {
        this.buildingData = res.data;
        console.log(res.data)
        this.drawBuildingChart()

      })


    },

    fetchRoomSta() {
      this.$http.get('/admin/room/statistics').then(res => {
        this.roomData = res.data
        this.drawRoomChart()
      })

    },
    drawBuildingChart() {
      const chartContainer = this.$refs.buildingchart;
      const myChart = echarts.init(chartContainer);

      const option = {
        tooltip: {
          trigger: 'axis', // 触发类型，坐标轴触发的有效
          axisPointer: {
            type: 'shadow', // 默认为直线，可选为：'line' | 'shadow'
          },
        },
        title: {
          text: 'Building Infromation',
        },
        xAxis: {
          type: 'category',
          data: this.buildingData.map((building) => building.buildingName),
        },
        yAxis: {
          type: 'value',
        },
        series: [
          {
            name: 'TotalLike',
            data: this.buildingData.map((building) => building.totalLike),
            type: 'bar',
            itemStyle: {
              color: '#ff0000', // 设置柱状图颜色
            },
          },
          {
            name: 'TotalReserve',
            data: this.buildingData.map((building) => building.totalReserve),
            type: 'bar',
            itemStyle: {
              color: '#00ff00', // 设置柱状图颜色
            },
          },
          {
            name: 'TotalRoom',
            data: this.buildingData.map((building) => building.totalRoom),
            type: 'bar',
            itemStyle: {
              color: '#0000ff', // 设置柱状图颜色
            },
          },
        ],
      };

      myChart.setOption(option);
    },

    drawRoomChart() {
      const chartContainer = this.$refs.roomchart;
      const myChart = echarts.init(chartContainer);

      const option = {
        tooltip: {
          trigger: 'axis', // 触发类型，坐标轴触发的有效
          axisPointer: {
            type: 'shadow', // 默认为直线，可选为：'line' | 'shadow'
          },
        },
        title: {
          text: 'Room Infromation',
        },
        xAxis: {
          type: 'category',
          data: this.roomData.map((room) => room.roomName),
        },
        yAxis: {
          type: 'value',
        },
        series: [
          {
            name: 'TotalLike',
            data: this.roomData.map((room) => room.totalLike),
            type: 'bar',
            itemStyle: {
              color: '#ff0000', // 设置柱状图颜色
            },
          },
          {
            name: 'TotalReserve',
            data: this.roomData.map((room) => room.totalReserve),
            type: 'bar',
            itemStyle: {
              color: '#00ff00', // 设置柱状图颜色
            },
          },
        ],
      };

      myChart.setOption(option);
    },

  },

}
</script>