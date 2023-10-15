package povtor.les3;

public class Special extends Discount{
    public double discountPrice(double price){
        if(price>250000){
            return 0.06;
        }
        else {
            return 0;
        }
    }
}
