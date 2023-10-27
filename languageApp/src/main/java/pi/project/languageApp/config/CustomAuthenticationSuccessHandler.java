package pi.project.languageApp.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;
import java.util.Collection;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    //https://docs.spring.io/spring-security/site/docs/current/api/org/springframework/security/web/RedirectStrategy.html
    //https://docs.spring.io/spring-security/site/docs/4.0.x/apidocs/org/springframework/security/web/DefaultRedirectStrategy.html
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority grantedAuthority : authorities) {
            if (grantedAuthority.getAuthority().equals("ROLE_STUDENT")) {
                redirectStrategy.sendRedirect(request, response, "/martin/link");
                System.out.println("Student!");
                return;
            } else if (grantedAuthority.getAuthority().equals("ROLE_ADMIN")) {
                redirectStrategy.sendRedirect(request, response, "/languages/list");
                System.out.println("Admin!");
                return;
            }
            else if (grantedAuthority.getAuthority().equals("ROLE_TEACHER")) {
                redirectStrategy.sendRedirect(request, response, "/languages/list");
                System.out.println("Teacher!");
                return;
            }

        }
    }
}