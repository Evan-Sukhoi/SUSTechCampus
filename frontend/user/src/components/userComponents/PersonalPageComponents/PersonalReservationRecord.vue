<template>
  <div>
    <h1 id="title">{{$t('lang.reservationRecord')}}</h1>
    <el-container class="inf">
      <el-table
          :data="tableData"
          style="width: 100%"
          max-height="250">
        <el-table-column
            prop="roomID"
            :label="$t('lang.roomID')"
            width="150">
        </el-table-column>
        <el-table-column
            prop="department"
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
            prop="start_time"
            :label="$t('lang.startTime')"
            width="120"
            :formatter="formatDate">
        </el-table-column>
        <el-table-column
            prop="end_time"
            :label="$t('lang.endTime')"
            width="120"
            :formatter="formatDate">
        </el-table-column>
        <el-table-column
            fixed="right"
            :label="$t('lang.operation')"
            width="120">
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
  methods: {
    deleteRow(index, rows) {
      rows.splice(index, 1);
    },
    edit(index, tableData){
      this.$store.commit('edit', tableData[index])
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
      tableData: [{
        roomID:101,
        department:'计算机系',
        buildingType:'食堂',
        buildingName:'building1',
        date:'2023/9/10',
        start_time:new Date(2023, 9, 10, 15, 56, 23),
        end_time:new Date(2023, 9, 10, 17, 11, 12),
      }, {
        roomID:102,
        department:'数学系',
        buildingType:'图书馆',
        buildingName:'building2',
        date:'2023/9/10',
        start_time:new Date(2023, 9, 10, 14, 23, 23),
        end_time:new Date(2023, 9, 10, 16, 23, 12),
      }],
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
