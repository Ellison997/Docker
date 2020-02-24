$f("df", ___, function(_url) {

    $f("set", "domain", "http://0.0.0.0");
    //本地使用
    // var server = "/proxy/http://127.0.0.1:4000/";
    //docker 使用
    var server = "/backend/";
    return server + _url;
});
