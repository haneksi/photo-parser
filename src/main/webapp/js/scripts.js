var listNodes = document.getElementsByClassName("header-portfolio");
Array.prototype.forEach.call(listNodes, function(node) {
    var url = node.innerHTML;
    var newUrl;
    if(url.startsWith('http://www.')){
        newUrl = url.replace(/http:\/\/www./g, '').split('.')[0];
    } else{
        newUrl = url.replace(/http:\/\//g, '').split('.')[0];
    }
    node.innerText = newUrl;
});