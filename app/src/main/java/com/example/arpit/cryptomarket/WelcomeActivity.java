package com.example.arpit.cryptomarket;

import android.content.Intent;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class WelcomeActivity extends AppCompatActivity {

    private ViewPager mPager;
    private int[] layouts = {R.layout.first_slide,R.layout.second_slide,R.layout.third_slide};
    private MpagerAdapter mpagerAdapter;

    private LinearLayout Dots_Layout;
    private ImageView[] dots;
    private Button BnNext, BnSkip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(Build.VERSION.SDK_INT>=19){
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }else{
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        setContentView(R.layout.activity_welcome);
        mPager = findViewById(R.id.viewPager);
        mpagerAdapter = new MpagerAdapter(layouts,this);
        BnNext = findViewById(R.id.btnNext);
        BnSkip = findViewById(R.id.btnSkip);

        BnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int next_slide = mPager.getCurrentItem() + 1;
                if(next_slide<layouts.length){
                    mPager.setCurrentItem(next_slide);
                }else{
                    startActivity(new Intent(WelcomeActivity.this,MainActivity.class));
                    finish();
                }
            }
        });
        BnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPager.setCurrentItem(layouts.length);
//                startActivity(new Intent(WelcomeActivity.this,MainActivity.class));
//                finish();
            }
        });

        mPager.setAdapter(mpagerAdapter);
        Dots_Layout = findViewById(R.id.dotsLayout);
        createDots(0);

        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                createDots(i);
                if(i == layouts.length-1){
                    BnNext.setText("START");
                    BnSkip.setVisibility(View.INVISIBLE);
                } else{
                    BnNext.setText("NEXT");
                    BnSkip.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }
    private void createDots(int currentPosition){
        if(Dots_Layout!=null){
            Dots_Layout.removeAllViews();
        }
        dots = new ImageView[layouts.length];
        for(int i=0;i<layouts.length;i++){
            dots[i] = new ImageView(this);
            if(i==currentPosition){
                dots[i].setImageDrawable(ContextCompat.getDrawable(this,R.drawable.active_dots));
            }else{
                dots[i].setImageDrawable(ContextCompat.getDrawable(this,R.drawable.default_dots));
            }
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(4,0,4,0);
            Dots_Layout.addView(dots[i],params);
        }
    }
}
