<template>
  <div class="center">
    <vs-button flat @click="active=!active" style="float: right" v-if="!isLogin">
      {{$t('lang.login')}}
    </vs-button>
    <vs-tooltip shadow interactivity bottom style="float: right; margin-right: 10px">
      <vs-avatar circle :badge="isLogin" badge-color="success" >
        <i class="bx bx-question-mark" v-if="!isLogin"></i>
        <img src="../../assets/pad(canDelete)/photo/img.png" alt="" v-if="isLogin">
      </vs-avatar>
      <template #tooltip>
        <div v-if="isLogin">
          <el-divider></el-divider>
          <vs-button @click="logout">{{$t("lang.logout")}}</vs-button>
        </div>
      </template>
    </vs-tooltip>
    <vs-dialog v-model="active">
      <template #header>
        <h4 class="not-margin">
          {{$t('lang.welcome')}}
        </h4>
      </template>
      <div class="con-form">
        <el-input v-model="username" :placeholder="$t('lang.username')" class="userInput"></el-input>
        <el-input type="password" v-model="password" :placeholder="$t('lang.password')" class="userInput"></el-input>
        <div class="flex">
          <vs-checkbox v-model="remember" class="checkbox">{{$t('lang.remember')}}</vs-checkbox>
          <a class="labels" href="#">{{$t('lang.forget')}}</a>
        </div>
      </div>

      <template #footer>
        <div class="footer-dialog">
          <vs-button block @click="login">
            {{$t('lang.sign')}}
          </vs-button>

          <div class="new">
            {{$t('lang.hint')}}<a class="labels" href="#">{{$t('lang.newCount')}}</a>
          </div>
        </div>
      </template>
    </vs-dialog>
  </div>
</template>
<script>
export default {
  name:'LoginRegister',
  data(){
    return{
      active: false,
      username: '',
      password: '',
      remember: false,
      isLogin: false,
      photo: '',
    }
  },
  methods:{
    login(){
      /*if request is success*/
      /*this.photo need to be correct*/
      var request = true
      if (request){
        this.active = false
        this.isLogin = true
        this.photo = '../assets/pad(canDelete)/photo/img.png'
        if (this.remember){
          localStorage.setItem('username', this.username)
          localStorage.setItem('password', this.password)
        }
        localStorage.setItem('photo', this.photo)
        localStorage.setItem('isLogin', this.isLogin)
      }else {
        this.$message({
          showClose: true,
          message: 'Username or password was wrong',
          type: 'error'
        });
      }
    },
    logout(){
      this.isLogin = false
      this.photo = ''
      this.username = ''
      this.password = ''
      if (!this.remember){
        localStorage.removeItem('username')
        localStorage.removeItem('password')
      }
      localStorage.removeItem('photo')
      localStorage.removeItem('isLogin')
    }
  },
  beforeMount() {
    this.username = localStorage.getItem('username') || ''
    this.password = localStorage.getItem('password') || ''
    this.photo = localStorage.getItem('photo') || ''
    this.isLogin = localStorage.getItem('isLogin') || ''
  }
}
</script>

<style>
.not-margin{
  margin: 0px;
  font-weight: normal;
  padding:10px;
}
.labels{
  font-size: .8rem;
  opacity: .7;
}
.labels:hover{
  opacity: 1;
}
.con-form{
  width: 100%;
}
.checkbox.labels{
  font-size: .8rem;
}
.userInput{
  margin-top: 10px;
  width: calc(100%);
}
.flex{
  display: flex;
  align-items: center;
  justify-content: space-between;
}
.footer-dialog{
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
  width: calc(100%);
}

</style>