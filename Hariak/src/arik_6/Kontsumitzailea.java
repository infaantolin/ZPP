package arik_6;

public class Kontsumitzailea extends Thread {

    private Buffer buffer;

    public Kontsumitzailea(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) { // 10 karaktere jaso
        	char c = buffer.jaso();
            try {
                Thread.sleep(600); // Kontsumitzailea motelagoa
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(">>> Kontsumitzailea amaitu da.");
    }
}

