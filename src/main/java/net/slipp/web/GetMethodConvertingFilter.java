package net.slipp.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class GetMethodConvertingFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		chain.doFilter(wrapRequest((HttpServletRequest) request), response);
		
	}

	private ServletRequest wrapRequest(HttpServletRequest request) {
		return new HttpServletRequestWrapper(request) {
            @Override
            public String getMethod() {
                return "GET";
            }
        };
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
