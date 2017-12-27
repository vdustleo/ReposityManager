<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div id="" class="func_title">添加仓库</div>
<div id="repo_form" class="form">
    <form id="repo_add_form" method="post" action="">
        <table>
            <tbody>
                <tr>
                    <td><label class="field_name" for="repo_code">仓库编号:</label></td>
                    <td class="wl250"><input id="repo_code" class="field_value w150" name="info.repoCode"></td>
                    <td><span class="lh30"></span></td>
                </tr>
                <tr>
                    <td><label class="field_name" for="sep_num">隔档数量:</label></td>
                    <td class="w250"><input id="sep_num" class="field_value w150" name="info.sepNum"></td>
                    <td><span class="lh30"></span></td>
                </tr>
                <tr>
                    <td><label class="field_name" for="admin">管理员:</label></td>
                    <td class="w250"><input id="admin" class="field_value w150" name="info.admin"></td>
                    <td><span class="lh30"></span></td>
                </tr>
                <tr>
                    <td><label class="field_name"></label></td>
                    <td class="w250">
                        <button id="add_submit" type="submit">确定</button>
                        <button id="cancel_submit" type="button">取消</button>
                    </td>
                    <td><p id="repo_notify" class="notify_info hide">仓库添加成功!</p></td>
                </tr>
            </tbody>
        </table>
    </form>
</div>
<script type="text/javascript" src="js/reposity/reposity_add.js"></script>
