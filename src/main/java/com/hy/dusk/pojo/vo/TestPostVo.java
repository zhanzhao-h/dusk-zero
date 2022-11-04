package com.hy.dusk.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestPostVo {
    private Integer id;
    private String name;
    private HashMap<String, Object> testMap;
}