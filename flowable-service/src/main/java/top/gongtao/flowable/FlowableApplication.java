package top.gongtao.flowable;

import org.flowable.engine.*;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@EnableEurekaClient
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class, org.flowable.spring.boot.SecurityAutoConfiguration.class
})
public class FlowableApplication  implements CommandLineRunner {

    /**
     * 在第一次调用时初始化并构建流程引擎，在之后的调用都将返回相同的流程引擎
     * 使用 ProcessEngine 可以获得包含工作流/BPMN方法的调用，
     * ProcessEngine 和 服务对象都是线程安全的，因此可以在整个服务器上保存一个引用。
     */
    @Autowired
    private ProcessEngine processEngine;

    /**
     * RuntimeServie 可以启动流程定义的实例，同一个时刻，流程可能有多个运行的实例，
     * 可以用于查询流程实例与执行，
     */
    @Autowired
    private RuntimeService runtimeService;

    /**
     * RepositoryService 提供了管理与控制部署与流程定义的操作。 流程定义是 BPMN2.0流程的 java等价版本，展现了流程的每一步的结构和行为。
     * 他提供的是想对静态的信息
     */
    @Autowired
    private RepositoryService repositoryService;

    /**
     * 对于 BPM 引擎，核心是需要人类用户操作的任务，所有任务相关的功能都组织在 TaskService 中。
     */
    @Autowired
    private TaskService taskService;

    /**
     * 它可以读取数据库表与原始数据表信息，也提供了对作业(job)的查询和管理操作。
     */
    @Autowired
    private ManagementService managementService;

    /**
     * 用于管理组和用户，Flowable 实际运行中不会对用户进行检查，例如任务可以分派给任何用户，而不去验证系统中是否存在该用户
     * 因为 Flowable 有时需要与 LDAP 等服务配合使用。
     */
    @Autowired
    private IdentityService identityService;

    /**
     * 暴露 Flowable 引擎收集的所有记录，当执行流程时，引擎会保存许多数据，例如流程启动事件，谁在执行这个任务，
     * 完成任务花费的时间，每个流程实例的执行路径等
     */
    @Autowired
    private HistoryService historyService;

    /**
     * FormService 是可选服务，也就是说没有它 Flowable 也可以很好的运行，而不必牺牲任何功能。
     * 这个服务引入了开始表单与结束表单的概念，表单服务通过简单的方式暴露这些数据。
     */
    @Autowired
    private FormService formService;

    @Autowired
    private DynamicBpmnService dynamicBpmnService;

    public static void main(String[] args) {
        SpringApplication.run(FlowableApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Map<String,Object> variables = new HashMap<String,Object>();
        variables.put("starttime","2018-04-26");
        variables.put("endtime","2018-05-01");
        variables.put("reason","临时有事");

        // 启动一个带有表单的流程实例

        //ProcessInstance processInstance = runtimeService.startProcessInstanceWithForm("holidayRequest:2:25006",null,variables,null);

        // 从引擎中查询数据有两种方式，查询API 与原生(native) 查询。查询 API 可以使用链式API，通过编程方式进行类型安全的查询。
//		runtimeService.setVariableLocal();

        //System.out.println(processInstance.getActivityId());
    }
}
