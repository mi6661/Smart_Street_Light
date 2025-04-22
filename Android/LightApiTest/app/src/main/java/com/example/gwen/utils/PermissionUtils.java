package com.example.gwen.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class PermissionUtils {
    //需要获取的权限集合
    private static final String[] PERMISSIONS = {
            Manifest.permission.ACCESS_NETWORK_STATE,
            Manifest.permission.INTERNET
    };

    public static void getAllPermission(Activity act){
        if(Build.VERSION.SDK_INT>=23){
            ArrayList<String> pm = new ArrayList<>();
            for (String permission : PERMISSIONS) {
                int grant = ContextCompat.checkSelfPermission(act.getApplicationContext(),permission);
                if(grant != PackageManager.PERMISSION_GRANTED)  pm.add(permission);
            }
            if(pm.size()>0){
                String[] sList = pm.toArray(new String[0]);
                ActivityCompat.requestPermissions(act,sList,10000);
            }else{
                System.out.println("以获得全部权限");
            }
        }
    }
}
