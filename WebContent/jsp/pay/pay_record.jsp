<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div id="" class="func_title">搜索条件</div>
<div class="search">
    <form id="search_form" method="post" action="">
        <table>
            <tbody>
                <tr>
                    <td><label class="field_name" for="farmer_name">果农姓名:</label></td>
                    <td><input id="farmer_name" class="field_value" name="cond.farmerName"></td>
                    <td><label class="field_name" for="storage_sn">存储编号:</label></td>
                    <td><input id="storage_sn" class="field_value" name="cond.storageSn"></input></td>
                    <td><label class="field_name" ></label></td>
                    <td><button id="search_submit">查找</button></td>
                    <td><button id="search_cancel" type="reset">清除</button></td>
                </tr>
            </tbody>
        </table>
    </form>
</div>
<div id="" class="func_title">付款信息</div>
<button id="print_record" class="oper_btn">打印付款记录</button>
<table id="pay_data"></table>
<div id="pay_data_pager"></div>
<script type="text/javascript" src="js/pay/pay_record.js"></script>