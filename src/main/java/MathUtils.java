import java.util.Collections;
import java.util.List;

public class MathUtils {
    public static Double computeAvg(List<Long> data) throws Exception {
        if (data.isEmpty()) {
            throw new Exception();
        }

        double sum = 0.;
        for (Long elem : data) {
            sum += elem;
        }

        return sum / data.size();
    }

    /*
    x = P / 100 * (N - 1), 0 < P < 100
    V(x) = V_n + {x} * (V_{n+1} - V_n), P < 100
    V(x) = V_n, P = 100

    {x} - fraction of x
     */
    public static Double computePercentile(List<Long> data, double percent) throws Exception {
        if (data.isEmpty() || percent < 0 || percent > 100) {
            throw new Exception();
        }

        Collections.sort(data);
        double x = (data.size() - 1) * percent / 100;
        Integer n = Math.toIntExact(Math.round(x - 0.5));

        if (n < data.size() - 1) {
            return (data.get(n) + x%1 * (data.get(n + 1) - data.get(n)));
        } else {
            return Double.valueOf(data.get(n));
        }
    }
}
