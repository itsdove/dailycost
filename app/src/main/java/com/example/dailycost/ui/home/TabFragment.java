package com.example.dailycost.ui.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dailycost.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;


public class TabFragment extends Fragment {
    private TabLayout mTabLayout;
    private ViewPager2 mViewPage;
    private List<Fragment> mDatas = new ArrayList<>();//ViewPage2的Fragment容器
    private String[] tabTitles;//tab的标题
    public TabFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tab, container, false);
        initData();
        mTabLayout =view.findViewById(R.id.tablelayout);
        mViewPage = view.findViewById(R.id.viewpage);
        MyViewPageAdapter mAdapter = new MyViewPageAdapter(this, mDatas);
        mViewPage.setAdapter(mAdapter);
        new TabLayoutMediator(mTabLayout, mViewPage, (tab, position) -> tab.setText(tabTitles[position])).attach();
        mViewPage.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
            }
        });
        //TabLayout的选中改变监听
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        return view;
    }
    private void initData() {
        tabTitles = new String[]{"1","2","3","4","5","6","7","7","8","10","11","12"};
        mDatas.add(DetailFragment.newInstance(1));
        mDatas.add(DetailFragment.newInstance(2));
        mDatas.add(DetailFragment.newInstance(3));
        mDatas.add(DetailFragment.newInstance(4));
        mDatas.add(DetailFragment.newInstance(5));
        mDatas.add(DetailFragment.newInstance(6));
        mDatas.add(DetailFragment.newInstance(7));
        mDatas.add(DetailFragment.newInstance(8));
        mDatas.add(DetailFragment.newInstance(9));
        mDatas.add(DetailFragment.newInstance(10));
        mDatas.add(DetailFragment.newInstance(11));
        mDatas.add(DetailFragment.newInstance(12));





    }
}