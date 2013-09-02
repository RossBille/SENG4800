var app = app || {};
var list = app.list || [];
var canvas = app.canvas = document.getElementById('game');
var context = app.context = canvas.getContext('2d');

/* Our callback to perform action on each object */
app.action = function(objects) {
    context.clearRect(0, 0, canvas.width, canvas.height);

    while(objects.length > 0) {
        var object = objects.pop();
        var img = getImage(object);
        context.drawImage(img, img.setAtX, img.setAtY);
    }

    function getImage(object) {
        if (list[object.id] !== undefined) {
            var image = list[object.id];
        }
        else {
            var image = new Image();
        }
        image.src = object.sprite;
        image.setAtX = object.dx;
        image.setAtY = object.dy;
        list[object.id] = image;
        return image;
    }
};
