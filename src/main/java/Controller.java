public class Controller {
    private View view;
    private Model model;

    Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }

//    public void viewUpdated() {
//        view.draw(model);
//    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                View.createGUI();
            }
        });

    }
}
