assert = chai.assert;
var expect = chai.expect;

describe('Array', function() {
    describe('#indexOf()', function() {
        it('should return -1 when the value is not present', function() {
            assert(-1, [1, 2, 3].indexOf(5));
            expect([1, 2, 3].indexOf(5)).to.equal(-1);
        })
    })
})

describe('JQuery', function() {
    var json;
    before(function(done) {
        $.getJSON('http://localhost:8080/GameEngine/setup?callback=?', function(response) {
            json = response;
            done();
        })
    })

    describe('#getJSON()', function() {
        it('should contain image URLs', function() {
            expect(json).to.have.property('objectIds');
        })
        it('should contain object IDs', function() {
            expect(json).to.have.property('objectIds');
        })
        it('should have non-zero length image URLs', function() {
            expect(json.imageUrls.length).to.be.above(0);
        })
        it('should have non-zero length object IDs', function() {
            expect(json.objectIds.length).to.be.above(0);
        })
    })
})

describe('ObjectFactory', function() {
    var factory = new app.ObjectFactory;
    var options;

    before(function(done) {
        options = {
            id: 1,
            sprite: "test",
            visible: true,
            dx: 10,
            dy: 10
        }
        done();
    })
    describe('#createObject', function() {
        it('should create an object', function() {
            var object = factory.createObject(options);
            expect(object).to.be.an.instanceOf(app.Object);
        })
    })
})

describe('Object', function() {
    var factory;
    var options;
    var object;

    before(function(done) {
        factory = new app.ObjectFactory;
        options = {
            id: 1,
            sprite: "test",
            visible: true,
            dx: 10,
            dy: 10
        }
        object = factory.createObject(options);
        done();
    })

    describe('Correct API', function() {
        it('should return correct ID', function() {
            expect(object.getID()).to.equal(1);
        })
        it('should return correct sprite', function() {
            expect(object.getSprite()).to.equal('test');
        })
        it('should return correct X coordinate', function() {
            expect(object.getX()).to.equal(10);
        })
        it('should return correct Y coordinate', function() {
            expect(object.getY()).to.equal(10);
        })
        it('should correctly set the X coord', function() {
            object.setX(20);
            expect(object.getX()).to.equal(20);
        })
        it('should correctly set the Y coord', function() {
            object.setY(20);
            expect(object.getY()).to.equal(20);
        })
        it('should correctly set the sprite', function() {
            object.setSprite('testing');
            expect(object.getSprite()).to.equal('testing');
        })
        it('should have correct API', function() {
            expect(object).to.have.property('getID');
        });
        it('should have correct API', function() {
            expect(object).to.have.property('getSprite');
        });
        it('should have correct API', function() {
            expect(object).to.have.property('isVisible');
        });
        it('should have correct API', function() {
            expect(object).to.have.property('getX');
        });
        it('should have correct API', function() {
            expect(object).to.have.property('getY');
        });
        it('should have correct API', function() {
            expect(object).to.have.property('setID');
        });
        it('should have correct API', function() {
            expect(object).to.have.property('setSprite');
        });
        it('should have correct API', function() {
            expect(object).to.have.property('setX');
        });
        it('should have correct API', function() {
            expect(object).to.have.property('setY');
        });
        it('should have correct API', function() {
            expect(object).to.have.property('setVisible');
        });
    })
})

describe('WebSocket', function() {
    var websocket;

    before(function() {
        websocket = new app.Websocket('ws://localhost:8080/GameEngine/output');
    })

    describe('Error handling open sockets', function() {
        it('should return not return Error', function() {
            expect(websocket.open()).to.not.be.instanceOf(Error);
        })
    })

    describe('Implements websocket API', function() {
        it('should have open method', function() {
            expect(websocket).to.have.property('open');
        })
        it('should have onopen method', function() {
            expect(websocket).to.have.property('close');
        })
    })

    afterEach(function(done) {
        window.setTimeout(function() {
            websocket.close();
            done();
        }, 1000);
    })
})

describe('Action', function() {
    var canvas;
    var context;
    var action = new app.Helper;

    before(function() {
        canvas = document.getElementById('game');
        context = canvas.getContext('2d');
    })

    describe('#init', function() {
        it('should throw a RangeError when array is zero', function() {
            var objects = [];
            expect(action.draw(objects)).to.be.instanceOf(RangeError);
        })
    })
    describe("Visual Test", function() {
        it('should move the object over the screen', function() {
            var o = {
                id: 1,
                imageUrl: "images/ball.png",
                outputPos: {
                    x: 100,
                    y: 100
                }
            }
            var list = [];
            list.push(o);

            var newList = action.draw(list);
            expect(newList[1].src).to.equal(window.location.href + o.imageUrl);
        })
    })
})