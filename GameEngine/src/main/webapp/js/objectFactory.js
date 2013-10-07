/* Factory */
var app = app || {};

app.ObjectFactory = (function() {
    var Constructor;

    Constructor = function() {};

    Constructor.prototype.createObject = function(options) {
        this.object = app.Object;
        return new this.object(options);
    };

    return Constructor;
})();
