import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.nio.file.Path;

public class Swing {

    static Label[][] secretButtons = new Label[11][10];  // 10 * 9 个隐藏按钮

   // static JLabel board = new Label( SETTINGS.boardSkin, SETTINGS.boardSize);
    //棋子
    static JLabel board(){
        return new Label( SETTINGS.boardSkin, SETTINGS.boardSize);
    }


    static JLabel createPiece(String path, int x,int y){
        Label piece = new Label(path, SETTINGS.pieceSize );               //创建按钮
        POSITION p = new POSITION( SETTINGS.boardSize );    //调用position信息
        //获取棋子的位置
        int X = p.POS[x][y].x;
        int Y = p.POS[x][y].y;
        piece.setLocation(X - SETTINGS.pieceSize.height/2, Y - SETTINGS.pieceSize.height/2 );                 //定位

        return piece;
    }

    static void createSecretButtons(){
        POSITION p = new POSITION( SETTINGS.boardSize );
        for(int i = 1; i <= 10; i++){
            for(int j = 1; j <= 9; j++){
                secretButtons[i][j] = new Label( PATH.HIDE, SETTINGS.pieceSize );
                secretButtons[i][j].setLocation( p.POS[i][j].x - SETTINGS.pieceSize.height/2, p.POS[i][j].y - SETTINGS.pieceSize.height/2);
                int finalI = i;
                int finalJ = j;
                secretButtons[i][j].addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {

                    }

                    @Override
                    public void mousePressed(MouseEvent e) {

                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                        Game.clickPOS[0] = finalI;
                        Game.clickPOS[1] = finalJ;
                        Game.Movement();
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {

                    }

                    @Override
                    public void mouseExited(MouseEvent e) {

                    }
                });
            }
        }
    } // end createSecretButtons

    static JButton withDrawButton(){           //悔棋按钮
        JButton button = new JButton("悔棋");
        int x = SETTINGS.boardSize.width;
        int y = SETTINGS.boardSize.height;
        button.setLocation((int) ( x * 1.065 ),(int) ((y - SETTINGS.pieceSize.height) * 0.92));
        button.setSize((int)(x*0.4), (int) (y * 0.05));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Game.isOnlineGame){
                    Internet.askForWithdraw();
                    return;
                }

                Game.withDraw();
                Sounds.playSingleMp3(12);

            }
        });
        return button;
    }

    static Label switchSound(Point x){
        Label button = new Label(PATH.SoundSwitch[PATH.soundSwitch], new Dimension( (int)(SETTINGS.boardSize.width*0.065), (int)(SETTINGS.boardSize.width*0.065)));
        button.setLocation(x);
        button.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                Sounds.SoundSwitch();
                if(PATH.soundSwitch == 0)
                    PATH.soundSwitch = 1;
                else PATH.soundSwitch = 0;
                button.Change(PATH.SoundSwitch[PATH.soundSwitch], new Dimension( (int)(SETTINGS.boardSize.width*0.065), (int)(SETTINGS.boardSize.width*0.065)));
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        return button;
    }

    static JButton saveButton(){
        JButton button = new JButton("存储棋盘");
        int x = SETTINGS.boardSize.width;
        int y = SETTINGS.boardSize.height;
        button.setLocation((int) ( x * 1.065 ),(int) ((y - SETTINGS.pieceSize.height) * 0.78));
        button.setSize((int)(x*0.4), (int) (y * 0.05));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Sounds.playSingleMp3(13);
                Related_Frame.tips("存储棋盘", "成功！");
                I_O.saveGame();
            }
        });
        return button;
    }

    static JButton loadButton(){
        JButton button = new JButton("读档");
        int x = SETTINGS.boardSize.width;
        int y = SETTINGS.boardSize.height;
        button.setLocation((int) ( x * 1.065 ),(int) ((y - SETTINGS.pieceSize.height) * 0.50));
        button.setSize(SETTINGS.pieceSize);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                I_O.loadGame(null);
            }
        });
        return button;
    }

    static JButton newGameButton(){
        JButton button = new JButton("重开");
        int x = SETTINGS.boardSize.width;
        int y = SETTINGS.boardSize.height;
        button.setLocation((int) ( x * 1.065 ),(int) ((y - SETTINGS.pieceSize.height) * 0.35));
        button.setSize(SETTINGS.pieceSize);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Game.loadNewGame();
            }
        });
        return button;
    }

    static JButton outButton() {
        JButton button = new JButton("导出棋谱");
        int x = SETTINGS.boardSize.width;
        int y = SETTINGS.boardSize.height;
        button.setLocation((int) ( x * 1.065 ),(int) ((y - SETTINGS.pieceSize.height) * 0.85));
        button.setSize((int)(x*0.4), (int) (y * 0.05));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Sounds.playSingleMp3(13);
                Related_Frame.tips("导出棋谱","成功！");
                I_O.out();
            }
        });
        return button;
    }

    static JButton inButton () {
        JButton button = new JButton("导入棋谱");
        int x = SETTINGS.boardSize.width;
        int y = SETTINGS.boardSize.height;
        button.setLocation((int) (x * 1.065), (int) ((y - SETTINGS.pieceSize.height) * 0.05));
        button.setSize(SETTINGS.pieceSize);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                I_O.in(null);
                }
            });
        return button;
    }

    static JButton exitButton(){           //悔棋按钮

        JButton button = new JButton("退回大厅");
        int x = SETTINGS.boardSize.width;
        int y = SETTINGS.boardSize.height;
        button.setLocation((int) ( x * 1.065 ),(int) ((y - SETTINGS.pieceSize.height) * 0.99));
        button.setSize((int)(x*0.4), (int) (y * 0.05));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainMenu.mM();
                Draw.frame.dispose();
                SituationBar.jt.setText("");
                Sounds.playSingleMp3(17);

                Game.isRobot = false;
                Game.isIsRobotRed = false;
                Game.isRobotBlack = false;


                if(Game.isOnlineGame){
                    try {
                        Internet.writer.println("leave");
                        Internet.socket.close();

                    }catch(Exception e1){
                        e1.printStackTrace();
                    }
                }
                Game.onlinePlayBlack = false;
                Game.isOnlineGame = false;

            }
        });
        return button;
    }

    static MenuBar menuBar(int type){
        MenuBar menuBar = new MenuBar();
        Menu Start = new Menu("Start");
        Menu Settings = new Menu("Settings");
        Menu Operation = new Menu("Operation");

        MenuItem Start_NewGame = new MenuItem("New Game");
        MenuItem Start_NewGameWithRobot = new MenuItem("New Game With Robot");
        MenuItem Start_LoadGame = new MenuItem("Load Game");
        MenuItem Start_LoadSequence = new MenuItem("Load Sequence");
        MenuItem Start_OpenFile = new MenuItem("Open File");


        Start_NewGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(type==2){
                    Game.loadNewGame();
                }
                else {
                Draw.main(new String[] {""});
                mainMenu.menu.dispose();
                }
            }
        });
        Start_NewGameWithRobot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(type==1) {
                    mainMenu.menu.dispose();
                    robot.newRobotGame(1);
                    //1.机器执黑   2.机器执红   3.机器对弈
                }
                else {
                    Draw.frame.dispose();
                    robot.newRobotGame(1);
                }
            }
        });
        Start_LoadGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(type==1){
                Draw.main(new String[]{""});
                I_O.loadGame(null);
                mainMenu.menu.dispose();
                }
                else I_O.loadGame(null);
            }
        });
        Start_LoadSequence.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(type==1) {
                    Draw.main(new String[]{});
                    I_O.in(null);
                    mainMenu.menu.dispose();
                }
                else {
                    I_O.in(null);
                }
            }
        });
        Start_OpenFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(type==1)
                I_O.openFile(mainMenu.menu);
                else I_O.openFile(Draw.frame);
            }
        });

        MenuItem Operation_Withdraw = new MenuItem("Withdraw");
        MenuItem Operation_SaveChessBoard = new MenuItem("Save Chessboard");
        MenuItem Operation_SaveSequence = new MenuItem("Save Sequence");
        MenuItem Operation_GiveUp = new MenuItem("GiveUp");
        MenuItem Operation_QiuHe = new MenuItem("QiuHe");

        Operation_Withdraw.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Game.isOnlineGame){
                    Internet.askForWithdraw();
                    return;
                }
                Game.withDraw();
            }
        });
        Operation_SaveChessBoard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Related_Frame.tips("存储棋盘", "成功！");
                I_O.saveGame();
            }
        });
        Operation_SaveSequence.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Related_Frame.tips("导出棋谱","成功！");
                I_O.out();
            }
        });
        Operation_GiveUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Game.isOnlineGame){
                    Related_Frame.tips("游戏结束", "你认输了");
                    Internet.writer.println("Ren shu");
                }
            }
        });
        Operation_QiuHe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Game.isOnlineGame){
                    Internet.askForHeQi();
                }
            }
        });

        MenuItem Settings_SizeB = new MenuItem("Set Frame Size: Big");
        MenuItem Settings_SizeM = new MenuItem("Set Frame Size: Medium");
        MenuItem Settings_SizeS = new MenuItem("Set Frame Size: Small");

        MenuItem Settings_BoardSkin1 = new MenuItem("Board Skin 1");
        MenuItem Settings_BoardSkin2 = new MenuItem("Board Skin 2");
        MenuItem Settings_BoardSkin3 = new MenuItem("Board Skin 3");

        MenuItem Settings_RobotPlaysBlack = new MenuItem("Robot Plays Black");
        MenuItem Settings_RobotPlaysRed = new MenuItem("Robot Plays Red");
        MenuItem Settings_RobotVersusRobot = new MenuItem("Robot Versus Robot");

        Settings_SizeB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(type==1){
                    mainMenu.menu.dispose();
                    SETTINGS.boardSize = SIZE.LARGE;
                    SETTINGS.pieceSize = SIZE.PIECE_LARGE;
                    mainMenu.MainMenu();
                }
                else {
                    Draw.frame.dispose();
                    String str = SituationBar.getString();
                    SETTINGS.boardSize = SIZE.LARGE;
                    SETTINGS.pieceSize = SIZE.PIECE_LARGE;
                    I_O.saveGame();
                    Draw.newGame();
                    I_O.loadGame(null);
                    SituationBar.jt.setText(str);
                }
            }
        });
        Settings_SizeM.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(type==1){
                    mainMenu.menu.dispose();
                    SETTINGS.boardSize = SIZE.MEDIUM;
                    SETTINGS.pieceSize = SIZE.PIECE_MEDIUM;
                    mainMenu.MainMenu();
                }
                else {
                    Draw.frame.dispose();
                    String str = SituationBar.getString();
                    SETTINGS.boardSize = SIZE.MEDIUM;
                    SETTINGS.pieceSize = SIZE.PIECE_MEDIUM;
                    I_O.saveGame();
                    Draw.newGame();
                    I_O.loadGame(null);
                    SituationBar.jt.setText(str);
                }
            }
        });
        Settings_SizeS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(type==1){
                    mainMenu.menu.dispose();
                    SETTINGS.boardSize = SIZE.SMALL;
                    SETTINGS.pieceSize = SIZE.PIECE_SMALL;
                    mainMenu.MainMenu();
                }
                else {
                    Draw.frame.dispose();
                    String str = SituationBar.getString();
                    SETTINGS.boardSize = SIZE.SMALL;
                    SETTINGS.pieceSize = SIZE.PIECE_SMALL;
                    I_O.saveGame();
                    Draw.newGame();
                    I_O.loadGame(null);
                    SituationBar.jt.setText(str);
                }
            }
        });

        Settings_BoardSkin1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(type==1){
                    SETTINGS.boardSkin = PATH.BOARD_1;
                }
                else {
                    SETTINGS.boardSkin = PATH.BOARD_1;
                    Draw.repaint();
                }
            }
        });
        Settings_BoardSkin2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(type==1){
                    SETTINGS.boardSkin = PATH.BOARD_2;
                }
                else {
                    SETTINGS.boardSkin = PATH.BOARD_2;
                    Draw.repaint();
                }
            }
        });
        Settings_BoardSkin3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(type==1){
                    SETTINGS.boardSkin = PATH.BOARD_3;
                }
                else {
                    SETTINGS.boardSkin = PATH.BOARD_3;
                    Draw.repaint();
                }
            }
        });

        Settings_RobotPlaysBlack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SETTINGS.RobotPlaysType = 1;
            }
        });

        Settings_RobotPlaysRed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SETTINGS.RobotPlaysType = 2;
            }
        });

        Settings_RobotVersusRobot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SETTINGS.RobotPlaysType = 3;
            }
        });


        Start.add(Start_NewGame);
        Start.add(Start_NewGameWithRobot);
        Start.add(Start_LoadGame);
        Start.add(Start_LoadSequence);
        Start.add(Start_OpenFile);

        Operation.add(Operation_Withdraw);
        Operation.add(Operation_SaveChessBoard);
        Operation.add(Operation_SaveSequence);
        Operation.add(Operation_GiveUp);
        Operation.add(Operation_QiuHe);

        Settings.add(Settings_SizeB);
        Settings.add(Settings_SizeM);
        Settings.add(Settings_SizeS);
        Settings.addSeparator();
        Settings.add(Settings_BoardSkin1);
        Settings.add(Settings_BoardSkin2);
        Settings.add(Settings_BoardSkin3);
        Settings.addSeparator();

        if(type==1) {
            Settings.add(Settings_RobotPlaysBlack);
            Settings.add(Settings_RobotPlaysRed);
            Settings.add(Settings_RobotVersusRobot);
        }

        menuBar.add(Start);
        if(type==2)
        menuBar.add(Operation);
        menuBar.add(Settings);


        return menuBar;
    }





}
