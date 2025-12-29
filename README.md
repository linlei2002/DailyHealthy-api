# 每日健康打卡系统

项目结构：
``` 项目结构
common                          公共模块
    aop                         切面
    exception                   异常处理
    filter                      过滤器
    interceptor                 拦截器
    result                      返回结果集
    
config
    authentication              认证模块,完整码的发送
    system                      系统环境配置
   
controller                      控制层
entity                          实体类层
mapper                          SQL映射层
security                        安全模块
service                         业务逻辑层
transfer                        数据转换层 
    DTO                         前端传递数据
    VO                          后端返回数据
utils                           工具类层
DailyHealthyApplicaton.java     启动类
```

## 登录：
### 1. 后端通过前端传过来的账号和密码，根据用户ID或者username随机生成token。
### 2. 获取注册时用户的手机号，利用容联云发送验证码