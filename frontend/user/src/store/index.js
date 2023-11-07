import Vue from "vue";
import Vuex from 'vuex';
Vue.use(Vuex)

const store = new Vuex.Store({
    state: {
      isReserve: false,
    },
    mutations: {
      reserve (state, reservation) {
        this.state.roomID = reservation.roomID
        this.state.date = reservation.date
        this.state.buildingType = reservation.buildingType
        this.state.buildingName = reservation.buildingName
        this.state.rangeTime = reservation.rangeTime
        this.state.isReserve = true
      }
    }
  })

  export default store
