package com.testehan.security;

import java.security.BasicPermission;
import java.security.Permission;

public class WarpPermission extends BasicPermission {

    private final int warp;
    public WarpPermission(String name, String action) {
        super(name,action);
        warp = Integer.parseInt(action);
    }

    public boolean implies (Permission p){
        if (!(p instanceof  WarpPermission)) return false;
        if (super.implies(p)){
            WarpPermission other = (WarpPermission) p;
            return (warp >= other.warp);
        }
        else return false;
    }
}
