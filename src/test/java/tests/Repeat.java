package tests;

import org.spockframework.runtime.extension.AbstractAnnotationDrivenExtension;
import org.spockframework.runtime.extension.AbstractMethodInterceptor;
import org.spockframework.runtime.extension.ExtensionAnnotation;
import org.spockframework.runtime.extension.IMethodInvocation;
import org.spockframework.runtime.model.FeatureInfo;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@ExtensionAnnotation(Repeat.RepeatExtension.class)
public @interface Repeat {
    int value() default 10;


    class RepeatExtension extends AbstractAnnotationDrivenExtension<Repeat> {

        @Override
        public void visitFeatureAnnotation(Repeat annotation, FeatureInfo feature) {
            feature.addInterceptor(new RepeatInterceptor(annotation.value()));
        }
    }

    class RepeatInterceptor extends AbstractMethodInterceptor {
        private final int count;

        public RepeatInterceptor(int count) {
            this.count = count;
        }

        @Override
        public void interceptFeatureExecution(IMethodInvocation invocation) throws Throwable {
            for (int i = 0; i < count; i++) {
                invocation.proceed();
            }
        }
    }
}
