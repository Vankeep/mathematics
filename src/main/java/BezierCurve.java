import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.ArrayList;

public class BezierCurve extends JPanel {

    private ArrayList<Point> p = new ArrayList<>();
    private final JSlider slider;
    private final JSlider slider2;

    public BezierCurve() {
        // Задаем размер панели
        setPreferredSize(new Dimension(600, 600));

        // Определяем минимальное, максимальное и начальное значение для слайдера
        int minSliderValue = 0;
        int maxSliderValue = 600;
        int initialSliderValue = 40;
        int line = 400;
        int startX = 100;
        int centreX = 300;
        int endX = 500;

        int prom1X = (startX+centreX)/2;
        int prom2X = (centreX+endX)/2;

        // Добавляем управляющие точки   
        p.add(new Point(startX, line));
        p.add(new Point(prom1X, line));
        p.add(new Point(prom1X, initialSliderValue));
        p.add(new Point(centreX, initialSliderValue));
        p.add(new Point(prom2X, initialSliderValue));
        p.add(new Point(prom2X, line));
        p.add(new Point(endX, line));


        // Создаем слайдер для оси Y и его параметры
        slider = new JSlider(JSlider.VERTICAL, minSliderValue, maxSliderValue, initialSliderValue);
        slider.setMajorTickSpacing(25);
        slider.setMinorTickSpacing(5);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                // Обновляем значение y при изменении слайдера
                p.get(2).y = slider.getValue();
                p.get(3).y = slider.getValue();
                p.get(4).y = slider.getValue();
                // Перерисовываем всё окно
                repaint();
            }
        });
        slider2 = new JSlider(JSlider.HORIZONTAL, startX, endX, centreX);
        slider2.setMajorTickSpacing(25);
        slider2.setMinorTickSpacing(5);
        slider2.setPaintTicks(true);
        slider2.setPaintLabels(true);
        slider2.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                // Обновите значение yCurve при изменении слайдера
                int centreX = slider2.getValue();
                p.get(1).x = (startX + centreX)/2;
                p.get(2).x = (startX + centreX)/2;
                p.get(3).x = centreX;
                p.get(4).x = (centreX + endX)/2;
                p.get(5).x = (centreX + endX)/2;
                // Перерисоваем всё окно
                repaint();
            }
        });

//        setLayout(new BorderLayout());
        add(slider);
        add(slider2);




    }

    // Формула кубической кривой Безье
    private Point bezierCubic(Point P0, Point P1, Point P2, Point P3, double t) {
        int x = (int) (Math.pow(1 - t, 3) * P0.x + 3 * Math.pow(1 - t, 2) * t * P1.x + 3 * (1 - t) * Math.pow(t, 2) * P2.x + Math.pow(t, 3) * P3.x);
        int y = (int) (Math.pow(1 - t, 3) * P0.y + 3 * Math.pow(1 - t, 2) * t * P1.y + 3 * (1 - t) * Math.pow(t, 2) * P2.y + Math.pow(t, 3) * P3.y);
        return new Point(x, y);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Рисуем кривую
        g2d.setColor(Color.BLUE);
        for (double t = 0.0; t <= 1; t=t+0.01) {
            Point p1 = bezierCubic(p.get(0), p.get(1), p.get(2), p.get(3), t);
            g2d.fillOval(p1.x-2, p1.y-2, 4, 4);
        }
        // Рисуем кривую
        g2d.setColor(Color.ORANGE);
        for (double t = 0.0; t <= 1; t=t+0.01) {
            Point p1 = bezierCubic(p.get(3), p.get(4), p.get(5), p.get(6), t);
            g2d.fillOval(p1.x-2, p1.y-2, 4, 4);
        }

        // Рисуем управляющие точки
        g2d.setColor(Color.RED);
        for (Point p : p) {
            g2d.fillOval(p.x - 5, p.y - 5, 10, 10);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Cubic Bezier Curve");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            BezierCurve bezierCurve = new BezierCurve();
            frame.add(bezierCurve);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
