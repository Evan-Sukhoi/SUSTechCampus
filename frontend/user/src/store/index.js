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
    //   user: localStorage.getItem('user') ? localStorage.getItem('user') : null,
    //   //若localSorage存在token，将值赋给Vuex.state.token
    //   token: localStorage.getItem('token') ? localStorage.getItem('token') : null,
     },
    mutations: {
      reserve (state, reservation) {
        this.state.roomID = reservation.roomID
        this.state.roomNumber = reservation.roomNumber
        this.state.date = reservation.date
        this.state.buildingType = reservation.buildingType
        this.state.buildingName = reservation.buildingName
        this.state.rangeTime = reservation.rangeTime
        this.state.isShow = true
      },
      edit(state, info){
        this.state.reservationID = info.reservationId
        this.state.roomID = info.roomId
        this.state.roomNumber = info.roomNumber
        this.state.department = info.description
        this.state.buildingType = info.buildingType
        this.state.buildingName = info.buildingName
        this.state.date = info.date
        this.state.start_time = new Date(info.startTime)
        this.state.end_time = new Date(info.endTime)
        this.state.rangeTime = info.rangeTime
        this.state.isShow = true
        this.state.isEdit = true
      },
      // setUser(state, user) {
      //   state.user = user
      //   localStorage.setItem('user', JSON.stringify(user))
      // },
      // setToken(state, token) {
      //   localStorage.setItem('token', token)
      //   state.token = token
      // },
      // logout(state) {
      //   localStorage.removeItem('token')
      //   state.token = null
      //   localStorage.removeItem('user')
      //   state.user = null
      // }

    }
  })

  export default store
