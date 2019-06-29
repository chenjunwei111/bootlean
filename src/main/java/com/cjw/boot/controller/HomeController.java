package com.cjw.boot.controller;


import com.cjw.boot.common.base.BaseController;
import com.cjw.boot.pojo.admin.MenuPojo;
import com.cjw.boot.pojo.admin.UserPojo;
import com.cjw.boot.service.admin.Menuservice;
import com.cjw.boot.service.diy.DiySqlService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class HomeController extends BaseController {
	private static Logger logger = LoggerFactory.getLogger(HomeController.class);


	@ApiOperation(value = "首页", notes = "首页")
	@GetMapping("/index")
	public String index() {
		return "index";
	}

	@ApiOperation(value = "局部刷新区域", notes = "局部刷新区域")
	@GetMapping("/main")
	public String main() {
		return "main";
	}

	@Autowired
	DiySqlService diySqlService;

	@Autowired
	Menuservice menuservice;

	/**
	 * 请求到登陆界面
	 *
	 * @param request
	 * @return
	 */
	@ApiOperation(value = "请求到登陆界面", notes = "请求到登陆界面")
	@GetMapping("/login")
	public String login(HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
		System.out.println("进入登录页面");
		try {
			if ((null != SecurityUtils.getSubject() && SecurityUtils.getSubject().isAuthenticated()) || SecurityUtils.getSubject().isRemembered()) {
				return "index";
			} else {
				System.out.println("--进行登录验证..验证开始");
				return "login";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "login";
	}

	/**
	 * 用户登陆验证
	 *
	 * @param user
	 * @param code
	 * @param redirectAttributes
	 * @param rememberMe
	 * @param model
	 * @param request
	 * @return
	 */
	@PostMapping("login")
	@ResponseBody
	public HashMap<String, String> login(@RequestBody UserPojo user, String code, RedirectAttributes redirectAttributes, boolean rememberMe, Model model, HttpServletRequest request) {
//		 ModelAndView view =new ModelAndView();
//		 String scode = (String)request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
		//判断验证码
//		 if(StringUtils.isNotEmpty(scode)&&StringUtils.isNotEmpty(code)&&scode.equals(code)){
		String userName = user.getUsername();
		Subject currentUser = SecurityUtils.getSubject();
		HashMap<String ,String > resMap=new HashMap<>();
		if (!currentUser.isAuthenticated()) {
			UsernamePasswordToken token = new UsernamePasswordToken(userName, user.getPassword());
			try {
				if (rememberMe) {
					token.setRememberMe(true);
				}
				//存入用户
				currentUser.login(token);
				resMap.put("msg","success");
			} catch (UnknownAccountException uae) {
				logger.info("对用户[" + userName + "]进行登录验证..验证未通过,未知账户");
//			            redirectAttributes.addFlashAttribute("message", "未知账户");
				resMap.put("msg","noaccount");
			} catch (IncorrectCredentialsException ice) {
				logger.info("对用户[" + userName + "]进行登录验证..验证未通过,错误的凭证");
				resMap.put("msg","nopasswd");
			} catch (LockedAccountException lae) {
				logger.info("对用户[" + userName + "]进行登录验证..验证未通过,账户已锁定");
				resMap.put("msg","lock");
			} catch (ExcessiveAttemptsException eae) {
				logger.info("对用户[" + userName + "]进行登录验证..验证未通过,错误次数过多");
				resMap.put("msg","nopasswd");
			} catch (AuthenticationException ae) {
				//通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
				logger.info("对用户[" + userName + "]进行登录验证..验证未通过,堆栈轨迹如下");
				resMap.put("msg","nopasswd");
			}
		}
		return resMap;
	}

	/**
	 * 退出登陆
	 *
	 * @return
	 */
	@GetMapping("Loginout")
	public String LoginOut(HttpServletRequest request, HttpServletResponse response) {
		//在这里执行退出系统前需要清空的数据
		Subject subject = SecurityUtils.getSubject();
		//注销
		subject.logout();
		return "redirect:/login";
	}


	/****页面测试****/
	@GetMapping("Out404")
	public String Out404(HttpServletRequest request, HttpServletResponse response) {
		return "redirect:/error/404";
	}

	@GetMapping("Out403")
	public String Out403(HttpServletRequest request, HttpServletResponse response) {
		return "redirect:/error/403";
	}

	@GetMapping("Out500")
	public String Out500(HttpServletRequest request, HttpServletResponse response) {

		return "redirect:/error/500";
	}

	/**
	 * 权限测试跳转页面
	 *
	 * @param request
	 * @param response
	 * @return
	 */
	@GetMapping("Outqx")
//	@RequiresPermissions("system:user:asd")
	public String Outqx(HttpServletRequest request, HttpServletResponse response) {

		return "redirect:/error/500";
	}

	/****页面测试EDN****/


	/**
	* Description 返回权限下 用户基本信息
	* @param request
	* @Author junwei
	* @Date 10:03 2019/5/29
	**/
	@GetMapping(value = "/getUserInfo")
	@ResponseBody
	public LinkedHashMap<String, Object> getUserInfo(HttpServletRequest request) throws Exception {
		try {
			Subject currentUser = SecurityUtils.getSubject();
			UserPojo user = (UserPojo)currentUser.getPrincipal();
			String sql=" select U.id,U.USERNAME as USER_NAME,UR.SYS_ROLE_ID,R.role_name  from T_SYS_USER_2 u,\n" +
					"T_SYS_ROLE_USER_2 ur,T_SYS_ROLE_2 r where U.id=UR.SYS_USER_ID\n" +
					"AND R.id=UR.SYS_ROLE_ID and u.username='"+user.getUsername()+"' ";
			List<LinkedHashMap<String, Object>> users = diySqlService.diySqlList(sql);
			HttpSession session = request.getSession();
			session.setAttribute("id", users.get(0).get("ID"));
			session.setAttribute("userName", users.get(0).get("USER_NAME"));
			session.setAttribute("deptName", users.get(0).get("ROLE_NAME"));
			return users.get(0);
		} catch (Exception e) {
           logger.error("错误信息:",e);
			return  null;
		}
	}


	// http://192.168.9.182:8080/contra/getMuen
	@GetMapping(value = "/getMuen")
	@ResponseBody
	public Map<String, Object> getMuen(String userCode) {
		try {
			List<MenuPojo> rootMenu=null;
//
			rootMenu = menuservice.queryMenuListCode(userCode);

			List<MenuPojo> menuList = new ArrayList<MenuPojo>();
			// 先找到所有的一级菜单
			for (int i = 0; i < rootMenu.size(); i++) {
				// 一级菜单没有parentId
				if (StringUtils.isBlank(rootMenu.get(i).getFunctionParentCode())) {
					menuList.add(rootMenu.get(i));
				}
			}
			// 为一级菜单设置子菜单，getChild是递归调用的
			for (MenuPojo menu : menuList) {
				menu.setChildren(getChild(menu.getFunctionCode(), rootMenu));
			}
			Map<String, Object> jsonMap = new HashMap<>();
			jsonMap.put("menu", menuList);
			return jsonMap;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}


	/**
	 * 递归查找子菜单
	 *
	 * @param id
	 *            当前菜单id
	 * @param rootMenu
	 *            要查找的列表
	 * @return
	 */
	private List<MenuPojo> getChild(String id, List<MenuPojo> rootMenu) {
		// 子菜单
		List<MenuPojo> childList = new ArrayList<>();
		for (MenuPojo menu : rootMenu) {
			// 遍历所有节点，将父菜单id与传过来的id比较
			if (StringUtils.isNotBlank(menu.getFunctionParentCode())) {
				if (menu.getFunctionParentCode().equals(id)) {
					childList.add(menu);
				}
			}
		}
		// 把子菜单的子菜单再循环一遍
		for (MenuPojo menu : childList) {// 没有url子菜单还有子菜单
			if (StringUtils.isBlank(menu.getHref())) {
				// 递归
				menu.setChildren(getChild(menu.getFunctionCode(), rootMenu));
			}
		} // 递归退出条件
		if (childList.size() == 0) {
			return null;
		}
		return childList;
	}


}
