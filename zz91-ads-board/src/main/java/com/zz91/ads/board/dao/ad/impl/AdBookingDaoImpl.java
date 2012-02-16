/**
 * 
 */
package com.zz91.ads.board.dao.ad.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.zz91.ads.board.dao.BaseDaoSupport;
import com.zz91.ads.board.dao.ad.AdBookingDao;
import com.zz91.ads.board.domain.ad.AdBooking;
import com.zz91.ads.board.dto.Pager;

/**
 * @author root
 *
 */
@Component("adBookingDao")
public class AdBookingDaoImpl extends BaseDaoSupport implements AdBookingDao {
	
	final static String SQL_PREFIX = "adBooking";

	@SuppressWarnings("unchecked")
	@Override
	public List<AdBooking> queryBooking(AdBooking booking, Pager<AdBooking> page) {
		Map<String, Object> root=new HashMap<String, Object>();
		root.put("booking", booking);
		root.put("page", page);
		return getSqlMapClientTemplate().queryForList(addSqlKeyPreFix(SQL_PREFIX, "queryBooking"), root);
	}

	@Override
	public Integer queryBookingCount(AdBooking booking) {
		Map<String, Object> root=new HashMap<String, Object>();
		root.put("booking", booking);
		return (Integer) getSqlMapClientTemplate().queryForObject(addSqlKeyPreFix(SQL_PREFIX, "queryBookingCount"), root);
	}

}
