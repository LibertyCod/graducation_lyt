package com.graduation.project.filter;

import com.graduation.project.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

/**
 * 认证过滤器
 * </p>
 * @author leejianhao
 *
 */
public class JWTAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
    @Value(value = "${jwt.token_header}")
	private String jwtTokenHeader;

	@Override
	protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String jwtToken = request.getHeader(this.jwtTokenHeader);
		String userId = jwtTokenUtil.getUserIDFromToken(jwtToken);
        if (userId != null) {
			if (jwtTokenUtil.validateToken(jwtToken)) {
				SystemContext.setUserId(userId);
				UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
						userId, null, Collections.<GrantedAuthority>emptyList());
				SecurityContextHolder.getContext().setAuthentication(token);
			}
		}
		
		filterChain.doFilter(request, response);
	}
	
}