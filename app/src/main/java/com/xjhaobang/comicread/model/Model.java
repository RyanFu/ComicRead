package com.xjhaobang.comicread.model;

import android.util.Log;

import com.xjhaobang.comicread.utils.OkHttpResultCallback;
import com.xjhaobang.comicread.utils.OkHttpUtil;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.UnsupportedEncodingException;

import okhttp3.Call;

import static android.content.ContentValues.TAG;

/**
 * Created by PC on 2017/9/26.
 */

public class Model {

    public Model() {
        OkHttpUtil.getInstance().getAsync("http://ac.qq.com/Comic/all/state/pink/search/time/vip/1/page/1", new OkHttpResultCallback() {
            @Override
            public void onError(Call call, Exception e) {
                Log.e(TAG, "onError: " );
            }

            @Override
            public void onResponse(byte[] bytes) {
                try {
                    String s = new String(bytes,"utf-8");
                    Document doc = Jsoup.parse(s);
                    Elements e = doc.select("div.ret-works-cover");
                    for (Element e1 : e){
                        Element e2 = e1.getElementsByTag("a").first();
                        Log.e(TAG, "run: " + e2.attr("title"));
                        Log.e(TAG, "run: " + e2.attr("href"));
                        Element e3 = e2.getElementsByTag("img").first();
                        Log.e(TAG, "run: " + e3.attr("data-original"));
                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
