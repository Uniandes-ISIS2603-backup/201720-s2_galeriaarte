(function (ng) {
    var mod = ng.module("clienteModule");
    mod.constant("clienteContext", "api/clientes");
    mod.controller('clienteCtrl', ['$scope', '$http', 'clienteContext',
        function ($scope, $http, editorialContext) {
            $http.get('data/clientes.json').then(function (response) {
                $scope.clientesRecords = response.data;
            });
        }
    ]);
}
)(angular);