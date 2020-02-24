$f("df", ___, function (_listdata, _userdata) {
    var title = "",
        desc = "",
        attr = _listdata.templates[0].attr;

    for (var i = 0; i < attr.length; i++) {
        if (attr[i].istitle) {
            title = _userdata[attr[i].title];
        }
        if (attr[i].isdesc) {
            desc = _userdata[attr[i].title];
        }
    }

    return {
        type: _listdata.groupname,
        label: ["#标签"],
        title: title,
        desc: desc,
        data: _userdata,
        action: _listdata.templates[0].action,
        templatename: _listdata.templates[0].name,
        templateid: _listdata._id,
        linklistid: _listdata.linklistid
    };
});