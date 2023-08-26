import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

class ChatRoom extends JFrame {
    private JPanel pnl;
    private int num;
    private JSlider sld;
    private int num1 = 0;
    private int num2 = 100;
    private int num3 = 0;

    ChatRoom(int num) {
        this.num = num;
        initComponents(this.num);
    }

    void initComponents(int num) {
        setSize(400, 300);
        setTitle("Slider "+(this.num+1));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        sld = new JSlider(JSlider.HORIZONTAL, num1, num2, num3);
        sld.setOrientation(SwingConstants.VERTICAL);
        sld.setMajorTickSpacing(10);
        sld.setMinorTickSpacing(1);
        sld.setPaintTicks(true);
        sld.setPaintLabels(true);


        pnl = new JPanel();

        sld.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent event) {


                for (int i = 0; i < (MainWindow.chatRooms.length-1); i++) {
                    int value1 = (int)MainWindow.chatRooms[i].sld.getValue();
                    if(value1!=0){


                        JSlider source = (JSlider) event.getSource();

                        MainWindow.chatRooms[i+1].sld.setValue(source.getValue());




                    }
                }

                for (int i = 0; i < (MainWindow.chatRooms.length); i++) {
                    int value1 = (int)MainWindow.chatRooms[MainWindow.chatRooms.length-(i+1)].sld.getValue();
                    if(value1!=0){


                        //JSlider source = (JSlider) event.getSource();

                        MainWindow.chatRooms[i].sld.setValue(MainWindow.chatRooms[MainWindow.chatRooms.length-(i+1)].sld.getValue());




                    }
                }



            }
        });
        add(sld);



    }
}
class MainWindow extends JFrame {
    private JButton btnJoin;
    private JTextField txtUserName;
    private JPanel pnlName;
    private JPanel pnlBtn;

    static ChatRoom[] chatRooms;

    MainWindow() {
        initComponents();
    }

    void extendsArray() {
        ChatRoom[] temp = new ChatRoom[chatRooms.length + 1];
        for (int i = 0; i < chatRooms.length; i++) {
            temp[i] = chatRooms[i];
        }
        chatRooms = temp;

    }

    void initComponents() {
        setSize(400, 300);
        setTitle("Main Window");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        chatRooms = new ChatRoom[0];

        pnlName = new JPanel();
        pnlBtn = new JPanel();



        btnJoin = new JButton("Create");
        btnJoin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnJoin.addActionListener((event) -> {

            extendsArray();
            int num = (chatRooms.length-1);

            ChatRoom c1 = new ChatRoom(num);
            chatRooms[chatRooms.length - 1] = c1;

            System.out.println(chatRooms.length);

            c1.setVisible(true);
        });

        pnlBtn.add(btnJoin);

        add(pnlBtn, BorderLayout.CENTER);

    }
}

class Main {
    public static void main(String args[]) {
        new MainWindow().setVisible(true);
    }
}

