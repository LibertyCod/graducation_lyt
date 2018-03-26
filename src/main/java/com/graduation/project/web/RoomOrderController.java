package com.graduation.project.web;
import com.graduation.project.core.ApiResponse;
import com.graduation.project.model.RoomOrder;
import com.graduation.project.service.RoomOrderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
* Created by CodeGenerator on 2018/03/23.
*/
@RestController
@RequestMapping("/room/order")
public class RoomOrderController {
    @Resource
    private RoomOrderService roomOrderService;

    @PostMapping("/add")
    public ApiResponse add(RoomOrder roomOrder) {
        roomOrderService.save(roomOrder);
        return ApiResponse.getSuccResponse("下单成功");
    }

    @PostMapping("/delete")
    public ApiResponse delete(@RequestParam Integer id) {
        roomOrderService.deleteById(id);
        return ApiResponse.getSuccResponse("订单删除成功");
    }

    @PostMapping("/update")
    public ApiResponse update(RoomOrder roomOrder) {
        roomOrderService.update(roomOrder);
        return ApiResponse.getSuccResponse("订单更新成功");
    }

    @PostMapping("/detail")
    public ApiResponse detail(@RequestParam Integer id) {
        RoomOrder roomOrder = roomOrderService.findById(id);
        return ApiResponse.getSuccResponse(roomOrder);
    }

    @PostMapping("/list")
    public ApiResponse list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<RoomOrder> list = roomOrderService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ApiResponse.getSuccResponse(pageInfo);
    }
}
