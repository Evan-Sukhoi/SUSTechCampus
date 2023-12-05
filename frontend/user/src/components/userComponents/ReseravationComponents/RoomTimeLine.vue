<template>
  <div class="center">
    <div class="box">
      <vs-table>
        <template #header>
          <vs-input v-model="search" border placeholder="Search" />
        </template>
        <template #thead>
          <vs-tr>
            <vs-th sort @click="roomDetail = $vs.sortData($event ,roomDetail, 'roomNumber')">
              {{$t('lang.roomID')}}
            </vs-th>
            <vs-th sort @click="roomDetail = $vs.sortData($event ,roomDetail, 'roomType')">
              {{$t('lang.roomType')}}
            </vs-th>
            <vs-th >
              {{$t('lang.operation')}}
            </vs-th>
          </vs-tr>
        </template>
        <template #tbody>
          <vs-tr
              :key="i"
              v-for="(tr, i) in $vs.getPage($vs.getSearch(roomDetail, search), page, max)"
              :data="tr"
              :is-selected="!!selected.includes(tr)"
              not-click-selected
              open-expand-only-td
          >
            <vs-td>
              {{ tr.roomNumber }}
            </vs-td>
            <vs-td>
              {{ tr.roomType }}
            </vs-td>
            <vs-td>
              <vs-button @click="reserve(tr.roomId, tr.roomNumber,tr.availableTimeBegin, tr.availableTimeEnd)">{{$t('lang.reserve')}}</vs-button>
            </vs-td>
          </vs-tr>
        </template>
        <template #footer>
          <vs-pagination v-model="page" :length="$vs.getLength($vs.getSearch(roomDetail, search), max)" />
        </template>
      </vs-table>
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
      search: '',
      page: 1,
      max: 5,
      active: 0,
      selected: [],
      roomDetail: [
        {roomID: 101, reserveTime:['09:30:00 - 12:00:00', '14:30:00 - 18:30:00']},
        {roomID: 102, reserveTime:['10:30:00 - 12:00:00', '15:30:00 - 18:30:00']},
        {roomID: 103, reserveTime:['09:30:00 - 13:00:00', '14:30:00 - 20:30:00']}
      ],
      dialogFormVisible: false,
    }
  },
  methods:{
    reserve(roomID, roomNumber, availableTimeBegin, availableTimeEnd){
      const d = this.$route.params.date
      const bt = this.$route.params.buildingType
      const bn = this.$route.params.buildingName
      const len = availableTimeBegin.length
      var reserveTime = []
      for (let i = 0; i < len; i++) {
        reserveTime.push(availableTimeBegin[i] + '-' + availableTimeEnd[i])
      }
      const reservation = {roomID: roomID, roomNumber: roomNumber, date: d, buildingType: bt, buildingName: bn, rangeTime: reserveTime}
      this.$store.commit('reserve', reservation)
    },
  },
  beforeMount() {
    console.log(this.$route.params.date)
    var data = {
      buildingId: this.$route.params.buildingId,
      date: this.$route.params.date
    }
    console.log(data)
    this.$http.post(`user/reservation/get`, data).then(resp => {
      if (resp.status === 200){
        this.roomDetail = resp.data
        console.log(resp.data)
      }
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