package com.testehan.security;

public class CustomPermissionExample {
    public static void main(String[] args) {
        System.setSecurityManager(new SecurityManager());
        CustomPermissionExample customPermissionExample = new CustomPermissionExample();
        customPermissionExample.makeItSo(8);
    }

    private void makeItSo(int warp){
        SecurityManager securityManager = System.getSecurityManager();
        if (securityManager != null){
            securityManager.checkPermission(new WarpPermission("maxWarp", String.valueOf(warp)));
        }

        System.out.println("warp now set to " + warp);
    }
}
