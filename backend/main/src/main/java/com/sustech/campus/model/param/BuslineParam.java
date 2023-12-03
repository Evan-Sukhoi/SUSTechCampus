package com.sustech.campus.model.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class BuslineParam {
    @ApiModelProperty(value = "线路名称", required = true, example = "busline1")
    @NotNull
    String name;

    @ApiModelProperty(value = "线路内容", required = true)
    List<BusStop> list;


    static class BusStop {

        @ApiModelProperty(value = "两个站点名称", required = true, example = "[\"站点1\",\"站点2\"]")
        public List<String> two;

        @ApiModelProperty(value = "两个站点坐标", required = true, example = "[[1.0,2.0],[3.0,4.0]]")
        public List<Double[]> point;


    }

}
