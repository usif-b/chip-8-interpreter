import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Display extends JPanel {
    private int[][] board;
    public Display(int [][] board) {
        this.board = board;
        JFrame frame = new JFrame("Chip-8 Interpreter");
        frame.add(this);
        frame.setSize(board.length*10+16, board[0].length * 10+38);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void update(int[][] board) {
        this.board = board;
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        for (int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                if(board[i][j] == 1) {
                    g2d.setColor(Color.white);
                } else{
                    g2d.setColor(Color.black);
                }
                g2d.fillRect(i*10, j*10, 10, 10);
            }
        }
    }
}