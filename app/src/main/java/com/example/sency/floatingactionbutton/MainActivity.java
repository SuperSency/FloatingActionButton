package com.example.sency.floatingactionbutton;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;

public class MainActivity extends Activity implements View.OnClickListener {

    FloatingActionButton menu;
    FloatingActionButton one;
    FloatingActionButton two;
    FloatingActionButton three;
    boolean mFlag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        menu.setOnClickListener(this);
    }

    private void initView() {
        menu = (FloatingActionButton) findViewById(R.id.menu);
        one = (FloatingActionButton) findViewById(R.id.one);
        two = (FloatingActionButton) findViewById(R.id.two);
        three = (FloatingActionButton) findViewById(R.id.three);
    }


    @Override
    public void onClick(View view) {
        //按纽动画
        if (!mFlag) {
            //逆时针方向
            ObjectAnimator animator = ObjectAnimator.ofFloat(menu, "rotation", 0f, -45f);
            animator.setDuration(500);
            animator.start();
            showMenu();
            mFlag=true;
        }else{
            ObjectAnimator animator = ObjectAnimator.ofFloat(menu, "rotation", -45f, 0f);
            animator.setDuration(500);
            animator.start();
            hideMenu();
            mFlag=false;
        }
    }

    private void hideMenu() {
        //第一个按钮渐变淡出
        ObjectAnimator oneHide = ObjectAnimator.ofFloat(one, "alpha", 1f, 0.7f, 0.5f, 0.3f, 0f);
        //X轴方向移动
        ObjectAnimator oneHideMove = ObjectAnimator.ofFloat(one, "translationX", -350f, 0f);
        AnimatorSet animSet = new AnimatorSet();
        //移动和淡出同时
        animSet.play(oneHide).with(oneHideMove);
        animSet.setDuration(500);
        animSet.start();

        //第二按钮渐变淡出
        ObjectAnimator twoHide = ObjectAnimator.ofFloat(two, "alpha", 1f, 0.7f, 0.5f, 0.3f, 0f);
        //Y轴方向移动
        ObjectAnimator twoHideMove = ObjectAnimator.ofFloat(two, "translationY", -350f, 0f);
        AnimatorSet animSet2 = new AnimatorSet();
        //移动和淡出同时
        animSet2.play(twoHide).with(twoHideMove);
        animSet2.setDuration(500);
        animSet2.start();

        //第三按钮渐变淡出
        ObjectAnimator threeHide = ObjectAnimator.ofFloat(three, "alpha", 1f, 0.7f, 0.5f, 0.3f, 0f);
        //Y轴方向移动
        float x = (float) Math.sqrt(350*350/2);
        ObjectAnimator threeHideMoveX = ObjectAnimator.ofFloat(three, "translationX", -x, 0f);
        ObjectAnimator threeHideMoveY = ObjectAnimator.ofFloat(three, "translationY", -x, 0f);
        AnimatorSet animSet3 = new AnimatorSet();
        //移动和淡出同时
        animSet3.play(threeHide).with(threeHideMoveX).with(threeHideMoveY);
        animSet3.setDuration(500);
        animSet3.start();
        //结束之后再将按钮隐藏
        if (!animSet.isRunning()) {
            one.setVisibility(View.GONE);
            two.setVisibility(View.GONE);
            three.setVisibility(View.GONE);
        }
    }

    private void showMenu() {
        one.setVisibility(View.VISIBLE);
        ObjectAnimator oneShowUp= ObjectAnimator.ofFloat(one,"alpha",0f,0.3f,0.5f,0.7f,1f);
        ObjectAnimator oneMoveIn = ObjectAnimator.ofFloat(one,"translationX",0f,-350f);
        AnimatorSet animSet = new AnimatorSet();
        animSet.play(oneShowUp).with(oneMoveIn);
        animSet.setDuration(500);
        animSet.start();

        two.setVisibility(View.VISIBLE);
        ObjectAnimator twoShowUp = ObjectAnimator.ofFloat(two,"alpha",0f,0.3f,0.5f,0.7f,1f);
        ObjectAnimator twoMoveIn = ObjectAnimator.ofFloat(two,"translationY",0f,-350f);
        AnimatorSet animSet2 = new AnimatorSet();
        animSet2.play(twoShowUp).with(twoMoveIn);
        animSet2.setDuration(500);
        animSet2.start();

        three.setVisibility(View.VISIBLE);
        ObjectAnimator threeShowUp = ObjectAnimator.ofFloat(three,"alpha",0f,0.3f,0.5f,0.7f,1f);
        float x = (float) Math.sqrt(350*350/2);
        ObjectAnimator threeMoveInX = ObjectAnimator.ofFloat(three,"translationX",0f,-x);
        ObjectAnimator threeMoveInY = ObjectAnimator.ofFloat(three,"translationY",0f,-x);
        AnimatorSet animSet3 = new AnimatorSet();
        animSet3.play(threeShowUp).with(threeMoveInX).with(threeMoveInY);
        animSet3.setDuration(500);
        animSet3.start();
    }
}
