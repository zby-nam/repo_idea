package com.lagou.service;

import com.lagou.domain.Role;
import com.lagou.domain.RoleMenuVo;
import com.lagou.domain.Role_menu_relation;

import java.util.List;

public interface RoleService {
    //查询所有角色
    public List<Role> findAllRole(Role role);

    //根据角色id查询该角色关联的菜单信息
    public List<Integer> findMenuByRoleId(Integer roleId);

    //为角色分配菜单
    public void RoleContextMenu(RoleMenuVo roleMenuVo);

    //删除juese
    void deleteRole(Integer Id);
}
