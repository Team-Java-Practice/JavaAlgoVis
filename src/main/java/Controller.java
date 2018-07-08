public class Controller {

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                View.createGUI();
            }
        });
    }
}
