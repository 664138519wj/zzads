/**
 * Copyright 2011 ASTO.
 * All right reserved.
 * Created on 2011-4-18.
 */
package com.zz91.ads.board.controller.ad;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zz91.ads.board.controller.BaseController;
import com.zz91.ads.board.domain.ad.AdBooking;
import com.zz91.ads.board.dto.ExtResult;
import com.zz91.ads.board.dto.Pager;
import com.zz91.ads.board.service.ad.AdBookingService;

/**
 * @author Rolyer(rolyer.live@gmail.com)
 *
 */
@Controller
public class BookingController extends BaseController{
	
	@Resource
	private AdBookingService adBookingService;
	
//	final static int EXPIRE_DAY=2;
	
	@RequestMapping
	public void index(Map<String, Object> model){
		
	}
	
	@RequestMapping
	public ModelAndView query(HttpServletRequest request, Map<String, Object> out,
			Pager<AdBooking> page,AdBooking booking){
		//计算期限
//		if(booking.getGmtBooking()==null){
//		}
//		booking.setGmtBooking(DateUtil.getDateAfterDays(new Date(), -EXPIRE_DAY));
		
		if(booking.getPositionId()!=null && booking.getPositionId()==0){
			booking.setPositionId(null);
		}
		
		page = adBookingService.pageBooking(booking, page);
		return printJson(page, out);
	}
	
	@RequestMapping
	public ModelAndView createBooking(HttpServletRequest request, Map<String, Object> out, AdBooking booking){
		
		
		ExtResult result=new ExtResult();
		if(!adBookingService.bookingEnable(booking.getPositionId(), booking.getKeywords())){
			return printJson(result, out);
		}
		booking.setAccount(getCachedUser(request).getAccount());
		Integer i=adBookingService.createBooking(booking);
		if(i!=null && i.intValue()>0){
			result.setSuccess(true);
		}
		return printJson(result, out);
	}
	
	@RequestMapping
	public ModelAndView deleteBooking(HttpServletRequest request, Map<String, Object> out, Integer id){
		
		Integer i=adBookingService.deleteBooking(id);
		ExtResult result=new ExtResult();
		if(i!=null && i.intValue()>0){
			result.setSuccess(true);
		}
		return printJson(result, out);
	}
}
