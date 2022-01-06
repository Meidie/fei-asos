package sk.matus.asos.spark01;

import java.util.ArrayList;
import java.util.List;
import org.apache.spark.util.AccumulatorV2;

public class AccountAccumulator extends AccumulatorV2<String, List<String>> {

    private final List<String> loglist = new ArrayList<>();

    @Override
    public boolean isZero() {
        return true;
    }

    @Override
    public AccumulatorV2<String, List<String>> copy() {
        AccumulatorV2<String, List<String>> c = new AccountAccumulator();
        c.merge(this);
        return c;
    }

    @Override
    public void reset() {
        loglist.clear();
    }

    @Override
    public void add(String in) {
        loglist.add(in);
    }

    @Override
    public void merge(AccumulatorV2<String, List<String>> av) {
        loglist.addAll(av.value());
    }

    @Override
    public List<String> value() {
        return loglist;
    }
}
