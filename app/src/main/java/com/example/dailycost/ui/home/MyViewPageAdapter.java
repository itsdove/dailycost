package com.example.dailycost.ui.home;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

public class MyViewPageAdapter extends FragmentStateAdapter {
    List<Fragment> mDatas ;

    public MyViewPageAdapter(@NonNull Fragment fragmentActivity, List<Fragment> mDatas) {
        super(fragmentActivity);
        this.mDatas = mDatas;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return mDatas.get(position);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }
}
