import javax.swing.*;
        import java.awt.*;
        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class Game extends Actions {
    //GUI objects
    JFrame frame = new JFrame("Mines");
    JPanel panel = new JPanel(new GridLayout(10, 10));

    Game() {
        //Function to initiate the address of all bombs called
        initBombs(10);

        //All the buttons created
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                boxes[i][j] = new Box();
                boxes[i][j].addActionListener(new Listener());
                boxes[i][j].addMouseListener(new Mouse());

                //Buttons containing bombs are assigned a state of -1
                if (bombAddress.contains(i * 10 + j)) {
                    boxes[i][j].state = -1;
                }

                panel.add(boxes[i][j]);
            }
        }

        //state of the remaining buttons initialized
        initState();

        //GUI elements added and properties set
        frame.add(panel);

        frame.setSize(500, 500);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    //Inner Class for button Actions
    //ActionListener interface implemented
    class Listener implements ActionListener {

        @Override
        //sets the image to whatever the state is
        public void actionPerformed(ActionEvent actionEvent) {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    if (actionEvent.getSource() == boxes[i][j]) {
                        //Adding the icons based on the state of the box. i.e. no. of neighbors with bombs
                        if (boxes[i][j].state == -1) { //You lose the game in this case.
                            boxes[i][j].setIcon(new ImageIcon("mine.png"));
                            JOptionPane.showMessageDialog(null, "You Lose");
                            frame.dispose();
                        }
                        else {
                            Clicked(i,j);
                        }
                        if(noOfBoxesLeft == 0) JOptionPane.showMessageDialog(null, "You Win!");
                    }
                }
            }
        }
    }

    class Mouse extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    if (mouseEvent.getSource() == boxes[i][j]) {
                        // Adding Flag;
                        boxes[i][j].setIcon(new ImageIcon("flag.png"));
                        break;
                    }
                }
            }
        }
    }

    //Main method
    public static void main(String[] args) {
        Game game = new Game();
    }

}
