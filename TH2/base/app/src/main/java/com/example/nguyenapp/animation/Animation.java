package com.example.nguyenapp.animation;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

public class Animation implements ViewPager.PageTransformer {
    @Override
    public void transformPage(@NonNull View page, float position) {
//        if (position < -1) {
//            page.setAlpha(0);
//        } else if (position <= 1) {
//            page.setAlpha(1);
//            page.setTranslationX(-position * page.getWidth());
//            float yPosition = position * page.getHeight();
//            page.setTranslationY(yPosition);
//        } else {
//            page.setAlpha(0);
//        }
        int pageWidth = page.getWidth();

        if (position < -1) { // Page is off-screen to the left
            page.setAlpha(0);
        } else if (position <= 0) { // Page is transitioning from left to right
            page.setAlpha(1);
            page.setTranslationX(0);
        } else if (position <= 1) { // Page is transitioning from right to left
            page.setAlpha(1);
            page.setTranslationX(-pageWidth * position);
        } else { // Page is off-screen to the right
            page.setAlpha(0);
        }
    }
}
