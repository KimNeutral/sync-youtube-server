package com.neutral.sync_youtube.common.security;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthRequiredInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // Http 요청 처리 전 수행할 로직 생성
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;

        if (!isHandlerMethodRequiresAuthentication(handlerMethod)
                && !isClassRequiresAuthentication(handlerMethod.getMethod().getDeclaringClass())) {
            return true;
        }

        return super.preHandle(request, response, handler);
    }

    private boolean isHandlerMethodRequiresAuthentication(HandlerMethod handlerMethod) {
        return handlerMethod.hasMethodAnnotation(AuthRequired.class);
    }

    private boolean isClassRequiresAuthentication(Class<?> c) {
        return c.isAnnotationPresent(AuthRequired.class);
    }
}
