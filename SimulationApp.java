import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import javax.swing.*;

public class SimulationApp extends JPanel {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public static void main(String[] args) {
        new SimulationApp();
    }
    public SimulationApp() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                         UnsupportedLookAndFeelException ex) {
                }
                JFrame f = new JFrame();
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                f.setLayout(new BorderLayout());
                f.add(new TestPane());
                f.pack();
                f.setLocationRelativeTo(null);
                f.setBackground(Color.BLACK);
                f.setVisible(true);
            }
        });
    }

    public class TestPane extends JPanel {
        private World world = new World(screenSize.width , screenSize.height, 10, 10);
        public TestPane() {
            Timer timer = new Timer(40, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    world.advanceFrame();
                    repaint();
                }
            });
            timer.start();
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(screenSize.width, screenSize.height);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            super.setBackground(Color.black);
            Graphics2D gg = (Graphics2D) g;
            gg.setColor(Color.RED);
            gg.setBackground(Color.BLACK);
            for (Organism org : world.getOrganisms()) {
                Ellipse2D shape = new Ellipse2D.Double(org.getX() - org.getRadius(), org.getY() - org.getRadius(), org.getRadius() * 2 , org.getRadius() * 2);
                gg.fill(shape);
            }
            gg.setColor(Color.GREEN);
            for (Fuel fuel : world.getFuels()) {
                Ellipse2D shape = new Ellipse2D.Double(fuel.getX() - fuel.getRadius(), fuel.getY() - fuel.getRadius(), fuel.getRadius() * 2, fuel.getRadius() * 2);
                gg.fill(shape);
            }
        }
    }
}
