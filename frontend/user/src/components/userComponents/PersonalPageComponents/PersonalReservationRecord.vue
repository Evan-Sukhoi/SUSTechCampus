<template>
  <div>
    <h1 id="title">{{$t('lang.reservationRecord')}}</h1>
    <el-container class="inf">
      <el-table
          :data="tableData"
          style="width: 100%"
          max-height="250">
        <el-table-column
            prop="roomNumber"
            :label="$t('lang.roomID')"
            width="150">
        </el-table-column>
        <el-table-column
            prop="description"
            :label="$t('lang.department')"
            width="120">
        </el-table-column>
        <el-table-column
            prop="buildingType"
            :label="$t('lang.buildingType')"
            width="120">
        </el-table-column>
        <el-table-column
            prop="buildingName"
            :label="$t('lang.buildingName')"
            width="120"
            style="text-align: center">
        </el-table-column>
        <el-table-column
            prop="date"
            :label="$t('lang.date')"
            width="120">
        </el-table-column>
        <el-table-column
            prop="startTime"
            :label="$t('lang.startTime')"
            width="120"
            :formatter="formatDate">
        </el-table-column>
        <el-table-column
            prop="endTime"
            :label="$t('lang.endTime')"
            width="120"
            :formatter="formatDate">
        </el-table-column>
        <el-table-column
            fixed="right"
            :label="$t('lang.operation')"
            :width="width">
          <template slot-scope="scope">
            <el-button
                @click.native.prevent="deleteRow(scope.$index, tableData)"
                type="text"
                size="small">
              {{$t('lang.delete')}}
            </el-button>
            <el-button
                @click.native.prevent="edit(scope.$index, tableData)"
                type="text"
                size="small">
              {{$t('lang.edit')}}
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-container>
    <ReservationForm></ReservationForm>
  </div>

</template>

<script>
import ReservationForm from "@/components/userComponents/ReseravationComponents/ReservationForm";
export default {
  name: "PersonalReservationRecord",
  components:{
    ReservationForm
  },
  mounted() {
    window.addEventListener("resize", this.handleResize)
    this.handleResize()
  },
  methods: {
    handleResize(){
      const isSmallScreen = window.matchMedia('(max-width: 768px)').matches;
      if (isSmallScreen){
        this.width='80'
      }
    },
    fetchData(){
      console.log(1)
      this.$http.get('/user/reservation/get/all?userId='+ localStorage.getItem('userID')).then(resp => {
        if (resp.status === 200) {
          this.tableData = resp.data
          for (let i = 0; i < this.tableData.length; i++) {
            var time = new Date(this.tableData[i].startTime)
            var day = time.getDate()
            if (day >= 0 && day <= 9) {
              day = "0" + String(day);
            }
            this.tableData[i]["date"] = time.getFullYear() + '-' + (time.getMonth() + 1) + '-' + day
            var data = {buildingId:this.tableData[i].buildingId, date:time}
            this.$http.post(`user/reservation/get`, data).then(resp => {
              if (resp.status === 200){
                for (let j = 0; j < resp.data.length; j++) {
                  if (resp.data[j].roomId === this.tableData[i].roomId){
                    this.tableData[i].availableTimeBegin = resp.data[j].availableTimeBegin
                    this.tableData[i].availableTimeEnd = resp.data[j].availableTimeEnd
                  }
                }
              }
            }).catch(err=>err)
            console.log(resp.data)
            console.log(this.tableData)
          }
        }
      }).catch(err=>err)

    },
    deleteRow(index, rows) {
      this.$http.post('/user/reservation/delete?reservationId='+rows[index].reservationId).then(resp => {
        if (resp.status === 200) {
          this.$vs.notification({
            color: 'success',
            position: 'top-center',
            title: this.$t('lang.deleteSuccess'),
            text: '',
          })
          rows.splice(index, 1);
        }else {
          this.$vs.notification({
            color: 'danger',
            position: 'top-center',
            title: resp.data,
            text: '',
          })
        }
      }).catch(err=>{
        this.$vs.notification({
          color: 'danger',
          position: 'top-center',
          title: err,
          text: '',
        })
      })

    },
    edit(index, tableData){
      const availableTimeBegin = tableData[index].availableTimeBegin
      const availableTimeEnd = tableData[index].availableTimeEnd
      const len = availableTimeBegin.length
      var reserveTime = []
      for (let i = 0; i < len; i++) {
        reserveTime.push(availableTimeBegin[i] + '-' + availableTimeEnd[i])
      }
      this.$store.commit('edit', tableData[index], reserveTime)
    },
    formatDate(row, column) {
      let data = row[column.property]
      if(data == null) {
        return null
      }
      let dt = new Date(data)
      return dt.getHours() + ':' + dt.getMinutes() + ':' + dt.getSeconds()
    },
  },
  data() {
    return {
      width:120,
      tableData: [{
        roomID:101,
        department:'计算机系',
        buildingType:'食堂',
        buildingName:'building1',
        date:'2023-9-10',
        start_time:new Date(2023, 9, 10, 15, 56, 23),
        end_time:new Date(2023, 9, 10, 17, 11, 12),
      }, {
        roomID:102,
        department:'数学系',
        buildingType:'图书馆',
        buildingName:'building2',
        date:'2023-9-10',
        start_time:new Date(2023, 9, 10, 14, 23, 23),
        end_time:new Date(2023, 9, 10, 16, 23, 12),
      }],
      formLabelWidth: '120px'
    }
  },
  beforeMount() {
    this.fetchData()
  },

}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->

<style scoped>

.inf {
  padding: 30px;
  border-style: solid;
  border-color: rgba(204, 204, 204, 0.7);
  border-width: 10px;
  margin: 100px;
}

#title {
  position: relative;
}
@media screen and (max-width: 768px){
  .inf {
    padding: 10px;
    border-style: solid;
    border-color: rgba(204, 204, 204, 0.7);
    border-width: 10px;
    margin: 20px;
  }
}
</style>
