package me.toptas.rssreader.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import me.toptas.rssreader.R;
import me.toptas.rssreader.base.BaseActivity;
import me.toptas.rssreader.chrome.ChromeTabsWrapper;
import me.toptas.rssreader.model.Feed;
import me.toptas.rssreader.model.RssItem;
import me.toptas.rssreader.rss.RssFragment;
import me.toptas.rssreader.rss.RssFragmentAdapter;
import me.toptas.rssreader.util.FeedParser;

public class MainActivity extends BaseActivity<MainContract.Presenter> implements MainContract.View, RssFragment.OnItemSelectListener {


    @BindView(R.id.viewPager)
    ViewPager mViewPager;

    @BindView(R.id.tablayout)
    TabLayout mTabLayout;

    @BindView(R.id.toolbar)
    android.support.v7.widget.Toolbar mToolbar;

    @Inject
    ChromeTabsWrapper mChromeTabsWrapper;

    @Override
    protected int getContentResource() {
        return R.layout.activity_main;
    }

    @Override
    protected void init(@Nullable Bundle state) {
        setSupportActionBar(mToolbar);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        getPresenter().loadRssFragments();
    }

    @Override
    public void injectDependencies() {
        getActivityComponent().inject(this);
    }

    @Override
    public void onLoadRssFragments() {
        setUpViewPager();
    }


    @Override
    protected void onStart() {
        super.onStart();
        mChromeTabsWrapper.bindCustomTabsService();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mChromeTabsWrapper.unbindCustomTabsService();
    }


    @Override
    public void onItemSelected(RssItem rssItem) {


        String jill=rssItem.getUrl();
        String jill2=jill.replace("www.","");
        contents.urlstrng(jill2);
        Toast.makeText(getApplicationContext(),jill2,Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MainActivity.this, contents.class);
        startActivity(intent);
    }

    private void setUpViewPager() {
        List<RssFragment> fragmentList = new ArrayList<>();
        List<String> titles = new ArrayList<>();
        for (Feed feed : new FeedParser().parseFeeds(this)) {
            fragmentList.add(RssFragment.newInstance(feed));
            titles.add(feed.getTitle());
        }

        RssFragmentAdapter adapter = new RssFragmentAdapter(getSupportFragmentManager(), fragmentList, titles);
        mViewPager.setAdapter(adapter);
    }
}
