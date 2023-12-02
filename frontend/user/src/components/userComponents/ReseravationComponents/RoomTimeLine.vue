<template>
  <div>
    <div class="box">
      <div v-for="item in roomDetail" :key="item.roomID">
        <span>{{$t('lang.roomID') + ': ' + item.roomID}}</span>
        <vs-button gradient class="reservation" @click="reserve(item.roomID, item.reserveTime)">{{$t('lang.reserve')}}</vs-button>
        <el-divider></el-divider>
      </div>
    </div>
    <ReservationForm></ReservationForm>
  </div>
</template>

<script>
import ReservationForm from "@/components/userComponents/ReseravationComponents/ReservationForm";

export default {
  name: "RoomTime",
  components:{
    ReservationForm
  },
  data(){
    return{
      roomDetail: [
        {roomID: 101, reserveTime:['09:30:00 - 12:00:00', '14:30:00 - 18:30:00']},
        {roomID: 102, reserveTime:['10:30:00 - 12:00:00', '15:30:00 - 18:30:00']},
        {roomID: 103, reserveTime:['09:30:00 - 13:00:00', '14:30:00 - 20:30:00']}
      ],
      dialogFormVisible: false,
    }
  },
  methods:{
    reserve(roomID, reserveTime){
      const d = this.$route.params.date
      const bt = this.$route.params.buildingType
      const bn = this.$route.params.buildingName
      const reservation = {roomID: roomID, date: d, buildingType: bt, buildingName: bn, rangeTime: reserveTime}
      this.$store.commit('reserve', reservation)
    }
  },
  beforeMount() {
    console.log(this.$route.params.date)
    this.$http.get(`user/room/get/building?buildingId=${this.$route.params.buildingId}`).then(resp => {
      console.log(resp);
      // this.roomDetail = resp
    }).catch(err=>err)

  }
}
</script>

<style scoped>
.box{
  width: 70%;
  background-color: rgba(255, 255, 255);
  border-radius: 12px;
  position: absolute;
  top:50%;
  left:50%;
  transform:translate(-50%,-50%);
}
.reservation{
  float: right;
  height: 100%;
}
</style>