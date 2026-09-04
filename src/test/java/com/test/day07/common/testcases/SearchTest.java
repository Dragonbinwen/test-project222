package com.test.day07.common.testcases;

import com.test.day07.common.common.BaseTest;
import com.test.day07.common.pojo.CaseData;
import com.test.day07.common.util.ExcelUtil;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

//搜索商品测试
public class SearchTest extends BaseTest {

    //数据提供者 - 会由代码读取Excel中的数据返回
    @DataProvider
    public Object[] getDatas() {
        return ExcelUtil.readExcel("src/test/resources/casedata.xlsx","搜索商品").toArray();
    }

    @Test(dataProvider = "getDatas")
    public void test_search(CaseData caseData){
        request(caseData);
    }

}
