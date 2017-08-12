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
import java.util.List;
import java.util.Map;

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

    @RequestMapping("/userTree")
    public String user(ModelMap modelMap, HttpServletRequest request, HttpSession session){
        return "userTree";
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
