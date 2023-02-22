public class NibbleUtil {
    public int getFirst(int instruction){
        return (instruction >> 12) & 0xF;
    }

    public int getSecond(int instruction) {
        return (instruction & 0x0F00) >> 8;
    }

    public int getThird(int instruction) {
        return (instruction & 0x00F0) >> 4;
    }
    
    public int getFourth(int instruction) {
        return instruction & 0x000F;
    }
    
    public byte getLastTwo(int instruction) {
        return (byte)(instruction & 0x00FF);
    }

    public short getLastThree(int instruction) {
        return (short)(instruction & 0x0FFF);
    }
}
