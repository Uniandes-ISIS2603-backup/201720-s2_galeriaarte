(function (ng) {
    var mod = ng.module("comentarioModule");
    mod.constant("comentariosContext", "api/comentarios");
    mod.controller('comentarioNewCtrl', ['$scope', '$http', '$state', '$rootScope',
        function ($scope, $http, $state, $rootScope) {
            $rootScope.edit = false;
            $scope.createComentario = function () {
                
                $http.get('api/obras/' + $state.params.obraId).then(function (responseO) 
                {
                $http.get('api/cliente/' + $rootScope.currentId).then(function (response)
                {                
                $http.post('api/comentarios', 
                {
                    id: 0,
                    contenido: $scope.comentarioContenido,
                    cliente: response.data,
                    obra: responseO.data
                })
                .then(function (response) 
               {
                    $state.go('comentariosList', {comentarioId: response.data.id}, {reload: true});
                });});});
            };
        }
    ]);
}
)(window.angular);