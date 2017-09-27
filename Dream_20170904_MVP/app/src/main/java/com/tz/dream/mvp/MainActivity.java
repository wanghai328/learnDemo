package com.tz.dream.mvp;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //1、MVP设计-第一步-普通代码
//    public void login(View v){
//        HttpTask httpTask = new HttpTask(new HttpUtils.OnHttpResultListener() {
//            @Override
//            public void onResult(String result) {
//                Toast.makeText(MainActivity.this, result, Toast.LENGTH_LONG).show();
//            }
//        });
//        httpTask.execute("http://192.168.57.1:8080/Dream_6_19_LoginServer/LoginServlet", "Dream","123456");
//    }

    //2、MVP设计->第二步->优化代码-优化第1步
    //分析问题：团队开发（存在问题：多人开发、模块化、迭代）
    //解决方案：MVP设计（UI层和M层进行分离）
    //解决P层和V层直接关联->接口回调
//    public void login(View v){
//        LoginPresenter loginPresenter = new LoginPresenter(this);
//        loginPresenter.login("Dream", "123456");
//    }
//
//    @Override
//    public void onLoginResult(String result) {
//        Toast.makeText(MainActivity.this, result, Toast.LENGTH_LONG).show();
//    }

    //3、MVP设计->优化代码-优化第2步
    //分析问题：UI层和M层解除绑定
    //解决方案：绑定UI和解除绑定UI->方法绑定(attachView、detachView)
//    public void login(View v){
//        loginPresenter = new LoginPresenter_2();
//        //绑定View
//        loginPresenter.attachView(this);
//        loginPresenter.login("Dream", "123456");
//    }
//
//    @Override
//    public void onLoginResult(String result) {
//        Toast.makeText(MainActivity.this, result, Toast.LENGTH_LONG).show();
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        //解除绑定View
//        loginPresenter.detachView();
//    }

    //4、MVP设计->优化代码-优化第3步
    //分析问题：其实这个绑定方式，那么你是一个presneter还好，如果多个presenter？很多冗余代码？
    //解决方案：抽象为父类->封装
//    public void login(View v) {
//        loginPresenter = new LoginPresenter_3();
//        //绑定View
//        loginPresenter.attachView(this);
//        loginPresenter.login("Dream", "123456");
//    }
//
//    @Override
//    public void onLoginResult(String result) {
//        Toast.makeText(MainActivity.this, result, Toast.LENGTH_LONG).show();
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        //解除绑定View
//        loginPresenter.detachView();
//    }


    //5、MVP设计->优化代码-优化第4步
    //分析问题：父类是不是写死了(LoginView)，假设有很多模块，多个View层，那么父类九行不通
    //解决方案：泛型设计
    //测试
//    public void login(View v) {
//        loginPresenter = new LoginPresenter_4();
//        //绑定View
//        loginPresenter.attachView(this);
//        loginPresenter.login("Dream", "123456");
//    }
//
//    @Override
//    public void onLoginResult(String result) {
//        Toast.makeText(MainActivity.this, result, Toast.LENGTH_LONG).show();
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        //解除绑定View
//        loginPresenter.detachView();
//    }


    //6、MVP设计->优化代码-优化第5步
    //分析问题：如果注意，你会发现activity里面需要不断重复的绑定和解除绑定（50个类，100类），代码冗余太多了
    //解决方案：泛型设计+抽象类(动态指定类型)
//    public void login(View v) {
//        getPresneter().login("Dream", "123456");
//    }
//
//    @Override
//    public LoginPresenter_5 createPresneter() {
//        return new LoginPresenter_5();
//    }
//
//    @Override
//    public LoginView_5 createView() {
//        return this;
//    }
//
//    @Override
//    public void onLoginResult(String result) {
//        Toast.makeText(MainActivity.this, result, Toast.LENGTH_LONG).show();
//    }


    //第二步：MVP进阶设计？
    //1、MVP设计->优化代码-优化第1步
    //分析问题：扩展功能功能时候，有问题了（例如：实现Fragment、LinearLayout等等...）
    //发现问题：扩展功能，代码冗余（扩展MVP实现，发现了很多冗余代码，例如：Activity、Fragment、Button、LinearLayout等等..）
    //双重代理模式+动态代理
    //第一重代理：分析角色?->核心代理->MVP绑定和解除绑定(P层和V层关联等等...相关逻辑处理)
    //分析代理模式？
    //Dream老师买iphone 8（据说：8000），我叫深圳一个朋友（代理商）帮我代购，然后寄给我。
    //角色一：目标接口->买手动作（抽象）
    //角色二：目标对象->我(Dream老师)
    //角色三：代理对象->朋友(代理商)
    //分析绑定和解绑操作代理角色？
    //角色一：目标接口->抽象解绑和绑定(MvpCallback)
    //角色二：目标对象->具体实现MvpActivity、MvpFragment、MvpLinearLayout(BaseActivity)->具体实现类
    //角色三：代理对象->ProxyMvpCallback(具体一些实现)

    //第二重代理：分析角色?->核心代理->代理各个模块的生命周期（目的：为了给绑定和解除绑定提供一个公共环境（抽象））
    //角色一：目标接口->Activity生命周期抽象(MvpActivityDelegate: onCreate、onStart、onStop等等...)
    //角色二：目标对象->具体的实现(MvpActivityDelegateImpl: 实现类)
    //角色三：代理对象->MvpActivity(代理对象)

    //学习框架设计如何学习？
    //步骤一：学习设计模式(完全懵逼的)
    //步骤二：学会分析设计模式角色(角色不一定规范的，很有可能是变种)
    //步骤三：分析框架结构(就是分析：角色)
    //例如：代理模式(代理对象：可以不实现目标接口)
    //之前你没有打基础
    //上一期课程里面：很多设计模式+框架分析和设计(应用层开发比较麻烦，水平提升)
    //做项目，拷贝粘贴(50-60%)，做了1年，做了2年，做了3年，自己各方面技能并没有提示
    //老师给童鞋们建议（3个方面）（技术层面上）：架构设计+底层开发+新技术新领域
    //底层开发NDK，新技术，新领域基于NDK开发开发（OpenCV图像处理、OpenGL ES渲染、智能家居、智能安防、VR/AR、人工智能这一块等等...）
    //第一步：C语言->C++语言->JNI->OpenCV(机器学习模块)、OpenGL ES、VR/AR(基于Unity3D)
    //我原来学习：自学(公司里面CTO，带我)
    //底层开发:基于C/C++开发->Android上层开发
    //目标最新一期班级：针对框架设计+底层开发NDK+科技前沿
    //2017年8月31日，开始上课，今天晚上VIP课程第一节课
    //第一阶段：OpenCV开发（今天晚上是第一节课），每周2、4、6上课(每天晚上8：30-11：30)->2个月课程
    //第二阶段：OpenGL ES 基于NDK开发，C/C++，游戏引擎(游戏，手游)->2个月
    //第三阶段：VR开发（Unity3D、VR）->2个月
    //尧金老师、Jerry老师、青岩老师、Tony老师、Dream老师，课程研发老师
    //福利一：支持分期免息，办理学院分期，200元就可以学习，办理分期，0利息+0手续费学习，6期（每个月：950元学费），12期（480元学费）
    //福利二：每周2、4、6上课，每周1、3、5、7复习和补课（架构设计：设计模式+框架设计、源码分析、系统新特性等...）
    //VIP课堂、补课课堂



}
