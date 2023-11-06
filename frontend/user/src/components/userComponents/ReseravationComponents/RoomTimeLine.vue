<template>
  <div>
    <div class="box">
      <div v-for="item in roomDetail" :key="item.roomID">
        <span>{{$t('lang.roomID') + ': ' + item.roomID}}</span>
        <vs-button gradient class="reservation" @click="reserve(item.roomID)">{{$t('lang.reserve')}}</vs-button>
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
        {roomID: 101, reserveTime:[1,3]},
        {roomID: 102},
        {roomID: 103}
      ],
      dialogFormVisible: false,
    }
  },
  methods:{
    reserve(roomID){
      var reservation = {roomID: roomID, date: this.$route.params.date, buildingType: this.$route.params.buildingType, buildingName: this.$route.params.buildingName}
      this.$store.commit('reserve', reservation)
    }
  }
}
</script>

<style scoped>
.box{
  width: 70%;
  background-color: rgba(255, 255, 255, 0.8);
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