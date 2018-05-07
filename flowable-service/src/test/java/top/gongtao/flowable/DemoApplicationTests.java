package top.gongtao.flowable;

import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Autowired
	private RepositoryService repositoryService;

	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	private TaskService taskService;



	// 测试部署流程
	@Test
	public void testDeploy() {
		// 部署流程
		Deployment deployment = repositoryService.createDeployment().addClasspathResource("holiday-request.bpmn20.xml").category("请假").deploy();
		System.out.println("引擎版本：" + deployment.getEngineVersion());

	}

	/**
	 * 测试启动流程
	 */
	@Test
	public void testStartProcess(){

		// 获取流程声明
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionCategory("请假").singleResult();

		// 根据流程 id 启动流程
		ProcessInstance processInstance = runtimeService.startProcessInstanceById("holidayRequests:1:2503");
	}

	/**
	 * 测试申领任务
	 */
	@Test
	public void testClaimTask(){
		// 获取用户的候选任务
		Task task = taskService.createTaskQuery().taskCandidateUser("gongtao").singleResult();

		// 候选用户申领任务
		taskService.claim(task.getId(),"gongtao");

		List<Task> tasks = taskService.createTaskQuery().taskAssignee("gongtao").list();

		Assert.assertEquals(1,tasks.size());

	}

	// 测试执行任务
	@Test
	public void testExecuteTask(){

		// 设置流程变量
		Map<String,Object> variables = new HashMap<String,Object>();
		variables.put("approved",true);
		variables.put("employee","mahang");

		// 获取用户任务实例
		Task task = taskService.createTaskQuery().taskAssignee("gongtao").singleResult();

		// 完成任务并将附上流程参数
		taskService.complete(task.getId(),variables);
	}

	// 测试执行销假任务
	@Test
	public void testExecuteEndTask(){

		// 获取分配给 mahang 的任务
		Task task = taskService.createTaskQuery().taskAssignee("mahang").singleResult();

		Map<String,Object> variables = new HashMap<String,Object>();
		variables.put("end","销假结束");

		// 完成任务
		taskService.complete(task.getId(),variables);


	}

	@Test
	public void testLeaveProcess(){
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionKey("holidayRequests").latestVersion().singleResult();


	}

}
