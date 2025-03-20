package com.tracker.config.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.web.filter.OncePerRequestFilter;

import java.util.UUID;


public class ReqResponseTracingFilter extends OncePerRequestFilter {
    static final String CORRELATION_ID_HEADER = "correlationId";

    @Override
    protected void doFilterInternal(HttpServletRequest request, final HttpServletResponse response,
                                    final FilterChain chain)
            throws java.io.IOException, ServletException {
        String MDC_KEY = "UUID";
        try {
            final String uuid;
            if (!StringUtils.isEmpty(request.getHeader(CORRELATION_ID_HEADER))) {
                uuid = request.getHeader(CORRELATION_ID_HEADER);
            } else {
                uuid = UUID.randomUUID().toString().toUpperCase().replace("-", "");

            }
            MDC.put(MDC_KEY, uuid);
            response.addHeader(CORRELATION_ID_HEADER, uuid);
            chain.doFilter(request, response);
        } finally {
            MDC.remove(MDC_KEY);
        }
    }


}




