<template>
  <div class="center">
    <vs-button flat @click="active=!active" style="float: right" v-if="!isLogin">
      {{$t('lang.login')}}
    </vs-button>
    <vs-tooltip shadow interactivity bottom style="float: right; margin-right: 10px">
      <vs-avatar circle :badge="isLogin" badge-color="success" >
        <i class="bx bx-question-mark" v-if="!isLogin"></i>
        <img :src="photo" alt="" v-if="isLogin">
      </vs-avatar>
      <template #tooltip>
        <div v-if="isLogin">
          <el-divider></el-divider>
          <vs-button @click="personalPage" class="Button">{{$t("lang.personalPage")}}</vs-button>
          <vs-button @click="logout" class="Button">{{$t("lang.logout")}}</vs-button>
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
          <div style="display: flex; justify-content: space-between; text-align: center">
            <el-input v-model="code" :placeholder="$t('lang.code')" style="margin-top: 10px;width: calc(70%);" prefix-icon="el-icon-bell"></el-input>
            <vs-button v-if="!activeSend" style="margin-top: 10px;" relief @click="sendCode"><i class="bx bxl-ok-ru">{{$t('lang.code')}}</i></vs-button>
            <vs-button v-else style="margin-top: 10px;" disabled relief success><i class="bx bx-time">{{countDown + '/s'}}</i></vs-button>
          </div>
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
import FormData from 'form-data'
import JSEncrypt from 'jsencrypt';

const encrypt = new JSEncrypt();
encrypt.setPublicKey('你的公钥');
export default {
  name:'LoginRegister',
  data(){
    return{
      active: false,
      username: '',
      password: '',
      passwordAgain: '',
      email: '',
      code:'',
      phoneNumber: '',
      remember: false,
      isLogin: false,
      photo: '',
      isRegister:false,
      isChose:false,
      imageUrl: '',
      imgFile: '',
      countDown:60,
      timer:null,
      activeSend:false
    }
  },
  methods:{
    getPublicKey() {
      this.$http.get('http://localhost:8081/public/get-key').then(res => {
        let publicKey = res.data.publicKey;
        console.log("publicKey: ", publicKey);
        encrypt.setPublicKey(publicKey);
      }).catch(err => {
        console.error('Failed to fetch public key:', err);
      });
    },
    sendCode(){
      if (this.email === ''){
        this.$vs.notification({
          color:'danger',
          position: 'top-center',
          title: this.$t('lang.errorEmptyEmail'),
          text: '',
        })
        return
      }
      this.activeSend = true
      this.startTimer()
      this.$http.post('public/send-auth-code?email='+ this.email).then(resp=>{
        if (resp.status === 200){
          this.$vs.notification({
            color:'success',
            position: 'top-center',
            title: this.$t('lang.seeCode'),
            text: '',
          })
        }else {
          this.$vs.notification({
            color:'primary',
            position: 'top-center',
            title: resp.data,
            text: '',
          })
        }
      }).catch(err=>{
        console.log(err)
        this.$vs.notification({
          color:'danger',
          position: 'top-center',
          title: err,
          text: '',
        })
      })
    },
    startTimer(){
      this.countDown = 60
      this.timer = setInterval(() =>{
        if(this.countDown === 0){
          clearInterval(this.timer);
          this.timer = null;
          this.activeSend = false
        }else{
          this.countDown--;
        }
      },1000);
    },
    login(){
      /*if request is success*/
      /*this.photo need to be correct*/
      const data = {
        username: this.username,
        password: this.password
      };
      // console.log("Encrypted password: ", data.password);
      this.$http.post('/public/login', data).then(resp =>{
        console.log(resp)
        if (resp.status == 200){
          this.active = false
          this.isLogin = true
          this.photo = resp.data.imageUrl
          if (this.remember){
            localStorage.setItem('username', this.username)
            localStorage.setItem('password', this.password)
            localStorage.setItem('remember', true)
          }
          // localStorage.setItem('userID', userID)
          localStorage.setItem('photo', this.photo)
          localStorage.setItem('isLogin', this.isLogin)
          localStorage.setItem('userID', resp.data.userId)
          localStorage.setItem('token', resp.data.token)
          localStorage.setItem('isBlocked', resp.data.isBlocked)
          console.log(resp.data.isBlocked)
        }else {
          this.$vs.notification({
            color:'danger',
            position: 'top-center',
            title: this.$t('lang.errorLogin'),
            text: '',
          })
        }
        console.log(resp)
      }).catch(err=>{
        console.log(err)
        this.$vs.notification({
          color:'danger',
          position: 'top-center',
          title: this.$t('lang.error'),
          text: '',
        })
      })

    },
    logout(){
      this.isLogin = false
      this.photo = ''
      if (!this.remember){
        this.username = ''
        this.password = ''
        localStorage.removeItem('username')
        localStorage.removeItem('password')
      }
      localStorage.removeItem('photo')
      localStorage.removeItem('isLogin')
      localStorage.removeItem('token')
      this.$router.push({path:'/user/home'}).catch(err=>err)
    },
    personalPage(){
      this.$router.push({path:'/user/personalPage'}).catch(err=>err)
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
        this.$vs.notification({
          color:'danger',
          position: 'top-center',
          title: this.$t('lang.errorFormat'),
          text: '',
        })
      }
      if (!isLt2M) {
        this.$vs.notification({
          color:'danger',
          position: 'top-center',
          title: this.$t('lang.errorSize'),
          text: '',
        })
      }
      this.imageUrl = window.URL.createObjectURL(file) // need to be changed
      this.imgFile = file
      return isJPG && isLt2M
    },
    register(){
      if (this.username === '' || this.password === '' || this.passwordAgain === '' || this.email === '' || this.phoneNumber === '' || this.code === ''){
        this.$vs.notification({
          color:'danger',
          position: 'top-center',
          title: this.$t('lang.errorEmpty'),
          text: '',
        })
        return
      }
      if (this.imageUrl === ''){
        this.$vs.notification({
          color:'danger',
          position: 'top-center',
          title: this.$t('lang.errorPhoto'),
          text: '',
        })
        return
      }
      if (this.password !== this.passwordAgain){
        this.$vs.notification({
          color:'danger',
          position: 'top-center',
          title: this.$t('lang.errorPassword'),
          text: '',
        })
        return
      }
      var password_patten= /^[\S]{6,13}$/
      if (!password_patten.test(this.password)){
        this.$vs.notification({
          color:'danger',
          position: 'top-center',
          title: this.$t('lang.errorPassFormat'),
          text: '',
        })
        return
      }
      var email_patten = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/
      if (!email_patten.test(this.email)){
        this.$vs.notification({
          color:'danger',
          position: 'top-center',
          title: this.$t('lang.errorEmail'),
          text: '',
        })
        return
      }
      var photo_patten = /\d{11}/
      if (!photo_patten.test(this.phoneNumber)){
        this.$vs.notification({
          color:'danger',
          position: 'top-center',
          title: this.$t('lang.errorPhone'),
          text: '',
        })
        return
      }
      const formData = new FormData();
      formData.append('file', this.imgFile);
      var data = {
        username:this.username,
        password:encrypt.encrypt(this.password),
        email:this.email,
        phoneNumber:this.phoneNumber,
        authCode:this.code
      }
      console.log("Encrypted password: ", data.password)
      formData.append('registerParam', new Blob([JSON.stringify(data)], {type: "application/json"}))
      console.log(formData)
      this.$http.post('/public/register', formData, {headers: {'Content-Type': 'multipart/form-data'}}).then(resp => {
        console.log(resp);
        if (resp.status==200){
          this.username = ''
          this.password = ''
          this.passwordAgain = ''
          this.email = ''
          this.phoneNumber = ''
          this.imageUrl = ''
          this.imgFile = ''
          this.active = false
          this.isRegister = false
          this.$vs.notification({
            color:'success',
            position: 'top-center',
            title: this.$t('lang.registerState'),
            text: '',
          })
        }else {
          this.$vs.notification({
            color:'primary',
            position: 'top-center',
            title: resp.data,
            text: '',
          })
        }
      }).catch(err=>{
        console.log(err)
        this.$vs.notification({
          color:'primary',
          position: 'top-center',
          title: this.$t('lang.error'),
          text: err.body,
        })
      })
    },
  },
  beforeMount() {
    this.username = localStorage.getItem('username') || ''
    this.password = localStorage.getItem('password') || ''
    this.remember = localStorage.getItem('remember') || false
    this.userID = localStorage.getItem('userID') || ''
    this.photo = localStorage.getItem('photo') || ''
    this.isLogin = localStorage.getItem('isLogin') || ''
    //获取公钥
    // this.getPublicKey();
  }
}
</script>

<style scoped>
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
.Button{
  width: 100px;
}
</style>