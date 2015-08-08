package com.gabrielkou.redditdemo;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.gabrielkou.redditdemo.model.RedditChild;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by gk on 8/7/15.
 */
public class RedditAdapter extends ArrayAdapter<RedditChild> {
    private final static String TAG = RedditAdapter.class.getSimpleName();
    private final Context mContext;
    private ViewHolder viewHolder;
    private LayoutInflater mInflater;

    public RedditAdapter(Context context, ArrayList<RedditChild> redditChildren) {
        super(context, 0, redditChildren);
        this.mContext = context;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        RedditChild child = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_item_reddit, null, false);
            viewHolder = new ViewHolder();
            viewHolder.thumbnailView = (ImageView) convertView.findViewById(R.id.list_item_thumbnail);
            viewHolder.titleView = (TextView) convertView.findViewById(R.id.list_item_title_textview);
            viewHolder.authorView = (TextView) convertView.findViewById(R.id.list_item_author_textview);
            Typeface typeFace=Typeface.createFromAsset(mContext.getAssets(),"fonts/bebasneue.ttf");
            viewHolder.authorView.setTypeface(typeFace);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // Populate the data into the template view using the data object
        viewHolder.titleView.setText(child.getData().getTitle());
        viewHolder.authorView.setText("@" + child.getData().getAuthor().toUpperCase());
        if (!child.getData().getThumbnail().isEmpty()){
            Picasso.with(mContext)
                    .load(child.getData().getThumbnail())
                    .placeholder(R.mipmap.ic_launcher)
                    .into(viewHolder.thumbnailView);
        } else {
            viewHolder.thumbnailView.setImageResource(R.mipmap.ic_launcher);
        }

        // Return the completed view to render on screen
        return convertView;
    }

    static class ViewHolder {
        ImageView thumbnailView;
        TextView titleView;
        TextView authorView;
    }
}
