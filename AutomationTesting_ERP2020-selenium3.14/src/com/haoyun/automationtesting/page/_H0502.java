package com.haoyun.automationtesting.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.haoyun.automationtesting.framework.action;
import com.haoyun.automationtesting.framework.log;
import org.openqa.selenium.WebElement;

/***
 * @功能模块:ERP 下单管理/产品销售下单管理
 * @作用:页面元素定位,包括属性数据及方法
 * @author admin
 *
 */

public class _H0502 extends action {

    public _H0502() {
        super();
        // TODO 自动生成的构造函数存根
    }

    public _H0502(WebDriver driver) {
        super(driver);
    }

    /**
     * 点击进入方案产品销售下单页面
     */
    public static void H0502_enterMenu() throws Exception{
        PM.clickMenu("下单管理");
        PM.clickMenu("产品销售下单管理");
        PM.clickMenu("方案产品销售下单");
    }

    /**
     * 新建方案产品销售下单
     * @param GLXDXM 关联下单项目
     * @param KPDW 开票单位
     * @param GCSMC 工程商名称
     * @param GCNR 工程内容
     * @param YWFZR 业务负责人
     * @param WDMC 网点名称
     * @param XXSQDH 线下售前单号
     */
    public static void H0502_XJ(String GLXDXM, String KPDW, String GCSMC, String GCNR, String YWFZR, String WDMC, String XXSQDH) throws Exception{
        PM.normalize_texts_clicks("新建");
        PM.search_select("关联下单项目*",GLXDXM);
        PM.option_select("浩云科技股份有限公司（销售票）");
        PM.input_sendKeys("工程商名称",GCSMC);
        PM.multiple_select("工程内容",GCNR);
//        action.JS_EXE("document.querySelector('.selectList').style.display = 'none'");
        action.sleep(1);
//      PM.search_select("业务负责人","唐展宏");
        PM.input_sendKeys("网点名称",WDMC);
        PM.input_sendKeys("线下售前单号",XXSQDH);
        action.sleep(2);
        //JS点击表格确定按钮
        PM.click_form_QD();
    }

    //public static void H0502_XD(String)
}