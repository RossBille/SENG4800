var app = app || {};
var canvas = app.canvas = document.getElementById('game');
var context = app.context = canvas.getContext('2d');

/* Our callback to perform action on each object */
app.action = function(objects, myList) {
    var list;

    if (typeof myList === 'undefined') {
        list = app.list || [];
    }
    else {
        list = myList;
    }

    context.clearRect(0, 0, canvas.width, canvas.height);

    if (objects.length <= 0) {
        return new RangeError("Array must be greater than zero")
    }

    while (objects.length > 0) {
        var object = objects.pop();
        var img = getImage(object);
        context.drawImage(img, img.setAtX, img.setAtY)
    }


    function getImage(object) {
        if (list[object.id] !== undefined) {
            var image = list[object.id];
        }
        else {
            var image = new Image();
        }
        image.src = object.imageUrl;
        list[object.id] = image;
        image.setAtX = object.outputPos.x;
        image.setAtY = object.outputPos.y;

        return image;
    }
    return list;
}
