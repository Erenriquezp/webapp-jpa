package org.edd.apiservlet.webapp.headers.interceptors;

import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import org.edd.apiservlet.webapp.headers.configs.MysqlConn;
import org.edd.apiservlet.webapp.headers.services.ServiceJdbcException;

import java.sql.Connection;
import java.util.logging.Logger;

@TransactionalJdbc
@Interceptor
public class TransactionalInterceptor {

    @Inject
    @MysqlConn
    private Connection conn;

    @Inject
    private Logger log;

    @AroundInvoke
    private Object transactional(InvocationContext invocationContext) throws Exception {
        if (conn.getAutoCommit()) {
            conn.setAutoCommit(false);
        }
        try {
            log.info(" -----> Transactional started "+ invocationContext.getMethod().getName()
            + " of the class: " + invocationContext.getMethod().getDeclaringClass());
            Object result = invocationContext.proceed();
            conn.commit();
            log.info(" -----> Transactional finished "+ invocationContext.getMethod().getName()
            + " of the class: " + invocationContext.getMethod().getDeclaringClass());
            return result;
        } catch (ServiceJdbcException e) {
            conn.rollback();
            throw e;
        }
    }
}
