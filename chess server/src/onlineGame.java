import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.jar.JarOutputStream;

public class onlineGame {
    Player Black, Red;
    boolean isBlack = false;
    BufferedReader BlackReader;
    BufferedReader RedReader;
    PrintWriter BlackWriter;
    PrintWriter RedWriter;
    boolean isGameOver = false;

    onlineGame(Player Black, Player Red){
        this.Black = Black;
        this.Red = Red;
        try {

            BlackReader = new BufferedReader(new InputStreamReader(Black.socket.getInputStream()));
            RedReader = new BufferedReader(new InputStreamReader(Red.socket.getInputStream()));
            BlackWriter = new PrintWriter(Black.socket.getOutputStream(), true);
            RedWriter = new PrintWriter(Red.socket.getOutputStream(), true);

            BlackWriter.println("completed");

        }catch(Exception e){
            e.printStackTrace();
        }
    }



    public void gameStart(){

        Thread redListener = new Thread(new Runnable() {
            @Override
            public void run() {
                try{

                    while(true){

                        String str = RedReader.readLine();
                        System.out.println("R"+str);
                        if(str==null)
                            continue;


                        if(str.equals("leave")){
                            Main.numbers--;
                            System.out.println("一名用户断开了连接，目前连接人数为：" + Main.numbers);
                            Red.socket.close();
                            BlackWriter.println("escape");
                            break;
                        }
                        else  BlackWriter.println(str);

                    }

                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });


        Thread BlackListener = new Thread(new Runnable() {
            @Override
            public void run() {
                try{

                    while(true){

                        String str = BlackReader.readLine();
                        System.out.println("B"+str);
                        if(str==null)
                            continue;

                        if(str.equals("leave")){
                            Main.numbers--;
                            System.out.println("一名用户断开了连接，目前连接人数为：" + Main.numbers);
                            Black.socket.close();
                            RedWriter.println("escape");
                            break;
                        }
                        else  RedWriter.println(str);


                    }

                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });

        redListener.start();
        BlackListener.start();
    }

}
