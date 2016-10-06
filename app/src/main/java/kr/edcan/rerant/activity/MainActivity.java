/*
 * Created by Junseok Oh on 2016.
 * Copyright by Good-Reserve Project @kotohana5706
 * All rights reversed.
 */

package kr.edcan.rerant.activity;


import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.nitrico.lastadapter.BR;
import com.github.nitrico.lastadapter.LastAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import kr.edcan.rerant.R;
import kr.edcan.rerant.databinding.ActivityMainBinding;
import kr.edcan.rerant.databinding.MainHeaderViewpagerLayoutBinding;
import kr.edcan.rerant.databinding.MainRecyclerContentBinding;
import kr.edcan.rerant.databinding.MainRecyclerHeaderBinding;
import kr.edcan.rerant.model.MainContent;
import kr.edcan.rerant.model.MainHeader;
import kr.edcan.rerant.model.MainTopHeader;


public class MainActivity extends AppCompatActivity implements LastAdapter.OnClickListener, LastAdapter.OnBindListener, LastAdapter.LayoutHandler {

    ActivityMainBinding binding;
    ArrayList<Object> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        startActivity(new Intent(getApplicationContext(), AuthActivity.class));
//        finish();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setTitle("");
        binding.progressLoading.startAnimation();
        arrayList = new ArrayList<>();
        setData();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                initUI();
            }
        }, 600);
    }

    private void setData() {
        arrayList.add(new MainTopHeader());
        arrayList.add(new MainHeader("곧 예약시간에 도달", "아래의 음식점에 예약한 시간이 얼마 남지 않았습니다."));
        arrayList.add(new MainContent("REZRO-4062", "종현이네 원조 보쌈 24시", "1일 23시간 43분 남음"));
    }

    private void initUI(){
        getSupportActionBar().setTitle("GoodReserve");
        binding.mainRecycler.setVisibility(View.VISIBLE);
        binding.progressLoading.setVisibility(View.GONE);
        binding.toolbar.setTitleTextColor(Color.WHITE);
        binding.mainRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        LastAdapter.with(arrayList, BR.item)
                .layoutHandler(this)
                .map(MainTopHeader.class, R.layout.main_first_header)
                .map(MainHeader.class, R.layout.main_recycler_header)
                .map(MainContent.class, R.layout.main_recycler_content)
                .onClickListener(this)
                .onBindListener(this)
                .into(binding.mainRecycler);a
    }

    @Override
    public void onClick(@NotNull Object o, @NotNull View view, int i, int i1) {

    }

    @Override
    public void onBind(@NotNull Object o, @NotNull View view, int type, int position) {
        switch (type) {
            case R.layout.main_first_header:
                break;
        }
    }

    @Override
    public int getItemLayout(@NotNull Object item, int i) {
        if (item instanceof MainHeader) return R.layout.main_recycler_header;
        else if(item instanceof MainTopHeader) return R.layout.main_first_header;
        else return R.layout.main_recycler_content;
    }
    /**
     * PagerAdapter
     */
    private class PagerAdapterClass extends PagerAdapter {
        private LayoutInflater mInflater;
        int score = 0;

        public PagerAdapterClass(Context c) {
            super();
            mInflater = LayoutInflater.from(c);

        }


        @Override
        public int getCount() {
            return 10;
        }

        @Override
        public Object instantiateItem(final ViewGroup container, int position) {
            MainHeaderViewpagerLayoutBinding binding = DataBindingUtil.getBinding(container);
            return binding.getRoot();
        }

        @Override
        public void destroyItem(View pager, int position, Object view) {
            ((ViewPager) pager).removeView((View) view);
        }

        @Override
        public boolean isViewFromObject(View pager, Object obj) {
            return pager == obj;
        }

        @Override
        public void restoreState(Parcelable arg0, ClassLoader arg1) {
        }

        @Override
        public Parcelable saveState() {
            return null;
        }

        @Override
        public void startUpdate(View arg0) {
        }

        @Override
        public void finishUpdate(View arg0) {
        }
    }
}
