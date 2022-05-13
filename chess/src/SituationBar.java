import javax.swing.*;
import java.awt.*;

public class SituationBar {

     static JTextArea jt;
     static JScrollPane jsp;
     static JPanel panel;

     static void append(String s){
         jt.append(s);
         jsp.getVerticalScrollBar().setValue(jsp.getVerticalScrollBar().getMaximum());
     }

     static void replace(String s, int index1, int index2, String newString){
         String str = jt.getText();
         String str1 = str.substring(0,index1);
         String str3 = str.substring(index2);
         jt.setText(str1+ newString +str3);
     }

     static void createNewTextArea(){
        panel = new JPanel();

        jt = new JTextArea("",(int)(20/720.0 * SETTINGS.boardSize.width),(int)(30/720.0 * SETTINGS.boardSize.width));

        jt.setFont(new Font("楷体",Font.BOLD,20));
        jt.setEditable(false);
        jt.setLineWrap(true);
        jsp = new JScrollPane(jt);
        panel.add(jsp);
        panel.setBackground(Color.ORANGE);
        panel.setBounds((int) (SETTINGS.boardSize.width*1.03), (int)(SETTINGS.boardSize.height * 0.06), (int) (SETTINGS.boardSize.width*0.48),(int)(SETTINGS.boardSize.height * 0.65) );

     }

     static String getString(){
         return jt.getText();
     }
}
