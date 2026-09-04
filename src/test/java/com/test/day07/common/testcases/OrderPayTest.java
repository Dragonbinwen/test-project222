package com.test.day07.common.testcases;

import com.test.day07.common.common.BaseTest;
import com.test.day07.common.pojo.CaseData;
import com.test.day07.common.util.ExcelUtil;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * 下单支付测试
 */
public class OrderPayTest extends BaseTest {
    //数据提供者 - 会由代码读取Excel中的数据返回
    @DataProvider
    public Object[] getDatas() {
        return ExcelUtil.readExcel("src/test/resources/casedata.xlsx","下单支付").toArray();
    }

    @Test(dataProvider = "getDatas")
    public void test_order_pay(CaseData caseData) {
        //caseData会保存每一条用例的数据
        request(caseData);
    }
}
