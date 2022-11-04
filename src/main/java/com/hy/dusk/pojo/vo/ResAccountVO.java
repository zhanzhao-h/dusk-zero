package com.hy.dusk.pojo.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("用户数据res vo")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResAccountVO {

    private Long id;

    /**
     * 年龄  0
     */
    private Integer age;

    /**
     * 性别，默认 Default 0；, 男 Male 1；女 Female 2；未知 Unknown 99")
     */
    private Integer gender;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 用户头像的 Url 地址
     */
    private String avatarUrl;

    private String description;

    /**
     * 1 正常，-1 注销
     */
    private Integer userStatus;

    /**
     * 操作人id
     */
    private Long operatorId;

    /**
     * 创建时间 13位时间戳
     */
    private Long createTime;

    /**
     * 更新时间 13位时间戳
     */
    private Long updateTime;
}
