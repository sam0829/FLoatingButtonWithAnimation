package com.sam.floatingbuttonwithanimation;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;

import com.sam.floatingbuttonwithanimation.circularrevealanimation.SupportAnimator;
import com.sam.floatingbuttonwithanimation.circularrevealanimation.ViewAnimationUtils;


public class AddTopicActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_topic);

        final View viewMain = findViewById(R.id.main);
        final View vs = findViewById(R.id.llContent);

        ImageView imageView = (ImageView) findViewById(R.id.ivClose);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int cx = (vs.getLeft() + vs.getRight()) / 2;
                int cy = (vs.getTop() + vs.getBottom()) / 2;

                // get the final radius for the clipping circle
                int finalRadius = Math.max(vs.getWidth(), vs.getHeight());
                vs.setVisibility(View.VISIBLE);
                SupportAnimator animator =
                        ViewAnimationUtils.createCircularReveal(vs, cx, cy, finalRadius, 0);
                animator.setInterpolator(new AccelerateDecelerateInterpolator());
                animator.setDuration(800);
                animator.addListener(new SupportAnimator.AnimatorListener() {
                    @Override
                    public void onAnimationStart() {

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                vs.setBackgroundColor(Color.parseColor("#F06767"));
                            }
                        },350);
                    }

                    @Override
                    public void onAnimationEnd() {
                        vs.setVisibility(View.INVISIBLE);
                        finish();
                    }

                    @Override
                    public void onAnimationCancel() {

                    }

                    @Override
                    public void onAnimationRepeat() {

                    }
                });
                animator.start();




            }
        });

        vs.setVisibility(View.INVISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                int cx = (vs.getLeft() + vs.getRight()) / 2;
                int cy = (vs.getTop() + vs.getBottom()) / 2;

                // get the final radius for the clipping circle
                int finalRadius = Math.max(vs.getWidth(), vs.getHeight());
                vs.setVisibility(View.VISIBLE);

                SupportAnimator animator =
                        ViewAnimationUtils.createCircularReveal(vs, cx, cy, 0, finalRadius);
                animator.setInterpolator(new AccelerateDecelerateInterpolator());
                animator.setDuration(800);

                animator.start();
                viewMain.setBackgroundColor(Color.parseColor("#7D000000"));
                vs.setVisibility(View.VISIBLE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        TransitionDrawable transition = (TransitionDrawable) vs.getBackground();
                        transition.startTransition(1000);
                    }
                },800);


            }
        }, 1000);
    }
}
