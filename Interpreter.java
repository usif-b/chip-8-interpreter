import java.io.IOException;

public class Interpreter{
    private Memory memory;
    private CPU cpu;
    private DisplayBoard board;
    private Display display;
    private Keyboard keyboard;
    private int delayTimer;
    private int soundTimer;

    public Interpreter(){
        memory = new Memory();
        cpu = new CPU();
        board = new DisplayBoard();
        keyboard = new Keyboard();
        display = new Display(board.getBoard());
        display.setFocusable(true);
        display.requestFocusInWindow();
        display.addKeyListener(keyboard);
    }

    public void execute() throws IOException{
        NibbleUtil nibble = new NibbleUtil();
        memory.initalizeRom("roms/Pong.ch8");
        while(true){
            int instruction = memory.getInstruction(cpu.getPC());
            cpu.incremenetPC();
            switch(nibble.getFirst(instruction)){
                case 0x0:
                    switch(nibble.getFourth(instruction)){
                        case 0x0:
                            cpu.OP_00E0(board);
                            break;
                        case 0xE:
                            cpu.OP_00EE();
                            break;
                        default:
                            cpu.incremenetPC();
                            break;
                    }
                    break;
                case 0x1:
                    cpu.OP_1NNN(nibble.getLastThree(instruction));
                    break;
                case 0x2:
                    cpu.OP_2NNN(nibble.getLastThree(instruction));
                    break;
                case 0x3:
                    cpu.OP_3XNN(nibble.getSecond(instruction), nibble.getLastTwo(instruction));
                    break;
                case 0x4:
                    cpu.OP_4XNN(nibble.getSecond(instruction), nibble.getLastTwo(instruction));
                    break;
                case 0x5:
                    cpu.OP_5XY0(nibble.getSecond(instruction), nibble.getThird(instruction));
                    break;
                case 0x6:
                    cpu.OP_6XNN(nibble.getSecond(instruction), nibble.getLastTwo(instruction));
                    break;
                case 0x7:
                    cpu.OP_7XNN(nibble.getSecond(instruction), nibble.getLastTwo(instruction));
                    break;
                case 0x8:
                    switch(nibble.getFourth(instruction)){
                        case 0x0:
                            cpu.OP_8XY0(nibble.getSecond(instruction), nibble.getThird(instruction));
                            break;
                        case 0x1:
                            cpu.OP_8XY1(nibble.getSecond(instruction), nibble.getThird(instruction));
                            break;
                        case 0x2:
                            cpu.OP_8XY2(nibble.getSecond(instruction), nibble.getThird(instruction));
                            break;
                        case 0x3:
                            cpu.OP_8XY3(nibble.getSecond(instruction), nibble.getThird(instruction));
                            break;
                        case 0x4:
                            cpu.OP_8XY4(nibble.getSecond(instruction), nibble.getThird(instruction));
                            break;
                        case 0x5:
                            cpu.OP_8XY5(nibble.getSecond(instruction), nibble.getThird(instruction));
                            break;
                        case 0x6:
                            cpu.OP_8XY6(nibble.getSecond(instruction));
                            break;
                        case 0x7:
                            cpu.OP_8XY7(nibble.getSecond(instruction), nibble.getThird(instruction));
                            break;
                        case 0xE:
                            cpu.OP_8XYE(nibble.getSecond(instruction));
                            break;                        
                    }
                    break;
                case 0x9:
                    cpu.OP_9XY0(nibble.getSecond(instruction), nibble.getThird(instruction));
                    break;
                case 0xA:
                    cpu.OP_ANNN(nibble.getLastThree(instruction));
                    break;
                case 0xB:
                    cpu.OP_BNNN(nibble.getLastThree(instruction));
                    break;
                case 0xC:
                    cpu.OP_CXNN(nibble.getSecond(instruction), nibble.getLastTwo(instruction));
                    break;
                case 0xD:
                    cpu.OP_DXYN(nibble.getSecond(instruction), nibble.getThird(instruction), nibble.getFourth(instruction), memory, board);
                    break;
                case 0xE:
                    switch(nibble.getFourth(instruction)){
                        case 0x1:
                            cpu.OP_EXA1(keyboard, nibble.getSecond(instruction));
                            break;
                        case 0xE:
                            cpu.OP_EX9E(keyboard, nibble.getSecond(instruction));
                            break;
                    }
                    break;
                case 0xF:
                    switch(nibble.getThird(instruction)){
                        case 0x0:
                            switch(nibble.getFourth(instruction)){
                                case 0x7:
                                    cpu.OP_FX07(delayTimer, nibble.getSecond(instruction));
                                    break;
                                case 0xA:
                                    for(int i = 0; i < 16; i++){
                                        if(keyboard.checkKey(i) == 1){
                                            cpu.OP_FX0A(i, nibble.getSecond(instruction));
                                        }else{
                                            cpu.decremenetPC();
                                        }
                                    }
                                    break;
                            }
                            break;
                        case 0x1:
                            switch(nibble.getFourth(instruction)){
                                case 0x5:
                                    delayTimer = cpu.OP_FX15(nibble.getSecond(instruction));
                                    break;
                                case 0x8:
                                    soundTimer = cpu.OP_FX18(nibble.getSecond(instruction));
                                    break;
                                case 0xE:
                                    cpu.OP_FX1E(nibble.getSecond(instruction));
                                    break;
                            }
                            break;
                        case 0x2:
                            cpu.OP_FX29(nibble.getSecond(instruction));
                            break;
                        case 0x3:
                            cpu.OP_FX33(nibble.getSecond(instruction), memory);
                            break;
                        case 0x5:
                            cpu.OP_FX55(nibble.getSecond(instruction), memory);
                            break;
                        case 0x6:
                            cpu.OP_FX65(nibble.getSecond(instruction), memory);
                            break;
                    }
                    break;
            }
            display.update(board.getBoard());
            if(delayTimer>0){
                --delayTimer;
            }
            if(soundTimer>0){
                --soundTimer;
            }
            try {
                Thread.sleep(3);
            } catch (InterruptedException e) {
                // Handle the exception if necessary
            }
            
        }
    }
}