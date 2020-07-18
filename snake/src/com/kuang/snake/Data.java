package com.kuang.snake;

import javax.swing.*;
import java.net.URL;
import java.util.Date;

//用于存放外部数据
public class Data {

    //头部
    public static URL headerURL = Date.class.getResource("/statics/header.png");
    public static ImageIcon header = new ImageIcon(headerURL);

    //头部的图片
    public static URL upURL = Date.class.getResource("/statics/up.png");
    public static ImageIcon up = new ImageIcon(upURL);

    public static URL downURL = Date.class.getResource("/statics/down.png");
    public static ImageIcon down = new ImageIcon(downURL);

    public static URL leftURL = Date.class.getResource("/statics/left.png");
    public static ImageIcon left = new ImageIcon(leftURL);

    public static URL rightURL = Date.class.getResource("/statics/right.png");
    public static ImageIcon right = new ImageIcon(rightURL);

    public static URL bodyURL = Date.class.getResource("/statics/body.png");
    public static ImageIcon body = new ImageIcon(bodyURL);

    public static URL foodURL = Date.class.getResource("/statics/food.png");
    public static ImageIcon food = new ImageIcon(foodURL);


}
