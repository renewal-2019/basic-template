#插件相关
## 1.GsonFormat
```
可用于快速将json转换为实体类
安装插件后,使用 Alt + s 调出代码生成页面
```
## 2.Rainbow Brackets
```
当代码中括号过多时可以清楚的识别括号的对应情况
```
## 3.Maven Helper
```
直接打开pom文件，即可查看依赖数，还能自动分析是否存在jar包冲突
```
## 3.Key promoter X
```
当鼠标操作有对应的快捷键时,会提示
```

# 加解密相关
```
在加密jar包所在文件位置执行命令:
java -cp jasypt-1.9.2.jar org.jasypt.intf.cli.JasyptPBEStringEncryptionCLI input=[明文] password=[密钥] algorithm=PBEWithMD5AndDES
开发过程中密钥存储在idea的环境变量中 设置为 swiftzsl
```

