package com.example.englearn.animations;

import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;

public class Animations {

    public void fadeInAnimationForTextView(final TextView selectedTextView) {
        final AlphaAnimation fadeInAnimationForTextView = new AlphaAnimation(0,1);
        fadeInAnimationForTextView.setDuration(450);
        fadeInAnimationForTextView.setFillAfter(true);
        fadeInAnimationForTextView.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                selectedTextView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        selectedTextView.startAnimation(fadeInAnimationForTextView);
    }

    public void fadeOutAnimationForTextView(final TextView selectedTextView) {
        final AlphaAnimation fadeOutAnimationForTextView = new AlphaAnimation(1,0);
        fadeOutAnimationForTextView.setDuration(450);
        fadeOutAnimationForTextView.setFillAfter(true);
        fadeOutAnimationForTextView.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                selectedTextView.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        selectedTextView.startAnimation(fadeOutAnimationForTextView);
    }

    public void fadeInAnimationForButton(final Button selectedButton) {
        final AlphaAnimation fadeInAnimationForButton = new AlphaAnimation(0,1);
        fadeInAnimationForButton.setDuration(450);
        fadeInAnimationForButton.setFillAfter(true);
        fadeInAnimationForButton.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                selectedButton.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        selectedButton.startAnimation(fadeInAnimationForButton);
    }

    public void fadeOutAnimationForButton(final Button selectedButton) {
        final AlphaAnimation fadeOutAnimationForButton = new AlphaAnimation(1,0);
        fadeOutAnimationForButton.setDuration(450);
        fadeOutAnimationForButton.setFillAfter(true);
        fadeOutAnimationForButton.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                selectedButton.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        selectedButton.startAnimation(fadeOutAnimationForButton);
    }
}
