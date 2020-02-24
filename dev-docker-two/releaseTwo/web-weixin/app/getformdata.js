$f("df", ___, function (_selector) {
    var data = $(_selector).serializeArray();
    var rtnData = {};
    for (var i = 0; i < data.length; i++) {
        rtnData[data[i].name] = data[i].value;
    }
    return rtnData;
});