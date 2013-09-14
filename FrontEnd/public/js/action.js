var app = app || {};
var list = app.list || [];
<<<<<<< HEAD
=======
var canvas = app.canvas = document.getElementById('game');
var context = app.context = canvas.getContext('2d');
>>>>>>> feature/game_engine

/* Our callback to perform action on each object */
app.action = function(objects) {
    var canvas = document.getElementById('game');
    var context = canvas.getContext('2d');

    while(objects.length > 0) {
        var object = objects.pop();
<<<<<<< HEAD
        console.log(object);
        var img = getImage(object);
        console.log(img);
        img.onload = function () {
            context.drawImage(this, this.setAtX, this.setAtY);
        };
    }

    function getImage(object) {

=======
        var img = getImage(object);
        context.drawImage(img, img.setAtX, img.setAtY);
    }

    function getImage(object) {
>>>>>>> feature/game_engine
        if (list[object.id] !== undefined) {
            var image = list[object.id];
        }
        else {
            var image = new Image();
<<<<<<< HEAD
        }
        image.src = object.sprite;
        image.setAtX = object.dx;
        image.setAtY = object.dy;
        list[object.id] = image;
=======
            image.src = object.sprite;
            list[object.id] = image;
        }
        image.setAtX = object.pos.x;
        image.setAtY = object.pos.y;
>>>>>>> feature/game_engine
        return image;
    }
};
