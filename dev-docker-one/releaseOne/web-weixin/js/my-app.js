function system_init(noncestr, timestamp, singature, plugin, data) {
    wx.config({
        debug: true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
        appId: 'wxddd51a814db3d20b', // 必填，公众号的唯一标识
        timestamp: timestamp, // 必填，生成签名的时间戳
        nonceStr: noncestr, // 必填，生成签名的随机串
        signature: singature, // 必填，签名
        jsApiList: [
            "onMenuShareTimeline",
            "onMenuShareAppMessage",
            "chooseImage"
        ] // 必填，需要使用的JS接口列表
    });

    wx.ready(function () {
        // config信息验证后会执行ready方法，所有接口调用都必须在config接口获得结果之后，config是一个客户端的异步操作，所以如果需要在页面加载时就调用相关接口，则须把相关接口放在ready函数中调用来确保正确执行。对于用户触发时才调用的接口，则可以直接调用，不需要放在ready函数中。
        wx.onMenuShareAppMessage({
            title: "给你传张小纸条", // 分享标题
            desc: "传纸条啦~", // 分享描述
            link: $f("get", "domain") + "/wxredirect", // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
            imgUrl: $f("get", "domain") + '/weixin/img/coffee-icon.png', // 分享图标
            trigger: function (res) {
                // 不要尝试在trigger中使用ajax异步请求修改本次分享的内容，因为客户端分享操作是一个同步操作，这时候使用ajax的回包会还没有返回
            },
            success: function (res) {},
            cancel: function (res) {},
            fail: function (res) {}
        });
        wx.onMenuShareTimeline({
            title: "给你传张小纸条", // 分享标题
            link: $f("get", "domain") + "/wxredirect", // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
            imgUrl: $f("get", "domain") + '/weixin/img/coffee-icon.png', // 分享图标
            trigger: function (res) {
                // 不要尝试在trigger中使用ajax异步请求修改本次分享的内容，因为客户端分享操作是一个同步操作，这时候使用ajax的回包会还没有返回
            },
            success: function (res) {},
            cancel: function (res) {},
            fail: function (res) {}
        });
    });

    if (plugin != undefined && plugin != null && plugin.length > 0) {
        $f("plugin", plugin, data)
    }

}