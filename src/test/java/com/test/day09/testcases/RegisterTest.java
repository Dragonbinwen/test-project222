package com.test.day09.testcases;

import com.test.day09.common.BaseTest;
import com.test.day09.pojo.CaseData;
import com.test.day09.util.ExcelUtil;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

//注册测试
public class RegisterTest extends BaseTest {

    //数据提供者 - 会由代码读取Excel中的数据返回
    @DataProvider
    public Object[] getDatas() {
        return ExcelUtil.readExcel("src/test/resources/casedata.xlsx","注册").toArray();
    }

    @Test(dataProvider = "getDatas")
    public void test_register_success(CaseData caseData) {
        //caseData会保存每一条用例的数据
        request(caseData);
        //TODO 完成接口用例断言设计
    }
}
