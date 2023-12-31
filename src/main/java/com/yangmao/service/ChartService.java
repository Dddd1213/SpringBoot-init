package com.yangmao.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yangmao.pojo.DTO.chart.AddChartDTO;
import com.yangmao.pojo.DTO.common.DeleteDTO;
import com.yangmao.pojo.VO.chart.AddChartVO;
import com.yangmao.pojo.entity.Chart;

import javax.servlet.http.HttpServletRequest;

/**
* @author 31067
* @description 针对表【chart(图表信息表)】的数据库操作Service
* @createDate 2023-11-08 16:08:43
*/
public interface ChartService extends IService<Chart>{

    AddChartVO addChart(AddChartDTO addChartDTO, HttpServletRequest request);

    Boolean delete(DeleteDTO deleteDTO, HttpServletRequest request);

    Page<Chart> listMyChartPage(long current, long size, HttpServletRequest request);
}
