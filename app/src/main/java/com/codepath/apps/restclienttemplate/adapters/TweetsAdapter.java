package com.codepath.apps.restclienttemplate.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import com.codepath.apps.restclienttemplate.activities.DetailActivity;
import com.codepath.apps.restclienttemplate.R;
import com.codepath.apps.restclienttemplate.models.Tweet;

import org.parceler.Parcels;

import java.util.List;

public class TweetsAdapter extends RecyclerView.Adapter<TweetsAdapter.ViewHolder>{
    String TAG = "TweetsAdapter";
    Context context;
    List<Tweet> tweets;
    // Pass in the context and list of tweets
    public TweetsAdapter(Context context, List<Tweet> tweets) {
        this.context = context;
        this.tweets = tweets;
    }

    // For each row, inflate the layout
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tweet, parent, false);
        return new ViewHolder(view);
    }

    // Bind values based on the position of the element
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Get data at position
        Tweet tweet = tweets.get(position);
        // Bind the tweet with view holder
        holder.bind(tweet);
    }

    @Override
    public int getItemCount() {
        return tweets.size();
    }

    // Clean all elements of the recycler
    public void clear() {
        tweets.clear();
        notifyDataSetChanged();
    }

    // Add a list of items
    public void addAll(List<Tweet> tweetList) {
        tweets.addAll(tweetList);
        notifyDataSetChanged();
    }

    // Define a viewholder
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivProfileImage;
        TextView tvBody;
        TextView tvScreenName;
        //formatted on
        TextView tvTime;
        TextView tvName;
        RelativeLayout container;
        //ImageView tvMedia;
        TextView numLikes;
        TextView numRetweets;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProfileImage = itemView.findViewById(R.id.ivProfileImage);
            tvBody = itemView.findViewById(R.id.tvBody);
            tvScreenName = itemView.findViewById(R.id.tvScreenName);
            tvTime = itemView.findViewById(R.id.tvTime);
            tvName = itemView.findViewById(R.id.tvName);
            container = itemView.findViewById(R.id.tweetContainer);
            //tvMedia = itemView.findViewById(R.id.tvMedia);
            numLikes = itemView.findViewById(R.id.numLikes);
            numRetweets = itemView.findViewById(R.id.numRetweets);
        }

        public void bind(final Tweet tweet) {
            tvBody.setText(tweet.body);
            tvScreenName.setText("@" + tweet.user.screenName);
            tvTime.setText("•" + tweet.formattedTime);
            tvName.setText(tweet.user.name);
            Glide.with(context)
                    .load(tweet.user.profileImageUrl)
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.error)
                    .circleCrop()
                    .into(ivProfileImage);
            numLikes.setText(tweet.getNumLikes());
            numRetweets.setText(tweet.getNumRetweets());
            container.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    //Toast.makeText(context, tweet.getUser().getName(), Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(context, DetailActivity.class);
                    i.putExtra("tweet", Parcels.wrap(tweet));
                    context.startActivity(i);
                }
            });
        }
    }
}
