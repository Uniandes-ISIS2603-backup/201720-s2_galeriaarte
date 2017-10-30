(function (ng) {
    var mod = ng.module("comentarioModule");
    mod.constant("comentarioContext", "api/comentarios");
    mod.controller("comentarioCtrl", ['$scope', '$http', 'comentarioContext',
        function ($scope, $http, comentarioContext) {
            $http.get('data/comentarios.json').then(function (response) {
                $scope.comentarioRecords = response.data;
            });
        }
    ]);
}
)(angular);