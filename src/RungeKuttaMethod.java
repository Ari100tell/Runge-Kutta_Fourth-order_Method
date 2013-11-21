/**
 * Class realized Runge-Kutta fourth-order method
 */

import java.util.ArrayList;
import java.util.List;

public class RungeKuttaMethod {

    public List run() {
        int numberOfDigits = 13;                  //количество знаков после запятой приокруглении
        double y1, z1;     //значения переменных в уравнении
        double x0 = 0, y0 = 0, z0 = 0; //Начальные условия
        double k1, k2, k4, k3;
        double step = 0.02; // шаг
        double q1, q2, q4, q3;

        List<String> resultData = new ArrayList<String>();
        for (; r(x0, 2) < 0.2; x0 += step) {

            k1 = step * f(x0, y0, z0);
            q1 = step * g(x0, y0, z0);

            k2 = step * f(x0 + step / 2.0, y0 + q1 / 2.0, z0 + k1 / 2.0);
            q2 = step * g(x0 + step / 2.0, y0 + q1 / 2.0, z0 + k1 / 2.0);

            k3 = step * f(x0 + step / 2.0, y0 + q2 / 2.0, z0 + k2 / 2.0);
            q3 = step * g(x0 + step / 2.0, y0 + q2 / 2.0, z0 + k2 / 2.0);

            k4 = step * f(x0 + step, y0 + q3, z0 + k3);
            q4 = step * g(x0 + step, y0 + q3, z0 + k3);

            z1 = z0 + (k1 + 2.0 * k2 + 2.0 * k3 + k4) / 6.0;
            y1 = y0 + (q1 + 2.0 * q2 + 2.0 * q3 + q4) / 6.0;
            Double x1 = x0 + step;
            Double analiticalResult = -Math.exp(2 * x1) + 0.5 * (Math.exp(3 * x1) + Math.exp(x1));
            resultData.add(r(x0 + step, numberOfDigits) + "\t" + r(y1, numberOfDigits) + "\t" + r(analiticalResult, numberOfDigits) + "\n");
            y0 = y1;
            z0 = z1;
        }
        return resultData;
    }

    /**
     * функция для округления и отбрасывания "хвоста"
     */
    public static double r(double value, int k) {
        return (double) Math.round((Math.pow(10, k) * value)) / Math.pow(10, k);
    }

    /**
     * функции, которые получаются из системы
     */
    public static double f(double x, double y, double z) {
        return (5 * z - 6 * y + Math.exp(x));
    }

    public static double g(double x, double y, double z) {
        return (z);
    }

}