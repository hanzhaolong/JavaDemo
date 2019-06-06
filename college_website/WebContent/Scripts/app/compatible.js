function CompatiblePlaceHolder() {
    if (!$.support.leadingWhitespace) {
        $('input[placeholder]').each(function () {
            var input = $(this);
            input.val(input.attr('placeholder'));
            input.focus(function () {
                if (input.val() == input.attr('placeholder')) {
                    input.val('');
                }
            });
            $(input).blur(function () {
                input.val(input.attr('placeholder'));
            });
        });
    }
}

//获取IE版本
function getIEVersion() {
    var userAgent = navigator.userAgent.toLowerCase();
    if (userAgent.match(/msie ([\d.]+)/) != null) {
        //ie6--ie9
        uaMatch = userAgent.match(/msie ([\d.]+)/);
        return 'IE' + uaMatch[1];
    }
    else if (userAgent.match(/(trident)\/([\w.]+)/)) {
        uaMatch = userAgent.match(/trident\/([\w.]+)/);
        switch (uaMatch[1]) {
            case "4.0":
                return "IE8";
            case "5.0":
                return "IE9";
            case "6.0":
                return "IE10";
            case "7.0":
                return "IE11";
            default:
                return undefined;
        }
    }
    return undefined;
}