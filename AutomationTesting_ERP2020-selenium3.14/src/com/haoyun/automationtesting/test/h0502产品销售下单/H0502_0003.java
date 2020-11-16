package com.haoyun.automationtesting.test.h0502产品销售下单;

import com.haoyun.automationtesting.page.PM;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.haoyun.automationtesting.framework.ExcelOperate;
import com.haoyun.automationtesting.framework.TestCase;
import com.haoyun.automationtesting.framework.action;
import com.haoyun.automationtesting.framework.log;
import com.haoyun.automationtesting.page._H0502;

/***
 * @用例名称:方案产品销售下单/下单
 * @author
 */

public class H0502_0003 extends _H0502 implements TestCase {
    public H0502_0003() {
    }

    public H0502_0003(WebDriver driver) {
        super(driver);
    }


    @Override
    public void testcase() throws Exception {
        // TODO 自动生成的方法存根
        action.sleep(3);
        String sheetname = H0502_0003.class.getPackage().getName().split("\\.")[4];
        int index = ExcelOperate.getclassname_rows(this.getClass()
                .getSimpleName(), sheetname);// 获取该用例在excel中的行数


        try {//CaseStepStart
            log.logInfo("开始方案产品销售下单/下单");
            H0502_enterMenu();
            H0502_XD(PM.GLXDXM);
            action.assertReport(1, "方案产品销售下单/下单用例通过","方案产品销售下单/下单用例验证失败" , sheetname, index);
            PM.return_menu();
        } catch (Exception e) {
            log.logError("方案产品销售下单/下单用例验证失败");
            ExcelOperate.cellexcelerr(sheetname, index);
            e.printStackTrace();
        }
    }



}
