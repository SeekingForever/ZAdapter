package com.zone.zadapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fuzhipeng on 16/8/26.
 */
public class Message {

    private List<String> contents = new ArrayList<>();

    public Message() {
        contents.add("你好");
        contents.add("嘿嘿");
        contents.add("你在哪?");
        contents.add("北京啊");
        contents.add("你干啥呢");
        contents.add("你说呢");
        contents.add("...");
        contents.add("Hello");
        contents.add("heihei");
        contents.add("where are you>");
        contents.add("Beijing");
        contents.add("what are you doing?");
        contents.add("you see");
        contents.add("...");

    }

    public void addHistory() {
        contents.add(0, "皑如山上雪，皎若云间月。");
        contents.add(0, "闻君有两意，故来相决绝。");
        contents.add(0, "今日斗酒会，明旦沟水头");
        contents.add(0, "躞蹀御沟上，沟水东西流。");
        contents.add(0, "凄凄复凄凄，嫁娶不须啼。");
        contents.add(0, "愿得一人心，白首不相离。");
        contents.add(0, "竹竿何袅袅，鱼尾何簁簁！");
        contents.add(0, "男儿重意气，何用钱刀为！");
    }

    public List<String> getContents() {
        return contents;
    }

    public void setContents(List<String> contents) {
        this.contents = contents;
    }
}
