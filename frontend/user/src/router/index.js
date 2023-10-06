import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '@/views/HomeView.vue'
import HomePage from "@/components/HomePage";
import SUSAppointment from "@/components/SUSAppointment";
import SUSBuilding from "@/components/SUSBuilding";
import BuildingDetails from "@/components/BuildingDetails";
import SUSMap from "@/components/SUSMap";
import SUSService from "@/components/SUSService";
import SUSBusLine from "@/components/SUSBusLine";

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    redirect:'/#',
    name: 'home',
    children:[
      {path:'/map', name:'map', component: SUSMap},
      {path:'/appointment', name:'appointment', component: SUSAppointment},
      {path:'/bus', name:'bus', component: SUSBusLine},
      {path:'/service', name:'service', component: SUSService},
      {path: '/building', name:'building', component: SUSBuilding},
      {path: '/building/:id', name: 'buildingDetails', component: BuildingDetails},
      {path: '#', component: HomePage}
    ],
    component: HomeView
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
