//package com.test.day05.testcases;
//
//import com.alibaba.excel.EasyExcel;
//import com.test.day05.common.BaseTest;
//import com.test.day05.pojo.CaseData;
//import com.test.day05.util.ExcelUtil;
//import org.testng.annotations.DataProvider;
//import org.testng.annotations.Test;
//
//import java.util.List;
//
//import static io.restassured.RestAssured.given;
//
////登录测试
//public class LoginTest extends BaseTest {
//
//    //数据提供者 - 会由代码读取Excel中的数据返回
//    @DataProvider
//    public Object[] getDatas() {
//        return ExcelUtil.readExcel("src/test/resources/casedata.xlsx","登录模块").toArray();
//    }
//
//    @Test(dataProvider = "getDatas")
//    public void test_login_success(CaseData caseData) {
//        //caseData会保存每一条用例的数据
//        request(caseData);
//        //TODO 完成接口用例断言设计
//
//    }
//}

package com.test.day05.testcases;

import com.alibaba.excel.EasyExcel;
import com.test.day05.common.BaseTest;
import com.test.day05.pojo.CaseData;
import com.test.day05.util.ExcelUtil;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

//登录测试
public class LoginTest extends BaseTest {

    //数据提供者 - 会由代码读取Excel中的数据返回
    @DataProvider
    public Object[] getDatas() {
        return ExcelUtil.readExcel("src/test/resources/casedata.xlsx","登录模块").toArray();
    }

    @Test(dataProvider = "getDatas")
    public void test_login_success(CaseData caseData) {
        //1. 发送请求
        Response response = request(caseData);

        //2. ===================== 断言开始 =====================
        // 获取期望结果（从Excel里的 expected 字段）
        String expected = caseData.getExpected();

        // 断言1：响应状态码 200
        response.then().statusCode(200);

        // 断言2：根据不同用例做响应体字段断言（通用版）
        if (caseData.getId() == 1) {
            // 用例1：登录成功 → 必须返回 access_token、nickName
            Assert.assertNotNull(response.path("access_token"), "登录成功未返回token");
            Assert.assertNotNull(response.path("nickName"), "登录成功未返回昵称");
            Assert.assertEquals(response.path("token_type"), "bearer", "token类型不正确");
        } else {
            // 其他用例：密码错误/账号为空 → 断言提示信息
            String msg = response.getBody().asString();
            Assert.assertTrue(msg.contains("账号或密码不正确") || msg.contains("不能为空"),
                    "失败用例提示信息不正确：" + msg);
        }

        // 断言3：如果Excel里写了预期结果，做整体包含断言
        if (expected != null && !expected.isEmpty()) {
            String body = response.asString();
            Assert.assertTrue(body.contains(expected),
                    "响应结果与预期不符\n预期：" + expected + "\n实际：" + body);
        }
        // ======================================================
    }
}