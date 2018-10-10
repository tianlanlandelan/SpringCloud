package com.originaldreams.common.router;

/**
 * @author yangkaile
 * @date 2018-09-18 13:46:58
 */
public class MyUserManagerRouter implements RouterInterface{

    @Override
    public int getRouterId(int index) {
        return 10 * 1000 + index;
    }

    private static MyUserManagerRouter _instance;
    private static MyUserManagerRouter getInstance(){
        if(_instance == null){
            _instance = new MyUserManagerRouter();
        }
        return _instance;
    }

    public final int logon                      = getRouterId(1);
    public final int register                   = getRouterId(2);
    public final int getAllRoles                = getRouterId(3);
    public final int getRoleByUserId            = getRouterId(4);
    public final int getRolesByRouterId         = getRouterId(5);
    public final int getUserByRoleId            = getRouterId(6);
    public final int getAllRouters              = getRouterId(7);
    public final int getRoutersByRoleId         = getRouterId(8);
    public final int getRouterIdsByUserId       = getRouterId(9);
    public final int getAllUserNameAndRoleName  = getRouterId(10);
    public final int addRole                    = getRouterId(11);
    public final int addRoleForUser             = getRouterId(12);
    public final int addRouterForRole           = getRouterId(13);
    public final int deleteRoleById             = getRouterId(14);
    public final int updateRole                 = getRouterId(15);
    public final int getUserInfoById            = getRouterId(16);
    public final int updateUserInfo             = getRouterId(17);

}
