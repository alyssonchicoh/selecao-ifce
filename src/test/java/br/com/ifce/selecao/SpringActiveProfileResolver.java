package br.com.ifce.selecao;


import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.test.context.ActiveProfilesResolver;

public class SpringActiveProfileResolver implements ActiveProfilesResolver {

    private String[] defaultActiveProfile = {"test"};
    private String[] defaultActiveProfileHomolog = {"production"};

    @Override
    public String[] resolve(Class<?> aClass) {
        final String activeProfileProperty = System.getProperty("spring.profiles.active");
        if(StringUtils.isNotBlank(activeProfileProperty) &&
                ArrayUtils.contains(defaultActiveProfileHomolog, activeProfileProperty)){
            defaultActiveProfile = new String[]{"homolog-test"};
        }
        return defaultActiveProfile;
    }
}
