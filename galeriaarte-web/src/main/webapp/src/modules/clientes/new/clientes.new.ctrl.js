(function (ng) {
    var mod = ng.module("clienteModule");
    mod.constant("clientesContext", "api/cliente");
    mod.controller('clienteNewCtrl', ['$scope', '$http', 'clientesContext', '$state', 'booksContext', '$rootScope',
        function ($scope, $http, clientesContext, $state, booksContext, $rootScope) {
            $rootScope.edit = false;
            $scope.createCliente = function () {
                $http.post(clientesContext, {
                    name: $scope.name,
                    tipoTarjeta: $scope.tipoTarjeta,
                    numTarjeta: $scope.numTarjeta
                }).then(function (response) {
                    //Author created successfully
                    $state.go('clientesList', {id: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(window.angular);