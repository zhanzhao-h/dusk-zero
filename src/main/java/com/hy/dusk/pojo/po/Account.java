package com.hy.dusk.pojo.po;


import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "account")
public class Account {
    @Id
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
    @Column(name = "real_name")
    private String realName;

    /**
     * 用户头像的 Url 地址
     */
    @Column(name = "avatar_url")
    private String avatarUrl;

    private String description;

    /**
     * 1 正常，-1 注销
     */
    @Column(name = "user_status")
    private Integer userStatus;

    /**
     * 操作人id
     */
    @Column(name = "operator_id")
    private Long operatorId;

    /**
     * 创建时间 13位时间戳
     */
    @Column(name = "create_time")
    private Long createTime;

    /**
     * 更新时间 13位时间戳
     */
    @Column(name = "update_time")
    private Long updateTime;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取年龄  0
     *
     * @return age - 年龄  0
     */
    public Integer getAge() {
        return age;
    }

    /**
     * 设置年龄  0
     *
     * @param age 年龄  0
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * 获取性别，默认 Default 0；, 男 Male 1；女 Female 2；未知 Unknown 99")
     *
     * @return gender - 性别，默认 Default 0；, 男 Male 1；女 Female 2；未知 Unknown 99")
     */
    public Integer getGender() {
        return gender;
    }

    /**
     * 设置性别，默认 Default 0；, 男 Male 1；女 Female 2；未知 Unknown 99")
     *
     * @param gender 性别，默认 Default 0；, 男 Male 1；女 Female 2；未知 Unknown 99")
     */
    public void setGender(Integer gender) {
        this.gender = gender;
    }

    /**
     * 获取昵称
     *
     * @return nickname - 昵称
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * 设置昵称
     *
     * @param nickname 昵称
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * 获取真实姓名
     *
     * @return real_name - 真实姓名
     */
    public String getRealName() {
        return realName;
    }

    /**
     * 设置真实姓名
     *
     * @param realName 真实姓名
     */
    public void setRealName(String realName) {
        this.realName = realName;
    }

    /**
     * 获取用户头像的 Url 地址
     *
     * @return avatar_url - 用户头像的 Url 地址
     */
    public String getAvatarUrl() {
        return avatarUrl;
    }

    /**
     * 设置用户头像的 Url 地址
     *
     * @param avatarUrl 用户头像的 Url 地址
     */
    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    /**
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取1 正常，-1 注销
     *
     * @return user_status - 1 正常，-1 注销
     */
    public Integer getUserStatus() {
        return userStatus;
    }

    /**
     * 设置1 正常，-1 注销
     *
     * @param userStatus 1 正常，-1 注销
     */
    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    /**
     * 获取操作人id
     *
     * @return operator_id - 操作人id
     */
    public Long getOperatorId() {
        return operatorId;
    }

    /**
     * 设置操作人id
     *
     * @param operatorId 操作人id
     */
    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }

    /**
     * 获取创建时间 13位时间戳
     *
     * @return create_time - 创建时间 13位时间戳
     */
    public Long getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间 13位时间戳
     *
     * @param createTime 创建时间 13位时间戳
     */
    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新时间 13位时间戳
     *
     * @return update_time - 更新时间 13位时间戳
     */
    public Long getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间 13位时间戳
     *
     * @param updateTime 更新时间 13位时间戳
     */
    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }
}