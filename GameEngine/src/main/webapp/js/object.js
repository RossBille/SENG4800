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
    Constructor.prototype = {
        getID: function() {
            return this.id;
        },
        getSprite: function() {
            return this.sprite;
        },
        isVisible: function() {
            return this.visible;
        },
        getX: function() {
            return this.x;
        },
        getY: function() {
            return this.y;
        },
        /* Setters */
        setID: function(id) {
            this.id = id;
        },
        setSprite: function(sprite) {
            this.sprite = sprite;
        },
        isVisible: function(visible) {
            this.visible = visible;
        },
                setX: function(x) {
            this.x = x;
        },
        setY: function(y) {
            this.y = y;
        },
    }
    return Constructor;

})();
