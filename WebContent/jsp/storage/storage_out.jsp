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
<table id="storage_data"></table>
<div id="storage_data_pager"></div>

<div id="out_storage_dialog" title="出库操作对话框">
    <form id="out_storage_form" method="post" action="">
        <input type="hidden" id="h_sid">
        <table>
            <tbody>
                <tr>
                    <td><label class="field_name" for="goods_count">货物数量:</label>
                    </td>
                    <td><input id="goods_count" class="field_value" name="info.goodsCount">
                    </td>
                    <td><span class="lh30"></span></td>
                </tr>
            </tbody>
        </table>
    </form>
</div>
<script type="text/javascript" src="js/storage/storage_out.js"></script>