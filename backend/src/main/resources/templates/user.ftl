
<@dt.init titles=["个人资料"]>

<form>
    <div class="form-group">
        <label for="domain">域:</label>
        <input type="text" id="domain" class="form-control" value="${signinUser.domain}" readonly>
    </div>
    <div class="form-group">
        <label for="domainAccount">域账户:</label>
        <input type="text" id="domainAccount" class="form-control" value="${signinUser.account}" readonly>
    </div>
    <div class="form-group">
        <label for="realName">真实姓名:</label>
        <input type="text" id="realName" class="form-control" value="${signinUser.name}" readonly>
    </div>
    <div class="form-group">
        <label for="department">所属部门:</label>
        <input type="text" id="department" class="form-control" value="${signinUser.department!"其他(非业务线)"}" readonly>
    </div>
    <div class="form-group">
        <label for="financeAuth">财务权限:</label>
        <input type="text" id="financeAuth" class="form-control" value="${signinUser.financeAuth?then('有', '无')}" readonly>
    </div>
    <div class="form-group">
        <label for="sysAuth">系统管理权限:</label>
        <input type="text" id="sysAuth" class="form-control" value="${signinUser.sysAuth?then('有', '无')}" readonly>
    </div>
</form>


</@dt.init>