function chang(id) {
    var informer = document.getElementById("informer"+id);
    var className = informer.className;
    if (className.indexOf(' expanded') === -1) {
        className += ' expanded';
    } else {
        className = className.replace(' expanded', '');
    }
    informer.className = className;
    return false;
}

