(function (ng) {
    var mod = ng.module("clienteModule");
    mod.constant("clientesContext", "api/cliente");
    mod.controller("clienteCtrl", ['$scope', '$http', 'clientesContext', '$state',
        function ($scope, $http, clientesContext, $state) {
            //$http.get('data/clientes.json').then(function (response) {
            $http.get(clientesContext).then(function (response) {
                $scope.clientesRecords = response.data;
            });
            
            if ($state.params.clienteId !== undefined) {
                $http.get(clientesContext + '/' + $state.params.clienteId).then(function (response) {
                    $scope.comentariosRecords = response.data.comentarios;
                    $scope.currentCliente = response.data;
                });
            }
        }
    ]);
})(window.angular);