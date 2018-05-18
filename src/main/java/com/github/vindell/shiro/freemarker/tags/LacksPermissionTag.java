package com.github.vindell.shiro.freemarker.tags;

/**
 * <p>
 * Equivalent to
 * {@link com.github.vindell.shiro.freemarker.tags.web.tags.LacksPermissionTag}
 * </p>
 */
public class LacksPermissionTag extends PermissionTag {
	
	protected boolean showTagBody(String p) {
		return !isPermitted(p);
	}
	
}
