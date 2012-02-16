package com.zz91.ads.board.service.ad.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.zz91.ads.board.dao.ad.AdBookingDao;
import com.zz91.ads.board.domain.ad.AdBooking;
import com.zz91.ads.board.dto.Pager;
import com.zz91.ads.board.service.ad.AdBookingService;

@Component("adBookingService")
public class AdBookingServiceImpl implements AdBookingService {

	@Resource
	private AdBookingDao adBookingDao;
	
	@Override
	public Pager<AdBooking> pageBooking(AdBooking booking, Pager<AdBooking> page) {
		page.setRecords(adBookingDao.queryBooking(booking, page));
		page.setTotals(adBookingDao.queryBookingCount(booking));
		return page;
	}

}
