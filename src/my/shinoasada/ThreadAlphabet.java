package my.shinoasada;

import com.sun.org.apache.bcel.internal.generic.ATHROW;

public class ThreadAlphabet {
    public static void main(String[] args) {
        ThreadedAlphabetPrinter printer1 = new ThreadedAlphabetPrinter("First Printer");
        ThreadedAlphabetPrinter printer2 = new ThreadedAlphabetPrinter("Second Printer");
        printer1.start();
        printer2.start();
    }
}

class ThreadedAlphabetPrinter extends Thread {
    public ThreadedAlphabetPrinter(String name) {
        super(name);
    }

    public static String alphabet = "abcdefghijklmnopqrstuvwxyz";

    public static void printAlphabet() {
        System.out.println(alphabet);
    }

    @Override
    public void run() {
        System.out.print("Thread: " + getName() + ", ");
        printAlphabet();
    }
}
