<template>
  <!--TODO-->
  <div id="back">
    <div class="box">
      <div class="small-box" >
        <el-row :gutter="20">
          <el-col :xs="12" :sm="12" v-for="(bus,i) in busLine" :key="i">
            <el-timeline>
              <el-timeline-item
                  v-for="(activity, index) in bus"
                  :key="index">
                {{activity}}
              </el-timeline-item>
            </el-timeline>
          </el-col>
        </el-row>
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
      url:'public/busline/all'
    }).then(resp =>{
      console.log(resp.data)
      var busline = []
      for (let i = 0; i < resp.data.length; i++) {
        busline.push([])
        console.log(busline)
        busline[i].push(resp.data[i].list[0].two[0])
        for (let j = 0; j < resp.data[i].list.length; j++) {
          busline[i].push(resp.data[i].list[j].two[1])
        }
      }
      console.log(busline)
      this.busLine = busline
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
  width: 100%;
  height: 100%;
  /*background-image: url("../../assets/pad(canDelete)/background/狐狸.jpg");*/
  /*background-size: cover;*/
  position: relative;
}
.box{
  border-radius: 20px;
  position: relative;
  top:50%;
  left:50%;
  height: 100%;
  width: 50%;
  transform:translate(-50%,-50%);
  background-color: #ffffff;
  overflow: auto;
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
  width: 80%;
}
@media screen and (max-width: 768px){
  .box{
    border-radius: 0px;
    position: relative;
    top:50%;
    left:50%;
    height: 100%;
    width: 100%;
    transform:translate(-50%,-50%);
    background-color: #ffffff;
    overflow: auto;
  }
}
</style>