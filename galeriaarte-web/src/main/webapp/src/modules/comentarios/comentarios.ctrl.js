(function (ng) {
    var mod = ng.module("comentarioModule");
    mod.constant("comentarioContext", "api/comentarios");
    mod.controller("comentarioCtrl", ['$scope', '$http', 'comentarioContext', '$state',
        function ($scope, $http, comentarioContext, $state) {
//            $http.get('data/comentarios.json').then(function (response) {
            $http.get(comentarioContext).then(function (response) {
                $scope.comentariosRecords = response.data;
            });
            
            if(($state.params.comentarioId !== undefined) && ($state.params.comentarioId !== null)) {
                $http.get(comentarioContext + '/' + $state.params.comentarioId).then(function (response) {
//                    $scope.clientesRecords = response.data.cliente;
                    $scope.currentComentario = response.data;
                });
            }
        }
    ]);
}
)(window.angular);