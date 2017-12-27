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
                    <td><label class="field_name" ></label></td>
                    <td><button id="search_submit">查找</button></td>
                    <td><button id="search_cancel" type="reset">清除</button></td>
                </tr>
            </tbody>
        </table>
    </form>
</div>
<div id="" class="func_title">果农信息</div>
<table id="farmer_data"></table>
<div id="farmer_data_pager"></div>
<script type="text/javascript" src="js/farmer/farmer_list.js"></script>