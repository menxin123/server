# Minecraft Server 配置文件  
  
## 简介  
  
这个仓库包含了 Minecraft Server 及 MCDReforged 及Scarpet 的配置文件。
用以维护服务器配置，以及供他人复刻等价服务器测试。


## 推荐系统
* archlinux
* ubuntu
* fedora
* 类似的linux系统

## 依赖
* git
* python 3.10及以上
* MCDReforged(python module)
* java 17
* 记得输入以下命令以确保安装其他所需的python库
```
pip3 install -r requirements.txt
```
  
## 使用方法  
* 确保依赖安装完毕,并下载此仓库
* 在仓库文件夹内的`server/`文件夹下安装fabric的mc服务端启动器,并将启动器名称改为`fabric-server-launch.jar`
* 在仓库文件夹内的`mods/`文件夹下安装`mods/mods.md`文件内所列的模组
* 设置你的环境变量如果你需要自定义java路径，变量名为`MY_JAVA_PATH`
    * 如你的java路径为`path/bin/java`,那么变量值为`"path/bin"`
* 启动服务器在仓库文件夹下输入`python3 -m mcdreforged init`初始化`MCDR`
    * 输入`python3 -m mcdreforged`即可启动

## 关于Scarpet插件
* Scarpet是carpet的编程语言
* 服务器预装的插件有[waypoints](https://github.com/funny233-github/waypoint),[freecamera](https://github.com/funny233-github/freecamera)，用于替代部分MCDR插件
* 插件相关指令信息:
    * 显示路径点:`/waypoints list`
    * 添加路径点:`/waypoints add <name>` or `/waypoints add <name> <pos>` or `/waypoints add <name> <pos> <commit>`
    * 删除路径点:`/waypoints del <waypointname>`
    * 传送至路径点:`/waypoints tp <waypointname>`,需旁观或创造模式
    * 待开发指令:`/waypoints dashboard`
    * 高级灵魂出窍:`/freecamera`
  

  
## 注意事项  
  
* 在编辑配置文件之前，建议备份原始文件，以防止意外情况发生。  
* 请确保你理解你正在编辑的配置项的含义和影响，不要随意更改它们。  
* 如果你的 Minecraft Server 是多人游戏服务器，建议在编辑配置文件之前与所有管理员协商，以确保大家的意见达成一致。  
  
## 贡献指南  
  
如果你有任何问题或建议，或者你想分享你自己的配置文件，欢迎在 issues 或 pull requests 中提供反馈。同时，如果你有任何改进或优化的建议，也欢迎提出。  
