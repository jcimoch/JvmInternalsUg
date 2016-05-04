package pl.jcimoch.ug;

class MemoryBuffer {
    public int[] buffer;

    public MemoryBuffer(int size) {
        buffer = new int[size];
    }
}

public class MemoryAllocator {
    public static MemoryBuffer alloc(int size) {
        return new MemoryBuffer(size * 1024 * 1024 / 4);
    }

}
