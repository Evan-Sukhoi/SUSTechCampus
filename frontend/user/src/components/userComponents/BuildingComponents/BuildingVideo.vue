<template>
  <div class="video-container">
    <video ref="videoPlayer" controls class="custom-video">
      <source :src="videoUrl" type="video/mp4">
      Your browser does not support the video tag.
    </video>
  </div>
</template>

<script>
export default {
  data() {
    return {
      videoUrl: 'https://1001b.cn/chronus/url/v1/getVideoUrl/da007a025ffb43019ab8ae69448d3a61',
      posterUrl: 'https://play.1001b.cn/25fc6ec1vodtransnj1257837729/592f7bc73270835012374887671/coverBySnapshot/coverBySnapshot_20134_0.jpg'
    };
  },
};
</script>

<style>
.video-container {
  max-width: 800px;
  margin: 0 auto;
}

.custom-video {
  width: 100%;
  border-radius: 8px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
}
</style>
