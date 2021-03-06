package top.gongtao.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: gongtao
 * @Date: Created in 2018/4/4 09:01
 * @Description: 用户接口
 */

@RestController
@RequestMapping("/api")
@Api(value = "UserController", description="用户接口")
public class UserController {

    @Autowired
    private Environment env;

//    @Autowired
//    private UserRepository userRepository;

    @ApiOperation(value = "获取当前登陆用户", notes = "获取当前登陆用户")
    @GetMapping(value = "/currentUser", produces = {"application/json;charset=UTF-8"})
    public String getCurrentUser() {

//        String userName = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();


//        String userName = userDetails.getUsername();

//        return authentication.toString();

        JSONObject jsonObject = new JSONObject(){{
            put("name", "gongtao");
            put("avatar", "https://gw.alipayobjects.com/zos/rmsportal/BiazfanxmamNRoxxVxka.png");
            put("userid", "00000001");
            put("notifyCount", 12);
        }};

        return jsonObject.toString();
    }

    @ApiOperation(value = "获取当前登陆用户", notes = "获取当前登陆用户")
    @GetMapping(value = "/fake_chart_data", produces = {"application/json;charset=UTF-8"})
    public String getChartData(){


        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        String userName = (String) SecurityContextHolder.getContext().getAuthentication().getName();

//        User user = userRepository.findByUsername(userName);
//
//        return FastJsonUtils.resultSuccess(200, "登录成功", user);
return null;
    }


    @ApiOperation(value = "获取通知", notes = "获取通知")
    @GetMapping(value = "/notices", produces = {"application/json;charset=UTF-8"})
    public String getNotices(){
        JSONObject jsonObject = new JSONObject(){{
            put("id", "000000001");
            put("avatar", "https://gw.alipayobjects.com/zos/rmsportal/ThXAXghbEsBCCSDihZxY.png");
            put("title", "你收到了 14 份新周报");
            put("datetime", "2017-08-09");
            put("type", "通知");
        }};
        JSONObject jsonObject2 = new JSONObject(){{
            put("id", "000000002");
            put("avatar", "https://gw.alipayobjects.com/zos/rmsportal/OKJXDXrmkNshAMvwtvhu.png");
            put("title", "你推荐的 曲妮妮 已通过第三轮面试");
            put("datetime", "2017-08-08");
            put("type", "通知");
        }};

        List<JSONObject> joList = new ArrayList<JSONObject>();
        joList.add(jsonObject);
        joList.add(jsonObject2);





        return joList.toString();
    }


    @ApiOperation(value = "获取当前登陆人员角色", notes = "获取当前登陆人员角色")
    @GetMapping(value = "/getRole", produces = {"application/json;charset=UTF-8"})
    public String getRole(){

        // 获取用户角色
        StringBuilder sb = new StringBuilder();
        boolean flag = false;
        List<GrantedAuthority> authList = (List<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        for(GrantedAuthority ga : authList){
            if (flag) {
                sb.append(",");
            }else {
                flag=true;
            }
            sb.append(ga.getAuthority());
        }
        JSONObject jsonObject = new JSONObject(){{
            put("role", sb.toString());
        }};
        return jsonObject.toString();
    }


    @ApiOperation(value = "获取系统角色", notes = "获取系统角色")
    @GetMapping(value = "/getSystemRole", produces = {"application/json;charset=UTF-8"})
    public String getSystemRole(){


        JSONObject jsonObject = new JSONObject(){{
            put("role", env.getProperty("antd.roles","ROLE_ADMIN"));
        }};
        return jsonObject.toString();
    }



}
