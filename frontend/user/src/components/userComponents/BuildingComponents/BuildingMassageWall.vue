<template>
  <div>
    <div>
      <p>
        hello massage
      </p>
    </div>

    <div id="word-cloud"></div>
  </div>

</template>
<script>
import * as d3 from 'd3'
import cloud from 'd3-cloud'
export default {
  data() {
    return {
      words: [
        { text: "Hello", size: 40 },
        { text: "World", size: 30 },
        { text: "D3", size: 50 },
        { text: "Vue", size: 25 },
        { text: "Word", size: 20 },
        { text: "Cloud", size: 35 },
      ],
    };
  },
  mounted() {
    this.createWordCloud();
  },
  methods: {
    createWordCloud() {
      const data = this.words;
      const width = 500;
      const height = 300;

      const layout = cloud()
          .size([width, height])
          .words(data)
          .padding(5)
          .fontSize(d => d.size)
          .on("end", draw);

      layout.start();

      function draw(words) {
        d3.select("#word-cloud")
            .append("svg")
            .attr("width", layout.size()[0])
            .attr("height", layout.size()[1])
            .append("g")
            .attr("transform", "translate(" + layout.size()[0] / 2 + "," + layout.size()[1] / 2 + ")")
            .selectAll("text")
            .data(words)
            .enter()
            .append("text")
            .style("font-size", d => d.size)
            .style("fill", () => d3.schemeCategory10[Math.floor(Math.random() * d3.schemeCategory10.length)])
            .attr("text-anchor", "middle")
            .attr("transform", d => "translate(" + [d.x, d.y] + ")rotate(" + d.rotate + ")")
            .text(d => d.text);
      }
    },
  },
};
</script>

<style>

</style>