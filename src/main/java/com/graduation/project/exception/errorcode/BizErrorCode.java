package com.graduation.project.exception.errorcode;


/**
 * 业务异常代码<br>
 * 错误码命名规则:5【系统编号(0-9)】【模块编号(0-9)】【错误码(0-9)】【错误码(0-9)】<br>
 *
 * @author leejianhao
 */
public class BizErrorCode extends SysErrorCode {
    public static final int FFF = 10001;

    public static final int PERMISSION_DENIED = 40003;

    public static final int USER_HAS_EXISTED = 50001;

    public static final int USER_HAD_NOT_LOGINED = 50002;

}
