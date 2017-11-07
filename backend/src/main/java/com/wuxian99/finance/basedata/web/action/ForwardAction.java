package com.wuxian99.finance.basedata.web.action;

import com.wuxian99.finance.basedata.domain.model.Menu;
import com.wuxian99.finance.basedata.domain.model.SigninUser;
import com.wuxian99.finance.basedata.service.system.MenuService;
import com.wuxian99.finance.basedata.service.system.UserService;
import com.wuxian99.finance.common.Result;
import com.wuxian99.finance.common.SigninCommand;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.*;

/**
 * Created by sxjiang on 2017/2/27.
 */
@RequestMapping
@Controller
public class ForwardAction {
    @Autowired
    private MenuService menuService;
    @Autowired
    private UserService userService;
    @Value("${wine.picPath}")
    private String webPath;

    @RequestMapping("/index")
    public String index(ModelMap modelMap, HttpServletRequest request, HttpSession session){
        return "index";
    }

    @RequestMapping("/overview")
    public String overview(ModelMap modelMap, HttpServletRequest request, HttpSession session){

//        Map<String, Object> newUser = new HashMap<>();
//        newUser.put("total", random(1000000));
//        newUser.put("year",  random(100000));
//        List<Long> userMonths = new ArrayList<>();
//        for(int i=0; i<12; i++){
//            userMonths.add(random(10000));
//        }
//        newUser.put("month-12", userMonths);
//        newUser.put("month", userMonths.get(11));
//        newUser.put("day", random(1000));
//
//
//        Map<String, Object> sellAmount = new HashMap<>();
//        sellAmount.put("total", random(10000000));
//        sellAmount.put("year",  random(1000000));
//        List<Long> sellMonths = new ArrayList<>();
//        for(int i=0; i<12; i++){
//            sellMonths.add(random(100000));
//        }
//        sellAmount.put("month-12", sellMonths);
//        sellAmount.put("month", sellMonths.get(11));
//        sellAmount.put("day",   random(10000));
//
//        String[] mdseNames = new String[]{
//                "千红裕·钻石级 赤霞珠干红葡萄酒",
//                "贝娜尼 干白 葡萄酒",
//                "千红裕·东一区 赤霞珠干红葡萄酒",
//                "陇尚红 经典 赤霞珠干红葡萄酒",
//                "贝乐丝 桃红 葡萄酒",
//                "千红裕·东二区 赤霞珠干红葡萄酒",
//                "千红裕·东三区 赤霞珠干红葡萄酒",
//                "泽风 洋葱 干红葡萄酒",
//                "千红裕·黄金级 赤霞珠干红葡萄酒",
//                "千红裕·铂金级 赤霞珠干红葡萄酒"};
//        Map<String, Object> singleSell = new HashMap<>();
//        singleSell.put("total", new Object[]{mdseNames[0], random(10000000)});
//        singleSell.put("year",  new Object[]{mdseNames[1], random(1000000)});
//        singleSell.put("month", new Object[]{mdseNames[2], random(100000)});
//        List<Object> singleTop10 = new ArrayList<>();
//        for(int i=0; i<10; i++){
//            singleTop10.add(new Object[]{mdseNames[i], random(10000)});
//        }
//        singleSell.put("day-top10", singleTop10);
//        singleSell.put("day",   singleTop10.get(0));
//
//
//        String[] prices = new String[]{
//                "200-300",
//                "0-100",
//                "100-200",
//                "600-700",
//                "900-1000",
//                "400-500",
//                "700-800",
//                "500-600",
//                "300-400",
//                "800-900"
//                };
//        Map<String, Object> userBuy = new HashMap<>();
//        userBuy.put("total", new Object[]{prices[0], random(10000000)});
//        userBuy.put("year",  new Object[]{prices[1], random(1000000)});
//        userBuy.put("month", new Object[]{prices[2], random(100000)});
//        List<Object> buyTop10 = new ArrayList<>();
//        for(int i=0; i<10; i++){
//            buyTop10.add(new Object[]{prices[i], random(10000)});
//        }
//        userBuy.put("day-top10", buyTop10);
//        userBuy.put("day",   random(10000));
//
//
//        Map<String, Object> order = new HashMap<>();
//        long total = random(1000000);
//        order.put("total", total);
//        order.put("year",  random(100000));
//        order.put("month", random(10000));
//        order.put("day",   random(1000));
//        order.put("total-pay", total - random(10000));
//        order.put("total-delivery", total - random(30000));
//        order.put("total-receive", total - random(50000));
//
//
//        Map<String, Object> map = new HashMap<>();
//        map.put("海门", new Object[]{121.15, 31.89, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("鄂尔多斯", new Object[]{109.781327, 39.608266, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("招远", new Object[]{120.38, 37.35, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("舟山", new Object[]{122.207216, 29.985295, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("齐齐哈尔", new Object[]{123.97, 47.33, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("盐城", new Object[]{120.13, 33.38, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("赤峰", new Object[]{118.87, 42.28, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("青岛", new Object[]{120.33, 36.07, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("乳山", new Object[]{121.52, 36.89, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("金昌", new Object[]{102.188043, 38.520089, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("泉州", new Object[]{118.58, 24.93, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("莱西", new Object[]{120.53, 36.86, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("日照", new Object[]{119.46, 35.42, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("胶南", new Object[]{119.97, 35.88, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("南通", new Object[]{121.05, 32.08, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("拉萨", new Object[]{91.11, 29.97, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("云浮", new Object[]{112.02, 22.93, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("梅州", new Object[]{116.1, 24.55, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("文登", new Object[]{122.05, 37.2, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("上海", new Object[]{121.48, 31.22, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("攀枝花", new Object[]{101.718637, 26.582347, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("威海", new Object[]{122.1, 37.5, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("承德", new Object[]{117.93, 40.97, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("厦门", new Object[]{118.1, 24.46, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("汕尾", new Object[]{115.375279, 22.786211, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("潮州", new Object[]{116.63, 23.68, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("丹东", new Object[]{124.37, 40.13, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("太仓", new Object[]{121.1, 31.45, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("曲靖", new Object[]{103.79, 25.51, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("烟台", new Object[]{121.39, 37.52, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("福州", new Object[]{119.3, 26.08, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("瓦房店", new Object[]{121.979603, 39.627114, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("即墨", new Object[]{120.45, 36.38, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("抚顺", new Object[]{123.97, 41.97, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("玉溪", new Object[]{102.52, 24.35, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("张家口", new Object[]{114.87, 40.82, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("阳泉", new Object[]{113.57, 37.85, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("莱州", new Object[]{119.942327, 37.177017, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("湖州", new Object[]{120.1, 30.86, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("汕头", new Object[]{116.69, 23.39, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("昆山", new Object[]{120.95, 31.39, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("宁波", new Object[]{121.56, 29.86, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("湛江", new Object[]{110.359377, 21.270708, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("揭阳", new Object[]{116.35, 23.55, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("荣成", new Object[]{122.41, 37.16, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("连云港", new Object[]{119.16, 34.59, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("葫芦岛", new Object[]{120.836932, 40.711052, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("常熟", new Object[]{120.74, 31.64, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("东莞", new Object[]{113.75, 23.04, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("河源", new Object[]{114.68, 23.73, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("淮安", new Object[]{119.15, 33.5, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("泰州", new Object[]{119.9, 32.49, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("南宁", new Object[]{108.33, 22.84, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("营口", new Object[]{122.18, 40.65, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("惠州", new Object[]{114.4, 23.09, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("江阴", new Object[]{120.26, 31.91, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("蓬莱", new Object[]{120.75, 37.8, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("韶关", new Object[]{113.62, 24.84, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("嘉峪关", new Object[]{98.289152, 39.77313, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("广州", new Object[]{113.23, 23.16, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("延安", new Object[]{109.47, 36.6, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("太原", new Object[]{112.53, 37.87, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("清远", new Object[]{113.01, 23.7, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("中山", new Object[]{113.38, 22.52, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("昆明", new Object[]{102.73, 25.04, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("寿光", new Object[]{118.73, 36.86, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("盘锦", new Object[]{122.070714, 41.119997, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("长治", new Object[]{113.08, 36.18, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("深圳", new Object[]{114.07, 22.62, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("珠海", new Object[]{113.52, 22.3, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("宿迁", new Object[]{118.3, 33.96, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("咸阳", new Object[]{108.72, 34.36, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("铜川", new Object[]{109.11, 35.09, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("平度", new Object[]{119.97, 36.77, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("佛山", new Object[]{113.11, 23.05, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("海口", new Object[]{110.35, 20.02, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("江门", new Object[]{113.06, 22.61, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("章丘", new Object[]{117.53, 36.72, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("肇庆", new Object[]{112.44, 23.05, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("大连", new Object[]{121.62, 38.92, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("临汾", new Object[]{111.5, 36.08, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("吴江", new Object[]{120.63, 31.16, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("石嘴山", new Object[]{106.39, 39.04, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("沈阳", new Object[]{123.38, 41.8, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("苏州", new Object[]{120.62, 31.32, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("茂名", new Object[]{110.88, 21.68, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("嘉兴", new Object[]{120.76, 30.77, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("长春", new Object[]{125.35, 43.88, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("胶州", new Object[]{120.03336, 36.264622, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("银川", new Object[]{106.27, 38.47, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("张家港", new Object[]{120.555821, 31.875428, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("三门峡", new Object[]{111.19, 34.76, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("锦州", new Object[]{121.15, 41.13, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("南昌", new Object[]{115.89, 28.68, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("柳州", new Object[]{109.4, 24.33, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("三亚", new Object[]{109.511909, 18.252847, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("自贡", new Object[]{104.778442, 29.33903, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("吉林", new Object[]{126.57, 43.87, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("阳江", new Object[]{111.95, 21.85, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("泸州", new Object[]{105.39, 28.91, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("西宁", new Object[]{101.74, 36.56, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("宜宾", new Object[]{104.56, 29.77, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("呼和浩特", new Object[]{111.65, 40.82, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("成都", new Object[]{104.06, 30.67, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("大同", new Object[]{113.3, 40.12, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("镇江", new Object[]{119.44, 32.2, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("桂林", new Object[]{110.28, 25.29, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("张家界", new Object[]{110.479191, 29.117096, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("宜兴", new Object[]{119.82, 31.36, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("北海", new Object[]{109.12, 21.49, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("西安", new Object[]{108.95, 34.27, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("金坛", new Object[]{119.56, 31.74, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("东营", new Object[]{118.49, 37.46, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("牡丹江", new Object[]{129.58, 44.6, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("遵义", new Object[]{106.9, 27.7, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("绍兴", new Object[]{120.58, 30.01, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("扬州", new Object[]{119.42, 32.39, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("常州", new Object[]{119.95, 31.79, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("潍坊", new Object[]{119.1, 36.62, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("重庆", new Object[]{106.54, 29.59, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("台州", new Object[]{121.420757, 28.656386, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("南京", new Object[]{118.78, 32.04, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("滨州", new Object[]{118.03, 37.36, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("贵阳", new Object[]{106.71, 26.57, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("无锡", new Object[]{120.29, 31.59, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("本溪", new Object[]{123.73, 41.3, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("克拉玛依", new Object[]{84.77, 45.59, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("渭南", new Object[]{109.5, 34.52, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("马鞍山", new Object[]{118.48, 31.56, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("宝鸡", new Object[]{107.15, 34.38, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("焦作", new Object[]{113.21, 35.24, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("句容", new Object[]{119.16, 31.95, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("北京", new Object[]{116.46, 39.92, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("徐州", new Object[]{117.2, 34.26, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("衡水", new Object[]{115.72, 37.72, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("包头", new Object[]{110, 40.58, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("绵阳", new Object[]{104.73, 31.48, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("乌鲁木齐", new Object[]{87.68, 43.77, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("枣庄", new Object[]{117.57, 34.86, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("杭州", new Object[]{120.19, 30.26, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("淄博", new Object[]{118.05, 36.78, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("鞍山", new Object[]{122.85, 41.12, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("溧阳", new Object[]{119.48, 31.43, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("库尔勒", new Object[]{86.06, 41.68, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("安阳", new Object[]{114.35, 36.1, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("开封", new Object[]{114.35, 34.79, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("济南", new Object[]{117, 36.65, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("德阳", new Object[]{104.37, 31.13, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("温州", new Object[]{120.65, 28.01, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("九江", new Object[]{115.97, 29.71, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("邯郸", new Object[]{114.47, 36.6, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("临安", new Object[]{119.72, 30.23, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("兰州", new Object[]{103.73, 36.03, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("沧州", new Object[]{116.83, 38.33, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("临沂", new Object[]{118.35, 35.05, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("南充", new Object[]{106.110698, 30.837793, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("天津", new Object[]{117.2, 39.13, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("富阳", new Object[]{119.95, 30.07, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("泰安", new Object[]{117.13, 36.18, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("诸暨", new Object[]{120.23, 29.71, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("郑州", new Object[]{113.65, 34.76, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("哈尔滨", new Object[]{126.63, 45.75, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("聊城", new Object[]{115.97, 36.45, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("芜湖", new Object[]{118.38, 31.33, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("唐山", new Object[]{118.02, 39.63, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("平顶山", new Object[]{113.29, 33.75, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("邢台", new Object[]{114.48, 37.05, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("德州", new Object[]{116.29, 37.45, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("济宁", new Object[]{116.59, 35.38, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("荆州", new Object[]{112.239741, 30.335165, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("宜昌", new Object[]{111.3, 30.7, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("义乌", new Object[]{120.06, 29.32, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("丽水", new Object[]{119.92, 28.45, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("洛阳", new Object[]{112.44, 34.7, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("秦皇岛", new Object[]{119.57, 39.95, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("株洲", new Object[]{113.16, 27.83, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("石家庄", new Object[]{114.48, 38.03, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("莱芜", new Object[]{117.67, 36.19, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("常德", new Object[]{111.69, 29.05, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("保定", new Object[]{115.48, 38.85, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("湘潭", new Object[]{112.91, 27.87, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("金华", new Object[]{119.64, 29.12, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("岳阳", new Object[]{113.09, 29.37, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("长沙", new Object[]{113, 28.21, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("衢州", new Object[]{118.88, 28.97, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("廊坊", new Object[]{116.7, 39.53, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("菏泽", new Object[]{115.480656, 35.23375, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("合肥", new Object[]{117.27, 31.86, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("武汉", new Object[]{114.31, 30.52, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//        map.put("大庆", new Object[]{125.03, 46.58, random(1000000), random(10000), mdseNames[new Random().nextInt(10)]});
//
//        Map<String, Object> overviewDate = new HashMap<>();
//        overviewDate.put("newUser", newUser);
//        overviewDate.put("sellAmount", sellAmount);
//        overviewDate.put("singleSell", singleSell);
//        overviewDate.put("userBuy", userBuy);
//        overviewDate.put("order", order);
//        overviewDate.put("map", map);
//        modelMap.put("overviewDate", overviewDate);
        return "redirect:/charts/index.html";
    }

    private long random(int val){
        return val*new Random().nextInt(10) +  new Random().nextInt(val);
    }

    @RequestMapping("/userTree")
    public String user(ModelMap modelMap, HttpServletRequest request, HttpSession session){
        return "userTree";
    }

    @RequestMapping("/orderMap")
    public String orderMap(ModelMap modelMap, HttpServletRequest request, HttpSession session){
        return "orderMap";
    }

    @RequestMapping("/menuRedirect/{menuKey}")
    public String menuRedirect(ModelMap modelMap, HttpServletRequest request, HttpSession session,@RequestParam(value="redirect") String redirect,@PathVariable(value="menuKey") String menuKey){
        Menu targetMenu = menuService.queryMenuByKey(menuKey,request.getContextPath());
        session.setAttribute("targetMenu",targetMenu);
        return "redirect:"+redirect;
    }

    @RequestMapping("/{module}/list")
    public String moduleList(ModelMap modelMap, @RequestParam Map<String, Object> parameters, @PathVariable(value="module") String module){
        modelMap.putAll(parameters);
        modelMap.put("moduleName", module);
        return module + "_list";
    }

    @RequestMapping({"/toSignin"})
    public String toSignin(ModelMap modelMap, @RequestParam Map<String, Object> parameters){
        modelMap.putAll(parameters);
        return "signin";
    }

    @RequestMapping({"/signin"})
    public String signin(ModelMap modelMap, @Valid SigninCommand signinCommand, BindingResult bindingResult, HttpServletRequest request, HttpSession session){
        if (bindingResult.hasErrors()) {
            return "signin";
        }

        Result<SigninUser> result = userService.signin(signinCommand);

        if(result == null){
            modelMap.put("errorMsg","用户名或密码错误");
            return "signin";
        }else if(!result.getSuccess()){
            modelMap.put("errorMsg",result.getErrorMsg());
            return "signin";
        }else{
            SigninUser user = result.getData();
            List<Menu> menuList = menuService.generate(user,request.getContextPath());
            session.setAttribute("menuList",menuList);
            session.setAttribute("signinUser",user);
            session.setAttribute("webPath",webPath);
            if(StringUtils.isNotEmpty(signinCommand.getDestination())){
                return "redirect:"+signinCommand.getDestination();
            }else{
                return "redirect:/menuRedirect/workbenchIndex?redirect=/index";
            }
        }
    }

    @RequestMapping({"/signout"})
    public String signout(HttpServletRequest request, HttpSession session){
        session.invalidate();
        return "signin";
    }

}
