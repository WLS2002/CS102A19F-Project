import javazoom.jl.player.Player;

import javax.swing.*;
import java.awt.*;

public class Draw {

    static JFrame frame;
    static JPanel gameBoard = new JPanel();
    static POSITION p = new POSITION( SETTINGS.boardSize );
    static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    static void newGame(){

        frame =new JFrame("象棋");
        frame.setMenuBar(Swing.menuBar(2));
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setSize( (int) (SETTINGS.boardSize.width*15.5/10.0),SETTINGS.boardSize.height+55  );
        frame.setLocationRelativeTo(null);    //人为安放棋盘在中央
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().add(gameBoard);
        frame.getContentPane().setLayout(null);

        addItems();      //加入小部件
        gameBoard.setSize(SETTINGS.boardSize);
        gameBoard.setLocation(0,0);
        gameBoard.setLayout(null);
        //加入隐藏按钮
        frame.getContentPane().setBackground(Color.ORANGE);
        frame.getContentPane().repaint();
        Swing.createSecretButtons();

        for(int i = 1; i <= 10; i++){
            for(int j = 1; j <= 9; j++){
                gameBoard.add(Swing.secretButtons[i][j]);
            }
        }

        addPieces(P_INFORMATION.INITIAL, gameBoard);
        gameBoard.add(Swing.board());
        Game.loadNewGame();



        gameBoard.repaint();


    }


    private static void addItems(){

        Container container = frame.getContentPane();
        container.setLayout(null);
        //container.add(Swing.settingsButton(new Point((int) (SETTINGS.boardSize.width*1.45), (int)(SETTINGS.boardSize.height * 0.003))));
        container.add(Swing.switchSound(new Point((int) (SETTINGS.boardSize.width*1.45), (int)(SETTINGS.boardSize.height * 0.003))));
        container.add(Swing.saveButton());

        SituationBar.createNewTextArea();

        container.add(SituationBar.panel);
        frame.revalidate();
        container.add(Swing.withDrawButton());
        container.add(Swing.exitButton());
        container.add(Swing.outButton());


    }

    static void repaint(){
        POSITION p = new POSITION(SETTINGS.boardSize);
        gameBoard.removeAll();


        for(int i = 1; i <= 10; i++){
            for(int j = 1; j <= 9; j++){
                Swing.secretButtons[i][j].Change( PATH.HIDE, SETTINGS.pieceSize);
                Swing.secretButtons[i][j].setLocation( p.POS[i][j].x - SETTINGS.pieceSize.height/2, p.POS[i][j].y - SETTINGS.pieceSize.height/2);
                gameBoard.add(Swing.secretButtons[i][j]);
            }
        }

        addPieces(Game.st,  gameBoard);

        gameBoard.add(Swing.board());

       // gameBoard.add(Swing.withDrawButton());

        gameBoard.repaint();



    }

    static void addPieces(char[][] a, JPanel panel){
        for(int i = 1; i <= 10; i++){
            for(int j = 1; j <= 9; j++){
                if( a[i][j] == '.') continue;
                switch ( a[i][j]){
                    case 'C' :panel.add(Swing.createPiece(SETTINGS.BLACK_C,i,j)); break;
                    case 'H' :panel.add(Swing.createPiece(SETTINGS.BLACK_H,i,j)); break;
                    case 'E' :panel.add(Swing.createPiece(SETTINGS.BLACK_E,i,j)); break;
                    case 'A' :panel.add(Swing.createPiece(SETTINGS.BLACK_A,i,j)); break;
                    case 'G' :panel.add(Swing.createPiece(SETTINGS.BLACK_G,i,j)); break;
                    case 'N' :panel.add(Swing.createPiece(SETTINGS.BLACK_N,i,j)); break;
                    case 'S' :panel.add(Swing.createPiece(SETTINGS.BLACK_S,i,j)); break;
                    case 'c' :panel.add(Swing.createPiece(SETTINGS.RED_C,i,j)); break;
                    case 'h' :panel.add(Swing.createPiece(SETTINGS.RED_H,i,j)); break;
                    case 'e' :panel.add(Swing.createPiece(SETTINGS.RED_E,i,j)); break;
                    case 'a' :panel.add(Swing.createPiece(SETTINGS.RED_A,i,j)); break;
                    case 'g' :panel.add(Swing.createPiece(SETTINGS.RED_G,i,j)); break;
                    case 'n' :panel.add(Swing.createPiece(SETTINGS.RED_N,i,j)); break;
                    case 's' :panel.add(Swing.createPiece(SETTINGS.RED_S,i,j)); break;
                }
            }
        }
    }

    static void preMove (int x, int y){
        POSITION p = new POSITION(SETTINGS.boardSize);
        Swing.secretButtons[x][y].Change( PATH.SELECTED, SETTINGS.pieceSize);
        Sounds.playSingleMp3(8);
        for(int i = 1; i <= 10; i++){
            for(int j = 1; j <= 9; j++){
                if ( Game.canMove ( new int[] { x, y }, new int[] {i, j}, Tools.copyChars(Game.st) )  ){
                    int size = (int) ( SETTINGS.pieceSize.height*50/77.0);
                    Swing.secretButtons[i][j].Change( PATH.PRE_MOVE, new Dimension(size,size));
                    Swing.secretButtons[i][j].setLocation( p.POS[i][j].x - size/2, p.POS[i][j].y - size/2);
                }
            }
        }
    }


    public static void main(String[] args) {
        newGame();
    }

}
