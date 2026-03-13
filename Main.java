public class Main {
    public static void main(String[] args) {
        // b=10 -> m=1024 kova
        HyperLogLog hll1 = new HyperLogLog(10);
        HyperLogLog hll2 = new HyperLogLog(10);

        // Birinci sete 5000 farklı veri ekle
        for (int i = 0; i < 5000; i++) {
            hll1.add("item_set1_" + i);
        }

        // İkinci sete 5000 farklı veri ekle
        for (int i = 0; i < 5000; i++) {
            hll2.add("item_set2_" + i);
        }

        System.out.println("HLL 1 Tahmini (Beklenen 5000): " + (long)hll1.estimate());
        System.out.println("HLL 2 Tahmini (Beklenen 5000): " + (long)hll2.estimate());

        hll1.merge(hll2);
        System.out.println("Birleştirilmiş Tahmin (Beklenen 10000): " + (long)hll1.estimate());

        double mVal = Math.pow(2, 10);
        double errorRate = (1.04 / Math.sqrt(mVal)) * 100;
        System.out.println("Teorik Hata Payı: %" + String.format("%.2f", errorRate));
    }
}