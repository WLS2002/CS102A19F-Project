import java.awt.*;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    static ServerSocket serverSocket;
    static ArrayList<Player> players;
    static int numbers = 0;
    static Player Black = null, Red = null;

    static{

        players = new ArrayList<>();

        try{
            serverSocket = new ServerSocket(45532);
            System.out.println("已创建服务器");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    static private Thread findPlayer = new Thread(new Runnable() {
        @Override
            public void run() {
            try {
                while (true) {

                    Socket socket = serverSocket.accept();

                    Thread load = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            BufferedReader reader = null;
                            PrintWriter writer = null;

                           try {
                               reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                               writer = new PrintWriter(socket.getOutputStream(), true);
                           }catch (Exception e){
                               e.printStackTrace();
                           }
                            try {

                                while (true) {

                                    String str = reader.readLine();
                                    if(str==null) continue;
                                    System.out.println(str);
                                    if (str.equals("deng lu")) {
                                        str = reader.readLine();
                                        if (Player.findPlayer(str) == 1) {
                                            writer.println("no found");
                                            continue;
                                        }
                                        if (Player.findPlayer(str) == 2) {
                                            writer.println("passwordWrong");
                                            continue;
                                        } else {
                                            writer.println("OK");
                                            Player newPlayer = Player.readPlayerFromTcp(str);
                                            newPlayer.socket = socket;
                                            players.add(newPlayer);
                                            listen(newPlayer);
                                            System.out.println(123);
                                            break;
                                        }
                                    }
                                    if(str.equals("zhu ce")){
                                        str = reader.readLine();
                                        if(Player.findPlayer(str) == 2)
                                            writer.println("yi cun zai");
                                        else {Player.createPlayer(str);
                                            writer.println("Ok");}
                                    }

                                }

                                System.out.println(123);


                                }catch(Exception e){
                                    e.printStackTrace();
                                }
                            }
                    });
                    load.start();

                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    });

    static private void listen(Player player){

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    BufferedReader reader = new BufferedReader(new InputStreamReader(player.socket.getInputStream()));
                    PrintWriter writer = new PrintWriter(player.socket.getOutputStream(), true);
                    while(true){
                        String str = reader.readLine();
                        System.out.println(str);
                        if(str.equals("Cha Kan Ge Ren Xin Xi")){
                            writer.println(player.name);
                            writer.println(String.format("%s %d %d %d", player.password, player.numberOfPlay, player.numberOfWin, player.score));
                        }
                        if(str.equals("Tui Chu")){
                            players.remove(player);
                            break;
                        }
                        if(str.equals("Kai Shi Pi Pei")){

                            if(Black!=null&&Red == null){
                                Red = player;
                                writer.println("Red");
                                onlineGame newGame = new onlineGame(Black, Red);
                                newGame.gameStart();
                                Black = null;
                                Red = null;
                                break;
                            }

                            if(Black == null){
                                Black = player;
                                writer.println("Black");
                                break;
                            }

                        }

                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
        t.start();

    }
    static private Thread Control = new Thread(new Runnable() {
        @Override
        public void run() {
            Scanner input = new Scanner(System.in);
            while(true){
                String str = input.next();
                if(str.equals("message")) {
                    System.out.println("当前在线人数 "+players.size() + "人");
                    for(Player player : players){
                        System.out.println(player.name+ "  " + player.numberOfPlay+"  "+player.numberOfWin+"  "+player.score);
                    }
                }
            }
        }
    });


    public static void main(String[] args) {
        Main.findPlayer.start();
        Main.Control.start();
        Scanner input = new Scanner(System.in);

    }

}
