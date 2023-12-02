<template>
  <!--TODO-->
  <div id="back">
   <div id="show_list">
     <ul>
       <li>
         <div>
           <h1>{{$t('lang.canteen')}}</h1>
         </div>

         <div>
           <el-row :gutter="20">
             <el-col v-for="building in buildings" :key="building.buildingId" :span="6">
               <router-link :key="building.name" :to="`/user/building/${building.buildingId}/intro`">
                 <vs-card type=2>
                   <template #title>
                     <h3>{{ building.name}}</h3>
                   </template>
                   <template #img>
                     <img v-bind:src= img  alt="" style="height: 300px;">
                   </template>
                   <template #text>
                     <p>
                       {{ building.introduction}}
                     </p>
                   </template>
                 </vs-card>
               </router-link>
             </el-col>
           </el-row>
         </div>
       </li>
       <li>
         <div>
           <h1>{{$t('lang.library')}}</h1>
         </div>
       </li>
     </ul>
   </div>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "SUSBuilding",
  data() {
    return {
      buildings: [],
      img: require("../../assets/pad(canDelete)/background/img.png"),

    }
  },
  created() {
    this.fetchBuildingData();
  },
  methods: {
    fetchBuildingData() {
      this.$http("/public/building/get/simple")
          .then(response => {
            this.buildings = response.data.data;
            console.log(response.data);
          })
          .catch(function (error) {
            console.log(error);
          })
          .finally(function () {
          });
    }
  },

}
</script>

<style scoped>
#back {
  width: 100%;
  height: 1000px;
}

ul {
  list-style-type: none;
}
</style>