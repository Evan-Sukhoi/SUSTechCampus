<template>
  <div>
  <el-dialog :title="title" :visible.sync="$store.state.isShow" :before-close="cancel" >
    <el-form :model="$store.state">
      <el-form-item :label="$t('lang.roomID')" :label-width="formLabelWidth">
        <el-input v-model="$store.state.roomID" autocomplete="off" placeholder="Room + number" disabled></el-input>
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
      title: this.$t('lang.reservation'),
      latent_index:-1,
      formLabelWidth: '120px'
    }
  },
  methods:{
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
            this.$message.error('No field is allowed to be null')
            return false
          }
        }
      }
      var start_hour = this.$store.state.start_time.getHours()
      var start_minute = this.$store.state.start_time.getMinutes()
      var end_hour = this.$store.state.end_time.getHours()
      var end_minute = this.$store.state.end_time.getMinutes()
      if (start_hour > end_hour || (start_hour === end_hour && end_minute <= start_minute)){
        this.$message.error('Start time no later than the end time')
        return false
      }
      return true
    },
    submit() {
      var newDate = {}
      newDate.room_ID = this.$store.state.roomID
      newDate.department = this.$store.state.department
      newDate.buildingType = this.$store.state.buildingType
      newDate.buildingName = this.$store.state.buildingName
      newDate.date = this.$store.state.date
      newDate.start_time = this.$store.state.start_time
      newDate.end_time = this.$store.state.end_time
      console.log(newDate)
      if (this.$store.state.isEdit) {
        if (!this.contentCheck()) {
          return
        }
        console.log('edit')
      }else {
        if (!this.contentCheck()) {
          return
        }
        console.log('reserve')
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