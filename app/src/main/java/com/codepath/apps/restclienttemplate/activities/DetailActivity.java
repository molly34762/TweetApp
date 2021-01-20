package com.codepath.apps.restclienttemplate.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.R;
import com.codepath.apps.restclienttemplate.models.Tweet;

import org.parceler.Parcels;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class DetailActivity extends AppCompatActivity {
    String TAG = "DetailActivity";
    ImageView ivProfileImage;
    TextView tvScreenName;
    TextView tvName;
    TextView tvCreatedAtTime;
    TextView tvBody;
    ImageView tvMedia;
    ImageView detailTweetArrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvScreenName = findViewById(R.id.tvScreenName);
        ivProfileImage = findViewById(R.id.ivProfileImage);
        tvName = findViewById(R.id.tvName);
        tvCreatedAtTime = findViewById(R.id.tvCreatedAtTime);
        tvBody = findViewById(R.id.tvBody);
        tvMedia = findViewById(R.id.tvMedia);
        detailTweetArrow = findViewById(R.id.detailTweetArrow);

        Tweet tweet = Parcels.unwrap(getIntent().getParcelableExtra("tweet"));
        tvScreenName.setText("@" + tweet.getUser().getScreenName());

        Glide.with(getApplicationContext())
                .load(tweet.getUser().getProfileImageUrl())
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .circleCrop()
                .into(ivProfileImage);

        String mediaUrl = tweet.getEntity().getMediaUrl();
        if (mediaUrl != "") {
            tvMedia.setVisibility(View.VISIBLE);
            Glide.with(getApplicationContext())
                    .load(tweet.getEntity().getMediaUrl())
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.error)
                    .fitCenter()
                    .transform(new RoundedCornersTransformation(25, 10))
                    .into(tvMedia);
        }
        tvName.setText(tweet.getUser().getName());
        tvCreatedAtTime.setText(tweet.getCreatedAt());
        tvBody.setText(tweet.getBody());

        detailTweetArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailActivity.this, TimelineActivity.class);
                startActivity(intent);
            }
        });
    }
}