package me.toptas.rssreader.rss;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.toptas.rssreader.R;
import me.toptas.rssreader.model.RssItem;
import me.toptas.rssreader.util.NullUtils;

public class RssItemsAdapter extends RecyclerView.Adapter<RssItemsAdapter.ViewHolder> {

    private final List<RssItem> mItems = new ArrayList<>();
    private OnItemClickListener mListener;
    private final Context mContext;


    public RssItemsAdapter(Context context) {
        mContext = context;
    }


    public void setListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    public void setItems(List<RssItem> items) {
        mItems.clear();
        mItems.addAll(items);
    }

    @Override
    public RssItemsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_rss_item, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(RssItemsAdapter.ViewHolder holder, int position) {
        if (mItems.size() <= position) {
            return;
        }
        final RssItem item = mItems.get(position);
        holder.textTitle.setText(item.getTitle());
        holder.textPubDate.setText(item.getPubDate());
        /*String date = Utils.convertDate(mContext, item.getPubDate());
        if (date != null) {
            if (date.length() == 0) {
                holder.textPubDate.setVisibility(View.GONE);
            } else {
                holder.textPubDate.setVisibility(View.VISIBLE);
                holder.textPubDate.setText(date);
            }
        } else {
            holder.textPubDate.setVisibility(View.GONE);
        }*/
        if (NullUtils.notEmpty(item.getImageUrl())) {
            Picasso.with(mContext).load(item.getImageUrl()).
                    fit()
                    .centerCrop()
//                    .error(R.drawable.bg_empty_image)
//                    .placeholder(R.drawable.news_placeholder_white)
                    .into(holder.imageThumb);
        } else {
//            holder.imageThumb.setImageResource(R.drawable.bg_empty_image);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) mListener.onItemSelected(item);
            }
        });
        holder.itemView.setTag(item);

    }


    @Override
    public int getItemCount() {
        return mItems.size();
    }


    interface OnItemClickListener {
        void onItemSelected(RssItem rssItem);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvTitle)
        TextView textTitle;

        @BindView(R.id.tvPubDate)
        TextView textPubDate;

        @BindView(R.id.ivThumb)
        ImageView imageThumb;

        @BindView(R.id.llTextContainer)
        RelativeLayout llTextContainer;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
