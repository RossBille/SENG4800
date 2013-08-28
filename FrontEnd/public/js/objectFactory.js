/* Factory */
var app = app || {};

app.objectFactory = (function() {
    var Constructor;

    Constructor = function() {};

    Constructor.prototype.createObject = function(options) {
        this.object = app.Object;
        return new this.object(options);
    };

    return Constructor;
})();
