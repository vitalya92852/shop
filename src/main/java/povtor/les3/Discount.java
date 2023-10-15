package povtor.les3;

import java.util.Objects;

public class Discount {

    public double discountPrice(double price){
        return 0;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClass());
    }
}
