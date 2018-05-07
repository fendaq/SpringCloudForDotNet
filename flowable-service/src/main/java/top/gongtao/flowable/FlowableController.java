package top.gongtao.flowable;

import org.flowable.engine.RuntimeService;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: gongtao
 * @Date: Created in 2018/4/25 14:13
 * @Description:
 */

@Controller
public class FlowableController {

    @Autowired
    RuntimeService runtimeService;

    @GetMapping("startp")
    public String startProcessWithForm(){

        Map<String,Object> variables = new HashMap<String, Object>();

        variables.put("startTime","2018-04-25");
        variables.put("endTime","2018-05-01");
        variables.put("reason","临时有事");

        // 启动带有表单定义的流程实例
        ProcessInstance pi = runtimeService.startProcessInstanceWithForm("holidayRequest:4:5025","hform",variables,"Holiday Request");

        return pi.getProcessDefinitionId();

    }

}
