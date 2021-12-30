package com.lagou.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.dao.UserMapper;
import com.lagou.domain.*;
import com.lagou.service.UserService;
import com.lagou.utils.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public PageInfo findAllUserByPage(UserVo userVo) {
        PageHelper.startPage(userVo.getCurrentPage(),userVo.getPageSize());
        List<User> allUser = userMapper.findAllUserByPage(userVo);
        PageInfo<User> userPageInfo = new PageInfo<>(allUser);

        System.out.println("总条数:"+userPageInfo.getTotal());
        System.out.println("总页数:"+userPageInfo.getPages());
        System.out.println("当前页:"+userPageInfo.getPageNum());
        System.out.println("每页显示长度:"+userPageInfo.getPageSize());
        System.out.println("是否第一页:"+userPageInfo.isIsFirstPage());
        System.out.println("是否最后一页:"+userPageInfo.isIsLastPage());

        return userPageInfo;
    }

    @Override
    public void updateUserStatus(int id, String status) {
        userMapper.updateUserStatus(id, status);
    }

    @Override
    public User login(User user) throws Exception {
        User login = userMapper.login(user);
        if(login != null && Md5.verify(user.getPassword(),"lagou",login.getPassword())){
             return login;
        } else {
            return null;
        }

    }

    @Override
    public List<Role> findUserRelationRoleById(Integer id) {
        List<Role> list = userMapper.findUserRelationRoleById(id);
        return list;
    }

    @Override
    public void UserContextRole(UserVo userVo) {
        //根据用户id清空中间表
        userMapper.deleteUserContextRole(userVo.getUserId());
        //重新简历中间关系
        for (Integer roleid : userVo.getRoleIdList()) {
            //封装数据
            User_Role_relation user_role_relation = new User_Role_relation();
            user_role_relation.setUserId(userVo.getUserId());
            user_role_relation.setRoleId(roleid);

            //封装创建时间
            Date date = new Date();
            user_role_relation.setCreatedTime(date);
            user_role_relation.setUpdatedTime(date);

            user_role_relation.setCreatedBy("system");
            user_role_relation.setUpdatedby("system");

            userMapper.userContextRole(user_role_relation);
        }
    }

    //获取用户权限信息
    @Override
    public ResponseResult getUserPermissions(Integer userid) {
        System.out.println("=======================" + userid +"=======================");
        //1.获取当前用户拥有的角色
        List<Role> roleList = userMapper.findUserRelationRoleById(userid);
        System.out.println("======================="+roleList+"=======================");
        //2.获取角色ID,保存到 list
        ArrayList<Integer> roleIds = new ArrayList<>();
        for(Role role : roleList){
            roleIds.add(role.getId());
        }
        //3 根据角色id查询顶级菜单
        List<Menu> parentMenu = userMapper.findParentMenuByRoleId(roleIds);

        //查询顶级菜单的子菜单
        for (Menu menu : parentMenu) {
            List<Menu> subMenu = userMapper.findSubMenuByPid(menu.getId());
            menu.setSubMenuList(subMenu);
        }
        // 获取资源权限
        List<Resource> resourceList = userMapper.findResourceByRoleId(roleIds);
        //封装数据
        Map<String,Object> map = new HashMap<>();
        map.put("menuList",parentMenu); //menuList: 菜单权限数据
        map.put("resourceList",resourceList);//resourceList: 资源权限数据
        ResponseResult result = new ResponseResult(true,200,"响应成功",map);
        return result;
    }
}
