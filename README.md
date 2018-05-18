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
	
	@PostConstruct
	public void setSharedVariable() throws TemplateModelException {
		configuration.setSharedVariable("shiro", new ShiroTags());
	}
	 
}
```