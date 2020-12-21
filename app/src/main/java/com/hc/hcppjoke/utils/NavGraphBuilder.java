package com.hc.hcppjoke.utils;

import android.content.ComponentName;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.ActivityNavigator;
import androidx.navigation.NavController;
import androidx.navigation.NavGraph;
import androidx.navigation.NavGraphNavigator;
import androidx.navigation.Navigator;
import androidx.navigation.NavigatorProvider;
import androidx.navigation.fragment.FragmentNavigator;

import com.hc.hcppjoke.model.Destination;
import com.hc.hcppjoke.navigator.FixFragmentNavigator;
import com.hc.libcommon.global.AppGlobals;

import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by hcw  on 2020/5/30
 * 类描述： 1、默认 repleace 会导致生命周期重新走一遍。
 *
 * 2、如果替换成定制的导航器，fragment切换时还能多次打印onCreateView日志。
 * 请检查FixFragmentNavigate的navigate方法中ft.add(id%2Cfragment%2Ctag)+方法，有没有把tag传递进去。
 *
 * '
 *
 * all rights reserved
 */

public class NavGraphBuilder {
    public static void build(FragmentActivity activity, FragmentManager childFragmentManager, NavController controller, int containerId) {
        NavigatorProvider provider = controller.getNavigatorProvider();

        //NavGraphNavigator也是页面路由导航器的一种，只不过他比较特殊。
        //它只为默认的展示页提供导航服务,但真正的跳转还是交给对应的navigator来完成的
        NavGraph navGraph = new NavGraph(new NavGraphNavigator(provider));

        //FragmentNavigator fragmentNavigator = provider.getNavigator(FragmentNavigator.class);
        //fragment的导航此处使用我们定制的FixFragmentNavigator，底部Tab切换时 使用hide()/show(),而不是replace()
        FixFragmentNavigator fragmentNavigator = new FixFragmentNavigator(activity, childFragmentManager, containerId);

        //这样就添加了自定义导航器
        provider.addNavigator(fragmentNavigator);
        ActivityNavigator activityNavigator = provider.getNavigator(ActivityNavigator.class);
        //解析自定义的 json ，实现添加导航的功能
        HashMap<String, Destination> destConfig = AppConfig.getDestConfig();
        Iterator<Destination> iterator = destConfig.values().iterator();
        while (iterator.hasNext()) {
            Destination node = iterator.next();
            if (node.isFragment) {
                FixFragmentNavigator.Destination destination = fragmentNavigator.createDestination();
                destination.setId(node.id);
                destination.setClassName(node.className);
                destination.addDeepLink(node.pageUrl);
                navGraph.addDestination(destination);
            } else {
                ActivityNavigator.Destination destination = activityNavigator.createDestination();
                destination.setId(node.id);
                destination.setComponentName(new ComponentName(AppGlobals.getApplication().getPackageName(), node.className));
                destination.addDeepLink(node.pageUrl);
                navGraph.addDestination(destination);
            }

            //给APP页面导航结果图 设置一个默认的展示页的id
            if (node.asStarter) {
                navGraph.setStartDestination(node.id);
            }
        }

        controller.setGraph(navGraph);
    }


    public static void build(NavController controller){

        NavigatorProvider provider = controller.getNavigatorProvider();

        FragmentNavigator fragmentNavigator = provider.getNavigator(FragmentNavigator.class);
        ActivityNavigator activityNavigator = provider.getNavigator(ActivityNavigator.class);
        NavGraph navGraph = new NavGraph(new NavGraphNavigator(provider));


        HashMap<String, Destination> destConfig = AppConfig.getDestConfig();
        Iterator<Destination> iterator = destConfig.values().iterator();
        while (iterator.hasNext()) {
            Destination node = iterator.next();
            if (node.isFragment) {
                FixFragmentNavigator.Destination destination = fragmentNavigator.createDestination();
                destination.setId(node.id);
                destination.setClassName(node.className);
                destination.addDeepLink(node.pageUrl);
                navGraph.addDestination(destination);
            } else {
                ActivityNavigator.Destination destination = activityNavigator.createDestination();
                destination.setId(node.id);
                destination.setComponentName(new ComponentName(AppGlobals.getApplication().getPackageName(), node.className));
                destination.addDeepLink(node.pageUrl);
                navGraph.addDestination(destination);
            }

            //给APP页面导航结果图 设置一个默认的展示页的id
            if (node.asStarter) {
                navGraph.setStartDestination(node.id);
            }
        }

        controller.setGraph(navGraph);
    }
}
