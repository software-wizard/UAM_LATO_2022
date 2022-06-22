package pl.psi.shop;


public final class Money {
    private final int price;

    public Money(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public boolean haveEnoghMoney(Money money){
        if(this.getPrice() >= money.price)
            return true;
        return false;
    }


}
