package povtor.les3;

import org.aspectj.weaver.ast.Or;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Order {
    private int price;
    private Set<Discount> discountSet;
    private double totalPrice;
    public Order(int price,Set<Discount> discountSet){

        this.price = price;
        this.discountSet = discountSet;

        try {
            totalPrice = countPrice(price,discountSet);
        } catch (MoreThanTwentyPercentException e) {
            System.out.println(e.getMessage());
            totalPrice = price;
        }



    }

    public Set<Discount> getDiscountSet() {
        return discountSet;
    }

    public void setDiscountSet(Set<Discount> discountSet) {
        this.discountSet = discountSet;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }


    public static double countPrice(int price,Set<Discount> discountSet){
        double totalDiscount = 0;

        for(Discount discount:discountSet){
            discountSet.add(discount);
        }
        for(Discount discount:discountSet){
            totalDiscount+= discount.discountPrice(price);
        }
        if(totalDiscount>0.20){
            throw new MoreThanTwentyPercentException("Скидка превышает 20%");
        }
        return price-price*totalDiscount;
    }

    public boolean printOrderDiscount(Discount discount) {
        for (Discount discount1 : this.discountSet) {
            if (discount1.equals(discount)) {
                return true;
            }
        }
        return false;
    }
}
