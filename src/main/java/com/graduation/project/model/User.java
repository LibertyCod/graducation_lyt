package com.graduation.project.model;

import java.util.Date;
import javax.persistence.*;

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 关联外键
     */
    private String uuid;

    /**
     * 邮箱，登陆使用
     */
    private String email;

    /**
     * 密码
     */
    private String password;

    /**
     * 手机号
     */
    private String cellphone;

    /**
     * 身份证
     */
    @Column(name = "identity_card")
    private String identityCard;

    /**
     * 住址
     */
    private String address;

    /**
     * 创建时间
     */
    @Column(name = "create_datetime")
    private Date createDatetime;

    /**
     * 修改时间
     */
    @Column(name = "update_datetime")
    private Date updateDatetime;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取关联外键
     *
     * @return uuid - 关联外键
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * 设置关联外键
     *
     * @param uuid 关联外键
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * 获取邮箱，登陆使用
     *
     * @return email - 邮箱，登陆使用
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置邮箱，登陆使用
     *
     * @param email 邮箱，登陆使用
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取手机号
     *
     * @return cellphone - 手机号
     */
    public String getCellphone() {
        return cellphone;
    }

    /**
     * 设置手机号
     *
     * @param cellphone 手机号
     */
    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    /**
     * 获取身份证
     *
     * @return identity_card - 身份证
     */
    public String getIdentityCard() {
        return identityCard;
    }

    /**
     * 设置身份证
     *
     * @param identityCard 身份证
     */
    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    /**
     * 获取住址
     *
     * @return address - 住址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置住址
     *
     * @param address 住址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取创建时间
     *
     * @return create_datetime - 创建时间
     */
    public Date getCreateDatetime() {
        return createDatetime;
    }

    /**
     * 设置创建时间
     *
     * @param createDatetime 创建时间
     */
    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    /**
     * 获取修改时间
     *
     * @return update_datetime - 修改时间
     */
    public Date getUpdateDatetime() {
        return updateDatetime;
    }

    /**
     * 设置修改时间
     *
     * @param updateDatetime 修改时间
     */
    public void setUpdateDatetime(Date updateDatetime) {
        this.updateDatetime = updateDatetime;
    }
}