package arik_6;

public class Ekoizlea extends Thread {

    private Buffer buffer;

    public Ekoizlea(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) { // 10 karaktere sortu
            char letra = (char) ('A' + (int) (Math.random() * 26)); // Ausazko letra
            buffer.jarri(letra);

            try {
                Thread.sleep(300); // Ekoizlea azkarragoa izan dadin
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(">>> Ekoizlea amaitu da.");
    }
}