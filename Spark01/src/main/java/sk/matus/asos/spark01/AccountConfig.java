package sk.matus.asos.spark01;

import java.io.Serializable;

public class AccountConfig implements Serializable {
     public double rate = 0.0;

    public AccountConfig(double rate) {
        this.rate = rate;
    }
}
