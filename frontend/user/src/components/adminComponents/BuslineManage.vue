<template>
  <div>
    <p>
      this is busline manage
    </p>
  </div>
</template>

<script>
export default {
  name: 'BuslineManage',
  data(){
    return{
      buslineInfo:{
        name:'busline1',
        list:[
          {two:['欣圆', '荔园'], point:[[12,2323],[32, 23],[23,12]]},
          {two:['荔园', '学生宿舍'], point:[[12,212], [121,12], [112,334]]}
        ]
      }
    }
  }
}
</script>

<style scoped>
</style>