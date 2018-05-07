package top.gongtao.flowablespringbootdemo;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

/**
 * @Author: gongtao
 * @Date: Created in 2018/5/7 12:34
 * @Description:
 */
public class CallExternalSystemDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) {

        System.out.println("Calling the external system for employee"+ execution.getVariable("employee"));
    }
}
