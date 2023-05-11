package com.example.clayshootinggame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView iv_gun;
    ImageView iv_bullet;
    ImageView iv_clay;

    double screen_width;
    double screen_height;

    float gun_width, gun_height;
    float bullet_width, bullet_height;
    float clay_width, clay_height;

    float bullet_center_x, bullet_center_y;
    float clay_center_x, clay_center_y;

    double gun_x,gun_y;
    double gun_center_x;

    final int NO_OF_CLAYS = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        Button btnStart = findViewById(R.id.btnStart);
        Button btnStop = findViewById(R.id.btnStop);
        btnStart.setOnClickListener(this);
        btnStop.setOnClickListener(this);

        ConstraintLayout layout = findViewById(R.id.layout);

        screen_height = Resources.getSystem().getDisplayMetrics().heightPixels;
        screen_width = Resources.getSystem().getDisplayMetrics().widthPixels;

        ImageView iv_gun = new ImageView(this);
        ImageView iv_bullet = new ImageView(this);
        ImageView iv_clay = new ImageView(this);

        iv_gun.setImageResource(R.drawable.gun);
        iv_gun.measure(View.MeasureSpec.UNSPECIFIED,View.MeasureSpec.UNSPECIFIED);
        gun_height = iv_gun.getMeasuredHeight();
        gun_width = iv_gun.getMeasuredWidth();
        layout.addView(iv_gun);

        iv_bullet.setImageResource(R.drawable.bullet);
        iv_bullet.measure(View.MeasureSpec.UNSPECIFIED,View.MeasureSpec.UNSPECIFIED);
        bullet_height = iv_bullet.getMeasuredHeight();
        bullet_width = iv_bullet.getMeasuredWidth();
        iv_bullet.setVisibility(View.INVISIBLE);
        layout.addView(iv_bullet);

        iv_clay.setImageResource(R.drawable.clay);
        iv_clay.setScaleX(0.8f);
        iv_clay.setScaleY(0.8f);
        iv_clay.measure(View.MeasureSpec.UNSPECIFIED,View.MeasureSpec.UNSPECIFIED);
        clay_height = iv_clay.getMeasuredHeight();
        clay_width = iv_clay.getMeasuredWidth();
        layout.addView(iv_clay);

        gun_center_x = screen_width * 0.7;
        gun_x = gun_center_x - gun_width * 0.5;
        gun_y = screen_height - gun_height;

        iv_gun.setX((float)gun_x);
        iv_gun.setY((float)gun_y);

        iv_gun.setClickable(true);
        iv_gun.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btnStart)
            gameStart();
        else if(view.getId() == R.id.btnStop)
            gameStop();
        else if(view == iv_gun)
            shootingStart();

    }

    private void gameStart() {
        ObjectAnimator clay_X = ObjectAnimator.ofFloat(iv_clay, "translationX", -100f, (float)screen_width );
        ObjectAnimator clay_Y = ObjectAnimator.ofFloat(iv_clay,"translationY",0f,0f);
        ObjectAnimator clay_rotation = ObjectAnimator.ofFloat(iv_clay,"rotation",0f,360f*5f);

        clay_X.setRepeatCount(NO_OF_CLAYS-1);
        clay_Y.setRepeatCount(NO_OF_CLAYS-1);
        clay_rotation.setRepeatCount(NO_OF_CLAYS-1);

        clay_X.setDuration(3000);
        clay_Y.setDuration(3000);
        clay_rotation.setDuration(3000);

        clay_X.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(@NonNull Animator animator) {
                iv_clay.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(@NonNull Animator animator) {
                Toast.makeText(getApplicationContext(),"게임종료",Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onAnimationCancel(@NonNull Animator animator) {

            }

            @Override
            public void onAnimationRepeat(@NonNull Animator animator) {
                iv_clay.setVisibility(View.VISIBLE);
            }
        });
        clay_X.start();
        clay_Y.start();
        clay_rotation.start();
    }

    private void gameStop() {
        finish();
    }

    private void shootingStart() {

    }
}