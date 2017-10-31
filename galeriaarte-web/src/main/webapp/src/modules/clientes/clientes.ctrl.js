(function (ng) {
    var mod = ng.module("clienteModule");
    mod.constant("clientesContext", "api/cliente");
    mod.controller("clienteCtrl", ['$scope', '$http', 'clientesContext', '$state',
        function ($scope, $http, clientesContext, $state) {
            //$http.get('data/clientes.json').then(function (response) {
            $http.get(clientesContext).then(function (response) {
                $scope.clientesRecords = response.data;
            });
            
            if ($state.params.id !== undefined) {
                $http.get(clientesContext + '/' + $state.params.id).then(function (response) {
                    $scope.booksRecords = response.data.books;
                    $scope.currentCliente = response.data;
                });
            }
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
})(window.angular);