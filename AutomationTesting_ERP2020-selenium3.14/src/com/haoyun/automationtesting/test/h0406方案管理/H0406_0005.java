package com.haoyun.automationtesting.test.h0406方案管理;

import com.haoyun.automationtesting.framework.*;
import com.haoyun.automationtesting.page.PM;
import com.haoyun.automationtesting.page._H0406;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/***
 * @用例名称:方案管理-方案清单-修改
 * @author wangxiuchun
 */

public class H0406_0005 extends _H0406 implements TestCase {
    public H0406_0005() {
    }

    public H0406_0005(WebDriver driver) {
        super(driver);
    }

    @Override
    public void testcase() throws Exception {
        String sheetname = H0406_0005.class.getPackage().getName().split("\\.")[4];
        int index = ExcelOperate.getclassname_rows(this.getClass()
                .getSimpleName(), sheetname);// 获取该用例在excel中的行数
        try {

            String FAQDMC = "方案清单"+PM.jcsjmc;
            String New_FAQDMC = "新方案清单"+PM.jcsjmc;
            String sql1 = "SELECT name FROM erp_base_project where name='"+New_FAQDMC+"'";

            String sql = mysql.select(sql1);
            if (sql.equals(FAQDMC)) {
                log.logInfo("方案名称已存在=====");
            } else {

                h0406_XGFAQD(FAQDMC, New_FAQDMC);

            }



//			action.assertReport(XMMC, "用例验证成功===1", "用例验证失败====2", sheetname, index);

            //By.xpath 断言
//			String xp = "//*[@title='"+XMMC+"']";
            action.assertReport(By.xpath("//*[@title='"+New_FAQDMC+"']"), "通过", "未通过", sheetname,index);

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
