package scheduler.rest.common;

import com.jayway.restassured.filter.Filter;
import com.jayway.restassured.filter.FilterContext;
import com.jayway.restassured.response.Cookie;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.FilterableRequestSpecification;
import com.jayway.restassured.specification.FilterableResponseSpecification;

import java.util.concurrent.atomic.AtomicReference;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

public class SecurityCookieFilter implements Filter {

    private final AtomicReference<String> securityToken = new AtomicReference<String>();
    private static final String COOKIE = "SPRING_SECURITY_REMEMBER_ME_COOKIE";

    public Response filter(final FilterableRequestSpecification requestSpec, final FilterableResponseSpecification responseSpec, final FilterContext ctx) {
        if (hasSecurityToken()) {
            requestSpec.cookie(new Cookie.Builder(COOKIE, securityToken.get()).setSecured(true).build());
        }

        final Response response = ctx.next(requestSpec, responseSpec);
        String currentSecurityToken = response.getCookie(COOKIE);

        if (isNotBlank(currentSecurityToken)) {
            securityToken.set(currentSecurityToken);
        }

        return response;
    }

    private boolean hasSecurityToken() {
        return securityToken.get() != null;
    }
}
