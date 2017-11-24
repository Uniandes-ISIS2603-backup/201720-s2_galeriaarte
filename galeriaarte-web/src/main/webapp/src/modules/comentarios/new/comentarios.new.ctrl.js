(function (ng) {
    var mod = ng.module("comentarioModule");
    mod.constant("comentariosContext", "api/comentarios");
    mod.controller('comentarioNewCtrl', ['$scope', '$http', '$state', '$rootScope',
        function ($scope, $http, $state, $rootScope) {
            $rootScope.edit = false;
            $scope.createComentario = function () {
                $http.post('api/cliente/' + $scope.comentarioClienteId + '/comentarios', {
                    id: 0,
                    contenido: $scope.comentarioContenido
                }).then(function (response) {
                    $state.go('comentariosList', {comentarioId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(window.angular);