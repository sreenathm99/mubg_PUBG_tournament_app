package me.toptas.rssreader;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import me.toptas.rssreader.app.SessionData;
import me.toptas.rssreader.model.Feed;
import me.toptas.rssreader.model.RError;
import me.toptas.rssreader.model.RssItem;
import me.toptas.rssreader.rss.RssContract;
import me.toptas.rssreader.rss.RssPresenter;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;

/**
 * Unit testing class for {@link RssPresenter}
 */

public class RssPresenterTest {

    public static final String MOCK_URL = "MOCK_URL";
    private List<RssItem> MOCK_RSS_ITEMS = new ArrayList<>();
    private Feed mFeed = new Feed();

    @Mock
    private RssContract.View mView;

    @Captor
    private ArgumentCaptor<List<RssItem>> mCaptorRssItems;
    @Captor
    private ArgumentCaptor<RError> mCaptorError;


    private RssPresenter mRssPresenter;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        generateMockRssItems();

        SessionData sessionData = new SessionData();
        mRssPresenter = new RssPresenter(sessionData);
        mRssPresenter.getSessionData().addContent(MOCK_URL, MOCK_RSS_ITEMS);
        mRssPresenter.attach(mView);
    }

    private void generateMockRssItems() {
        RssItem rssItem = new RssItem();
        rssItem.setUrl("http://");
        rssItem.setTitle("title");
        rssItem.setDescription("description");

        MOCK_RSS_ITEMS.add(rssItem);

        mFeed.setUrl(MOCK_URL);
    }

    @Test
    public void testLoadRssItems() {
        mRssPresenter.onSuccess(MOCK_RSS_ITEMS, MOCK_URL);
        verify(mView).onRssItemsLoaded(mCaptorRssItems.capture());
        verify(mView).hideLoading();
        assertTrue(mCaptorRssItems.getValue().equals(MOCK_RSS_ITEMS));
    }

    @Test
    public void testLoadRssItemsFromCache() {
        mRssPresenter.loadRssItems(mFeed, true);
        verify(mView).onRssItemsLoaded(MOCK_RSS_ITEMS);
    }

    @Test
    public void testLoadFail() {
        mRssPresenter.onFail(MOCK_URL);
        verify(mView).hideLoading();
        verify(mView).onFail(mCaptorError.capture());
        assertTrue(mCaptorError.getValue().getMessage().equals(RError.ERROR_FETCH));
    }
}
