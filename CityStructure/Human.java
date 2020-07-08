package CityStructure;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Класс, описывающий объекты типа Human
 */
public class Human implements Comparable<Human>, Serializable {
    private Date birthday;

    /**
     * @param bday день рождения человека
     */
    public Human(Date bday) {
        this.birthday = bday;
    }

    /**
     * @param human объект класса Human
     * @return день рождения человека, возвращает "null", если поле bday человека равно null
     */
    public static String valueOf(Human human){
        if (human == null){
            return "null";
        }else {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return dateFormat.format(human.getBirthday());
        }
    }

    /**
     * @param o объект класса Human
     * @return результат сравнения
     */
    @Override
    public int compareTo(Human o) {
        if (o == null) {
            return 0;
        } else {
            return String.valueOf(this.getBirthday()).compareTo(String.valueOf(o.getBirthday()));
        }
    }

    /**
     * @return день рождения человека в формате String, возвращает "null", если поле bday человека равно null
     */
    public String toString(){
        return Human.valueOf(this);
    }

    public Date getBirthday(){
        return this.birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}