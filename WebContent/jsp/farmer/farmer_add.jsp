<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div id="" class="func_title">果农登记</div>
<div id="farmer_form" class="form">
    <form id="farmer_add_form" method="post" action="">
        <table>
            <tbody>
                <tr>
                    <td><label class="field_name" for="farmer_name">果农姓名:</label></td>
                    <td class="wl250"><input id="farmer_name" class="field_value w150" name="info.farmerName"></td>
                    <td><span class="lh30"></span></td>
                </tr>
                <tr>
                    <td><label class="field_name" for="farmer_tel">联系电话:</label></td>
                    <td class="w250"><input id="farmer_tel" class="field_value w150" name="info.farmerTel"></td>
                    <td><span class="lh30"></span></td>
                </tr>
                <tr>
                    <td><label class="field_name" for="unit_price">存储单价:</label></td>
                    <td class="w250"><input id="unit_price" class="field_value w150" name="info.unitPrice"></td>
                    <td><span class="lh30"></span></td>
                </tr>
                <tr>
                    <td><label class="field_name"></label></td>
                    <td class="w250">
                        <button id="add_submit" type="submit">确定</button>
                        <button id="cancel_submit" type="button">取消</button>
                    </td>
                    <td><p id="farmer_notify" class="notify_info hide">果农登记成功!</p></td>
                </tr>
            </tbody>
        </table>
    </form>
</div>
<!-- 提示对话框信 -->
<div id="confirm_dialog" title="存储编号确认">
    <p class="dialog-info">
        果农信息登记成功，存储编码为<em id="storage_sn_info" style="font-weight:bold"></em>，请您务必牢记此编号。
    </p>
</div>
<script type="text/javascript" src="js/farmer/farmer_add.js"></script>
