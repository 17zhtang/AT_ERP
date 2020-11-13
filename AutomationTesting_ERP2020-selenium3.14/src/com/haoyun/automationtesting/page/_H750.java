package com.haoyun.automationtesting.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.haoyun.automationtesting.framework.action;
import com.haoyun.automationtesting.framework.log;

/***
 * @功能模块:设备管理
 * @作用:页面元素定位,包括属性数据及方法
 * @author wll
 *
 */
public class _H750 extends action {

	public _H750() {
		super();
		// TODO 自动生成的构造函数存根
	}

	public _H750(WebDriver driver) {
		super(driver);

	}

	/* 下面为页面所有元素 */
	/* * 点击设备管理-定时计划 */
	public static void step1() throws Exception {
		PM.return_sy();
		PM.menu_text("设备管理");
		PM.menu_text("定时计划");
	}

	/**
	 * 业务步骤 添加定时任务-单次定时/每周
	 * @例子 h750_DSRWTJ("自动化基础数据", "自动化343564单次定时计划名称", "单次定时", "此刻", "", "开", "是")
	 * 
	 * @param
	 *        ssjg
	 *            :所属机构
	 * @param dsjhmc
	 *            :单次定时计划名称定时计划名
	 * @param dslx
	 *            :定时类型
	 * @param zxsj
	 *            :执行时间
	 * @param cf
	 *          :重复           
	 * @param zt
	 *          :状态                                           
	 * @param sfqy
	 *            :是否启用
	 * @throws Exception
	 */
	public static void h750_DSRWTJ(String ssjg, String dsjhmc, String dslx, String zxsj, String cf, String zt, String sfqy)
			throws Exception {

		step1();

		PM.normalize_texts_clicks("添加定时任务");
		action.sleep(1);

		PM.normalize_inputs_clicks("所属机构");//所属机构
		//action.findElements_click(By.xpath("//*[normalize-space(text()) and normalize-space(.)='所属机构']/following::input[1]"), 0);
		PM.pop_click_textspan("所有机构");
		PM.normalize_texts_clicks(ssjg);//
		PM.normalize_texts_clicks("所属机构");//收起下拉框

		PM.normalize_inputs_sendkeys("定时计划名", dsjhmc);
		
		PM.normalize_inputs_clicks("定时类型");

		if (dslx.equals("单次定时")) {
			PM.normalize_texts_clicks("单次定时");
			PM.normalize_inputs_clicks("执行时间");
			action.sleep(1);
			PM.normalize_texts_clicks(zxsj);//"此刻"
		} else if (dslx.equals("每周")) {
			PM.normalize_texts_clicks("每周");
			PM.normalize_inputs_clicks("执行时间");
			action.sleep(1);
			PM.click_QD();
			PM.normalize_texts_clicks(cf);//"全选"
		}

		PM.normalize_inputs_clicks("状态");
		PM.normalize_texts_clicks(zt);//"开"

		PM.normalize_inputs_clicks("是否启用");
		PM.normalize_texts_clicks(sfqy);//"是"

		PM.click_QD();
		action.sleep(1);

		log.logInfo("添加定时任务信息,定时计划名称:" + dsjhmc);

	}
	
	/**
	 * 业务步骤 添加定时任务-单次定时重载
	 * @例子 h750_DSRWTJ("自动化343564单次定时计划名称")
	 * 
	 * @param dsjhmc
	 *            :单次定时计划名称定时计划名
	 * @throws Exception
	 */
	public static void h750_DSRWTJ(String dsjhmc)
			throws Exception {
		h750_DSRWTJ("自动化基础数据", dsjhmc, "单次定时", "此刻", "", "开", "是");
	}
	/**
	 * 业务步骤 定时计划-查询
	 * 
	 * @param jgmc
	 *            :机构名称
	 * @param dcdsjhmc
	 *            ：单次定时计划名称
	 * @throws Exception
	 */
	public static void h750_DSJHCX(String jgmc, String dcdsjhmc) throws Exception {

		step1();
        
		if(!jgmc.isEmpty()){
			
			action.findElements_click(By.xpath(".//*[@role='tree']//*[normalize-space(text()) and normalize-space(.)='"
					+ jgmc + "']"), 0);
			
		}
		if(!dcdsjhmc.isEmpty()){
			
			PM.normalize_inputs_sendkeys("定时计划名称", dcdsjhmc);
		}
		
		PM.click_CX();
		action.sleep(1);

		log.logInfo("查询单次定时任务信息,单次定时计划名称查询:" + dcdsjhmc);

	}

	/**
	 * 业务步骤 定时计划-重置
	 * @param  dcdsjhmc
	 *                 ：单次定时计划名称
	 * @throws Exception
	 */
	public static void h750_DSJHCZ(String dcdsjhmc) throws Exception {

		h750_DSJHCX("", dcdsjhmc);//先查询一个不存在的数据，再重置
		action.sleep(1);
		PM.click_CZ();

	}

	/**
	 * 业务步骤 定时计划-修改
	 * 
	 * @param dcdsjhmc
	 *            ：单次定时计划名称
	 * @param dcdsjhmcxg
	 *            : 单次定时计划名称修改
	 * @throws Exception
	 */
	public static void h750_DSJHXG(String dcdsjhmc, String dcdsjhmcxg)
			throws Exception {

		h750_DSJHCX("", dcdsjhmc);//先查询再修改
		action.sleep(1);

		PM.click_XG();
		PM.normalize_inputs_sendkeys("定时计划名", dcdsjhmcxg);
		PM.click_QD();
		action.sleep(1);

		log.logInfo("修改单次定时计划信息,定时计划名称:" + dcdsjhmcxg);

	}
	
	/**
	 * 业务步骤 定时计划-删除
	 * 
	 * @param dsjhmc
	 *            ：定时计划名称
	 *            
	 * @throws Exception
	 */
	public static void h750_DSJHSC(String dsjhmc) throws Exception {
		
		h750_DSJHCX("", dsjhmc);//先查询在删除
		action.sleep(1);
		
		PM.normalize_texts_clicks("删除任务");
		action.sleep(1);
		
		if (action.isTextPresent("确认删除选中记录吗？")) {
			PM.click_QD();//确定
		}
		action.sleep(1);
		
		log.logInfo("删除定时任务信息,定时任务删除");
			
	}
	

}
