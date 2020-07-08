package CityStructure;

import java.io.Serializable;

/**
 * класс, описывающий объекты типа Coordinates
 */
public class Coordinates implements Comparable<Coordinates>, Serializable {
    private Float x; //Максимальное значение поля: 349, Поле не может быть null
    private double y;

    public Coordinates(float X, double Y) {
        this.x = X;
        this.y = Y;
    }

    /**
     * @param coo объект класса Coordinates
     * @return координаты (0;0), если coo = null, иначе coo
     */
    public static Coordinates valueOf(Coordinates coo){
        if (coo == null){
            return new Coordinates(0,0);
        }else{
            return coo;
        }
    }

    public Float getX(){
        return this.x;
    }

    public double getY(){
        return this.y;
    }

    public void setX(Float X){
        this.x = X;
    }

    public void setY(double Y){
        this.y = Y;
    }

    /**
     * @param o объект класса Coordinates
     * @return результат сравнения
     */
    @Override
    public int compareTo(Coordinates o) {
        if ((this.getX() == o.getX()) && (this.getY() == o.getY())) {
            return 0;
        } else if (this.getY() - o.getY() > 0) {
            return 1;
        } else {
            return -1;
        }
    }
}