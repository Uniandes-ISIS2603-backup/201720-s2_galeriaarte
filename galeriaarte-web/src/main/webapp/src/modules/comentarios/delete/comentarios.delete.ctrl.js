(function (ng) {
    var mod = ng.module("comentarioModule");
    mod.constant("comentariosContext", "api/comentarios");
    mod.controller('comentarioDeleteCtrl', ['$scope', '$http', 'comentariosContext', '$state',
        function ($scope, $http, comentariosContext, $state) {
            var idComentario = $state.params.comentarioId;
            $scope.deleteComentario = function () {
                $http.delete(comentariosContext + '/' + idComentario, {}).then(function (response) {
                    $state.go('comentariosList', {comentarioId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(window.angular);