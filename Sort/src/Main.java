import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Main extends JFrame implements ActionListener, ChangeListener{

    Frame f1 = new Frame();
    String[] choses = new String[] {"bubble","merge","insertion","selection","quick" };
    JComboBox step = new JComboBox(choses);
    JSlider js = new JSlider(5,250);

    public Main() {
        super.setTitle("Sorting Visualizer");
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());

        cp.add(f1);

        JButton go = new JButton("sort");
        JButton reset = new JButton("reset");
        JPanel jp = new JPanel();
        jp.setBackground(Color.DARK_GRAY);
        jp.add(step);
        jp.add(go);
        jp.add(reset);
        jp.add(js);
        js.addChangeListener(this);
        go.addActionListener(this);

        reset.addActionListener(e -> f1.reset());

        cp.add(jp, BorderLayout.SOUTH);

        this.setBounds(100, 70, 700, 500);
    }


    public static void main(String[] args) {
        JFrame f = new Main();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = String.valueOf(step.getSelectedItem());

        if ("bubble".equals(cmd)) {
            new Thread(() -> f1.bubble()).start();
        }else if("merge".equals(cmd)) {
            new Thread(() -> f1.mergeSort(f1.height,0,f1.height.length-1)).start();
        }else if("insertion".equals(cmd)) {
            new Thread(() -> f1.insertion()).start();
        }else if("selection".equals(cmd)) {
            new Thread(() -> f1.selection()).start();
        }else if("quick".equals(cmd)) {
            new Thread(() -> f1.quick(f1.height,0,f1.height.length-1)).start();
        }

    }

    @Override
    public void stateChanged(ChangeEvent e) {
        f1.speed = js.getValue();

    }

}