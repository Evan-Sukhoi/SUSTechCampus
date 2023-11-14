import Vue from "vue";
import Vuex from 'vuex';
Vue.use(Vuex)

const store = new Vuex.Store({
    state: {
      roomID:'',
      date:'',
      department:'',
      buildingType:'',
      buildingName:'',
      start_time:'',
      end_time:'',
      isShow: false,
      isEdit:false,
    },
    mutations: {
      reserve (state, reservation) {
        this.state.roomID = reservation.roomID
        this.state.date = reservation.date
        this.state.buildingType = reservation.buildingType
        this.state.buildingName = reservation.buildingName
        this.state.rangeTime = reservation.rangeTime
        this.state.isShow = true
      },
      edit(state, info){
        this.state.roomID = info.roomID
        this.state.department = info.department
        this.state.buildingType = info.buildingType
        this.state.buildingName = info.buildingName
        this.state.date = info.date
        this.state.start_time = info.start_time
        this.state.end_time = info.end_time
        this.state.isShow = true
        this.state.isEdit = true
      }
    }
  })

  export default store
