package me.toptas.rssreader.rss;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class RssFragmentAdapter extends FragmentPagerAdapter {


    private final List<RssFragment> mRssFragments;
    private final List<String> mTitles;

    public RssFragmentAdapter(FragmentManager fm, List<RssFragment> rssFragments, List<String> titles) {
        super(fm);
        mRssFragments = rssFragments;
        mTitles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return mRssFragments.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }

    @Override
    public int getCount() {
        return mRssFragments.size();
    }
}