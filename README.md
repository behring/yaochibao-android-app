# 要吃饱Android App工程介绍
## 项目工程结构
- mobile-bff
    > 要吃饱Android App需要访问server获取数据，mobile-bff作为fake server为App提供必要测试数据。
- YaoChiBaoApp
    > 要吃饱Android App工程。

## 启动mobile-bff服务
设假设您已经安装了[Node.js](https://nodejs.org/en/)。进入`mobile-bff`工程目录，执行如下命令启动bff服务:
```
foo@bar:~$ cd mobile-bff
foo@bar:~$ npm install && npm start
```
当服务启动成功将看到如下信息：
```
...
Mobile BFF listening at http://localhost:3000
```


## 在模拟器运行Android应用程序
假设您已经安装了Android相关开发环境。进入`YaoChiBaoApp`工程目录，执行如下命令安装APK：
```
foo@bar:~$ cd YaoChiBaoApp
foo@bar:~$ ./grdlew :app:installRelease
```
如果您没有配置Andoid相关环境，可以直接安装`YaoChiBaoApp/apk/app-release.apk`文件体验应用。安装方式为：
1. 复制apk文件到手机中，在手机资源管理器找到相应的文件点击安装。
2. 通过[adb](https://developer.android.com/studio/command-line/adb)命令安装，命令如下：
   ```
   foo@bar:~$ adb install YaoChiBaoApp/apk/app-release.apk
   ```
> 注意：通过模拟器安装APK后，可以访问到本地部署的BFF服务，如果想要在真机上测试APK，需要将BFF部署到真机可以访问的网络环境。为了您方便的测试，可以安装`YaoChiBaoApp/apk/app-debug.apk`在真机上测试。`app-debug.apk`并没有真正访问BFF服务，而是Stub了网络层直接返回Happy Path数据，因此测试不能保证最终产品环境的功能有效性。建议使用`app-release.apk`进行测试。