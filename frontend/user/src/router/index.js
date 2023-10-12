import Vue from 'vue'
import VueRouter from 'vue-router'
import UserView from "@/views/UserView.vue";
import HomePage from "@/components/userComponents/HomePage.vue";
import SUSAppointment from "@/components/userComponents/SUSAppointment.vue";
import SUSBuilding from "@/components/userComponents/SUSBuilding.vue";
import BuildingDetails from "@/components/userComponents/BuildingComponents/BuildingDetails.vue";
import SUSMap from "@/components/userComponents/SUSMap.vue";
import SUSService from "@/components/userComponents/SUSService.vue";
import SUSBusLine from "@/components/userComponents/SUSBusLine.vue";
import BuildingIntro from "@/components/userComponents/BuildingComponents/BuildingIntro.vue";
import BuildingBlog from "@/components/userComponents/BuildingComponents/BuildingBlog.vue";
import BuildingRoom from "@/components/userComponents/BuildingComponents/BuildingRoom.vue";
import BuildingMassageWall from "@/components/userComponents/BuildingComponents/BuildingMassageWall.vue";
import VisualizationDashboard from "@/components/adminComponents/VisualizationDashboard.vue";
import AdminView from "@/views/AdminView.vue";
import AppointmentManage from "@/components/adminComponents/AppointmentManage.vue";
import BuildingManage from "@/components/adminComponents/BuildingManage.vue";
import BuslineManage from "@/components/adminComponents/BuslineManage.vue";
import CommentManage from "@/components/adminComponents/CommentManage.vue";
import InformationSending from "@/components/adminComponents/InformationSending.vue";
import LogLog from "@/components/adminComponents/LogLog.vue";
import UserManage from "@/components/adminComponents/UserManage.vue";
import AdminLogin from "@/components/adminComponents/AdminLogin.vue";


Vue.use(VueRouter)

const routes = [
  {
    path: '/user',
    redirect:'/user/home',
    name: 'userHome',
    beforeEnter: (to, from, next) => {
      console.log('Navigating to:', to.path)
      next()
    },
    children:[
      {path:'/user/map', name:'map', component: SUSMap},
      {path:'/user/appointment', name:'appointment', component: SUSAppointment},
      {path:'/user/bus', name:'bus', component: SUSBusLine},
      {path:'/user/service', name:'service', component: SUSService},
      {path: '/user/building', name:'building', component: SUSBuilding},
      {path: '/user/building/:id', name: 'buildingDetails', component: BuildingDetails,
      children: [
        {path: '/user/building/:id/intro', name: 'intro', component: BuildingIntro},
        {path: '/user/building/:id/blog', name: 'blog', component: BuildingBlog},
        {path: '/user/building/:id/room', name: 'room', component: BuildingRoom},
        {path: '/user/building/:id/mass', name: 'mass', component: BuildingMassageWall}
      ]
      },
      {path: '/user/home', component: HomePage}
    ],
    component: UserView
  },
  {
    path: '/admin',
    redirect: '/admin/login',
    name: 'adminLogin',
    beforeEnter: (to, from, next) => {
      console.log('Navigating to:', to.path)
      next()
    },
    children: [
      {path: '/admin/appointmentManage', name: 'appointmentManage', component: AppointmentManage},
      {path: '/admin/buildingManage', name: 'buildingManage', component: BuildingManage},
      {path: '/admin/buslineManage', name: 'buslineManage', component: BuslineManage},
      {path: '/admin/commentManage', name: 'commentManage', component: CommentManage},
      {path: '/admin/informationSending', name: 'informationSending', component: InformationSending},
      {path: '/admin/log', name: 'log', component: LogLog},
      {path: '/admin/userManage', name: 'userManage', component: UserManage},
      {path: '/admin/visualization', name: 'visualization', component: VisualizationDashboard},
      {path: '/admin/login', name: 'login', component: AdminLogin},
    ],
    component: AdminView

  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
