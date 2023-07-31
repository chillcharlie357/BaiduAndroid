# FinalProject

## 介绍

大作业

## 软件架构

软件架构说明

![files.png](README_assets%2Ffiles.png)

项目由一个`Mainactivity`和多个`Fragment`组成。

`data`下是数据，主要是Jetpack Room处理本地数据库。

`ui`下是UI层和ViewModel(逻辑)。

UI层对于的布局文件了：

![layouts.png](README_assets%2Flayouts.png)


`utils`是一些公共方法。


## 安装教程

1. 找到APK包：`app/release/app-release.apk`
2. 在手机上安装

## 项目说明

### 首页

没有添加新闻时的首页：

![img.png](README_assets/home_blank.png)

#### 添加新闻

新闻的数据层使用Room存储。

点击右上角的`+`按钮，进入新闻编辑界面：

![edit_news.png](README_assets%2Fedit_news.png)

输入相关信息：

![news.png](README_assets%2Fnews.png)

无图片的推文：

![no_image.png](README_assets%2Fno_image.png)

提交后的首页：

![home_2.png](README_assets%2Fhome_2.png)


### 天气

点击首页左上角的文字，进入天气页。

加载动画：

![loading.png](README_assets%2Floading.png)

天气页：

![weather.png](README_assets%2Fweather.png)

下方的recycleView可以滑动

### 搜索

输入关键词，点击按钮搜索。

![search_1.png](README_assets%2Fsearch_1.png)

![search_2.png](README_assets%2Fsearch_2.png)

### 视频

测试时发现在某些版本模拟器上音频驱动出错无法播放，在实机上播放正常。

可以一直向下滑动：

![video.png](README_assets%2Fvideo.png)

单击视频跳转到单独界面：

![video_2.png](README_assets%2Fvideo_2.png)


### 我的

##### 登录页

`我的`页实现了用户登录、注册、昵称修改，数据层使用Room。

![about_1.png](README_assets%2Fabout_1.png)

![sign_up.png](README_assets%2Fsign_up.png)

如果出现错误，下方有`toast`提示;登陆成功也会有提示。

* 用户不存在/密码错误

![sign_up_2.png](README_assets%2Fsign_up_2.png)

* 注册成功

![sign_3.png](README_assets%2Fsign_3.png)

#### 登录后

![about_2.png](README_assets%2Fabout_2.png)

修改用户昵称：

![update_name.png](README_assets%2Fupdate_name.png)

点击退出后回到登录页。