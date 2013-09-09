var app = app || {};
var list = app.list || [];

/* Our callback to perform action on each object */
app.action = function(objects) {
    var canvas = document.getElementById('game');
    var context = canvas.getContext('2d');

    while(objects.length > 0) {
        var object = objects.pop();
        console.log(object);
        var img = getImage(object);
        console.log(img);
        img.onload = function () {
            context.drawImage(this, this.setAtX, this.setAtY);
        };
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
