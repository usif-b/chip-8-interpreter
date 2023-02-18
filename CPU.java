import java.util.Random;

public class CPU {
    private short pc;
    private short sp;
    private byte[] V;
    private short I;
    private short[] stack;

    public CPU(){
        pc = 0x200;
        sp = -1;
        V = new byte[16];
        I = 0;
        stack = new short[16];
    }

    public short getPC(){
        return pc;
    }

    public void OP_00E0(){
        //clear display
    }

    public void OP_00EE(){
        sp--;
        pc = stack[sp];
    }

    public void OP_1NNN(short address){
        pc = address;
    }

    public void OP_2NNN(short nnn){
        sp++;
        stack[sp] = pc;
        pc = nnn;
    }

    public void OP_3XNN(byte x, byte nn){
        if (V[x] == nn){
            pc +=4;
        } else{
            pc+=2;
        }
    }

    public void OP_4XNN(byte x, byte nn){
        if(V[x] != nn){
            pc+=2;
        }
    }

    public void OP_5XY0(byte x, byte y){
        if(V[x] == V[y]){
            pc+=2;
        }
    }

    public void OP_6XNN(byte x, byte nn){
        V[x] = nn;
    }

    public void OP_7XNN(byte x, byte nn){
        V[x] += nn;
    }

    public void OP_8XY0(byte x, byte y){
        V[x] = V[y];
    }

    public void OP_8XY1(byte x, byte y){
        V[x] |= V[y];
    }

    public void OP_8XY2(byte x, byte y){
        V[x] &= V[y];
    }

    public void OP_8XY3(byte x, byte y){
        V[x] ^= V[y];
    }

    public void OP_8XY4(byte x, byte y){
        short sum = (short)(V[x] + V[y]);
        if(sum > 0xFF){
            V[15] = 1;
        } else{
            V[15] = 0;
        }
        V[x] = (byte)sum;
    }

    public void OP_8XY5(byte x, byte y){
        if(V[x] > V[y]){
            V[15] = 1;
        } else{
            V[15] = 0;
        }
        V[x] -= V[y];
    }

    public void OP_8XY6(byte x){
        V[15] = (byte)(V[x] & 1);
        V[x] >>=1;
    }

    public void OP_8XY7(byte x, byte y){
        if(V[x] < V[y]){
            V[15] = 1;
        } else{
            V[15] = 0;
        }
        V[x] = (byte)(V[y] - V[x]);
    }

    public void OP_8XYE(byte x){
        V[15] = (byte)((V[x] & 0x80) >> 7);
        V[x] <<=1;
    }

    public void OP_9XY0(byte x, byte y){
        if(V[x] != V[y]){
            pc +=2;
        }
    }

    public void OP_ANNN(short nnn){
       I = nnn;
    }

    public void OP_BNNN(short nnn){
        pc = (short)(V[0] + nnn);
    }

    public void OP_CXNN(byte x, byte nn){
        Random r = new Random();
        V[x] = (byte) (r.nextInt(256) & nn);
    }

    public void OP_DXYN(){
        //Display n-byte sprite startign at memory location I at (Vx, Vy), set VF = collision
    }

    public void OP_EX9E(){
        //Skip next instruction if key with the value of Vx is pressed
    }

    public void OP_EXA1(){
        //Skip next instruction if key with the value of Vx is not pressed.
    }

    public void OP_FX07(){
        //Set Vx = delay timer value
    }

    public void OP_FX0A(){
        //Wait for a keypress, store the value of the key in Vx
    }

    public void OP_FX15(){
        //Set delay timer = Vx
    }

    public void OP_FX18(){
        //Set sound timer = Vx
    }

    public void OP_FX1E(byte x){
        I += V[x];
    }

    public void OP_FX29(){
        //Set I = location of sprite for digit Vx
    }

    public void OP_FX33(){
        //Store BCD representation of Vx in memory locations I, I+1, and I+1
        //The interpreter takes the decimal value of Vx, and places the hundreds digit in memory at location in I, the tens digit at location I+1, and the ones digit at location I+2.
    }

    public void OP_FX55(){
        //Store registers V0 through Vx in memory starting at location I
    }

    public void OP_FX65(){
        //Read registers V0 through Vx from memory starting at location I.
    }
}