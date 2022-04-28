package school21;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface HtmlForm {
    public String fileName() default "formfile.html";
    public String action() default "/";
    public String method() default "post";

}
