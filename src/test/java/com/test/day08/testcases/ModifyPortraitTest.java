package com.test.day08.testcases;

import com.test.day08.common.BaseTest;
import com.test.day08.pojo.CaseData;
import com.test.day08.util.ExcelUtil;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * 修改个人头像测试
 */
public class ModifyPortraitTest extends BaseTest {
    //数据提供者 - 会由代码读取Excel中的数据返回
    @DataProvider
    public Object[] getDatas() {
        return ExcelUtil.readExcel("src/test/resources/casedata.xlsx","修改个人头像").toArray();
    }

    @Test(dataProvider = "getDatas")
    public void test_modify_portrait(CaseData caseData) {
        //caseData会保存每一条用例的数据
        request(caseData);
    }
}
