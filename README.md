Android_BaseLib
===============

这是一个用于Android快速开发的框架:

(一)、集成了项目常用到工具类
AppManager， CommonUtils， 常用的工具类封装 
DeviceInfoUtil 获得设备相关的信息，IMEI，设备的蓝牙，和SD卡是否可用。 
LogManager  项目Log的统一管理 
Screenshot 可以用于截图 
ToastManager  项目中Toast的统一管理 
ServiceManager  管理手机的各种系统服务，比如LocationManager，TelephonyManager，InputMethodManager，Vibrator，ConnectivityManager 
等等， .........................................  

（二）、封装了BaseActivity，BaseFragment，BasePage，对BaseAdapter进行封装，把所有的公共点进行封装，子类只需要继承即可  
（三）万能的ViewHolder   可以省去每次都需要在Adapter类中写一个静态的ViewHolder问题，实现代码的重用性。 

（四）集成了很多的自定义View  
比如项目常用到的顶部栏，直接封装成了TopBarView，只需要在布局文件中引入即可； 
RollViewPager 可以自动滚动的ViewPager，带有标题，和用于指示的小圆点。 
ProgressWheel 自定义的进度条，
CustomToast  自定义的Toast 
CircleImageView  圆形的ImageView
SlideButton  左右滑动的滑动开关 
ClearEditText  带有删除图标的EditText登录中常用到 
ScrollListView  解决ListView嵌套到ScrollView中只显示一行的问题   

（五）CrashHandler 用于异常崩溃处理  当程序发生未捕获异常时，由该类来接管程序并记录发送错误报告。把错误信息保存在sd卡中，然后上传异常信息到服务器，便于程序员对异常的处理。

（六）集成了常用到的开源框架 
ViewPageIndicator  常用到滑动导航的开源框架，可以很方便的做到网易新闻客户端Tab标签滑动导航的功能；

Pulltorefreshview   下拉刷新可以说是每个项目中都必须用到的吧，这个自然不用多说； 

Gson    可以用于对服务器端返回的json解析，在工具类中可以找到GsonUtil 帮助类，解析json非常的方便；

android-async-http   非常成熟的异步请求网络的类，使用起来非常简单，从MyHttpClient中可以看到使用方法；当然你也可以不需要网络框架，自己封装httpclient做成MyHttpClient，不过开源框架毕竟是很成熟了的，可以解决在实际运行过程中的一些未知问题；

Zxing   二维码/条形码识别的框架  项目中如果需要进行二维码的扫描，可以使用此框架，已经集成在项目中，只需要以startActivityForResult的方式调用本项目中的CaptureActivity类即可打开扫描界面,然后在返回结果中获得扫描到的结果；这个CaptureActivity已经实现了扫描成功时的震动和确认声音，提高用户的体验。当然还有从下而上的滑动滚动横杠，如果有特别的需求可以自己修改，比如说加上闪光灯也是个不错的想法。 

Universal-image-loader  对于图片的加载怎么少的了这个开源框架呢，全面解决你项目中ListView加载图片的各种问题。 

xUtils   很不错的快速开发框架，其中的ViewUtil模块可以省去我们在项目中的各种烦人的findViewById代码，以及setOnclickLister等，属于android中的ioc框架，完全注解方式就可以进行UI，资源和事件绑定；
