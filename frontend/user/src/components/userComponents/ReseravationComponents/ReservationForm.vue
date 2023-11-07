<template>
  <div>
  <el-dialog :title="form.title" :visible.sync="$store.state.isReserve" :before-close="cancel">
    <el-form :model="form">
      <el-form-item :label="$t('lang.roomID')" :label-width="formLabelWidth">
        <el-input v-model="this.$store.state.roomID" autocomplete="off" placeholder="Room + number" disabled></el-input>
      </el-form-item>
      <el-form-item :label="$t('lang.department')" :label-width="formLabelWidth">
        <el-input v-model="form.department" autocomplete="off"></el-input>
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
              v-model="form.start_time"
              :picker-options="{
                selectableRange: this.$store.state.rangeTime
              }"
              :placeholder="$t('lang.chooseATime')"
              style="width: 100%">
          </el-time-picker>
        </el-col>
      </el-form-item>
      <el-form-item :label="$t('lang.endTime')" :label-width="formLabelWidth">
        <el-col :span="11">
          <el-time-picker
              v-model="form.end_time"
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
      form: {
        title: this.$t('lang.reservation'),
        department: '',
        start_time: '',
        end_time: '',
        latent_index:-1,
      },
      formLabelWidth: '120px'
    }
  },
  methods:{
    restore() {
      this.form.department = ''
      this.form.start_time = ''
      this.form.end_time = ''
      this.form.latent_index = -1
      this.$store.state.isReserve = false
    },
    contentCheck() {
      for (const [key, value] of Object.entries(this.form)) {
        if (key !== 'title' || key !== 'latent_index'){
          if (value === ''){
            this.$message.error('No field is allowed to be null')
            return false
          }
        }
      }
      var start_hour = this.form.start_time.split(":")[0]
      var start_minute = this.form.start_time.split(":")[1]
      var end_hour = this.form.end_time.split(":")[0]
      var end_minute = this.form.end_time.split(":")[1]
      if (start_hour > end_hour || (start_hour === end_hour && end_minute <= start_minute)){
        this.$message.error('Start time no later than the end time')
        return false
      }
      return true
    },
    time_pick(){
      if (this.form.start_time === ''){
        return '00:15'
      }else {
        const times = this.form.start_time.split(":")
        var hour = parseInt(times[0])
        var minute = parseInt(times[1])
        if (minute + 15 >= 60){
          hour = hour + 1
          minute = 0
        }else {
          minute = minute + 15
        }
        return hour + ':' + minute
      }
    },
    submit() {
      var newDate = {}
      newDate.room_ID = this.$store.state.roomID
      newDate.department = this.form.department
      newDate.location = this.$store.state.buildingType + '\n' + this.$store.state.buildingName
      newDate.date = this.$store.state.date
      newDate.start_time = this.form.start_time
      newDate.end_time = this.form.end_time
      console.log(newDate)
      if (this.form.title === 'Information') {
        if (!this.contentCheck()) {
          return
        }
        // this.tableData.push(newDate)
      }else {
        if (!this.contentCheck()) {
          return
        }
        // this.$set(this.tableData, this.form.latent_index, newDate)
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