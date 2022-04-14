let mygrid;
$(function () {
    //构建dgrid表格
    loadSel();
    initGrid();
});

//初始化主页表格信息
function initGrid() {
    mygrid = new dhtmlXGridObject('grid');
    mygrid.setImagePath(CONTEXT_PATH + "/static/resources/v2/static/plugg/dhtmlx/dhtmlxGrid/codebase/imgs/");
    mygrid.setHeader("#master_checkbox,账户,姓名,性别,部门,出生日期,登记日期,是否禁用,排序号,查看,修改,删除");
    mygrid.setInitWidths("40,130,130,80,80,150,150,80,80,60,60,60");
    mygrid.setColTypes("ch,ro,ro,ro,ro,ro,ro,ro,ro,img,img,img");
    mygrid.setColAlign("center,center,center,center,center,center,center,center,center,center,center,center");
    mygrid.setColSorting("na,str,str,str,str,str,str,str,str,na,na,na");
    mygrid.setSkin("tdh_default");
    mygrid.pagingLayout("first,prev,page,next,last,limit,refresh,skip,count");
    mygrid.pagingLimits("10,20,50");
    mygrid.enablePagingon(true, 20);
    mygrid.init();
    mygrid.loadXML(CONTEXT_PATH+"loadUserXml");
}

//点击查询按钮
function queryInfo() {
    let yhid = $("#yhzh").val();
    let yhbm = $("#sYhbm").val();

    mygrid.loadXML(CONTEXT_PATH + "/loadUserXml?yhid=" + yhid + "&yhbm=" + yhbm);

}

//添加用户
function addForm() {
    openLayerModal(CONTEXT_PATH + "jumpToAddPage?func=add&kind=00003", "添加用户", 700, 400, addFormCallback());
}

//添加用户的回调函数，用于刷新
function addFormCallback(rtn) {
    if (rtn === "success") {
        return mygrid.loadXML(CONTEXT_PATH + "/loadUserXml");
    }
}

//查看用户信息
function view(yhid) {
    openLayerModal(CONTEXT_PATH + "viewUserInfo?yhid=" + yhid + "&func=view&kind=00003", "用户详细信息", 700, 400);
}

//修改用户信息
function modify(yhid) {
    openLayerModal(CONTEXT_PATH + "viewUserInfo?yhid=" + yhid + "&func=modify&kind=00003", "修改用户", "700", "400", "modifyCallBack");
}

//修改用户信息的回调函数，用于刷新
function modifyCallBack(rtn) {
    if (rtn === "success") {
        mygrid.loadPage();
    }
}

//批量删除
function bulkDeletion() {
    let gridlist = mygrid.getCheckedRows(0);

    if (gridlist == null || gridlist.length === 0) {
        layer.msg("还没选择任何信息", {
            icon: 7,
            shade: 0.000001,
            time: 1000
        })
    } else {
        layer.confirm('确定删除这些数据吗？', {
            title: '删除提示',
            btn: ['确定', '取消'],
            btn1: function () {
                deleted(gridlist);
            },
            btn2: function () {
                layerClose(true);
            }
        })
    }
}

//删除图标的点击事件
function delInfo(yhid) {
    layer.confirm('确定删除这条数据吗？', {
        title: '删除提示',
        btn: ['确定', '取消'],
        btn1: function () {
            deleted(yhid);
        },
        btn2: function () {
            layerClose(true);
        }
    });
}

//删除动作
function deleted(yhidInfo) {
    encodeStr()
    let index = layer.msg('正在删除中...请稍等', {icon: 16, shade: 0.4, time: false});
    $.ajax({
        url: CONTEXT_PATH + "/bulkDel",
        data: {del: yhidInfo},
        dataType: "JSON",
        success: function (data) {
            layer.close(index);
            if (data.code === 0) {
                layer.msg(data.data, {
                    icon: 1,
                    shade: 0.000001, //不展示遮罩，但是要有遮罩效果
                    time: 2000
                }, function () {
                    mygrid.loadPage();
                })
            } else if (data.code === 1) {
                layer.msg("删除失败，请联系管理员！", {
                    icon: 2,
                    shade: 0.000001, //不展示遮罩，但是要有遮罩效果
                    time: 2000
                })
            }
        },
        error: function () {
            layer.close(index);
            layer.msg("请求出现了错误，请联系管理员解决！", {
                icon: 2,
                shade: 0.000001, //不展示遮罩，但是要有遮罩效果
                time: 2000
            })
        }
    })
}

//注销
function logoff() {
    $.get(CONTEXT_PATH + "logoff", function (path) {
        window.location.href = CONTEXT_PATH + path;
    });
}

//加载下拉框
function loadSel(){
    let departs = $('#iDeparts');
    let jDeparts = JSON.parse(departs.val());
    console.log(jDeparts);
    $.each(jDeparts, function (index, item) {
        $('#sYhbm').append(new Option(item.bmmc, item.bmdm));
    });
}