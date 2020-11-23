package com.atguigu.crowd.mvc.handler;

import com.atguigu.crowd.entity.Admin;
import com.atguigu.crowd.entity.ParamData;
import com.atguigu.crowd.entity.Student;
import com.atguigu.crowd.service.api.AdminService;
import com.atguigu.crowd.util.CrowdUtil;
import com.atguigu.crowd.util.ResultEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class TestController {

    @Autowired
    private AdminService adminService;

    private Logger logger = LoggerFactory.getLogger(TestController.class);

    @RequestMapping("/test/ssm.html")
    public String testSsm(ModelMap modelMap, HttpServletRequest request) {

        boolean judeResult = CrowdUtil.judgeRequestType(request);

        logger.info("judeResult=" + judeResult);

        List<Admin> adminList = adminService.getAll();

        modelMap.addAttribute("adminList", adminList);

        System.out.println(10 / 0);

        return "target";
    }

    @RequestMapping("/send/array/one.html")
    @ResponseBody
    public String testReceiveArrayOne(@RequestParam("array[]") List<Integer> array) {
        for (Integer number : array) {
            System.out.println("number" + number);
        }
        return "success";
    }

    @RequestMapping("/send/array/two.html")
    @ResponseBody
    public String testReceiveArrayTwo(ParamData paramData) {

        List<Integer> array = paramData.getArray();

        for (Integer number : array) {
            System.out.println("number" + number);
        }

        return "success";
    }

    @RequestMapping("/send/array/three.html")
    @ResponseBody
    public String testReceiveArrayThree(@RequestBody List<Integer> array) {

        for (Integer number : array) {
            logger.info("number" + number);
        }
        return "success";
    }

    @RequestMapping("/send/array/object.html")
    @ResponseBody
    public ResultEntity<Student> testReceiveComposeObject(@RequestBody Student student){

        logger.info(student.toString());

        ResultEntity<Student> resultEntity = ResultEntity.successWithData(student);

        return resultEntity;
    }
}
