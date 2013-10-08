describe("App module", function () {
    beforeEach(module("App"));

    describe("ConfigController", function () {
        var scope, controller;

        beforeEach(inject(function ($rootScope, $controller) {
            scope = $rootScope.$new();
            controller = $controller;

            // Create the controller.
            controller("ConfigController", {$scope: scope});
        }));

        it ("will initialize its values", function () {
            // Test the scope for values
            expect(scope.game).not.toBeNull();
            expect(scope.canvas).not.toBeNull();
        });

        /*it ("will charge the same for one screen either front or back and the cost will be > $0", function () {
            var frontScreenCost = scope.screensCharge(1, 0);
            var backScreenCost = scope.screensCharge(0, 1);

            expect(frontScreenCost).toBe(backScreenCost);
            expect(frontScreenCost).toBeGreaterThan(0);
        });*/
    });
});