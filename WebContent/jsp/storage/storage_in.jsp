<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript" src="js/storage/storage_in.js"></script>
<div id="" class="func_title">添加仓库</div>
<div id="storage_form" class="form">
    <form id="storage_add_form" method="post" action="">
        <table>
            <tbody>
                <tr>
                    <td><label class="field_name" for="storage_sn">存储编号:</label></td>
                    <td><input id="storage_sn" class="field_value" style="display: inline-block" name="info.storageSn"> <a id="relate_info"
                        type="button" style="cursor: pointer; text-decoration: none">获取详细信息</a></td>
                    <td><span class="lh30"></span></td>
                </tr>
                <tr>
                    <td><label class="field_name" for="farmer_name">果农姓名:</label></td>
                    <td><input id="farmer_name" class="field_value" name="info.farmerName" readonly></td>
                    <td><span class="lh30"></span></td>
                </tr>
                <tr>
                    <td><label class="field_name" for="farmer_tel">联系电话:</label></td>
                    <td><input id="farmer_tel" class="field_value" readonly></td>
                    <td><span class="lh30"></span></td>
                </tr>
                <tr>
                    <td><label class="field_name" for="unit_price">存储单价:</label></td>
                    <td><input id="unit_price" class="field_value" name="info.unitPrice" readonly></td>
                    <td><span class="lh30"></span></td>
                </tr>
                <tr>
                    <td><label class="field_name" for="apple_type">苹果类型:</label></td>
                    <td><select id="apple_type" class="field_value" name="info.appleType"></select></td>
                    <td><span class="lh30"></span></td>
                </tr>
                <tr>
                    <td><label class="field_name" for="apple_size">苹果尺寸:</label></td>
                    <td><select id="apple_size" class="field_value" name="info.appleSize"></select></td>
                    <td><span class="lh30"></span></td>
                </tr>
                <tr>
                    <td><label class="field_name" for="repo_code">仓库编号:</label></td>
                    <td><select id="repo_code" class="field_value" name="info.repoCode"></select></td>
                    <td><span class="lh30"></span></td>
                </tr>
                <tr>
                    <td><label class="field_name" for="goods_count">货物数量:</label></td>
                    <td><input id="goods_count" class="field_value" name="info.goodsCount"></td>
                    <td><span class="lh30"></span></td>
                </tr>
                <tr>
                    <td><label class="field_name" for="repo_sep">隔档编号:</label></td>
                    <td><select id="repo_sep" class="field_value" name="info.repoSep"></select></td>
                    <td><span class="lh30"></span></td>
                </tr>
                <tr>
                    <td><label class="field_name"></label></td>
                    <td class="w250">
                        <button id="add_submit" type="submit">确定</button>
                        <button id="cancel_submit" type="button">取消</button>
                    </td>
                    <td><p id="storage_notify" class="notify_info hide">入库添加成功!</p></td>
                </tr>
            </tbody>
        </table>
    </form>
</div>
