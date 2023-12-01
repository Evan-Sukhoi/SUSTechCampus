<template>
  <div>
    <vs-card type="3">
      <template #title>
        <h3>Pot with a plant</h3>
      </template>
      <template #img>
        <img src="/foto5.png" alt="">
      </template>
      <template #text>
        <p>
          Lorem ipsum dolor sit amet consectetur, adipisicing elit.
        </p>
      </template>
    </vs-card>
  </div>
</template>
<script>
export default {
  name: "BuildingRoom",
  data(){
    return{
      room:[
        {room_type:'面当', room_ID:'101', img:require('../../../assets/pad(canDelete)/photo/img.png'), capacity:''},
        {room_type:'大众菜', room_ID:'102', img:require('../../../assets/pad(canDelete)/photo/img.png'), capacity:''},
      ]
    }
  },
  beforeMount() {
    const path = this.$route.path;
    path = path.split("/")
    this.$http({

    }).then(resp=>{

    }).catch(err=>{

    })
  }
}
</script>