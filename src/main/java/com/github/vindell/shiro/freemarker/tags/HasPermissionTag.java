package com.github.vindell.shiro.freemarker.tags;

/**
 * <p>Equivalent to {@link com.github.vindell.shiro.freemarker.tags.web.tags.HasPermissionTag}</p>
 *
 * @since 0.1
 */
public class HasPermissionTag extends PermissionTag {
    protected boolean showTagBody(String p) {
        return isPermitted(p);
    }
}
