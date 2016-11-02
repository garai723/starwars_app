package com.example.garai.starwars;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;

public class AsyncGetJSONObject extends AsyncTask<String, Void, JSONObject> {

    private static final String TAG = AsyncGetJSONObject.class.getSimpleName();

    @Override
    protected JSONObject doInBackground(String... params) {
        if (params == null || params.length < 1) {
            return null;
        }

        URL url;
        try {
            url = new URL(params[0]);
        } catch (MalformedURLException e) {
            Log.e(TAG, "invalid URL : " + params[0], e);
            return null;
        }

        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Connection", "close");
            conn.setFixedLengthStreamingMode(0);

            conn.connect();

            int code = conn.getResponseCode();
            Log.d(TAG, "Responce code : " + code);

            if (code != 200) {
                Log.e(TAG, "HTTP GET Error : code=" + code);
                return null;
            }

            return new JSONObject(readContent(conn));
        } catch (IOException e) {
            Log.e(TAG, "Failed to get content : " + url, e);
            return null;
        } catch (JSONException e) {
            Log.e(TAG, "invalid JSON String", e);
            return null;
        } finally {
            if (conn != null) {
                try {
                    conn.disconnect();
                } catch (Exception ignore) {
                }
            }
        }
    }

    private String readContent(HttpURLConnection conn) throws IOException {
        String charsetName;

        String contentType = conn.getContentType();
        if (!TextUtils.isEmpty(contentType)) {
            int idx = contentType.indexOf("charset=");
            if (idx != -1) {
                charsetName = contentType.substring(idx + "charset=".length());
            } else {
                charsetName = "UTF-8";
            }
        } else {
            charsetName = "UTF-8";
        }

        InputStream is = new BufferedInputStream(conn.getInputStream());

        int length = conn.getContentLength();
        ByteArrayOutputStream os = length > 0 ? new ByteArrayOutputStream(length) : new ByteArrayOutputStream();

        byte[] buff = new byte[10240];
        int readLen;
        while ((readLen = is.read(buff)) != -1) {
            if (readLen > 0) {
                os.write(buff, 0, readLen);
            }
        }

        return new String(os.toByteArray(), charsetName);
    }

    @Override
    protected void onPostExecute(JSONObject result) {
        Log.d("JSON", String.valueOf(result));
    }
}