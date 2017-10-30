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
//    mod.controller("clienteCtrl", ['$scope', '$http', 'clientesContext', function ($scope, $http, clientesContext) {
//            
//            // inicialmente el listado de ciudades está vacio
//            $scope.records = {};
//            // carga las ciudades
//            $http.get(clientesContext).then(function (response) {
//                $scope.records = response.data;
//            });
//}
//]);
})(angular);