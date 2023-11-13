package com.yangmao.controller;

import com.yangmao.common.enumeration.ErrorCode;
import com.yangmao.common.exception.BusinessException;
import com.yangmao.common.result.Result;
import com.yangmao.common.utils.ResultUtils;
import com.yangmao.pojo.DTO.user.UserLoginDTO;
import com.yangmao.pojo.DTO.user.UserRegisterDTO;
import com.yangmao.pojo.VO.user.UserLoginVO;
import com.yangmao.pojo.VO.user.UserRegisterVO;
import com.yangmao.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;


    /**
     *用户注册
     * @param userRegisterDTO
     * @return
     */
    @PostMapping("/register")
    public Result<UserRegisterVO> userRegister(@RequestBody UserRegisterDTO userRegisterDTO){

        if (userRegisterDTO == null ){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        String userAccount = userRegisterDTO.getUserAccount();
        String userPassword = userRegisterDTO.getUserPassword();
        String checkPassword = userRegisterDTO.getCheckPassword();
        if (StringUtils.isAnyBlank(userAccount, userPassword, checkPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        UserRegisterVO userRegisterVO = userService.register(userAccount,userPassword,checkPassword);

        return ResultUtils.success(userRegisterVO);
    }

    /**
     * 用户登录
     * @param userLoginDTO
     * @param request
     * @return
     */
    @PostMapping("/login")
    public Result<UserLoginVO> userLogin(@RequestBody UserLoginDTO userLoginDTO, HttpServletRequest request){

        String userAccount = userLoginDTO.getUserAccount();
        String userPassword = userLoginDTO.getUserPassword();

        if(StringUtils.isAnyBlank(userAccount,userPassword))
        {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        UserLoginVO userLoginVO = userService.userLogin(userAccount, userPassword, request);

        return ResultUtils.success(userLoginVO);
    }

    /**
     * 用户登出
     * @param request
     * @return
     */
    @PostMapping("/logout")
    public Result<Boolean> userLogout(HttpServletRequest request){

        if(request==null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Boolean result = userService.userLogout(request);

        return ResultUtils.success(result);
    }

    /**
     * 获取当前登录用户
     * @param request
     * @return
     */
    @GetMapping("/get/login")
    public Result<UserLoginVO> getLoginUser(HttpServletRequest request){
        if(request==null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        UserLoginVO userLoginVO = userService.getLoginUser(request);
        return ResultUtils.success(userLoginVO);
    }

//    /**
//     * 分页获取当前用户创建的资源列表
//     * @param chartQueryRequest
//     * @param request
//     * @return
//     */
//    @PostMapping("/my/list/page")
//    public Result<Page<Chart>> listMyChartByPage(@RequestBody ChartQueryRequest chartQueryRequest,
//                                                 HttpServletRequest request)



}
