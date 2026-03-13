import java.util.Arrays;

public class HyperLogLog {
    private final int b;
    private final int m;
    private final int[] registers;
    private final double alphaM;

    public HyperLogLog(int b) {
        this.b = b;
        this.m = 1 << b;
        this.registers = new int[m];
        this.alphaM = calculateAlphaM(m);
    }

    private double calculateAlphaM(int m) {
        if (m == 16) return 0.673;
        if (m == 32) return 0.697;
        if (m == 64) return 0.709;
        return 0.7213 / (1 + 1.079 / m);
    }

    private long hash(String val) {
        long h = 1125899906842597L;
        for (int i = 0; i < val.length(); i++) {
            h = 31 * h + val.hashCode(); // Basit ve hızlı bir karma
            h = (h ^ (h >>> 33)) * 0xff51afd7ed558ccdL;
            h = (h ^ (h >>> 33)) * 0xc4ceb9fe1a85ec53L;
            h = h ^ (h >>> 33);
        }
        return h;
    }

    public void add(String item) {
        long x = hash(item);
        int j = (int) (x >>> (64 - b)); // Kova seçimi
        long w = x << b;
        // En soldaki 1'in konumunu bul (0'ları say)
        int rho = Long.numberOfLeadingZeros(w | (1L << (b - 1))) + 1;
        if (rho > 64 - b) rho = 64 - b; // Sınırlandırma

        registers[j] = Math.max(registers[j], rho);
    }

    public double estimate() {
        double sum = 0;
        for (int r : registers) {
            sum += Math.pow(2, -r);
        }
        double estimate = alphaM * m * m * (1.0 / sum);

        if (estimate <= 2.5 * m) {
            int V = 0;
            for (int r : registers) {
                if (r == 0) V++;
            }
            if (V > 0) {
                estimate = m * Math.log((double) m / V);
            }
        }
        return estimate;
    }

    public void merge(HyperLogLog other) {
        for (int i = 0; i < m; i++) {
            this.registers[i] = Math.max(this.registers[i], other.registers[i]);
        }
    }
}