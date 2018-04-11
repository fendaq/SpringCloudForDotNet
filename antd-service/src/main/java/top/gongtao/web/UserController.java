package top.gongtao.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import top.gongtao.entity.Role;
import top.gongtao.entity.User;
import top.gongtao.repository.UserRepository;
import top.gongtao.util.FastJsonUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
    private UserRepository userRepository;

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

        User user = userRepository.findByUsername(userName);

        return FastJsonUtils.resultSuccess(200, "登录成功", user);

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

        List<GrantedAuthority> r = (List<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();

        StringBuilder sb=new StringBuilder();
        if(r != null){
            for(GrantedAuthority g : r){
                sb.append(g.getAuthority()+",");
            }
        }

        JSONObject jsonObject = new JSONObject(){{
            put("role", sb.toString().substring(0,sb.toString().length()-1));
        }};
        return jsonObject.toString();
    }




}
