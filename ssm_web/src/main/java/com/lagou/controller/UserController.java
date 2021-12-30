package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.ResponseResult;
import com.lagou.domain.Role;
import com.lagou.domain.User;
import com.lagou.domain.UserVo;
import com.lagou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/findAllUserByPage")
    public ResponseResult findAllUserByPage(@RequestBody UserVo userVo){
        PageInfo pageInfo = userService.findAllUserByPage(userVo);
        List<User> list = pageInfo.getList();
        System.out.println(list);
        return new ResponseResult(true,200,"响应成功",pageInfo);
    }

    @RequestMapping("/updateUserStatus")
    public ResponseResult updateUserStatus(@RequestParam int id, String status){
        if("ENABLE".equalsIgnoreCase(status)){
            status = "DISABLE";
        } else {
            status = "ENABLE";
        }
        userService.updateUserStatus(id, status);
        ResponseResult responseResult = new ResponseResult(true,200,"响应成功",status);
        return responseResult;

    }
    @RequestMapping("/login")
    public ResponseResult login(User user, HttpServletRequest request) throws Exception {
        User user1 = userService.login(user);
        if(user1 != null){
            //保存用户id及access_token到session中
            HttpSession session = request.getSession();
            String access_token = UUID.randomUUID().toString();
            session.setAttribute("access_token",access_token);
            session.setAttribute("user_id",user1.getId());

            //将查询出来的信息响应给前台
            HashMap<String, Object> map = new HashMap<>();
            map.put("access_token",access_token);
            map.put("user_id",user1.getId());
            return new ResponseResult(true,1,"登陆成功",map);
        } else {
            return new ResponseResult(true,400,"用户名密码错误",null);
        }
    }

    //分配角色回显
    @RequestMapping("/findUserRoleById")
    public ResponseResult findUserRelationRoleById(Integer id){
        List<Role> list = userService.findUserRelationRoleById(id);
        ResponseResult result = new ResponseResult(true, 200, "分配角色成功", list);
        return result;
    }

    //分配角色
    @RequestMapping("/userContextRole")
    public ResponseResult userContextRole(@RequestBody UserVo userVo){
        userService.UserContextRole(userVo);
        ResponseResult result = new ResponseResult(true, 200, "分配角色成功", null);
        return result;
    }

    //获取用户权限
    @RequestMapping("/getUserPermissions")
    public ResponseResult getUserPermissions(HttpServletRequest request){
        //获取请求头中的token
        String header_token = request.getHeader("Authorization");
        //获取session中的access_token
        String session_token = (String) request.getSession().getAttribute("access_token");

        //判断token是否一致
        if (header_token.equals(session_token)){
            //获取用户id
            Integer user_id = (Integer) request.getSession().getAttribute("user_id");
            ResponseResult responseResult = userService.getUserPermissions(user_id);
            return responseResult;
        } else {
            return new ResponseResult(false,400,"获取菜单信息失败",null);
        }
    }

}
