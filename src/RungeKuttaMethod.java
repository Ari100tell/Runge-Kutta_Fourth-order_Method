
import java.util.ArrayList;
import java.util.List;

/**
 * Class for
 */
public class RungeKuttaMethod {
    
    public List run() {
        int k = 13;                  //количество знаков после запятой приокруглении
        double Xo, Yo, Y1, Zo, Z1;  //значения переменных в уравнении
        double k1, k2, k4, k3, h;
        double q1, q2, q4, q3;
        
                /*
                 *Начальные условия
                 */
        Xo = 0;
        Yo = 0;
        Zo = 0;

        h = 0.02; // шаг

        List<String> resultData=new ArrayList<String>();
        for(; r(Xo,2)<0.2; Xo += h){

            k1 = h * f(Xo, Yo, Zo);
            q1 = h * g(Xo, Yo, Zo);

            k2 = h * f(Xo + h/2.0, Yo + q1/2.0, Zo + k1/2.0);
            q2 = h * g(Xo + h/2.0, Yo + q1/2.0, Zo + k1/2.0);

            k3 = h * f(Xo + h/2.0, Yo + q2/2.0, Zo + k2/2.0);
            q3 = h * g(Xo + h/2.0, Yo + q2/2.0, Zo + k2/2.0);

            k4 = h * f(Xo + h, Yo + q3, Zo + k3);
            q4 = h * g(Xo + h, Yo + q3, Zo + k3);

            Z1 = Zo + (k1 + 2.0*k2 + 2.0*k3 + k4)/6.0;
            Y1 = Yo + (q1 + 2.0*q2 + 2.0*q3 + q4)/6.0;
            Double x1 = Xo+h;
            Double analiticalResult=-Math.exp(2*x1)+0.5*(Math.exp(3*x1)+Math.exp(x1));
            resultData.add(r(Xo + h, k) + "\t" + r(Y1 ,k) + "\t" + r(analiticalResult ,k)+"\n");            
            Yo = Y1;
            Zo = Z1;        
        }
        
        return resultData;
    }
    /**
     * функция для округления и отбрасывания "хвоста"
     */
    public static double r(double value, int k){
        return (double)Math.round((Math.pow(10, k)*value))/Math.pow(10, k);
    }
    /**
     * функции, которые получаются из системы
     */
    public static double f(double x, double y, double z){
        return (5*z-6*y+Math.exp(x));
    }
    public static double g(double x, double y, double z){
        return (z);
    }

}