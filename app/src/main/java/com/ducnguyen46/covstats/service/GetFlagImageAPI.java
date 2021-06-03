package com.ducnguyen46.covstats.service;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetFlagImageAPI extends AsyncTask<String, Void, Bitmap> {

    private ImageView imageView;
    public GetFlagImageAPI(ImageView image){
        this.imageView = image;
    }

    @Override
    protected Bitmap doInBackground(String... urls) {
        HttpURLConnection httpURLConnection;
        URL url;

        try {
            url = new URL(urls[0]);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.connect();

            Bitmap imageBitmap = BitmapFactory.decodeStream(httpURLConnection.getInputStream());
            return  imageBitmap;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        imageView.setImageBitmap(bitmap);
    }
}
