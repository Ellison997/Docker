$f("df", ___, function (_arr, _fn) {
    var new_arr = [];
    for (var i = 0; i < _arr.length; i++) {
        var val1 = _fn(_arr[i]);
        if (val1 != undefined | val1 != null) {
            new_arr.push(_fn(_arr[i]));
        }
    }
    return new_arr;
});