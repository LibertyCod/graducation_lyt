package com.graduation.project.model;

import javax.persistence.*;

public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String uuid;

    @Column(name = "room_name")
    private String roomName;

    @Column(name = "room_num")
    private Byte roomNum;

    @Column(name = "room_price")
    private Double roomPrice;

    @Column(name = "room_total_price")
    private Double roomTotalPrice;

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
     * @return uuid
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * @param uuid
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * @return room_name
     */
    public String getRoomName() {
        return roomName;
    }

    /**
     * @param roomName
     */
    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    /**
     * @return room_num
     */
    public Byte getRoomNum() {
        return roomNum;
    }

    /**
     * @param roomNum
     */
    public void setRoomNum(Byte roomNum) {
        this.roomNum = roomNum;
    }

    /**
     * @return room_price
     */
    public Double getRoomPrice() {
        return roomPrice;
    }

    /**
     * @param roomPrice
     */
    public void setRoomPrice(Double roomPrice) {
        this.roomPrice = roomPrice;
    }

    /**
     * @return room_total_price
     */
    public Double getRoomTotalPrice() {
        return roomTotalPrice;
    }

    /**
     * @param roomTotalPrice
     */
    public void setRoomTotalPrice(Double roomTotalPrice) {
        this.roomTotalPrice = roomTotalPrice;
    }
}