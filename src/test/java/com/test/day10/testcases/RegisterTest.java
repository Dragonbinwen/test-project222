package com.test.day10.testcases;

import com.test.day10.common.BaseTest;
import com.test.day10.common.Environment;
import com.test.day10.pojo.CaseData;
import com.test.day10.util.ExcelUtil;
import com.test.day10.util.RandomDataUtil;
import org.testng.annotations.*;

//注册测试
public class RegisterTest extends BaseTest {

    //数据提供者 - 会由代码读取Excel中的数据返回
    @DataProvider
    public Object[] getDatas() {
        return ExcelUtil.readExcel("src/test/resources/casedata.xlsx","注册").toArray();
    }

    @BeforeClass
    public void setup(){
        //生成未注册过的手机号及用户名
        String phone = RandomDataUtil.getUnregisterPhone();
        String username = RandomDataUtil.getUnregisterUsername();
        //存储到环境变量中
        Environment.env.put("phone",phone);
        Environment.env.put("username",username);
    }

    @Test(dataProvider = "getDatas")
    public void test_register_success(CaseData caseData) {
        request(caseData);
    }
}
