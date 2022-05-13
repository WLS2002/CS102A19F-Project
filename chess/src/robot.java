import javax.print.attribute.standard.PrinterMakeAndModel;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ListResourceBundle;
import java.util.Random;

public class robot {

    static final int MAX = 1000000;

    static void newRobotGame(int a) {
        Game.isRobot = true;

        if (a == 1) {
            Game.isRobotBlack = true;
            Game.isIsRobotRed = false;
        } else if (a == 2) {
            Game.isRobotBlack = false;
            Game.isIsRobotRed = true;
        } else if (a == 3) {
            Game.isRobotBlack = true;
            Game.isIsRobotRed = true;
        }
        Draw.main(null);

    }

    static int getValue(char[][] chessboard, boolean isBlack) {

        int number1 = 0;
        int number2 = 0;
        int number3 = 0;
        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 9; j++) {

                switch (chessboard[i][j]) {
                    case 'A':
                        number1 += 250;
                        break;
                    case 'G':
                        number1 += 600;
                        break;
                    case 'C':
                        number1 += 400;
                        break;
                    case 'N':
                        number1 += 325;
                        break;
                    case 'H':
                        number1 += 300;
                        break;
                    case 'E':
                        number1 += 250;
                        break;
                    case 'S':
                        number1 += 200;
                        break;
                    case 'a':
                        number1 += -250;
                        break;
                    case 'g':
                        number1 += -600;
                        break;
                    case 'c':
                        number1 += -400;
                        break;
                    case 'n':
                        number1 += -325;
                        break;
                    case 'h':
                        number1 += -300;
                        break;
                    case 's':
                        number1 += -200;
                        break;
                    case 'e':
                        number1 += -250;
                        break;
                }

                if( (Game.kindOfClick(i, j, chessboard) == 1 && !isBlack) || (Game.kindOfClick(i, j, chessboard) == -1 && isBlack)  ){
                    for(int x = 1; x <= 10; x++){
                        for(int y = 1; y <= 9; y++){
                            if(!Game.canMove(new int[]{i, j}, new int[]{x, y}, Tools.copyChars(chessboard)))
                                continue;
                            switch(chessboard[x][y]){
                             //   case 'A': number2 += -150; break;
                                case 'G': number2 += -500; break;
                                case 'C': number2 += -300; break;
                                case 'N': number2 += -200; break;
                                case 'H': number2 += -200; break;
                            //    case 'E': number2 += -150; break;
                            //    case 'S': number2 += -100; break;
                             //   case 'a': number2 += -150; break;
                                case 'g': number2 += -500; break;
                                case 'c': number2 += -300; break;
                                case 'n': number2 += -200; break;
                                case 'h': number2 += -200; break;
                             //   case 's': number2 += -100; break;
                            //    case 'e': number2 += -150; break;
                            }
                        }

                    }
                }
                if( (Game.kindOfClick(i, j, chessboard) == 1 && isBlack) || (Game.kindOfClick(i, j, chessboard) == -1 && !isBlack)  ){
                    for(int x = 1; x <= 10; x++){
                        for(int y = 1; y <= 9; y++){
                            if(!Game.canMove(new int[]{i, j}, new int[]{x, y},  Tools.copyChars(chessboard)))
                                continue;
                            switch(chessboard[x][y]){
                              //  case 'A': number3 += 75; break;
                                case 'G': number3 += 250; break;
                                case 'C': number3 += 150; break;
                                case 'N': number3 += 100; break;
                                case 'H': number3 += 100; break;
                             //   case 'E': number3 += 75; break;
                             //   case 'S': number3 += 50; break;
                             //  case 'a': number3 += 75; break;
                                case 'g': number3 += 250; break;
                                case 'c': number3 += 150; break;
                                case 'n': number3 += 100; break;
                                case 'h': number3 += 100; break;
                            //    case 's': number3 += 50; break;
                            //    case 'e': number3 += 75; break;
                            }
                        }

                    }
                }

            }

        }
        if (!isBlack)
            number1 = -number1;
     /*   System.out.println("第一阶段 子力 得"+number1+"分");
        System.out.println("第二阶段 威胁得"+number2+"分");
        System.out.println("第三阶段 叫杀得"+number3+"分");
     //   Tools.printBoard(chessboard);*/
        return number1 + number2 + number3;
    }

    static int[] selectBestStepNormal(char[][] chessboard, boolean isBlack) {
        int maxPoint = -9999;
        ArrayList<int[]> returns = new ArrayList<>();


        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 9; j++) {


                if ((isBlack && Game.kindOfClick(i, j, Tools.copyChars(chessboard)) != 1) || (!isBlack && Game.kindOfClick(i, j, Tools.copyChars(chessboard)) != -1))
                    continue;

                for (int x = 1; x <= 10; x++) {
                    for (int y = 1; y <= 9; y++) {
                        if (!Game.canMove(new int[]{i, j}, new int[]{x, y}, Tools.copyChars(chessboard)))
                            continue;
                        char[][] newChessBoard = Tools.copyChars(chessboard);

                        newChessBoard[x][y] = newChessBoard[i][j];
                        newChessBoard[i][j] = '.';

                        int value = getValue(Tools.copyChars(newChessBoard), isBlack);
                        // System.out.println(value+ "   "+i+""+j+""+x+""+y);

                        if (value > maxPoint) {
                            maxPoint = value;
                            returns.clear();
                            returns.add(new int[]{i, j, x, y});
                        } else if (value == maxPoint) {
                            returns.add(new int[]{i, j, x, y});
                        }
                    }
                }
            }
        }

        Random rand = new Random();
        return returns.get(rand.nextInt(returns.size()));
    }

    static int[] selectBestStepDeeper(char[][] chessboard, boolean isBlack) {
        int maxPoint = -9999;
        ArrayList<int[]> returns = new ArrayList<>();


        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 9; j++) {


                if ((isBlack && Game.kindOfClick(i, j, Tools.copyChars(chessboard)) != 1) || (!isBlack && Game.kindOfClick(i, j, Tools.copyChars(chessboard)) != -1))
                    continue;

                for (int x = 1; x <= 10; x++) {
                    for (int y = 1; y <= 9; y++) {
                        if (!Game.canMove(new int[]{i, j}, new int[]{x, y}, Tools.copyChars(chessboard)))
                            continue;
                        char[][] newChessBoard = Tools.copyChars(chessboard);

                        newChessBoard[x][y] = newChessBoard[i][j];
                        newChessBoard[i][j] = '.';

                        int value = swapPositionThinking(Tools.copyChars(newChessBoard), isBlack);
                        // System.out.println(value+ "   "+i+""+j+""+x+""+y);

                        if (value > maxPoint) {
                            maxPoint = value;
                            returns.clear();
                            returns.add(new int[]{i, j, x, y});
                        } else if (value == maxPoint) {
                            returns.add(new int[]{i, j, x, y});
                        }
                    }
                }
            }
        }

        Random rand = new Random();
        return returns.get(rand.nextInt(returns.size()));
    }

    static int swapPositionThinking(char[][] chessboard, boolean isBlack) {
        int minPoint = 100000;
        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 9; j++) {
                if ((!isBlack && Game.kindOfClick(i, j, Tools.copyChars(chessboard)) != 1) || (isBlack && Game.kindOfClick(i, j, Tools.copyChars(chessboard)) != -1))
                    continue;
                for (int x = 1; x <= 10; x++) {
                    for (int y = 1; y <= 9; y++) {
                        if (!Game.canMove(new int[]{i, j}, new int[]{x, y}, Tools.copyChars(chessboard)))
                            continue;
                        char[][] newChessBoard = Tools.copyChars(chessboard);

                        newChessBoard[x][y] = newChessBoard[i][j];
                        newChessBoard[i][j] = '.';

                        int value = getValue(Tools.copyChars(newChessBoard), isBlack);
                        // System.out.println(value+ "   "+i+""+j+""+x+""+y);

                        if (value < minPoint) {
                            minPoint = value;
                        }
                    }
                }
            }
        }
        return minPoint;
    }

    static int[] robotMove(char[][] chessboard, boolean isBlack, String s){
        //Tools.printBoard(Tools.mirror(chessboard));
        //先查找库存
        int[] x = robotFind( Tools.translateChessBoard( Tools.copyChars(chessboard), isBlack), isBlack);
        if(  x != null)
            return x;

        x = robotFind( Tools.translateChessBoard( Tools.copyChars(Tools.mirror(chessboard)), isBlack), isBlack); //查找镜像

        if( x != null){
            x[1] = 10 - x[1];
            x[3] = 10 - x[3];
            return x;
        }

      //  System.out.println( Arrays.toString(selectBestStep(Tools.copyChars(chessboard), isBlack)) );
        if(s.equals("Normal"))
        return selectBestStepNormal(Tools.copyChars(chessboard), isBlack);
        else return selectBestStepDeeper(Tools.copyChars(chessboard), isBlack);
    }

    static int[] robotFind(String chessboard, boolean isBlack){
        // 1 - 102 保存了棋盘的信息
        File file;
        String s = "";

        if( !isBlack )
            file= new File("资源/Robot/R.memory");
        else file = new File("资源/Robot/B.memory");

        try {
            FileReader in = new FileReader(file);
            char[] ins = new char[MAX];
            int len =  in.read(ins);
            if(len == -1) return null;
            s = new String(ins, 0, len);
        } catch(Exception e ){
            e.printStackTrace();
        }   // 读入文件

        ArrayList<int[]> a = new ArrayList<>();
        {
            while(true) {

                int index = s.indexOf(chessboard);
                if (index < 0)
                    break;

                String ints = s.substring(index + 102, s.indexOf('\n', index + 101));

                System.out.println(ints);

                int[] returnInt = new int[4];
                int index1 = ints.indexOf('/');
                int index2 = ints.indexOf('/', index1 + 1);
                int index3 = ints.indexOf('/', index2 + 1);

                returnInt[0] = Integer.parseInt(ints.substring(0, index1));
                returnInt[1] = Integer.parseInt(ints.substring(index1 + 1, index2));
                returnInt[2] = Integer.parseInt((ints.substring(index2 + 1, index3)));
                returnInt[3] = Integer.parseInt(ints.substring(index3 + 1));

                a.add(returnInt);
                s = s.substring(index + 102);

            }
        }

        if(a.size() == 0)
            return null;

        System.out.println(a.size());
        Random rand = new Random();
        return a.get(rand.nextInt(a.size()));
    }

    //static void study
    static void memorize(String a, int x0, int y0, int x1, int y1){
        File file;
        if( a.charAt(0) == 'R' )
            file= new File("Robot/R.memory");
        else file = new File("Robot/B.memory");

        a = a + x0 + '/' + y0 + '/' + x1 + '/' + y1 + '\n';

        try {
            if (!file.exists()) file.createNewFile();
            FileWriter out = new FileWriter(file, true );
            out.write(a);
            out.close();
        } catch(Exception e ){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
       // System.out.println(Tools.translateChessBoard(P_INFORMATION.INITIAL, false));
        char[][] INITIAL = {   {},
                {'.', 'C', 'H', 'E', 'A', 'G', 'A', 'E', 'H', 'C'},
                {' ', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {' ', '.', 'N', '.', '.', '.', '.', '.', 'N', '.'},
                {' ', 'S', '.', 'S', '.', 'S', '.', 'S', '.', 'S'},
                {' ', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {' ', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {' ', 's', '.', 's', '.', 's', '.', 's', '.', 's'},
                {' ', '.', 'n', '.', '.', '.', '.', '.', 'n', '.'},
                {' ', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {' ', 'c', 'h', 'e', 'a', 'g', 'a', 'e', 'h', 'c'}  };

        char[][] INITIAL2 = {   {},
                {'.', '.', 'C', 'E', 'A', 'G', 'A', 'E', 'C', '.'},
                {' ', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {' ', '.', 'N', '.', '.', '.', '.', '.', 'N', '.'},
                {' ', 'S', '.', 'S', '.', 'S', '.', 'S', '.', 'S'},
                {' ', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {' ', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {' ', 's', '.', 's', '.', 's', '.', 's', '.', 's'},
                {' ', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {' ', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {' ', 'c', 'h', 'e', 'a', 'g', 'a', 'e', 'h', 'c'}  };
        System.out.println(getValue(INITIAL, false));
      //  System.out.println(getValue(INITIAL2, true));
    }
}
