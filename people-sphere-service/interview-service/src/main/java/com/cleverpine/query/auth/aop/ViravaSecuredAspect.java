package com.cleverpine.query.auth.aop;

import com.cleverpine.query.auth.ViravaSecured;
import com.cleverpine.query.auth.principal.PrincipalProvider;
import com.cleverpine.viravaspringhelper.aop.BaseViravaSecuredAspect;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ViravaSecuredAspect extends BaseViravaSecuredAspect {

    public ViravaSecuredAspect(PrincipalProvider principalProvider) {
        super(principalProvider);
    }

    @Before("@annotation(viravaSecured)")
    public void secure(JoinPoint joinPoint, ViravaSecured viravaSecured) {
        authorise(joinPoint, viravaSecured.resource(), viravaSecured.scope());
    }
}
