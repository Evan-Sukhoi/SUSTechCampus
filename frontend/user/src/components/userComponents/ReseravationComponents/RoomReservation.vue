<template>
<div>
  <div class="box">
    <el-calendar v-model="value" style="background-color: rgba(255,255,255,0.8)">
      <template v-slot:dateCell="{data}" >
        <div style="width: 100%; height: 100%" @click="showDetail(data)">
          <p :class="data.isSelected ? 'is-selected' : ''" >
            {{ data.day.split('-').slice(1).join('-') }} {{ data.isSelected ? '✔️' : ''}}
          </p>
        </div>
      </template>
    </el-calendar>
  </div>

</div>
</template>

<script>
export default {
  name: "RoomReservation",
  data() {
    return {
      value: new Date()
    }
  },
  methods:{
    showDetail(data){
      this.$router.push({name:'roomTimeLine', params:{date:data.day}}).catch(err=>err)
    }
  }
}
</script>

<style scoped>

.box{
  width: 70%;
  background-color: rgba(255, 255, 255, 0.8);
  overflow: auto;
  border-radius: 12px;
  position: absolute;
  top:50%;
  left:50%;
  transform:translate(-50%,-50%);
}

</style>