(function (ng) {
    var mod = ng.module("clienteModule");
    mod.constant("clientesContext", "api/cliente");
    mod.controller("clienteCtrl", ['$scope', '$http', 'clientesContext', '$state',
        function ($scope, $http, clientesContext, $state) {
            //$http.get('data/clientes.json').then(function (response) {
            $http.get(clientesContext).then(function (response) {
                $scope.clientesRecords = response.data;
            });
        }
    ]);
})(angular);