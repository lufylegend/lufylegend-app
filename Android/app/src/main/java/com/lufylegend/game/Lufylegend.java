package com.lufylegend.game;

import android.app.Activity;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.HashMap;

/**
 * Created by lubin.zhang on 16/10/23.
 */
public class Lufylegend {
    public static String gamePath;
    public static WebView myWebView;
    public static Lufylegend instance = null;
    private String purchaseParams;
    private Activity context;
    private MediaPlayer mediaPlayer;
    private SoundPool soundPool;
    private AudioAttributes audioAttributes;
    private HashMap<String, Integer> soundIds = new HashMap<String, Integer>();
    public Lufylegend(Activity context){
        this.context = context;
        soundInit();
    }
    public static void initialize(String path, Activity activity){
        Lufylegend.gamePath = path;
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        activity.requestWindowFeature(Window.FEATURE_NO_TITLE);
        activity.setContentView(R.layout.activity_main);
        WebView myWebView = (WebView)activity.findViewById(R.id.webView1);
        Lufylegend.myWebView = myWebView;
        myWebView.getSettings().setJavaScriptEnabled(true);
        Lufylegend.instance = new Lufylegend(activity);
        myWebView.addJavascriptInterface(Lufylegend.instance, "LPlugin");
        myWebView.setWebViewClient(new WebViewClient());
        String url = String.format("%sindex.html", String.format("file:///android_asset/%s", path));
        myWebView.loadUrl(url);
    }
    @JavascriptInterface
    public void playSE(String name){
        playSE(name, 1);
    }
    @JavascriptInterface
    public void playSE(String name, int volume){
        soundPool.play(soundIds.get(name.toLowerCase()), 1.0f, 1.0f, 0, 0, 1);
    }
    @JavascriptInterface
    public void playBGM(String name){
        playBGM(name, 1);
    }
    @JavascriptInterface
    public void playBGM(String name, int volume){
        if(mediaPlayer != null){
            mediaPlayer.stop();
        }
        mediaPlayer = MediaPlayer.create(context, soundIds.get(name.toLowerCase()));
        mediaPlayer.start();
    }
    private void soundInit(){
        HashMap<Integer, String> sounds = new HashMap<Integer, String>();
        //.wav
        //sounds.put(R.raw.se_block, "se_block");
        if(sounds.size() > 0){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                audioAttributes = new AudioAttributes.Builder()
                        .setUsage(AudioAttributes.USAGE_GAME)
                        .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
                        .build();
                soundPool = new SoundPool.Builder()
                        .setAudioAttributes(audioAttributes)
                        .setMaxStreams(sounds.size())
                        .build();
            }
            else {
                soundPool = new SoundPool(sounds.size(), AudioManager.STREAM_MUSIC, 0);
            }
        }
        for (int id : sounds.keySet()) {
            int sound = soundPool.load(context, id, 1);
            soundIds.put(sounds.get(id), sound);
        }

        //.mp3
        //soundIds.put("map", R.raw.map);
    }
}
