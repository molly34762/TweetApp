package com.codepath.apps.restclienttemplate.models;

import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

@Parcel
public class Entities {
    public String mediaUrl;
    public String mediaType;

    public Entities() {
    }

    public static Entities fromJson(JSONObject jsonObject) throws JSONException {
        Entities entity = new Entities();
        if(jsonObject.has("media")){
            entity.mediaType = jsonObject.getJSONArray("media").getJSONObject(0).getString("type");
            entity.mediaUrl = jsonObject.getJSONArray("media").getJSONObject(0).getString("media_url");
        }else {
            entity.mediaUrl = "";
            entity.mediaType = "";
        }
        return entity;
    }

    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getMediaUrl() {
        return mediaUrl;
    }

    public String getMediaType() {
        return mediaType;
    }
}