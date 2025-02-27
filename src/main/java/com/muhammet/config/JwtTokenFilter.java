package com.muhammet.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

public class JwtTokenFilter extends OncePerRequestFilter {
    /**
     * DI -> constructor injection
     */
    @Autowired
    private JwtManager jwtManager;
    @Autowired
    private JwtUserDetails jwtUserDetails;
    /**
     *  Sunucuya gelen tüm isteklerin geçtiği nokta. hiç bir istek istisnası olmadan buradan geçer.
     *
     * @param request
     * @param response
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        /**
         * Gelen her istek kontrol edilecek ve içerisinde yer alan token bilgisi alınarak oturum açma ve yetkilendirme
         * işlemleri tamamlanacak.
         * -- DİKKAT!!!!!!
         * client - istemci token bilgisini HEADER içerisnde göndermelidir. Bu işin best practice i budur.
         * Sunucuya gönderilen isteğin yapısını konuşalım.
         * --------------
         * REQUEST
         * - URL => http://localhost:9090/dev/v1/urun/add-urun
         * - HEADER => configler yazılır. Content-Type: application/json, Authorization: Bearer [TOKEN]
         * - BODY => send data, JSON, FILE ..
         * - METHOD => POST, GET, HEADER, DELETE ...
         */
        final String requestHeaderAuthorization = request.getHeader("Authorization");
        System.out.println("header....: "+ requestHeaderAuthorization);
        /**
         * Artık buradan sonra token bilgisini alıp kullanıcının oturumunu yönetecek koflamaları yapmalıyız.
         */
        if(Objects.nonNull(requestHeaderAuthorization) && requestHeaderAuthorization.startsWith("Bearer ")){
            String token = requestHeaderAuthorization.substring(7);
            Optional<Long> userId =  jwtManager.validateToken(token);
            if(userId.isPresent()){ // token geçerli ve bir id var ise
                /**
                 * 1. İlgili kullanıcı id si ile kontrol yapılıp, bir kimlik artı oluşturuylur (UserDetails)
                 * 2. Spring in yönetebileceğin bir token oluşturulur. (AuthenticationToken)
                 * 3. Geçerli session içerisine bu token enjekte edilir. (holder içerisine ekleme)
                 */
                UserDetails userDetails = jwtUserDetails.loadUserById(userId.get());
                UsernamePasswordAuthenticationToken springToken = new
                                            UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(springToken);
            }
        }
        filterChain.doFilter(request,response);
    }
}
