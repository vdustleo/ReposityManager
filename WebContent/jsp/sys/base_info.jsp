<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div id="" class="func_title">基础信息</div>
<div id="base_form" class="form">
    <form id="base_info_form" method="post" action="">
        <table>
            <tbody>
                <tr>
                    <td><label class="field_name" for="repo_name">仓库名称:</label></td>
                    <td class="wl250"><input id="repo_name" class="field_value w150" name="info.repoCode"></td>
                    <td><span class="lh30"></span></td>
                </tr>
                <tr>
                    <td><label class="field_name"></label></td>
                    <td class="w250">
                        <button id="baseinfo_submit" type="submit">确定</button>
                    </td>
                    <td><p id="baseinfo_notify" class="notify_info hide">仓库名称修改成功!</p></td>
                </tr>
            </tbody>
        </table>
    </form>
</div>
<script type="text/javascript" src="js/sys/base_info.js"></script>
