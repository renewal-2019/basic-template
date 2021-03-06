#注解的相关说明
@ResponseBody
```
注解用于在HTTP的body中携带响应数据，默认是使用JSON的格式。
如果不加该注解，spring响应字符串类型，是跳转到模板页面或jsp页面的开发模式。
说白了：加上这个注解你开发的是一个数据接口，
不加这个注解你开发的是一个页面跳转控制器。
```
@RestController与@Controller
 ```
@Controller注解是开发中最常使用的注解，它的作用有两层含义：
一是告诉Spring，被该注解标注的类是一个Spring的Bean，需要被注入到Spring的上下文环境中。
二是该类里面所有被RequestMapping标注的注解都是HTTP服务端点。

@RestController相当于 @Controller和@ResponseBody结合。它有两层含义：
一是作为Controller的作用，将控制器类注入到Spring上下文环境，该类RequestMapping标注方法为HTTP服务端点。
二是作为ResponseBody的作用，请求响应默认使用的序列化方式是JSON，而不是跳转到jsp或模板页面。
```
@PathVariable 与@RequestParam
```
@DeleteMapping("/article/{id}")
public @ResponseBody AjaxResponse deleteArticle(@PathVariable Long id) {

@PostMapping("/article")
public @ResponseBody AjaxResponse deleteArticle(@RequestParam Long id) {
```
jackson相关
```
@JsonPropertyOrder(value={"pname1","pname2"}) 
改变子属性在JSON序列化中的默认定义的顺序。如：param1在先，param2在后。

@JsonIgnore 
排除某个属性不做序列化与反序列化

@JsonProperty(anotherName) 
为某个属性换一个名称，体现在JSON数据里面

@JsonInclude(JsonInclude.Include.NON_NULL) 
排除为空的元素不做序列化反序列化

@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8") 
指定日期类型的属性格式
```
字段校验相关
```
@Null	            被注释的元素必须为 null
@NotNull	        被注释的元素必须不为 null
@AssertTrue	        被注释的元素必须为 true
@AssertFalse	    被注释的元素必须为 false
@Min(value)	        被注释的元素必须是一个数字，其值必须大于等于指定的最小值
@Max(value)	        被注释的元素必须是一个数字，其值必须小于等于指定的最大值
@DecimalMin(value)	被注释的元素必须是一个数字，其值必须大于等于指定的最小值
@DecimalMax(value)	被注释的元素必须是一个数字，其值必须小于等于指定的最大值
@Size(max, min)	    被注释的元素的大小必须在指定的范围内
@Digits (integer, fraction)	被注释的元素必须是一个数字，其值必须在可接受的范围内
@Past	            被注释的元素必须是一个过去的日期
@Future	            被注释的元素必须是一个将来的日期
@Pattern(value)     被注释的元素必须符合指定的正则表达式
@Email	            被注释的元素必须是电子邮箱地址
@Length	            被注释的字符串的大小必须在指定的范围内
@NotEmpty	        被注释的字符串的必须非空
@Range	            被注释的元素必须在合适的范围内
```
