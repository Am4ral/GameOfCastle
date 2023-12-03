import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;

public class InterfaceSplash extends JWindow {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(InterfaceSplash::new);
    }

    public InterfaceSplash() {
        ImageIcon splashIcon = new ImageIcon("path/to/your/splash_image.png");
        JLabel splashLabel = new JLabel(splashIcon);
        getContentPane().add(splashLabel, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        try {
            TimeUnit.SECONDS.sleep(5);  // Adjust the time as needed
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        setVisible(false);
        dispose();
        showMainInterface();
    }

    private void showMainInterface() {
        SwingUtilities.invokeLater(() -> {
            InterfaceGrafica interfaceGrafica = new InterfaceGrafica();
            try {
                interfaceGrafica.exibirInterface();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
