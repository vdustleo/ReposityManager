<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div id="" class="func_title">搜索条件</div>
<div class="search">
    <form id="search_form" method="post" action="">
        <table>
            <tbody>
                <tr>
                    <td><label class="field_name" for="farmer_name">果农姓名:</label></td>
                    <td><input id="farmer_name" class="field_value" style="width: 80px" name="cond.farmerName"/></td>
                    <td><label class="field_name" for="storage_sn">存储编号:</label></td>
                    <td><input id="storage_sn" class="field_value" style="width: 80px" name="cond.storageSn"/></td>
                    <td><label class="field_name" for="apple_type">苹果类型:</label></td>
                    <td><select id="apple_type" class="field_value" style="width: 80px" name="cond.appleType"></select></td>
                    <td><label class="field_name" for="apple_size">苹果尺寸:</label></td>
                    <td><select id="apple_size" class="field_value" style="width: 80px" name="cond.appleSize"></select></td>
                    <td><label class="field_name" for="repo_code">仓库编号:</label></td>
                    <td><select id="repo_code" class="field_value" style="width: 80px" name="cond.repoCode"></select></td>
                    <td><label class="field_name" for="repo_sep" >隔档编号:</label></td>
                    <td><input id="repo_sep" class="field_value" style="width: 80px" name="cond.repoSep"/></td>
                    <td><label class="field_name" for="repo_sep" ></label></td>                    
                </tr>
                <tr>
                    <td><label class="field_name" for="farmer_name"></label></td>
                    <td><button id="search_submit">查找</button></td>
                    <td><button id="search_cancel" type="reset">清除</button></td>                
                </tr>
            </tbody>
        </table>
    </form>
</div>
<div class="cboth"></div>
<div id="" class="func_title">库存信息</div>
<button id="print_record_sum" class="oper_btn">打印入库汇总单</button>
<div class="statistic_info">
    库存数量:<span id="sum_goods"></span>
    存储总价:<span id="sum_price"></span>
    已付金额:<span id="pay_money"></span>
    欠款金额:<span id="owe_money"></span>
</div>
<table id="storage_data"></table>
<div id="storage_data_pager"></div>
<script type="text/javascript" src="js/storage/storage_query.js"></script>