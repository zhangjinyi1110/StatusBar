# StatusBar
状态栏工具类  
添加到项目中  
Add it in your root build.gradle at the end of repositories:  
```gradle
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```  
Add the dependency  
```gradle
dependencies {
	        implementation 'com.github.zhangjinyi1110:StatusBar:1.0.0'
	}
```  
方法：  
```java
//设置状态栏背景颜色
    setStatusBackgroundColor();

    //设置状态栏背景图片
    setStatusBackgroundDrawable();

    //隐藏状态栏
    hideStatusBar();

    //显示状态栏
    showStatusBar();

    //隐藏状态栏但显示文字图标
    hideHalfStatusBar();

    //亮色主题时字体为黑色
    lightColor();

    //暗色主题时字体为白色
    darkColor();
```  
使用：  
```java
BarHelper.with(this).setStatusBackgroundColor(Color.parseColor("#333333")).lightColor();//设置状态栏颜色为#333333，字体颜色是黑色
```
