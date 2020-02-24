$f("df", ___, function (_selector, _page, _data) {
    $(_selector).append(template.render(_page, _data))
});