package yuansim.exception;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import yuansim.view.BaseResource;
import yuansim.view.exception.DataException;
import yuansim.view.exception.ViewParamException;

/**
 * @QQ交流群: 648741281
 * @Email: 177300883312@163.com
 * @微信: londu19930418
 * @Author: Simon.Mr
 * @Created 2019/12/31
 */
@Aspect
@Component(value = "simFrameworkTemplateCoreExceptionAspect")
public class ExceptionAspect {

    @Pointcut("target(yuansim.view.controller.BaseRestController)")
    public void exception() {
    }

    @Around("exception()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) {

        // TODO: 2019/12/16 有时间 针对Controller 返回页面的返回指定错误页面处理
        Object ret;
        try {

            ret = proceedingJoinPoint.proceed();

        } catch (Throwable t) {
            t.printStackTrace();

            if (t instanceof ViewParamException) {

                return BaseResource.fromError(((ViewParamException) t).getName());

            }

            if(t instanceof DataException) {

                return BaseResource.fromError(((DataException) t).getName());
            }

            return BaseResource.fromError("系统开小差了");

        }

        return ret;

    }
}
