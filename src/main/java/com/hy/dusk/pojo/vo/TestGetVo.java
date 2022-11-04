package com.hy.dusk.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(" 测试 test get model")
public class TestGetVo {

    @ApiModelProperty("test get id")
    private Integer id;

    @ApiModelProperty("test get name")
    private String name;

    @ApiModelProperty("test get test vo2 model")
    private TestGetVo2 testVo;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @ApiModel(" 测试 test get model---vo2")
    public static class TestGetVo2 {

        @ApiModelProperty("test get id--vo2")
        private Integer id;

        @ApiModelProperty("test get name--vo2")
        private String name;

        @ApiModelProperty("test get age--vo2")
        private Integer age;

    }
}


