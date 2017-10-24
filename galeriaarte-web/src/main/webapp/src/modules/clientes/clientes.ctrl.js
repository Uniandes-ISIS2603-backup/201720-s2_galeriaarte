(function (ng) {
    var mod = ng.module("clienteModule");
    mod.constant("clientesContext", "api/clientes");
    mod.controller("clienteCtrl", ['$scope', '$http', 'clientesContext',
        function ($scope, $http, clienteContext) {
            $http.get('data/clientes.json').then(function (response) {
                $scope.clientesRecords = response.data;
            });
        }
    ]);
}
)(angular);