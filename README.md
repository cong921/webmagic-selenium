# webmagic-selenium

## 部署环境
### jdk1.8
## selenium爬虫
### 简介
采用最新版chromedriver ( https://chromedriver.storage.googleapis.com/2.25/chromedriver_win32.zip ),和最新版的google浏览器,
模拟手机浏览器登录从而得到真实的视频地址.
对于反爬措施较严的网站可采用这种措施
### 多线程实现
#### SeleniumTestUrl类中的test方法为列表页url的采集方法
#### SeleniumTest类中可自定义失败的采集次数,可根据设备性能自定义启动的线程数

