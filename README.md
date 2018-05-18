# shiro-freemarker


### 说明

 > 基于[shiro-freemarker-tags](https://github.com/zhoushuaichang/shiro-freemarker-tags) 添加一些新的逻辑


### Maven

``` xml
<dependency>
	<groupId>${project.groupId}</groupId>
	<artifactId>shiro-freemarker</artifactId>
	<version>${project.version}</version>
</dependency>
```

### Sample

普通的项目整合：
```java
cfg.setSharedVeriable("shiro", new ShiroTags());
```

Spring Boot整合：

```java
@Configuration
public class FreemarkerConfig {

	@Autowired
	private freemarker.template.Configuration configuration;
	
	/**
	 * Shiro整合Freemarker模板标签，使用方式参考：https://www.sojson.com/blog/143.html
	 * @throws TemplateModelException
	 */
	@PostConstruct
	public void setSharedVariable() throws TemplateModelException {
		configuration.setSharedVariable("shiro", new ShiroTags());
	}
	 
}
```

###标签使用 [参考](https://www.sojson.com/blog/143.html)：

1.guest（游客）
```xml
<@shiro.guest>
	您当前是游客，<a href="javascript:void(0);" class="dropdown-toggle qqlogin" >登录</a>
</@shiro.guest>
```
2.user（已经登录，或者记住我登录）
```xml
<@shiro.user>
	欢迎[<@shiro.principal/>]登录，<a href="/logout.shtml">退出</a>  
</@shiro.user>
```
3.authenticated（已经认证，排除记住我登录的）
```xml
<@shiro.authenticated>
	用户[<@shiro.principal/>]已身份验证通过
</@shiro.authenticated>
```
4.notAuthenticated（和authenticated相反）
```xml
<@shiro.notAuthenticated>
	当前身份未认证（包括记住我登录的）
</@shiro.notAuthenticated> 
```
这个功能主要用途，识别是不是本次操作登录过的，比如支付系统，进入系统可以用记住我的登录信息，但是当要关键操作的时候，需要进行认证识别。

5.principal标签，这个要稍微重点讲讲。好多博客都是一下带过。

principal标签，取值取的是你登录的时候。在Realm实现类中的如下代码：
```xml
....
return new SimpleAuthenticationInfo(user,user.getPswd(), getName());
```
在new SimpleAuthenticationInfo(第一个参数,....)的第一个参数放的如果是一个username，那么就可以直接用。
```xml
<!--取到username-->
<@shiro.principal/>
```
如果第一个参数放的是对象，比如我喜欢放User对象。那么如果要取username字段。
```xml
<!--需要指定property-->
<@shiro.principal property="username"/>
```
和Java如下Java代码一致
```xml
User user = (User)SecurityUtils.getSubject().getPrincipals();
String username = user.getUsername();
```
6.hasRole标签（判断是否拥有这个角色）
```xml
<@shiro.hasRole name="admin">  
    	用户[<@shiro.principal/>]拥有角色admin<br/>  
</@shiro.hasRole>   
```
7.hasAnyRoles标签（判断是否拥有这些角色的其中一个）
```xml
<@shiro.hasAnyRoles name="admin,user,member">  
    用户[<@shiro.principal/>]拥有角色admin或user或member<br/>  
</@shiro.hasAnyRoles>   
```
8.lacksRole标签（判断是否不拥有这个角色）
```xml
<@shiro.lacksRole name="admin">  
    用户[<@shiro.principal/>]不拥有admin角色
</@shiro.lacksRole>   
```
9.hasPermission标签（判断是否有拥有这个权限）
```xml
<@shiro.hasPermission name="user:add">  
    	用户[<@shiro.principal/>]拥有user:add权限
</@shiro.hasPermission>   
```
10.lacksPermission标签（判断是否没有这个权限）
```xml
<@shiro.lacksPermission name="user:add">  
    	用户[<@shiro.principal/>]不拥有user:add权限
</@shiro.lacksPermission>   
```