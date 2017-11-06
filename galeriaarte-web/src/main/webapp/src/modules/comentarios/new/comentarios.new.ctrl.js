(function (ng) {
    var mod = ng.module("comentarioModule");
    mod.constant("comentariosContext", "api/comentarios");
    mod.controller('comentarioNewCtrl', ['$scope', '$http', 'comentariosContext', '$state', 'clientesContext', '$rootScope',
        function ($scope, $http, comentariosContext, $state, clientesContext, $rootScope) {
            $rootScope.edit = false;
            $scope.createComentario = function () {
                $http.post(comentariosContext, {
                    id: 0,
                    contenido: $scope.comentarioContenido
                }).then(function (response) {
                    //Author created successfully
                    $state.go('comentariosList', {comentarioId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(window.angular);