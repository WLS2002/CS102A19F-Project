import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.*;
import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import javax.swing.*;

public class Internet {
    static Socket socket;
    static Boolean isConnected = false;
    static BufferedReader reader;
    static PrintWriter writer;

    static void connect(){
        try {
            socket = new Socket("WlS", 45532);
            //Related_Frame.tips("Congratulations!", "已连接至服务器！");
            isConnected = true;
            writer = new PrintWriter(socket.getOutputStream(), true);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }catch(Exception e){
            Related_Frame.tips("Error", "找不到服务器");
        }
    }

    static void Denglujiemian(){
        JFrame frame = new JFrame("登陆");
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setSize(300,300);
        frame.setLocationRelativeTo(null);
        Container c = frame.getContentPane();
        c.setLayout(null);

        JTextField name = new JTextField();
        JPasswordField password = new JPasswordField();

        JLabel label1 = new JLabel("用户名：");
        JLabel label2 = new JLabel("密码：");

        label1.setBounds(30,50,50,30);
        label2.setBounds(30,125,50,30);

        JButton button1 = new JButton("登陆");
        JButton button2 = new JButton("注册");

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String account = name.getText();
                String pass = new String(password.getPassword());
                writer.println("deng lu");
                writer.println(account+"#"+pass);

                String returnString = "";
                try {
                    returnString = reader.readLine();
                }catch(Exception e1){
                    e1.printStackTrace();
                }
                // System.out.println(returnString);
                if(returnString.equals("no found")){
                    Related_Frame.tips("Error", "没有此用户");
                }
                else if(returnString.equals("passwordWrong")){
                    Related_Frame.tips("Error", "密码不正确");
                }
                else {
                    onlineMenu();
                    frame.dispose();
                }
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ZhuCeJieMian();
            }
        });



        button1.setBounds(50,200,80,30);
        button2.setBounds(175,200,80,30);

        name.setBounds(80,50,200,30);
        password.setBounds(80,125,200,30);

        c.add(name);
        c.add(password);
        c.add(label1);
        c.add(label2);
        c.add(button1);
        c.add(button2);
        c.repaint();
    }

    static void ZhuCeJieMian(){
        JFrame frame = new JFrame("注册");
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setSize(300,300);
        frame.setLocationRelativeTo(null);
        Container c = frame.getContentPane();
        c.setLayout(null);

        JTextField name = new JTextField();
        JPasswordField password = new JPasswordField();
        JPasswordField passwordAgain = new JPasswordField();

        JButton button1 = new JButton("注册");
        button1.setBounds(100,220,80,30);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str1 = name.getText();
                String p1 = new String(password.getPassword());
                String p2 = new String(passwordAgain.getPassword());
                System.out.println(p1);
                System.out.println(p2);

                if(str1.equals("")||p1.equals("")||p2.equals(""))
                    return;

                if(!p1.equals(p2)){
                    Related_Frame.tips("Error", "前后密码不一");
                }

                else {
                    writer.println("zhu ce");
                    writer.println(str1+"#"+p1);
                    String str  = "";

                    try{
                        str = reader.readLine();
                        System.out.println(str);
                    }catch(Exception e1){
                        e1.printStackTrace();
                    }
                    if(str.equals("yi cun zai")){
                        Related_Frame.tips("Error", "此用户已存在");
                    }
                    else {
                        Related_Frame.tips("提示", "注册成功");
                        frame.dispose();
                    }
                }
            }
        });


        name.setBounds(80,25,200,30);
        password.setBounds(80,90,200,30);
        passwordAgain.setBounds(80,155,200,30);

        JLabel label1 = new JLabel("用户名：");
        JLabel label2 = new JLabel("密码：");
        JLabel label3 = new JLabel("再次输入密码：");

        label1.setBounds(30,25,50,30);
        label2.setBounds(30,90,50,30);
        label3.setBounds(0,155,100,30);


        c.add(button1);
        c.add(name);
        c.add(password);
        c.add(passwordAgain);
        c.add(label1);
        c.add(label2);
        c.add(label3);
        c.repaint();

    }

    static void ownInformation(){
        JFrame frame = new JFrame("个人信息");
        frame.setSize(200,200);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
        Container c = frame.getContentPane();
        c.setLayout(null);
        writer.println("Cha Kan Ge Ren Xin Xi");
        String name = "";
        String str = "";

        try {
            name = reader.readLine();
            str = reader.readLine();
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(str);
        int index1 = str.indexOf(" ");
        int index2 = str.indexOf(" ", index1 + 1);
        int index3 = str.indexOf(" ", index2 + 1);

        String numberOfPlay = str.substring(index1+1, index2);
        String numberOfWin = str.substring(index2+1, index3);
        String score = str.substring(index3+1).trim();

        JLabel label1 = new JLabel("用户名: " + name);
        JLabel label2 = new JLabel("总对局数："+ numberOfPlay);
        JLabel label3 = new JLabel("胜利数: " + numberOfWin);
        JLabel label4 = new JLabel("积分: " + score);

        label1.setBounds(50,15,200,30);
        label2.setBounds(50,45,200,30);
        label3.setBounds(50,75,200,30);
        label4.setBounds(50,105,200,30);

        c.add(label1);
        c.add(label2);
        c.add(label3);
        c.add(label4);

        c.repaint();
    }

    static void onlineMenu(){
        JFrame menu = new JFrame("联机对战");
        menu.setVisible(true);
        menu.setResizable(false);
        menu.setSize(  400,400 );
        menu.setLocationRelativeTo(null);    //人为安放棋盘在中央
        menu.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        Container c = menu.getContentPane();
        c.setLayout(null);

        JButton button1 = new JButton("开始匹配");
        JButton button2 = new JButton("查看个人资料");
        JButton button3 = new JButton("退出登录");

        button1.setBounds(40,50,300,50);
        button2.setBounds(40,150,300,50);
        button3.setBounds(40,250,300,50);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                writer.println("Kai Shi Pi Pei");
                menu.dispose();
                listener();
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ownInformation();
            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                writer.println("Tui Chu");
                menu.dispose();
            }
        });

        c.add(button1);
        c.add(button2);
        c.add(button3);

    }

    static void listener(){
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
                    Game.isOnlineGame = true;

                    while(true){
                        String str = reader.readLine();

                        if(str == null)
                            continue;

                        System.out.println(str);

                        switch(str){
                            case "Black": Game.onlinePlayBlack = true ; Draw.newGame();mainMenu.menu.dispose(); continue;
                            case "Red": Game.onlinePlayBlack = false; Draw.newGame();mainMenu.menu.dispose();  continue;
                            case "Withdraw":QingQiuHuiqi();continue;
                            case "Qiu He":QingQiuHeQi();continue;
                            case "Ren shu":Related_Frame.tips("游戏结束", "对方认输"); continue;
                            case "completed":SituationBar.append("对手已连线，游戏开始！\n轮到红方\n"); continue;
                            case "Tong Yi Hui Qi":Game.withDraw();continue;
                            case "Tong Yi He Qi": Related_Frame.tips("游戏结束", "双方和棋"); continue;
                            case "Refuse"    : Related_Frame.tips("服务器", "对方拒绝了你的请求");continue;
                            case "escape": Related_Frame.tips("游戏结束", "对手离开了"); SituationBar.jt.append("你的对手离开了游戏");continue;
                        }

                        Game.receiveFromInternetAndMove(str.substring(str.indexOf('M')+1));

                        Thread.sleep(500);

                    }


                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
        t.start();
    }

    static void QingQiuHuiqi(){
        JFrame frame = new JFrame("");
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setSize(200,200);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        Container c = frame.getContentPane();
        c.setLayout(null);

        JLabel label = new JLabel("对方请求悔棋" );
        label.setSize(100,50);
        label.setLocation(50,30);
        c.add(label);

        JButton button1 = new JButton("同意");
        JButton button2 = new JButton("拒绝");
        button1.setBounds(20,100,70,40);
        button2.setBounds(100,100,70,40);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                writer.println("Tong Yi Hui Qi");
                Game.withDraw();
                frame.dispose();
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                writer.println("Refuse");
                frame.dispose();
            }
        });


        c.add(button1);
        c.add(button2);
    }

    static void QingQiuHeQi(){
        JFrame frame = new JFrame("");
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setSize(200,200);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        Container c = frame.getContentPane();
        c.setLayout(null);

        JLabel label = new JLabel("对方请求和棋" );
        label.setSize(100,50);
        label.setLocation(50,30);
        c.add(label);

        JButton button1 = new JButton("同意");
        JButton button2 = new JButton("拒绝");
        button1.setBounds(20,100,70,40);
        button2.setBounds(100,100,70,40);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                writer.println("Tong Yi He Qi");
                Related_Frame.tips("游戏结束", "双方和棋");
                frame.dispose();
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                writer.println("Refuse");
                frame.dispose();
            }
        });


        c.add(button1);
        c.add(button2);
    }

    static void askForWithdraw(){
        Related_Frame.tips("服务器", "已发送请求");
        writer.println("Withdraw");

    }

    static void askForHeQi(){
        Related_Frame.tips("服务器", "已发送请求");
        writer.println("Qiu He");

    }

    public static void main(String[] args) {

        connect();
        Denglujiemian();

    }
}
