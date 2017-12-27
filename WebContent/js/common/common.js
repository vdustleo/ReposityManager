window.CommonUI = (function($, module) {
    function commonGridConfig() {
        return {
            // multiselect: true,
            // caption: "数组数据操作",
            rowNum : 20,
            rowList : [ 20, 40, 60 ],

            // sortname : 'id',
            recordpos : 'right',
            viewrecords : true,
            prmNames : {
                search : "pageInfo.search",
                page : "pageInfo.page",
                rows : "pageInfo.rows",
                sidx : "pageInfo.sidx",
                sord : "pageInfo.sord",
                nd : "pageInfo.nd"
            },
            jsonReader : {
                root : "datas",
                page : "pageInfo.page",
                records : "pageInfo.record",
                total : "pageInfo.totalPage",
                sidx : "pageInfo.sidx",
                sord : "pageInfo.sord",
                userdata : "",
                repeatitems : false
            },
            formatter : {
                integer : {
                    thousandsSeparator : " ",
                    defaultValue : '0'
                },
                number : {
                    decimalSeparator : ".",
                    thousandsSeparator : " ",
                    decimalPlaces : 2,
                    defaultValue : '0.00'
                },
                currency : {
                    decimalSeparator : ".",
                    thousandsSeparator : " ",
                    decimalPlaces : 2,
                    prefix : "",
                    suffix : "",
                    defaultValue : '0.00'
                }
            }
        };
    }

    module.commonGridConfig = commonGridConfig;

    return module;

}($, window.CommonUI || {}));
