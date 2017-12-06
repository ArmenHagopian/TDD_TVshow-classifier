import javax.swing.*;

public class Frame extends JFrame {

    private static final long serialVersionUID = 1L;


    public Frame(String title){
        //Defining visual aspects of the frame.
        //Name to be displayed on top of the frame
        setTitle(title);
        //Size of the frame
        setSize(520,480);
        //Permits to end the operation if you click on the exit button
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Avoids the user to change the size of the frame
        setResizable(true);
        // Centers the frame at launch
        setLocationRelativeTo(null);
        //Makes the frame visible
        setVisible(true);

        //Adding a panel to the frame on which items will be placed.
        add(new Panel());


    }


}
