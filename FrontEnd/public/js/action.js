var app = app || {};

/* Our callback to perform action on each object */
app.action = function(objects) {
    var canvas = document.getElementById('game');
    var context = canvas.getContext('2d');

    while(objects.length > 0) {
        var object = objects.pop();
        var image = new Image();
        image.src = object.sprite;
        image.setAtX = object.dx;
        image.setAtY = object.dy;
        image.onload = function () {
            context.drawImage(this, this.setAtX, this.setAtY);
        };
    }
};

app.Image = function(id, x, y) {
    this.id = id;
    this.x = x;
    this.y = y;
};
