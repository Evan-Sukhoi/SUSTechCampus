<template>
  <!--TODO-->
  <div id="back">
   <div id="show_list">
     <ul>
       <li>
         <div>
           <h1>{{$t('lang.library')}}</h1>
         </div>

         <div>
           <el-row :gutter="20">
             <el-col v-for="building in this.buildings" v-if="building.buildingType === `${$t('lang.library')}`" :key="building.buildingId" :span="6">
               <router-link :key="building.name" :to="`/user/building/${building.buildingId}/intro`">
                 <vs-card type=2>
                   <template #title>
                     <h3>{{ building.name}}</h3>
                   </template>
                   <template #img>
                     <img v-bind:src= building.coverUrl  alt="" style="height: 300px;">
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
           <h1>{{$t('lang.teachingBuilding')}}</h1>
         </div>

         <div>
           <el-row :gutter="20">
             <el-col v-for="building in this.buildings" v-if="building.buildingType === `${$t('lang.teachingBuilding')}`" :key="building.buildingId" :span="6">
               <router-link :key="building.name" :to="`/user/building/${building.buildingId}/intro`">
                 <vs-card type=2>
                   <template #title>
                     <h3>{{ building.name}}</h3>
                   </template>
                   <template #img>
                     <img v-bind:src= building.coverUrl  alt="" style="height: 300px;">
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
           <h1>{{$t('lang.playGround')}}</h1>
         </div>

         <div>
           <el-row :gutter="20">
             <el-col v-for="building in this.buildings" v-if="building.buildingType === `${$t('lang.playGround')}`" :key="building.buildingId" :span="6">
               <router-link :key="building.name" :to="`/user/building/${building.buildingId}/intro`">
                 <vs-card type=2>
                   <template #title>
                     <h3>{{ building.name}}</h3>
                   </template>
                   <template #img>
                     <img v-bind:src= building.coverUrl  alt="" style="height: 300px;">
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
           <h1>{{$t('lang.residence')}}</h1>
         </div>

         <div>
           <el-row :gutter="20">
             <el-col v-for="building in this.buildings" v-if="building.buildingType === `${$t('lang.residence')}`" :key="building.buildingId" :span="6">
               <router-link :key="building.name" :to="`/user/building/${building.buildingId}/intro`">
                 <vs-card type=2>
                   <template #title>
                     <h3>{{ building.name}}</h3>
                   </template>
                   <template #img>
                     <img v-bind:src= building.coverUrl  alt="" style="height: 300px;">
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
           <h1>{{$t('lang.college')}}</h1>
         </div>

         <div>
           <el-row :gutter="20">
             <el-col v-for="building in this.buildings" v-if="building.buildingType === `${$t('lang.college')}`" :key="building.buildingId" :span="6">
               <router-link :key="building.name" :to="`/user/building/${building.buildingId}/intro`">
                 <vs-card type=2>
                   <template #title>
                     <h3>{{ building.name}}</h3>
                   </template>
                   <template #img>
                     <img v-bind:src= building.coverUrl  alt="" style="height: 300px;">
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
           <h1>{{$t('lang.park')}}</h1>
         </div>

         <div>
           <el-row :gutter="20">
             <el-col v-for="building in this.buildings" v-if="building.buildingType === `${$t('lang.park')}`" :key="building.buildingId" :span="6">
               <router-link :key="building.name" :to="`/user/building/${building.buildingId}/intro`">
                 <vs-card type=2>
                   <template #title>
                     <h3>{{ building.name}}</h3>
                   </template>
                   <template #img>
                     <img v-bind:src= building.coverUrl  alt="" style="height: 300px;">
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
           <h1>{{$t('lang.conventionCenter')}}</h1>
         </div>

         <div>
           <el-row :gutter="20">
             <el-col v-for="building in this.buildings" v-if="building.buildingType === `${$t('lang.conventionCenter')}`" :key="building.buildingId" :span="6">
               <router-link :key="building.name" :to="`/user/building/${building.buildingId}/intro`">
                 <vs-card type=2>
                   <template #title>
                     <h3>{{ building.name}}</h3>
                   </template>
                   <template #img>
                     <img v-bind:src= building.coverUrl  alt="" style="height: 300px;">
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
           <h1>{{$t('lang.officeSpace')}}</h1>
         </div>

         <div>
           <el-row :gutter="20">
             <el-col v-for="building in this.buildings" v-if="building.buildingType === `${$t('lang.officeSpace')}`" :key="building.buildingId" :span="6">
               <router-link :key="building.name" :to="`/user/building/${building.buildingId}/intro`">
                 <vs-card type=2>
                   <template #title>
                     <h3>{{ building.name}}</h3>
                   </template>
                   <template #img>
                     <img v-bind:src= building.coverUrl  alt="" style="height: 300px;">
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
  height: 100%;
  overflow: auto;
  overflow-x: hidden;
  background-image: url("../../assets/pad(canDelete)/background/提瓦特.jpg");
  background-repeat: no-repeat;
  background-size: cover;
}

ul {
  list-style-type: none;
}

</style>