/*import java.awt.*;
import java.util.ArrayList;

//数据：面板大小

class Tools {

    static void printBoard(char[][] a ){
        for(int i = 1 ; i <= 10; i++){
            for( int j = 1; j <= 9; j++)
                System.out.print(a[i][j]);
            System.out.println();
        }
    }

    static boolean checkBoard(String board){
        if(board.length()!=111)
            return false;
        int     C = 0,
                A = 0,
                G = 0,
                N = 0,
                S = 0,
                H = 0,
                E = 0,
                c = 0,
                a = 0,
                g = 0,
                n = 0,
                s = 0,
                h = 0,
                e = 0;

        for(int i = 0; i < 111; i++){
                switch(board.charAt(i)){
                    case 'C' :C++; break;
                    case 'H' :H++; break;
                    case 'E' :E++; break;
                    case 'A' :A++; break;
                    case 'G' :G++; break;
                    case 'N' :N++; break;
                    case 'S' :S++; break;
                    case 'c' :c++; break;
                    case 'h' :h++; break;
                    case 'e' :e++; break;
                    case 'a' :a++; break;
                    case 'g' :g++; break;
                    case 'n' :n++; break;
                    case 's' :s++; break;
                }
            }
        if(C>2||c>2||a>2||A>2||e>2||E>2||S>5||s>5||G>1||g>1||H>2||h>2||n>2||N>2)
            return false;
        return true;
    }

    static char[][] copyChars( char[][] a ){
        char[][] b = new char[11][10];
        for(int i = 1; i <= 10; i++){
            System.arraycopy(a[i], 1, b[i], 1, 9);
        }
        return b;
    }

    static String translateChessBoard(char[][] a, boolean isBlack){
        StringBuilder str = new StringBuilder();
        if(isBlack)
            str.append("B/");
        else str.append("R/");
        for(int i = 1; i <= 10; i++){
            for(int j = 1; j <= 9; j++){
                str.append(a[i][j]);
            }
            str.append('/');
        }

        return str.toString();
    }

    static char[][] mirror (char[][] a){
        char[][] b = copyChars(a);
        char temp;
        for(int i = 1; i <= 10; i++){
            for(int j = 1; j <= 4; j++){
                temp = b[i][j];
                b[i][j] = b[i][10 - j];
                b[i][10 - j] = temp;
            }
        }
        return b;
    }

}

class SIZE  {

    static final Dimension SMALL = new Dimension(428,483);
    static final Dimension MEDIUM = new Dimension(600,676);
    static final Dimension LARGE = new Dimension(720,811);

    static final Dimension PIECE_LARGE = new Dimension(77,77);
    static final Dimension PIECE_MEDIUM = new Dimension(65,65);
    static final Dimension PIECE_SMALL = new Dimension(50,50);

}

//数据：文件路径
class PATH {

    static int soundSwitch = 0; // 0 on  1  off
    static final String LJDZDJ = "F:/Java/Assignment/project/Chess/资源/按钮s/联机对战点击.png";
    static final String LJDZXZ = "F:/Java/Assignment/project/Chess/资源/按钮s/联机对战选中.png";
    static final String LJDZ = "F:/Java/Assignment/project/Chess/资源/按钮s/联机对战.png";
    static final String ZRCDDJ = "F:/Java/Assignment/project/Chess/资源/按钮s/载入存档点击.png";
    static final String ZRCDXZ = "F:/Java/Assignment/project/Chess/资源/按钮s/载入存档选中.png";
    static final String ZRCD = "F:/Java/Assignment/project/Chess/资源/按钮s/载入存档.png";
    static final String ZRQPDJ = "F:/Java/Assignment/project/Chess/资源/按钮s/载入棋谱点击.png";
    static final String ZRQPXZ = "F:/Java/Assignment/project/Chess/资源/按钮s/载入棋谱选中.png";
    static final String ZRQP = "F:/Java/Assignment/project/Chess/资源/按钮s/载入棋谱.png";
    static final String RJDZDJ = "F:/Java/Assignment/project/Chess/资源/按钮s/人机对战点击.png";
    static final String RJDZXZ = "F:/Java/Assignment/project/Chess/资源/按钮s/人机对战选中.png";
    static final String RJDZ = "F:/Java/Assignment/project/Chess/资源/按钮s/人机对战.png";
    static final String RRDZDJ = "F:/Java/Assignment/project/Chess/资源/按钮s/人人对战点击.png";
    static final String RRDZXZ = "F:/Java/Assignment/project/Chess/资源/按钮s/人人对战选中.png";
    static final String RRDZ = "F:/Java/Assignment/project/Chess/资源/按钮s/人人对战.png";
    static final String ChinaChess = "F:/Java/Assignment/project/Chess/资源/中国象棋.png";
    static final String panelBackGround = "F:/Java/Assignment/project/Chess/资源/panelBG.jpg";
    static final String BackGround = "F:/Java/Assignment/project/Chess/资源/背景.jpg";
    static final String BLACK_H = "F:/Java/Assignment/project/Chess/资源/棋子/skin1/黑马.png";
    static final String BLACK_C = "F:/Java/Assignment/project/Chess/资源/棋子/skin1/黑车.png";
    static final String BLACK_E = "F:/Java/Assignment/project/Chess/资源/棋子/skin1/黑象.png";
    static final String BLACK_G = "F:/Java/Assignment/project/Chess/资源/棋子/skin1/黑将.png";
    static final String BLACK_N = "F:/Java/Assignment/project/Chess/资源/棋子/skin1/黑炮.png";
    static final String BLACK_S = "F:/Java/Assignment/project/Chess/资源/棋子/skin1/黑卒.png";
    static final String BLACK_A = "F:/Java/Assignment/project/Chess/资源/棋子/skin1/黑士.png";
    static final String RED_H = "F:/Java/Assignment/project/Chess/资源/棋子/skin1/红马.png";
    static final String RED_C = "F:/Java/Assignment/project/Chess/资源/棋子/skin1/红车.png";
    static final String RED_E = "F:/Java/Assignment/project/Chess/资源/棋子/skin1/红相.png";
    static final String RED_G = "F:/Java/Assignment/project/Chess/资源/棋子/skin1/红帅.png";
    static final String RED_N = "F:/Java/Assignment/project/Chess/资源/棋子/skin1/红炮.png";
    static final String RED_S = "F:/Java/Assignment/project/Chess/资源/棋子/skin1/红兵.png";
    static final String RED_A = "F:/Java/Assignment/project/Chess/资源/棋子/skin1/红仕.png";
    static final String BOARD_1 = "F:/Java/Assignment/project/Chess/资源/棋盘1.jpg";
    static final String BOARD_2 = "F:/Java/Assignment/project/Chess/资源/棋盘2.jpg";
    static final String PRE_MOVE = "F:/Java/Assignment/project/Chess/资源/预选边框.png";
    static final String HIDE = "F:/Java/Assignment/project/Chess/资源/透明.png";
    static final String SELECTED = "F:/Java/Assignment/project/Chess/资源/test2 - 副本.png";
    static final String SETTINGS = "F:/Java/Assignment/project/Chess/资源/设置.png";
    static final String SAVE = "F:/Java/Assignment/project/Chess/资源/存档.png";
    static final String[] SoundSwitch = {"F:/Java/Assignment/project/Chess/资源/声音.png",
                                           "F:/Java/Assignment/project/Chess/资源/静音.png"};

    static final String[] SOUNDS = {"F:/Java/Assignment/project/Chess/资源/sound/静音.mp3", //0
                                    "F:/Java/Assignment/project/Chess/资源/sound/Move.mp3", //1
                                    "F:/Java/Assignment/project/Chess/资源/sound/Eat.mp3", //2
                                      "F:/Java/Assignment/project/Chess/资源/sound/将军！.mp3", //3
                                      "F:/Java/Assignment/project/Chess/资源/sound/王小龙.mp3", //4
                                      "F:/Java/Assignment/project/Chess/资源/sound/林俊杰 - 对的时间点.mp3", //5
                                      "F:/Java/Assignment/project/Chess/资源/sound/憨憨.mp3", //6
                                      "F:/Java/Assignment/project/Chess/资源/sound/BGM1.mp3", //7
                                      "F:/Java/Assignment/project/Chess/资源/sound/选中.mp3",//8
                                      "F:/Java/Assignment/project/Chess/资源/sound/将军.mp3",//9
                                      "F:/Java/Assignment/project/Chess/资源/sound/BGM2.mp3",//10
                                      "F:/Java/Assignment/project/Chess/资源/sound/BGM3.mp3",//11
                                      "F:/Java/Assignment/project/Chess/资源/sound/悔棋.mp3",//12
                                      "F:/Java/Assignment/project/Chess/资源/sound/叮.mp3",//13
                                    "F:/Java/Assignment/project/Chess/资源/sound/按钮点击.mp3", //14
                                    "F:/Java/Assignment/project/Chess/资源/sound/按钮选中.mp3",//15
                                    "F:/Java/Assignment/project/Chess/资源/sound/jeer.mp3", //16
                                     "F:/Java/Assignment/project/Chess/资源/sound/退回大厅.mp3"}; // 17
}

//数据：棋盘关键点位置
/*原始大小：
    棋盘1：775*871
    棋盘2：


class POSITION {


    int x;
    int y;
    private static final int[][] BOARD_1 = {
            {54, 64}, {137, 64}, {220, 64}, {305, 64}, {388, 64}, {472, 64}, {555, 64}, {637, 64}, {721, 64},
            {54, 146}, {137, 146}, {220, 146}, {305, 146}, {388, 146}, {472, 146}, {555, 146}, {637, 146}, {721, 146},
            {54, 229}, {137, 229}, {220, 229}, {305, 229}, {388, 229}, {472, 229}, {555, 229}, {637, 229}, {721, 229},
            {54, 313}, {137, 313}, {220, 313}, {305, 313}, {388, 313}, {472, 313}, {555, 313}, {637, 313}, {721, 313},
            {54, 398}, {137, 398}, {220, 398}, {305, 398}, {388, 398}, {472, 398}, {555, 398}, {637, 398}, {721, 398},
            {54, 478}, {137, 478}, {220, 478}, {305, 478}, {388, 478}, {472, 478}, {555, 478}, {637, 478}, {721, 478},
            {54, 562}, {137, 562}, {220, 562}, {305, 562}, {388, 562}, {472, 562}, {555, 562}, {637, 562}, {721, 562},
            {54, 646}, {137, 646}, {220, 646}, {305, 646}, {388, 646}, {472, 646}, {555, 646}, {637, 646}, {721, 646},
            {54, 729}, {137, 729}, {220, 729}, {305, 729}, {388, 729}, {472, 729}, {555, 729}, {637, 729}, {721, 729},
            {54, 809}, {137, 809}, {220, 809}, {305, 809}, {388, 809}, {472, 809}, {555, 809}, {637, 809}, {721, 809}};

    POSITION[][] POS = new POSITION[11][10];

    private POSITION(int x, int y) {
        this.x = x;
        this.y = y;
    }

    POSITION(Dimension size) {
        int number = 0;
        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 9; j++) {
                POS[i][j] = new POSITION(BOARD_1[number][0] * size.width / 775, BOARD_1[number][1] * size.height / 871);
                number++;

            }
        }
    }
}

class SETTINGS{

    static Dimension boardSize = SIZE.LARGE;
    static Dimension pieceSize = SIZE.PIECE_LARGE;
    static String BLACK_H = "F:/Java/Assignment/project/Chess/资源/棋子/skin1/黑马.png";
    static String BLACK_C = "F:/Java/Assignment/project/Chess/资源/棋子/skin1/黑车.png";
    static String BLACK_E = "F:/Java/Assignment/project/Chess/资源/棋子/skin1/黑象.png";
    static String BLACK_G = "F:/Java/Assignment/project/Chess/资源/棋子/skin1/黑将.png";
    static String BLACK_N = "F:/Java/Assignment/project/Chess/资源/棋子/skin1/黑炮.png";
    static String BLACK_S = "F:/Java/Assignment/project/Chess/资源/棋子/skin1/黑卒.png";
    static String BLACK_A = "F:/Java/Assignment/project/Chess/资源/棋子/skin1/黑士.png";
    static String RED_H = "F:/Java/Assignment/project/Chess/资源/棋子/skin1/红马.png";
    static String RED_C = "F:/Java/Assignment/project/Chess/资源/棋子/skin1/红车.png";
    static String RED_E = "F:/Java/Assignment/project/Chess/资源/棋子/skin1/红相.png";
    static String RED_G = "F:/Java/Assignment/project/Chess/资源/棋子/skin1/红帅.png";
    static String RED_N = "F:/Java/Assignment/project/Chess/资源/棋子/skin1/红炮.png";
    static String RED_S = "F:/Java/Assignment/project/Chess/资源/棋子/skin1/红兵.png";
    static String RED_A = "F:/Java/Assignment/project/Chess/资源/棋子/skin1/红仕.png";
    static String boardSkin = PATH.BOARD_1;

}

class P_INFORMATION{

    final static char[][] INITIAL = {   {},
            {' ', 'C', 'H', 'E', 'A', 'G', 'A', 'E', 'H', 'C'},
            {' ', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
            {' ', '.', 'N', '.', '.', '.', '.', '.', 'N', '.'},
            {' ', 'S', '.', 'S', '.', 'S', '.', 'S', '.', 'S'},
            {' ', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
            {' ', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
            {' ', 's', '.', 's', '.', 's', '.', 's', '.', 's'},
            {' ', '.', 'n', '.', '.', '.', '.', '.', 'n', '.'},
            {' ', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
            {' ', 'c', 'h', 'e', 'a', 'g', 'a', 'e', 'h', 'c'}  };


}

/*
                   {' ', 'C', 'H', 'E', 'A', 'G', 'A', 'E', 'H', 'C'},
            {' ', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
            {' ', '.', 'N', '.', '.', '.', '.', '.', 'N', '.'},
            {' ', 'S', '.', 'S', '.', 'S', '.', 'S', '.', 'S'},
            {' ', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
            {' ', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
            {' ', 's', '.', 's', '.', 's', '.', 's', '.', 's'},
            {' ', '.', 'n', '.', '.', '.', '.', '.', 'n', '.'},
            {' ', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
            {' ', 'c', 'h', 'e', 'a', 'g', 'a', 'e', 'h', 'c'}  };
 */

import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

//数据：面板大小

class Tools {

    static void printBoard(char[][] a ){
        for(int i = 1 ; i <= 10; i++){
            for( int j = 1; j <= 9; j++)
                System.out.print(a[i][j]);
            System.out.println();
        }
    }

    static boolean checkBoard(String board){

        int     C = 0,
                A = 0,
                G = 0,
                N = 0,
                S = 0,
                H = 0,
                E = 0,
                c = 0,
                a = 0,
                g = 0,
                n = 0,
                s = 0,
                h = 0,
                e = 0;

        for(int i = 0; i < board.length(); i++){
            switch(board.charAt(i)){
                case 'C' :C++; break;
                case 'H' :H++; break;
                case 'E' :E++; break;
                case 'A' :A++; break;
                case 'G' :G++; break;
                case 'N' :N++; break;
                case 'S' :S++; break;
                case 'c' :c++; break;
                case 'h' :h++; break;
                case 'e' :e++; break;
                case 'a' :a++; break;
                case 'g' :g++; break;
                case 'n' :n++; break;
                case 's' :s++; break;
            }
        }
        if(C>2||c>2||a>2||A>2||e>2||E>2||S>5||s>5||G>1||g>1||H>2||h>2||n>2||N>2)
            return false;
        return true;
    }

    static char[][] copyChars( char[][] a ){
        char[][] b = new char[11][10];
        for(int i = 1; i <= 10; i++){
            System.arraycopy(a[i], 1, b[i], 1, 9);
        }
        return b;
    }

    static String translateChessBoard(char[][] a, boolean isBlack){
        StringBuilder str = new StringBuilder();
        if(isBlack)
            str.append("B/");
        else str.append("R/");
        for(int i = 1; i <= 10; i++){
            for(int j = 1; j <= 9; j++){
                str.append(a[i][j]);
            }
            str.append('/');
        }

        return str.toString();
    }

    static char[][] mirror (char[][] a){
        char[][] b = copyChars(a);
        char temp;
        for(int i = 1; i <= 10; i++){
            for(int j = 1; j <= 4; j++){
                temp = b[i][j];
                b[i][j] = b[i][10 - j];
                b[i][10 - j] = temp;
            }
        }
        return b;
    }

    static String DeleteDenotations(String str){
        StringBuilder returns = new StringBuilder(str);
        int index = 0;
        int index2;
        while(returns.indexOf("#")>0){
            index = returns.indexOf("#");
            index2 = returns.indexOf("\n", index);
            returns.replace(index, index2, "");
        }
        return returns.toString();

    }

    public static void main(String[] args) {
        String s = "231561354313515651#65536\n23333#1234561564651\n2315361\n#165132156\n15615";
        System.out.println(s+"\n\n\n\n\n\n");
        System.out.println(DeleteDenotations(s));
    }
}

class SIZE  {

    static final Dimension SMALL = new Dimension(428,483);
    static final Dimension MEDIUM = new Dimension(600,676);
    static final Dimension LARGE = new Dimension(720,811);

    static final Dimension PIECE_LARGE = new Dimension(77,77);
    static final Dimension PIECE_MEDIUM = new Dimension(65,65);
    static final Dimension PIECE_SMALL = new Dimension(50,50);

}

//数据：文件路径
class PATH {

    static int soundSwitch = 0; // 0 on  1  off
    static final String LJDZDJ = "资源/按钮s/联机对战点击.png";
    static final String LJDZXZ = "资源/按钮s/联机对战选中.png";
    static final String LJDZ = "资源/按钮s/联机对战.png";
    static final String ZRCDDJ = "资源/按钮s/载入存档点击.png";
    static final String ZRCDXZ = "资源/按钮s/载入存档选中.png";
    static final String ZRCD = "资源/按钮s/载入存档.png";
    static final String ZRQPDJ = "资源/按钮s/载入棋谱点击.png";
    static final String ZRQPXZ = "资源/按钮s/载入棋谱选中.png";
    static final String ZRQP = "资源/按钮s/载入棋谱.png";
    static final String RJDZDJ = "资源/按钮s/人机对战点击.png";
    static final String RJDZXZ = "资源/按钮s/人机对战选中.png";
    static final String RJDZ = "资源/按钮s/人机对战.png";
    static final String RRDZDJ = "资源/按钮s/人人对战点击.png";
    static final String RRDZXZ = "资源/按钮s/人人对战选中.png";
    static final String RRDZ = "资源/按钮s/人人对战.png";
    static final String ChinaChess = "资源/图片/中国象棋.png";
    static final String panelBackGround = "资源/图片/panelBG.jpg";
    static final String BLACK_H = "资源/棋子/黑马.png";
    static final String BLACK_C = "资源/棋子/黑车.png";
    static final String BLACK_E = "资源/棋子/黑象.png";
    static final String BLACK_G = "资源/棋子/黑将.png";
    static final String BLACK_N = "资源/棋子/黑炮.png";
    static final String BLACK_S = "资源/棋子/黑卒.png";
    static final String BLACK_A = "资源/棋子/黑士.png";
    static final String RED_H = "资源/棋子/红马.png";
    static final String RED_C = "资源/棋子/红车.png";
    static final String RED_E = "资源/棋子/红相.png";
    static final String RED_G = "资源/棋子/红帅.png";
    static final String RED_N = "资源/棋子/红炮.png";
    static final String RED_S = "资源/棋子/红兵.png";
    static final String RED_A = "资源/棋子/红仕.png";
    static final String BOARD_1 = "资源/图片/棋盘1.jpg";
    static final String BOARD_2 = "资源/图片/棋盘2.jpg";
    static final String BOARD_3 = "资源/图片/棋盘3.png";
    static final String PRE_MOVE = "资源/图片/预选边框.png";
    static final String HIDE = "资源/图片/透明.png";
    static final String SELECTED = "资源/图片/test2.png";
    //static final String SETTINGS = "资源/图片/设置.png";
    static final String[] SoundSwitch = {"资源/图片/声音.png",
            "资源/图片/静音.png"};

    static final String[] SOUNDS = {"资源/sound/静音.mp3", //0
            "资源/sound/Move.mp3", //1
            "资源/sound/Eat.mp3", //2
            "资源/sound/将军！.mp3", //3
            "资源/sound/王小龙.mp3", //4
            "资源/sound/林俊杰 - 对的时间点.mp3", //5   弃用
            "资源/sound/憨憨.mp3", //6
            "资源/sound/BGM1.mp3", //7
            "资源/sound/选中.mp3",//8
            "资源/sound/将军.mp3",//9
            "资源/sound/BGM2.mp3",//10
            "资源/sound/BGM3.mp3",//11
            "资源/sound/悔棋.mp3",//12
            "资源/sound/叮.mp3",//13
            "资源/sound/按钮点击.mp3", //14
            "资源/sound/按钮选中.mp3",//15
            "资源/sound/jeer.mp3", //16
            "资源/sound/退回大厅.mp3"}; // 17
}

//数据：棋盘关键点位置
/*原始大小：
    棋盘1：775*871
    棋盘2：

*/
class POSITION {


    int x;
    int y;
    private static final int[][] BOARD_1 = {
            {54, 64}, {137, 64}, {220, 64}, {305, 64}, {388, 64}, {472, 64}, {555, 64}, {637, 64}, {721, 64},
            {54, 146}, {137, 146}, {220, 146}, {305, 146}, {388, 146}, {472, 146}, {555, 146}, {637, 146}, {721, 146},
            {54, 229}, {137, 229}, {220, 229}, {305, 229}, {388, 229}, {472, 229}, {555, 229}, {637, 229}, {721, 229},
            {54, 313}, {137, 313}, {220, 313}, {305, 313}, {388, 313}, {472, 313}, {555, 313}, {637, 313}, {721, 313},
            {54, 398}, {137, 398}, {220, 398}, {305, 398}, {388, 398}, {472, 398}, {555, 398}, {637, 398}, {721, 398},
            {54, 478}, {137, 478}, {220, 478}, {305, 478}, {388, 478}, {472, 478}, {555, 478}, {637, 478}, {721, 478},
            {54, 562}, {137, 562}, {220, 562}, {305, 562}, {388, 562}, {472, 562}, {555, 562}, {637, 562}, {721, 562},
            {54, 646}, {137, 646}, {220, 646}, {305, 646}, {388, 646}, {472, 646}, {555, 646}, {637, 646}, {721, 646},
            {54, 729}, {137, 729}, {220, 729}, {305, 729}, {388, 729}, {472, 729}, {555, 729}, {637, 729}, {721, 729},
            {54, 809}, {137, 809}, {220, 809}, {305, 809}, {388, 809}, {472, 809}, {555, 809}, {637, 809}, {721, 809}};

    POSITION[][] POS = new POSITION[11][10];

    private POSITION(int x, int y) {
        this.x = x;
        this.y = y;
    }

    POSITION(Dimension size) {
        int number = 0;
        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 9; j++) {
                POS[i][j] = new POSITION(BOARD_1[number][0] * size.width / 775, BOARD_1[number][1] * size.height / 871);
                number++;

            }
        }
    }
}

class SETTINGS{

    static Dimension boardSize = SIZE.MEDIUM;
    static Dimension pieceSize = SIZE.PIECE_MEDIUM;
    static String BLACK_H = PATH.BLACK_H;
    static String BLACK_C = PATH.BLACK_C ;
    static String BLACK_E = PATH.BLACK_E;
    static String BLACK_G = PATH.BLACK_G;
    static String BLACK_N = PATH.BLACK_N;
    static String BLACK_S = PATH.BLACK_S;
    static String BLACK_A = PATH.BLACK_A;
    static String RED_H = PATH.RED_H;
    static String RED_C = PATH.RED_C;
    static String RED_E = PATH.RED_E;
    static String RED_G = PATH.RED_G;
    static String RED_N = PATH.RED_N;
    static String RED_S = PATH.RED_S;
    static String RED_A = PATH.RED_A;
    static String boardSkin = PATH.BOARD_1;
    static int RobotPlaysType = 1;    // 1 Black
                                      // 2 Red
                                      // 3 Both

}

class P_INFORMATION{

    final static char[][] INITIAL = {   {},
            {' ', 'C', 'H', 'E', 'A', 'G', 'A', 'E', 'H', 'C'},
            {' ', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
            {' ', '.', 'N', '.', '.', '.', '.', '.', 'N', '.'},
            {' ', 'S', '.', 'S', '.', 'S', '.', 'S', '.', 'S'},
            {' ', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
            {' ', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
            {' ', 's', '.', 's', '.', 's', '.', 's', '.', 's'},
            {' ', '.', 'n', '.', '.', '.', '.', '.', 'n', '.'},
            {' ', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
            {' ', 'c', 'h', 'e', 'a', 'g', 'a', 'e', 'h', 'c'}  };


}

/*
                   {' ', 'C', 'H', 'E', 'A', 'G', 'A', 'E', 'H', 'C'},
            {' ', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
            {' ', '.', 'N', '.', '.', '.', '.', '.', 'N', '.'},
            {' ', 'S', '.', 'S', '.', 'S', '.', 'S', '.', 'S'},
            {' ', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
            {' ', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
            {' ', 's', '.', 's', '.', 's', '.', 's', '.', 's'},
            {' ', '.', 'n', '.', '.', '.', '.', '.', 'n', '.'},
            {' ', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
            {' ', 'c', 'h', 'e', 'a', 'g', 'a', 'e', 'h', 'c'}  };

 */
