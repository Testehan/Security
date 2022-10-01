package com.testehan.security;

import java.io.FileWriter;
import java.io.IOException;
import java.security.*;
import java.util.Enumeration;

public class SimpleExample
{
    public static void main( String[] args ) throws IOException {
        System.setSecurityManager(new SecurityManager());

        String version = System.getProperty("java.version");
        System.out.println(version);

        try (FileWriter writer = new FileWriter("out.txt")){
            /*
            This will throw an exception unless we modify the java policy file (see tutorials.txt for more info):
            Exception in thread "main" java.security.AccessControlException: access denied ("java.io.FilePermission" "out.txt" "write")
             */
            writer.write("Hello worldddd");
        }

        SimpleExample app = new SimpleExample();
        app.getPermissions();

    }

    public void getPermissions(){
        Policy policy = Policy.getPolicy();
        ProtectionDomain pd = getClass().getProtectionDomain();
        CodeSource cs = pd.getCodeSource();
        PermissionCollection permissionCollection = policy.getPermissions(cs);
        Enumeration<Permission> permissions = permissionCollection.elements();

        while (permissions.hasMoreElements()){
            System.out.println(permissions.nextElement());
        }
    }
}
