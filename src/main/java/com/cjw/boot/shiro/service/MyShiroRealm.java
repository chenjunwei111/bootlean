package com.cjw.boot.shiro.service;


import com.cjw.boot.mapper.admin.UserMapper;
import com.cjw.boot.pojo.admin.UserPojo;
import com.cjw.boot.service.diy.DiySqlService;
import com.cjw.boot.util.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;


/**
 * 身份校验核心类
 *
 * @author fuce
 * @ClassName: MyShiroRealm
 * @date 2018年8月25日
 */
@Service
public class MyShiroRealm extends AuthorizingRealm {


    @Autowired
    private UserMapper userMapper;

//    @Autowired
//    private PermissionDao permissionDao;//权限dao


    @Autowired
    private DiySqlService diySqlService;

    /**
     * 认证登陆
     */
    @SuppressWarnings("unused")
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken token) throws AuthenticationException {

        //加这一步的目的是在Post请求的时候会先进认证，然后在到请求
        if (token.getPrincipal() == null) {
            return null;
        }
        String username = (String) token.getPrincipal();
        String password = new String((char[]) token.getCredentials());
        // 通过username从数据库中查找 User对象，如果找到，没找到.
        // 实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法


//        查数据库
        UserPojo userInfo = userMapper.queryUserName(username);

        System.out.println(userInfo);
        System.out.println("----->>userInfo=" + userInfo.getUsername() + "---" + userInfo.getPassword());
        if (userInfo == null)
            return null;
        else {
            SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                    userInfo, // 用户名
                    userInfo.getPassword(), // 密码
                    getName() // realm name
            );
            return authenticationInfo;
        }

    }

    /**
     * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");
        if (principals == null) {
            throw new AuthorizationException("principals should not be null");
        }

//        Gson gson = new Gson();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        UserPojo userinfo = (UserPojo) principals.getPrimaryPrincipal();
        String uid = userinfo.getId();

        String querySql="select r.id,r.ROLE_NAME as NAME,ru.sys_role_id ,ru.sys_user_id from t_sys_role_2 r\n" +
                "\t\tinner JOIN t_sys_role_user_2 ru ON  r.id=ru.sys_role_id\n" +
                "\t\tand ru.sys_user_id='"+uid+"' ";
        List<LinkedHashMap<String, Object>> roleList=diySqlService.diySqlList(querySql);

        for (LinkedHashMap<String, Object> userrole : roleList) {
            String rolid = userrole.get("ID").toString();//角色id
            authorizationInfo.addRole(userrole.get("NAME").toString());//添加角色名字

            String checkPerSql="\tselect p.PERMS from t_sys_premission_2 p left join t_sys_permission_role_2 pr \n" +
                    "  on p.FUNCTION_CODE=pr.PERMISSION_CODE where pr.role_code='"+rolid+"' ";

            List<LinkedHashMap<String, Object>> perList=diySqlService.diySqlList(checkPerSql);

            for (LinkedHashMap<String, Object> p : perList) {
//                System.out.println("角色下面的权限:" + gson.toJson(p));
                if (p!=null && StringUtils.isNotEmpty(p.get("PERMS").toString())) {
                    authorizationInfo.addStringPermission(p.get("PERMS").toString());
                }

            }
        }


        return authorizationInfo;
    }

    /**
     * 清理缓存权限
     */
    public void clearCachedAuthorizationInfo() {
        this.clearCachedAuthorizationInfo(SecurityUtils.getSubject().getPrincipals());
    }

}
