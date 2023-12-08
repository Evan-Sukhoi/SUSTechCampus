<template>
  <div>
  <el-dialog :title="title" :visible.sync="$store.state.isShow" :before-close="cancel" :width="width">
    <el-form :model="$store.state">
      <el-form-item :label="$t('lang.roomID')" :label-width="formLabelWidth">
        <el-input v-model="$store.state.roomNumber" autocomplete="off" placeholder="Room + number" disabled></el-input>
      </el-form-item>
      <el-form-item :label="$t('lang.department')" :label-width="formLabelWidth">
        <el-input v-model="$store.state.department" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item :label="$t('lang.date')" :label-width="formLabelWidth">
        <el-col :span="11">
          <el-date-picker
              type="date"
              :placeholder="$t('lang.chooseADate')"
              v-model="this.$store.state.date"
              format="yyyy/MM/dd"
              style="width: 100%;"
              value-format="yyyy/MM/dd"
              disabled
              :picker-options="{
         disabledDate: time => {
            return time.getTime() < Date.now()
          },
        }" ></el-date-picker>
        </el-col>
      </el-form-item>
      <el-form-item :label="$t('lang.startTime')" :label-width="formLabelWidth">
        <el-col :span="11">
          <el-time-picker
              v-model="$store.state.start_time"
              :picker-options="{
                selectableRange: $store.state.rangeTime
              }"
              :placeholder="$t('lang.chooseATime')"
              style="width: 100%">
          </el-time-picker>
        </el-col>
      </el-form-item>
      <el-form-item :label="$t('lang.endTime')" :label-width="formLabelWidth">
        <el-col :span="11">
          <el-time-picker
              v-model="$store.state.end_time"
              :picker-options="{
                selectableRange: this.$store.state.rangeTime
              }"
              :placeholder="$t('lang.chooseATime')"
              style="width: 100%">
          </el-time-picker>
        </el-col>
      </el-form-item>
      <el-form-item :label="$t('lang.location')" :label-width="formLabelWidth">
        <el-col :span="11">
          <el-select v-model="this.$store.state.buildingType" :placeholder="$t('lang.chooseAPlace')" disabled>
          </el-select>
        </el-col>
        <el-col class="line" :span="2">-</el-col>
        <el-col :span="11">
          <el-input v-model="this.$store.state.buildingName" autocomplete="off" :placeholder="$t('lang.suchAs434A')" disabled></el-input>
        </el-col>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button type="primary" @click="submit">Submit</el-button>
      <el-button type="danger" @click="cancel">Cancel</el-button>
    </div>
  </el-dialog>
</div>
</template>

<script>
export default {
  name: "ReservationForm",
  data(){
    return{
      width:'50%',
      title: this.$t('lang.reservation'),
      latent_index:-1,
      formLabelWidth: '120px'
    }
  },
  mounted() {
    window.addEventListener("resize", this.handleResize)
    this.handleResize()
  },
  methods:{
    handleResize(){
      const isSmallScreen = window.matchMedia('(max-width: 768px)').matches;
      if (isSmallScreen){
        this.width='100%'
      }
    },
    restore() {
      this.$store.state.department = ''
      this.$store.state.start_time = ''
      this.$store.state.end_time = ''
      this.latent_index = -1
      this.$store.state.isShow = false
      this.$store.state.isEdit = false
    },
    contentCheck() {
      for (const [key, value] of Object.entries(this.$store.state)) {
        if (key !== 'title' || key !== 'latent_index'){
          if (value === ''){
            this.$vs.notification({
              color:'danger',
              position: 'top-center',
              title: this.$t('lang.errorEmpty'),
              text: '',
            })
            return false
          }
        }
      }
      var start_hour = this.$store.state.start_time.getHours()
      var start_minute = this.$store.state.start_time.getMinutes()
      var start_second = this.$store.state.start_time.getSeconds()
      var end_hour = this.$store.state.end_time.getHours()
      var end_minute = this.$store.state.end_time.getMinutes()
      var end_second = this.$store.state.end_time.getSeconds()
      var diff = (end_hour - start_hour) * 3600 + (end_minute - start_minute) * 60 + end_second - start_second
      if (diff < 0){
        this.$vs.notification({
          color:'danger',
          position: 'top-center',
          title: this.$t('lang.errorTime'),
          text: '',
        })
        return false
      }
      var flag = false
      for (let i = 0; i < this.$store.state.rangeTime.length; i++) {
        var judge = this.$store.state.rangeTime[i].split('-')
        var start = judge[0]
        var end = judge[1]
        var judge_start_part = start.split(':')
        var judge_end_part = end.split(':')
        var judge_start = new Date()
        var judge_end = new Date()
        judge_start.setHours(judge_start_part[0], judge_start_part[1], judge_start_part[2], 0)
        judge_end.setHours(judge_end_part[0], judge_end_part[1], judge_end_part[2], 0)
        var test_start = new Date()
        var test_end = new Date()
        test_start.setHours(start_hour, start_minute, start_second, 0)
        test_end.setHours(end_hour, end_minute, end_second, 0)
        if (judge_start.getTime() <= test_start.getTime() && judge_end.getTime() >= test_end.getTime()){
          flag = true
          break
        }
      }
      if (!flag){
        this.$vs.notification({
          color:'danger',
          position: 'top-center',
          title: this.$t('lang.errorRangeTime'),
          text: '',
        })
        return false
      }
      return true
    },
    submit() {

      if (this.$store.state.isEdit) {
        if (!this.contentCheck()) {
          return
        }
        var changeDate = {}
        var reserveDay = this.$store.state.date.split('-')
        const year = parseInt(reserveDay[0])
        const month = parseInt(reserveDay[1])
        const day = parseInt(reserveDay[2])
        var startTime = this.$store.state.start_time
        var endTime = this.$store.state.end_time
        changeDate.userId = localStorage.getItem('userID')
        changeDate.reservation_id = this.$store.state.reservationID
        changeDate.room_id = this.$store.state.roomID
        changeDate.description = this.$store.state.department
        changeDate.startTime = new Date(year, month-1, day, startTime.getHours(), startTime.getMinutes(), startTime.getSeconds())
        changeDate.endTime = new Date(year, month-1, day, endTime.getHours(), endTime.getMinutes(), endTime.getSeconds())
        console.log(changeDate)
        this.$http({
          method: 'post',
          url: '/user/reservation/update',
          headers: {
            'content-type': 'application/json'
          },
          data: changeDate
        }).then(resp => {
          console.log(resp);
        }).catch(err=>err)
        console.log('edit')
      }else {
        if (!this.contentCheck()) {
          return;
        }
        var newDate = {}
        var reserveDay = this.$store.state.date.split('-')
        const year = parseInt(reserveDay[0])
        const month = parseInt(reserveDay[1])
        const day = parseInt(reserveDay[2])
        var startTime = this.$store.state.start_time
        var endTime = this.$store.state.end_time
        newDate.userId = localStorage.getItem('userID')
        newDate.roomId = this.$store.state.roomID
        newDate.description = this.$store.state.department
        newDate.startTime = new Date(year, month-1, day, startTime.getHours(), startTime.getMinutes(), startTime.getSeconds())
        newDate.endTime = new Date(year, month-1, day, endTime.getHours(), endTime.getMinutes(), endTime.getSeconds())
        //傻逼js，month必须-1
        // console.log(newDate)
        // console.log(year, month, day)
        this.$http({
          method: 'post',
          url: '/user/reservation/post',
          headers: {
            'content-type': 'application/json'
          },
          data: newDate
        }).then(resp => {
          if (resp.status === 200){
            this.$vs.notification({
              color: 'success',
              position: 'top-center',
              title: this.$t('lang.reserveSuccess'),
              text: '',
            })
          }else {
            this.$vs.notification({
              color: 'danger',
              position: 'top-center',
              title: resp.data,
              text: '',
            })
          }
          console.log(resp);
        }).catch(err=>{
          this.$vs.notification({
            color: 'danger',
            position: 'top-center',
            title: err,
            text: '',
          })
        })
      }
      this.restore()
    },
    cancel() {
      this.restore()
    },
  }
}
</script>

<style scoped>

</style>