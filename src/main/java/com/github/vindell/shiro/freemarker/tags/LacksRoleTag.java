package com.github.vindell.shiro.freemarker.tags;

/**
 * <p>Equivalent to {@link com.github.vindell.shiro.freemarker.tags.web.tags.LacksRoleTag}</p>
 */
public class LacksRoleTag extends RoleTag {
    protected boolean showTagBody(String roleName) {
        boolean hasRole = getSubject() != null && getSubject().hasRole(roleName);
        return !hasRole;
    }
}
