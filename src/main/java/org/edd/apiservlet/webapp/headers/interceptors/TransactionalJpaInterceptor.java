package org.edd.apiservlet.webapp.headers.interceptors;

import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import jakarta.persistence.EntityManager;
import org.edd.apiservlet.webapp.headers.services.ServiceJdbcException;

import java.util.logging.Logger;

@TransactionalJpa
@Interceptor
public class TransactionalJpaInterceptor {

    @Inject
    private EntityManager em;

    @Inject
    private Logger log;

    @AroundInvoke
    private Object transactional(InvocationContext invocationContext) throws Exception {
        try {
            log.info(" -----> Transactional started "+ invocationContext.getMethod().getName()
            + " of the class: " + invocationContext.getMethod().getDeclaringClass());
            em.getTransaction().begin();
            Object result = invocationContext.proceed();
            em.getTransaction().commit();
            log.info(" -----> Transactional finished "+ invocationContext.getMethod().getName()
            + " of the class: " + invocationContext.getMethod().getDeclaringClass());
            return result;
        } catch (ServiceJdbcException e) {
            em.getTransaction().rollback();
            throw e;
        }
    }
}
