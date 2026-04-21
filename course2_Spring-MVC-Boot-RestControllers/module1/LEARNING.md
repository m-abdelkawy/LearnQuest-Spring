# Externalize Configurations
* the values in application.properties are filtered through org.springframework.core.env.Environment object, 
that represents a handle on the environment tht the current application is running in 

* Through this object, we can access our properties originally from our application.properties file
and also system properties

```
@Inject
private Environment env;

@GetMapping("/test")
public String goHome(){
    String title = env.getProperty("salutation");
    String javaVersion = env.getProperty("java.runtime.version");
    return title + " " + javaVersion;
}
```

