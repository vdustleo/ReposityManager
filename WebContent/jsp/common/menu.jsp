<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!-- 采用部门及岗位 控制显示的菜单及功能 -->
<div class="menu">
    <ul class="menu">
        <li><a id="home_page" href="javascript:void(0)">首页</a></li>
        <li><a id="farmer_manage" href="javascript:void(0)">果农管理</a>
            <ul>
                <li><a id="farmer_add" href="javascript:void(0)">果农登记</a></li>
                <li><a id="farmer_list" href="javascript:void(0)">果农信息</a></li>
            </ul>
        </li>        
        <li><a id="storage_in" href="javascript:void(0)">入库</a></li>
        <li><a id="storage_out" href="javascript:void(0)">出库</a></li>
        <li><a id="pay_oper" href="javascript:void(0)">付款</a></li>
        <li><a id="record_query" href="javascript:void(0)">查询</a>
            <ul>
                <li><a id="storage_in_record" href="javascript:void(0)">入库记录</a></li>
                <li><a id="storage_out_record" href="javascript:void(0)">出库记录</a></li>
                <li><a id="storage_query" href="javascript:void(0)">存库查询</a></li>
                <li><a id="pay_record" href="javascript:void(0)">付款记录</a></li>
                <li><a id="pay_statistic" href="javascript:void(0)">财务统计</a></li>
            </ul></li>
        <li><a id="" href="javascript:void(0)">系统设置</a>
            <ul>
                <li><a id="repo_manage" href="javascript:void(0)">仓库管理</a></li>
                <li><a id="repo_add" href="javascript:void(0)">仓库添加</a></li>
                <li><a id="base_info" href="javascript:void(0)">基础信息</a></li>
                <li><a id="" href="javascript:void(0)">用户管理</a></li>
                <li><a id="app_config" href="javascript:void(0)">APP配置</a></li>
            </ul></li>
    </ul>
</div>