#说明
+ 本文档所有接口应答均为如下格式：

  成功应答：
```json
{
    "success": 0,
    "data": 6,
    "message": null
}
```

  失败应答：
```json
{
    "success": 1,
    "data": null,
    "message": "不支持手机号登录"
}
```

+ 接口参数说明

参数名称|类型|说明
:---:|:---:|:---:
success|int|业务执行状态 0:成功;1:失败
data|Object|业务执行结果 参照具体应答的参数说明
message|String|业务执行失败时的错误信息

+ 注意

1 本接口文档的所有接口均为Http请求格式，在使用接口时，应当先判断http返回码
2 仅当http返回码为200且success=0时，说明业务处理成功。此时应关注data字段返回的业务数据
3 当http返回码为其他，或http返回码200但success=1时，说明业务处理失败。此时应关注message字段给出的错误提示


## 1 登录日志
### 1.1 记录用户登录日志

+ 接口名称：记录用户登录日志
+ 接口地址：/logonLog/add
+ 请求方式：POST
+ 请求参数

| 字段名 | 变量名 | 必填 | 类型 | 示例值 | 描述 |
|:-----:|:-----:|:---:|:----:|:-----:|:---:|
| 登录时间 | createDatetime | 是 | String | 2018-05-05 12:33:00 | 登录的时间 |
| 登录用户id | userId | 是 | String | kabshkfbssefsefsebf | 登录的用户的id |
| 登录类型 | type | 是 | String | SIGNIN/SIGNOUT | 登录类型有两种：登入/登出 |
| 登录方式 | way | 是 | String |  | 登录方式 |
| 登录时的ip | ip | 是 | String | 192.168.3.125 | 登录的用户的ip地址 |
| 登录设备 | deviceId | 是 | String |  | 登录的用户的设备 |

+ 应答

| 字段名 | 变量名 |  类型 |描述 |
|:-----:|:-----:|:---:|:---:|

+ 响应示例
```
{
    "success": 0,
    "message": null
}
```


### 1.2 查找用户登录日志
+ 接口名称：查找用户登录日志
+ 接口地址：/logonLog/list
+ 请求方式：GET
+ 请求参数

| 字段名 | 变量名 | 必填 | 类型 | 示例值 | 描述 |
|:-----:|:-----:|:---:|:----:|:-----:|:---:|
| 登录时间 | startDate | 否 | String | 2018-05-05 12:33:00 | 以登录时间筛选 |
| 登录时间 | endDate | 否 | String | 2018-05-05 12:33:00 | 以登录时间筛选 |
| 登录用户id | userId | 否 | String | kabshkfbssefsefsebf | 以登录的用户的id筛选 |
| 登录类型 | type | 否 | String | SIGNIN/SIGNOUT | 以登录类型筛选 |
| 登录方式 | way | 否 | String |  | 以登录方式筛选 |
| 登录时的ip | ip | 否 | String | 192.168.3.125 | 以登录的用户的ip地址筛选 |
| 登录设备 | deviceId | 否 | String |  | 以登录的用户的设备筛选 |

+ 应答

| 字段名 | 变量名 |类型 | 描述 |
|:-----:|:-----:|:-----:|:---:|
| 日志的id | id |  String |   |
| 登录时间 | createDatetime |  String |   |
| 登录用户的id | userId | String |  |
| 登录类型 | type |  String |   |
| 登录方式 | way |  String |  |
| 登录时的ip | ip |  String |   |
| 登录设备 | deviceId |  String |   |

+ 响应示例
```
{
    "success": 0,
    "data": [
        {
            "id": 1,
            "createDatetime": "2018-07-23 12:47:00",
            "userId": "1",
            "type": "SIGNIN",
            "way": "web登入",
            "ip": "127.0.0.1",
            "deviceId": "apple"
        },......        
    ],
    "message": null
}
```


## 2 邮件日志
### 2.1 记录邮件发送日志
+ 接口名称：记录邮件发送日志
+ 接口地址：/emailLog/add
+ 请求方式：POST
+ 请求参数

| 字段名 | 变量名 | 必填 | 类型 | 示例值 | 描述 |
|:-----:|:-----:|:---:|:----:|:-----:|:---:|
| 邮件发送类型 | type | 是 | String |  | 邮件发送类型 |
| 收件人 | recipients | 是 | String | donwilliamlone@gmail.com | 收件人的邮箱地址 |
| 标题 | title | 是 | String | "邮箱验证" |  |
| 内容 | content | 是 | String | 请您立刻检出邮件内的链接 |  |
| 发送时间 | sendDate | 是 | String | 2018-05-01 12:21:00 | 发送的时间 |

+ 返回参数

| 字段名 | 变量名 | 类型 | 描述 |
|:-----:|:-----:|:-----:|:---:|



+ 响应示例
```
{
    "success": 0,
    "message": null
}
```

### 2.2 查找邮件发送日志
+ 接口名称：查找用户登录日志
+ 接口地址：/logonLog/list
+ 请求方式：GET
+ 请求参数

| 字段名 | 变量名 | 必填 | 类型 | 示例值 | 描述 |
|:-----:|:-----:|:---:|:----:|:-----:|:---:|
| 收件人 | recipients | 否 | String | donwilliamlone@gmail.com | 以邮箱地址查找 |
| 发送时间 | startDate | 否 | String | 2018-05-01 12:21:00 | 发送时间的起始查找 |
| 截止时间 | endDate | 否 | String | 2018-05-01 12:21:00 | 发送时间的截止查找 |

+ 返回参数

| 字段名 | 变量名 | 类型 |  描述 |
|:---:|:---:|:---:|:---:|
| 邮件发送类型 | type |  String  | 邮件发送类型 |
| 收件人 | recipients |  String |  收件人的邮箱地址 |
| 标题 | title |  String |   |
| 内容 | content |  String |   |
| 发送时间 | sendDate | String | 发送的时间 |

+ 响应示例
```
{
    "success": 0,
    "data": [
        {
            "id": 1,
            "type": "type",
            "recipients": "donwilliamlone@gmail.com",
            "title": "邮箱验证",
            "content": "请您立刻检出邮件内的链接",
            "sendDate": "2018-05-01 12:21:00"
        }
    ],
    "message": null
}
```
