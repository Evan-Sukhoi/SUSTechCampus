<template>
  <!--TODO-->
  <div id="back">
   <div id="show_list">
     <ul>
       <li>
         <div>
           <h1>Canteen</h1>
         </div>

         <div>
           <el-row :gutter="20">
             <el-col v-for="building in buildings" :key="building.id" :span="6">
               <router-link :key="building.name" :to="`/user/building/${building.id}/intro`">
                 <vs-card type=2>
                   <template #title>
                     <h3>{{ building.name}}</h3>
                   </template>
                   <template #img>
                     <img v-bind:src="building.src" alt="" style="height: 300px;">
                   </template>
                   <template #text>
                     <p>
                       {{ building.description}}
                       {{building.src}}
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
           <h1>Library</h1>
         </div>
       </li>
     </ul>
   </div>

  </div>
</template>

<script>
export default {
  name: "SUSBuilding",
  data() {
    return {
      buildings: [
        {id: 1, name: "the first teaching building", description: "study here and learn more", src: require("@/assets/pad(canDelete)/background/youla.jpg")},
        {id: 2, name: "the second teaching building", description: "study here and learn more", src: require("@/assets/pad(canDelete)/background/youla.jpg")},
        {id: 3, name: "the third teaching building", description: "study here and learn more", src: require("../../assets/pad(canDelete)/background/youla.jpg")},
        {id: 4, name: "the fourth teaching building", description: "study here and learn more", src: require("../../assets/pad(canDelete)/background/youla.jpg")},
        {id: 5, name: "the fifth teaching building", description: "study here and learn more", src: require("../../assets/pad(canDelete)/background/youla.jpg")},
        {id: 6, name: "the sixth teaching building", description: "study here and learn more", src: require("../../assets/pad(canDelete)/background/youla.jpg")},
        {id: 7, name: "the seventh teaching building", description: "study here and learn more", src: require("../../assets/pad(canDelete)/background/youla.jpg")},
        {id: 8, name: "the eighth teaching building", description: "study here and learn more", src: require("../../assets/pad(canDelete)/background/youla.jpg")},
      ]
    }
  }
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