package com.graduation.project.service.impl;

import com.graduation.project.dao.RoomOrderMapper;
import com.graduation.project.exception.ServiceException;
import com.graduation.project.exception.errorcode.BizErrorCode;
import com.graduation.project.filter.SystemContext;
import com.graduation.project.model.RoomOrder;
import com.graduation.project.service.RoomOrderService;
import com.graduation.project.core.AbstractService;
import com.graduation.project.util.UUIDGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/03/23.
 */
@Service("roomOrderService")
@Transactional
public class RoomOrderServiceImpl extends AbstractService<RoomOrder> implements RoomOrderService {
    @Resource
    private RoomOrderMapper roomOrderMapper;

    @Override
    public void save(RoomOrder model) {
        if (StringUtils.isEmpty(SystemContext.getUserId()))
            throw new ServiceException(BizErrorCode.USER_HAD_NOT_LOGINED);
        model.setUuid(UUIDGenerator.uuid());
        super.save(model);
    }
}
