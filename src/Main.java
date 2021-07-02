import Exceptions.HeapIsEmpty;
import Exceptions.HeapIsFull;

import java.util.Random;

public class Main {

    public static void main(String[] args){
        MinHeap heap = new MinHeap(10);
        Random random = new Random();

        System.out.println(">>>Creating heap...");
        for (int i = 0; i <= 10; i++){
            try {
                heap.insert(random.nextInt(20));
            }
            catch (HeapIsFull e){
                System.out.println(e);
            }
        }

        System.out.println("Done.");

        System.out.println(heap);

        System.out.println(">>>Deleting root element...");
        try {
            System.out.println("Root element: " + heap.popRoot());
        }
        catch (HeapIsEmpty e){
            System.out.println(e);
        }

        System.out.println(heap);
    }
}
