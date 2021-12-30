package com.lagou.dao;

import com.lagou.domain.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    //用户分页&多条件组合查询
    public List<User> findAllUserByPage(UserVo userVo);

    //修改用户状态
    void updateUserStatus(@Param("id") int id, @Param("status") String status);

    //用户登陆（更具用户名查询具体点用户信息）
    public User login(User user);


    //根据用户id清空中间表
    public void deleteUserContextRole(Integer userId);

    //分配角色
    public void userContextRole(User_Role_relation user_role_relation);

    //根据用户id查询关联的角色信息
    public List<Role> findUserRelationRoleById(Integer id);

    //根据角色id查询角色的顶集菜单(-1)
    public List<Menu> findParentMenuByRoleId(List<Integer> ids);

    //根据pid查询子菜单信息
    public List<Menu> findSubMenuByPid(Integer pid);

    //获取用户拥有的资源权限信息 用户——角色——资源
    public List<Resource> findResourceByRoleId(List<Integer> ids);




}
