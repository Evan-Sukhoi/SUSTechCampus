<template>
  <!--TODO-->
  <div id="back2">
    <div class="svg_wrap z-hide2" style="position:absolute; top:30%;  left:50%; z-index: 1; margin-left:-300px">
      <svg version="1.1" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px" viewBox="0 0 600 170" xml:space="preserve" class="bayleyssvg">
				<path id="bayleys" stroke-dasharray="1200 1000" d="
				M42.88,2.76s-6.24,3.57-8.47,8.4c-1,2-.41,3.35,3.2,1.06a75.7,75.7,0,0,1,15.26-7.7C58.93,2.32,70.05-.36,70.85,0c.33.16.34.8-.29,1.41C54.82,16.3,53.12,27.37,57.41,29.12c2.12.86,5.7.36,8.89-.71s6.3-2.45,9.87-2.57c8.4-.27,11.92,16.1,2.85,31.61-5.08,8.69-18.63,19.75-19.6,19.34-.44-.17-.58-.67-.39-1.47.13-.53,5-11.52,1.14-13.27-.6-.27-2.11.17-3.53,3.65-1.84,4.51-3.25,5.34-4,5.15s-.48-1.44-.36-2.28.29-1.54-.17-1.24-4.78,5.32-6.15,5.2c-.48,0-.79-.58-.48-1.33s.64-1.74.64-1.74-7.46,7.44-16.57,9.23c-.68.13-2.5.59-3.08.27a.84.84,0,0,1,.06-1.35c.59-.41,10.24-5.54,14.42-12.76,1.26-2.19,1.3-3.31,1-3.89-.84-1.49-5,0-7.65,1.16s-6.75,3.6-11.89,4.5c-6.38,1.11-14-.87-17.92-5.9C-.1,54.92-.39,49.21.26,43.42c2-18.14,18.9-29.65,24.76-33.35C32.09,5.6,42.57.94,43.38,1.28c.41.17.59.82-.5,1.48z
				M41.6,109.61V88.75a2.74,2.74,0,0,0-2.7-2.6l.06,0A115,115,0,0,1,18,83.87a1.43,1.43,0,0,0-1.58.47c-.34.59-.18,1,0,1.73s1,3.66,1,3.66c.56,2.48,3.78,3.08,5.83,3.53l9.18,2c4.43,1,5.64,4.51,5.64,9.18v4.14c0,3.64-.58,6-2.94,6.09-3,.14-5.15-2.91-6.3-5.56a1.21,1.21,0,0,1-.08-.17l0-.09c-.1-.24-.19-.47-.27-.69-.78-2-2.54-6.37-2.54-6.37a2.39,2.39,0,0,0-.6-1,1.81,1.81,0,0,0-1.16-.36h-3.1l4,14.25c.82,2.92,1.82,6.3,4.07,8.44,2.76,2.67,7.26,4.11,13.23,4.11s10.48-1.44,13.23-4.11c2.24-2.15,3.25-5.52,4.07-8.44l4-14.25H60.61a1.81,1.81,0,0,0-1.16.36,2.43,2.43,0,0,0-.59,1s-1.75,4.38-2.54,6.37c-.08.22-.17.45-.28.69l0,.09-.07.17c-1.18,2.65-3.34,5.7-6.33,5.56-2.34-.11-2.93-2.45-2.93-6.09v-4.14c0-4.66,1.2-8.2,5.64-9.18l9.17-2c2.06-.45,5.29-1,5.84-3.53,0,0,.85-2.95,1-3.66s.33-1.14,0-1.73a1.43,1.43,0,0,0-1.58-.47,115.1,115.1,0,0,1-20.93,2.25l.07,0a2.76,2.76,0,0,0-2.71,2.6v20.86a.81.81,0,1,1-1.62,0z
				M142,53.24a36.72,36.72,0,0,0-13-2.63c-8,0-12.5,3.77-12.5,8.93,0,3.66,2.3,7.09,10.08,12.36l5.1,3.55c10.71,7.32,18,12.69,18,23.23,0,11.78-10.21,21-26.4,21-9.45,0-17.08-3.48-21.29-5.42v-9.91l5,2.51c4.72,2.18,9.69,4.23,15.94,4.23,8.42,0,12.23-4.81,12.23-9.38,0-4.93-3.82-8.13-11.22-13.28l-5.1-3.66C109.75,78.65,102,72.23,102,62.06c0-11.22,11-20,26.27-20A45.56,45.56,0,0,1,144,44.71v9.46Z
				M172.4,43.06v42.8c0,10.18.25,14.76,3.18,18.76s8,6.07,13,6.07c4.71,0,11.09-2,13.89-7.44,1.91-3.32,2.81-6.87,2.81-17.5V43.06h11.1v43c0,5.38,2.3,33.64-30.86,33.64-4.71,0-19.26-.57-26.39-13.51-2.56-4.58-3.07-9.84-3.07-18.42V43.06Z
				M264.61,53.24a36.73,36.73,0,0,0-13-2.63c-8,0-12.5,3.77-12.5,8.93,0,3.66,2.3,7.09,10.07,12.36l5.1,3.55c10.71,7.32,18,12.69,18,23.23,0,11.78-10.19,21-26.39,21-9.45,0-17.08-3.48-21.29-5.42v-9.91l5,2.51c4.72,2.18,9.69,4.23,15.94,4.23,8.42,0,12.24-4.81,12.24-9.38,0-4.93-3.83-8.13-11.22-13.28l-5.1-3.66c-9-6.17-16.83-12.59-16.83-22.76,0-11.22,11-20,26.26-20a45.54,45.54,0,0,1,15.73,2.68v9.46Z
				M333.78,43.06v8.58H311.85v66.93h-16.2V51.64H273.59V43.06Z
				M342.68,98.46a14.66,14.66,0,0,0,3.43,9.18c2.46,2.65,5.81,3.53,9.28,3.53A20,20,0,0,0,368,106.63l1.64-1.29V115l-.44.32c-4.54,3.3-10.21,4.32-15.66,4.32-5.95,0-11.35-1.79-15.35-6.43-4.18-4.85-5.49-11.25-5.49-17.58,0-6.52,1.72-12.56,6-17.47a19.18,19.18,0,0,1,15-6.95c5.43,0,10.34,1.66,14,5.93,3.83,4.51,5,10.48,5,16.31v5Zm20.38-8.38a12.68,12.68,0,0,0-2.49-7.54,8.47,8.47,0,0,0-6.9-2.92,9.52,9.52,0,0,0-7.15,3.17,14.78,14.78,0,0,0-3.69,7.3Z
				M433.26,77.51a16.09,16.09,0,0,1,13.19-6.31C458,71.21,462,79.39,462,90.29v28.3h-9.64V92.1c0-6.28-1.5-12.32-8.64-12.32a9.27,9.27,0,0,0-7.44,3.54,13.75,13.75,0,0,0-3,9.13v26.14h-9.67V51h9.67Z
				M414.53,116.43a23.38,23.38,0,0,1-12.46,3.2c-6.08,0-11.53-1.92-15.76-6.49s-6-10.38-6-16.68c0-6.83,1.76-13.22,6.41-18.27s10.56-7,17.16-7a22.94,22.94,0,0,1,10.59,2.27l.56.29V84.24L413.43,83a15.41,15.41,0,0,0-9.52-3.23,12.54,12.54,0,0,0-9.9,4.31c-2.87,3.25-3.84,7.42-3.84,11.72,0,4.06.85,8.18,3.59,11.25a12.4,12.4,0,0,0,9.71,4,16.08,16.08,0,0,0,9.91-3.56l1.63-1.25v9.87Z
				"></path>
				</svg>
    </div>
  </div>
</template>

<script>
export default {
  name: "HomePage"
}
</script>

<style scoped>
.bayleyssvg {
  enable-background:new 0 0 600 170;
  display: block;
  width: 800px;
  margin: 40px auto 15px;
}
#back2{
  width: 100%;
  height: 1000px;
  background-image: url("../../assets/pad(canDelete)/background/main.jpg");
  background-size: cover;
}
#bayleys {
  stroke-width: 0;
  animation: logo 8s linear 1 alternate, 2s 15s ease forwards logohide;
}

#bayleys {
  fill: #ffffff;
  stroke: #ffffff;
}

@keyframes logo {
  0% {
    stroke-width: 1pt;
    stroke-dashoffset: 1200;
    fill-opacity: 0;
  }
  40% {
    fill-opacity: 0;
    stroke-width: 1pt;
    stroke-dashoffset: 1200;
  }
  60% {
    fill-opacity: 0;
    stroke-width: 1pt;
    stroke-dashoffset: 0;
  }
  100% {
    fill-opacity: 1;
    stroke-width: 0;
    stroke-dashoffset: 0;
  }
}

@keyframes logohide {
  0% {
    opacity: 1;
    z-index: 99999;
  }

  100% {
    opacity: 0;
    z-index: 0;
  }
}
@media screen and (max-width: 768px) {
  #back2{
    background-image: url("../../assets/pad(canDelete)/background/img.png");
    background-size: cover;
    width: 100%;
    height: 100%;
  }
  .bayleyssvg {
    display: none;
  }
  .svg_wrap z-hide2{

  }
}
</style>