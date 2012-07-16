package web2.ls5.de.services;

import java.io.IOException;
import java.util.Set;

import org.apache.tapestry5.*;
import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.ioc.OrderedConfiguration;
import org.apache.tapestry5.ioc.ScopeConstants;
import org.apache.tapestry5.ioc.ServiceBinder;
import org.apache.tapestry5.ioc.annotations.Contribute;
import org.apache.tapestry5.ioc.annotations.InjectService;
import org.apache.tapestry5.ioc.annotations.Local;
import org.apache.tapestry5.ioc.annotations.Scope;
import org.apache.tapestry5.ioc.services.ClasspathURLConverter;
import org.apache.tapestry5.services.*;
import org.slf4j.Logger;
import web2.ls5.de.backend.ApplicationBackend;
import web2.ls5.de.boundries.GuiBoundry;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.UserTransaction;

/**
 * This module is automatically included as part of the Tapestry IoC Registry, it's a good place to
 * configure and extend Tapestry, or to place your own service definitions.
 */
public class AppModule
{
    public static void bind(ServiceBinder binder)
    {
        // binder.bind(MyServiceInterface.class, MyServiceImpl.class);

        // Make bind() calls on the binder object to define most IoC services.
        // Use service builder methods (example below) when the implementation
        // is provided inline, or requires more initialization than simply
        // invoking the constructor.
    }

    public static void contributeFactoryDefaults(
            MappedConfiguration<String, Object> configuration)
    {
        // The application version number is incorprated into URLs for some
        // assets. Web browsers will cache assets because of the far future expires
        // header. If existing assets are changed, the version number should also
        // change, to force the browser to download new versions. This overrides Tapesty's default
        // (a random hexadecimal number), but may be further overriden by DevelopmentModule or
        // QaModule.
        configuration.override(SymbolConstants.APPLICATION_VERSION, "1.0-SNAPSHOT");
    }

    public static void contributeApplicationDefaults(
            MappedConfiguration<String, Object> configuration)
    {
        // Contributions to ApplicationDefaults will override any contributions to
        // FactoryDefaults (with the same key). Here we're restricting the supported
        // locales to just "en" (English). As you add localised message catalogs and other assets,
        // you can extend this list of locales (it's a comma separated series of locale names;
        // the first locale name is the default when there's no reasonable match).
        configuration.add(SymbolConstants.SUPPORTED_LOCALES, "en");
    }

	/**
	 * Registers a custom URL converter to workaround TAP5-576.
	 */
	public static void contributeServiceOverride(MappedConfiguration<Class, Object> configuration) {
		configuration.add(ClasspathURLConverter.class, new JBoss7ClasspathURLConverterImpl());
	}


	public void contributePageRenderRequestHandler(OrderedConfiguration<PageRenderRequestFilter> configuration,
												   @InjectService("PageTransactionFilter") PageRenderRequestFilter pageTransactionFilter) {
		// The filter is registered if the tapestry application is embedded in the jboss, only.
		if (!Boolean.parseBoolean(System.getProperty("myapp.external"))) {
			// register the transaction filter.
			configuration.add("pageTransactionFilter", pageTransactionFilter);
		}
	}

	public void contributeComponentEventRequestHandler(OrderedConfiguration<ComponentEventRequestFilter> configuration,
													   @InjectService("ComponentTransactionFilter") ComponentEventRequestFilter componentTransactionFilter) {
		// The filter is registered if the tapestry application is embedded in the jboss, only.
		if (!Boolean.parseBoolean(System.getProperty("myapp.external"))) {
			// register the transaction filter.
			configuration.add("componentTransactionFilter", componentTransactionFilter);
		}
	}

	public void contributeAjaxComponentEventRequestHandler(OrderedConfiguration<ComponentEventRequestFilter> configuration,
														   @InjectService("AjaxTransactionFilter") ComponentEventRequestFilter ajaxComponentTransactionFilter) {
		configuration.add("ajaxComponentTransactionFilter", ajaxComponentTransactionFilter);
	}

	public PageRenderRequestFilter buildPageTransactionFilter(final Logger log) {
		return new PageRenderRequestFilter() {

			@Override
			public void handle(PageRenderRequestParameters parameters,
							   PageRenderRequestHandler handler) throws IOException {
				// begin transaction, process page render request and commit the transaction.
				// rollback the transaction, if an exception is thrown.
				try {
					UserTransaction tx = ControllerUtil.beginTransaction();
					log.info("### beginTransaction page");
					try {
						handler.handle(parameters);
						ControllerUtil.commitTransaction(tx);
						log.info("### commitTransaction page");
					}
					catch (Throwable e) {
						log.info("### rollbackTransaction page");
						ControllerUtil.rollbackTransaction(tx);
						throw e;
					}
				} catch (Throwable e) {
					throw new IllegalStateException(e);
				}
			}
		};
	}

	public ComponentEventRequestFilter buildComponentTransactionFilter(final Logger log) {
		return new ComponentEventRequestFilter() {

			@Override
			public void handle(ComponentEventRequestParameters parameters,
							   ComponentEventRequestHandler handler) throws IOException {
				try {
					// begin transaction, process page render request and commit the transaction.
					// rollback the transaction, if an exception is thrown.
					UserTransaction tx = ControllerUtil.beginTransaction();
					log.info("### beginTransaction component");
					try {
						handler.handle(parameters);
						ControllerUtil.commitTransaction(tx);
						log.info("### commitTransaction component");
					}
					catch (Throwable e) {
						log.info("### rollbackTransaction component");
						ControllerUtil.rollbackTransaction(tx);
						throw e;
					}
				} catch (Throwable e) {
					throw new IllegalStateException(e);
				}
			}
		};
	}

	public ComponentEventRequestFilter buildAjaxTransactionFilter(final Logger log) {
		return new ComponentEventRequestFilter() {

			@Override
			public void handle(ComponentEventRequestParameters parameters,
							   ComponentEventRequestHandler handler) throws IOException {
				try {
					// begin transaction, process page render request and commit the transaction.
					// rollback the transaction, if an exception is thrown.
					UserTransaction tx = ControllerUtil.beginTransaction();
					log.info("### beginTransaction ajax");
					try {
						handler.handle(parameters);
						ControllerUtil.commitTransaction(tx);
						log.info("### commitTransaction ajax");
					} catch (Throwable e) {
						log.info("### rollbackTransaction ajax");
						ControllerUtil.rollbackTransaction(tx);
						throw e;
					}
				} catch (Throwable e) {
					throw new IllegalStateException(e);
				}
			}
		};
	}


    /**
     * This is a service definition, the service will be named "TimingFilter". The interface,
     * RequestFilter, is used within the RequestHandler service pipeline, which is built from the
     * RequestHandler service configuration. Tapestry IoC is responsible for passing in an
     * appropriate Logger instance. Requests for static resources are handled at a higher level, so
     * this filter will only be invoked for Tapestry related requests.
     * <p/>
     * <p/>
     * Service builder methods are useful when the implementation is inline as an inner class
     * (as here) or require some other kind of special initialization. In most cases,
     * use the static bind() method instead.
     * <p/>
     * <p/>
     * If this method was named "build", then the service id would be taken from the
     * service interface and would be "RequestFilter".  Since Tapestry already defines
     * a service named "RequestFilter" we use an explicit service id that we can reference
     * inside the contribution method.
     */
    public RequestFilter buildTimingFilter(final Logger log)
    {
        return new RequestFilter()
        {
            public boolean service(Request request, Response response, RequestHandler handler)
                    throws IOException
            {
                long startTime = System.currentTimeMillis();

                try
                {
                    // The responsibility of a filter is to invoke the corresponding method
                    // in the handler. When you chain multiple filters together, each filter
                    // received a handler that is a bridge to the next filter.

                    return handler.service(request, response);
                } finally
                {
                    long elapsed = System.currentTimeMillis() - startTime;

                    log.info(String.format("Request time: %d ms", elapsed));
                }
            }
        };
    }

    /**
     * This is a contribution to the RequestHandler service configuration. This is how we extend
     * Tapestry using the timing filter. A common use for this kind of filter is transaction
     * management or security. The @Local annotation selects the desired service by type, but only
     * from the same module.  Without @Local, there would be an error due to the other service(s)
     * that implement RequestFilter (defined in other modules).
     */
    public void contributeRequestHandler(OrderedConfiguration<RequestFilter> configuration,
                                         @Local
                                         RequestFilter filter)
    {
        // Each contribution to an ordered configuration has a name, When necessary, you may
        // set constraints to precisely control the invocation order of the contributed filter
        // within the pipeline.

        configuration.add("Timing", filter);
    }



	@Scope(ScopeConstants.PERTHREAD)
	public EntityManager buildEntityManger() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("testapp");

		EntityManager em =  factory.createEntityManager();


		return em;

	}


	@SuppressWarnings("unchecked")
	private <T> T buildController(Class<T> clazz, String beanname) {
		try {
			InitialContext ctx = new InitialContext();

			BeanManager bmgr = (BeanManager) ctx.lookup("java:comp/BeanManager");
			Set<Bean<?>> beans = bmgr.getBeans(clazz);
			@SuppressWarnings("unchecked")
			Bean<T> bean = null;

			for (final Bean<?> b : beans) {
				if (b.getName() != null && b.getName().equals(beanname)) {
					bean = (Bean<T>) b;
					break;
				}
			}

			if (bean == null) {
				throw new IllegalStateException("Bean with name '" + beanname + "' of type '" + clazz.getName() + "' not found");
			}


			CreationalContext<T> cctx = bmgr.createCreationalContext(bean);
			@SuppressWarnings({"unchecked", "UnnecessaryLocalVariable"})
			T o = (T) bmgr.getReference(bean, clazz, cctx);

			return o;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}


	public ApplicationBackend buildApplicationBackend() {
		return buildController(ApplicationBackend.class, "applicationBackend");
	}

	public GuiBoundry buildGuiBoundry() {
		return buildController(GuiBoundry.class, "guiBoundry");
	}
}
