package povtor.les1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите : 'Имя','Город','Возраст'");

        int count = 3;

        List<Human> humanList = new ArrayList<>();

        for (int i = 0; i < count; i++) {

            System.out.println("Введите имя :");
            String name = scanner.nextLine();

            if(name.equals("end")){
                int numCity = 1;
                for(Human human:humanList){
                    System.out.println("Город "+numCity+" "+"\n-- "+human.getCity()+"\n-- "+human.getName()+"\n-- "+human.getAge()+"\n");
                    numCity++;
                }
                break;
            }

            System.out.println("Введите город :");
            String city = scanner.nextLine();

            System.out.println("Введите возраст :");
            int age = Integer.parseInt(scanner.nextLine());

            Human human = new Human(name,city,age);

            if(humanList.contains(human)){
                System.out.println("Такой человек уже существует!");
                continue;
            }

            humanList.add(human);

            System.out.println("Человек успешно добавлен!");

            i--;

        }


    }
}
