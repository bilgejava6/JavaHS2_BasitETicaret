package com.muhammet.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class EticaretSecurityConfig {

    @Bean
    public JwtTokenFilter jwtTokenFilter() {
        return new JwtTokenFilter();
    }

    /**
     * Spring Security için SecurityFilterChain gereklidir ve uygulama ayağa kalkarken bu sınıftan
     * bir nesne BEAN yaratılarak gerekli nesen Sercurity ye girilir. Biz burada oluşturulacak olan
     * BEAN nesnesini manipüle edceğiz.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        /**
         *
         * belli URL adreslerine,  kullanıcılar tarafından girilmesine izin verilecek
         * belli adreslerin erişimi kapatılacak.
         * erişimi kapatılan adreslere kontrollü erişim ve rol tabanlı erişim sağlanacak.
         *
         */
        http.authorizeHttpRequests(req->
                req
                        .requestMatchers("/swagger-ui/**", "v3/api-docs/**", "/dev/v1/kullanici/**") // belli bir URL adresine erişimi yönet.
                        .permitAll() // yukarıdaki adres yada adreslere izin ver.
                        .requestMatchers("/dev/v1/kategori/**").hasAuthority("KATEGORI_ADMIN")
                        .requestMatchers("/dev/v1/urun/**").hasAuthority("URUN_ADMIN")

                        // yukarıdaki, oturum açanın yetki kimliği USER, ADMIN, MANAGER .. tipinde ise erişime izin ver.
                        .anyRequest() // yapılan tüm istek türleri (/admin, /user/addUSer, comment/getById ...)
                        .authenticated() // oturum açma zorunululuğu getir.
                );
        /**
         * _csrf nedir?
         * sunucu ile istemci arasında güvenli iletişim için sunucunun otomatik vermiş olduğu özel bir id dir.
         * RESTAPI kullanımlarında iptal edilmelidir.
         */
         http.csrf(AbstractHttpConfigurer::disable);
       // http.formLogin(AbstractAuthenticationFilterConfigurer::permitAll);

        /**
         * Kullanıcıların sisteme nasıl giriş yapacakları. Yani kendi kimliklerini nasıl doğrulayacakları.
         */
        http.addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
