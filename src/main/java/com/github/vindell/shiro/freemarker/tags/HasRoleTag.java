package com.github.vindell.shiro.freemarker.tags;

/**
 * <p>Equivalent to {@link com.github.vindell.shiro.freemarker.tags.web.tags.HasRoleTag}</p>
 */
public class HasRoleTag extends RoleTag {
    protected boolean showTagBody(String roleName) {
        return getSubject() != null && getSubject().hasRole(roleName);
    }
}
