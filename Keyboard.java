import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {
    private int[] keys;

    public Keyboard(){
        keys = new int[16];
        for(int i = 0; i < keys.length; i++){
            keys[i] = 0;
        }
    }

    public void setKeyDown(int keyCode){
        switch(keyCode){
            case KeyEvent.VK_1:
                keys[0] = 1;
                break;
            case KeyEvent.VK_2:
                keys[1] = 1;
                break;
            case KeyEvent.VK_3:
                keys[2] = 1;
                break;
            case KeyEvent.VK_4:
                keys[3] = 1;
                break;
            case KeyEvent.VK_Q:
                keys[4] = 1;
                break;
            case KeyEvent.VK_W:
                keys[5] = 1;
                break;
            case KeyEvent.VK_E:
                keys[6] = 1;
                break;
            case KeyEvent.VK_R:
                keys[7] = 1;
                break;
            case KeyEvent.VK_A:
                keys[8] = 1;
                break;
            case KeyEvent.VK_S:
                keys[9] = 1;
                break;
            case KeyEvent.VK_D:
                keys[10] = 1;
                break;
            case KeyEvent.VK_F:
                keys[11] = 1;
                break;
            case KeyEvent.VK_Z:
                keys[12] = 1;
                break;
            case KeyEvent.VK_X:
                keys[13] = 1;
                break;
            case KeyEvent.VK_C:
                keys[14] = 1;
                break;
            case KeyEvent.VK_V:
                keys[15] = 1;
                break;
        }
    }
    
    public void setKeyUp(int keyCode){
        switch(keyCode){
            case KeyEvent.VK_1:
                keys[0] = 0;
                break;
            case KeyEvent.VK_2:
                keys[1] = 0;
                break;
            case KeyEvent.VK_3:
                keys[2] = 0;
                break;
            case KeyEvent.VK_4:
                keys[3] = 0;
                break;
            case KeyEvent.VK_Q:
                keys[4] = 0;
                break;
            case KeyEvent.VK_W:
                keys[5] = 0;
                break;
            case KeyEvent.VK_E:
                keys[6] = 0;
                break;
            case KeyEvent.VK_R:
                keys[7] = 0;
                break;
            case KeyEvent.VK_A:
                keys[8] = 0;
                break;
            case KeyEvent.VK_S:
                keys[9] = 0;
                break;
            case KeyEvent.VK_D:
                keys[10] = 0;
                break;
            case KeyEvent.VK_F:
                keys[11] = 0;
                break;
            case KeyEvent.VK_Z:
                keys[12] = 0;
                break;
            case KeyEvent.VK_X:
                keys[13] = 0;
                break;
            case KeyEvent.VK_C:
                keys[14] = 0;
                break;
            case KeyEvent.VK_V:
                keys[15] = 0;
                break;
        }
    }

    public int checkKey(int x){
        return keys[x];
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Not used
    }

    public void keyPressed(KeyEvent e) {
        setKeyDown(e.getKeyCode());
    }
    
    public void keyReleased(KeyEvent e) {
        setKeyUp(e.getKeyCode());
    }
}