package com.example.userasef.parentcontrolapp.utils;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by userAsef on 10/7/2018.
 */

public class ActivityUtil {
    public static void pushFragment(@NonNull Fragment fragment, @NonNull FragmentManager fragmentManager, @IdRes int resId, boolean addToBackStack){
        FragmentTransaction transaction =fragmentManager.beginTransaction();
        if(addToBackStack){
            transaction.add(resId, fragment, fragment.getClass().getSimpleName());
            transaction.addToBackStack(fragment.getClass().getSimpleName());
        }else{
            transaction.replace(resId, fragment, fragment.getClass().getSimpleName());
        }

        transaction.commit();
    }

    public static void backToHomeScreen(@NonNull FragmentManager fragmentManager){
        int backStackCount = fragmentManager.getBackStackEntryCount();

        for (int i = 0; i < backStackCount; i++) {
            int backId = fragmentManager.getBackStackEntryAt(i).getId();

            fragmentManager.popBackStack(backId, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            fragmentManager.popBackStack();
        }
    }

    public static void goToPreviousFragment(@NonNull FragmentManager fragmentManager){
        fragmentManager.popBackStack();
    }

    public static void popAllBackStack(@NonNull FragmentManager fragmentManager) {
        int backStackCount = fragmentManager.getBackStackEntryCount();

        for (int i = 0; i < backStackCount; i++) {
            int backId = fragmentManager.getBackStackEntryAt(i).getId();

            fragmentManager.popBackStack(backId, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            fragmentManager.popBackStack();
        }
    }
}
