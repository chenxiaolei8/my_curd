package com.hxkj.system.model.base;

import com.jfinal.plugin.activerecord.IBean;
import com.jfinal.plugin.activerecord.Model;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseSysRoleMenu<M extends BaseSysRoleMenu<M>> extends Model<M> implements IBean {

    public Integer getRoleId() {
        return get("role_id");
    }

    public void setRoleId(Integer roleId) {
        set("role_id", roleId);
    }

    public Integer getMenuId() {
        return get("menu_id");
    }

    public void setMenuId(Integer menuId) {
        set("menu_id", menuId);
    }

}
