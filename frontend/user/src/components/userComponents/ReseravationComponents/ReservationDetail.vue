<template>
  <div>
    <h1 id="title">Conference Rooms</h1>
    <el-container class="inf">
      <el-table
          :data="tableData"
          style="width: 100%"
          max-height="250">
        <el-table-column
            prop="room_name"
            label="Room name"
            width="150">
        </el-table-column>
        <el-table-column
            prop="department"
            label="Department"
            width="120">
        </el-table-column>
        <el-table-column
            prop="type"
            label="Type"
            width="120">
        </el-table-column>
        <el-table-column
            prop="location"
            label="Location"
            width="120"
            style="text-align: center">
        </el-table-column>
        <el-table-column
            prop="date"
            label="Date"
            width="120">
        </el-table-column>
        <el-table-column
            prop="start_time"
            label="Start Time"
            width="120">
        </el-table-column>
        <el-table-column
            prop="end_time"
            label="End Time"
            width="120">
        </el-table-column>
        <el-table-column
            prop="max_duration"
            label="Max Duration"
            width="120">
        </el-table-column>
        <el-table-column
            fixed="right"
            label="Operation"
            width="120">
          <template slot-scope="scope">
            <el-button
                @click.native.prevent="deleteRow(scope.$index, tableData)"
                type="text"
                size="small">
              Delete
            </el-button>
            <el-button
                @click.native.prevent="editRow(scope.$index, tableData)"
                type="text"
                size="small">
              Edit
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-container>
    <el-button
        type="success"
        size="medium"
        @click=addRow>
      Add room
    </el-button>
    <ReservationForm></ReservationForm>
  </div>

</template>

<script>
import ReservationForm from "@/components/userComponents/ReseravationComponents/ReservationForm";
export default {
  name: "HelloWorld",
  components:{
    ReservationForm
  },
  methods: {
    deleteRow(index, rows) {
      rows.splice(index, 1);
    },
    editRow(index, rows) {
      this.form.title = 'Edit Room'
      this.form.room_name = rows[index]['room_name']
      this.form.room_type = rows[index]['type']
      this.form.department = rows[index]['department']
      this.form.date = rows[index]['date']
      this.form.start_time = rows[index]['start_time']
      this.form.end_time = rows[index]['end_time']
      const location = rows[index]['location'].split('\n')
      this.form.location1 = location[0]
      this.form.location2 = location[1]
      this.form.max_duration = rows[index]['max_duration'].substr(0, rows[index]['max_duration'].length - 1)
      this.form.latent_index = index
      this.dialogFormVisible = true
    },
    addRow(){
      this.form.title = 'Information'
      this.dialogFormVisible = true
    },
    restore() {
      this.dialogFormVisible = false
      this.form.title = ''
      this.form.room_name = ''
      this.form.room_type = ''
      this.form.department = ''
      this.form.date = ''
      this.form.start_time = ''
      this.form.end_time = ''
      this.form.location1 = ''
      this.form.location2 = ''
      this.form.max_duration = ''
      this.form.latent_index = -1
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
      var name_patten = /^Room\d+$/
      if (!name_patten.test(this.form.room_name)){
        this.$message.error('Room name should be a combination of letters and numbers (e.g., Room1 Room2)')
        return false
      }
      var room_patten = /^\d+[A-z]$/
      if (!room_patten.test(this.form.location2)){
        this.$message.error('Room number should be like \'434A\'')
        return false
      }
      var max_duration_patten = /^\d+$/
      if (!max_duration_patten.test(this.form.max_duration)){
        this.$message.error('Max duration must be a number')
        return false
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
      newDate.room_name = this.form.room_name
      newDate.department = this.form.department
      newDate.type = this.form.room_type
      newDate.location = this.form.location1 + '\n' + this.form.location2
      newDate.date = this.form.date
      newDate.start_time = this.form.start_time
      newDate.end_time = this.form.end_time
      newDate.max_duration = this.form.max_duration + 'h'
      console.log(newDate)
      if (this.form.title === 'Information') {
        if (!this.contentCheck()) {
          return
        }
        this.tableData.push(newDate)
      }else {
        if (!this.contentCheck()) {
          return
        }
        this.$set(this.tableData, this.form.latent_index, newDate)
      }
      this.restore()
    },
    cancel() {
      this.restore()
    },
  },
  data() {
    return {
      tableData: [{
        room_name: 'Room1',
        department: 'Electrical',
        type: 'Small',
        location: 'South Building\n426A',
        date: '2023/9/10',
        start_time: '08:00',
        end_time: '20:00',
        max_duration: '2h',
      }, {
        room_name: 'Room2',
        department: 'Computer Science',
        type: 'Big',
        location: 'South Building\n434A',
        date: '2023/9/10',
        start_time: '00:00',
        end_time: '24:00',
        max_duration: '4h',
      }],
      dialogFormVisible: false,
      form: {
        title: '',
        room_name: '',
        room_type: '',
        department: '',
        date: '',
        start_time: '',
        end_time: '',
        location1: '',
        location2: '',
        max_duration: '',
        latent_index:-1,
      },
      formLabelWidth: '120px'
    }
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
</style>
