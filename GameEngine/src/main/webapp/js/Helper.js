var app = app || {};

/* Our callback to perform action on each object */
app.Helper = (function() {

    var Constructor = function(list) {
        this.canvas = app.canvas = document.getElementById('game');
        this.context = app.context = this.canvas.getContext('2d');

        if (typeof list === 'undefined') {
            this.list = app.list || [];
        }
        else {
            this.list = list;
        }
    }

    Constructor.prototype = {

        getImage: function(object) {
            if (this.list[object.id] !== undefined) {
                var image = this.list[object.id];
            }
            else {
                var image = new Image();
            }
            image.src = object.imageUrl;
            this.list[object.id] = image;
            image.setAtX = object.outputPos.x;
            image.setAtY = object.outputPos.y;

            return image;
        },

        draw: function(objects) {
            this.context.clearRect(0, 0, this.canvas.width, this.canvas.height);

            if (objects.length <= 0) {
                return new RangeError("Array must be greater than zero")
            }

            while (objects.length > 0) {
                var object = objects.pop();
                var img = this.getImage(object);
                this.context.drawImage(img, img.setAtX, img.setAtY)
            }
            return this.list;
        }
    }
    return Constructor;
})();
