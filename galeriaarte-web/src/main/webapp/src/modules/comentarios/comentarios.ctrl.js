(function (ng) {
    var mod = ng.module("comentarioModule");
    mod.constant("comentarioContext", "api/comentarios");
    mod.controller("comentarioCtrl", ['$scope', '$http', 'comentarioContext', '$state',
        function ($scope, $http, comentarioContext, $state) {
            $http.get('comentarioContext').then(function (response) {
                $scope.comentarioRecords = response.data;
            });
            
            if ($state.params.id !== undefined) {
                $http.get(comentarioContext + '/' + $state.params.id).then(function (response) {
                    $scope.currentComentario = response.data;
                });
            }
        }
    ]);
}
)(window.angular);