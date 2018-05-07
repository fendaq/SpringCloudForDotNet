package top.gongtao.flowable;

import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: gongtao
 * @Date: Created in 2018/5/7 15:46
 * @Description:
 */

@RestController
public class ProcessController {

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private RuntimeService runtimeService;


    @PostMapping("/leave/form")
    public String LeaveForm(@RequestParam(value = "date[]") String[] date,@RequestParam("reason") String reason){


        // 获取版本最新的流程定义对象
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionKey("holidayRequests").latestVersion().singleResult();

        // 定义map集合存放流程参数
        Map<String,Object> variables = new HashMap<String, Object>();

        // 放入日期数据
        variables.put("startTime",date[0]);
        variables.put("endTime",date[1]);
        variables.put("reason",reason); // 请假理由
        variables.put("employee","mahang");

        // 启动带有表单定义的流程实例
        ProcessInstance pi = runtimeService.startProcessInstanceWithForm(processDefinition.getId(),null,variables,null);

        return pi.getId();
    }


}
