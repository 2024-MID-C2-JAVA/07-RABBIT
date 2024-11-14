package com.bank.management.interceptor;

import com.bank.management.data.DinHeader;
import com.bank.management.data.RequestMs;
import com.bank.management.context.DinHeaderContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.ContentCachingRequestWrapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.stream.Collectors;

@Component
public class DinHeaderInterceptor implements HandlerInterceptor {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        if (request instanceof ContentCachingRequestWrapper) {
            ContentCachingRequestWrapper cachingRequest = (ContentCachingRequestWrapper) request;
            String body = new String(cachingRequest.getContentAsByteArray(), request.getCharacterEncoding());

            if (!body.isEmpty()) {
                RequestMs<?> requestMs = objectMapper.readValue(body, RequestMs.class);
                DinHeader dinHeader = requestMs.getDinHeader();
                if (dinHeader != null) {
                    DinHeaderContext.setDinHeader(dinHeader);
                }
            }
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        DinHeaderContext.clear();
    }
}
