package com.iexiao.pnsp.base.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iexiao.pnsp.annotation.OpLog;
import com.iexiao.pnsp.base.BaseController;
import com.iexiao.pnsp.base.dto.PnspImageTDTO;
import com.iexiao.pnsp.base.query.PnspImageTQuery;
import com.iexiao.pnsp.base.service.PnspImageTService;
import com.iexiao.pnsp.utils.RestResponse;

@Controller
@RequestMapping("/pnspImageT")
public class PnspImageTController extends BaseController{

	private static final Logger LOGGER = LoggerFactory.getLogger(PnspImageTController.class);
	
	@Autowired
	private PnspImageTService pnspImageTService;
	
	/**
	 * 根据图片资源分组主键获取图片资源
	 * @author lizhiyong
	 * @date 2018年9月9日
	 * @param dto
	 * @return
	 */
	@OpLog(opType = OpLog.QUERY, opDescr = "getImageListByGroupId", keyWord = "getImageListByGroupId")
	@RequestMapping("/getImageListByGroupId.do")
	@ResponseBody
	public RestResponse<Object> getImageListByGroupId(@RequestBody PnspImageTDTO dto){
		LOGGER.info("[/getImageListByGroupId.do]");
		PnspImageTQuery query = new PnspImageTQuery();
		BeanUtils.copyProperties(dto,query);
		List<PnspImageTQuery> selectListByPage = this.pnspImageTService.getImageListByGroupId(query);
		return RestResponse.success(selectListByPage);
	}
	
}
