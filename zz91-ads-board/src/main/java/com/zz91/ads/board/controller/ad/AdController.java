/**
 * Copyright 2011 ASTO.
 * All right reserved.
 * Created on 2011-4-18.
 */
package com.zz91.ads.board.controller.ad;

import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zz91.ads.board.controller.BaseController;
import com.zz91.ads.board.domain.ad.Ad;
import com.zz91.ads.board.domain.ad.AdExactType;
import com.zz91.ads.board.domain.ad.ExactType;
import com.zz91.ads.board.dto.ExtResult;
import com.zz91.ads.board.dto.Pager;
import com.zz91.ads.board.dto.ad.AdDto;
import com.zz91.ads.board.dto.ad.AdSearchDto;
import com.zz91.ads.board.service.ad.AdService;
import com.zz91.ads.board.service.ad.ExactTypeService;
import com.zz91.ads.board.utils.AdConst;
import com.zz91.ads.board.utils.MvcUpload;
import com.zz91.util.datetime.DateUtil;
import com.zz91.util.lang.StringUtils;

/**
 * @author Rolyer(rolyer.live@gmail.com)
 *
 */
@Controller
public class AdController extends BaseController{
	
	@Resource
	private AdService adService;
	@Resource
	private ExactTypeService exactTypeService;
	
	
	@RequestMapping
	public void index(Map<String, Object> model){
		
	}
	
	@RequestMapping
	public ModelAndView apply(Map<String, Object> model){
		return null;
	}
	
	@RequestMapping
	public ModelAndView applyAd(HttpServletRequest request, Map<String, Object> out, Ad ad, String gmtStartStr, String gmtPlanEndStr){
		try {
			ad.setGmtStart(DateUtil.getDate(gmtStartStr, AdConst.FORMAT_DATE));
			if(StringUtils.isNotEmpty(gmtPlanEndStr)){
				ad.setGmtPlanEnd(DateUtil.getDate(gmtPlanEndStr, AdConst.FORMAT_DATE));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		ad.setApplicant(getCachedUser(request).getAccount());
		Integer i = adService.applyAd(ad);
		ExtResult result = new ExtResult();
		if(i!=null && i.intValue()>0){
			result.setSuccess(true);
			result.setData(i);
		}
		return printJson(result, out);
	}
	
	@RequestMapping
	public ModelAndView queryAdExact(HttpServletRequest request, Map<String, Object> out, Integer aid){
		return printJson(adService.queryAdExact(aid), out);
	}
	
	@RequestMapping
	public ModelAndView queryExactTypeOfAd(HttpServletRequest request, Map<String, Object> out, Integer aid){
		Integer pid=adService.queryPositionOfAd(aid);
		List<ExactType> list=exactTypeService.queryExactTypeByAdPositionId(pid);
		return printJson(list, out);
	}
	
	@RequestMapping
	public ModelAndView createAdExact(HttpServletRequest request, Map<String, Object> out, AdExactType adexact){
		adexact.setAdPositionId(adService.queryPositionOfAd(adexact.getAdId()));
		Integer i=adService.insertAdExactType(adexact);
		ExtResult result = new ExtResult();
		if(i!=null && i.intValue()>0){
			result.setSuccess(true);
			result.setData(i);
		}
		return printJson(result, out);
	}
	
	@RequestMapping
	public ModelAndView deleteAdExact(HttpServletRequest request, Map<String, Object> out, Integer id){
		Integer i=adService.deleteAdExactTypeById(id);
		ExtResult result = new ExtResult();
		if(i!=null && i.intValue()>0){
			result.setSuccess(true);
			result.setData(i);
		}
		return printJson(result, out);
	}
	
	final static String UPLOAD_ROOT = "/usr/data/resources/ads";
	
	@RequestMapping
	public ModelAndView upload(HttpServletRequest request,
			Map<String, Object> out) {
		ExtResult result = new ExtResult();
		Long monthFolder=DateUtil.getMillis(DateUtil.getNowMonthFirstDay());
		String uploadedFile = MvcUpload.localUpload(request, UPLOAD_ROOT+"/"+monthFolder, UUID.randomUUID().toString());
		if (StringUtils.isNotEmpty(uploadedFile)) {
			result.setSuccess(true);
			result.setData("/"+monthFolder+"/"+uploadedFile);
		}
		return printJson(result, out);
	}
	
	@RequestMapping
	public ModelAndView query(HttpServletRequest request, Map<String, Object> out,
			Pager<AdDto> page, Ad ad, AdSearchDto adSearch){
		
		page = adService.pageAdByConditions(ad, adSearch, page);
		return printJson(page, out);
	}
}
