# mysql-explain-jr-plugin

这是一款jrebel插件，依赖jrebel启动，用于查看MySQL执行计划。


## 使用说明

- 依赖jrebel插件，idea自行安装并激活
- 通过jrebel启动项目时，Java启动参数中指定该插件位置

```
-Drebel.plugins=other-plugins,/path/your/mysql-explain-jr-plugin.jar
-Dmysql-explain-jr-plugin.showSQL=false                             
-Dmysql-explain-jr-plugin.filter=QRTZ_,COUNT(0)               
"-Dmysql-explain-jr-plugin.moreType=ALL"                            
"-Dmysql-explain-jr-plugin.moreExtra=Using filesort,Using temporary"
```

- 可通过配置文件指定属性，优先级低于jvm参数。配置文件默认为类路径下 `mysql-explain.properties`，可通过jvm参数指定名称：`-Dmysql-explain-properties-file=quick-dev.properties`

```properties
# 非必填项：是否打印所有执行的MySQL语句，默认false。设置true时，会根据[mysql.filter]过滤滤
mysql.showSQL=false

# 非必填项：MySQL explain执行过滤，按关键词匹配，英文逗号分割，比如：QRTZ_,COUNT(0)
mysql.filter=QRTZ_,COUNT(0)

# 非必填项：MySQL explain结果按[type]过滤，默认ALL，英文逗号分割，*打印所有
mysql.types=ALL

# 非必填项：MySQL explain结果按[Extra]过滤，默认Using filesort,Using temporary，英文逗号分割，*打印所有
mysql.extras=Using filesort,Using temporary
```

- 启动项目后，查看控制台输出。正常启动输出如下：

```
MySQL执行计划插件启用, 配置详情:
+---+----------------------------+--------------------------------------------+--------------------------------+------------------------------------------------------------------+
| # | config item                | current value                              | default value                  | remark                                                           |
+---+----------------------------+--------------------------------------------+--------------------------------+------------------------------------------------------------------+
| 1 | Print SQL                  | false                                      | true                           | true/false                                                       |
| 2 | Filter SQL by keywords     | QRTZ_,COUNT(0)                             |                                | Example: QRTZ_,COUNT(0)                                          |
| 3 | Optimization item by type  | ALL,index,*                                | ALL                            | system > const > eq_ref > ref > range > index > ALL              |
| 4 | Optimization item by Extra | Using where,Using filesort,Using temporary | Using filesort,Using temporary | Using filesort,Using temporary,Using where,Using index condition |
+---+----------------------------+--------------------------------------------+--------------------------------+------------------------------------------------------------------+
```