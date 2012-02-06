/**
 * Copyright 2011 ASTO.
 * All right reserved.
 * Created on 2011-4-12.
 */
package com.zz91.ads.board.controller.ad;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zz91.ads.board.controller.BaseController;
import com.zz91.ads.board.domain.ad.Advertiser;
import com.zz91.ads.board.dto.ExtResult;
import com.zz91.ads.board.dto.Pager;
import com.zz91.ads.board.service.ad.AdvertiserService;
import com.zz91.util.lang.StringUtils;

/**
 * 广告主控制器
 * 
 * @author Rolyer(rolyer.live@gmail.com)
 */
@Controller
public class AdvertiserController extends BaseController {

	@Resource
	AdvertiserService advertiserService;

	/**
	 * 初始化页面
	 */
	@RequestMapping
	public void index() {

	}

	/**
	 * 读取列表
	 * 
	 * @param model
	 * @param advertiser
	 * @param delete
	 * @param pager
	 * @return
	 */
	@RequestMapping
	public ModelAndView query(Map<String, Object> model, Advertiser advertiser, Boolean delete,
			Pager<Advertiser> pager) {
		// 分页排序设置
		if (pager != null) {
			if (pager.getSort() == null) {
				pager.setSort("id");
			}
			if (pager.getDir() == null) {
				pager.setDir("DESC");
			}
		} else {
			pager = new Pager<Advertiser>();
			pager.setDir("DESC");
			pager.setSort("id");
		}
		// 读取数据
		pager = advertiserService.pageAdvertiserByConditions(advertiser, delete, pager);
		return printJson(pager, model);
	}

	/**
	 * 添加
	 * 
	 * @param model
	 * @param advertiser
	 * @return
	 */
	@RequestMapping
	public ModelAndView add(Map<String, Object> model, Advertiser advertiser) {
		ExtResult result = new ExtResult();

		if (advertiser != null) {
			Integer id = advertiserService.insertAdvertiser(advertiser);
			if (id != null && id.intValue() > 0) {
				result.setSuccess(true);
			}
		}
		return printJson(result, model);
	}

	/**
	 * 更新
	 * 
	 * @param model
	 * @param advertiser
	 * @return
	 */
	@RequestMapping
	public ModelAndView update(Map<String, Object> model, Advertiser advertiser) {
		ExtResult result = new ExtResult();

		if (advertiser != null) {
			Integer im = advertiserService.updateAdvertiser(advertiser);
			if (im != null && im.intValue() > 0) {
				result.setSuccess(true);
			}
		}
		return printJson(result, model);
	}

	/**
	 * 删除
	 * 
	 * @param model
	 * @param items
	 *            如：1,2,3,4
	 * @return
	 */
	@RequestMapping
	public ModelAndView delete(Map<String, Object> model, String items) {
		ExtResult result = new ExtResult();

		if (items != null) {
			String[] i = items.split(",");
			Integer im = 0;
			for (String s : i) {
				if (StringUtils.isNumber(s)
						&& advertiserService.signDeleted(Integer.parseInt(s)).intValue() > 0) {
					im++;
				}
			}
			if (im != null && im.intValue() == i.length) {
				result.setSuccess(true);
			}
		}
		return printJson(result, model);
	}

	/**
	 * 读取指定记录
	 * 
	 * @param model
	 * @param id
	 *            记录编号
	 * @return
	 */
	@RequestMapping
	public ModelAndView queryById(Map<String, Object> model, String id) {
		Pager<Advertiser> pager = new Pager<Advertiser>();
		if (StringUtils.isNumber(id)) {
			Advertiser advertiser = advertiserService.queryAdvertiserById(Integer.parseInt(id));
			if (advertiser != null && advertiser.getId().intValue() > 0) {
				List<Advertiser> list = new ArrayList<Advertiser>();
				list.add(advertiser);
				pager.setRecords(list);
			}
		}
		return printJson(pager, model);
	}
}
