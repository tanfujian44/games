package com.kuang.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GamePanel extends JPanel implements KeyListener, ActionListener {

    //蛇的长度
    int length;
    //蛇的个数
    int[] snakeX = new int[500];
    int[] snakeY = new int[600];
    //蛇的方向
    String fx;
    //判断游戏是否开始
    boolean isStart = false;
    Timer timer = new Timer(100,this);
    //定义一个食物
    int foodX;
    int foodY;
    Random random = new Random();
    //游戏失败判定
    boolean isFail;
    //定义积分系统
    int score;

    public GamePanel() {
        init();
        //获取键盘的监听事件
        this.setFocusable(true);
        this.addKeyListener(this);
        timer.start();
    }

    //初始化
    public void init(){
        //定义蛇的初始化位置
        length = 3;
        snakeX[0] = 100; snakeY[0] = 100;
        snakeX[1] = 75;  snakeY[1] = 100;
        snakeX[2] = 50;  snakeY[2] = 100;
        fx = "R";

        //定义食物的初始位置
        foodX = 25 + 25 * random.nextInt(34);
        foodY = 25 + 25 * random.nextInt(24);
        isFail = false;
        score = 0;
    }


    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);//清屏
        this.setBackground(Color.WHITE); //设置面板的背景色
        Data.header.paintIcon(this,g,25,11); //绘制头部信息区域
        g.fillRect(25,75,850,600); //绘制游戏区域

        //画条静态的小蛇,进行判断小蛇头部位置
        if (fx.equals("R")){
            Data.right.paintIcon(this,g,snakeX[0],snakeY[0]);
        }else if (fx.equals("L")){
            Data.left.paintIcon(this,g,snakeX[0],snakeY[0]);
        }else if (fx.equals("U")){
            Data.up.paintIcon(this,g,snakeX[0],snakeY[0]);
        }else if (fx.equals("D")){
            Data.down.paintIcon(this,g,snakeX[0],snakeY[0]);
        }

        //画出蛇的身体
        for (int i = 1; i < length; i++) {
            Data.body.paintIcon(this,g,snakeX[i],snakeY[i]);
        }
        //定义食物的身体
        Data.food.paintIcon(this,g,foodX,foodY);

        //定义积分
        g.setColor(Color.WHITE);
        g.setFont(new Font("微软雅黑",Font.BOLD,20));
        g.drawString("长度:"+length,750,35);
        g.drawString("分数"+score,750,50);

        //进行游戏开始与否判断
        if (isStart == false){
            g.setColor(Color.WHITE);
            g.setFont(new Font("微软雅黑",Font.BOLD,40));
            g.drawString("按下空格开始游戏!",300,300);
        }
        //游戏失败判定
        if (isFail){
            g.setColor(Color.RED);
            g.setFont(new Font("微软雅黑",Font.BOLD,40));
            g.drawString("游戏失败!请按空格重新开始游戏",200,300);
        }
    }

    //键盘按下事件
    @Override
    public void keyPressed(KeyEvent e) {
        //获取键盘按下的键
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_SPACE){
            isStart = !isStart;
            repaint();
            init();
        }
        //进行位移判断
        if (keyCode == KeyEvent.VK_LEFT){
            fx = "L";
        }else if (keyCode == KeyEvent.VK_RIGHT){
            fx = "R";
        }else if (keyCode == KeyEvent.VK_UP){
            fx = "U";
        }else if (keyCode == KeyEvent.VK_DOWN){
            fx = "D";
        }
    }

    //定时器
    @Override
    public void actionPerformed(ActionEvent e){
        if (isStart && isFail == false){
            for (int i = length-1; i > 0; i--) {
                snakeX[i] = snakeX[i-1];
                snakeY[i] = snakeY[i-1];
            }
            //进行头部位移判断
            if (fx.equals("R")){
                snakeX[0] += 25;
                if (snakeX[0] > 850){
                    snakeX[0] = 25;
                }
            }else if (fx.equals("L")){
                snakeX[0] -= 25;
                if (snakeX[0] < 25){
                    snakeX[0] = 850;
                }
            }else if (fx.equals("U")){
                snakeY[0] -= 25;
                if (snakeY[0] < 75){
                    snakeY[0] = 600;
                }
            }else if (fx.equals("D")){
                snakeY[0] += 25;
                if (snakeY[0] > 650){
                    snakeY[0] = 75;
                }
            }
            //进行判断，如果小蛇吃到了食物就长度增加1
            if (snakeX[0] == foodX && snakeY[0] == foodY){
                length ++;
                score += 10;
                //重新随机生成食物
                foodX = 25 + 25 * random.nextInt(34);
                foodY = 25 + 25 * random.nextInt(22);
            }
            //进行判断，如果小蛇头部与身体相撞
            for (int i =length-1; i > 0; i--) {
                if (snakeX[0] == snakeX[i] && snakeY[0] == snakeY[i]){
                    isFail = true;
                }
            }
            //刷新页面
            repaint();
        }
        timer.start();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
