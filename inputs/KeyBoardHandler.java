package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyBoardHandler implements KeyListener {

    @Override
    public void keyPressed(KeyEvent e) {
        
        int code = e.getKeyCode(); // return the integer code associate with the key event

        if(code == KeyEvent.VK_A) {
            System.out.println("A pressed");

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {
       
    }
    
}
