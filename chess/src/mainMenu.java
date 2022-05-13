
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class mainMenu {

    static  JFrame menu;

    static void MainMenu(){

        menu = new JFrame("象棋");

        menu.setVisible(true);
        menu.setResizable(false);
        menu.setSize(  SETTINGS.boardSize.width + 14, SETTINGS.boardSize.height +  52 );
        menu.setLocationRelativeTo(null);    //人为安放棋盘在中央
        menu.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        menu.setMenuBar(Swing.menuBar(1));


        Container c = menu.getContentPane();
        c.setLayout(null);

        JPanel panel = new JPanel();
        panel.setSize(SETTINGS.boardSize);
        c.add(panel);
        panel.setLayout(null);

        Label ChinaChess = new Label(PATH.ChinaChess, new Dimension((int)(SETTINGS.boardSize.width*0.5), (int)(SETTINGS.boardSize.height*0.38)));
        ChinaChess.setLocation((int)(SETTINGS.boardSize.width*0.25),(int)(SETTINGS.boardSize.width*0.05));
        panel.add(RRDZ());
        panel.add(RJDZ());
        panel.add(LJDZ());
        panel.add(ZRCD());
        panel.add(ZRQP());
        panel.add(ChinaChess);
        panel.add(Swing.switchSound(new Point((int) (SETTINGS.boardSize.width*0.91), 0)));
        //panel.add(Swing.settingsButton(new Point((int) (SETTINGS.boardSize.width*0.82), 0 )));
        panel.add(new Label(PATH.panelBackGround, panel.getSize()));
        c.revalidate();
        c.repaint();
      //  SituationBar.createNewTextArea();
    }

    static Label RRDZ(){
        Label rrdz = new Label(PATH.RRDZ, new Dimension((int)(SETTINGS.boardSize.width*0.4), (int)(SETTINGS.boardSize.height*0.08)));
        rrdz.setLocation((int)(SETTINGS.boardSize.width*0.3), (int)(SETTINGS.boardSize.height*0.45));
        rrdz.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                rrdz.Change(PATH.RRDZDJ, new Dimension((int)(SETTINGS.boardSize.width*0.4), (int)(SETTINGS.boardSize.height*0.08)));
                Sounds.playSingleMp3(14);

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                rrdz.Change(PATH.RRDZXZ, new Dimension((int)(SETTINGS.boardSize.width*0.4), (int)(SETTINGS.boardSize.height*0.08)));
                Draw.main(null);
                menu.dispose();


            }

            @Override
            public void mouseEntered(MouseEvent e) {
                rrdz.Change(PATH.RRDZXZ, new Dimension((int)(SETTINGS.boardSize.width*0.4), (int)(SETTINGS.boardSize.height*0.08)));
                Sounds.playSingleMp3(15);

            }

            @Override
            public void mouseExited(MouseEvent e) {
                rrdz.Change(PATH.RRDZ, new Dimension((int)(SETTINGS.boardSize.width*0.4), (int)(SETTINGS.boardSize.height*0.08)));
            }
        });
        return rrdz;
    }

    static Label RJDZ(){
        Label rrdz = new Label(PATH.RJDZ, new Dimension((int)(SETTINGS.boardSize.width*0.4), (int)(SETTINGS.boardSize.height*0.08)));
        rrdz.setLocation((int)(SETTINGS.boardSize.width*0.3), (int)(SETTINGS.boardSize.height*0.55));
        rrdz.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                rrdz.Change(PATH.RJDZDJ, new Dimension((int)(SETTINGS.boardSize.width*0.4), (int)(SETTINGS.boardSize.height*0.08)));
                Sounds.playSingleMp3(14);

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                rrdz.Change(PATH.RJDZXZ, new Dimension((int)(SETTINGS.boardSize.width*0.4), (int)(SETTINGS.boardSize.height*0.08)));
                menu.dispose();
                robot.newRobotGame(SETTINGS.RobotPlaysType);
                //1.机器执黑   2.机器执红   3.机器对弈


            }

            @Override
            public void mouseEntered(MouseEvent e) {
                rrdz.Change(PATH.RJDZXZ, new Dimension((int)(SETTINGS.boardSize.width*0.4), (int)(SETTINGS.boardSize.height*0.08)));
                Sounds.playSingleMp3(15);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                rrdz.Change(PATH.RJDZ, new Dimension((int)(SETTINGS.boardSize.width*0.4), (int)(SETTINGS.boardSize.height*0.08)));
            }
        });
        return rrdz;
    }

    static Label LJDZ(){
        Label rrdz = new Label(PATH.LJDZ, new Dimension((int)(SETTINGS.boardSize.width*0.4), (int)(SETTINGS.boardSize.height*0.08)));
        rrdz.setLocation((int)(SETTINGS.boardSize.width*0.3), (int)(SETTINGS.boardSize.height*0.65));
        rrdz.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                rrdz.Change(PATH.LJDZDJ, new Dimension((int)(SETTINGS.boardSize.width*0.4), (int)(SETTINGS.boardSize.height*0.08)));
                Sounds.playSingleMp3(14);

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                rrdz.Change(PATH.LJDZXZ, new Dimension((int)(SETTINGS.boardSize.width*0.4), (int)(SETTINGS.boardSize.height*0.08)));
                Internet.connect();
                if(Internet.isConnected){
                    Internet.main(null);

                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                rrdz.Change(PATH.LJDZXZ, new Dimension((int)(SETTINGS.boardSize.width*0.4), (int)(SETTINGS.boardSize.height*0.08)));
                Sounds.playSingleMp3(15);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                rrdz.Change(PATH.LJDZ, new Dimension((int)(SETTINGS.boardSize.width*0.4), (int)(SETTINGS.boardSize.height*0.08)));
            }
        });
        return rrdz;
    }

    static Label ZRCD(){
        Label rrdz = new Label(PATH.ZRCD, new Dimension((int)(SETTINGS.boardSize.width*0.4), (int)(SETTINGS.boardSize.height*0.08)));
        rrdz.setLocation((int)(SETTINGS.boardSize.width*0.3), (int)(SETTINGS.boardSize.height*0.75));
        rrdz.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                rrdz.Change(PATH.ZRCDDJ, new Dimension((int)(SETTINGS.boardSize.width*0.4), (int)(SETTINGS.boardSize.height*0.08)));
                Sounds.playSingleMp3(14);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                rrdz.Change(PATH.ZRCDXZ, new Dimension((int)(SETTINGS.boardSize.width*0.4), (int)(SETTINGS.boardSize.height*0.08)));
                Draw.main(new String[]{""});
                I_O.loadGame(null);
                menu.dispose();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                rrdz.Change(PATH.ZRCDXZ, new Dimension((int)(SETTINGS.boardSize.width*0.4), (int)(SETTINGS.boardSize.height*0.08)));
                Sounds.playSingleMp3(15);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                rrdz.Change(PATH.ZRCD, new Dimension((int)(SETTINGS.boardSize.width*0.4), (int)(SETTINGS.boardSize.height*0.08)));
            }
        });
        return rrdz;
    }

    static Label ZRQP(){
        Label rrdz = new Label(PATH.ZRQP, new Dimension((int)(SETTINGS.boardSize.width*0.4), (int)(SETTINGS.boardSize.height*0.08)));
        rrdz.setLocation((int)(SETTINGS.boardSize.width*0.3), (int)(SETTINGS.boardSize.height*0.85));
        rrdz.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                rrdz.Change(PATH.ZRQPDJ, new Dimension((int)(SETTINGS.boardSize.width*0.4), (int)(SETTINGS.boardSize.height*0.08)));
                Sounds.playSingleMp3(14);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                rrdz.Change(PATH.ZRQPXZ, new Dimension((int)(SETTINGS.boardSize.width*0.4), (int)(SETTINGS.boardSize.height*0.08)));
                Draw.main(new String[]{});
                I_O.in(null);
                menu.dispose();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                rrdz.Change(PATH.ZRQPXZ, new Dimension((int)(SETTINGS.boardSize.width*0.4), (int)(SETTINGS.boardSize.height*0.08)));
                Sounds.playSingleMp3(15);
            }

            @Override

            public void mouseExited(MouseEvent e) {
                rrdz.Change(PATH.ZRQP, new Dimension((int)(SETTINGS.boardSize.width*0.4), (int)(SETTINGS.boardSize.height*0.08)));
            }
        });
        return rrdz;
    }

    static void mM(){
        MainMenu();
    }

    public static void main(String[] args) {
        Sounds.BGM();
        MainMenu();
    }
}
