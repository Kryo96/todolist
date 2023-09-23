package progetto.react.com.counter.config;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
@Order(1)
public class JwtTokenFilter extends GenericFilterBean {

    @Value("${jwt.secret}")
    private String jwtSecret; // Load your secret key from application.properties or configuration

    private String extractTokenFromRequest(HttpServletRequest request) {
        // Extract the token from headers or cookies, depending on how the React app sends it
        // Example: request.getHeader("Authorization") or request.getCookies()
        Cookie [] cookie = request.getCookies();

        return null;
    }

    private Claims parseJwtToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(jwtSecret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            return null;
        }
    }

    private boolean hasAccessRights(Long userIdFromToken, HttpServletRequest request) {
        // Implement your access control logic here, e.g., checking if the user has access to the requested resource
        // You can retrieve additional information from the request to make the access decision
        return true; // Return true if the user has access, false otherwise
    }

    @Override
    public void doFilter(jakarta.servlet.ServletRequest servletRequest, jakarta.servlet.ServletResponse servletResponse, jakarta.servlet.FilterChain filterChain) throws IOException, jakarta.servlet.ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        // Retrieve the JWT token from the request's headers or cookies
        String token = extractTokenFromRequest(request);

        // Verify and parse the JWT token
        Claims claims = parseJwtToken(token);

        if (claims != null) {
            // Extract the user's ID from the claims
            Long userIdFromToken = claims.get("userId", Long.class);

            // Check if the user has access rights to the requested resource
            if (hasAccessRights(userIdFromToken, request)) {
                // Set the user's authentication in the security context (optional)
                SecurityContextHolder.getContext().setAuthentication(new JwtAuthenticationToken(claims));

                // Proceed with the request
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                // Handle unauthorized access
                servletResponse.setContentType("application/json");
                servletResponse.getWriter().write("{\"message\":\"Unauthorized access\"}");
                servletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
            }
        } else {
            // Handle invalid or expired token
            servletResponse.setContentType("application/json");
            servletResponse.getWriter().write("{\"message\":\"Invalid or expired token\"}");
            servletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        }
    }
}
