public class Jan27 {
    public static int[] randomNumberGenerator(int size, int MIN, int MAX) {
        int[] array = new int[size];

        for(int i = 0; i < size; i++) {
            array[i] = MIN + (int)(Math.random() * (MAX - MIN) + 1);
        }

        return array;
    }
}
