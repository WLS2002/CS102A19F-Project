import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.Socket;
import java.util.ArrayList;

public class Player {

    String account;
    String password;
    String name;
    int numberOfPlay;
    int numberOfWin;
    int score;
    Socket socket;

    Player(String password, String name , int numberOfPlay, int numberOfWin, int score){
        this.password = password;
        this.name = name;
        this.numberOfPlay = numberOfPlay;
        this.numberOfWin = numberOfWin;
        this.score = score;
    }

    static int findPlayer(String str){
        int index = str.indexOf('#');
        String name = str.substring(0, index);
        String password = str.substring(index+1) ;
        /*System.out.println(name);
        System.out.println(password);*/

        File file = new File("data/"+name+".player");
        String players = "";

        if( !file.exists() )
            return 1;

        try {
            FileReader in = new FileReader(file);
            char[] ins = new char[1000000];
            int len = in.read(ins);
            if(len == -1) return 0;
            players = new String(ins, 0, len);
        }catch(Exception e){
            e.printStackTrace();
        }

        String pass = players.substring(0, players.indexOf(' '));
        if(!pass.equals(password))
            return 2;   //密码不匹配
        return 3;                                  //登陆成功

    }

    static Player readPlayerFromTcp(String str){
        int index = str.indexOf('#');
        String name = str.substring(0, index);
        String password = str.substring(index+1) ;

        File file = new File("data/"+name+".player");

        String players = "";
        try {
            FileReader in = new FileReader(file);
            char[] ins = new char[1000000];
            int len = in.read(ins);
            if(len == -1) return null;
            players = new String(ins, 0, len);
        }catch(Exception e){
            e.printStackTrace();
        }
        int index1 = players.indexOf(" ");
        int index2 = players.indexOf(" ", index1 + 1);
        int index3 = players.indexOf(" ", index2 + 1);
        /*System.out.println(
                players
        );*/
       /* String name = players.substring(index1, index2);
        String numberOfPlay = players.substring(index2+1, index3);
        String numberOfWin = players.substring(index3+1, index4);
        String score = players.substring(index4+1, players.indexOf('\n', index4));
*/
        int numberOfPlay = Integer.parseInt(players.substring(index1+1, index2));
        int numberOfWin = Integer.parseInt(players.substring(index2+1, index3));
        int score = Integer.parseInt(players.substring(index3+1, players.indexOf('\n', index3)).trim());

        /*System.out.println(name);
        System.out.println(numberOfPlay);
        System.out.println(numberOfWin);
        System.out.println(score);*/

        return new Player(password , name, numberOfPlay, numberOfWin, score);
    }

    static void createPlayer(  String str ){
        int index = str.indexOf('#');
        String name = str.substring(0, index);
        String password = str.substring(index+1) ;

        File file = new File("data/"+name+".player");
        try {
            file.createNewFile();
            FileWriter out = new FileWriter(file);
            out.write(password + " 0 0 10\n");
            out.close();
        } catch(Exception e ){
            e.printStackTrace();
        }

    }

    void savePlayer(){
        File file = new File("data/"+name+".player");
        String str = String.format("%s %d %d %d", password, numberOfPlay, numberOfWin, score);
        try {
            FileWriter out = new FileWriter(file);
            out.write(str);
            out.close();
        } catch(Exception e ){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

    }

}
