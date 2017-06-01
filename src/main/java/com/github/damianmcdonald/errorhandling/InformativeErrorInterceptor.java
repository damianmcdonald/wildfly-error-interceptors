package com.github.damianmcdonald.errorhandling;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.util.Map;
import java.util.logging.Level;
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
            // Get an instance of the interceptorBinding
            final InformativeErrorInterceptorBinding interceptorBinding = context.getMethod().getAnnotation(InformativeErrorInterceptorBinding.class);
            // we can pass the parameters of the failed method to the exception
            // in case we want to extract them for display in the error message to the user
            final Object[] parameters = (context.getParameters() != null && context.getParameters().length != 0) ? context.getParameters() : null;
            // Each instance of InformativeErrorInterceptorBinding has a UUID error reference that should be
            // used to identify this issue in the log file and displayed to the end user.
            // This UUID functions like a correlation ID
            final InformativeErrorException informativeException =  new InformativeErrorException(interceptorBinding.errorMsg(), ex, parameters);
            // In this exception handler we can add any specific logic such as sending an admin an email.
            // The InvocationContext allows you to access attributes of the invocation as shown below
            logger.log(Level.INFO, "Error reference UUID == {0}", new Object[]{informativeException.getErrorReference()});
            logger.log(Level.INFO, "{0} >>> context.getMethod() == {1}", new Object[]{informativeException.getErrorReference(),context.getMethod()});
            logger.log(Level.INFO, "{0} >>> context.getTarget() == {1}", new Object[]{informativeException.getErrorReference(),context.getTarget()});
            for (int i=0; i<context.getParameters().length; i++) {
                logger.log(Level.INFO, "{0} >>> Param[{1}] == {2}", new Object[]{informativeException.getErrorReference(),i,context.getParameters()[i].toString()});
            }
            for (Map.Entry<String, Object> entry : context.getContextData().entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                logger.log(Level.INFO, "{0} >>> Key ==  {1}, object == {2}", new Object[]{informativeException.getErrorReference(),key.toString(),value.toString()});
            }
            ex.printStackTrace();
            throw informativeException;
        }
    }
}
