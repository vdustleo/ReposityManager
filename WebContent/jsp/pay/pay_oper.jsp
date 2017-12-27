<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript" src="js/pay/pay_oper.js"></script>
<div id="" class="func_title">搜索条件</div>
<div class="search">
    <form id="search_form" method="post" action="">
        <table>
            <tbody>
                <tr>
                    <td><label class="field_name" for="storage_sn">存储编号:</label></td>
                    <td><input id="storage_sn" class="field_value" name="cond.storageSn"></input></td>
                    <td><label class="field_name" ></label></td>
                    <td><button id="search_submit">查找</button></td>
                    <td><button id="search_cancel" type="reset">清除</button></td>
                    <td><label id="error_notify" class="error_submit hide">没有查询到相关的数据</label></td>
                </tr>
            </tbody>
        </table>
    </form>
</div>
<div id="" class="func_title">付款信息</div>
<div id="pay_form" class="form">
    <form id="pay_oper_form" method="post" action="">
        <input id="h_psn" type="hidden" name="info.psn"/>
        <input id="h_total_price" type="hidden" name="info.totalPrice"/>
        <input id="h_owe_price" type="hidden" name="info.owePrice"/>
        <input id="h_farmer_name" type="hidden" name="info.farmerName"/>
        <input id="h_storage_sn" type="hidden" name="info.storageSn"/>        
        <table>
            <tbody>
                <tr>
                    <td><label class="field_name" for="total_price">存款总价:</label></td>
                    <td><input id="total_price" class="field_value" readonly disabled="disabled"></td>
                    <td><label class="field_name" for="_pay_price">已付金额:</label></td>
                    <td><input id="_pay_price" class="field_value" readonly disabled="disabled"></td>
                    <td><label class="field_name" for="owe_price">欠款金额:</label></td>
                    <td><input id="owe_price" class="field_value" readonly disabled="disabled"></td>                    
                </tr>
                <tr>
                    <td><label class="field_name" for="pay_price" >支付金额:</label></td>
                    <td><input id="pay_price" class="field_value" name="info.payPrice" disabled="disabled"></td>
                    <td><span class="lh30"></span><label id="error_pay" class="error_submit hide">提交的金额不能大于欠款金额</label></td>
                </tr>
            </tbody>
        </table>
        <div style='width:450px'>
            <button id="confirm_print" class="w150" type="submit" disabled="disabled">确认支付并打印支付单</button>
            <button id="confirm_only" class="w150" type="button" disabled="disabled">确认支付不打印支付单</button>
            <button id="cancel_submit" type="button">取消</button>
        </div>
    </form>
</div>
