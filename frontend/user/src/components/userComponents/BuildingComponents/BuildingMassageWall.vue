<template>
  <div id="word-cloud"></div>
</template>

<script>
import * as d3 from "d3";

export default {
  props: {
    words: Array, // 输入的词云数据
  },
  mounted() {
    this.drawWordCloud();
  },
  methods: {
    drawWordCloud() {
      const words = this.words;
      const width = 500;
      const height = 300;

      const svg = d3.select("#word-cloud")
          .append("svg")
          .attr("width", width)
          .attr("height", height);

      const g = svg.append("g")
          .attr("transform", `translate(${width / 2},${height / 2})`);

      g.selectAll("text")
          .data(words)
          .enter()
          .append("text")
          .style("font-size", d => `${d.size}px`)
          .style("fill", d => d.color)
          .attr("text-anchor", "middle")
          .attr("transform", (d, i) => `translate(${i * 30},${i * 20}) rotate(${i * 20})`)
          .text(d => d.text);
    }
  }
}
</script>

<style>
/* 添加一些样式 */
#word-cloud svg {
  background-color: #f0f0f0;
}
</style>
