$(document).ready(function () {
    $.getScript("/resources/c/common/include.js");


        var  webroot=document.location.href;
        webroot=webroot.substring(webroot.indexOf('//')+2,webroot.length);
        webroot=webroot.substring(webroot.indexOf('/')+1,webroot.length);
        webroot=webroot.substring(0,webroot.indexOf('/'));
        alter(webroot);

});

