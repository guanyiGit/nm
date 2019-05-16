package com.msgmanagement.controller;

import com.entities.Msg;
import com.msgmanagement.service.MsgService;
import com.utils.PageUtils;
import com.utils.Query;
import com.utils.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-10-06 10:06:04
 */
 
@Controller
@RequestMapping("/system/msg")
public class MsgController {
	@Autowired
	private MsgService msgService;
	
	@GetMapping()
	@RequiresPermissions("system:msg:msg")
	String Msg(){
	    return "system/msg/msg";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("system:msg:msg")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<Msg> msgList = msgService.list(query);
		int total = msgService.count(query);
		PageUtils pageUtils = new PageUtils(msgList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("system:msg:add")
	String add(){
	    return "system/msg/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("system:msg:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		Msg msg = msgService.get(id);
		model.addAttribute("msg", msg);
	    return "system/msg/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("system:msg:add")
	public R save(Msg msg){
		if(msgService.save(msg)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("system:msg:edit")
	public R update( Msg msg){
		msgService.update(msg);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("system:msg:remove")
	public R remove( Integer id){
		if(msgService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("system:msg:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		msgService.batchRemove(ids);
		return R.ok();
	}
	
}
