<template>
<div>
  <div class="box">
    <el-calendar v-model="value" style="background-color: rgba(255,255,255,0.8)">
      <template v-slot:dateCell="{data}" >
        <div style="width: 100%; height: 100%" @click="showDetail(data)" v-if="judgeTime(data)">
          <p :class="data.isSelected ? 'is-selected' : ''" >
            {{ data.day.split('-').slice(1).join('-') }} {{ data.isSelected ? '✔️' : ''}}
          </p>
        </div>
        <div v-else style="width: 100%; height: 100%">
          <p style="color: #76869f">
            {{ data.day.split('-').slice(1).join('-') }}{{$t('lang.reserveTime')}}
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
    },
    judgeTime(data){
      const today = data.day.split('-')
      const year = today[0]
      const month = today[1] - 1
      const day = today[2]
      const time = new Date(year, month, day).getTime()
      return time > Date.now() && time < Date.now() + 10 * 24 * 3600 * 1000
    }

  }
}
</script>

<style scoped>

.box{
  width: 70%;
  background-color: rgba(255, 255, 255);
  overflow: auto;
  border-radius: 12px;
  position: absolute;
  top:50%;
  left:50%;
  transform:translate(-50%,-50%);
}

</style>