package me.mubg.tournament.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.ads.InterstitialAd;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import me.mubg.tournament.base.BaseActivity;
import me.mubg.tournament.chrome.ChromeTabsWrapper;
import me.mubg.tournament.model.Feed;
import me.mubg.tournament.model.RssItem;
import me.mubg.tournament.rss.RssFragment;
import me.mubg.tournament.rss.RssFragmentAdapter;
import me.mubg.tournament.util.FeedParser;
import me.mubg.tournament.R;

public class MainActivity extends BaseActivity<MainContract.Presenter> implements MainContract.View, RssFragment.OnItemSelectListener {

    private static String LOG_TAG = "EXAMPLE";
    InterstitialAd mInterstitialAd;
    private InterstitialAd interstitial;

    private FirebaseAnalytics mFirebaseAnalytics;

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

       /*
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        */

        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(null);


        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        getPresenter().loadRssFragments();

        //subscribe to push news
        FirebaseMessaging.getInstance().subscribeToTopic("NEWS");



// Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        mFirebaseAnalytics.setAnalyticsCollectionEnabled(true);
        mFirebaseAnalytics.setMinimumSessionDuration(10000);

    }

  /*
   public void displayInterstitial() {
// If Ads are loaded, show Interstitial else show nothing.
        if (interstitial.isLoaded()) {
            interstitial.show();
        }
    }
    */

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
       /*
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

// Prepare the Interstitial Ad
        interstitial = new InterstitialAd(MainActivity.this);
// Insert the Ad Unit ID
        interstitial.setAdUnitId(getString(R.string.admob_interstitial_id));

        interstitial.loadAd(adRequest);
// Prepare an Interstitial Ad Listener
        interstitial.setAdListener(new AdListener() {
            public void onAdLoaded() {
                // Call displayInterstitial() function
                displayInterstitial();
            }
        });

        displayInterstitial();
       */
        String title = rssItem.getTitle();
        String jill = rssItem.getUrl();
        System.out.println(jill);
        String jill2 = jill.replace("www.", "");
        System.out.println(jill2);
        if(jill2.contains("regscreen"))
        {
            register.urlstrng(jill2, title);
            Intent intent = new Intent(MainActivity.this, register.class);
            startActivity(intent);
        }
        else if (jill2.contains("morescreen"))
        {
            more.urlstrng(jill2, title);
            Intent intent = new Intent(MainActivity.this, more.class);
            startActivity(intent);
        }
        else {
            htmlextractpage.urlstrng(jill2, title);
            Intent intent = new Intent(MainActivity.this, htmlextractpage.class);
            startActivity(intent);
        }
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.id_aboutus) {
            Intent intent = new Intent(MainActivity.this, aboutus.class);
            startActivity(intent);
            return true;
        }
        else if(id == R.id.support)
        {
            Intent intent = new Intent(MainActivity.this, support.class);
            startActivity(intent);
            return true;

        }
        return  true;
    }
}


