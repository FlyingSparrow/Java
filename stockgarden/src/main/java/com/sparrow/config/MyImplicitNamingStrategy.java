package com.sparrow.config;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.ImplicitBasicColumnNameSource;
import org.hibernate.boot.model.naming.ImplicitNamingStrategy;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;

/**
 * @author wangjianchun
 */
public class MyImplicitNamingStrategy extends ImplicitNamingStrategyJpaCompliantImpl implements ImplicitNamingStrategy {
    /**
     *
     */
    private static final long serialVersionUID = -1783599788222948168L;

    @Override
    public Identifier determineBasicColumnName(ImplicitBasicColumnNameSource source) {
        return convert(source);
    }

    private Identifier convert(ImplicitBasicColumnNameSource source) {
        if (StringUtils.isBlank(transformAttributePath(source.getAttributePath()))) {
            return toIdentifier(transformAttributePath(source.getAttributePath()),
                    source.getBuildingContext());
        }
        String newName = transformAttributePath(source.getAttributePath());
        return toIdentifier(newName, source.getBuildingContext());
    }
}
