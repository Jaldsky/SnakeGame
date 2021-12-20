public class Food {
    public int foodCordX;
    public int foodCordY;

    public Food(int foodCordX, int foodCordY){ //конструктор координат
        this.foodCordX = foodCordX;
        this.foodCordY = foodCordY;
    }

    public void setRandomPosition(){ //установка рандомных координат
        foodCordX = Math.abs ((int) (Math.random() * MainGame.ROW-1));
        foodCordY = Math.abs ((int) (Math.random() * MainGame.COLUMN-1));
    }


}
