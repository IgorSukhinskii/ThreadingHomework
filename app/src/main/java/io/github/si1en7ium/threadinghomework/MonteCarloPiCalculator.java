package io.github.si1en7ium.threadinghomework;

import java.util.Random;

public class MonteCarloPiCalculator {
    public static Double calculate(long iterations) {
        double pi;
        // Добавьте параллелизм здесь.
        pi = calculatePartial(iterations);
        return pi;
    }

    private static Double calculatePartial(long iterations) {
        Random rng = new Random();
        double x, y;
        long count = 0l; // количество точек, попавших в круг
        for (long i = 0; i < iterations; i++) {
            x = rng.nextDouble() - 0.5; // равномерно распределённая случайная величина в интервале
            y = rng.nextDouble() - 0.5; // от -0.5 до 0.5
            // неравенство круга: сумма квадратов координат меньше квадрата радиуса
            if (x * x + y * y <= 0.25) {
                count++;
            }
        }
        // отношение количества точек в круге к общему количеству точек приблизительно равно
        // отношению площадей круга и квадрата.
        // площадь квадрата со стороной 1 равна 1
        // значит, отношение площади круга и квадрата равно площади круга
        // S = pi * r * r, где r = 0.5
        // pi = S * 4
        return ((double) count / (double) iterations) * 4.0;
    }
}
