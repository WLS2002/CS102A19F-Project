import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

class Game {

    static boolean isStudy = false;
    static boolean isRobot = false;
    static boolean isRobotBlack = false;
    static boolean isIsRobotRed = false;

    static boolean isOnlineGame = false;
    static boolean onlinePlayBlack = false;

    static boolean isBlack = false;
    static int[] clickPOS = new int[2];
    static boolean isKeepPiece = false;
    static int[] choosePiecePOS = new int[2];
    static ArrayList<char[][]> save = new ArrayList<>();
    static ArrayList<int[]> steps = new ArrayList<>();
    //上，左多加一行保证从1开始
    //st means situation
    static char[][] st = new char[11][10];
    
    //载入一局新游戏
    static void loadNewGame() {
        if(!isOnlineGame)
        SituationBar.append("创建新游戏\n轮到红方\n");
        else if(onlinePlayBlack){
            SituationBar.append("欢迎来到联机对战模式。\n你是黑方，正在等待红方加入。。。\n");
        }
        else     SituationBar.append("欢迎到联机对战模式。\n你是红方，游戏开始!\n轮到红方\n");
        st = Tools.copyChars(P_INFORMATION.INITIAL);
        isBlack = false;
        isKeepPiece = false;
        choosePiecePOS = null;
        save.clear();
        save.add(Tools.copyChars(st));
        steps.clear();
        Draw.repaint();

        //如果是机器人模式且机器人执红 则开始游戏时机器人便下一步
        if( isIsRobotRed && isRobot){
            int[] x = robot.robotMove(Tools.copyChars(st), isBlack, "Depper");
            assert x != null;
            move(x[0], x[1], x[2], x[3]);
        }


    }

    static int kindOfClick(int x, int y, char[][] board){
        char click = board[x][y];
        if(click == '.') return 0;   //如果点击的是空白区域，返回0
        if('A' <= click && click <= 'Z') return 1;  // 黑棋，返回1
        return -1;              // default  返回-1
    }

     static boolean isGameOver(char[][] st, boolean isBlack){

        if(isBlack){             //红棋下完，判断黑棋是否输

            for(int i = 1; i <= 10; i++){
                for(int j = 1; j <= 9; j++){
                    if(kindOfClick( i, j, Tools.copyChars(st)) != 1)
                        continue;
                    for(int x = 1; x <= 10; x++)
                        for(int y = 1; y <= 9; y ++)
                            if(canMove( new int[]{i, j} , new int[]{x, y} , Tools.copyChars(st)) ){
                                return false;
                            }
                }
            }
            return true;
        }

        else{             //黑棋下完，判断红棋是否输

            for(int i = 1; i <= 10; i++){
                for(int j = 1; j <= 9; j++){
                    if(kindOfClick( i, j, Tools.copyChars(st)) != -1)
                        continue;
                    for(int x = 1; x <= 10; x++)
                        for(int y = 1; y <= 9; y ++)
                            if(canMove( new int[]{i, j} , new int[]{x, y} , Tools.copyChars(st)) ){
                                return false;
                            }
                }
            }
            return true;
        }
    }

    static void Movement() {
        int posX = Game.clickPOS[0];  //点击的位置
        int posY = Game.clickPOS[1];
        int kind = kindOfClick(posX, posY, Tools.copyChars(st));  //点击的类型

        if (kind == 0 && !isKeepPiece)      //如果点击的是空白而且棋子没有被抓起，则无事发生
            return;

        if (kind != 0 && !isKeepPiece) {         //如果点击的是棋子而且棋子未被抓起，则抓取一枚棋子（如果是对应方落子），并显示preMove

            if ((kind == 1 && isBlack) || (kind == -1 && !isBlack)) {
                if((isOnlineGame&&onlinePlayBlack&&kind == -1)||(isOnlineGame&&!onlinePlayBlack&&kind == 1))
                    return;

                isKeepPiece = true;
                choosePiecePOS = clickPOS.clone();
                Draw.preMove( choosePiecePOS[0], choosePiecePOS[1] );
                return;
            }
        }

        if (isKeepPiece) {      //如果点击的是空白而且棋子已被抓起，则棋子可能会位移

            if( Arrays.equals(clickPOS, choosePiecePOS) ) {  //如果两次点击同一枚棋子，则将棋子放下
                isKeepPiece = false;
                choosePiecePOS = null;
                Draw.repaint();
                return;
            }

            if (kindOfClick(choosePiecePOS[0], choosePiecePOS[1], Tools.copyChars(st)) * kindOfClick(clickPOS[0], clickPOS[1],Tools.copyChars(st)) == 1) {
                choosePiecePOS = clickPOS.clone();
                Draw.repaint();
                Draw.preMove(choosePiecePOS[0],choosePiecePOS[1]);
                return;
            }       // 改变所执的棋子

            if( !canMove( choosePiecePOS, clickPOS,Tools.copyChars(st)))      //判断是否是无效操作
                return;



            int x0 = choosePiecePOS[0];
            int y0 = choosePiecePOS[1];
            int x1 = clickPOS[0];
            int y1 = clickPOS[1];
            char choosePiece = st[ x0 ][ y0 ];



            move(x0, y0 ,x1, y1);



            //    System.out.println(Arrays.toString(steps.get(steps.size()-1)));

        }

    }

    static void move(int x0, int y0, int x1, int y1) {
        Thread t = new Thread( () -> {

            if(isStudy)
                robot.memorize(Tools.translateChessBoard(Tools.copyChars(st), isBlack), x0, y0, x1, y1);

            Sounds.playSingleMp3(1);
            char choosePiece = st[ x0 ][ y0 ];
            POSITION p = new POSITION( SETTINGS.boardSize );
            double x0POS = p.POS[x0][y0].x - SETTINGS.pieceSize.height / 2.0;
            double y0POS = p.POS[x0][y0].y - SETTINGS.pieceSize.height / 2.0;
            double x1POS = p.POS[x1][y1].x - SETTINGS.pieceSize.height / 2.0;
            double y1POS = p.POS[x1][y1].y - SETTINGS.pieceSize.height / 2.0;
            double X = (x1POS - x0POS) / Math.abs(x1POS - x0POS) * Math.abs(x1POS - x0POS) /100;
            double Y = (y1POS - y0POS )/ Math.abs(y1POS - y0POS) * Math.abs(y1POS - y0POS) /100;
            if( x1POS == x0POS)
                X = 0;
            if( y1POS == y0POS)
                Y = 0;
            char[][] b = Tools.copyChars(st);

            JLabel label;
            String path = "";

            String pieceName = "";
            String twoCharacters = "";

            boolean oneLine = false;

            switch(choosePiece){
                case 'A': path = SETTINGS.BLACK_A; pieceName = "士"; break;
                case 'G': path = SETTINGS.BLACK_G; pieceName = "帅"; break;
                case 'C': path = SETTINGS.BLACK_C; pieceName = "车"; break;
                case 'N': path = SETTINGS.BLACK_N; pieceName = "炮"; break;
                case 'H': path = SETTINGS.BLACK_H; pieceName = "马"; break;
                case 'E': path = SETTINGS.BLACK_E; pieceName = "相"; break;
                case 'S': path = SETTINGS.BLACK_S; pieceName = "兵"; break;
                case 'a': path = SETTINGS.RED_A; pieceName = "士"; break;
                case 'g': path = SETTINGS.RED_G; pieceName = "帅"; break;
                case 'c': path = SETTINGS.RED_C; pieceName = "车"; break;
                case 'n': path = SETTINGS.RED_N; pieceName = "炮"; break;
                case 'h': path = SETTINGS.RED_H; pieceName = "马"; break;
                case 's': path = SETTINGS.RED_S; pieceName = "兵"; break;
                case 'e': path = SETTINGS.RED_E; pieceName = "相"; break;
            }

            for(int i = 1; i <= 10 ; i++){
                if(i == x0) continue;
                if(st[i][y0] == choosePiece){
                    oneLine = true;
                    if( (isBlack && i < x0) || ( !isBlack && i > x0 ) )
                        twoCharacters = "前" + pieceName;
                    else twoCharacters = "后" + pieceName;
                }
            }

            if(!oneLine){
                String x = "";
                switch (y0){
                    case 1 : x = "九" ; break;
                    case 2 : x = "八" ; break;
                    case 3 : x = "七" ; break;
                    case 4 : x = "六" ; break;
                    case 5 : x = "五" ; break;
                    case 6 : x = "四" ; break;
                    case 7 : x = "三" ; break;
                    case 8 : x = "二" ; break;
                    case 9 : x = "一" ; break;
                }
                twoCharacters = pieceName + x;
            }

            String third = "";
            if ( x0 == x1 ) third = "平";
            else if( (isBlack && x1 > x0 )||(!isBlack && x1 < x0 ))
                third = "进";
            else third = "退";

            String fourth = "";
            if( (third.equals("平"))||(pieceName.equals("马")||pieceName.equals("象")||pieceName.equals("士")) )
                switch (y1){
                case 1 : fourth = "九" ; break;
                case 2 : fourth = "八" ; break;
                case 3 : fourth = "七" ; break;
                case 4 : fourth = "六" ; break;
                case 5 : fourth = "五" ; break;
                case 6 : fourth = "四" ; break;
                case 7 : fourth = "三" ; break;
                case 8 : fourth = "二" ; break;
                case 9 : fourth = "一" ; break;
                }
            else  switch (Math.abs(x0-x1)){
                case 1 : fourth = "一" ; break;
                case 2 : fourth = "二" ; break;
                case 3 : fourth = "三" ; break;
                case 4 : fourth = "四" ; break;
                case 5 : fourth = "五" ; break;
                case 6 : fourth = "六" ; break;
                case 7 : fourth = "七" ; break;
                case 8 : fourth = "八" ; break;
                case 9 : fourth = "九" ; break;
            }

            String which = "红";
            if(isBlack) which = "黑";

            SituationBar.jt.append(String.format("%s方：%s%s%s\n", which, twoCharacters, third, fourth));
            if( st[x1][y1] != '.') {
                Sounds.playSingleMp3(2);
                SituationBar.append("吃！\n");
            }


            label = new Label(path, SETTINGS.pieceSize);
            label.setLocation((int)x0POS, (int)y0POS);
            b[x0][y0] = '.';
            Draw.gameBoard.removeAll();
            Draw.gameBoard.add(label);
            Draw.addPieces(b, Draw.gameBoard);
            Draw.gameBoard.add(Swing.board());
            Draw.gameBoard.repaint();
            // Sounds.playSingleMp3(0);

            while( !( -1 <= x0POS - x1POS &&  x0POS - x1POS<=1 && -1<= y0POS - y1POS && y0POS - y1POS <=1) ) {
                label.setLocation((int)x0POS, (int)y0POS);
                x0POS += X;
                y0POS += Y;
                try {
                    Thread.sleep(1);
                }catch(Exception e){
                    return;
                }
            }

            if(isOnlineGame && isBlack == onlinePlayBlack){
                try{
                    Internet.writer.println("M"+x0+"/"+y0+"/"+x1+"/"+y1);
                }catch(Exception e){
                    e.printStackTrace();
                }
        }

            st[x0][y0] = '.';
            st[x1][y1] = choosePiece;
            isKeepPiece = false;             //落子
            isBlack = !isBlack;            // 换人下棋
            choosePiecePOS = null;

            save.add( Tools.copyChars( st ) );
            // System.out.println(Arrays.toString(st));
            steps.add(new int[]{x0,y0,x1,y1});

            Draw.repaint();

           /* if ( ThreatKing( isBlack, Tools.copyChars(st) ) )
                if( isBlack )Sounds.playSingleMp3(3);
                else Sounds.playSingleMp3(6);*/
           if(ThreatKing( isBlack, Tools.copyChars(st))) {
               SituationBar.append("将军！\n");
               Sounds.playSingleMp3(9);
           }

           if( isGameOver( Tools.copyChars(st), isBlack ) ){
               String s = "红方胜利";
               if(!isBlack) s = "黑方胜利";
               Related_Frame.tips("游戏结束", s);
               SituationBar.append("游戏结束   "+s);
               Sounds.playSingleMp3(16);
           }

           else if(isBlack)  SituationBar.append("轮到黑方\n");
           else  SituationBar.append("轮到红方\n");

           if(isRobot&&isBlack&&isRobotBlack) {
               int[] x = robot.robotMove(Tools.copyChars(st), isBlack, "Depper");
               if( x != null )
                   move(x[0], x[1], x[2], x[3]);
           }

            if(isRobot&&!isBlack&&isIsRobotRed) {
                int[] x = robot.robotMove(Tools.copyChars(st), isBlack, "Depper");
                if( x != null )
                    move(x[0], x[1], x[2], x[3]);
            }


            /*if(isOnlineGame&& !isBlack == onlinePlayBlack){
                System.out.println(23333333);
                int[] x = receiveFromInternet();
                move(x[0], x[1], x[2], x[3]);
            }*/

        });
        t.start();



    }

    //isBlack True 表示判断黑棋是否被将  false  判断红棋是否被将
    static private boolean ThreatKing(boolean isBlack, char[][] board){

        //isBlack true 自然是判断黑方是否被将啦
        if(isBlack){
            int[] GPOS = findPiecePos('G', board);
            for(int i = 1; i <= 10; i++){
                for(int j = 1; j <= 9 ;j++){
                    if(kindOfClick(i,j,board) != -1) continue;
                    if( moveRule ( new int[]{i, j} , GPOS , Tools.copyChars(board))){
                        return true;
                    }

                }
            }
        }
        if(!isBlack){
            int[] gPOS = findPiecePos('g', board);
            for(int i = 1; i <= 10; i++){
                for(int j = 1; j <= 9 ;j++){
                    if(kindOfClick(i,j,  board) != 1) continue;
                    if( moveRule (  new int[]{i, j} , gPOS , Tools.copyChars(board)))
                        return true;
                }
            }
        }
        return false;
    }

    private static boolean STReasonable(boolean isBlack,char[][] board){


        boolean canMove = false;
        int[] gPOS = findPiecePos('g', board);
        int[] GPOS = findPiecePos('G', board);
        if (gPOS == null || GPOS == null)
            return true;
        if( gPOS[1] == GPOS[1] ){
            for(int i = GPOS[0] + 1; i < gPOS[0] ; i++ ) {
                if(board[i][GPOS[1]] != '.')
                    canMove = true;
            }
            if(!canMove) return false;
        }             //老将对面返回false;

        if( isBlack && ThreatKing(true, Tools.copyChars(board))) return false;

        if( !isBlack && ThreatKing(false, Tools.copyChars(board))) return false;

        return true;
    }

    //判断棋子能否  ！！！只看规则
    static  boolean moveRule ( int[] piecePOS, int[]target, char[][] board ){

        int x0 = piecePOS[0];
        int y0 = piecePOS[1];
        int x1 = target[0];
        int y1 = target[1];
        int diboardance = (x0-x1) * (x0-x1) + (y0-y1) * (y0-y1) ;
        char piece = board[ x0 ][ y0 ];

        if(Arrays.equals(piecePOS, target)) return false;

        if(x0>10||x0<1||x1>10||x1<1||y0>10||y0<1||y1>10||y1<1) return false;

        if (kindOfClick( x0, y0 ,board) * kindOfClick( x1, y1,board) == 1) return false;

        if( piece == '.')
            return false;

        if( piece == 'G' ){
            if( !(diboardance ==1  &&  1 <= x1 && x1 <= 3 && 4 <= y1 && y1 <= 6) )
                return false;
        }

        if( piece == 'g' ){
            if(! (diboardance == 1 && 8 <= x1 && x1 <= 10 && 4 <= y1 && y1 <= 6))
                return false;
        }

        if( piece == 'A'){
            if(!(diboardance == 2 && 1 <= x1 && x1 <= 3 && 4 <= y1 && y1 <= 6))
                return false;
        }

        if( piece == 'a'){
            if(!( diboardance == 2 && 8 <= x1 && x1 <= 10 && 4 <= y1 && y1 <= 6))
                return false;
        }

        if( piece == 'E'){
            if(!(diboardance == 8 && x1 <= 5 && board[ (x0 + x1)/2 ][ (y0 + y1)/2 ] == '.'))
                return false;
        }

        if(piece == 'e'){
            if(!(diboardance == 8 && x1 >= 6 && board[ (x0 + x1)/2 ][ (y0 + y1)/2 ] == '.'))
                return false;
        }

        if( piece == 'C' || piece == 'c'){
            if( x0 != x1 && y0 != y1 )
                return false;

            if( x1 == x0 )
                for( int i = Math.min(y0, y1) + 1; i < Math.max(y0, y1); i++)
                    if(board[x0][i] != '.' ) return false;

            if( y1 == y0 )
                for( int i = Math.min(x0, x1) + 1; i < Math.max(x0, x1); i++)
                    if(board[i][y0] != '.' ) return false;

        }

        if( piece == 'S'){
            if( x0 <= 5 ){
                if(!( diboardance == 1 && x1 == x0 + 1 ))
                    return false;
            }

            else if(!(diboardance ==1 && x1 >=  x0))
                return false;
        }

        if( piece == 's'){
            if( x0 >= 6 ) {
                if(!( diboardance == 1 && x1 == x0 - 1))
                    return false;
            }
            else if(!(diboardance ==1 && x1 <=  x0))
                return false;
        }

        if( piece == 'h' || piece == 'H' ){
            if( diboardance != 5 )
                return false;
            if( x0 - x1 == -2 || x0 - x1 == 2){
                if(!(board[ ( x0 + x1 )/2 ][ y0 ] == '.'))
                    return false;
            }
            else if(!(board[ x0 ][ (y0 + y1)/2 ] == '.'))
                return false;
        }


        if( piece == 'N' || piece == 'n'){
            if( x0 != x1 && y0 != y1 )
                return false;

            int number = 0;

            if( board[x1][y1] != '.' ) number = 1;

            if( x1 == x0 )
                for( int i = Math.min(y0, y1) + 1; i < Math.max(y0, y1); i++)
                    if(board[x0][i] != '.' ) number++;

            if( y1 == y0 )
                for( int i = Math.min(x0, x1) + 1; i < Math.max(x0, x1); i++)
                    if(board[i][y0] != '.' ) number++;


            if(!((number == 0) || (number == 2 && board[ x1 ][ y1 ] != '.')))
                return false;

        }
        return true;
    }


    static boolean canMove ( int[] piecePOS, int[]target , char[][] board){

        if( ! moveRule(piecePOS, target , Tools.copyChars(board) ) )
            return false;

        board[ target[0] ][ target[1] ] = board[ piecePOS[0] ][ piecePOS[1] ];
        board[ piecePOS[0] ][ piecePOS[1] ] = '.';

        return ( STReasonable( isBlack , Tools.copyChars(board) ) );

    }

    static void withDraw(){



        System.out.println(save.size());

        if(save.size() == 1 )
            return;
        save.remove( save.size() -1 );
        steps.remove(steps.size() -1);

        for(int i = 1; i <= 10; i++)
            System.arraycopy( save.get(save.size() -1)[i] , 1, st[i], 1, 9);

        st = Tools.copyChars(save.get(save.size()-1));

        isBlack = !isBlack;
        clickPOS = new int[2];
        choosePiecePOS = new int[2];
        isKeepPiece = false;
        Draw.repaint();
        SituationBar.append("悔棋\n轮到");
        if(isBlack) SituationBar.append("黑方\n");
        else SituationBar.append("红方\n");

    }

    static int[] findPiecePos(char a, char[][] st){
        int[] POS = new int[2];
        for(int i = 1; i <= 10; i++)
            for(int j = 1; j <= 9; j++)
                if(st[i][j] == a ){
                    POS[0] = i;
                    POS[1] = j;
                    return POS;
                }
        return null;
    }

    static void receiveFromInternetAndMove(String ints){
    /*    String ints = "";
        try {
             ints = Internet.reader.readLine();
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(ints);*/
        int[] returnInt = new int[4];
        int index1 = ints.indexOf('/');
        int index2 = ints.indexOf('/', index1 + 1);
        int index3 = ints.indexOf('/', index2 + 1);

        returnInt[0] = Integer.parseInt(ints.substring(0, index1));
        returnInt[1] = Integer.parseInt(ints.substring(index1 + 1, index2));
        returnInt[2] = Integer.parseInt((ints.substring(index2 + 1, index3)));
        returnInt[3] = Integer.parseInt(ints.substring(index3 + 1));

        move(returnInt[0], returnInt[1], returnInt[2], returnInt[3]);
    }



}
