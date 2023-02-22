import java.util.Random;

public class CPU {
    private int pc;
    private int sp;
    private int[] V;
    private int index;
    private int[] stack;

    public CPU(){
        pc = 0x200;
        sp = 0;
        V = new int[16];
        index = 0;
        stack = new int[16];
    }

    public void decremenetPC(){
        pc -=2;
    }

    public void incremenetPC(){
        pc +=2;
    }

    public int getPC(){
        return pc;
    }

    public void OP_00E0(DisplayBoard board){
        board.clearBoard();
    }

    public void OP_00EE(){
        pc = stack[sp--];
    }

    public void OP_1NNN(int nnn){
        pc = nnn;
    }

    public void OP_2NNN(int nnn){
        stack[++sp] = pc;
        pc = nnn;
    }

    public void OP_3XNN(int x, int nn){
        if (V[x] == nn){
            incremenetPC();
        }
    }

    public void OP_4XNN(int x, int nn){
        if(V[x] != nn){
            incremenetPC();
        }
    }

    public void OP_5XY0(int x, int y){
        if(V[x] == V[y]){
            incremenetPC();
        }
    }

    public void OP_6XNN(int x, int nn){
        V[x] = nn;
    }

    public void OP_7XNN(int x, int nn){
        V[x] += nn;
    }

    public void OP_8XY0(int x, int y){
        V[x] = V[y];
    }

    public void OP_8XY1(int x, int y){
        V[x] |= V[y];
    }

    public void OP_8XY2(int x, int y){
        V[x] &= V[y];
    }

    public void OP_8XY3(int x, int y){
        V[x] ^= V[y];
    }

    public void OP_8XY4(int x, int y){
        int sum = V[x] + V[y];
        if(sum > 255){
            V[15] = 1;
        } else{
            V[15] = 0;
        }
        V[x] = sum & 255;
    }

    public void OP_8XY5(int x, int y){
        if(V[x] > V[y]){
            V[15] = 1;
        } else{
            V[15] = 0;
        }
        V[x] -= V[y];
    }

    public void OP_8XY6(int x){
        V[15] = V[x] & 1;
        V[x] >>=1;
    }

    public void OP_8XY7(int x, int y){
        if(V[y] > V[x]){
            V[15] = 1;
        } else{
            V[15] = 0;
        }
        V[x] = V[y] - V[x];
    }

    public void OP_8XYE(int x){
        V[15] = (V[x] & 0x80) >> 7;
        V[x] <<=1;
    }

    public void OP_9XY0(int x, int y){
        if(V[x] != V[y]){
            incremenetPC();
        }
    }

    public void OP_ANNN(int nnn){
        index = nnn;
    }

    public void OP_BNNN(int nnn){
        pc = V[0] + nnn;
    }

    public void OP_CXNN(int x, int nn){
        Random r = new Random();
        V[x] = r.nextInt(255) & nn;
    }

    public void OP_DXYN(int x, int y, int n, Memory memory, DisplayBoard board) {
    
        int xPos = V[x] % 64;
        int yPos = V[y] % 32;
    
        V[15] = 0;
            for (int i = 0; i < n; i++) {
                int spriteByte = memory.getByte(index + i);
                for (int j = 0; j < 8; j++) {
                    if ((spriteByte & (0x80 >> j)) != 0) {
                        int xCoord = xPos + j;
                        int yCoord = yPos + i;
    
                        if (xCoord < 64 && yCoord < 32) {
                            if (board.getPixel(xCoord, yCoord) == 1) {
                                V[15] = 1;
                            }
                            board.setPixel(xCoord, yCoord);
                        }
                    }
                }
            }
        }
    

    public void OP_EX9E(Keyboard keyboard, int x){
        if(keyboard.checkKey(V[x]) == 1){
            incremenetPC();
        }
    }

    public void OP_EXA1(Keyboard keyboard, int x){
        if(keyboard.checkKey(V[x]) == 0){
            incremenetPC();
        }
    }

    public void OP_FX07(int t, int x){
        V[x] = t;
    }

    public void OP_FX0A(int i, int x){
        V[x] = i;
    }

    public int OP_FX15(int x){
        return V[x];
    }

    public int OP_FX18(int x){
        return V[x];
    }

    public void OP_FX1E(int x){
        index += V[x];
    }

    public void OP_FX29(int x){
        int digit = V[x];
        index = 0x50 + (5 * digit);
    }

    public void OP_FX33(int x, Memory memory){
        int val = (byte)(V[x]);
        memory.setByte(index + 2, val % 10);
        val /= 10;
        memory.setByte(index + 1,val % 10);
        val /= 10;
        memory.setByte(index, val % 10);
    }

    public void OP_FX55(int x, Memory memory){
        for(int i = 0; i < x+1; i++){
            memory.setByte(index + i, V[i]);
        }
    }

    public void OP_FX65(int x, Memory memory){
        for(int i = 0; i < x+1; i++){
            V[i] = memory.getByte(index + i);
        }
    }
}