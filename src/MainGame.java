import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class MainGame extends JPanel implements ActionListener {
    private static final long serialVersionUID = 1L; //id сериализации
    public static JFrame frame; //создание переменной рамки
    public static final int SCALE = 32; //размер клетки 32х32
    public static final int ROW = 20; //количество клеток в окне по ширине
    public static final int COLUMN = 20; //количество клеток в окне по высоте
    public static int snakeSpeed = 10; //скорость змейки

    Snake snake = new Snake(5, 5, 5, 5);
    Food food = new Food(Math.abs((int) (Math.random() * MainGame.ROW - 1)), Math.abs((int) (Math.random() * MainGame.COLUMN - 1)));
    Timer timer = new Timer(1000 / snakeSpeed, this);

    public MainGame() {
        timer.start();
        addKeyListener(new KeyBoard());
        setFocusable(true);
    }

    public void paint(Graphics graphics) { //графический художник
        graphics.setColor(Color.WHITE); //цвет заднего фона
        graphics.fillRect(0, 0, ROW * SCALE, COLUMN * SCALE); //заливка заднего фона

        graphics.setColor(Color.red); //цвет еды
        graphics.fillOval(food.foodCordX * SCALE + 4, food.foodCordY * SCALE + 4, SCALE - 8, SCALE - 8);

        for (int row = 0; row < COLUMN * SCALE; row = row + SCALE) { //построение горизонтальных линий
            graphics.setColor(Color.BLUE);
            graphics.drawLine(0, row, ROW * SCALE, row);
        }

        for (int column = 0; column < ROW * SCALE; column = column + SCALE) { //построение вертикальных линий
            graphics.setColor(Color.BLUE);
            graphics.drawLine(column, 0, column, COLUMN * SCALE);
        }

        for (int i = 1; i < snake.snakeLenght; i++) { //отрисовка змейки
            graphics.setColor(Color.GREEN);
            graphics.fillRect(snake.snakeCordX[i] * SCALE + 3, snake.snakeCordY[i] * SCALE + 3, SCALE - 6, SCALE - 6);
            graphics.setColor(Color.BLACK);
            graphics.fillRect(snake.snakeCordX[0] * SCALE + 3, snake.snakeCordY[0] * SCALE + 3, SCALE - 6, SCALE - 6);

        }
    }

    public static void main(String[] args) {
        frame = new JFrame("Snake Game"); //создания окна с тайтлом
        frame.setSize(ROW * SCALE+17, COLUMN * SCALE+40); //установка размеров окна
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //установка операции закрытия
        frame.setResizable(true); //отмена масштабирования
        frame.setLocationRelativeTo(null); //установка окна по центру
        frame.add(new MainGame()); //добавляем отрисовщик
        frame.setVisible(true); //отображение окна
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        snake.move();
        if ((snake.snakeCordX[0] == food.foodCordX) && (snake.snakeCordY[0] == food.foodCordY)) {
            food.setRandomPosition();
            snake.snakeLenght++;
        }

        for (int i = 1; i < snake.snakeLenght; i++) {
            if ((snake.snakeCordX[i] == food.foodCordX) && (snake.snakeCordY[i] == food.foodCordY)) {
                food.setRandomPosition();
            }

            if ((snake.snakeCordX[0] == snake.snakeCordX[i]) && (snake.snakeCordY[0] == snake.snakeCordY[i])) {
                    timer.stop();
                    JOptionPane.showMessageDialog(null,"Game over");
                    frame.setVisible(false);
                    snake.snakeLenght = 2;
                    snake.directionMove = 0;
                    food.setRandomPosition();
                    frame.setVisible(true);
                    timer.start();
            }
            repaint();
        }
    }

    public class KeyBoard extends KeyAdapter {
        public void keyPressed(KeyEvent event){
            int key = event.getKeyCode(); //получаем события с клавиатуры

            if (key == KeyEvent.VK_UP && snake.directionMove != 1) snake.directionMove = 0; //клавиша вверх, обозначение 0, нельзя нажимать вниз
            if (key == KeyEvent.VK_DOWN && snake.directionMove != 0) snake.directionMove = 1; //клавиша вниз, обозначение 1, нельзя нажимать вверх
            if (key == KeyEvent.VK_RIGHT && snake.directionMove != 3) snake.directionMove = 2; //клавиша вправо, обозначение 2, нельзя нажимать влеов
            if (key == KeyEvent.VK_LEFT && snake.directionMove != 2) snake.directionMove = 3; //клавиша влево, обозначение 3, нельзя нажимать вправо
        }
    }
}



