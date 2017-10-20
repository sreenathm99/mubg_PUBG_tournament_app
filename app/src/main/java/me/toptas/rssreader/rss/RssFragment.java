package me.toptas.rssreader.rss;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import me.toptas.rssreader.R;
import me.toptas.rssreader.base.BaseFragment;
import me.toptas.rssreader.model.Feed;
import me.toptas.rssreader.model.RError;
import me.toptas.rssreader.model.RssItem;

/**
 * Created by faruktoptas on 29/01/17.
 */

public class RssFragment extends BaseFragment<RssPresenter> implements RssContract.View, SwipeRefreshLayout.OnRefreshListener, RssItemsAdapter.OnItemClickListener {

    private static final String KEY_FEED = "FEED";


    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @BindView(R.id.swRefresh)
    SwipeRefreshLayout mSwRefresh;

    @BindView(R.id.tvNoItems)
    TextView mTvNoItems;

    private Feed mFeed;
    private RssItemsAdapter mAdapter;
    private OnItemSelectListener mListener;

    public static RssFragment newInstance(Feed feed) {
        RssFragment rssFragment = new RssFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY_FEED, feed);
        rssFragment.setArguments(bundle);
        return rssFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFeed = (Feed) getArguments().getSerializable(KEY_FEED);
    }

    @Override
    protected int getContentResource() {
        return R.layout.fragment_rss;
    }

    @Override
    protected void init(@Nullable Bundle state) {
        mAdapter = new RssItemsAdapter(getActivity());
        mAdapter.setListener(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);
        mSwRefresh.setOnRefreshListener(this);
        getPresenter().loadRssItems(mFeed, true);
    }

    @Override
    public void injectDependencies() {
        getFragmentComponent().inject(this);
    }

    @Override
    public void showLoading() {
        mSwRefresh.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        mSwRefresh.setRefreshing(false);
    }

    @Override
    public void onRssItemsLoaded(List<RssItem> rssItems) {
        mAdapter.setItems(rssItems);
        mAdapter.notifyDataSetChanged();
        if (mRecyclerView.getVisibility() != View.VISIBLE) {
            mRecyclerView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onBrowse(RssItem rssItem) {
        mListener.onItemSelected(rssItem);
    }

    @Override
    public void onFail(RError rError) {
        mRecyclerView.setVisibility(View.GONE);
        mTvNoItems.setVisibility(View.VISIBLE);
    }

    @Override
    public void onRefresh() {
        getPresenter().loadRssItems(mFeed, false);
    }

    @Override
    public void onItemSelected(RssItem rssItem) {
        getPresenter().browseRssUrl(rssItem);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnItemSelectListener) {
            mListener = (OnItemSelectListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnItemSelectListener {
        void onItemSelected(RssItem rssItem);
    }
}
