package webtech2.faceplace.persistence;

import static java.lang.annotation.ElementType.*;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;
import javax.inject.Qualifier;

/**
 * Persistence
 * Copyright: TU Dortmund - Webtech2 - SS 2012
 * @created 07.06.2012 - 11:39:18
 */
/**
 * Denotes the applications persistence context.
 */
@Qualifier
@Target({ FIELD, METHOD, PARAMETER })
@Retention(RUNTIME)
public @interface Persistence {
	// parameterless qualifier
}
