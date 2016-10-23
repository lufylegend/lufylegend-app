# lufylegend-Android

##游戏目录
app/src/main/assets/game/

##如何调用java来播放音频文件
1，将wav,mp3文件放入app/src/main/res/raw中，所有音频文件名用小写

2，打开app/src/main/java/com/lufylegend/game/Lufylegend.java，将音频文件列表依次添加到soundInit函数中的相应位置

3，js中使用LPlugin.playSE(name)来播放wav文件，使用LPlugin.playBGM(name)来播放mp3文件

##app名称
修改app/src/main/res/values/strings.xml文件

##app横竖屏设置
修改AndroidManifest.xml文件的android:screenOrientation属性

##扩展
app/src/main/java/com/lufylegend/game/Lufylegend.java 中添加java函数，js中通过LPlugin.函数名()直接调用，例如：
###java代码
    @JavascriptInterface
    public void pring(String message){
        Log.d("print",message);
    }
###js代码
    LPlugin.pring("测试");