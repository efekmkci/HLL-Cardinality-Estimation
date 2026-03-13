# HyperLogLog Algorithm Implementation & Cardinality Analysis

Bu proje, **Büyük Veri Analitiği** kapsamında, devasa veri kümelerinde benzersiz öğe sayısını (cardinality) düşük bellek kullanımıyla tahmin etmek için kullanılan **HyperLogLog (HLL)** algoritmasının Java ile sıfırdan gerçeklenmesidir.

## 🤖 Agentic Kodlama Yaklaşımı
Bu proje, geleneksel kodlama yöntemlerinden farklı olarak **Agentic Coding** prensipleriyle geliştirilmiştir. Geliştirme sürecinde bir yapay zeka asistanı (Gemini) ile şu adımlar izlenmiştir:
1.  **Konsept Tasarımı:** Algoritmanın matematiksel temelleri ve Java implementasyon stratejisi tartışıldı.
2.  **Iteratif Geliştirme:** İlk taslak kodlar oluşturulduktan sonra test çıktıları asistanla paylaşılarak hata ayıklama (debugging) yapıldı.
3.  **Optimizasyon:** Bit kaydırma işlemleri ve harmonik ortalama hesaplamaları asistanın önerileriyle optimize edildi.

## 🛠️ Teknik Detaylar
Algoritma, veriyi saklamak yerine hash değerleri üzerinden olasılıksal bir tahmin yürütür:
-   **Bucketing (Kovalama):** b=10 parametresi kullanılarak 1024 kova oluşturulmuştur.
-   **Hash Fonksiyonu:** 64-bitlik yüksek hassasiyetli bir hash yapısı kullanılmıştır.
-   **Harmonik Ortalama:** Uç değerlerin tahmini bozmaması için harmonik ortalama tercih edilmiştir.
-   **Düzeltme Faktörleri:** Küçük veri setleri için "Small Range Correction" uygulanmıştır.

## 📊 Matematiksel Analiz ve Hata Payı
Algoritmanın teorik standart hatası şu formülle hesaplanmaktadır:
Standard Error = 1.04 / sqrt(m)

Bu projede m = 1024 seçildiği için teorik hata payı **%3.25**'tir.
-   **Test Sonucu:** 10.000 benzersiz öğe için yapılan testte yaklaşık **10.083** sonucu alınmıştır.
-   **Gerçekleşen Sapma:** ~%0.8 (Teorik sınır olan %3.25'in oldukça altındadır).

## 🚀 Nasıl Çalıştırılır?
1.  `HyperLogLog.java` ve `Main.java` dosyalarını IntelliJ IDEA'da bir projeye ekleyin.
2.  `Main` sınıfını çalıştırarak konsol üzerinden HLL tahminlerini ve hata payı analizini gözlemleyebilirsiniz.

## 📁 Proje Yapısı
-   `HyperLogLog.java`: Algoritmanın çekirdek mantığı, kovalama ve hesaplama metodları.
-   `Main.java`: Test senaryoları ve sonuçların simülasyonu.

---

###  Sorular ve Cevaplar
-   **HLL Nedir?** Büyük veride çok az bellek harcayarak benzersiz öğe sayısını tahmin eden olasılıksal bir yapıdır.
-   **Hangi IDE/Model?** IntelliJ IDEA ve Gemini (Agentic Coding yaklaşımıyla).
-   **Analiz Yöntemi?** Hash değerlerindeki ardışık sıfırların sayısına dayanan olasılıksal analiz ve harmonik ortalama.
