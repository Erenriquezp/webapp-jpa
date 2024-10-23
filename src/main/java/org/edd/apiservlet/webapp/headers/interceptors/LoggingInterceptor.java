package org.edd.apiservlet.webapp.headers.interceptors;

import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;

import java.util.logging.Logger;

@Logging
@Interceptor
public class LoggingInterceptor {
    @Inject
    private Logger log;

    @AroundInvoke
    public Object logging(InvocationContext invocationContext) throws Exception {
        log.info("**** Method: " + invocationContext.getMethod().getName()
                + " called of the clase: " + invocationContext.getMethod().getDeclaringClass());
        Object result = invocationContext.proceed();
        log.info("**** Method: " + invocationContext.getMethod().getName()
                + " finished of the clase: " + invocationContext.getMethod().getDeclaringClass());
        return result;
    }
}
