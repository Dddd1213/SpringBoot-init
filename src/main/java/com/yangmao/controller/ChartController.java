package com.yangmao.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yangmao.common.enumeration.ErrorCode;
import com.yangmao.common.exception.BusinessException;
import com.yangmao.common.result.Result;
import com.yangmao.common.utils.ResultUtils;
import com.yangmao.pojo.DTO.chart.AddChartDTO;
import com.yangmao.pojo.DTO.chart.QueryChartDTO;
import com.yangmao.pojo.DTO.common.DeleteDTO;
import com.yangmao.pojo.VO.chart.AddChartVO;
import com.yangmao.pojo.entity.Chart;
import com.yangmao.service.ChartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/chart")
@Slf4j
public class ChartController {

    @Autowired
    ChartService chartService;

    @PostMapping("/add")
    public Result<AddChartVO> addChart(@RequestBody AddChartDTO addChartDTO, HttpServletRequest request){
        if(addChartDTO==null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        if(request==null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        AddChartVO addChartVO = chartService.addChart(addChartDTO,request);

        return ResultUtils.success(addChartVO);
    }

    @PostMapping("/delete")
    public Result<Boolean> deleteChart(@RequestBody DeleteDTO deleteDTO, HttpServletRequest request){

        if(deleteDTO==null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        if(request==null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Boolean result = chartService.delete(deleteDTO,request);

        return ResultUtils.success(result);
    }

    /**
     * 分页获取当前用户创建的资源列表
     *
     * @param queryChartDTO
     * @param request
     * @return
     */
    @PostMapping("/my/list/page")
    public Result<Page<Chart>> listMyChartByPage(@RequestBody QueryChartDTO queryChartDTO,
                                                 HttpServletRequest request) {
        if (queryChartDTO == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        long current = queryChartDTO.getCurrent();
        long size = queryChartDTO.getPageSize();

        Page<Chart> chartPage = chartService.listMyChartPage(current,size,request);

        return ResultUtils.success(chartPage);
    }


}
