package com.haoyun.automationtesting.test.h0406方案管理;

import com.haoyun.automationtesting.page._H0406;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.haoyun.automationtesting.framework.ExcelOperate;
import com.haoyun.automationtesting.framework.TestCase;
import com.haoyun.automationtesting.framework.action;
import com.haoyun.automationtesting.framework.log;
import com.haoyun.automationtesting.framework.mysql;
import com.haoyun.automationtesting.page.PM;

/***
 * @用例名称:方案管理-方案清单-新增
 * @author wangxiuchun
 */

public class H0406_0000 extends _H0406 implements TestCase {
    public H0406_0000() {
    }

    public H0406_0000(WebDriver driver) {
        super(driver);
    }

    @Override
    public void testcase() throws Exception {
        String sheetname = H0406_0000.class.getPackage().getName().split("\\.")[4];
        int index = ExcelOperate.getclassname_rows(this.getClass()
                .getSimpleName(), sheetname);// 获取该用例在excel中的行数
        try {

            String FAQDMC = "方案清单"+PM.jcsjmc;
            String sql1 = "SELECT name FROM erp_base_project where name='"+FAQDMC+"'";

            String sql = mysql.select(sql1);
            if (sql.equals(FAQDMC)) {
                log.logInfo("方案清单名称已存在=====");
            } else {

                h0406_XZFAQD(FAQDMC, "广发银行股份有限公司","广发银行股份有限公司肇庆分行","大楼","IP对讲系统","自动化方案清单备注");

            }



//			action.assertReport(XMMC, "用例验证成功===1", "用例验证失败====2", sheetname, index);

            //By.xpath 断言
//			String xp = "//*[@title='"+XMMC+"']";
            action.assertReport(By.xpath("//*[@title='"+FAQDMC+"']"), "通过", "未通过", sheetname,index);

            //sql断言
//			action.assertReport(sql1, XMMC, "用例验证成功", "用例验证失败", sheetname,index);
            // CaseStepEnd
        } catch (Exception e) {
            log.logError("h723_0001--用例验证失败");
            ExcelOperate.cellexcelerr(sheetname, index);
            e.printStackTrace();
        }
    }
}
