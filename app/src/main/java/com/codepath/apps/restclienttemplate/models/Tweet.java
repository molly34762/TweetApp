package com.codepath.apps.restclienttemplate.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.codepath.apps.restclienttemplate.utils.TimeFormatter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;


@Parcel
@Entity(foreignKeys = @ForeignKey(entity = User.class, parentColumns = "id", childColumns = "userId"))
public class Tweet {
    public static String TAG = "Tweet";
    @ColumnInfo
    @PrimaryKey
    public long id;

    @ColumnInfo
    public String body;

    @ColumnInfo
    public String createdAt;

    @ColumnInfo
    public String formattedTime;

    @ColumnInfo
    public String mediaType;
    @ColumnInfo
    public String mediaUrl;
    @Ignore
    public Entities entity;
    @ColumnInfo
    public int numLikes;
    @ColumnInfo
    public int numRetweets;
    @ColumnInfo
    public boolean likeBool;
    @ColumnInfo
    public boolean retweetBool;

    @Ignore
    public User user;

    @ColumnInfo
    public long userId;

    // empty constructor needed by the Parceler library
    public Tweet() {

    }

    public static Tweet fromJson(JSONObject jsonObject) throws JSONException {
        Tweet tweet = new Tweet();
        tweet.body = jsonObject.getString("text");
        tweet.createdAt = jsonObject.getString("created_at");
        tweet.id = jsonObject.getLong("id");
        User user = User.fromJson(jsonObject.getJSONObject("user"));
        tweet.user = user;
        tweet.userId = user.id;
        tweet.formattedTime = getFormattedTimestamp(tweet.createdAt);
        tweet.entity = Entities.fromJson(jsonObject.getJSONObject("entities"));
        tweet.numLikes = jsonObject.getInt("favorite_count");
        tweet.numRetweets = jsonObject.getInt("retweet_count");

        return tweet;
    }

    private static String getFormattedTimestamp(String createdAt) {
        return TimeFormatter.getTimeDifference(createdAt);
    }

    public static List<Tweet> fromJsonArray(JSONArray jsonArray) throws JSONException {
        List<Tweet> tweets = new ArrayList<>();
        for (int i=0; i < jsonArray.length(); i++) {
            tweets.add(fromJson(jsonArray.getJSONObject(i)));
        }
        return tweets;
    }

    public String getBody() {
        return body;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public long getId() {
        return id;
    }

    public String getFormattedTime() {
        return formattedTime;
    }

    public User getUser() {
        return user;
    }

    public String getMediaType() {
        return mediaType;
    }

    public String getMediaUrl() {
        return mediaUrl;
    }

    public Entities getEntity() {
        return entity;
    }

    public void setEntity(Entities entity) {
        this.entity = entity;
    }

    public String getNumLikes() {
        return String.valueOf(numLikes);
    }

    public String getNumRetweets() {
        return String.valueOf(numRetweets);
    }

    public boolean isLikeBool() {
        return likeBool;
    }

    public boolean isRetweetBool() {
        return retweetBool;
    }
}
