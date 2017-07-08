package com.wuxian99.finance.basedata.service.system.impl;

import com.wuxian99.finance.basedata.domain.entity.system.DdicItemEntity;
import com.wuxian99.finance.basedata.domain.model.SigninUser;
import com.wuxian99.finance.basedata.repository.system.DdicItemRepository;
import com.wuxian99.finance.basedata.service.system.UserService;
import com.wuxian99.finance.common.Result;
import com.wuxian99.finance.common.SigninCommand;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;
import java.util.Hashtable;
import java.util.List;

/**
 * Created by sxjiang on 2017/3/23.
 */
@Service
public class UserServiceImpl implements UserService {
    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private static final String ATTR_NAME = "name";
    private static final String ATTR_SAMA_NAME = "sAMAccountName";
    private static final String ATTR_DEPARTMENT = "Department";
    @Value("${handpay.ad.domain}")
    private String domain;
    @Value("${handpay.ad.url}")
    private String url;
    @Value("${handpay.ad.userName}")
    private String userName;
    @Value("${handpay.ad.password}")
    private String password;

    @Autowired
    private DdicItemRepository ddicItemRepository;

    @Override
    public Result<SigninUser> signin(SigninCommand signinCommand) {
        LdapContext ctx = null;
        String accountName = signinCommand.getAccount();
        try {
            ctx = createAuthenticateContext( accountName , signinCommand.getPassword() );
            // 域节点
            String searchBase = "OU=user1,DC=99wuxian,DC=com";
            // LDAP搜索过滤器类
            String searchFilter = ATTR_SAMA_NAME + "=" + accountName;
            // 搜索控制器
            SearchControls searchCtls = new SearchControls();
            // 设置搜索范围
            searchCtls.setSearchScope( SearchControls.SUBTREE_SCOPE );
            // 定制返回属性,sAMAccountName：指域用户登录名，name：域用户真实姓名
            String returnedAtts[] = { ATTR_SAMA_NAME, ATTR_NAME,ATTR_DEPARTMENT };
            // 设置返回属性集
            searchCtls.setReturningAttributes(returnedAtts);
            // 根据设置的域节点、过滤器类和搜索控制器搜索LDAP得到结果
            NamingEnumeration<SearchResult> answer = ctx.search( searchBase , searchFilter , searchCtls );
            // 遍历结果集
            while ( answer.hasMoreElements() ) {
                Attributes attrs = answer.next().getAttributes();
                String samaAccont = getValue( attrs , ATTR_SAMA_NAME );
                if ( accountName.equals(samaAccont) ) {
                    String name = getValue( attrs , ATTR_NAME );
                    String department = getValue( attrs , ATTR_DEPARTMENT );
                    SigninUser user = new SigninUser();
                    BeanUtils.copyProperties(signinCommand,user);
                    user.setSysAuth(false);
                    user.setFinanceAuth(false);
                    user.setName(name);

                    extractSigninUser(accountName, department, user);
                    return Result.buildSuccess(user);
                }
            }
        }
        catch (AuthenticationException e) {
            logger.warn( "authentication failed, domain = [{}], account = [{}], message = {}" , domain , accountName , e.getMessage() );
        }
        catch ( Exception e ) {
            logger.error( "authentication failed, url = [{}], domain = [{}], account = [{}]" , url, domain, accountName , e );
        }
        finally {
            if ( ctx != null ) {
                try {
                    ctx.close();
                }
                catch ( NamingException e ) {
                    logger.error( "LDAP context close error" , e );
                }
            }
        }
        return null;
    }

    private void extractSigninUser(String accountName, String department, SigninUser user) {
        List<DdicItemEntity> departmentCn = ddicItemRepository.findByCategory("departmentCn");
        for(DdicItemEntity entity : departmentCn){
            if(department.indexOf(entity.getKey()) > -1){
                user.setDepartment(entity.getValue());
            }
        }
        List<DdicItemEntity> sysAuth = ddicItemRepository.findByCategory("sysAuth");
        for(DdicItemEntity item : sysAuth){
            if(StringUtils.equals(accountName,item.getKey())){
                user.setSysAuth(true);
            }
        }
        List<DdicItemEntity> financeAuth = ddicItemRepository.findByCategory("financeAuth");
        for(DdicItemEntity item : financeAuth){
            if(StringUtils.equals(accountName,item.getKey())){
                user.setFinanceAuth(true);
            }
        }
    }

    private LdapContext createAuthenticateContext(String accountName, String password)
            throws NamingException {

        Hashtable<String, String> env = new Hashtable<String, String>();
        // LDAP访问安全级别
        env.put( Context.SECURITY_AUTHENTICATION , "simple" );
        // AD User
        env.put( Context.SECURITY_PRINCIPAL , domain + "\\" + accountName );
        // AD Password
        env.put( Context.SECURITY_CREDENTIALS , password );
        // LDAP工厂类
        env.put( Context.INITIAL_CONTEXT_FACTORY , "com.sun.jndi.ldap.LdapCtxFactory" );
        env.put( Context.PROVIDER_URL , url );

        return new InitialLdapContext( env , null );
    }

    private String getValue(Attributes attrs, String name) throws NamingException {
        Attribute attr = attrs.get( name );
        if ( attr == null || attr.size() == 0 ) {
            return null;
        }
        return String.valueOf( attr.get() );
    }
}
