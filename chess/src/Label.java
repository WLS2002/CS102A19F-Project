import javax.swing.*;
import java.awt.*;
//label 用于创建图片面板，主要是背景棋盘
//缩放图片功能很厉害呀，并不只是用于棋盘制作好吧
//这段代码我会背下来的

public class Label extends JLabel {
    private ImageIcon picture;
    private int width,height;

    Label(String path,Dimension size){
        picture = new ImageIcon(path);
        width = size.width;
        height = size.height;
        this.setSize(size);
        repaint();
    }

    void Change(String path,Dimension size){
        picture = new ImageIcon(path);
        width = size.width;
        height = size.height;
        this.setSize(size);
        repaint();

    }



    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(picture.getImage(),0,0,width,height,null);

    }
}
