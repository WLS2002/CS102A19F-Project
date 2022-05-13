import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Currency;

class Related_Frame {

    public static void main(String[] args) {
        tips("123", "djsda");
    }

    static void tips(String title, String s) {

        JDialog a = new JDialog();
        a.setTitle(title);
        a.setSize(100, 130);
        a.setVisible(true);
        a.setResizable(false);
        a.setLocationRelativeTo(null);
        a.getContentPane().setLayout(null);
        a.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        JButton button = new JButton();

        button.setText("确定");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                a.dispose();
            }
        });
        button.setBounds(40, 50, 70, 30);

        a.getContentPane().add(button);

        JLabel label = new JLabel(s);

        int x = label.getSize().width;

        label.setBounds((int) (65 - s.length() * 3.5), 0, 100, 50);
        a.getContentPane().add(label);
        a.getContentPane().repaint();
    }

}
class I_O  {


    static final int MAX = 10000;

    static void saveGame() {
        char[][] boardST = Tools.copyChars( Game.st );
        String MOVER = "BLACK";
        if(Game.isBlack) MOVER = "RED";
        StringBuilder s = new StringBuilder("@LAST_MOVER="+MOVER+"\n@@\n\n");
        for(int i = 1; i <= 5; i++){
            s.append(boardST[i],1,9);
            s.append('\n');
        }
        s.append("---------\n");
        for(int i = 6; i <= 10; i++){
            s.append(boardST[i],1,9);
            s.append('\n');
        }
        File file = new File("boardgame.chessboard");
        try {
            if (!file.exists()) file.createNewFile();
            FileWriter out = new FileWriter(file);
            out.write(s.toString());
            out.close();
        } catch(Exception e ){
            e.printStackTrace();
        }
    }

    static void loadGame(File file) {

        if(file == null)
            file = new File("boardgame.chessboard");

        if( !file.exists() ){
            Related_Frame.tips("Error", "File no found");
            return;
        }

        SituationBar.jt.setText("载入棋盘\n");
        String s = "";

        try {
            FileReader in = new FileReader(file);
            char[] ins = new char[MAX];
            int len =  in.read(ins);
            if(len == -1) return;
            s = new String(ins, 0, len);
        }catch(Exception e){
            e.printStackTrace();
        }

        int index1 = s.indexOf('=');
        int index2 = s.indexOf("\n");
        String LastMove = s.substring(index1+1 , index2 );
        boolean isBlack = true;
        if(LastMove.equals("BLACK")) isBlack = false;

        if(LastMove.equals("BLACK")) SituationBar.jt.append("轮到红方\n");
        else SituationBar.jt.append("轮到黑方\n");
        String board = s.substring(index2 + 4 );
       // System.out.println(board.length());

        System.out.println(board);
        System.out.println(board.length());

        char[][] st = new char[11][10];
        int x = 1;

        //读取棋盘数据
        for( int i = 1 ; i <= 50; i++){
            if( i % 10 == 0 ) {x++; continue;}
            st[x][i - (x-1) *10] = board.charAt(i);
        }
        for(int i = 61; i <= 110; i++){
            if( i % 10 == 0 ) {x++; continue;}
            st[x][i - (x) *10] = board.charAt(i);
        }

        Game.st = st.clone();               //替换棋盘
        Game.isBlack = isBlack;     //替换先手
        Game.save.clear();   //清空悔棋库
        Game.steps.clear();
        Game.save.add(Tools.copyChars(st));
        Game.isKeepPiece = false;
        Game.choosePiecePOS = null;
        Draw.repaint();             //  重新绘制

    }

    static void out(){
        StringBuilder sbuilder = new StringBuilder("@TOTAL_STEP="+Game.steps.size()+"\n@@\n\n");
        for(int i = 0; i < Game.steps.size(); i++){
            int[] x = Game.steps.get(i);
            if(i%2==0)
            sbuilder.append(String.format("%d %d %d %d\n",10 - x[1],11 -  x[0],10 - x[3],11 - x[2]));
            else sbuilder.append(String.format("%d %d %d %d\n", x[1], x[0], x[3],x[2]));
        }
        File file = new File("Sequence.chessmoveseq");
        try{
            if(!file.exists()) file.createNewFile();
            FileWriter out = new FileWriter(file);
            BufferedWriter e = new BufferedWriter(out);
            out.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    static void in(File file){
        if(file == null)
            file = new File("Sequence.chessmoveseq");
        if( !file.exists() ){
            Related_Frame.tips("Error"," File no found");
            return;
        }

        String s = "";
        try {

            FileReader in = new FileReader(file);
            char[] ins = new char[MAX];
            int len =  in.read(ins);
            if(len == -1) return;
            s = new String(ins, 0, len);
        }catch(Exception e){
            e.printStackTrace();
        }
        if(!s.endsWith("\n"))
            s = s+'\n';
        int index1 = s.indexOf('=');
        int index2 = s.indexOf('\n');

        int total = Integer.parseInt(s.substring(index1+1,index2));

        s = s.substring(index2+5);

        int x[][] = new int[total+1][4];

        boolean isBlack = Game.isBlack;

        for(int i = 1; i <= total; i++){
            int index;
            String str = s.substring(0,s.indexOf('\n'));
            index = s.indexOf(' ');
            x[i][1] = Integer.parseInt(s.substring(0,index));
            x[i][0] = Integer.parseInt(s.substring(index+1 ,s.indexOf(' ', index+1)));
            index = s.indexOf(' ', index+1);
            x[i][3] = Integer.parseInt(s.substring(index+1 ,s.indexOf(' ', index+1)));
            index = s.indexOf(' ', index+1);
            x[i][2] = Integer.parseInt(s.substring(index+1,s.indexOf('\n')));
           /* if( !isBlack ){
                x[i][0] = 11 - x[i][0];
                x[i][1] = 10 - x[i][1];
                x[i][2] = 11 - x[i][2];
                x[i][3] = 10 - x[i][3];
            }*/
            s = s.substring(s.indexOf('\n') + 1);
            isBlack = !isBlack;

        }

        for(int i = 1; i <= total; i++){
            System.out.printf("%d %d %d %d\n", x[i][0], x[i][1], x[i][2], x[i][3]);
        }

        Game.steps.clear();
        Game.save.clear();


        StringBuilder errors = new StringBuilder();

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {

                try{

                    int step = 1;

                    while(step<=total) {

                        //Thread.sleep(1000);
                        int x0 = x[step][0];
                        int y0 = x[step][1];
                        int x1 = x[step][2];
                        int y1 = x[step][3];

                        if( !Game.isBlack ){
                        x0 = 11 - x0;
                        y0 = 10 - y0;
                        x1 = 11 - x1;
                        y1 = 10 - y1;
                        }

                        System.out.println(Game.isBlack+"   "+step);
                        if(!( 1<=x0&&x0<=10 && 1<=x1&&x1<=10 && 1<=y0&&y0<=9 && 1 <=y1&&y1<=9)){
                            errors.append("第").append(step).append("步：Position Out Of Range\n");
                            //System.out.println(step+"位置在边界外");
                            step++;
                            continue;

                        }

                        if( Game.isBlack&&Game.kindOfClick(x0, y0,Tools.copyChars(Game.st))!=1 ){
                                errors.append("第").append(step).append("步：Invalid From Position\n");
                            //System.out.println(Game.st[x0][y0]);
                            //System.out.println(step+"非本方目标类型棋子");
                            step++;
                            continue;
                        }

                        if( !Game.isBlack&&Game.kindOfClick(x0, y0,Tools.copyChars(Game.st))!=-1 ){
                            errors.append("第").append(step).append("步：Invalid From Position\n");
                        //System.out.println(Game.st[x0][y0]);
                        //System.out.println(step+"非本方目标类型棋子");
                            step++;
                            continue;
                        }

                        if( (Game.kindOfClick(x1, y1, Tools.copyChars(Game.st)) == 1&&Game.isBlack)){
                            errors.append("第").append(step).append("步：Invalid To Position\n");
                            //System.out.println(step+"目标位置存在本方棋子");
                            step++;
                            continue;
                        }

                        if( (Game.kindOfClick(x1, y1, Tools.copyChars(Game.st)) == -1&&!Game.isBlack)){
                            errors.append("第").append(step).append("步：Invalid To Position\n");
                            //System.out.println(step+"目标位置存在本方棋子");
                            step++;
                            continue;
                        }

                        if( !Game.moveRule(new int[]{x0, y0}, new int[]{x1, y1}, Tools.copyChars(Game.st)) ){
                            errors.append("第").append(step).append("步：Invalid Move Pattern\n");
                           // System.out.println(step+"棋子移动非法");
                            step++;
                            continue;
                        }


                        Game.move(x0, y0, x1, y1);
                        Thread.sleep(500);
                        System.out.println(step);
                        step++;

                    }

                    SituationBar.jt.append(errors.toString());
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
        t.start();
        System.out.println(errors);



    }

    static void openFile(JFrame frame) {
        JFileChooser jfc = new JFileChooser(new File("Official_test_case"));
        jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        jfc.showDialog(new JLabel(), "选择");
        File file = jfc.getSelectedFile();
        String fileName = file.getName();
        if(fileName.endsWith(".chessboard")){
            if(boardCheck(file)) {
                Draw.main(null);
                I_O.loadGame(file);
                frame.dispose();
            }
        }
        else if(fileName.endsWith(".chessmoveseq")){
            I_O.in(file);
        }
        else {
            Related_Frame.tips("错误","文件错误！");
        }

    }

    static boolean boardCheck(File file){
        String s = "";

        try {
            FileReader in = new FileReader(file);
            char[] ins = new char[MAX];
            int len =  in.read(ins);
            if(len == -1) return false;
            s = new String(ins, 0, len);
        }catch(Exception e){
            e.printStackTrace();
        }


        s = Tools.DeleteDenotations(s);

        if(!s.endsWith("\n"))
            s = s+'\n';
        //System.out.println(s);

        if(!s.contains("-")){
            Related_Frame.tips("Error", "楚河汉界缺失");
            return false;
        }

        int index = s.indexOf("@@\n")+4;
        String s1 = s.substring(index);

        System.out.println(s1);

        int numberOfEnter = 0;

        for(int i = 0; i < s1.length(); i++){
            if(s1.charAt(i) == '\n')
                numberOfEnter++;
        }

        int[] lengthOfEachLine = new int[numberOfEnter];

        int flag = 0;
        int numberOfError = 0;
        for(int i = 0; i < s1.length(); i++){
            if(s1.charAt(i) == '\n'){
                if(lengthOfEachLine[flag]!=9)
                    numberOfError++;
                flag++;
            }
            else lengthOfEachLine[flag]++;

        }

        if(numberOfError == 11){
            Related_Frame.tips("Error", "棋局长宽错误");
            return false;
        }

        if( numberOfError != 0){
            Related_Frame.tips("Error", "空白符丢失");
            return false;
        }

        if(numberOfEnter!=11) {
            Related_Frame.tips("Error", "棋局长宽错误");
            return false;
        }

        if(!Tools.checkBoard(s1)){
            Related_Frame.tips("Error", "棋子数量错误");
            return false;
        }

        return true;
    }

    public static void main(String[] args) {
        boardCheck(new File("Official_test_case/Task2/demo1.chessboard"));
    }
}
