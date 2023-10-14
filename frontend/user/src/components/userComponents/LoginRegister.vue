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
    <vs-dialog v-model="active" prevent-close width="300px">
      <template #header>
        <div v-if="!isRegister">
          <h4 class="not-margin">
            {{$t('lang.welcome')}}
          </h4>
        </div>
        <div v-if="isRegister">
          <h4 class="not-margin">
            {{$t('lang.registerTitle')}}
          </h4>
        </div>
      </template>
      <div v-if="!isRegister">
        <div class="con-form">
          <el-input v-model="username" :placeholder="$t('lang.username')" class="userInput" prefix-icon="el-icon-user"></el-input>
          <el-input type="password" v-model="password" :placeholder="$t('lang.password')" class="userInput" prefix-icon="el-icon-lock"></el-input>
          <div class="flex">
            <vs-checkbox v-model="remember" class="checkbox">{{$t('lang.remember')}}</vs-checkbox>
            <a class="labels" href="#">{{$t('lang.forget')}}</a>
          </div>
        </div>
      </div>
      <div v-if="isRegister">
        <div class="con-form">
          <h3 style="float: left">{{$t('lang.chooseAvatar')}}</h3>
          <vs-avatar circle :badge="isLogin" badge-color="success" style="float: left; margin-left: 10px">
            <el-upload
                class="avatar-uploader"
                action=""
                :show-file-list="false"
                :on-success="handleAvatarSuccess"
                :before-upload="beforeAvatarUpload">
              <img v-if="imageUrl" :src="imageUrl" class="avatar">
              <i class="bx bx-up-arrow-alt" v-else></i>
            </el-upload>
          </vs-avatar>
          <el-input v-model="username" :placeholder="$t('lang.username')" class="userInput" prefix-icon="el-icon-user"></el-input>
          <el-input type="password" v-model="password" :placeholder="$t('lang.password')" class="userInput" prefix-icon="el-icon-lock"></el-input>
          <el-input type="password" v-model="passwordAgain" :placeholder="$t('lang.passwordAgain')" class="userInput" prefix-icon="el-icon-check"></el-input>
          <el-input v-model="email" :placeholder="$t('lang.email')" class="userInput" prefix-icon="el-icon-message"></el-input>
          <el-input v-model="phoneNumber" :placeholder="$t('lang.phoneNumber')" class="userInput" prefix-icon="el-icon-mobile-phone"></el-input>
        </div>
      </div>

      <template #footer>
        <div v-if="!isRegister">
          <div class="footer-dialog">
            <vs-button block @click="login">
              {{$t('lang.sign')}}
            </vs-button>

            <div class="new">
              {{$t('lang.hint')}}<a class="labels" @click="createAccount">{{$t('lang.newCount')}}</a>
            </div>
          </div>
        </div>
        <div v-if="isRegister">
          <div class="footer-dialog">
            <vs-button block @click="register">
              {{$t('lang.register')}}
            </vs-button>
            <vs-button block @click="back">
              {{$t('lang.back')}}
            </vs-button>
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
      passwordAgain: '',
      email: '',
      phoneNumber: '',
      remember: false,
      isLogin: false,
      photo: '',
      isRegister:false,
      isChose:false,
      imageUrl: ''
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
    },
    createAccount(){
      this.isRegister = true
    },
    back(){
      this.isRegister = false
    },
    handleAvatarSuccess(res, file) {
      this.imageUrl = URL.createObjectURL(file.raw)// need to be changed
    },
    beforeAvatarUpload(file) {
      const isJPG = file.type === 'image/jpeg'
      const isLt2M = file.size / 1024 / 1024 < 2

      if (!isJPG) {
        this.$message.error('上传头像图片只能是 JPG 格式!')
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!')
      }
      this.imageUrl = window.URL.createObjectURL(file) // need to be changed
      return isJPG && isLt2M
    },
    register(){
      this.username = ''
      this.password = ''
      this.passwordAgain = ''
      this.email = ''
      this.phoneNumber = ''
      this.imageUrl = ''
      this.active = false
      this.isRegister = false
      this.$vs.notification({
        position: 'top-center',
        title: this.$t('lang.registerState'),
        text: '',
      })
    },
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
  align-items: center;
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
a{
  cursor: pointer;
  color: hotpink;
}
a:hover{
  color: royalblue;
}
</style>