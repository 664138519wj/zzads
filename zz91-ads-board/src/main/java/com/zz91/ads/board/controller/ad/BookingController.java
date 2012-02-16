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
	
	@RequestMapping
	public void index(Map<String, Object> model){
		
	}
	
	@RequestMapping
	public ModelAndView query(HttpServletRequest request, Map<String, Object> out,
			Pager<AdBooking> page,AdBooking booking){
		
		page = adBookingService.pageBooking(booking, page);
		return printJson(page, out);
	}
}
