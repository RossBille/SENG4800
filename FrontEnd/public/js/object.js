var app = app || {};

app.Object = (function() {

    var Constructor;

    Constructor = function(options) {
        this.id = options.id;
        this.sprite = options.sprite;
        this.visible = options.visible;
        this.x = options.dx;
        this.y = options.dy;
    }

    /* Getters */
    Constructor.prototype.getID = function() {
        return this.id;
    }
    Constructor.prototype.getSprite = function () {
        return this.sprite;
    }
    Constructor.prototype.isVisible = function() {
        return this.visible;
    }
    Constructor.prototype.getX = function() {
        return this.x;
    }
    Constructor.prototype.getY = function() {
        return this.y;
    }
    /* Setters */
    Constructor.prototype.setID = function(id) {
        this.id = id;
    }
    Constructor.prototype.setSprite = function (sprite) {
        this.sprite = sprite;
    }
    Constructor.prototype.isVisible = function(visible) {
        this.visible = visible;
    }
    Constructor.prototype.setX = function(x) {
        this.x = x;
    }
    Constructor.prototype.setY = function(y) {
        this.y = y;
    } 

    return Constructor;

})();
