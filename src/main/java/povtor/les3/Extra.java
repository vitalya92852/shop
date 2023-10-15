package povtor.les3;

public class Extra extends Discount{
    public double discountPrice(double price){
        if(price>100000 && price<150000){
            return 0.03;
        }
        else if (price>150000 && price<200000){
            return 0.05;
        }
        else if(price>200000){
            return 0.07;
        }
        else {
            return 0;
        }
    }
}
