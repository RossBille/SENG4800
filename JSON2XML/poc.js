var js2xmlparser = require('js2xmlparser');
var game = {};
var levels = []; 

(function() {
    /* Build our current level */
    var level = {
        id: 0,
        name: "Level One",
        background_id: 1,
        objectList: {objects: []},
        events: []
    };
    /* This is a mockup of our objects */
    var object0 = {
        id: "0",
        name: "Object 0",
        sprite_id: "101",
        shape: "circle",
        visible: true,
        start_pos: { x:0, y:0},
        start_vel: "100",
        start_acc: "200"
    };
    var object1 = {
        id: "1",
        name: "Object 1",
        sprite_id: "101",
        shape: "circle",
        visible: true,
        start_pos: { x:0, y:0},
        start_vel: "100",
        start_acc: "200"
    };
    var object2 = {
        id: "2",
        name: "Object 2",
        sprite_id: "101",
        shape: "circle",
        visible: true,
        start_pos: { x:0, y:0},
        start_vel: "100",
        start_acc: "200"
    };
    var object3 = {
        id: "3",
        name: "Object 3",
        sprite_id: "101",
        shape: "circle",
        visible: true,
        start_pos: { x:0, y:0},
        start_vel: "100",
        start_acc: "200"
    };
    /* Here we're adding our objects into the current level */
    level.objectList.objects.push(object0);
    level.objectList.objects.push(object1);
    level.objectList.objects.push(object2);
    level.objectList.objects.push(object3);

    /* Now we need to build our events */
    var event = {
        id: 0,
        type: "collision",
        actions: [] 
    };
    var action0 = {
        id: 0,
        type: "collision"
    };
    var action1 = {
        id: 1,
        type: "collision"
    };
    var action2 = {
        id: 2,
        type: "collision"
    };
    
    event.actions.push(action0);
    event.actions.push(action1);
    event.actions.push(action2);

    level.events.push(event);


    /* Once we've defined all objects, events and actions in the level
     * we push our level to the global levels object 
     */
    levels.push(level);

    console.log(JSON.stringify(levels));
    console.log(js2xmlparser("levels", levels));

})();
