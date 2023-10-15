package povtor.les3;

import org.aspectj.weaver.ast.Or;

import java.util.HashSet;
import java.util.Set;

public class OrderMain {
    public static void main(String[] args) {
        Fixed fixed = new Fixed();
        Special special = new Special();
        Extra extra = new Extra();
        Set<Discount> discountSet = new HashSet<>();
//        discountSet.add(fixed);
        discountSet.add(special);
        discountSet.add(extra);
        Order o2 = new Order(300000,discountSet);
        System.out.println(o2.getTotalPrice());
        System.out.println(o2.printOrderDiscount(fixed));

    }
}
