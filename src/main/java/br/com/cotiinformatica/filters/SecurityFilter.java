package br.com.cotiinformatica.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SecurityFilter implements Filter {

	public SecurityFilter() {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		if(((HttpServletRequest) request).getServletPath().contains("/admin/")) {
			
			if(((HttpServletRequest) request).getSession().getAttribute("auth_usuario") == null) {
			
				((HttpServletResponse) response).sendRedirect("/projeto_contas/");
			}			
		}
		
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {

	}
}



