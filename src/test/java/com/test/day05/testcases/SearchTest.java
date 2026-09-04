package com.test.day05.testcases;

import com.alibaba.excel.EasyExcel;
import com.test.day05.common.BaseTest;
import com.test.day05.pojo.CaseData;
import com.test.day05.util.ExcelUtil;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

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
