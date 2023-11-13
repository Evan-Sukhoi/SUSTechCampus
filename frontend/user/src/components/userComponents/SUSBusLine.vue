<template>
  <!--TODO-->
  <div id="back">
    <div class="box">
      <div class="small-box">
        <el-timeline>
          <el-timeline-item
              v-for="(activity, index) in busLine"
              :key="index"
              :color="activity.color">
            {{activity.content}}
          </el-timeline-item>
        </el-timeline>
      </div>
      <div class="small-box">
        <el-timeline>
          <el-timeline-item
              v-for="(activity, index) in busLine2"
              :key="index">
            {{activity.content}}
          </el-timeline-item>
        </el-timeline>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "SUSBusLine",
  data(){
    return{
      busLine: [{
        content: '欣圆',
      }, {
        content: '荔园',
        color: '#0bbd87',
      }, {
        content: '学生宿舍',
        color: '#0bbd87',
      },{
        content: '社康中心',
        color: '#0bbd87',
      },{
        content: '教工餐厅'
      },{
        content: '教师公寓'
      }],
      busLine2:[{
        content: '欣圆',
      }, {
        content: '荔园',
      }, {
        content: '学生宿舍',
      },{
        content: '科研楼'
      }],
    }
  }
}
</script>

<style scoped>
#back{
  /*width: 100%;*/
  /*height: 100%;*/
  /*background-image: url("../../assets/pad(canDelete)/background/狐狸.jpg");*/
  /*background-size: cover;*/
  /*position: relative;*/
}
.box{
  border-radius: 20px;
  position: absolute;
  top:50%;
  left:50%;
  transform:translate(-50%,-50%);
  background-color: #ffffff;

}
.small-box{
  display: flex;
  float: left;
}
</style>