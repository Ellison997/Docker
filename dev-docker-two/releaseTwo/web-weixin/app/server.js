$f("df", ___, function (_url) {

    $f("set", "domain", "http://192.168.1.103");
    //本地使用
    //var server = "/proxy/http://192.168.52.101:4000/";
    //docker 使用
    var server = "/backend/";
    return server + _url;
});