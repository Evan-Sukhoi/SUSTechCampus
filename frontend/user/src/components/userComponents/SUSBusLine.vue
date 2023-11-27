<template>
  <!--TODO-->
  <div id="back">
    <div class="show">
      <h1>{{$t('lang.path')}}</h1>
      <el-form label-position="left" label-width="80px">
        <el-form-item style="width: 400px;">
          <h3>{{$t('lang.from')+": "+from}}</h3>
          <h3>{{$t('lang.to')+": "+to}}</h3>
        </el-form-item>
      </el-form>
    </div>
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
      from:'荔园',
      to:'社康中心',
      busLine: [{
        content: '欣圆',
      }, {
        content: '荔园',
      }, {
        content: '学生宿舍',
      },{
        content: '社康中心',
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
  },
  beforeMount() {
    this.$http({
      method:'get',
      url:''
    }).then(resp =>{
      console.log(resp)
    }).catch(err=>{
      console.log(err)
    })
  },
  created() {
    var flag = false
    for (var i=0; i < this.busLine.length; i++){
      if (this.busLine[i].content === this.from){
        flag = true
      }
      if (flag){
        this.busLine[i]['color'] = '#0bbd87'
      }
      if (this.busLine[i].content === this.to){
        flag = false
      }
    }
  },
  methods:{
    show(){

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
  top:60%;
  left:50%;
  transform:translate(-50%,-50%);
  background-color: #ffffff;

}
.show{
  background-color: rgba(255, 255, 255);
  width: 500px;
  height:200px;
  position: absolute;
  top:20%;
  left:50%;
  transform:translate(-50%,-50%);
  text-align: center;
  border-radius: 20px;
}
.small-box{
  display: flex;
  float: left;
}
</style>