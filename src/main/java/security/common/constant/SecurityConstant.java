package security.common.constant;

/**
 * @author 水张哲
 */
public final class SecurityConstant {

    /** Swagger Whitelist  */
    public static final String[] SWAGGER_WHITELIST = {
            "/swagger-ui/**",
            "/swagger-resources/**",
            "/v3/api-docs",
            "/webjars/**",
            "/doc.html"
    };

    /** Druid Whitelist  */
    public static final String[] DRUID_WHITELIST = {
            "/druid/**"
    };

    /** System Whitelist */
    public static final String[] SYSTEM_WHITELIST = {

    };

    private SecurityConstant() {
        throw new IllegalStateException("Cannot create instance of static util class");
    }
}
