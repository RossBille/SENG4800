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
        console.log('x:' + img.setAtX);
        console.log('y:' + img.setAtY);
        context.drawImage(img, img.setAtX, img.setAtY);
    }
    
    function getImage(object) {
        if (list[object.id] !== undefined) {
            var image = list[object.id];
        }
        else {
            var image = new Image();
            image.src = object.imageUrl;
            list[object.id] = image;
        }
<<<<<<< HEAD
        image.setAtX = object.outputPos.x;
        image.setAtY = object.outputPos.y;
=======
        image.setAtX = object.outputPos.x;// * 2.4;
        image.setAtY = object.outputPos.y;// * 2.2;
>>>>>>> 02d53db19b927f377b5bad1fa04f916779beeca5
        return image;
    }
};
