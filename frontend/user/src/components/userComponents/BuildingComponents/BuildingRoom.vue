<template>
  <div class="background">
    <div class="roomList">
      <vs-card type="3" v-for="room in roomInfo" :key="room.room_ID" class="box-card">
        <template #title>
          <h3>{{room.room_type}}</h3>
        </template>
        <template #img>
          <img :src="room.img" style="width: 200px; height: 200px">
        </template>
        <template #text>
          <p>
            {{$t('lang.capacity') + room.capacity}}
          </p>
        </template>
        <template #interactions>
          <vs-button danger icon>
            <i class='bx bx-heart'></i>
          </vs-button>
          <vs-button class="btn-chat" shadow success>
            <i class='bx bx-chat' ></i>
            <span class="span">
              lala
        </span>
          </vs-button>
        </template>
      </vs-card>
    </div>
  </div>
</template>
<script>
export default {
  name: "BuildingRoom",
  data(){
    return{
      roomInfo:[
        {room_type:'面当', room_ID:'101', img:require('../../../assets/pad(canDelete)/photo/img.jpg'), capacity:'40'},
        {room_type:'大众菜', room_ID:'102', img:require('../../../assets/pad(canDelete)/photo/img.jpg'), capacity:'40'},
        {room_type:'面当', room_ID:'101', img:require('../../../assets/pad(canDelete)/photo/img.jpg'), capacity:'40'},
        {room_type:'大众菜', room_ID:'102', img:require('../../../assets/pad(canDelete)/photo/img.jpg'), capacity:'40'},
      ]
    }
  },
  beforeMount() {
    let path = this.$route.path;
    path = path.split("/")
    this.$http({

    }).then(resp=>{

    }).catch(err=>{

    })
  }
}
</script>

<style>
.roomList{
  top: 150px;
  position: relative;
  width: 1000px;
  background-color: #f4d1cc;
  border-radius: 20px;
}
.background{
  display: flex;
  justify-content: center; /* 水平居中 */
  align-items: center;     /* 垂直居中 */
  position: relative;
  height: 100%;
  overflow: auto;
}
.box-card{
  margin-bottom: 10px;
  height: 220px;
  width: 500px;
  border-radius: 20px;
}
.card-img{
  margin-left: 10px;
  top: 10px;
}
</style>