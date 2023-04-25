package com.example.nguyenapp.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.nguyenapp.fragments.FragmentA;
import com.example.nguyenapp.fragments.FragmentB;
import com.example.nguyenapp.fragments.FragmentC;

public class AdapterViewpager extends FragmentPagerAdapter {
    private int numPage = 3;
    public AdapterViewpager(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        numPage = behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new FragmentA();
            case 1:
                return new FragmentB();
            case 2:
                return new FragmentC();
            default:
                return new FragmentA();
        }
    }

    @Override
    public int getCount() {
        return numPage;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Home";
            case 1:
                return "Search";
            case 2:
                return "Info";
            default:
                return "Home";
        }
    }
}
