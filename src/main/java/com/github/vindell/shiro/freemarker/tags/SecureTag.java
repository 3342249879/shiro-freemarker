package com.github.vindell.shiro.freemarker.tags;

import freemarker.core.Environment;
import freemarker.ext.beans.BeansWrapper;
import freemarker.ext.beans.BeansWrapperBuilder;
import freemarker.template.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import java.io.IOException;
import java.util.Map;

/**
 * <p>
 * Equivalent to {@link com.github.vindell.shiro.freemarker.tags.web.tags.SecureTag}
 * </p>
 */
public abstract class SecureTag implements TemplateDirectiveModel {
	
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
			throws TemplateException, IOException {
		verifyParameters(params);
		render(env, params, body);
	}

	public abstract void render(Environment env, Map params, TemplateDirectiveBody body)
			throws IOException, TemplateException;

	protected String getParam(Map params, String name) {
		Object value = params.get(name);

		if (value instanceof SimpleScalar) {
			return ((SimpleScalar) value).getAsString();
		}

		return null;
	}

	protected Subject getSubject() {
		return SecurityUtils.getSubject();
	}

	protected void verifyParameters(Map params) throws TemplateModelException {
	}

	protected void renderBody(Environment env, TemplateDirectiveBody body) throws IOException, TemplateException {
		if (body != null) {
			body.render(env.getOut());
		}
	}
	
	public static BeansWrapper getBeansWrapper(){
		BeansWrapper beansWrapper = new BeansWrapperBuilder(Configuration.VERSION_2_3_0).build();
		return beansWrapper;
	}
}
