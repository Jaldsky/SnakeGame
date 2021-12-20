public class Snake {
    public int snakeLenght = 2; //длина змейки
    public int directionMove = 3; //направление передвижение
    //координаты передвижения
    public int snakeCordX[] = new int[300];
    public int snakeCordY[] = new int[300];

    public Snake(int snakeCordX1,int snakeCordY1, int snakeCordX2, int snakeCordY2){ //конструктор координат
        //координата головы змеи
        snakeCordX[0] = snakeCordX1;
        snakeCordY[0] = snakeCordY1;
        //координата туловища змеи
        snakeCordX[1] = snakeCordX2;
        snakeCordY[1] = snakeCordY2;
    }

    public void move(){ //каждая клетка занимает место предыдущей клетки змейки (логика сохранения тела змейки)
        for (int i = snakeLenght; i>0; i--){
            snakeCordX[i] = snakeCordX[i-1];
            snakeCordY[i] = snakeCordY[i-1];
        }

        if (directionMove == 0) snakeCordY[0]--; //передвижение головы вверх
        if (directionMove == 1) snakeCordY[0]++; //передвижение головы вправо
        if (directionMove == 2) snakeCordX[0]++; //передвижение головы вниз
        if (directionMove == 3) snakeCordX[0]--; //передвижение головы влево

        //зацикливание игрового поля
        if (snakeCordY[0] > MainGame.COLUMN-1) snakeCordY[0] = 0;
        if (snakeCordY[0] < 0) snakeCordY[0] = MainGame.COLUMN-1;
        if (snakeCordX[0] > MainGame.ROW-1) snakeCordX[0] = 0;
        if (snakeCordX[0] < 0) snakeCordX[0] = MainGame.ROW-1;
    }
}
