package kata;

import java.util.Arrays;
import java.util.stream.DoubleStream;

public final class UniqueNumber {

    public static double findUniq(double arr[]) {
        for(int i = 0; i < arr.length - 2; i++) {
            if(arr[i] != arr[i + 1] && arr[i] != arr[i + 2]) {
                return arr[i];
            }
        }
        return arr[arr.length - 2] != arr[0]
                ? arr[arr.length - 2]
                : arr[arr.length - 1];
    }

}
