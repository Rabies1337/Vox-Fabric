package dev.rabies.vox.features;

import org.apache.commons.lang3.StringUtils;

public enum Category {
    LEGIT, RAGE, OTHER;

    @Override
    public String toString() {
        // 最初を大文字に
        return StringUtils.capitalize(name().toLowerCase());
    }
}
