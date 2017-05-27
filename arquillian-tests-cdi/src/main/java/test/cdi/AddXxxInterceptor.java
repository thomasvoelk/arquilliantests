package test.cdi;

import javax.interceptor.*;

@XxxIsAdded
@Interceptor
public class AddXxxInterceptor {
    @AroundInvoke
    public Object addXxx(InvocationContext context)
            throws Exception {
        System.out.println("Entering method: "
                + context.getMethod().getName() + " in class "
                + context.getMethod().getDeclaringClass().getName());
        String param = (String) context.getParameters()[0];
        context.getParameters()[0] = param + "Xxx";
        return context.proceed();
    }
}
