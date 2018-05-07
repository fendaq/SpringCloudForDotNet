package top.gongtao.flowable;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
    public String LeaveForm(@RequestBody ObjectNode payload) throws ParseException {


        // 获取版本最新的流程定义对象
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionKey("holidayRequests").latestVersion().singleResult();

        // 定义map集合存放流程参数
        Map<String,Object> variables = new HashMap<String, Object>();

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");//注意格式化的表达式

        payload.get("date").get(0).asText().replace("Z", " UTC");

        Date startTime = format.parse(payload.get("date").get(0).asText().replace("Z", " UTC"));
        Date endTime = format.parse(payload.get("date").get(1).asText().replace("Z", " UTC"));

        // 放入日期数据
        variables.put("starttime",startTime.toString());
        variables.put("endtime",endTime.toString());
        variables.put("reason",payload.get("reason").asText()); // 请假理由
        variables.put("employee","mahang");

        // 启动带有表单定义的流程实例
        ProcessInstance pi = runtimeService.startProcessInstanceWithForm(processDefinition.getId(),null,variables,null);

        return pi.getId();
    }


}
