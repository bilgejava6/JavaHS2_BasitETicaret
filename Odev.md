# ÖDEV
        Konu: Spring boot back end projesi olacak. Amaç Kullanıcı üye olabilmeli ve doktor randevusu 
    alabilmeli.
## Hastahane Otomasyon
    * Entity
    - User(ad, soyad, email, sifre, telefon, adres, tc, yas,cinsiyet)
    - Randevu(userId, brans[örn: kbb, çocuk, kardio ... ],doktor, randevu günü, randevu saati, durumu)
    * İşlemler
    - Proje çatısını oluşturun.(paketler oluşturulur)
    - exceptions, restapis, ...
    - gerekli tüm bağımılılıklar eklensin.(lombok, db, mapstruct, security, validation, jwt, swagger-ui)
    - secutiry sınıflarını ekleyin. DİKKAT!!! kullanıcı ve userRole bunları - User/UserRole değiştirin.
    - gerekli sınıflar ve katmanları ekleyin.
    - iş akışını kodlayın
        - kullanıcı üye olabilir - security istisna ekleyin ve şifre yada token olmadan ulaşılabilsin.
        - giriş yapabilir - security istisna ekleyin ve şifre yada token olmadan ulaşılabilsin.
        - randevu alabilir, mutlaka spring security ile giriş yapma zorunululuğu olacak bu nedenle token ile
        işlem yapacağız.
        - randevularını görebilir. aktif / geçmiş randevularını görme şeklinde olabilir.
        - randevu iptal edebilir.
    DİKKAT!!!!
    - lombok kullanın
    - mapstruct kullanını DTO TO ENTITY
    - exception kullanın
    - validation kullanın