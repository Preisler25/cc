package preisler.com.crazy_counter.security;

import jakarta.servlet.*;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.ExpiredJwtException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@Component
public class JwtAuthenticationFilter implements Filter {

    private final JwtTokenProvider jwtTokenProvider;

    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        final HttpServletRequest httpRequest = (HttpServletRequest) request;
        final HttpServletResponse httpResponse = (HttpServletResponse) response;


        final String path = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());

        //getting the user ip
        String ipAddress = httpRequest.getRemoteAddr();
        System.out.println("IP: " + ipAddress);
        System.out.println("Path: " + path);

        System.out.println("Request: " + httpRequest.getMethod() + " " + httpRequest.getRequestURI());

        System.out.println(path.equals("/hangman/start"));
        //if the request is for login or register, let it pass
        if (path.equals("/user/login") || path.equals("/user/register") || path.equals("/file/get") || path.equals("/file/getAllNames" ) || path.equals("/hangman/start") || path.equals("/hangman/guess") || path.equals("/hangman/change")) {
            filterChain.doFilter(httpRequest, httpResponse);
            return;
        }

        final String authHeader = httpRequest.getHeader("Authorization");

        System.out.println("AuthHeader: " + authHeader);


            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                String jwtToken = authHeader.substring(7);
                try {
                    System.out.println("trying to validate token Token: " + jwtToken);
                    if (!jwtTokenProvider.validateToken(jwtToken)) {
                        httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token");
                        return;
                    }
                    else {
                        System.out.println("Token is valid");
                        filterChain.doFilter(httpRequest, httpResponse);
                    }

                } catch (ExpiredJwtException e) {
                    httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token expired");
                    return;
                }
            } else {
                httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authorization header not found");
                return;
            }
    }


    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
