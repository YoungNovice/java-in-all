// 点击执行按钮事件
$("#executeCmd").on("click", function (e) {
    $("#confirmModal").modal("show");
});

// 点击确定执行逻辑
$("#okBtn").on("click", function (e) {
    // 关掉confirm框
    $("#confirmModal").modal("hide");
    $('body').loading({
        loadingWidth:240,
        title:'请稍等!',
        name:'finishLoading',
        discription:'正在执行',
        direction:'column',
        type:'origin',
        // originBg:'#71EA71',
        originDivWidth:40,
        originDivHeight:40,
        originWidth:6,
        originHeight:6,
        smallLoading:false,
        loadingMaskBg:'rgba(0,0,0,0.2)'
    });
    var executeDeferred = $.ajax("../../execute/bat" + window.location.search);
    executeDeferred.done(function (result) {
        if (result && result.msg) {
            $("#executeMsg").text(result.msg);
        } else {
            $("#executeMsg").text(result.data);
        }
        $("#myModal").modal("show");
    });
    executeDeferred.fail(function (result) {
        /*$("#executeMsg").text(result.msg);
        $("#myModal").modal("show");*/
    });
    executeDeferred.always(function () {
        setTimeout(function(){
            removeLoading('finishLoading');
        }, 200);
    });
});
