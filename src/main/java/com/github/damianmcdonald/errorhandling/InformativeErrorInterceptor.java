package com.github.damianmcdonald.errorhandling;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.util.logging.Logger;

@Interceptor
@InformativeErrorInterceptorBinding
public class InformativeErrorInterceptor {

    private final static Logger logger = Logger.getLogger(InformativeErrorInterceptor.class.getName());

    @AroundInvoke
    public Object handleErrors(InvocationContext context) throws Exception {
        logger.fine(">> InformativeErrorInterceptor invoked");
        try {
          return context.proceed();
        } catch (Exception ex) {
            /*
            In this exception handler we can add any specific logic such as sending an admin an email.
            The InvocationContext allows you to access attributes of the invocation as shown below
            logger.info("context.getMethod() == " + context.getMethod());
            logger.info("context.getTarget() == " + context.getTarget());
            for (Object obj: context.getParameters()) {
                logger.info("Param == " + obj.toString());
            }
            for (Map.Entry<String, Object> entry : context.getContextData().entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                logger.info("Key ==  " + key + ", object == " + value.toString());
            }
            */
            ex.printStackTrace();
            final InformativeErrorInterceptorBinding interceptorBinding = context.getMethod().getAnnotation(InformativeErrorInterceptorBinding.class);
            throw new InformativeErrorException(interceptorBinding.errorMsg(), ex);
        }

    }
}
