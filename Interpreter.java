import java.io.IOException;

public class Interpreter{
    private Memory memory;
    private CPU cpu;

    public Interpreter(){
        memory = new Memory();
        cpu = new CPU();
    }

    public void execute() throws IOException{
        memory.initalizeRom("Pong.ch8");
        while(true){
            short instruction = memory.getInstruction(cpu.getPC());
            // handle instruction
        }
    }
}