package com.yahoo.netram.instagramviewer;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.MessageFormat;
import java.util.ArrayList;


public class PhotosActivity extends ActionBarActivity {

    private static final String instagramClientId = "347061eaa59e426c8e45fbb99cd49e76";

    private static final String instagramUrlFormat = "https://api.instagram.com/v1/media/popular?client_id={0}";

    //json node names for extracting image data from instagram API response
    public static final String DATA = "data";
    public static final String IMAGES = "images";
    public static final String STANDARD_RESOLUTION = "standard_resolution";
    public static final String CAPTION = "caption";
    public static final String TEXT = "text";
    public static final String URL = "url";
    public static final String HEIGHT = "height";
    public static final String USER = "user";
    public static final String USERNAME = "username";
    public static final String PROFILE_PICTURE = "profile_picture";
    public static final String LIKES = "likes";
    public static final String COUNT = "count";
    public static final String CREATED_TIME = "created_time";

    private ArrayList<InstagramPhoto> photos;
    private InstagramPhotosAdapter photosAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos);

        fetchPopularPhotos();
    }

    private void fetchPopularPhotos() {

        photos = new ArrayList<InstagramPhoto>();
        photosAdapter = new InstagramPhotosAdapter(this, photos);

        ListView lvPhotos = (ListView) findViewById(R.id.lvPhotos);
        lvPhotos.setAdapter(photosAdapter);

        String popularImagesAPIUrl = MessageFormat.format(instagramUrlFormat, instagramClientId);
        AsyncHttpClient httpClient = new AsyncHttpClient();
        httpClient.get(popularImagesAPIUrl, new JsonHttpResponseHandler() {

            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                Log.d("Instagram Response:", response.toString());

                JSONArray photosJSONArray = null;

                try {
                    photosJSONArray = response.getJSONArray(DATA);

                    for (int i = 0; i < photosJSONArray.length(); i++) {
                        JSONObject photoJSONObject = photosJSONArray.getJSONObject(i);

                        InstagramPhoto instagramPhoto = getPhotoDataFromJson(photoJSONObject);

                        Log.d("Photo object:", instagramPhoto.toString());

                        photos.add(instagramPhoto);

                    }
                    photosAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_photos, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private InstagramPhoto getPhotoDataFromJson(JSONObject photoJSONObject) {
        InstagramPhoto instagramPhoto = new InstagramPhoto();
        try {
            instagramPhoto.setImageUrl(photoJSONObject.getJSONObject(IMAGES).getJSONObject(STANDARD_RESOLUTION).getString(URL));
            if (photoJSONObject.getJSONObject(CAPTION) != null) {
                instagramPhoto.setCaption(photoJSONObject.getJSONObject(CAPTION).getString(TEXT));
            }
            instagramPhoto.setHeight(photoJSONObject.getJSONObject(IMAGES).getJSONObject(STANDARD_RESOLUTION).getInt(HEIGHT));
            instagramPhoto.setUserName(photoJSONObject.getJSONObject(USER).getString(USERNAME));
            instagramPhoto.setNumLikes(photoJSONObject.getJSONObject(LIKES).getInt(COUNT));
            instagramPhoto.setUserImageUrl(photoJSONObject.getJSONObject(USER).getString(PROFILE_PICTURE));
            instagramPhoto.setCreateTime(Long.valueOf(photoJSONObject.getString(CREATED_TIME)));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return instagramPhoto;
    }
}
