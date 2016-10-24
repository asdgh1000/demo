package com.netease.seckill.controller;

import com.netease.seckill.dto.Exposer;
import com.netease.seckill.dto.SeckillExcution;
import com.netease.seckill.dto.SeckillResult;
import com.netease.seckill.entity.Seckill;
import com.netease.seckill.enums.SeckillStatusEnum;
import com.netease.seckill.exception.RepeatKillException;
import com.netease.seckill.exception.SeckillCloseException;
import com.netease.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by Jo on 10/19/16.
 */
@Controller
@RequestMapping("/seckill")  //module
public class SeckillController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SeckillService seckillService;

	/**
	 * get all seckill list
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list",method = RequestMethod.GET)
	public String list(Model model){
		//list.jsp + model = ModelAndView
		List<Seckill> seckills = seckillService.getSeckillList();
		model.addAttribute("seckills", seckills);
		return "list";  //WEB-INF/jsp/list.jsp    look at spring-web.xml
	}

	/**
	 * get seckill by seckillId
	 * @param seckillId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/{seckillId}/detail",method = RequestMethod.GET)
	public String detail(@PathVariable("seckillId") Long seckillId,Model model){
		if(seckillId == null){
			return "redirect:/seckill/list";
		}
		Seckill seckill = seckillService.getById(seckillId);
		if(null == seckill){
			return "forward:/seckill/list";
		}
		model.addAttribute("seckill",seckill);
		return "detail";
	}

	//ajax return json
	@RequestMapping(value = "/{seckillId}/exposer",method = RequestMethod.POST,
			produces = {"application/json;charset=UTF-8"})//http context
	@ResponseBody  //packging return result to json
	public SeckillResult<Exposer> exposer(@PathVariable Long seckillId){
		SeckillResult<Exposer> result;
		try{
			Exposer exposer = seckillService.exposeSeckillUrl(seckillId);
			result = new SeckillResult<Exposer>(true,exposer);
		}catch (Exception e){
			logger.error(e.getMessage());
			result = new SeckillResult<Exposer>(false,e.getMessage());
		}
		return result;
	}

	@RequestMapping(value = "/{seckillId}/{md5}/execution",method = RequestMethod.POST,
			produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public SeckillResult<SeckillExcution> execute(@PathVariable("seckillId") Long seckillId,@PathVariable("md5") String md5,
			@CookieValue(value = "killPhone",required = false) Long phone){
		if(phone == null){
			return new SeckillResult<SeckillExcution>(false,"do not login");
		}
		try{
			SeckillExcution seckillExcution = seckillService.executeSeckill(seckillId, phone, md5);
			return new SeckillResult<SeckillExcution>(true,seckillExcution);
		}catch (RepeatKillException e){
			SeckillExcution seckillExcution = new SeckillExcution(seckillId, SeckillStatusEnum.REPEAT_KILL);
			return new SeckillResult<SeckillExcution>(true,seckillExcution);
		}catch (SeckillCloseException e){
			SeckillExcution seckillExcution = new SeckillExcution(seckillId,SeckillStatusEnum.END);
			return new SeckillResult<SeckillExcution>(true,seckillExcution);
		}
		catch (Exception e){
			logger.error(e.getMessage());
			SeckillExcution seckillExcution = new SeckillExcution(seckillId,SeckillStatusEnum.INNER_ERROR);
			return new SeckillResult<SeckillExcution>(true,seckillExcution);
		}
	}

	@RequestMapping(value = "/time/now",method = RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public SeckillResult<Long> time(){
		Date date = new Date();
		return  new SeckillResult<Long>(true,date.getTime());
	}
}
